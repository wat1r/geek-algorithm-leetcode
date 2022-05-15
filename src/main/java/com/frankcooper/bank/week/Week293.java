package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week293 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            List<String> expected = Arrays.asList("a", "b", "a");
            Assert.assertEquals(expected, handler.removeAnagrams(new String[]{"a", "b", "a"}));


        }


        public List<String> removeAnagrams(String[] words) {
            List<String> res = new ArrayList<>();
            Set<String> set = new HashSet<>();
            for (String word : words) {
                char[] ch = word.toCharArray();
                Arrays.sort(ch);
                if (!set.contains(String.valueOf(ch))) {
                    res.add(word);
                    set.add(String.valueOf(ch));
                }
            }
            return res;
        }


    }

    static class _1st_1 {
        public static void main(String[] args) {

        }

        public List<String> removeAnagrams(String[] words) {
            List<String> res = new ArrayList<>();
            int n = words.length;
            for (int i = 0; i < n; i++) {
                int j = i;
                res.add(words[i]);
                while (j < n && check(words[i], words[j])) {
                    j++;
                }
                i = j - 1;
            }
            return res;
        }

        public boolean check(String s, String t) {
            char[] chs = s.toCharArray();
            char[] cht = t.toCharArray();
            Arrays.sort(chs);
            Arrays.sort(cht);
            return String.valueOf(chs).equals(String.valueOf(cht));
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int bottom = 2, top = 9;
            int[] special = {4, 6};
            handler.maxConsecutive(bottom, top, special);
//            bottom = 2;
//            top = 9;
//            special = new int[]{4, 6};
        }

        public int maxConsecutive(int bottom, int top, int[] special) {
            int maxx = 0;
            Arrays.sort(special);
            int index = 0;
            for (int i = bottom; i <= top; ) {
                int b = i;
                if (index < special.length && i < special[index]) {
                    i = special[index];
                }
                if (index >= special.length && b == i) {
                    i = top + 1;
                }
                int t = i - b;
                i++;
                index++;
                maxx = Math.max(maxx, t);
            }
            return maxx;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] cs = new int[]{1, 5, 3};
            handler.largestCombination(cs);
        }

        //统计这些candidates上二进制为1的个数
        public int largestCombination(int[] candidates) {
            int n = 24;
            int[] bits = new int[n];
            int maxx = 0;
            for (int c : candidates) {
                for (int i = 0; i < n; i++) {
                    //当前位数上是否是1
                    if (((1 << i) & c) != 0) {
                        bits[i]++;
                    }
                    maxx = Math.max(maxx, bits[i]);
                }
            }
            return maxx;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        class CountIntervals {

            public CountIntervals() {

            }

            public void add(int left, int right) {

            }

            public int count() {


                return 0;
            }
        }

    }
}
