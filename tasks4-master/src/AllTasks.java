import java.util.ArrayList;

public class AllTasks {

    public static void main(String[] args) {

        // Task 1 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(1);
        String answer1 = essay(10,7, "hello my name is Bessie and this is my essay");
        System.out.println(answer1);
        PrintHelper.blank();

        // Task 2 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(2);
        String[] answer2 = split("((()))(())()()(()())");

        for (int i = 0; i < answer2.length; i++) {
            System.out.print(answer2[i] + " ");
        }

        PrintHelper.blank();
        PrintHelper.blank();

        // Task 3 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(3);
        String answer3_1 = toCamelCase("hello_my_name_is_bob");
        String answer3_2 = toSnakeCase(answer3_1);

        System.out.println(answer3_1);
        System.out.println(answer3_2);
        PrintHelper.blank();

        // Task 4 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(4);
        double[] arr = new double[]{13.25, 15, 30, 1.5};
        String answer4 = overTime(arr);
        System.out.println(answer4);
        PrintHelper.blank();

        // Task 5 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(5);
        String answer5 = BMI("55 kilos", "1.65 meters");
        System.out.println(answer5);
        PrintHelper.blank();

        // Task 6 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(6);
        int answer6 = bugger(999);
        System.out.println(answer6);
        PrintHelper.blank();

        // Task 7 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(7);
        String answer7 = toStarShorthand("77777geff");
        System.out.println(answer7);
        PrintHelper.blank();

        // Task 8 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(8);
        boolean answer8 = doesRhyme("Sam I am!", "Green eggs and HAM.");
        System.out.println(answer8);
        PrintHelper.blank();

        // Task 9 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(9);
        long number1 = 451_999_277L;
        long number2 = 41_177_722_899L;
        boolean answer9 = trouble(number1, number2);
        System.out.println(answer9);
        PrintHelper.blank();

        // Task 10 -----------------------------------------------------------------------------------------------------

        PrintHelper.printTask(10);
        int answer10 = countUniqueBooks("$AA$BBCATT$C$$B$", '$');
        System.out.println(answer10);
        PrintHelper.blank();

    }

    // Task 1 ----------------------------------------------------------------------------------------------------------
    // Formats and returns a string so that each line contains no more than k characters, not counting spaces
    public static String essay(int n, int k, String str){
        String[] words = str.split(" ");
        int current_string_length = 0;
        String answer = "";

        for (int i = 0; i < words.length; i++) {
            if (current_string_length + words[i].length() <= k) {
                answer += words[i];
                answer += " ";
                current_string_length += words[i].length();
            } else {
                answer += "\n";
                answer += words[i];
                answer += " ";
                current_string_length = words[i].length();
            }
        }

        for (int i = 1; i < answer.length(); i++) {
            if (answer.charAt(i) == "\n".charAt(0)) {
                answer = answer.substring(0, i-1) + answer.substring(i);
            }
        }

        answer = answer.substring(0, answer.length()-1);

        return answer;
    }

    // Task 2 ----------------------------------------------------------------------------------------------------------
    // Groups a string into a cluster of brackets
    // "(()())()()" => "(()())" "()" "()"
    public static String[] split(String str) {
        char[] str_char = str.toCharArray();

        ArrayList<String> answer = new ArrayList<>();
        String buffer = "";
        int opened = 0;

        for (int i = 0; i < str_char.length; i++) {
            if (str_char[i] == "(".charAt(0)) { opened += 1; }
            if (str_char[i] == ")".charAt(0)) { opened -= 1; }

            buffer += str_char[i];

            if (opened == 0) {
                answer.add(buffer);
                buffer = "";
            }
        }

        String[] final_answer = new String[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            final_answer[i] = answer.get(i);
        }

        return final_answer;
    }

    // Task 3 ----------------------------------------------------------------------------------------------------------
    // Formats and returns string toCamelCase
    public static String toCamelCase(String str) {
        char[] str_char = str.toCharArray();
        String answer = "";
        int i = 0;

        while (i < str_char.length) {
            if (str_char[i] == '_') {
                answer += Character.toUpperCase(str_char[i+1]);
                i += 2;
            } else {
                answer += str_char[i];
                i += 1;
            }
        }

        return answer;
    }

    // Formats and returns string to_snake_case
    public static String toSnakeCase(String str) {
        char[] str_char = str.toCharArray();
        String answer = "";

        for (int i = 0; i < str_char.length; i++) {
            if (Character.isUpperCase(str_char[i])) {
                answer += "_" + Character.toLowerCase(str_char[i]);
            } else {
                answer += str_char[i];
            }
        }

        return answer;
    }

    // Task 4 ----------------------------------------------------------------------------------------------------------
    // Returns the payment to the employee, taking into account the additional workload
    public static String overTime(double[] arr) {

        double time_start = arr[0];
        double time_end = arr[1];
        double hourly_rate = arr[2];
        double over_time_scale = arr[3];

        double pay = 0;

        if ((17 - time_start > 0) && (time_end >= 17)) {
            pay += (17 - time_start) * hourly_rate;
        } else if ((17 - time_start > 0) && (time_end < 17)) {
            pay += (time_end - time_start) * hourly_rate;
        }

        if ((time_end - 17 > 0) && (time_start <= 17)) {
            pay += (time_end - 17) * (hourly_rate * over_time_scale);
        } else if ((17 - time_start <= 0) && (time_end >= 17)) {
            pay += (time_end - time_start) * hourly_rate * over_time_scale;
        }

        pay = Math.round(pay * 100.0) / 100.0;

        String answer = "$";
        answer += Double.toString(pay);

        int point_id = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == '.') {
                point_id = i;
            }
        }

        if (point_id > answer.length()-3) {answer += "0";}

        return answer;
    }

    // Task 5 ----------------------------------------------------------------------------------------------------------
    // Returns BMI by weight and height
    public static String BMI(String weight, String height) {
        char[] weight_char = weight.toCharArray();
        char[] height_char = height.toCharArray();

        int weight_space_id = 0;
        int height_space_id = 0;

        double weight_value = 0;
        double height_value = 0;

        for (int i = 0; i < weight_char.length; i++) {
            if (weight_char[i] == ' ') {weight_space_id = i;}
        }

        for (int i = 0; i < height_char.length; i++) {
            if (height_char[i] == ' ') {height_space_id = i;}
        }

        if (weight.substring(weight_space_id + 1).equals("kilos")) {
            weight_value = Double.parseDouble(weight.substring(0, weight_space_id));
        } else if (weight.substring(weight_space_id + 1).equals("pounds")) {
            weight_value = Double.parseDouble(weight.substring(0, weight_space_id)) * 0.453592;
        }

        if (height.substring(height_space_id + 1).equals("meters")) {
            height_value = Double.parseDouble(height.substring(0, height_space_id));
        } else if (height.substring(height_space_id + 1).equals("inches")) {
            height_value = Double.parseDouble(height.substring(0, height_space_id)) * 0.02539998628;
        }

        double BMI = weight_value / (height_value * height_value);
        BMI = Math.round(BMI * 10.0) / 10.0;

        String answer = String.valueOf(BMI) + " ";
        if (BMI < 18.5) {
            answer += "Underweight";
        } else if (BMI > 25.0){
            answer += "Overweight";
        } else {
            answer += "Normal weight";
        }

        return answer;
    }

    // Task 6 ----------------------------------------------------------------------------------------------------------
    // Returns the multiplicative constancy of a number
    public static int bugger(int number) {
        char[] number_char = Integer.toString(number).toCharArray();

        int counter = 0;

        while (number_char.length > 1) {
            int result = 1;
            for (int i = 0; i < number_char.length; i++) {
                int digit = Integer.parseInt(String.valueOf(number_char[i]));
                result *= digit;
            }
            number_char = Integer.toString(result).toCharArray();
            counter += 1;
        }

        return counter;
    }

    // Task 7 ----------------------------------------------------------------------------------------------------------
    // Converts and returns a string to star shorthand
    public static String toStarShorthand(String str) {

        if (str.length() == 0) { return ""; }

        char[] str_char = str.toCharArray();

        int counter = 1;
        String answer = "";

        for (int i = 0; i < str_char.length; i++) {
            if (i > 0) {
                if (str_char[i] == str_char[i-1]) {
                    counter += 1;
                } else {
                    if (counter > 1) {
                        answer += str_char[i - 1] + "*" + counter;
                    } else {
                        answer += str_char[i - 1];
                    }
                    counter = 1;
                }
            }
        }
        if (counter > 1) {
            answer += str_char[str_char.length - 1] + "*" + counter;
        } else {
            answer += str_char[str_char.length - 1];
        }

        return answer;
    }

    // Task 8 ----------------------------------------------------------------------------------------------------------
    // Returns true if all vowels in last words of strings are same
    public static boolean doesRhyme(String str1, String str2) {
        char[] word1 = str1.split(" ")[str1.split(" ").length - 1].toCharArray();
        char[] word2 = str2.split(" ")[str2.split(" ").length - 1].toCharArray();

        String vowels = "aeiou";

        ArrayList<Character> word1_vowels = new ArrayList<>();
        ArrayList<Character> word2_vowels = new ArrayList<>();

        for (int i = 0; i < word1.length; i++) {
            if (vowels.indexOf(Character.toLowerCase(word1[i])) > -1) {
                word1_vowels.add(Character.toLowerCase(word1[i]));
            }
        }

        for (int i = 0; i < word2.length; i++) {
            if (vowels.indexOf(Character.toLowerCase(word2[i])) > -1) {
                word2_vowels.add(Character.toLowerCase(word2[i]));
            }
        }

        boolean result = true;

        for (int i = 0; i < word1_vowels.size(); i++) {
            if (!word2_vowels.contains(word1_vowels.get(i))){
                result = false;
            }
        }

        for (int i = 0; i < word2_vowels.size(); i++) {
            if (!word1_vowels.contains(word2_vowels.get(i))){
                result = false;
            }
        }

        return result;
    }

    // Task 9 ----------------------------------------------------------------------------------------------------------
    // Takes two integers and returns true if
    // the number is repeated three times in a row anywhere in num1 and the same number
    // repeated twice in a row in num2
    public static boolean trouble(long number1, long number2) {
        char[] number1_char = Long.toString(number1).toCharArray();
        char[] number2_char = Long.toString(number2).toCharArray();

        if ((number1_char.length < 3) & (number2_char.length < 2)) { return false; }

        ArrayList<Character> number2_arr = new ArrayList<>();

        int counter = 1;

        for (int i = 0; i < number2_char.length; i++) {
            if (i > 0) {
                if (number2_char[i] == number2_char[i-1]) {
                    counter += 1;
                } else {
                    if (counter == 2) {
                        number2_arr.add(number2_char[i-1]);
                    }
                    counter = 1;
                }
            }
        }
        if (counter == 2) {
            number2_arr.add(number2_char[number2_char.length - 1]);
        }

        counter = 1;

        for (int i = 0; i < number1_char.length; i++) {
            if (i > 0) {
                if (number1_char[i] == number1_char[i-1]) {
                    counter += 1;
                } else {
                    if (counter == 3) {
                        if (number2_arr.contains(number1_char[i - 1])) {
                            return true;
                        }
                    }
                    counter = 1;
                }
            }
        }

        if (counter == 3) {
            if (number2_arr.contains(number1_char[number1_char.length - 1 ])) {
                return true;
            }
        }

        return false;
    }

    // Task 10 ---------------------------------------------------------------------------------------------------------
    // Returns amount of all unique characters between bookends
    public static int countUniqueBooks(String str, char book_end) {
        char[] str_char = str.toCharArray();

        ArrayList<Character> unique_chars = new ArrayList<>();
        boolean is_book_open = false;

        for (int i = 0; i < str_char.length; i++) {
            if (str_char[i] == book_end) {
                is_book_open = !is_book_open;
            } else {
                if (is_book_open) {
                    if (!unique_chars.contains(str_char[i])) {
                        unique_chars.add(str_char[i]);
                    }
                }
            }
        }

        return unique_chars.size();
    }

}
