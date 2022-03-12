package com.frankcooper.bank._1501_2000;

public class _2000 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public String reversePrefix(String word, char ch) {
            int end = -1;
            for (int i = 0; i < word.toCharArray().length; i++) {
                if (word.charAt(i) == ch) {
                    end = i;
                    break;
                }
            }
            if (end == -1) return word;
            StringBuilder res = new StringBuilder();
            res.append(reverse(word.toCharArray(), 0, end));
            return res.toString();
        }

        private char[] reverse(char[] chas, int i, int j) {
            while (i < j) {
                char t = chas[i];
                chas[i++] = chas[j];
                chas[j--] = t;
            }
            return chas;
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
