public class RadixSort {
    public int[] radixSort(int[] arr, int d) {
        for (int pos = 1; pos <= d; pos++) {
            arr = countingSort(arr, pos);
        }
        return arr;
    }
    public int[] countingSort(int[] arr, int pos) {
        int[] count = new int[10];
        int[] output = new int[arr.length];
        int size = arr.length;
        int exp = (int) Math.pow(10, pos - 1);
        for(int i=0;i<size;i++){
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i-1];
        }
        for (int i = size - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            count[digit]--;
            output[count[digit]] = arr[i];
        }
        return output;
    }
}
