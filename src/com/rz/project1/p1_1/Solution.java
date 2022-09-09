package com.rz.project1.p1_1;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        Reader reader = new Reader();

        ArrayList<Double> data = reader.getData();
        Analyzer.analyze(data);
    }
}
