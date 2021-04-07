package com.frankcooper.bank.week;

import java.util.*;

import com.frankcooper.swordoffer.utils.PrintUtils;
import org.junit.Assert;

public class Week235 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public String truncateSentence(String s, int k) {
            Deque<String> q = new LinkedList<>(Arrays.asList(s.split("\\s+")));
            StringBuilder res = new StringBuilder();
            k--;
            while (k >= 0 && !q.isEmpty()) {
                res.append(q.pollFirst());
                if (k != 0) res.append(" ");
                k--;
            }
            return res.toString();
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] logs = PrintUtils.processSymbol("[[1,1],[2,2],[2,3]]");
            int k = 4;
            handler.findingUsersActiveMinutes(logs, k);
        }

        public int[] findingUsersActiveMinutes(int[][] logs, int k) {
            HashMap<Integer, Set<Integer>> map = new HashMap<>();//k为用户ID ，v为time v的size 为UAM
            for (int[] log : logs) {
                Set<Integer> set = map.getOrDefault(log[0], new HashSet<>());
                set.add(log[1]);
                map.put(log[0], set);
            }
            int[] res = new int[k];
            for (Set<Integer> times : map.values()) {
                int uam = times.size();
                res[uam - 1] += 1;
            }
            return res;

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
            handler.countDifferentSubsequenceGCDs(new int[]{6, 10, 3});
        }


        public int countDifferentSubsequenceGCDs(int[] nums) {
            boolean[] visited = new boolean[11];
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                visited[num] = true;
                max = Math.max(max, num);
            }
            int count = 0;
            // 公约数可能的范围【1, max】
            for (int i = 1; i <= max; i++) {
                System.out.printf("i:%d\n", i);
                int commonGCD = -1;
                // 检查所有i的倍数
                for (int j = i; j <= max; j += i) {
                    System.out.printf("  j:%d, %s\n", j, visited[j]);
                    if (visited[j]) {
                        if (commonGCD == -1) {
                            commonGCD = j;
                        } else {
                            commonGCD = gcd(commonGCD, j);
                        }
                    }
                }
                System.out.printf("i:%d,commonGCD:%d\n", i, commonGCD);
                if (i == commonGCD) {
                    count++;
                }
            }
            return count;
        }

        private int gcd(int x, int y) {
            return x == 0 ? y : gcd(y % x, x);
        }


    }


    static class _4th_1 {

        public static void main(String[] args) {
            _4th_1 handler = new _4th_1();
            handler.countDifferentSubsequenceGCDs(new int[]{6, 10, 3});
        }


        int[] f = new int[200_005];
        int MAX;

        //校验x是否是nums的一个子序列的最大公约数
        //给定的数组存在一个序列的最大公约数为 g，当且仅当数组中所有 g 的倍数的最大公约数为 g
        public boolean check(int x) {
            int g = -1;
            for (int i = x; i <= MAX; i += x) {
                if (f[i] == 1) {
                    if (g == -1) g = i;
                    else g = gcd(g, i);
                }
            }
            return g == x;
        }

        public int countDifferentSubsequenceGCDs(int[] nums) {
            MAX = Integer.MIN_VALUE;
            for (int x : nums) {
                MAX = Math.max(MAX, x);
                f[x] = 1;
            }
            int res = 0;
            for (int i = 1; i <= MAX; i++) {
                if (check(i)) res++;
            }
            return res;
        }


        public int gcd(int x, int y) {
            return x == 0 ? y : gcd(y % x, x);
        }

    }
}
