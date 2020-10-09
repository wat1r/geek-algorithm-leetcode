package com.frankcooper.bank;

import java.util.*;

public class _1610 {

    static _1610 handler = new _1610();

    public static void main(String[] args) {

    }


    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {


        List<Double> angles = new ArrayList<>();
        int extraAns = 0;
        for (List<Integer> point : points) {
            int deltaY = point.get(1) - location.get(1);
            int deltaX = point.get(0) - location.get(0);
            if (deltaX == 0 && deltaY == 0) {
                extraAns++;
                continue;
            }
            double tmp = Math.atan2(deltaY, deltaX);
            angles.add(tmp);
            angles.add(tmp + 2 * Math.PI);
        }
        Collections.sort(angles);



        return 0;
    }


}
