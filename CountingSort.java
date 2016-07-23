import java.util.ArrayList;
import java.util.List;

public class CountingSort {
    public static List<Integer> countingSort(int[] nums, int max) {
        List<Integer> sorted = new ArrayList<>();
        int[] counts = new int[max + 1];

        for (int i = 0; i < nums.length; i++) {
            counts[nums[i]] += 1;
        }
        
        for (int i = 0; i < max + 1; i++) {
            for (int j = 0; j < counts[i]; j++) {
                sorted.add(i);
            }
        }

        return sorted;
    }

    public static void main(String[] args) {
        int[] test = new int[] { 10, 9, 5, 4, 1, 3, 1, 6, 8, 7 };
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        
        List<Integer> sorted = countingSort(test, 10);

        for (Integer i : sorted) {
            System.out.print(i + " ");
        }
        System.out.println();

        test = new int[0];
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        
        sorted = countingSort(test, 0);

        for (Integer i : sorted) {
            System.out.print(i + " ");
        }
        System.out.println();

        test = new int[] {0, 0, 0, 0, 0, 10, 0, 0, 0, 0};
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        
        sorted = countingSort(test, 10);

        for (Integer i : sorted) {
            System.out.print(i + " ");
        }
        System.out.println();

        test = new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        
        sorted = countingSort(test, 10);

        for (Integer i : sorted) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
