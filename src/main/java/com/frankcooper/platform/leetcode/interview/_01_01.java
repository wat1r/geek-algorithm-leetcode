package com.frankcooper.platform.leetcode.interview;

public class _01_01 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            System.out.println("test");
        }


        public boolean isUnique(String astr) {
            char[] ch = new char[26];
            for (char c : astr.toCharArray()) {
                if (ch[c - 'a'] != 0) return false;
                ch[c - 'a']++;
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "leetcode";
            s = "carefully";
            handler.isUnique(s);
        }

        public boolean isUnique(String astr) {
            int mark = 0;
            for (char ch : astr.toCharArray()) {
                //拿到每个字符的移动的位数 [0-25]开始
                int bit = ch - 'a';
                //如果这一位被对冲掉，返回false
                if ((mark & (1 << bit)) != 0) {
                    return false;
                } else {
                    //拼凑mark
                    mark |= (1 << bit);
                }
//                PrintUtils.toBinaryString(mark, 26);
            }
            return true;
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
