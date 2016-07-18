public class HeapSort {
    public static void heapify(int[] array, int i, int size) {
        int left = i * 2;
        int right = i * 2 + 1;
        int largest = i;
        if (left < size && array[left] > array[i]) {
            largest = left;
        }
        if (right < size && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(array, largest, size);
        }
    }

    public static void main(String[] args) {
        int[] test = new int[] { 10, 9, 5, 4, 2, 3, 1, 6, 8, 7 };
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        
        for (int i = test.length / 2; i > 0; i--) {
            heapify(test, i, test.length);
        }
        int size = test.length;
        for (int i = test.length - 1; i > 0; i--) {
            int temp = test[i];
            test[i] = test[0];
            test[0] = temp;
            size--;
            heapify(test, 0, size);
        }

        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
    }
}
