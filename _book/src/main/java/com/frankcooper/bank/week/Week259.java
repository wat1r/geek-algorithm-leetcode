package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

//https://leetcode-cn.com/contest/weekly-contest-259
public class Week259 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public int finalValueAfterOperations(String[] operations) {
            Map<String, Integer> dict = new HashMap<>();
            dict.put("++X", 1);
            dict.put("X++", 1);
            dict.put("--X", -1);
            dict.put("X--", -1);
            int res = 0;
            for (String op : operations) {
                res += dict.get(op);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{1, 2, 3};
            Assert.assertEquals(2, handler.sumOfBeauties(nums));
        }

        public int sumOfBeauties(int[] nums) {
            int n = nums.length;
            //从前往后，之前的遍历过的最大的数比当前nums[i]大的数，有的话，就设置为true，没有的话设置为false
            boolean[] start = new boolean[n];
            int t = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] > t) t = nums[i];
                else start[i] = true;
            }
            //从后往前，之前遍历的过的最小的数比当前nums[i]大的数，有的话，就设置为true，没有的话设置为false
            boolean[] end = new boolean[n];
            t = 100010;
            for (int i = n - 1; i >= 0; i--) {
                if (nums[i] < t) t = nums[i];
                else end[i] = true;
            }
            int total = 0;
            for (int i = 0; i < n; i++) {
                if (i > 0 && i < n - 1) {
                    if (!start[i] && !end[i]) total += 2;
                    else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) total += 1;
                }
//                else total += 0;
            }
            return total;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();

        }

        class DetectSquares {

            //k:x v:{k:y,[x,y]出现的次数}
            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

            public DetectSquares() {

            }

            public void add(int[] point) {
                int x = point[0], y = point[1];
                Map<Integer, Integer> sub = map.getOrDefault(x, new HashMap<>());
                sub.put(y, sub.getOrDefault(y, 0) + 1);
                map.put(x, sub);
            }

            public int count(int[] point) {
                int x = point[0], y = point[1];
                Map<Integer, Integer> sub = map.getOrDefault(x, new HashMap<>());
                int total = 0;
                for (int ny : sub.keySet()) {
                    if (ny == y) continue;
                    int c1 = sub.get(ny);//[x,ny]的数量
                    int len = y - ny;//正方形的边长
                    int[] nums = new int[]{x - len, x + len};
                    for (int nx : nums) {
                        Map<Integer, Integer> tmp = map.getOrDefault(nx, new HashMap<>());
                        int c2 = tmp.getOrDefault(y, 0);
                        int c3 = tmp.getOrDefault(ny, 0);
                        total += c1 * c2 * c3;
                    }
                }
                return total;
            }
        }
    }

    static class _4th {

        //https://leetcode-cn.com/problems/longest-subsequence-repeated-k-times/solution/zui-jian-ji-yi-dong-de-fang-fa-li-yong-z-hay1/
        public static void main(String[] args) {
            _4th handler = new _4th();
            String s = "letsleetcode";
            int k = 2;
            handler.longestSubsequenceRepeatedK(s, k);
        }

        StringBuilder ans = new StringBuilder();
        boolean[] used = new boolean[10];//n < 8*k 所以 n/k 比10小


        public String longestSubsequenceRepeatedK(String s, int k) {
            int[] hash = new int[26];//记录每个字符出现的次数
            char[] ch = s.toCharArray();
            for (char c : ch) hash[c - 'a']++;
            //按字典逆序，将满足k次的字符加入其中
            StringBuilder sb = new StringBuilder();
            for (int i = hash.length - 1; i >= 0; i--) {
                for (int j = 0; j < hash[i] / k; j++) {
                    sb.append((char) ('a' + i));
                }
            }
            //搜索 i=n->1长度的前i个字符的全排列
            int n = sb.length();
            char[] pattern = sb.toString().toCharArray();
            for (int i = n; i > 0; i--) {
                if (dfs(ch, pattern, 0, i, n, k)) {
                    return ans.toString();
                }
            }
            return "";

        }

        /**
         * @param ch      s字符
         * @param pattern 满足k次要求的字符
         * @param len     当前的子序列长度
         * @param cnt     查找长度为cnt的子序列
         * @param n       pattern的总长度
         * @param k       目标要求的重复k次的子序列
         * @return
         */
        private boolean dfs(char[] ch, char[] pattern, int len, int cnt, int n, int k) {
            if (len == cnt) return check(ch, cnt, k);
            for (int i = 0; i < n; i++) {
                if (!used[i]) {
                    used[i] = true;
                    ans.append(pattern[i]);
                    if (dfs(ch, pattern, len + 1, cnt, n, k)) {
                        return true;
                    }
                    ans.deleteCharAt(len);
                    used[i] = false;
                }
            }
            return false;
        }


        /**
         * 检查当前排列是不是满足s中的k次子序列 ans要在s中出现k次
         *
         * @param s 源字符串
         * @param n 当前子序列的长度
         * @param k 目标要求的重复k次的子序列
         * @return
         */
        private boolean check(char[] s, int n, int k) {
            int cnt = 0, idx = 0;
            for (char c : s) {
                if (c == ans.charAt(idx)) {
                    idx++;
                    if (idx == n) {
                        idx = 0;
                        cnt++;
                    }
                }
            }
            System.out.printf("ans:%s-> %s\n", ans.toString(), cnt >= k);
            return cnt >= k;
        }

    }
}
