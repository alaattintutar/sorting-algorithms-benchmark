public class ShellSort {
    public void shellSort(int[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int j = i;
                while (j >= h && arr[j] > arr[j - h]) {
                    int temp = arr[j];
                    arr[j] = arr[j - h];
                    arr[j - h] = temp;
                    j -= h;
                }
            }
            h = h / 3;
        }
    }
}
