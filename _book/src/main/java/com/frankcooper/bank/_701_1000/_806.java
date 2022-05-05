package com.frankcooper.bank._701_1000;

/*import java.util.*;
import org.junit.Assert;*/
public class _806 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int[] numberOfLines(int[] widths, String s) {
            //行数 最后一行个留下的字符的宽度
            int lines = 1, num = 0;
            int i = 0;
            while (i < s.length()) {
                int t = widths[s.charAt(i) - 'a'];
                num += t;
                if (num > 100) {
                    lines++;
                    num = t;
                }
                i++;
            }
            return new int[]{lines, num};
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
