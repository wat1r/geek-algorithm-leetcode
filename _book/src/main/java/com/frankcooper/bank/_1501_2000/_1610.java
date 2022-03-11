package com.frankcooper.bank._1501_2000;

import java.util.*;

public class _1610 {

    static _1610 handler = new _1610();

    public static void main(String[] args) {

        List<List<Integer>> points = new ArrayList<List<Integer>>() {
            {
                add(Arrays.asList(2, 1));
                add(Arrays.asList(2, 2));
                add(Arrays.asList(3, 3));
            }
        };
        int angle = 90;
        List<Integer> location = Arrays.asList(1, 1);
        points = new ArrayList<List<Integer>>() {
            {
                add(Arrays.asList(0, 0));
                add(Arrays.asList(0, 2));
            }
        };
        angle = 90;
        location = Arrays.asList(1, 1);
        handler.visiblePoints(points, angle, location);
    }


    double CONST = 1e-8;

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
        double maxAngle = angle * Math.PI / 180.0;
        int r = 0;
        int max = 0;
        for (int l = 0; l < angles.size(); l++) {
            while (r + 1 < angles.size() && angles.get(r + 1) - angles.get(l) <= maxAngle) {
                r++;
            }
            max = Math.max(max, r - l + 1);
        }
        return max + extraAns;
    }


}
