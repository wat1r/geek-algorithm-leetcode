package com.frankcooper.platform.leetcode.bank._101_200;

public class _152 {


    static class _1st {
        /**
         * https://leetcode-cn.com/problems/maximum-product-subarray/solution/dpfang-fa-xiang-jie-by-yang-cong-12/
         *
         * @param nums
         * @return
         */
        public int maxProduct(int[] nums) {
            int n = nums.length;
            if (n == 0) {
                return 0;
            } else if (n == 1) {
                return nums[0];
            }
            int p = nums[0];
            int maxP = nums[0];
            int minP = nums[0];
            for (int i = 1; i < n; i++) {
                int t = maxP;
                maxP = Math.max(Math.max(maxP * nums[i], nums[i]), minP * nums[i]);
                minP = Math.min(Math.min(t * nums[i], nums[i]), minP * nums[i]);
                p = Math.max(maxP, p);
            }
            return p;
        }
    }


    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {2, 3, -2, 4};
            handler.maxProduct(nums);
        }

        public int maxProduct(int[] nums) {
            int res = nums[0];
            for (int i = 1, maxx = res, minn = res; i < nums.length; i++) {
                if (nums[i] < 0) {
                    int t = maxx;
                    maxx = minn;
                    minn = t;
                }
                maxx = Math.max(nums[i], nums[i] * maxx);
                minn = Math.min(nums[i], nums[i] * minn);
                res = Math.max(res, maxx);
            }
            return res;
        }
    }

    static class _3rd {

        public int maxProduct(int[] nums) {
            Pair[] f = new Pair[nums.length];
            f[0] = new Pair(nums[0], nums[0]);
            int res = f[0].maxx;
            for (int i = 1; i < nums.length; i++) {
                Pair prev = f[i - 1];
                int maxx = Math.max(Math.max(nums[i], nums[i] * prev.maxx), nums[i] * prev.minn);
                int minn = Math.min(Math.min(nums[i], nums[i] * prev.minn), nums[i] * prev.maxx);
                f[i] = new Pair(maxx, minn);
                res = Math.max(res, maxx);
            }
            return res;
        }


        class Pair {
            int maxx;
            int minn;

            public Pair(int maxx, int minn) {
                this.maxx = maxx;
                this.minn = minn;
            }
        }
    }

    static class _4th {
        public int maxProduct(int[] nums) {
            if (nums.length == 0) return 0;
            if (nums.length == 1) return nums[0];
            int maxx = nums[0], minn = nums[0], res = nums[0];
            for (int i = 1; i < nums.length; i++) {
                int t = maxx;
                maxx = Math.max(Math.max(nums[i], maxx * nums[i]), minn * nums[i]);
                minn = Math.min(Math.min(nums[i], minn * nums[i]), nums[i] * t);
                res = Math.max(res, maxx);
            }
            return res;
        }
    }
}
