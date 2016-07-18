public class StringPerm {
    public static void permuteStr(String str) {
        permuteStrHelper("", str);
    }

    public static void permuteStrHelper(String permStr, String str) {
        if (str.length() == 0) {
            System.out.println(permStr);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permuteStrHelper(permStr + str.charAt(i), str.substring(0, i) + str.substring(i + 1, str.length()));
        }


    }

    public static void main(String[] args) {
        permuteStr("weston");
    }
}
