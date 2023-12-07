package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2477 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        long res = 0;

        public long minimumFuelCost(int[][] roads, int seats) {
            int n = roads.length;
            List<Integer>[] g = new List[n + 1];
            for (int i = 0; i <= n; i++) {
                g[i] = new ArrayList<>();
            }
            for (int[] e : roads) {
                g[e[0]].add(e[1]);
                g[e[1]].add(e[0]);
            }
            dfs(0, -1, seats, g);
            return res;


        }


        public int dfs(int x, int fa, int seats, List<Integer>[] g) {
            int size = 1;
            for (int y : g[x]) {
                if (y != fa) {
                    int s = dfs(y, x, seats, g);
                    size += s;
                    res += (s + seats - 1) / seats;
                }
            }
            return size;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        long res = 0;

        public long minimumFuelCost(int[][] roads, int seats) {
            int n = roads.length;
            List<Integer>[] g = new List[n + 1];
            for (int i = 0; i <= n; i++) {
                g[i] = new ArrayList<>();
            }
            for (int[] e : roads) {
                g[e[0]].add(e[1]);
                g[e[1]].add(e[0]);
            }
            dfs(0, -1, seats, g);
            return res;


        }


        public int dfs(int x, int fa, int seats, List<Integer>[] g) {
            int size = 1;
            for (int y : g[x]) {
                if (y != fa) {
                    int s = dfs(y, x, seats, g);
                    size += s;
                }
            }
            if(x != 0){
                res += (size - 1) / seats +1;
            }
            return size;
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
