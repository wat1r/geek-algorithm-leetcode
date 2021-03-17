package com.frankcooper.bank._1001_2000;

import java.util.Arrays;
import java.util.Comparator;

public class _1686 {

    public int stoneGameVI(int[] A, int[] B) {
        int n = A.length;
        //0存Alice与Bob认为当前石子的价值偶总和，1存Alice认为的石子的价值，2存Bob认为的石子价值
        int[][] sum = new int[n][3];
        for (int i = 0; i < n; i++) sum[i] = new int[]{A[i] + B[i], A[i], B[i]};
        //从大到小排序
        Arrays.sort(sum, (o1, o2) -> o2[0] - o1[0]);
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            //偶数 Alice从  0 2 4 ... a是Alice拿到的总价值
            if ((i & 1) == 0) a += sum[i][1];
            //奇数 Bob从 1 3 5 .... b是Bob拿到的总价值
            else b += sum[i][2];
        }
        return Integer.compare(a, b);
    }

}
