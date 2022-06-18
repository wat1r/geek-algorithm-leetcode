package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

public class Week258 {

    //
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public String reversePrefix(String word, char c) {
            char[] ch = word.toCharArray();
            int i = 0;
            while (i < ch.length && ch[i] != c) i++;
            if (i == ch.length) return word;
            reverse(ch, 0, i);
            return String.valueOf(ch);
        }


        private void reverse(char[] ch, int i, int j) {
            while (i < j) {
                char t = ch[i];
                ch[i++] = ch[j];
                ch[j--] = t;
            }
        }


    }

    //2001. 可互换矩形的组数
    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            int[][] rectangles = {{4, 8}, {3, 6}, {10, 20}, {15, 30}};
//            for (int[] r : rectangles) {
//                System.out.println(handler.gcd(r[0], r[1]));
//            }
        }

        public long interchangeableRectangles(int[][] rectangles) {
            Map<String, Integer> map = new HashMap<>();
            for (int[] r : rectangles) {
                int a = r[0], b = r[1];
                int t = gcd(a, b);
                a /= t;
                b /= t;
                String key = a + "#" + b;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            long res = 0;
            for (int v : map.values()) {
                if (v >= 2) {
                    res += (long) v * (v - 1) / 2;
                }
            }
            return res;
        }


        public int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }


    //2002. 两个回文子序列长度的最大乘积
    //朴素版
    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int maxProduct(String s) {
            char[] ch = s.toCharArray();
            int n = ch.length;
            //[0]位置记录state [1]记录state下二进制1的数量，也就是哪些位置是放了字符
            List<int[]> list = new ArrayList<>();
            for (int state = 0; state < (1 << n); state++) {
                if (check(ch, state)) list.add(new int[]{state, Integer.bitCount(state)});
            }
            int res = 0;
            int[][] arr = list.toArray(new int[0][0]);
            for (int i = 0; i < arr.length; i++) {
                //第一个回文子序列的state：x 以及长度
                int x = arr[i][0], len_x = arr[i][1];
                for (int j = i + 1; j < arr.length; j++) {
                    //第一个回文子序列的state：y 以及长度
                    int y = arr[j][0], len_y = arr[j][1];
                    //两段回文子序列之间应该没有交集
                    if ((x & y) == 0) {
                        res = Math.max(res, len_x * len_y);
                    }
                }
            }
            return res;
        }

        //判断对于当前state状态下，ch[]是否是满足state条件下的回文子序列
        private boolean check(char[] ch, int state) {
            int l = 0, r = ch.length - 1;
            while (l < r) {
                while (l < r && (state >> l & 1) == 0) l++;
                while (l < r && (state >> r & 1) == 0) r--;
                if (ch[l] != ch[r]) return false;
                l++;
                r--;
            }
            return true;
        }
    }

    //2002. 两个回文子序列长度的最大乘积
    //子集补集版
    static class _3rd_1 {

        public int maxProduct(String s) {
            char[] ch = s.toCharArray();
            int n = ch.length;
            //[0]位置记录state [1]记录state下二进制1的数量，也就是哪些位置是放了字符
            int[] count = new int[1 << n];


//            List<int[]> list = new ArrayList<>();
            for (int state = 0; state < (1 << n); state++) {
                if (check(ch, state)) count[state] = Integer.bitCount(state);
            }
            int res = 0;
            for (int i = 0; i < (1 << n); i++) {
                for (int j = i; j > 0; j = (j - 1) & i) {
                    res = Math.max(res, count[j] * count[i ^ j]);
                }
            }
            return res;
        }

        //判断对于当前state状态下，ch[]是否是满足state条件下的回文子序列
        private boolean check(char[] ch, int state) {
            int l = 0, r = ch.length - 1;
            while (l < r) {
                while (l < r && (state >> l & 1) == 0) l++;
                while (l < r && (state >> r & 1) == 0) r--;
                if (ch[l] != ch[r]) return false;
                l++;
                r--;
            }
            return true;
        }
    }

    static class _4th {
        //https://leetcode-cn.com/problems/smallest-missing-genetic-value-in-each-subtree/solution/dfs-liang-bian-dfs-de-xian-xing-zuo-fa-d-s5dd/
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[] parents = {-1, 0, 0, 2};
            int[] nums = {1, 2, 3, 4};
            handler.smallestMissingValueSubtree(parents, nums);
        }

        int N = 100010;
        int[] ans;
        List<Integer>[] edges = new List[N];

        boolean[] has1 = new boolean[N];
        boolean[] vis = new boolean[N];
        int now = 1;

        public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
            int n = parents.length;
            ans = new int[n];
            for (int i = 0; i < edges.length; i++) edges[i] = new ArrayList<>();
            for (int i = 1; i < parents.length; i++) {
                edges[parents[i]].add(i);
            }
            find1(0, nums);

            cal(0, nums);
            return ans;

        }

        private void cal(int u, int[] nums) {
            if (!has1[u]) return;
            for (int v : edges[u]) {
                if (has1[v]) cal(v, nums);
            }
            for (int v : edges[u]) {
                if (!has1[v]) {
                    helper(v, nums);
                }
            }
            vis[nums[u]] = true;
            while (vis[now]) now++;
            ans[u] = now;
        }

        private void helper(int u, int[] nums) {
            for (int v : edges[u]) helper(v, nums);
            vis[nums[u]] = true;
        }

        private void find1(int u, int[] nums) {
            if (nums[u] == 1) has1[u] = true;
            else has1[u] = false;
            for (int v : edges[u]) {
                find1(v, nums);
                has1[u] = has1[u] || has1[v];
            }
            if (!has1[u]) ans[u] = 1;
        }

    }
}
