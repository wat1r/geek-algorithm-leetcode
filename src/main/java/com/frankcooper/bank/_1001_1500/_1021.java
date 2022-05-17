package com.frankcooper.bank._1001_1500;

/*import java.util.*;
import org.junit.Assert;*/
public class _1021 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public String removeOuterParentheses(String s) {
            StringBuilder sb = new StringBuilder();
            int level = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') level++;
                if (level > 1) sb.append(c);
                if (c == ')') level--;
            }
            return sb.toString();
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