package com.frankcooper.bank._901_1000;

public class _740 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = new int[]{3, 4, 2};
            handler.deleteAndEarn(nums);

        }


        public int deleteAndEarn(int[] nums) {
            if (nums.length == 1) return nums[0];
            int max = 0;
            for (int x : nums) max = Math.max(max, x);
            int[] arr = new int[max + 1];
            for (int x : nums) arr[x]++;
            int[] f = new int[max + 1];
            f[1] = arr[1] * 1;
            f[2] = Math.max(f[1], arr[2] * 2);
            for (int i = 3; i <= max; i++) {
                f[i] = Math.max(f[i - 1], f[i - 2] + arr[i] * i);
            }
            return f[max];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int deleteAndEarn(int[] nums) {
            int N = (int) 1e4 + 5;
            int[] cnt = new int[N];
            for (int x : nums) cnt[x]++;
            /**
             * 定义 f[i][0] 代表数值为 i 的数字「不选择」的最大价值；f[i][1] 代表数值为 i 的数字「选择」的最大价值
             */
            int[][] f = new int[N][2];
            for (int i = 1; i < N; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + cnt[i] * i;
            }
            return Math.max(f[N - 1][0], f[N - 1][1]);
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int deleteAndEarn(int[] nums) {
            int N = (int) 1e4 + 5;
            int[] cnt = new int[N];
            for (int x : nums) cnt[x]++;
            /**
             * 定义 f[i][0] 代表数值为 i 的数字「不选择」的最大价值；f[i][1] 代表数值为 i 的数字「选择」的最大价值
             */
            int[] f = new int[N];
            int res = 0;
            f[1] = cnt[1] * 1;
            for (int i = 2; i < N; i++) {
                f[i] = Math.max(f[i - 2] + cnt[i] * i, f[i - 1]);
                res = Math.max(res, f[i]);
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
