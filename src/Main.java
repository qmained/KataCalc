import java.util.TreeMap;

public class Main{

    enum Roman{
        I, II, III, IV, V, VI, VII, VIII, IX, X
    }

    public static class RomanNumber {

        private final static TreeMap<Integer, String> map = new TreeMap<>();

        static {

            map.put(1000, "M");
            map.put(900, "CM");
            map.put(500, "D");
            map.put(400, "CD");
            map.put(100, "C");
            map.put(90, "XC");
            map.put(50, "L");
            map.put(40, "XL");
            map.put(10, "X");
            map.put(9, "IX");
            map.put(5, "V");
            map.put(4, "IV");
            map.put(1, "I");

        }

        public static String toRoman(int number) {
            int l =  map.floorKey(number);
            if ( number == l ) {
                return map.get(number);
            }
            return map.get(l) + toRoman(number-l);
        }

    }

    public static String calc(String input) throws Exception {
        String[] arr = input.split("\\s");
        if (arr.length != 3)
            throw new Exception();
        int a, b;
        boolean isDigit = Character.isDigit(arr[0].charAt(0));
        if (isDigit){
            a = Integer.parseInt(arr[0]);
            b = Integer.parseInt(arr[2]);
            if (a <= 0 || a >= 10 || b <= 0 || b >= 10)
                throw new Exception();
        }
        else{
            a = Roman.valueOf(arr[0]).ordinal() + 1;
            b = Roman.valueOf(arr[2]).ordinal() + 1;
        }
        int result = switch (arr[1]) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new Exception();
        };
        if (isDigit)
            return Integer.toString(result);
        else{
            if (result <= 0)
                throw new Exception();
            else
                return RomanNumber.toRoman(result);
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println(calc(String.join(" ", args)));
    }
}
