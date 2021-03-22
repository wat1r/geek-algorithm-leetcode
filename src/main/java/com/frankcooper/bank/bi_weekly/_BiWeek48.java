package com.frankcooper.bank.bi_weekly;


import org.junit.Assert;
import org.omg.CORBA.INTERNAL;

import java.util.*;

public class _BiWeek48 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            Assert.assertEquals(handler.secondHighest("dfa12321afd"), 2);
//            Assert.assertEquals(handler.secondHighest("abc1111"), -1);
//            Assert.assertEquals(handler.secondHighest("sjhtz8344"), 4);
            Assert.assertEquals(handler.secondHighest("vwkxfq9791769"), 7);
        }


        public int secondHighest(String s) {
            int first = -1, second = -1;
            int[] arr = new int[10];
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    int c = s.charAt(i) - '0';
                    arr[c]++;
                }
            }
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] != 0 && first == -1) {
                    first = i;
                    continue;
                }
                if (arr[i] != 0 && second == -1) {
                    second = i;
                    break;
                }
            }
            return second;
        }


       /* public int secondHighest(String s) {
            int first = -1, second = -1;
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) >= '0' && s.charAt(i) < '9') {
                    int c = s.charAt(i) - '0';
                    set.add(c);
                    if (first == -1) {
                        first = c;
                        continue;
                    }
                    if (first < c) {
                        second = first;
                        first = c;
                    } else if (set.size() >= 2 && c > second) {
                        second = c;
                    }
//                    if(c > secon)
                }
            }
            return second;
        }*/

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        class AuthenticationManager {

            int timeToLive;
            int cnt = 0;
            HashMap<String, Integer> map;

            public AuthenticationManager(int timeToLive) {
                this.timeToLive = timeToLive;
                this.map = new HashMap<>();
            }

            public void generate(String tokenId, int currentTime) {
                map.put(tokenId, currentTime + timeToLive);
                cnt++;
            }

            public void renew(String tokenId, int currentTime) {
                if (!map.containsKey(tokenId) || map.get(tokenId) <= currentTime) {
                    if (cnt > 0) cnt--;
                    return;
                }
                map.put(tokenId, currentTime + timeToLive);
            }

            public int countUnexpiredTokens(int currentTime) {
                int ans = 0;
                for (Map.Entry<String, Integer> e : map.entrySet()) {
                    if (e.getValue() > currentTime) ans++;
                }
                return ans;
            }
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.getMaximumConsecutive(new int[]{1, 1, 1, 4});
        }

        public int getMaximumConsecutive(int[] coins) {
            Arrays.sort(coins);
            int ans = 1;
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] <= ans) {
                    ans += coins[i];
                } else {
                    break;
                }
            }
            return ans;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public int maxScore(int[] nums) {
            int n = nums.length;
            int maxn = 1 << n;
            int[] dp = new int[maxn];
            int[] cnt = new int[maxn];
            return 0;
        }


        public int gcd(int a, int b) {
            System.out.printf("a:%d,b:%d\n", a, b);
            return b == 0 ? a : gcd(b, a % b);
        }
    }


}
