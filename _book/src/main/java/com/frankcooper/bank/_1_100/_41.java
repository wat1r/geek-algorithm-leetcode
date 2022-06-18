package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.*;

import org.junit.Assert;

public class _41 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {3, 4, -1, 1};
            handler.firstMissingPositive(nums);

        }

        public int firstMissingPositive(int[] nums) {
            BitSet bitSet = new BitSet();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] >= 1 && nums[i] <= n + 1) {
                    bitSet.set(nums[i]);
                }
            }
            for (int i = 1; i <= n; i++) {
                if (!bitSet.get(i)) return i;
            }
            return n + 1;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                //前两个条件是为了判断nums[i]是否在0-n范围内，防止index越界
                //后一个是判断i 这个index（当前的值）和nums[i]-1这个index（标准位置）是否相等，
                //标准位置:某个数nums[i] 落整个array的i-1的位置上
                while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1);
                }
            }
            //判断第一个出现异常的数字
            for (int i = 0; i < n; i++) {
                if (nums[i] - 1 != i) {
                    return i + 1;
                }
            }
            return n + 1;
            //[3, 4, 4, -1, 1]->[1,4,3,4,-1] 异常的时index为1的数nums[1] 其值约为 （1+1=2）但是实际是4
            //[3, 4, -1, 1]  -->[1,-1,3,4]
            //[3,4,-1,-2,1,5,16,0,2,0]-->[1,2,3,4,5,-1,16,0,-2,0]
        }

        private void swap(int[] nums, int m, int n) {
            int temp = nums[m];
            nums[m] = nums[n];
            nums[n] = temp;
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
