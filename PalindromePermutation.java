import java.util.Map;
import java.util.HashMap;

/*
 * Check to see if a string can be permutated in such a way that it
 * makes a palindrome.
 */
public class PalindromePermutation {
    public static boolean palPerm(String str) {
        str.toLowerCase();
        Map<Character, Integer> letters = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (letters.containsKey(c)) {
                int count = letters.get(c);
                letters.put(c, count + 1);
            }
            else {
                letters.put(c, 1);
            }
        }
        boolean oneOdd = false;
        boolean oddLength = !(str.length() % 2 == 0);
        for (Integer i : letters.values()) {
            if (i % 2 != 0) {
                if (oddLength && !oneOdd) {
                    oneOdd = true;
                }
                else if (oddLength && oneOdd) {
                    return false;
                }
                if (!oddLength) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(palPerm("tacotaco")); // True
        System.out.println(palPerm("tadcotaco")); // True
        System.out.println(palPerm("t adcotaco")); // False
        System.out.println(palPerm("tadcotack")); // False
        System.out.println(palPerm("")); // True
        System.out.println(palPerm("d")); // True
        System.out.println(palPerm("dd")); // True
        System.out.println(palPerm("df")); // False
        System.out.println(palPerm("d d")); // True
    }
}
