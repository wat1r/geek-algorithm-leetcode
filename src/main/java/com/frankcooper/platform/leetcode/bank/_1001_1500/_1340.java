package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.Arrays;
import java.util.Comparator;
//1340. 跳跃游戏 V 1340. Jump Game V Hard

public class _1340 {
    public static void main(String[] args) {

    }

    /*
        这个题解的图解释的很清楚
        这一题同1306题可以往左右两个方向跳跃，不同点是其限定了举例与高度,有些情况下是跳不过去的
        https://leetcode-cn.com/problems/jump-game-v/solution/yi-wei-dong-tai-gui-hua-tu-jie-python3-by-ml-zimin/
        ##### 方法1：`DP` (**从柱子的最小值到最大值**）
        - 先对`arr[i]`的值按从小到大排列，最后可以想象成`(k,v)`的这种形式，按`k`来排序，`k`指的是`arr[i]`的值，`v`的值指的是`index`索引,写入一个`idx`的数组中，存的是排序好的`index`的值
        - 涉及到的排序的方式：
          ```java
           Arrays.sort(idx, Comparator.comparingInt(o -> arr[o]));//按升序排列
          //        Arrays.sort(idx, (Comparator) ((o1, o2) -> arr[(int) o1] - arr[(int) o2]));
          ```
          - 两种均可，第一种是`lambda`表达式
        - 准备一个`dp`，**`dp[i]`表示的是下表`i`最多可以跳跃的次数，或是访问的次数**，当`i`处于某个位置的时候，`i`可以往左边跳跃，也可以往右边跳跃

          - 往左边跳跃 `dp[i]=max{dp[j]+1,dp[i]} j∈[i-d,i-1]`
          - 往右边跳跃`dp[i]=max{dp[j]+1,dp[i]} j∈[i+1,i+d]`
          - 因为从`i`跳跃到`j`位置，都是一次跳跃，所以要`+1`
          - 要判断下`arr[j]>arr[i]`的情况，此种情况表示跳跃不了的，要`break` 掉
        - 在`for loop`的过程中，记录下`max`值，最后返回

     */
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        Integer[] idx = new Integer[n];//存储的arr的下标
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, Comparator.comparingInt(o -> arr[o]));//按升序排列
//        Arrays.sort(idx, (Comparator) ((o1, o2) -> arr[(int) o1] - arr[(int) o2]));
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int index = 0; index < n; index++) {
            int i = idx[index];
            for (int j = i - 1; j >= 0 && j >= (i - d); j--) {
                if (arr[j] >= arr[i]) break;
                dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            for (int j = i + 1; j < n && j <= (i + d); j++) {
                if (arr[j] >= arr[i]) break;
                dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
