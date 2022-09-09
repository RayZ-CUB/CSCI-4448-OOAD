package com.rz.project1.p1_2;

import java.util.Arrays;
import java.util.Scanner;

public class Processor {
    public static String read() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input text:");
        String input = scanner.nextLine();
        if (input == null) {
            return "Empty Input";
        }
        return input;
    }
//    https://www.geeksforgeeks.org/how-to-remove-all-white-spaces-from-a-string-in-java/
    public static String clean(String input) {
        String upperCasedInput = input.toUpperCase();  //change to all Uppercase
        return upperCasedInput.replaceAll("\\s+", "");  //remove all spaces
    }

    public static String sort(String input) {
        char[] chars = input.toCharArray();   //change the string to characters,because we cannot sort strings
        Arrays.sort(chars);
        return new String(chars);
    }

    // https://www.geeksforgeeks.org/convert-string-palindrome-string-changing-one-character/
    public static String palindrome(String input) {
        char[] chars = input.toCharArray();
        char[] ouput = new char[input.length() + input.length() - 1];
        for (int i = 0; i < ouput.length; i++) {
            if (i < input.length()) {
                ouput[i] = chars[input.length() - i - 1];
            } else {
                ouput[i] = chars[i - input.length() + 1];
            }
        }
        return new String(ouput);
    }

    public static void print(String input){
        System.out.println(input);
    }
}
