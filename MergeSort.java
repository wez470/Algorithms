import java.util.List;
import java.util.ArrayList;

public class MergeSort {
    public static void mergeSort(int[] array) {
        mergeSortHelper(array, 0, array.length - 1);
    }

    public static void mergeSortHelper(int[] array, int min, int max) {
        if (max - min < 1) {
            return;
        }
        int mid = (max + min) / 2;
        mergeSortHelper(array, min, mid);
        mergeSortHelper(array, mid + 1, max);
        combine(array, min, mid, max);
    }

    public static void combine(int[] array, int min, int mid, int max) {
        int i = min;
        int j = mid + 1;
        int k = 0;
        int[] holder = new int[max - min + 1];
        while (i < mid + 1 || j < max + 1) {
            if (j == max + 1 || (i < mid + 1 && array[i] < array[j])) {
                holder[k] = array[i];
                k++;
                i++;
            }
            else if (i == mid + 1 || (j < max + 1 && array[j] < array[i])) {
                holder[k] = array[j];
                k++;
                j++;
            }
        }

        for (int l = min, m = 0; l < max + 1; l++, m++) {
            array[l] = holder[m];
        }
    }
    
    public static void main(String[] args) {
        int[] test = new int[] { 10, 9, 5, 4, 2, 3, 1, 6, 8, 7 };
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        mergeSort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        
        int[] test2 = new int[21];
        for (int i = 20, j = 0; i >= 0; i--, j++) {
            test2[j] = i;
        }
        for (int i = 0; i < test2.length; i++) {
            System.out.print(test2[i] + " ");
        }
        System.out.println();
        mergeSort(test2);
        for (int i = 0; i < test2.length; i++) {
            System.out.print(test2[i] + " ");
        }
        System.out.println();
    }
}
