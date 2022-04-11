package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

//https://leetcode-cn.com/contest/weekly-contest-259
public class Week259 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public int finalValueAfterOperations(String[] operations) {
            Map<String, Integer> dict = new HashMap<>();
            dict.put("++X", 1);
            dict.put("X++", 1);
            dict.put("--X", -1);
            dict.put("X--", -1);
            int res = 0;
            for (String op : operations) {
                res += dict.get(op);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{1, 2, 3};
            Assert.assertEquals(2, handler.sumOfBeauties(nums));
        }

        public int sumOfBeauties(int[] nums) {
            int n = nums.length;
            //从前往后，之前的遍历过的最大的数比当前nums[i]大的数，有的话，就设置为true，没有的话设置为false
            boolean[] start = new boolean[n];
            int t = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] > t) t = nums[i];
                else start[i] = true;
            }
            //从后往前，之前遍历的过的最小的数比当前nums[i]大的数，有的话，就设置为true，没有的话设置为false
            boolean[] end = new boolean[n];
            t = 100010;
            for (int i = n - 1; i >= 0; i--) {
                if (nums[i] < t) t = nums[i];
                else end[i] = true;
            }
            int total = 0;
            for (int i = 0; i < n; i++) {
                if (i > 0 && i < n - 1) {
                    if (!start[i] && !end[i]) total += 2;
                    else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) total += 1;
                }
//                else total += 0;
            }
            return total;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();

        }

        class DetectSquares {

            //k:x v:{k:y,[x,y]出现的次数}
            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

            public DetectSquares() {

            }

            public void add(int[] point) {
                int x = point[0], y = point[1];
                Map<Integer, Integer> sub = map.getOrDefault(x, new HashMap<>());
                sub.put(y, sub.getOrDefault(y, 0) + 1);
                map.put(x, sub);
            }

            public int count(int[] point) {
                int x = point[0], y = point[1];
                Map<Integer, Integer> sub = map.getOrDefault(x, new HashMap<>());
                int total = 0;
                for (int ny : sub.keySet()) {
                    if (ny == y) continue;
                    int c1 = sub.get(ny);//[x,ny]的数量
                    int len = y - ny;//正方形的边长
                    int[] nums = new int[]{x - len, x + len};
                    for (int nx : nums) {
                        Map<Integer, Integer> tmp = map.getOrDefault(nx, new HashMap<>());
                        int c2 = tmp.getOrDefault(y, 0);
                        int c3 = tmp.getOrDefault(ny, 0);
                        total += c1 * c2 * c3;
                    }
                }
                return total;
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
