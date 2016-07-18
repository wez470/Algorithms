public class QuickSort {
    public static void quickSort(int[] array) {
       quickSortHelper(array, 0, array.length - 1); 
    }


    public static void quickSortHelper(int[] array, int start, int end) {
        if (start < end) {
            int partition = partition(array, start, end);
            quickSortHelper(array, start, partition - 1);
            quickSortHelper(array, partition + 1, end); 
        }
    }

    public static int partition(int[] array, int start, int end) {
        int last = array[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (array[j] <= last) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

        }
        int temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] test = new int[] { 10, 9, 5, 4, 2, 3, 1, 6, 8, 7 };
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        
        quickSort(test);

        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
    }
}
