package com.frankcooper.bank.week;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/10/25 10:27
 * Description
 */
public class Week212 {

    static Week212 handler = new Week212();

    public static void main(String[] args) {
//        int[] releaseTimes = {12, 23, 36, 46, 62};
//        String keysPressed = "spuda";
//
//        handler.slowestKey(releaseTimes, keysPressed);

//        int[] nums = {4, 6, 5, 9, 3, 7};
//        int[] l = {0, 0, 2};
//        int[] r = {2, 3, 5};
//        handler.checkArithmeticSubarrays(nums, l, r);

        int[][] h = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        handler.minimumEffortPath(h);

    }


    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char[] chas = keysPressed.toCharArray();
        char ans = chas[0];
        int max = releaseTimes[0];
        int n = keysPressed.length();
        for (int i = 1; i < n; i++) {
            int tmp = releaseTimes[i] - releaseTimes[i - 1];
            if (tmp >= max) {
                if (tmp == max && chas[i] > ans) {
                    ans = chas[i];
                } else {
                    ans = chas[i];
                }
                max = tmp;
            }
        }
        return ans;
    }


    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int[] arr = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            ans.add(validate(arr));
        }
        return ans;
    }

    private boolean validate(int[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            if ((arr[i] - arr[i - 1]) != (arr[1] - arr[0])) return false;
        }
        return true;
    }

    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int m, n;

    public int minimumEffortPath(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        boolean[][] vis = new boolean[m][n];
        vis[0][0] = true;
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                for (int[] d : directions) {
                    int nextI = curr[0] + d[0], nextJ = curr[1] + d[1];
                    System.out.printf("%d--%d\n", nextI, nextJ);
                    if (inArea(nextI, nextJ) && !vis[nextI][nextJ]) {
                        queue.add(new int[]{nextI, nextJ});
                        vis[nextI][nextJ] = true;
                        min = Math.min(min, Math.abs(heights[nextI][nextJ] - heights[curr[0]][curr[1]]));
                    }
                    if (nextI == m - 1 && nextJ == n - 1 && vis[nextI][nextJ]) {
                        return min;
                    }
                }
            }
        }
        return min;
    }


    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    static class _2nd {

        static _2nd handler = new _2nd();


        public static void main(String[] args) {

            int[][] h = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
            h = new int[][]{{1,10,6,7,9,10,4,9}};
            handler.minimumEffortPath(h);

        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int m, n;

        public int minimumEffortPath(int[][] heights) {
            m = heights.length;
            n = heights[0].length;
            if (m == 1 && n == 1) return 0;
            int[][] dp = new int[m][n];
//            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0][0] = heights[0][0];
            for (int i = 1; i < m; i++) dp[i][0] = Math.max(dp[i][0], Math.abs(heights[i][0] - heights[i - 1][0]));
            for (int j = 1; j < n; j++) dp[0][j] = Math.max(dp[0][j], Math.abs(heights[0][j] - heights[0][j - 1]));
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.min(Math.max(dp[i - 1][j], Math.abs(heights[i][j] - heights[i - 1][j]))
                            , Math.max(dp[i][j - 1], Math.abs(heights[i][j] - heights[i][j - 1]))
                    );

                }
            }
            return dp[m - 1][n - 1];
        }


    }


}
