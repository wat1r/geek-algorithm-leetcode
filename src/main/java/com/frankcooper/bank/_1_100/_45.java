package com.frankcooper.bank._1_100;


import org.junit.Assert;

//45. 跳跃游戏 II 45. Jump Game II Hard
public class _45 {
    public static void main(String[] args) {

        System.out.println();
        System.out.println("test");
    }


    static class _1st {
        public int jump(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            dp[0] = 0;
            for (int i = 1; i < n; i++) {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (nums[j] >= (i - j)) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
            return dp[n - 1];
        }
    }


    static class _2nd {


        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{2, 3, 1, 1, 4};
            Assert.assertEquals(handler.jump(nums), 2);
        }

        public int jump(int[] nums) {
            int N = nums.length, INF = Integer.MAX_VALUE / 2;
            int[] f = new int[N];
            f[0] = 0;
            for (int i = 1; i < N; i++) {
                f[i] = INF;
                for (int j = 0; j < i; j++) {
                    if (j + nums[j] >= i) f[i] = Math.min(f[i], f[j] + 1);
                }
            }
            return f[N - 1];
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = new int[]{2, 3, 1, 1, 4};
            Assert.assertEquals(handler.jump(nums), 2);
        }

        int N;
        Integer[] memo;//返回当前位置索引curr跳到最后位置的最小步数

        public int jump(int[] nums) {
            N = nums.length;
            memo = new Integer[N];
            return helper(nums, 0);
        }

        /**
         * 返回当前位置索引curr跳到最后位置的最小步数
         *
         * @param nums 数组
         * @param curr 当前所处的位置
         * @return
         */
        public int helper(int[] nums, int curr) {
            //当curr跳到末尾或者跳过末尾的时候，递归结束
            if (curr >= N - 1) return 0;
            if (memo[curr] != null) return memo[curr];
            int ans = Integer.MAX_VALUE / 2;
            //对于当前的curr索引，最多可以跳nums[curr]步，最少我们从1开始起跳，0没有意义，出去了
            for (int i = 1; i <= nums[curr]; i++) {
                //当前的索引是curr，可以跳i步，到i+curr ，每做一次，就跳了一次
                ans = Math.min(ans, helper(nums, i + curr) + 1);
            }
            return memo[curr] = ans;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        int N;

        public int jump(int[] nums) {
            N = nums.length;
            return helper(nums, 0);
        }

        public int helper(int[] nums, int curr) {
            //当curr跳到末尾或者跳过末尾的时候，递归结束
            if (curr >= N - 1) return 0;
            int ans = Integer.MAX_VALUE / 2;
            //对于当前的curr索引，最多可以跳nums[curr]步，最少我们从1开始起跳，0没有意义，出去了
            for (int i = 1; i <= nums[curr]; i++) {
                //当前的索引是curr，可以跳i步，到i+curr ，每做一次，就跳了一次
                ans = Math.min(ans, helper(nums, i + curr) + 1);
            }
            return ans;
        }
    }

    static class _5th {

        public static void main(String[] args) {
            _5th handler = new _5th();
            int[] nums = new int[]{2, 3, 1, 1, 4};
            Assert.assertEquals(handler.jump(nums), 2);
        }


        public int jump(int[] A) {
            int jumps = 0, curEnd = 0, curFarthest = 0;
            for (int i = 0; i < A.length - 1; i++) {
                curFarthest = Math.max(curFarthest, i + A[i]);
                if (i == curEnd) {
                    jumps++;
                    curEnd = curFarthest;
                }
            }
            return jumps;
        }
    }

}
