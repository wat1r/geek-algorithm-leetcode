package com.frankcooper.bank._501_1000;


import java.util.*;

import com.frankcooper.swordoffer.utils.PrintUtils;
import org.junit.Assert;


public class _698 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {4, 3, 2, 3, 5, 2, 1};
            int k = 4;
//            Assert.assertTrue(handler.canPartitionKSubsets(nums,k));
            nums = new int[]{2,2,2,2,3,4,5};
            k = 4;
            Assert.assertFalse(handler.canPartitionKSubsets(nums,k));

        }


        /**
         * WA
         * @param nums
         * @param k
         * @return
         */
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int N = nums.length;
            int sum = 0;
            for (int i : nums) sum += i;
            if (sum % k != 0) return false;
            int T = sum / k;
            boolean[][] f = new boolean[N][T + 1];
            for (int i = 0; i < N; i++) f[i][0] = true;
            for (int j = 0; j <= T; j++) if (j == nums[0]) f[0][j] = true;
            for (int i = 1; i < N; i++) {
                for (int j = 1; j <= T; j++) {
                    f[i][j] = f[i - 1][j];
                    if (j >= nums[i]) f[i][j] = f[i - 1][j] || f[i - 1][j - nums[i]];
                }
            }
            PrintUtils.printMatrix(f);
            return f[N - 1][T];
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
