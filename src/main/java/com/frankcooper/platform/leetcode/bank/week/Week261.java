package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week261 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "OXOOX";
            Assert.assertEquals(2, handler.minimumMoves(s));

        }


        public int minimumMoves(String s) {
            int res = 0;
            for (int i = 0; i < s.length(); ) {
                if (s.charAt(i) == 'O') {
                    i++;
                    continue;
                }
                String t = s.substring(i, Math.min(i + 3, s.length()));
                for (char c : t.toCharArray()) {
                    if (c == 'X') {
                        res++;
                        break;
                    }
                }
                i += 3;
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int[] missingRolls(int[] rolls, int mean, int n) {
            int m = rolls.length;
            int summ = 0;
            for (int x : rolls) summ += x;
            int sumn = (n + m) * mean - summ;
            if (1 * n > sumn || sumn > 6 * n) return new int[]{};
            int avg = sumn / n;
            int[] res = new int[n];
            Arrays.fill(res, avg);
            sumn -= avg * n;
            int i = 0;
            while (sumn > 0) {
                res[i++]++;
                sumn--;
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
//            Assert.assertFalse(handler.stoneGameIX(new int[]{5, 1, 2, 4, 3}));
            Assert.assertFalse(handler.stoneGameIX(new int[]{15, 20, 10, 13, 14, 15, 5, 2, 3}));
        }


        public boolean stoneGameIX(int[] stones) {
            int[] cnt = new int[3];
            for (int x : stones) cnt[x % 3]++;
            //开头移除2的情况
            int[] arr = new int[]{cnt[0], cnt[2], cnt[1]};
            return check(cnt) || check(arr);

        }

        private boolean check(int[] cnt) {
            if (cnt[1] == 0) return false;
            cnt[1]--;//开头为1
            //计算回合的数量
            int turn = 1 + Math.min(cnt[1], cnt[2]) * 2 + cnt[0];
            //序列末尾可以再加个1
            if (cnt[1] > cnt[2]) {
                turn++;
                cnt[1]--;
            }
            //回合数为奇数的时候，且还有剩余石子
            return turn % 2 == 1 && cnt[1] != cnt[2];
        }

    }

    static class _3rd_1 {
        public static void main(String[] args) {
            _3rd_1 handler = new _3rd_1();
            Assert.assertFalse(handler.stoneGameIX(new int[]{2, 2, 3}));
        }


        //https://www.youtube.com/watch?v=8MTch2zTOoY&ab_channel=HuifengGuan
        public boolean stoneGameIX(int[] stones) {
            int[] cnt = new int[3];
            for (int x : stones) cnt[x % 3]++;
            int[] t1 = Arrays.copyOf(cnt, 3);
            if (t1[1] > 0) {
                t1[1]--;
                if (!win(t1, 1, 1)) return true;
            }
            int[] t2 = Arrays.copyOf(cnt, 3);
            if (t2[2] > 0) {
                t2[2]--;
                if (!win(t2, 2, 1)) return true;
            }
            return false;
        }

        private boolean win(int[] t, int sum, int turn) {
            if (t[0] + t[1] + t[2] == 0) {
                if (turn == 1) return true;
                else return false;
            }
            if (t[0] > 0) {
                t[0]--;
                return !win(t, sum, 1 - turn);
            } else if (sum % 3 == 1) {
                if (t[1] > 0) {
                    t[1]--;
                    return !win(t, sum + 1, 1 - turn);
                } else {
                    return false;
                }
            } else if (sum % 3 == 2) {
                if (t[2] > 0) {
                    t[2]--;
                    return !win(t, sum + 2, 1 - turn);
                } else {
                    return false;
                }
            }
            return false;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            String s = "leet";
            int k = 3;
            char letter = 'e';
            int repetition = 1;
//            Assert.assertEquals("eet", handler.smallestSubsequence(s, k, letter, repetition));
            s = "aaabbbcccddd";
            k = 3;
            letter = 'b';
            repetition = 2;
            Assert.assertEquals("abb", handler.smallestSubsequence(s, k, letter, repetition));


        }


        public String smallestSubsequence(String s, int k, char letter, int repetition) {

            //letter这个字符出现的次数
            int cnt = 0;
            for (char c : s.toCharArray()) {
                if (c == letter) cnt++;
            }
            //m是s中要删除的字符的数量，留下的字符从长度是k个
            int n = s.length(), m = n - k;
            StringBuilder res = new StringBuilder();
            int p = 0;//目前为止letter已扫描了的次数
            for (int i = 0; i < n; i++) {
                //删除逆序的字母
                while (m > 0 && res.length() > 0 && res.charAt(res.length() - 1) > s.charAt(i)) {
                    if (res.charAt(res.length() - 1) == letter) {
                        if (repetition > cnt - 1 + p) {//后面的letter不够凑成repetition个letter
                            break;
                        }
                        p--;//删除一个letter
                    }
                    res.deleteCharAt(res.length() - 1);
                    m--;
                }
                if (s.charAt(i) == letter) {
                    p++;//扫描letter的次数+1
                    cnt--;//使用一次letter -1
                }
                res.append(s.charAt(i));
            }
            while (res.length() > k) {
                if (res.charAt(res.length() - 1) == letter) p--;
                res.deleteCharAt(res.length() - 1);
            }
            for (int i = k - 1; i >= 0; i--) {
                if (p < repetition && res.charAt(i) != letter) {
                    res.setCharAt(i, letter);
                    p++;
                }
            }
            return res.toString();
        }


    }
}
