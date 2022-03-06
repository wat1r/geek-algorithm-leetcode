package com.frankcooper.bank._201_300;

import java.util.*;

import org.junit.Assert;

public class _213 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 0) return 0;
            if (n == 1) return nums[0];
            int[][] f = new int[n][2];
            //不选第一间
            for (int i = 1; i < n - 1; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + nums[i];
            }
            //选了倒数第2间 VS 选了倒数第二间 选了倒数第一间
            int x = Math.max(f[n - 2][1], f[n - 2][0] + nums[n - 1]);
            //选了第一间
            f[0][0] = 0;
            f[0][1] = nums[0];
            for (int i = 1; i < n - 1; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + nums[i];
            }
            int y = Math.max(f[n - 2][0], f[n - 2][1]);
            return Math.max(x, y);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 0) return 0;
            if (n == 1) return nums[0];
            //f[i][0]表示没有偷第i间房间的前提下，[0,i]间房间所能获得的总收益
            //f[i][1]表示偷第i间房间的前提下，[0,i]间房间所能获得的总收益
            int[][] f = new int[n][2];
            //不偷第一间
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + nums[i];
            }
            //因为第一间房间没偷，所以最后一间房间时 偷或者不偷
            //不偷：f[n - 1][0]
            //偷 :  f[n - 1][1]
            int x = Math.max(f[n - 1][1], f[n - 1][0]);
            //偷了第一间房间
            f[0][0] = 0;
            f[0][1] = nums[0];
            for (int i = 1; i < n - 1; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + nums[i];
            }
            //因为第一间房间偷了，所以最后一间房间时 不能偷了，需要考虑倒数第二间房间是否偷了
            //不偷：f[n - 2][0]
            //偷 :  f[n - 2][1]
            int y = Math.max(f[n - 2][0], f[n - 2][1]);
            return Math.max(x, y);
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 0) return 0;
            if (n == 1) return nums[0];
            //copyOfRange 函数取头不取尾，也就是[0,n-2]和[1,n-1]的范围
            return Math.max(sub_rob(Arrays.copyOfRange(nums, 0, n - 1))
                    , sub_rob(Arrays.copyOfRange(nums, 1, n)));
        }


        public int sub_rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
            int[] f = new int[n];
            f[0] = nums[0];
            f[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < n; i++) {
                f[i] = Math.max(f[i - 1], f[i - 2] + nums[i]);
            }
            return f[n - 1];
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 0) return 0;
            if (n == 1) return nums[0];
            //copyOfRange 函数取头不取尾，也就是[0,n-2]和[1,n-1]的范围
            return Math.max(sub_rob(Arrays.copyOfRange(nums, 0, n - 1))
                    , sub_rob(Arrays.copyOfRange(nums, 1, n)));
        }


        Integer[] memo;

        public int sub_rob(int[] nums) {
            memo = new Integer[nums.length];
            return helper(nums, nums.length - 1);
        }

        public int helper(int[] nums, int i) {
            if (i < 0) return 0;
            if (memo[i] != null) return memo[i];
            int steal = nums[i] + helper(nums, i - 2);
            int non_steal = helper(nums, i - 1);
            return memo[i] = Math.max(steal, non_steal);
        }
    }

    static class _5th {
        public int rob(int[] nums) {
            int n = nums.length;
            //处理一些边界返回
            if (n == 0) return 0;
            if (n == 1) return nums[0];
            //偷第一间房
            int[] prefix = new int[n + 1];
            //不偷第一间房
            int[] suffix = new int[n + 1];
            //初始化
            prefix[0] = 0;
            prefix[1] = nums[0];
            suffix[0] = 0;
            suffix[1] = 0;
            for (int i = 2; i <= n; i++) {
                prefix[i] = Math.max(prefix[i - 1], prefix[i - 2] + nums[i - 1]);
                suffix[i] = Math.max(suffix[i - 1], suffix[i - 2] + nums[i - 1]);
            }
            //prefix中的倒数第二件房间和suffix的最后一间房间  取最大值
            return Math.max(prefix[n - 1], suffix[n]);
        }
    }
}
