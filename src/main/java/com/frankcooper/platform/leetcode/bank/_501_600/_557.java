package com.frankcooper.platform.leetcode.bank._501_600;

/*import java.util.*;
import org.junit.Assert;*/
public class _557 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public String reverseWords(String s) {
            String[] words = s.split(" ");
            StringBuilder sb = new StringBuilder();
            int len = words.length;
            if (words.length == 1) {
                return reverseString(s);
            }
            for (int i = 0; i < len - 1; i++) {
                sb.append(reverseString(words[i]) + " ");
            }
            sb.append(reverseString(words[len - 1]));
            return String.valueOf(sb);
        }

        public String reverseString(String s) {
            char[] chars = s.toCharArray();
            int i = 0, j = chars.length - 1;
            while (i < j) {
                char t = chars[i];
                chars[i++] = chars[j];
                chars[j--] = t;
            }
            return new String(chars);
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
