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
        num.add(4);
        num.add(5);
        num.add(6);
        num.add(7);
        num.add(8);
        num.add(9);


        int partitionSize = IntMath.divide(num.size(), 5, RoundingMode.UP);
        List<List<Integer>> partitions = Lists.partition(num, 7);

//        for (List<Integer> a: partitions){
//            for (Integer b: a){
//                System.out.println(b);
//            }
//            System.out.println("First List Complete");
//        }

//        int a = 2;
//       long total = 7/3;
//        System.out.println(total);


String len= "dfjkskj,,anjal,,sapkota,,haha,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha,,dfjkskj,,anjal,,sapkota,,haha";

        System.out.println(len.length());
    }
}
