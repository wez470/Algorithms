public class IndividualDigits {
    // `num` must be non-negative.
    public static int sumDigits(long num) {
        int sum = 0;
        while (num > 0) {
            long digit = num % 10;
            num = num / 10;
            sum += digit;
        }
        return sum;
    }

    // `num` must be non-negative.
    public static String toString(long num) {
        if (num == 0) {
            return "0";
        }
        StringBuffer s = new StringBuffer();
        char[] chars = new char[20];
        int i = 19;
        while (num > 0) {
            long digit = num % 10;
            num = num / 10;
            char c = Character.forDigit((int)digit, 10);
            chars[i] = c;
            i--;
        }
        s.append(chars, i + 1, 19 - i);
        return s.toString();
    }
    
    public static void main(String[] args) {
        long num = 1L << 50;
        System.out.println(toString(num));
        System.out.println(num);
        System.out.println(sumDigits(num));
        System.out.println(Character.getNumericValue('9') + 9);
    }
}

