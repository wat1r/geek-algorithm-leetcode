package com.frankcooper.platform.leetcode.bank._101_200;

public class _186 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public void reverseWords(char[] str) {
            int i = 0;
            for (int j = 0; j < str.length; j++) {
                if (str[j] == ' ') {
                    reverse(str, i, j);
                    i = j + 1;
                }
            }
            reverse(str, i, str.length);
            System.out.println(String.valueOf(str));
            reverse(str, 0, str.length);
        }

        /**
         * 将 ch 的 [l, r] 进行翻转，
         * 注意，[l,r) 是左闭右开
         *
         * @param ch
         * @param l
         * @param r
         */
        private void reverse(char[] ch, int l, int r) {
            for (int k = l; k < (l + r) / 2; k++) {
                char tmp = ch[k];  // 位置 k 的元素
                int g = r - 1 - k + l;  // 位置 k 的对称位置
                ch[k] = ch[g];
                ch[g] = tmp;
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
        }
    }
}
