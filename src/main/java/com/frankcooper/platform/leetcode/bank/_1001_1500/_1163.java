package com.frankcooper.platform.leetcode.bank._1001_1500;

/*import java.util.*;
import org.junit.Assert;*/
public class _1163 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        //最大表示法
        public String lastSubstring(String s) {
            int n = s.length();
            int i = 0, j = 1, k = 0;
            char[] ch = s.toCharArray();
            while (i + k < n && j + k < n) {
                if (ch[i + k] == ch[j + k]) {
                    k++;
                    continue;
                }
                if (ch[i + k] > ch[j + k]) j = j + k + 1;
                else i = i + k + 1;
                if (i == j) j++;
                k = 0;
            }
            return s.substring(Math.min(i, j));

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
