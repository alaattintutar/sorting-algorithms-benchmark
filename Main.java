import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class Main {

    private static final int[] INPUT_SIZES = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};
    private static final int NUM_RUNS = 10;

    public static void main(String[] args) {
        String csvFile = "all_stocks_5yr.csv";
        int[] originalData = readVolumeData(csvFile);

        String[] algorithms = {"Quick Sort", "Insertion Sort", "Merge Sort", "Shell Sort", "Radix Sort"};

        double[][][] allResults = new double[3][algorithms.length][INPUT_SIZES.length];

        for (int sizeIndex = 0; sizeIndex < INPUT_SIZES.length; sizeIndex++) {
            int n = INPUT_SIZES[sizeIndex];

            int[] randomData = Arrays.copyOfRange(originalData, 0, n);

            int[] sortedData = Arrays.copyOf(randomData, n);
            Arrays.sort(sortedData);

            int[] reversedData = new int[n];
            for (int i = 0; i < n; i++) {
                reversedData[i] = sortedData[n - 1 - i];
            }

            // RANDOM DATA TESTS
            allResults[0][0][sizeIndex] = testAlgorithm("QuickSort", randomData);
            allResults[0][1][sizeIndex] = testAlgorithm("InsertionSort", randomData);
            allResults[0][2][sizeIndex] = testAlgorithm("MergeSort", randomData);
            allResults[0][3][sizeIndex] = testAlgorithm("ShellSort", randomData);
            allResults[0][4][sizeIndex] = testAlgorithm("RadixSort", randomData);

            // SORTED DATA TESTS
            allResults[1][0][sizeIndex] = testAlgorithm("QuickSort", sortedData);
            allResults[1][1][sizeIndex] = testAlgorithm("InsertionSort", sortedData);
            allResults[1][2][sizeIndex] = testAlgorithm("MergeSort", sortedData);
            allResults[1][3][sizeIndex] = testAlgorithm("ShellSort", sortedData);
            allResults[1][4][sizeIndex] = testAlgorithm("RadixSort", sortedData);

            // REVERSED DATA TESTS
            allResults[2][0][sizeIndex] = testAlgorithm("QuickSort", reversedData);
            allResults[2][1][sizeIndex] = testAlgorithm("InsertionSort", reversedData);
            allResults[2][2][sizeIndex] = testAlgorithm("MergeSort", reversedData);
            allResults[2][3][sizeIndex] = testAlgorithm("ShellSort", reversedData);
            allResults[2][4][sizeIndex] = testAlgorithm("RadixSort", reversedData);
        }

        printTable("Random Input Data Timing Results in ms", algorithms, allResults[0]);
        printTable("Sorted Input Data Timing Results in ms", algorithms, allResults[1]);
        printTable("Reversely Sorted Input Data Timing Results in ms", algorithms, allResults[2]);


        showChart("Tests on Random Data", algorithms, allResults[0]);
        showChart("Tests on Sorted Data", algorithms, allResults[1]);
        showChart("Tests on Reversely Sorted Data", algorithms, allResults[2]);
    }


    private static double testAlgorithm(String algoName, int[] sourceData) {
        long totalTime = 0;
        int n = sourceData.length;

        QuickSort qs = new QuickSort();
        InsertionSort is = new InsertionSort();
        MergeSort ms = new MergeSort();
        ShellSort ss = new ShellSort();
        RadixSort rs = new RadixSort();

        for (int i = 0; i < NUM_RUNS; i++) {
            int[] testArray = Arrays.copyOf(sourceData, n);
            long startTime = System.nanoTime();

            switch (algoName) {
                case "QuickSort":
                    qs.quickSort(testArray, 0, testArray.length - 1);
                    break;
                case "InsertionSort":
                    is.insertionSort(testArray);
                    break;
                case "MergeSort":
                    ms.mergeSort(testArray);
                    break;
                case "ShellSort":
                    ss.shellSort(testArray);
                    break;
                case "RadixSort":
                    int max = Arrays.stream(testArray).max().orElse(0);
                    int d = String.valueOf(max).length();
                    rs.radixSort(testArray, d);
                    break;
            }

            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }

        double avgTimeMs = (totalTime / (double) NUM_RUNS) / 1_000_000.0;
        return avgTimeMs;
    }

    private static int[] readVolumeData(String filename) {
        ArrayList<Integer> volumes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 6) {
                    try {
                        volumes.add(Integer.parseInt(values[5].trim()));
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        int[] arr = new int[volumes.size()];
        for (int i = 0; i < volumes.size(); i++) {
            arr[i] = volumes.get(i);
        }
        return arr;
    }

    private static void printTable(String title, String[] algos, double[][] results) {
        System.out.println("\n" + title);
        System.out.printf("%-15s", "Algorithm");
        for (int size : INPUT_SIZES) {
            System.out.printf("%10d", size);
        }
        System.out.println();

        for (int i = 0; i < algos.length; i++) {
            System.out.printf("%-15s", algos[i]);
            for (int j = 0; j < INPUT_SIZES.length; j++) {
                System.out.printf("%10.3f", results[i][j]);
            }
            System.out.println();
        }
    }

    private static void showChart(String title, String[] algos, double[][] results) {
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .xAxisTitle("Input Size").yAxisTitle("Time in Milliseconds").build();

        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        double[] xData = Arrays.stream(INPUT_SIZES).asDoubleStream().toArray();
        for (int i = 0; i < algos.length; i++) {
            XYSeries series = chart.addSeries(algos[i], xData, results[i]);
            series.setMarker(SeriesMarkers.CIRCLE);
        }

        new SwingWrapper<>(chart).displayChart();
    }
}