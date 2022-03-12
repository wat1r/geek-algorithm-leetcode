package com.frankcooper.bank._601_700;


import org.junit.Assert;


public class _698 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {4, 3, 2, 3, 5, 2, 1};
            int k = 4;
//            Assert.assertTrue(handler.canPartitionKSubsets(nums,k));
            nums = new int[]{2, 2, 2, 2, 3, 4, 5};
            k = 4;
            Assert.assertFalse(handler.canPartitionKSubsets(nums, k));

        }


        /**
         *
         * @param nums
         * @param K
         * @return
         */


        public boolean canPartitionKSubsets(int[] nums, int K) {
            int N = nums.length;
            int sum = 0;
            for (int i : nums) sum += i;
            if (sum % K != 0) return false;//如果不能被K整除，返回false
            int T = sum / K;//每个子集的目标和 target
            boolean[] vis = new boolean[N];
            return backtracing(nums, vis, K, T, 0, 0);
        }

        /**
         * @param nums   给定的子集数组
         * @param vis    记录该子集数组的元素是否访问过
         * @param k      当前处理到还剩下的子集的个数，总的是K
         * @param target 每个子集的目标和 也就是T
         * @param curr   当前这个处理到的这个潜在的子集的和
         * @param idx    当前处理到的子集数组的下标
         * @return
         */
        public boolean backtracing(int[] nums, boolean[] vis, int k, int target, int curr, int idx) {
            //还剩下一个子集时候，已经结束了
            if (k == 1) return true;
            //或者下面的剪枝可以写这里
//            if(curr>target) return false;
            //当前子集的和到target的时候，需要进行下一个子集的探测，已经找到了当前子集，所以k-1， 重新开始找子集，curr 和 idx 重新初始化
            if (curr == target) return backtracing(nums, vis, k - 1, target, 0, 0);
            for (int i = idx; i < nums.length; i++) {
                //这里的剪枝 && curr + nums[i] <= target 起到很大加速作用，如果当前的值，加上我们要的这个i进入下一层，已经大于target这个值时，说明溢出了，没必要继续了
                if (!vis[i] && curr + nums[i] <= target) {
                    vis[i] = true;
                    if (backtracing(nums, vis, k, target, curr + nums[i], i + 1)) return true;
                    vis[i] = false;
                }
            }
            return false;

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
