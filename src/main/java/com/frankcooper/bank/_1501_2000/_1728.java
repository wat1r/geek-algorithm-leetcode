package com.frankcooper.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1728 {

    //没掌握
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        static int S = 8 * 8 * 8 * 8, K = 1000;
        static int[][] f = new int[S][K]; // mouse : 0 / cat : 1
        String[] g;
        int n, m, a, b, tx, ty;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // mouse : (x, y) / cat : (p, q)
        int dfs(int x, int y, int p, int q, int k) {
            int state = (x << 9) | (y << 6) | (p << 3) | q;
            if (k == K - 1) return f[state][k] = 1;
            if (x == p && y == q) return f[state][k] = 1;
            if (x == tx && y == ty) return f[state][k] = 0;
            if (p == tx && q == ty) return f[state][k] = 1;
            if (f[state][k] != -1) return f[state][k];
            if (k % 2 == 0) { // mouse
                for (int[] di : dirs) {
                    for (int i = 0; i <= b; i++) {
                        int nx = x + di[0] * i, ny = y + di[1] * i;
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                        if (g[nx].charAt(ny) == '#') break;
                        if (dfs(nx, ny, p, q, k + 1) == 0) return f[state][k] = 0;
                    }
                }
                return f[state][k] = 1;
            } else { // cat
                for (int[] di : dirs) {
                    for (int i = 0; i <= a; i++) {
                        int np = p + di[0] * i, nq = q + di[1] * i;
                        if (np < 0 || np >= n || nq < 0 || nq >= m) break;
                        if (g[np].charAt(nq) == '#') break;
                        if (dfs(x, y, np, nq, k + 1) == 1) return f[state][k] = 1;
                    }
                }
                return f[state][k] = 0;
            }
        }

        public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
            g = grid;
            n = g.length;
            m = g[0].length();
            a = catJump;
            b = mouseJump;
            for (int i = 0; i < S; i++) Arrays.fill(f[i], -1);
            int x = 0, y = 0, p = 0, q = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (g[i].charAt(j) == 'M') {
                        x = i;
                        y = j;
                    } else if (g[i].charAt(j) == 'C') {
                        p = i;
                        q = j;
                    } else if (g[i].charAt(j) == 'F') {
                        tx = i;
                        ty = j;
                    }
                }
            }
            return dfs(x, y, p, q, 0) == 0;
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
