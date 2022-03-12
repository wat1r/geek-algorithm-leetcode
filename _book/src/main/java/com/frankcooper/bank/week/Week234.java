package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week234 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String word = "leet1234code234";
            word = "0dada01dadda67";
            word = "0a0";
            handler.numDifferentIntegers(word);
        }

        public int numDifferentIntegers(String word) {
            char[] chas = word.toCharArray();
            Set<Integer> set = new HashSet<>();
            int num = -1, i = 0;
            while (i < chas.length) {
                if (chas[i] >= '0' && chas[i] <= '9') {
                    if (num == -1) num = 0;
                    num = num * 10 + chas[i] - '0';
                } else {
                    if (num != -1) set.add(num);
                    num = -1;
                }
                i++;
            }
            if (num != -1) set.add(num);
            return set.size();
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            Assert.assertEquals(handler.reinitializePermutation(2), 1);
//            Assert.assertEquals(handler.reinitializePermutation(4), 2);
            Assert.assertEquals(handler.reinitializePermutation(6), 4);
        }

        public int reinitializePermutation(int n) {
            int[] perm = new int[n];
            int[] ori = new int[n];
            for (int i = 0; i < n; i++) {
                perm[i] = i;
                ori[i] = i;
            }

            int[] arr = new int[n];
            int ans = 0;
            while (true) {
                for (int i = 0; i < n; i++) {
                    if (i % 2 == 0) arr[i] = perm[i / 2];
                    else arr[i] = perm[n / 2 + (i - 1) / 2];
                }
                ans++;
                if (Arrays.equals(ori, arr)) return ans;
                for (int i = 0; i < n; i++) perm[i] = arr[i];
            }
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String s = "(name)is(age)yearsold";
            List<List<String>> knowledge = new ArrayList<List<String>>() {{
                add(Arrays.asList("name", "bob"));
                add(Arrays.asList("age", "two"));
            }};
            handler.evaluate(s, knowledge);
        }

        public String evaluate(String s, List<List<String>> knowledge) {
            Map<String, String> map = new HashMap<>();
            for (List<String> list : knowledge) {
                map.put(list.get(0), list.get(1));
            }
            char[] chas = s.toCharArray();
            StringBuilder ans = new StringBuilder();
            Stack<Character> stk = new Stack<>();
            int i = 0;
            boolean push = false;
            while (i < chas.length) {
                if (chas[i] == '(') {
                    push = true;
                    i++;
                    continue;
                }
                if (chas[i] == ')') {
                    push = false;
                    i++;
                    StringBuilder tmp = new StringBuilder();
                    while (!stk.isEmpty()) {
                        tmp.append(stk.pop());
                    }
                    ans.append(map.getOrDefault(tmp.reverse().toString(), "?"));
                    continue;
                }
                if (push) {
                    stk.push(chas[i]);
                    i++;
                    continue;
                }
                ans.append(chas[i]);
                i++;
            }
            return ans.toString();
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
//            handler.getPrime(200);
//            handler.maxNiceDivisors(73);
            handler.pow(3, 2);
        }

        //        int MOD = 1_000_000_007;
        int MOD = (int) 1e9 + 7;

        private long pow(int x, int n) {
            long N = n, X = x;
            long ans = 1L;
            while (N > 0) {
                if ((N & 1) == 1) ans = ans * X % MOD;
                X = X * X % MOD;
                N >>= 1;
            }
            return ans;
        }


        public int maxNiceDivisors(int n) {
            if (n <= 3) return n - 1;
            int a = n / 3, b = n % 3;
            if (b == 0) return (int) (pow(3, a) % MOD);
            if (b == 1) return (int) ((pow(3, a - 1) * 2 * 2) % MOD);
            return (int) ((pow(3, a) * 2) % MOD);

        }


        /**
         *
         typedef long long LL;

         class Solution {
         public:
         const int MOD = 1e9 + 7;
         int qmi(int a, int k) {
         int res = 1;
         while (k) {
         if (k & 1) res = (LL)res * a % MOD;
         a = (LL)a * a % MOD;
         k >>= 1;
         }
         return res;
         }


         int maxNiceDivisors(int n) {
         if (n <= 3) return n;
         if (n % 3 == 0) return qmi(3, n / 3);
         if (n % 3 == 1) return (LL)qmi(3, (n - 4) / 3) * 4 % MOD;
         return (LL)qmi(3, (n - 2) / 3) * 2 % MOD;
         }
         };
         */
    }
}
