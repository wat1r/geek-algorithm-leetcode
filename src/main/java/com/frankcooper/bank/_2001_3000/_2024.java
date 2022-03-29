package com.frankcooper.bank._2001_3000;

import java.util.*;

import org.junit.Assert;

public class _2024 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String answerKey = "TTFTTFTT";
            int k = 1;
            handler.maxConsecutiveAnswers(answerKey, k);

        }


        public int maxConsecutiveAnswers(String str, int k) {
            return Math.max(getMaxLength(str, 'T', k), getMaxLength(str, 'F', k));
        }

        //字符s中不至多包含k个c的最大长度
        private int getMaxLength(String s, char c, int k) {
            int n = s.length();
            int cnt = 0;
            int res = 0;
            //左右窗口，让r自增往右推
            for (int l = 0, r = 0; r < n; r++) {
                //如果[r]=c cnt需要统计+1
                if (s.charAt(r) == c) cnt++;
                //当cnt > k 说明[l,r]当前的窗口内有超过了k个字符c，缩减左窗口
                while (cnt > k) {
                    if (s.charAt(l++) == c) cnt--;
                }
                //统计最大长度
                res = Math.max(res, r - l + 1);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String answerKey = "TTFTTFTT";
            int k = 1;
            handler.maxConsecutiveAnswers(answerKey, k);
        }


        public int maxConsecutiveAnswers(String str, int k) {
            return Math.max(getMaxLength(str, 'T', k), getMaxLength(str, 'F', k));
        }

        //字符s中可以至多包含k个c的最大长度
        private int getMaxLength(String s, char c, int k) {
            //左右窗口
            int n = s.length(), l = 0, r = 0;
            int res = 0;
            while (r < n) {
                //[r]如果不是c的话，消耗掉一次k的值 k--
                if (s.charAt(r) != c) k--;
                while (k < 0) {
                    if (s.charAt(l) != c) k++;
                    l++;
                }
                r++;
                //注意已经向右移动过一次
                res = Math.max(res, r - l);
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();

            String answerKey = "TTFTTFTT";
            int k = 1;
            handler.maxConsecutiveAnswers(answerKey, k);
        }


        public int maxConsecutiveAnswers(String s, int k) {
            //maxf表示滑窗中，相同字符最大的出现次数，本题只是'T' 和 'F'
            int maxf = 0, l = 0, n = s.length();
            //count数组 可以缩减到[2]个长度
            int[] count = new int[26];
            for (int r = 0; r < n; ++r) {
                maxf = Math.max(maxf, ++count[s.charAt(r) - 'A']);
                //如果当前的滑窗的大小 大于 maxf('F'或者'T')出现的次数+k次替换操作，开始缩减l窗口，移除次数
                if (r - l + 1 > maxf + k) {
                    --count[s.charAt(l++) - 'A'];
                }
            }
            return n - l;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
