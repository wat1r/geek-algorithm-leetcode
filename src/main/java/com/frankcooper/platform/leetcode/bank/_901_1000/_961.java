package com.frankcooper.platform.leetcode.bank._901_1000;

import java.util.*;

public class _961 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public int repeatedNTimes(int[] nums) {
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == nums[i + 1]) return nums[i];
            }
            return -1;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        //间隔最小为1
        public int repeatedNTimes(int[] nums) {
            int N = nums.length;
            for (int i = 0; i < nums.length - 1; ++i) {
                for (int j = 1; j <= 3 && i + j < N; ++j) {
                    if (nums[i] == nums[i + j])
                        return nums[i];
                }
            }
            return -1;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        //循环数组
        public int repeatedNTimes(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] == nums[(i + 1) % n]) {
                    return nums[i];
                }
                if (nums[i] == nums[(i + 2) % n]) {
                    return nums[i];
                }
            }
            return -1;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        //随机选择
        public int repeatedNTimes(int[] nums) {
            int n = nums.length;
            Random random = new Random();

            while (true) {
                int x = random.nextInt(n), y = random.nextInt(n);
                if (x != y && nums[x] == nums[y]) {
                    return nums[x];
                }
            }
        }

    }
}
