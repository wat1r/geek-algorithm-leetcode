package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

public class _1656 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        class OrderedStream {
            String[] arr;
            int ptr;

            //从1开始下标计算的，数据大小开n+1
            public OrderedStream(int n) {
                ptr = 1;
                arr = new String[n + 1];
            }

            public List<String> insert(int idKey, String value) {
                arr[idKey] = value;
                List<String> res = new ArrayList<>();
                //区分idKey和ptr
                while (ptr < arr.length && arr[ptr] != null) {
                    res.add(arr[ptr++]);
                }
                return res;
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
