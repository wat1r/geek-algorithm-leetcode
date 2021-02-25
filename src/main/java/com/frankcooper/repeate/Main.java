package com.frankcooper.repeate;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
//            System.out.println(processOne(in.nextInt()));
            int m = in.nextInt(), n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = in.nextInt();
//            System.out.println(processTwo(m, n, nums));
            System.out.println(allocate(m, nums));
        }
    }


    public static int allocate(int m, int[] ts) {
        int n = ts.length;
        int ans = 0;
        if (n <= m) {
            for (int t : ts) {
                ans = Math.max(ans, t);
            }
            return ans;
        }
        Arrays.sort(ts);

        PriorityQueue<Integer> nextFree = new PriorityQueue<>(m);

        for (int _m = 0; _m < m; _m++) {
            nextFree.add(0);
        }

        for (int i = n - 1; i >= 0; i--) {
            int t = nextFree.poll() + ts[i];
            ans = Math.max(t, ans);
            nextFree.add(t);
        }

        return ans;

    }


    /**
     * https://www.cnblogs.com/xuyy-isee/p/10732186.html
     *
     * @param m
     * @param n
     * @param nums
     * @return
     */
    private static int processTwo(int m, int n, int[] nums) {
        Arrays.sort(nums);
        int idx = nums.length % m - 1;
        int ans = 0;
        for (int i = idx; i < nums.length; i += m) {
            ans += nums[i];
        }
        return ans;
    }


    private static String processOne(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            n--;
            sb.append((char) ('a' + n % 26));
            n /= 26;
        }
        return sb.reverse().toString();
    }


}
