package com.frankcooper.interview;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;

public class _16_16 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] arr = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
//            handler.subSort(arr);

        }


/*        public int[] subSort(int[] arr) {
            if (arr == null || arr.length == 0) return new int[]{-1, -1};
            int last = -1, first = -1;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int len = arr.length;
            for (int i = 0; i < len; i++) {
                if (arr[i] < max) {
                    last = i;
                } else {
                    max = Math.max(max, arr[i]);
                }

                if (arr[len - 1 - i] > min) {
                    first = len - 1 - i;
                } else {
                    min = Math.min(min, arr[len - 1 - i]);
                }
            }
            return new int[]{first, last};
        }*/


    }

    static class _2nd {
        public static void main(String[] args) throws IOException {
            _2nd handler = new _2nd();

            List<String> lines = FileUtils.readLines(new File("D:\\Dev\\SrcCode\\geek-algorithm-leetcode\\src\\main\\resources\\input.txt"), Charset.defaultCharset());
            for (String line : lines) {
                String[] ss = line.split(",");
                int[] arr = new int[ss.length];
                for (int i = 0; i < ss.length; i++) {
                    arr[i] = Integer.parseInt(ss[i]);
                }
                handler.subSort(arr);

            }
        }


        public int[] subSort(int[] arr) {
            int n = arr.length;
            int maxx = Integer.MIN_VALUE, minn = Integer.MAX_VALUE;
            int l = -1, r = -1;
            for (int i = 0; i < n; ++i) {
                if (i == 664) {
                    System.out.printf("");
                }
                if (arr[i] < maxx) r = i;
                else maxx = arr[i];
            }
            for (int i = n - 1; i >= 0; --i) {
                if (i == 668) {
                    System.out.printf("");
                }
                if (arr[i] > minn) l = i;
                else minn = arr[i];//少写了else
            }
            return new int[]{l, r};
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
