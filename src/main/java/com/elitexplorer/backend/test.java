package com.elitexplorer.backend;

import com.google.common.collect.Lists;
import com.google.common.math.IntMath;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(3);

        int partitionSize = IntMath.divide(num.size(), 2, RoundingMode.UP);
        List<List<Integer>> partitions = Lists.partition(num, partitionSize);

        for (List<Integer> a: partitions){
            for (Integer b: a){
                System.out.println(b);
            }
            System.out.println("First List Complete");
        }
    }
}
