package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _397 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            Assert.assertEquals(3, handler.integerReplacement(8));
            Assert.assertEquals(2, handler.integerReplacement(4));
            Assert.assertEquals(4, handler.integerReplacement(7));
        }

        public int integerReplacement(int n) {
            return (int) dfs(n);
        }

        public long dfs(long n) {
            if (n == 1) return 0;
            if (n % 2 == 0) {
                n /= 2;
                return dfs(n) + 1;
            } else {
                return Math.min(dfs(n + 1), dfs(n - 1)) + 1;
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int integerReplacement(int n) {
            return (int) dfs(n);
        }


        private long dfs(long n) {
            if (n == 1) return 0;
            if (n % 2 == 0) {
                n /= 2;
                return dfs(n) + 1;
            } else {
                return Math.min(dfs(n - 1), dfs(n + 1)) + 1;
            }

        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int integerReplacement(int n) {
            int res = 0;
            while (n != 1) {
                if ((n & 1) == 0) {
                    n >>>= 1;
                } else if (n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1)) {
                    n--;
                } else {
                    n++;
                }
                res++;
            }
            return res;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public int integerReplacement(int n) {
            return dfs(n, 0);
        }

        Map<Long, Integer> memo = new HashMap<>();

        private int dfs(long n, int s) {
            if (n == 1) return s;
            if (n <= 0) return Integer.MAX_VALUE;
            if (memo.containsKey(n)) return memo.get(n) + s;
            int res = 0;
            if (n % 2 == 0) {
                res = dfs(n / 2, s + 1);
            } else {
                res = Math.min(dfs(n + 1, s + 1), dfs(n - 1, s + 1));
            }
            memo.put(n, res - s);
            return res;
        }
    }

    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            Assert.assertEquals(4, handler.integerReplacement(7));
        }


        public int integerReplacement(int n) {
            if (n == 1) return 0;
            Queue<Long> q = new LinkedList<>();
            Set<Long> vis = new HashSet<>();
            q.offer((long) n);
            int res = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    long cur = q.poll();
                    if (cur == 1) return res;
                    if (vis.contains(cur)) {
                        continue;
                    }
                    vis.add(cur);
                    if (cur % 2 == 0) {
                        q.offer(cur / 2);
                    } else {
                        q.offer(cur - 1);
                        q.offer(cur + 1);
                    }
                }
                res++;
            }
            return res;
        }


    }

    static class _8th {
        Map<Integer, Integer> cache = new HashMap<Integer, Integer>() {{
            put(1, 0);
        }};

        public int integerReplacement(int n) {
            if (cache.containsKey(n))
                return cache.get(n);
            int ans;
            if (n % 2 == 0)
                ans = 1 + integerReplacement(n / 2);
            else
                ans = Math.min(integerReplacement(n / 2 + 1), integerReplacement(n / 2)) + 2;
            cache.put(n, ans);
            return ans;
        }

    }

    static class _9th {
        // 记忆化
        private Map<Integer, Integer> cache = new HashMap<>();

        public int integerReplacement(int n) {
            if (cache.containsKey(n))    // 若缓存中有答案，则返回答案
                return cache.get(n);
            if (n == 1)                  // 递归出口
                return 0;
            if (n == Integer.MAX_VALUE)  // 防止2^31-1 在+1后溢出
                return Integer.SIZE;
            int ans = 0;
            if ((n & 1) == 1)    // n是奇数
                ans = Math.min(integerReplacement(n - 1) + 1, integerReplacement(n + 1) + 1);
            else
                ans = integerReplacement(n / 2) + 1;
            cache.put(n, ans);           // 记录下本次integerReplacement(n)的结果
            return ans;
        }


    }
}
