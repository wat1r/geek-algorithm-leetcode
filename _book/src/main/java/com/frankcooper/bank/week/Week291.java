package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week291 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public String removeDigit(String number, char digit) {
            String res = "";
            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i) == digit) {
                    String t = number.substring(0, Math.max(0, i)) + number.substring(i + 1);
                    if (t.compareTo(res) > 0) res = t;
                }
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] cards = {3, 4, 2, 3, 4, 7};
            //3
            Assert.assertEquals(4, handler.minimumCardPickup(cards));
            cards = new int[]{95, 11, 8, 65, 5, 86, 30, 27, 30, 73, 15, 91, 30, 7, 37, 26, 55, 76, 60, 43, 36, 85, 47, 96, 6};
            Assert.assertEquals(3, handler.minimumCardPickup(cards));
        }

        public int minimumCardPickup(int[] cards) {
            int len = -1;
            Set<Integer> set = new HashSet<>();
            for (int i = 0, j = 0; i < cards.length; i++, j++) {
                while (j < cards.length && !set.contains(cards[j])) {
                    set.add(cards[j++]);
                }
                while (j < cards.length && cards[i] != cards[j]) {
                    set.remove(cards[i++]);
                }
                //bug了一次 1,0,5,3
                if (len == -1 && j < cards.length && cards[i] == cards[j]) len = j - i + 1;
                else len = Math.min(len, j - i + 1);
            }
            return len;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {2, 3, 3, 2, 2};
            int k = 2, p = 2;
            Assert.assertEquals(11, handler.countDistinct(nums, k, p));
        }

        public int countDistinct(int[] nums, int k, int p) {
            boolean[] f = new boolean[210];
            for (int x : nums) {
                if (x % p == 0) f[x] = true;
            }
            int cnt = 0;
            Set<String> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                int t = 0;
                StringBuilder key = new StringBuilder();
                for (int j = i; j < nums.length; j++) {
                    if (f[nums[j]]) t++;
                    key.append(nums[j]).append("#");
                    if (t > k) break;
                    System.out.println(key);
                    if (!set.contains(key.toString())) {
                        cnt++;
                        set.add(key.toString());
                    }
                }
            }
            return cnt;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            String s = "abbca";
            handler.appealSum(s);
        }

        //WA presum
        public long appealSum(String s) {
            int n = s.length();
            int[] pre = new int[n + 1];
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (!set.contains(s.charAt(i))) {
                    pre[i + 1] = pre[i] + 1;
                    set.add(s.charAt(i));
                } else {
                    pre[i + 1] = Math.max(1, pre[i]);
                }
            }
            long res = 0;
            for (int i = 1; i <= n; i++) {
                for (int len = 1; len + i <= n; len++) {
                    int t = Math.max(1, pre[Math.min(len + i + 1, n)] - pre[i - 1]);
                    res += t;
                    System.out.printf("%d,%s\n", t, s.substring(i, i + len));
                }
            }
            return res;
        }
    }

    //https://leetcode-cn.com/problems/total-appeal-of-a-string/solution/by-endlesscheng-g405/
    static class _4th_1 {
        public static void main(String[] args) {
            _4th_1 handler = new _4th_1();
//            Assert.assertEquals(28, handler.appealSum("abbca"));
//            Assert.assertEquals(20, handler.appealSum("code"));
            Assert.assertEquals(52, handler.appealSum("abcdbe"));

        }

        public long appealSum(String s) {
            int n = s.length();
            //sum[i]为字符s[0...i]的引力值之和
            int[] sum = new int[n];
            sum[0] = 1;
            //字符最近一次出现的位置
            int[] pos = new int[26];
            Arrays.fill(pos, -1);
            pos[s.charAt(0) - 'a'] = 0;
            long res = sum[0];
            for (int i = 1; i < n; i++) {
                char c = s.charAt(i);
                if (pos[c - 'a'] == -1) sum[i] = sum[i - 1] + i + 1;
                else sum[i] = sum[i - 1] + (i - pos[c - 'a']);//该字符最近一次出现的位置
                pos[c - 'a'] = i;
                res += sum[i];
            }
            return res;
        }
    }

    static class _4th_2 {
        public static void main(String[] args) {
            _4th_2 handler = new _4th_2();
            Assert.assertEquals(52, handler.appealSum("abcdbe"));
        }

        //TLE 63 / 76
        public long appealSum(String s) {
            int[] pos = new int[26];
            int n = s.length();
            Arrays.fill(pos, -1);
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < 26; i++) map.put(i, -1);
            //pos[i]表示当前字符出现时，该字符前一出现的位置
            for (int i = 0; i < s.length(); i++) {
                int k = s.charAt(i) - 'a';
                pos[k] = map.get(k);
                map.put(k, i);
            }
            int[][] dp = new int[n][n];
            long res = dp[0][0] = 1;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (j == 0) continue;
                    if (i == j) {
                        dp[i][j] = 1;
                        res += 1;
                    } else {
                        if (pos[s.charAt(j) - 'a'] >= i && pos[s.charAt(j) - 'a'] < j) dp[i][j] = dp[i][j - 1];
                        else dp[i][j] = dp[i][j - 1] + 1;
                        res += dp[i][j];
                    }
                }
            }
            return res;
        }


    }

    static class _4th_3 {
        public static void main(String[] args) {

        }


        public long appealSum(String s) {
            long res = 0;
            int[] pos = new int[26];
            int sum = 0;
            Arrays.fill(pos, -1);
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                sum += i - pos[c];
                res += sum;
                pos[c] = i;
            }
            return res;
        }
    }

    static class _4th_4 {
        public static void main(String[] args) {
            _4th_4 handler = new _4th_4();
            Assert.assertEquals(52, handler.appealSum("abcdbe"));
        }

        public long appealSum(String s) {
            int[] pos = new int[26];
            long sum = 0;
            Arrays.fill(pos, -1);
            int n = s.length();
            for (int i = 0; i < n; i++) {
                int c = s.charAt(i) - 'a';
                sum += (n - i) * (i - pos[c]);
                pos[c] = i;
            }
            return sum;
        }
    }


}
