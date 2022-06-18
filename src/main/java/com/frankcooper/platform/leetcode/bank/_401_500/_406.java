package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Date 2020/9/9
 * @Author Frank Cooper
 * @Description
 */
public class _406 {
    static _406 handler = new _406();


    public static void main(String[] args) {

    }


    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return new int[][]{};
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });
//        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        List<int[]> output = new ArrayList<>();
        for (int[] p : people) {
            output.add(p[1], p);
        }
        return output.toArray(new int[people.length][2]);
    }

}
