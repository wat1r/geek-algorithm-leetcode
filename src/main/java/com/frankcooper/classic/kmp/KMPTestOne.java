package com.frankcooper.classic.kmp;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/21 19:00
 * @description:
 */
public class KMPTestOne {


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            _1st.getnext();

            String s = "ABABCABAA";
            int[] next = new int[s.length()];
//            _1st.getNext(s.toCharArray(), next);


            handler.getNext1(s.toCharArray(), next);

        }


        /**
         * https://zhuanlan.zhihu.com/p/369617900
         * @param text
         * @param pattarn
         * @return
         */
        public int kmp(char[] text, char[] pattarn) {
            int[] next = new int[pattarn.length];
            getNext1(pattarn, next);
            int m = 0;//matchLength
            for (int i = 0; i < text.length; i++) {
                while (m > 0 && pattarn[m] != text[i]) {
                    m = next[m - 1];
                }
                if (text[i] == pattarn[m]) {
                    m++;
                    if (m == pattarn.length) {
                        return i - m + 1;
                    }
                }
            }
            return -1;
        }

        private void getNext1(char[] pattern, int[] next) {
            int q = 0;//q代表前一个字符前后缀能匹配的最大长度
            for (int i = 1; i < pattern.length; i++) {//next[0] = 0，因此从1开始
                while (q > 0 && pattern[q] != pattern[i]) {//递归直到q为0（没有匹配的前缀）或者当前字符与q相等时（不断“递归”查前缀匹配的前一个位置q）
                    q = next[q - 1];//如果不相等，如“acad”,i=3,q=1,则q变成next[q-1](q-1是不匹配的前一个位置)
                }
                if (pattern[q] == pattern[i]) {
                    q++;
                }
                next[i] = q;
            }
        }


        /**
         * https://www.bilibili.com/video/BV1hW411a7ys
         *
         * @param pattern
         * @param next
         */
        private static void getNext(char[] pattern, int[] next) {
//            next[0] = 0;
            int len = 0, n = pattern.length;
            int i = 1;
            while (i < n) {
                if (pattern[i] == pattern[len]) {
                    len++;
                    next[i] = len;
                    i++;
                } else {
                    if (len > 0) len = next[len - 1];
                    else next[i++] = len;
                }
            }
            for (int x : next) {
                System.out.printf("%d ", x);
            }
        }
    }
}
