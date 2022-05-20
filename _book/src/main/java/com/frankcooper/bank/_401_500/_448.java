package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _448 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<Integer> findDisappearedNumbers(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != nums[nums[i] - 1]) {
                    int t = nums[i];
                    nums[i] = nums[nums[i] - 1];
                    nums[t - 1] = t;
                }
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) res.add(i + 1);//缺失的数是i+1
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
            handler.findDisappearedNumbers(nums);
        }

        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> resList = new ArrayList<>();
            if (nums == null || nums.length == 0) return resList;
            for (int i = 0; i < nums.length; i++) {
                int index = Math.abs(nums[i]) - 1;
                nums[index] = nums[index] < 0 ? nums[index] : -nums[index];
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    resList.add(i + 1);
                }
            }
            return resList;
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
