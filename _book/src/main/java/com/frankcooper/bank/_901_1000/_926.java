package com.frankcooper.platform.leetcode.bank._901_1000;

import java.util.*;

import org.junit.Assert;

public class _926 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "00110";
//            handler.minFlipsMonoIncr(s);
            Assert.assertEquals(1, handler.minFlipsMonoIncr("11011"));

        }


        public int minFlipsMonoIncr(String s) {
            int n = s.length();
            //zero 统计0的前缀和 one 统计1的前缀和
            int[] zero = new int[n + 1], one = new int[n + 1];
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '0') {
                    zero[i + 1] = zero[i] + 1;
                    one[i + 1] = one[i];
                } else {
                    one[i + 1] = one[i] + 1;
                    zero[i + 1] = zero[i];
                }
            }
            int res = s.length();
            for (int i = 1; i <= n; i++) {
                //前部分1的个数  后部分0的个数
                int pre_one = one[i], suc_zero = zero[n] - zero[i];
                //将前部分的1都变成0 后部分的0都变成1 形如00000111这样的结果
                res = Math.min(res, pre_one + suc_zero);
                //前部分0的个数
                int pre_zero = zero[i];
                //将前部分的0都变成1 后部分的0都变成1 形如111111111这样的结果
                res = Math.min(res, pre_zero + suc_zero);
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            handler.minFlipsMonoIncr("001101");
            Assert.assertEquals(1, handler.minFlipsMonoIncr("11011"));
        }

        public int minFlipsMonoIncr(String S) {
            int N = S.length();
            int[] P = new int[N + 1];
            for (int i = 0; i < N; ++i)
                P[i + 1] = P[i] + (S.charAt(i) == '1' ? 1 : 0);

            int ans = Integer.MAX_VALUE;
            for (int j = 0; j <= N; ++j) {
                System.out.printf("%d %d\n", P[j], N - j - (P[N] - P[j]));
                ans = Math.min(ans, P[j] + N - j - (P[N] - P[j]));
            }

            return ans;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
//            Assert.assertEquals(1, handler.minFlipsMonoIncr("00110"));
            Assert.assertEquals(2, handler.minFlipsMonoIncr("010110"));
        }


        public int minFlipsMonoIncr(String s) {
            int n = s.length();
            //dp[i][0]表示前i个元素，最后一个元素为0的最小翻转次数；
            //dp[i][1]表示前i个元素，最后一个元素为1的最小翻转次数
            int[][] dp = new int[n][2];
            //初始化
            char c0 = s.charAt(0);
            //第一个字符本身是'0'的话，不需要翻转，如果是'1'需要执行一次翻转
            dp[0][0] = c0 == '0' ? 0 : 1;
            //第一个字符是'1'的话，不需要翻转，如果是'0'的话，需要执行一次翻转
            //鉴于下面的转移会拿dp[i-1][0] 和 dp[i-1][1]的最小值，这个最小值肯定为0，对于[0]这个元素来说
            //下面的这段初始化可以省略，设置为默认值0
//            dp[0][1] = c0 == '1' ? 0 : 1;
            for (int i = 1; i < n; i++) {
                char c = s.charAt(i);
                //注意优先级
                //前[i-1]个元素必须要都是0，c如果是'1'要执行一次翻转
                dp[i][0] = dp[i - 1][0] + (c == '0' ? 0 : 1);
                //前[i-1]可以都是0 可以以'1'结束，c如果是'0'，要进行一次翻转
                dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]) + (c == '1' ? 0 : 1);
            }
            //总的区间，取最终变成 000000这种格式还是000011这种格式
            return Math.min(dp[n - 1][0], dp[n - 1][1]);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        //[i]的状态只和[i-1]即前一个状态有关，可以进行空间优化
        public int minFlipsMonoIncr(String s) {
            int n = s.length();
            //当前下标i下字符为0和1的情况下，使得s[0...i]单调递增的最小翻转次数
            int f0 = 0, f1 = 0;
            for (int i = 0; i < n; i++) {
                //下一轮的 f0 f1
                int _f0 = f0, _f1 = Math.min(f0, f1);
                if (s.charAt(i) == '1') {//当前下标i的字符是'1'
                    _f0++;
                } else {
                    _f1++;
                }
                f0 = _f0;
                f1 = _f1;
            }
            return Math.min(f0, f1);
        }
    }

    static class _5th {
        public static void main(String[] args) {

        }

        public int minFlipsMonoIncr(String s) {
            int n = s.length();
            //维护一个单调递增的tails数组 形如 00011
            char[] tails = new char[n];
            int index = 0;//tails数组当前的处理到的位置
            for (char x : s.toCharArray()) {
                int i = 0, j = index;//
                while (i < j) {
                    int mid = i + (j - i) / 2;
                    //如果当前的x比tails[mid]大，说明应该放在mid的右侧位置，否则放在左侧位置
                    if (tails[mid] <= x) i = mid + 1;
                    else j = mid;
                }
                tails[i] = x;//x放置的位置
                //如果放在末尾，index要偏移一个值
                if (j == index) index++;
            }
            //index表示 单调递增的长度(恰好在上面的操作中+1了)，剩下的n -index就是要翻转的字符，即翻转的操作数
            return n - index;
        }
    }
}
