package com.rz.project1.p1_1;
// https://www.geeksforgeeks.org/ways-to-read-input-from-console-in-java/
// https://stackoverflow.com/questions/11621302/read-multiple-lines-from-console-and-store-it-in-array-list-in-java
// https://stackabuse.com/java-read-a-file-into-an-arraylist/

import java.util.ArrayList;
import java.util.Scanner;


public class Reader {
    public ArrayList<Double> getData() {
//     public static void main(String[] args){
        ArrayList<Double> list;
        Scanner scanner = new Scanner(System.in);
        list = new ArrayList<>();
        System.out.println("Enter number size:");   //set a limit for user input
        int size = Integer.parseInt(scanner.nextLine());   //read the user input also handle some unexpected input
        System.out.println("Enter data: (Please enter one data each line and hit enter)");
        int count = 0;
        while (count < size) {
            list.add(Double.parseDouble(scanner.nextLine()));
            count++;
        }

        System.out.println("\nThe data you entered is:");
        System.out.println(list);
        return list;

        //return list;
    }

}