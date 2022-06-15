package com.frankcooper.bank._601_700;

/*import java.util.*;
import org.junit.Assert;*/
public class _670 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        //https://leetcode.cn/problems/maximum-swap/solution/0ms-100-bu-miao-da-wo-by-wang-xue-lei-2-iyz9/
        public int maximumSwap(int num) {
            char[] ch = String.valueOf(num).toCharArray();
            int n = ch.length, maxIndex = n - 1;
            int[] arr = new int[n];
            for (int i = n - 1; i >= 0; --i) {
                if (ch[i] > ch[maxIndex]) {
                    maxIndex = i;
                }
                arr[i] = maxIndex;
            }
            for (int i = 0; i < n; i++) {
                if (ch[i] != ch[arr[i]]) {
                    char t = ch[i];
                    ch[i] = ch[arr[i]];
                    ch[arr[i]] = t;
                    break;
                }
            }
            return Integer.parseInt(new String(ch));
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
