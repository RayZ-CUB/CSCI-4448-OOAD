package com.rz.project1.p1_2;

public class Solution {
    public static void main(String[] args) {
        String input = Processor.read();
        String cleanedOutput = Processor.clean(input);
        String sortedOutput = Processor.sort(cleanedOutput);
        String palindromeOutput = Processor.palindrome(sortedOutput);
        Processor.print(palindromeOutput);
    }
}
