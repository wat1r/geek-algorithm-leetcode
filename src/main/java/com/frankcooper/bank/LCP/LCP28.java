package com.frankcooper.bank.LCP;

import java.util.*;

import org.junit.Assert;

public class LCP28 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {2, 5, 3, 5};
            int target = 6;
            nums = new int[]{2, 2, 1, 9};
            target = 10;
            handler.purchasePlans(nums, target);
        }

        //[2,2,1,9] 10


        public int purchasePlans(int[] nums, int target) {
            Arrays.sort(nums);
            int MOD = (int) 1e9 + 7;
            int i = 0, j = nums.length - 1, res = 0;
            for (; i < j; i++) {
                while (i < j && nums[i] + nums[j] > target) j--;
//                if (i < j && nums[i] + nums[j] <= target) {一定满足
                res = (res + j - i) % MOD;
//                }
            }
            return res % MOD;
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
