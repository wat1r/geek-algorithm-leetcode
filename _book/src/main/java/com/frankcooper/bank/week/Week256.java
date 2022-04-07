package com.frankcooper.bank.week;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
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
            int[] tasks = new int[]{1, 2, 3};
            int sessionTime = 3;
            handler.minSessions(tasks, sessionTime);
        }


        public int minSessions(int[] tasks, int sessionTime) {
            int n = tasks.length, m = 1 << n;
            final int INF = 20;
            int[] dp = new int[m];
            Arrays.fill(dp, INF);

            // 预处理每个状态，合法状态预设为 1
            for (int i = 1; i < m; i++) {
                int state = i, idx = 0;
                int spend = 0;
                while (state > 0) {
                    int bit = state & 1;
                    if (bit == 1) {
                        spend += tasks[idx];
                    }
                    state >>= 1;
                    idx++;
                }
                if (spend <= sessionTime) {
                    dp[i] = 1;
                }
            }

            // 对每个状态枚举子集，跳过已经有最优解的状态
            for (int i = 1; i < m; i++) {
                if (dp[i] == 1) {
                    continue;
                }
                //method 1
                for (int j = 1; j <= i; j++) {
                    if ((j | i) == i) {
                        dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
                    }
                }
                //method 2
//                for (int j = i; j > 0; j = (j - 1) & i) {
//                    dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
//                }


//                int split = i >> 1;
//                // 由于转移是由子集与补集得来，因此可以将子集分割为两块，避免重复枚举
//                for (int j = (i - 1) & i; j > split; j = (j - 1) & i) {
//                    // i 状态的最优解可能由当前子集 j 与子集 j 的补集得来
//                    dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
//                }
            }

            return dp[m - 1];
        }


    }

    static class _3th_1 {
        public static void main(String[] args) {
            // 将 0b 后面的二进制值修改为你想测试的二进制数
            int i = 0b10111;
            String pattern = "%25s%25s";
            System.out.println(String.format(pattern, "j", "i ^ j"));
            // 请按你所想，修改循环条件
            for (int j = i; j > 0; j = (j - 1) & i) {
                // 打印格式 数字(二进制)，j 为子集，i ^ j 为补集
                String A = j + "(" + Integer.toBinaryString(j) + ")";
                String B = (i ^ j) + "(" + Integer.toBinaryString(i ^ j) + ")";
                System.out.println(String.format(pattern, A, B));
            }

        }

        public int minSessions(int[] tasks, int sessionTime) {
            int m = tasks.length, n = 1 << m;
            int INF = 20;
            int[] dp = new int[n];
            Arrays.fill(dp, INF);
            for (int i = 1; i < n; i++) {
                int state = i, idx = 0, spend = 0;
                while (state > 0) {
                    int bit = state & 1;
                    if (bit == 1) spend += tasks[idx];
                    state >>= 1;
                    idx++;
                }
                if (spend <= sessionTime) dp[i] = 1;
            }


            return 0;
        }
    }

    static class _3th_2 {
        public static void main(String[] args) {
            _3th_2 handler = new _3th_2();
            int[] tasks = new int[]{1, 2, 3};
            int sessionTime = 3;
            handler.minSessions(tasks, sessionTime);
        }


        public int minSessions(int[] tasks, int sessionTime) {
            int n = tasks.length;
            boolean[] valid = new boolean[1 << n];
            for (int mask = 1; mask < (1 << n); mask++) {
                int needTime = 0;
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) {
                        needTime += tasks[i];
                    }
                }
                if (needTime <= sessionTime) valid[mask] = true;
            }
            int[] f = new int[1 << n];
            Arrays.fill(f, Integer.MAX_VALUE / 2);
            f[0] = 0;
            for (int mask = 1; mask < (1 << n); mask++) {
                for (int subset = mask; subset > 0; subset = (subset - 1) & mask) {
                    if (valid[subset]) {
                        f[mask] = Math.min(f[mask], f[subset ^ mask] + 1);
                    }
                }
            }
            return f[(1 << n) - 1];
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();

//            int i = 19;
            int i = 21;
            for (int j = i; j > 0; j = (j - 1) & i) {
                PrintUtils.toBinaryString(j, 5);
                System.out.println(j);
            }

        }
    }
}
