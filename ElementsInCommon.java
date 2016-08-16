public class ElementsInCommon {
    /*
     * Input must be sorted arrays
     */
    public static int elementsInCommon(int[] a1, int[] a2) {
        int i = 0;
        int j = 0;
        int count = 0;
        while (true) {
            System.out.println(i + " " + j + " " + count);
            if (i < a1.length && a1[i] < a2[j]) {
                i++;
            }
            else if (j < a2.length && a2[j] < a1[i]) {
                j++;
            }
            else if (i < a1.length && j < a2.length && a1[i] == a2[j]) {
                i++;
                j++;
                count++;
            }
            
            if (i >= a1.length || j >= a2.length) {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
          int[] a1 = new int[] {0, 2, 5, 5, 6, 7, 8};  
          int[] a2 = new int[] {1, 3, 5, 6, 8, 9, 10};  
          System.out.println(elementsInCommon(a1, a2)); // 3
    }
}
