public class StringReverse {
    public static char[] reverse(String s) {
        char[] str = s.toCharArray();
        for(int i = 0, j = s.length() - 1; i < s.length() / 2; i++, j--) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
        return str;
    }

    public static void main(String[] args) {
        char[] rev = reverse(args[0]);
        for (char c : rev) {
            System.out.print(c);
        }
        System.out.println();
    }
}
