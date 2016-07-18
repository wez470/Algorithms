public class SubString {
    public static boolean substr(char[] str, char[] sub) {
        if (sub.length == 0) {
            return true;
        }

        boolean matchFound = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == sub[0]) {
                matchFound = true;
                for (int j = 0; j < sub.length; j++) {
                    if (i + j >= str.length || str[i + j] != sub[j]) {
                        matchFound = false;
                        break;
                    }
                }

                if (matchFound) {
                    break;
                }
            }
        }

        return matchFound;
    }

    public static void main(String[] args) {
        char[] ca = "TEST TEST TEST".toCharArray();
        char[] sub1 = {'T', 'E', 'S', 'T'};
        char[] sub2 = {'t', 'e', 's', 't'};
        System.out.println(substr(ca, sub1));
        System.out.println(substr(ca, sub2));
        System.out.println(substr(ca, "TEST TEST".toCharArray()));
        System.out.println(substr(ca, "TESTTEST".toCharArray()));
        System.out.println(substr(ca, ca));
        System.out.println(substr(ca, "".toCharArray()));
        System.out.println(substr(ca, "E".toCharArray()));
    }
}
