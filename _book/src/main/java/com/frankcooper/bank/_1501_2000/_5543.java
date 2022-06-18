package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/10/18 10:31
 * Description
 */
public class _5543 {

    static _5543 handler = new _5543();

    public static void main(String[] args) {
//        handler.maxLengthBetweenEqualCharacters("cabbac");

//        handler.gcd(16, 9);
//        handler.test(16);
//        handler.test(9);
//        handler.test(6);

//
//        int n = 14;
//        int threshold = 4;
//        int[][] queries = new int[][]{{4, 2}, {7, 2}, {4, 3}, {1, 4}, {4, 11}, {6, 8}, {8, 12}, {12, 5}, {3, 7}, {12, 6}, {3, 6}, {
//                11, 9}, {6, 9}, {6, 4}, {4, 9}, {14, 4}, {10, 14}, {14, 2}, {9, 8}, {8, 7}, {13, 14}, {12, 4}, {7, 4}, {10, 4}, {1, 6}, {9, 7}, {
//                5, 13}, {10, 11}, {14, 8}, {5, 6}, {7, 12}, {11, 5}, {8, 13}, {4, 8}, {1, 9}, {8, 2}, {1, 13}, {5, 9}, {12, 1}, {13, 10}, {
//                1, 8}, {10, 6}, {9, 13}, {6, 11}, {3, 5}, {5, 2}};

//        handler.areConnected(n, threshold, queries);


        int n = 26;
        int threshold = 3;
        int[][] queries = new int[][]{{8, 3}, {14, 9}, {22, 23}, {22, 25}, {12, 6}, {17, 3}, {25, 17}, {26, 14}, {4, 12}, {16, 12}, {16, 9}};
        handler.areConnected(n, threshold, queries);


    }

    HashMap<Integer, List<Integer>> memo = new HashMap<>();


    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        List<Boolean> resList = new ArrayList<>();
        if (queries == null || queries.length == 0) return resList;
        for (int[] q : queries) {
            List<Integer> aList = f(q[0]);
            List<Integer> bList = f(q[1]);


            if ((retain(aList, bList) > threshold) || retain(bList, aList) > threshold) {
                resList.add(true);
            } else {
                resList.add(false);
            }
        }
        return resList;
    }


    public List<Boolean> areConnected1(int n, int threshold, int[][] queries) {
        List<Boolean> resList = new ArrayList<>();
        if (queries == null || queries.length == 0) return resList;
        for (int[] q : queries) {
            List<Integer> list1 = null;
            if (!memo.containsKey(q[0])) {
                List<Integer> aList = f(q[0]);
                memo.put(q[0], new ArrayList<>(aList));
                list1 = new ArrayList<>(aList);
            } else {
                list1 = memo.get(q[0]);
            }
            List<Integer> list2 = null;
            if (!memo.containsKey(q[1])) {
                List<Integer> bList = f(q[1]);
                memo.put(q[1], bList);
                list2 = new ArrayList<>(bList);
            } else {
                list2 = memo.get(q[1]);
            }
            List<Integer> list1New = new ArrayList<>();
            List<Integer> list2New = new ArrayList<>();
            for (int i = 0; i < list1.size(); i++) {
                list1New.add(list1.get(i));
            }
            for (int i = 0; i < list2.size(); i++) {
                list2New.add(list2.get(i));
            }


            if ((retain(list1New, list2New) > threshold) || retain(list2New, list1New) > threshold) {
                resList.add(true);
            } else {
                resList.add(false);
            }
        }
        return resList;
    }


    public int retain(List<Integer> list1, List<Integer> list2) {
        boolean b = list2.retainAll(list1);
        int max = Integer.MIN_VALUE;
        if (b) {
            for (Integer i : list2) {
                max = Math.max(max, i);
            }
        }
        return max;

    }


    public List<Integer> f(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
            }
        }
        return res;
    }

//
//    public int gcd(int a, int b) {
//        while (b != 0) {
//            int temp = a % b;
//            a = b;
//            b = temp;
//        }
//        return a;
//    }


    public int maxLengthBetweenEqualCharacters(String s) {
        int res = -1;
        if (s == null || s.length() == 0) return res;
        char[] chas = s.toCharArray();
        int n = s.length();
        for (int l = 0; l < n - 1; l++) {
            for (int r = l + 1; r < n; r++) {
                if (chas[l] == chas[r]) {
                    res = Math.max(res, r - l - 1);
                }
            }
        }
        return res;
    }

    public int bestTeamScore(int[] scores, int[] ages) {

        return 0;
    }


}
