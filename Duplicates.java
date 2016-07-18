import java.util.List;
import java.util.ArrayList;
import java.util.BitSet;

public class Duplicates {
    public static void findDuplicates(List<Integer> nums) {
        BitSet set = new BitSet(nums.size() - 1);
        for (Integer num : nums) {
            boolean duplicate = set.get(num);
            if (duplicate) {
                System.out.println(num);
            }
            else {
                set.set(num);
            }
        }
    }

    public static char[] reverseStr(char[] str) {
        for (int i = 0, j = str.length - 1; i < str.length / 2; i++, j--) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }

        return str;
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(1);
        nums.add(2);
        nums.add(4);
        nums.add(5);
        findDuplicates(nums);
        String s = "TEST TEST TEST";
        char[] ca = s.toCharArray();
        System.out.println(reverseStr(ca));
    }
}
