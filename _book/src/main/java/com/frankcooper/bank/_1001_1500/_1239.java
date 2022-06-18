package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import org.junit.Assert;

public class _1239 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        int res = 0;

        public int maxLength(List<String> arr) {
            dfs(arr, 0, 0, 0);
            return res;
        }


        /**
         * @param arr
         * @param idx    当前要处理的字符的下标
         * @param hash   上一轮算出来的字符的hash
         * @param curLen 当前字符的长度
         */
        public void dfs(List<String> arr, int idx, int hash, int curLen) {
            if (idx == arr.size()) {
                res = Math.max(res, curLen);
                return;
            }
            String next = arr.get(idx);
            int val = getHash(hash, next);
            if (val != -1) dfs(arr, idx + 1, val, curLen + next.length());
            dfs(arr, idx + 1, hash, curLen);
        }


        /**
         * 比较当前的字符的hash 与next字符是否有一样的，有一样的就返回-1
         *
         * @param hash
         * @param next
         * @return
         */
        public int getHash(int hash, String next) {
            for (char c : next.toCharArray()) {
                int t = c - 'a';
                if ((hash & (1 << t)) != 0) {//只有&后的各位都不同，也就是说结果是0的时候，才说明这个hash与下个next的字符没有重复的
                    return -1;
                }
                hash |= (1 << t);
            }
            return hash;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();


            List<String> arr = Arrays.asList("cha", "r", "act", "ers");
//            Assert.assertEquals(6, handler.maxLength(arr));

        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int maxLength(List<String> arr) {
            return dfs(arr, 0, 0);
        }


        public int dfs(List<String> arr, int idx, int hash) {
            if (idx == arr.size()) {
                return 0;
            }
            int ans = 0;
            for (int i = idx; i < arr.size(); i++) {
                String next = arr.get(i);
                int t = getHash(next);
                if (t == -1 || (hash & t) != 0) continue;
                ans = Math.max(ans, dfs(arr, i + 1, t | hash) + next.length());
            }
            return ans;
        }

        /**
         * 获取到字符next的掩码值
         *
         * @param next
         * @return
         */
        public int getHash(String next) {
            int hash = 0;
            for (char c : next.toCharArray()) {
                int t = c - 'a';
                if ((hash & (1 << t)) != 0) {//只有&后的各位都不同，也就是说结果是0的时候，才说明这个hash与下个next的字符没有重复的
                    return -1;
                }
                hash |= (1 << t);
            }
            return hash;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
