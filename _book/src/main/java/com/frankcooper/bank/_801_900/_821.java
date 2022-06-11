package com.frankcooper.bank._801_900;

import java.util.*;

public class _821 {

    //WA
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


            String s = "loveleetcode";
            char c = 'e';
            handler.shortestToChar(s, c);

        }

        public int[] shortestToChar(String s, char c) {
            char[] ch = s.toCharArray();
            int n = ch.length;
            int[] ans = new int[n];
            int i = 0, j = 0;
            while (j < n) {
                while (ch[j] != c) {
                    j++;
                }
                while (i != j) {
                    ans[i] = j - i;
                    i++;
                }
                i++;
                j++;
            }
            return ans;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "loveleetcode";
            char c = 'e';
            //[3,2,1,0,1,0,0,1,2,2,1,0]
            System.out.println(Arrays.toString(handler.shortestToChar(s, c)));
        }

        public int[] shortestToChar(String s, char c) {
            char[] ch = s.toCharArray();
            int n = ch.length;
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                if (c == ch[i]) continue;
                int l = i - 1, r = i + 1;
                while (l >= 0 && ch[l] != c) l--;
                while (r < n && ch[r] != c) r++;
                int a = (l >= 0 && ch[l] == c) ? l : Integer.MIN_VALUE / 2;
                int b = (r < n && ch[r] == c) ? r : Integer.MAX_VALUE / 2;
//                System.out.printf("%d -> %d -> %d -> %d\n", l, r, a, b);
                res[i] = Math.min(i - a, b - i);
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String s = "loveleetcode";
            char c = 'e';
            //[3,2,1,0,1,0,0,1,2,2,1,0]
            System.out.println(Arrays.toString(handler.shortestToChar(s, c)));
        }

        //先来一轮从左到右，再来一轮从右到左
        public int[] shortestToChar(String S, char C) {
            int n = S.length(), pos = -n, res[] = new int[n];
            for (int i = 0; i < n; ++i) {
                if (S.charAt(i) == C) pos = i;
                res[i] = i - pos;
            }
            for (int i = pos - 1; i >= 0; --i) {
                if (S.charAt(i) == C) pos = i;
                res[i] = Math.min(res[i], pos - i);
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        //
        public int[] shortestToChar(String s, char c) {
            int n = s.length();
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) dp[i] = s.charAt(i) == c ? 0 : n;
            for (int i = 1; i < n; i++) dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            for (int i = n - 2; i >= 0; i--) dp[i] = Math.min(dp[i], dp[i + 1] + 1);
            return dp;
        }
    }


    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            String s = "loveleetcode";
            char c = 'e';
            //[3,2,1,0,1,0,0,1,2,2,1,0]
            System.out.println(Arrays.toString(handler.shortestToChar(s, c)));
        }

        public int[] shortestToChar(String s, char c) {
            int n = s.length();
            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == c) {
                    queue.offer(i);
                    ans[i] = 0;
                }
            }

            while (!queue.isEmpty()) {
                int pos = queue.poll();
                //每一轮排除掉已经筛选过的 （不为0和-1）的 和 为 0 这种本身是c字符的位置
                if (pos - 1 >= 0 && ans[pos - 1] == -1) {
                    queue.offer(pos - 1);
                    ans[pos - 1] = ans[pos] + 1;
                }

                if (pos + 1 < n && ans[pos + 1] == -1) {
                    queue.offer(pos + 1);
                    ans[pos + 1] = ans[pos] + 1;
                }
            }
            return ans;
        }

    }

}
