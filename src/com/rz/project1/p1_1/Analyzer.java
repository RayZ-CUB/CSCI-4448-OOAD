// https://www.delftstack.com/howto/java/calculate-median-of-an-array-in-java/

package com.rz.project1.p1_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Analyzer {
    //    public ArrayList<Double> getData() throws IOException{
    public static void analyze(ArrayList<Double> list) {
//        System.out.println(list);
        Collections.sort(list);  //sorted the list by alpha order
//        System.out.println(list);
        double median = calculateMedian(list);
        System.out.println("The median is " + median);

        double mean = calculateMean(list);
        System.out.println("The mean is " + mean);

        //double mode = calculateMode(list);
        Map<Double, Integer> calculateMode = calculateMode(list);
        calculateMode.forEach((k, v) -> System.out.println("The mode is " + k ));
        //System.out.println("The mode is " + calculateMode);

        double variance = calculateVariance(list);
        System.out.println("The variance is " + variance);

        double SD = calculateSD(list);
        System.out.println("The Standard deviation is " + SD);

        double minimumValue = Min(list);
        System.out.println("The Minimum value is " + minimumValue);

        double maxmumValue = Max(list);
        System.out.println("The Maximum value is " + maxmumValue);

        //use hashmap to store a key with its value
        Map<Double, Integer> maxOccur = MaxOccur(list);
        maxOccur.forEach((k, v) -> System.out.println("The maximum occurrences value is " + k + " and the occurrence is " + v));
        //System.out.println("The maximum occurrences value is" + maxOccur.);
}

    private static double calculateMedian(ArrayList<Double> list) {
        double median;
        int size = list.size();
        if (size % 2 == 1) {
            median = list.get(list.size() / 2);

        } else {
            median = (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2;
        }
        return median;
    }

    private static double calculateMean(ArrayList<Double> list) {
        double mean;
        int size = list.size();
        int i = 0;
        double sum = 0;
        while (i < size) {
            sum += list.get(i);
            i++;
        }
        mean = (sum / size);
        return mean;
    }

    public static HashMap<Double, Integer> calculateMode(ArrayList<Double> list) { //use hashmap store each number and its count
        double max_value = 0.0;
        int max_count = 0;
        HashMap<Double, Integer> map = new HashMap<>();

        for (Double aDouble : list) {
            int count = 0;
            for (Double value : list) {
                if (value.equals(aDouble))
                    ++count;
            }
            if (count > max_count) {
                max_count = count;
                max_value = aDouble;
            }
        }
        if (max_count == 1) {
            System.out.println("There is No mode in this data set.");
        }  else
            map.put(max_value,max_count);
        return map;
    }

    //https://www.anycodings.com/1questions/2564756/calculating-standard-deviation-variance-in-java
    private static double calculateVariance(ArrayList<Double> list) {
        double mean;
        int size = list.size();
        int i = 0;
        double sum = 0;
        while (i < size) {
            sum += list.get(i);
            i++;
        }
        mean = sum / size;
        double variance = 0;
        for (int j = 0; j < size; j++) {
            variance += Math.pow((list.get(j) - mean), 2);
        }
        //System.out.println(variance);
        variance /= (size - 1);
        return variance;
    }

    private static double calculateSD(ArrayList<Double> list) {
        int size = list.size();
        int i = 0;
        double sum = 0;
        while (i < size) {
            sum += list.get(i);
            i++;
        }
        double mean = sum / size;
        sum = 0;
        for (i = 0; i < size; i++) {
            sum = sum + Math.pow(list.get(i) - mean, 2);
        }
        mean = sum / (size - 1);
        return Math.sqrt(mean);
    }

    private static double Min(ArrayList<Double> list) {
        Double min = list.get(0);
        return min;
    }

    private static double Max(ArrayList<Double> list) {
        int size = list.size();
        Double max = list.get(size - 1);
        //System.out.println(size-1);
        return max;
    }

    //https://www.geeksforgeeks.org/frequent-element-array/
    private static Map<Double, Integer> MaxOccur(ArrayList<Double> list) {
        int size = list.size();
        int max_count = 0;
        double max_value = 0;
        HashMap<Double, Integer> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            int count = 0;
            for (int j = 0; j < size; j++) {
                if (list.get(i).equals(list.get(j))) {
                    count++;
                }
            }
            if (count > max_count) {
                max_count = count;
                max_value = list.get(i);
            }
        }
        if (max_count == 1){
            System.out.println("There is no maximum occurrences value for this data set.");
        }else {
            map.put(max_value,max_count);
        }
        return map;
    }
}

