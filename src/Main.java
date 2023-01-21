import java.util.Scanner;
public class Main {
    private static final int INPUT_ERROR = 404;
    private static final int CHECK_TO_RIM = -404;
    private static final String[] RIM_NUMBERS = new String[] {"","I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX", "X"};
    private static final String[] RIM_DECIMAL = new String[] {"","X", "XX", "XXX", "LX", "L",
            "LX", "LXX", "LXXX", "XC", "C"};
    public static void main(String[] args) {
        try{
            System.out.println("Enter 2 numbers and the operation (+, -, /, *) sign between them:");
            Scanner in = new Scanner(System.in);
            System.out.println(calc(in.nextLine()));
            in.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static String calc(String input) throws Exception {
        String result;
        String[] numbers = input.split("[-+*/]");
        if (numbers.length == 2) {
            String number1 = numbers[0].trim();
            String number2 = numbers[1].trim();
            String operation = ((input.split(number1))[1].split(number2))[0].trim();
            int arab = checkNumbers(number1, number2, operation);
            if (arab >= -100 && arab <= 100) {
                result = "" + arab;
            } else if (arab == INPUT_ERROR) {
                throw new Exception("calculator must accept numbers from 1 to 10 as input");
            } else {
                int rimNum1 = arabConvert(number1);
                int rimNum2 = arabConvert(number2);
                if (rimNum1 != INPUT_ERROR && rimNum2 != INPUT_ERROR ) {
                    int rim = checkNumbers("" + rimNum1, "" + rimNum2, operation);
                    if(rim > 0) {
                        result = rimConvert(rim);
                    } else {
                        throw new Exception("in the Roman numeral system , there cannot be less than 1");
                    }
                } else {
                    throw new Exception("the calculator must accept numbers from 1 to 10 " +
                            "in one calculus system as input");
                }

            }
        } else {
            throw new Exception("format of the mathematical operation does not satisfy the task - " +
                    "two operands and one operator (+, -, /, *).");
        }
        return result;
    }
    private static int checkNumbers(String a, String b, String op) {
        int result;
        try {
            int num1 = Integer.parseInt(a);
            int num2 = Integer.parseInt(b);
            if(num1 > 10 || num1 < 1 || num2 < 1 || num2 > 10) {
                result = INPUT_ERROR;
            } else {
                result = switch (op) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    default -> num1 / num2;
                };
            }
            return result;
        }
        catch (Exception e) {
            return CHECK_TO_RIM;
        }
    }
    private static int arabConvert(String src) {
        int result = INPUT_ERROR;
        for(int i = 1; i < RIM_NUMBERS.length; i++)
            if (RIM_NUMBERS[i].equals(src)) {
                result = i;
                break;
            }
        return result;
    }
    private static String rimConvert(int value){
        return RIM_DECIMAL[value / 10] + RIM_NUMBERS[value % 10];
    }
}




