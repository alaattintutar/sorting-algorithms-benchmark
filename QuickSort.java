public class QuickSort {
    public void quickSort(int[] arr, int low, int high) {
        int stackSize = high - low + 1;
        int[] stack = new int[stackSize];
        int top = -1;
        top++;
        stack[top] = low;
        top++;
        stack[top] = high;
        while (top >= 0) {
            high = stack[top];
            top--;
            low = stack[top];
            top--;
            int pivot = partition(arr, low, high);
            if (pivot - 1 > low) {
                top++;
                stack[top] = low;
                top++;
                stack[top] = pivot - 1;
            }
            if (pivot + 1 < high) {
                top++;
                stack[top] = pivot + 1;
                top++;
                stack[top] = high;
            }
        }
    }
    public int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i ++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}
