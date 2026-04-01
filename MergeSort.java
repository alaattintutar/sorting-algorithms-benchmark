public class MergeSort {
    public void mergeSort(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        int currSize = 1;
        while (currSize < n){
            int left = 0;
            while (left < n - 1){
                int mid = Math.min(left + (currSize - 1), n - 1);
                int right = Math.min(left + (currSize * 2) - 1, n - 1);
                merge(arr, temp, left, mid, right);
                left = left + 2 * currSize;
            }
            currSize *= 2;
        }
    }
    public void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[k] = arr[i];
                i++;
            }
            else{
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i <= mid){
            temp[k] = arr[i];
            i++;
            k++;
        }
        while (j <= right) {
            temp[k] = arr[j];
            j++;
            k++;
        }
        for (int l = left; l <= right; l++){
            arr[l] = temp[l];
        }
    }
}
