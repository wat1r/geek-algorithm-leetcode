package com.frankcooper.bank._1001_1500;

import java.util.*;
import org.junit.Assert;
public class _994 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int orangesRotting(int[][] grid) {
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int R = grid.length, C = grid[0].length;
            Queue<int[]> q = new LinkedList<>();
            int fresh = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid[r][c] == 1) fresh++;
                    if (grid[r][c] == 2) q.offer(new int[]{r, c});
                }
            }
            int round = 0;
            while (fresh > 0 && !q.isEmpty()) {
                int size = q.size();
                round++;
                for (int i = 0; i < size; i++) {
                    int[] p = q.poll();
                    int r = p[0], c = p[1];
                    for (int[] d : dirs) {
                        int nr = r + d[0], nc = c + d[1];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] == 1) {
                            grid[nr][nc] = 2;
                            q.offer(new int[]{nr, nc});
                            fresh--;
                        }
                    }
                }
            }
            return fresh > 0 ? -1 : round;

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
