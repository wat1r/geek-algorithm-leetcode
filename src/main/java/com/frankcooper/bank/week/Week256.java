package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week256 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        //1984. 学生分数的最小差值
        public int minimumDifference(int[] nums, int k) {
            int i = 0, j = k - 1;
            Arrays.sort(nums);//排序后[i...i+k]段的首位两个数便是最小值和最大值
            int res = 100_005;
            while (j < nums.length) {
                res = Math.min(res, nums[j++] - nums[i++]);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String[] nums = {"2", "21", "12", "1"};
            int k = 3;
            handler.kthLargestNumber(nums, k);
        }


        //1985. 找出数组中的第 K 大整数
        public String kthLargestNumber(String[] nums, int k) {
            //按字典序的数字从大到小排列，返回k-1个数，即k大的数
            Arrays.sort(nums, ((o1, o2) -> {
                if (o1.length() > o2.length()) return -1;
                else if (o1.length() < o2.length()) return 1;
                return o2.compareTo(o1);
            }));
            return nums[k - 1];
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
