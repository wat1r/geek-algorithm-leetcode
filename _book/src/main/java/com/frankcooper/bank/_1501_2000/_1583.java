package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1583 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int n = 4;
            int[][] preferences = {{1, 2, 3}, {3, 2, 0}, {3, 1, 0}, {1, 2, 0}}, pairs = {{0, 1}, {2, 3}};
            handler.unhappyFriends(n, preferences, pairs);


        }


        public int unhappyFriends(int n, int[][] ps, int[][] pairs) {
            //其中order[i][j] 表示朋友 j 在 i 的朋友列表中的亲近程度下标
            int[][] order = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {//总共n个人，除开i这个人本身，其余由n-1个人，遍历到n-1即可
                    order[i][ps[i][j]] = j;
                }
            }
            //如果 x 和 yy 配对，则有 connected[u]=v 以及 connected[v]=u
            int[] connected = new int[n];
            for (int[] pair : pairs) {
                int u = pair[0], v = pair[1];
                connected[u] = v;
                connected[v] = u;
            }
            int res = 0;
            for (int x = 0; x < n; x++) {
                int y = connected[x];
                int index = order[x][y];
                for (int i = 0; i < index; i++) {
                    int u = ps[x][i];
                    int v = connected[u];
                    if (order[u][x] < order[u][v]) {
                        res++;
                        break;
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
