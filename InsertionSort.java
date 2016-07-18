public class InsertionSort {
    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int shiftIndex = 0;
            for (int j = 0; j <= i; j++) {
                shiftIndex = j;
                if (array[i] < array[j]) {
                    break;
                }
            }

            int temp = array[i];
            for (int j = i; j > shiftIndex; j--) {
                array[j] = array[j - 1]; 
            }
            array[shiftIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {2, 3, 5, 6, 1, 8, 10, 9, 4, 7};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        int[] a2 = new int[25];
        for (int i = 0; i < a2.length; i++) {
            a2[i] = 25 - i;
            System.out.print(a2[i] + " ");
        }
        System.out.println();
        sort(a2);
        for (int i = 0; i < a2.length; i++) {
            System.out.print(a2[i] + " ");
        }
        System.out.println();

    }
}
