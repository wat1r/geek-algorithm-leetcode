package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.*;

import com.frankcooper.utils.PrintUtils;

public class _407 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] heightMap = PrintUtils.processSymbol("[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]");
            handler.trapRainWater(heightMap);

        }


        public int trapRainWater(int[][] heightMap) {
            int R = heightMap.length, C = heightMap[0].length;
            boolean[][] vis = new boolean[R][C];
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);//按高度从小到大排
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (i == 0 || i == R - 1 || j == 0 || j == C - 1) {//先处理最外圈
                        pq.offer(new int[]{i, j, heightMap[i][j]});//x y h 坐标+高度
                        vis[i][j] = true;
                    }
                }
            }
            int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};//方向数组
            int res = 0;
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                for (int[] d : dirs) {
                    int ni = cur[0] + d[0], nj = cur[1] + d[1];
                    if (ni >= 0 && ni < R && nj >= 0 && nj < C && !vis[ni][nj]) {//下一个元素没有被访问过
                        if (cur[2] > heightMap[ni][nj]) {
                            res += cur[2] - heightMap[ni][nj];//当前的位置比相邻的位置高，相邻的位置可以装雨水
                        }
                        pq.offer(new int[]{ni, nj, Math.max(heightMap[ni][nj], cur[2])});//更新当前的位置的高度，取最高的
                        vis[ni][nj] = true;
                    }
                }
            }
            return res;

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
