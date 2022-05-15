package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _944 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String[] strs = {"a", "b"};
            strs = new String[]{"cba", "daf", "ghi"};
            handler.minDeletionSize(strs);

        }

        public int minDeletionSize(String[] strs) {
            int res = 0;
            int m = strs[0].length(), n = strs.length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (j < n - 1 && strs[j + 1].charAt(i) < strs[j].charAt(i)) {
                        res++;
                        break;
                    }
                }
            }
            return res;
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
