package com.frankcooper.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _398 {

    //hashmap 额外空间
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        class Solution {

            Map<Integer, List<Integer>> map = new HashMap<>();
            Random random = new Random();

            public Solution(int[] nums) {
                for (int i = 0; i < nums.length; i++) {
                    List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
                    list.add(i);
                    map.put(nums[i], list);
                }
            }

            public int pick(int target) {
                List<Integer> list = map.get(target);
                return list.get(random.nextInt(list.size()));
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }

    //蓄水池抽样
    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = {1, 2, 3, 3, 3};
            int n = 10;
            while (n-- > 0) {
                Solution solution = new Solution(nums);
                System.out.println(solution.pick(3));
                solution.pick(1);
            }

        }

        static class Solution {
            int[] nums;
            Random random;

            public Solution(int[] nums) {
                this.nums = nums;
                random = new Random();
            }

            public int pick(int target) {
                int res = 0;
                int cnt = 0;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == target) {
                        //统计target遇到的次数
                        cnt++;// 第 cnt 次遇到 target
                        //同一个数字的频数1/n的概率选出其中一个索引
                        int r = random.nextInt(cnt);
                        if (r == 0) {
                            res = i;
                        }
                    }
                }
                return res;
            }
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
