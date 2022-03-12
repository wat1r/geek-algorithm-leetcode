package com.frankcooper.interview;

import java.util.*;

import org.junit.Assert;

public class _17_05 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            String[] array = {"A", "1", "B", "C", "D", "2", "3", "4", "E", "5", "F", "G", "6", "7", "H", "I", "J", "K", "L", "M"};
            array = new String[]{"42", "10", "O", "t", "y", "p", "g", "B", "96", "H", "5", "v", "P", "52", "25", "96", "b", "L", "Y", "z", "d", "52", "3", "v", "71", "J", "A", "0", "v", "51", "E", "k", "H", "96", "21", "W", "59", "I", "V", "s", "59", "w", "X", "33", "29", "H", "32", "51", "f", "i", "58", "56", "66", "90", "F", "10", "93", "53", "85", "28", "78", "d", "67", "81", "T", "K", "S", "l", "L", "Z", "j", "5", "R", "b", "44", "R", "h", "B", "30", "63", "z", "75", "60", "m", "61", "a", "5", "S", "Z", "D", "2", "A", "W", "k", "84", "44", "96", "96", "y", "M"};
            handler.findLongestSubarray(array);

        }


        public String[] findLongestSubarray(String[] array) {
            int n = array.length;
            int[] sum = new int[n];
            for (int i = 0; i < array.length; i++) {
                String s = array[i];
                //数字可能是 42 19 这样的
                if (s.compareTo("A") >= 0 && s.compareTo("z") <= 0) {
                    sum[i] = -1;
                } else {
                    sum[i] = 1;
                }
            }
            for (int i = 1; i < n; i++) sum[i] += sum[i - 1];
            int l = -1, r = -1;
            Map<Integer, Integer> map = new HashMap<>();//记录当前值的最左侧的下标
            for (int i = 0; i < n; i++) {
                if (sum[i] == 0) {//为0的情况
                    if (r - l + 1 < i + 1) {
                        r = i;
                        l = 0;
                    }
                    continue;
                }
                //记录下第一次出现的数字的最左侧的下标
                if (!map.containsKey(sum[i])) map.put(sum[i], i);
                else {
                    if (r - l + 1 < i - map.get(sum[i])) {
                        r = i;
                        l = map.get(sum[i]) + 1;
                    }
                }
            }
            if (l == -1 && r == -1) return new String[]{};
            return Arrays.copyOfRange(array, l, r + 1);//取头不取尾
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
