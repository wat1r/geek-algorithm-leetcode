package com.frankcooper.bank._401_500;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class _473 {


    static class _1st {
        static _1st handler = new _1st();

        public static void main(String[] args) {


//        int[] a = {9, 8, 7, 2, 3, 4, 1, 0, 6, 5};
//        Arrays.sort(a,Collections.reverseOrder());

            int[] nums = {3, 3, 3, 3, 4};
//        nums = new int[]{2, 2, 2, 2, 2, 6};
            System.out.println(handler.makesquare(nums));


        }

        //目标正方形的边长
        int maxEdgeLen = 0;


        public boolean makesquare(int[] nums) {
            if (nums == null || nums.length == 0) return false;
            int n = nums.length;
            boolean[] visited = new boolean[n];
            int sum = 0;
            for (int num : nums) sum += num;
            if (sum % 4 != 0) return false;
            this.maxEdgeLen = sum / 4;
            return dfs(nums, 0, visited, 0, 0);
        }


        /**
         * @param nums        源数组
         * @param edgeIdx     当前边的索，一共4条边，0,1,2,3
         * @param visited     数组元素是否被访问
         * @param start       nums数组的位置索引
         * @param currEdgeLen 当前这条边已经累计的边长，不能超过maxEdgeLen
         * @return
         */
        private boolean dfs(int[] nums, int edgeIdx, boolean[] visited, int start, int currEdgeLen) {
            if (edgeIdx == 4) return true;
            //开始一条新的边，start 和 currEdgeLen 需要重置为0
            if (currEdgeLen == maxEdgeLen) {
                return dfs(nums, edgeIdx + 1, visited, 0, 0);
            }
            System.out.printf("edgeIdx:%d,start:%d,currEdgeLen:%d\n", edgeIdx, start, currEdgeLen);
            for (int i = start; i < nums.length; i++) {
                if (!visited[i] && currEdgeLen + nums[i] <= maxEdgeLen) {
                    visited[i] = true;
                    if (dfs(nums, edgeIdx, visited, i + 1, currEdgeLen + nums[i])) {
                        return true;
                    }
                    visited[i] = false;
                }
            }
            return false;
        }

    }


    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] matchsticks = new int[]{1, 1, 2, 2, 2};
            Assert.assertTrue(handler.makesquare(matchsticks));
        }


        boolean[] vis;
        int[] matchsticks;
        int maxLen;
        int n;

        public boolean makesquare(int[] matchsticks) {
            n = matchsticks.length;
            int sum = 0;
            for (int x : matchsticks) sum += x;
            if (sum % 4 != 0) return false;
            maxLen = sum / 4;//正方形边长
            vis = new boolean[n];//每个火柴的访问情况
            this.matchsticks = matchsticks;
            return dfs(0, 0, 0);
        }

        //squareIndex:当前处理到的边的编号，从0~3 index:当前处理的matchsticks的哪根火柴，curLen 当前这条边所积累的边长
        public boolean dfs(int squareIndex, int index, int curLen) {
            if (squareIndex == 4) return true;
            if (curLen == maxLen) {
                return dfs(squareIndex + 1, 0, 0);
            }
            //遍历每一根火柴
            for (int i = index; i < n; i++) {
                //当前的火车没有被使用过且当前的边长+即将被使用的火柴的长度，不会越界，这根火柴可以被使用
                if (!vis[i] && curLen + matchsticks[i] <= maxLen) {
                    vis[i] = true;//标记
                    //如果当前的火柴可以使用，那就进入到下一根火车
                    if (dfs(squareIndex, i + 1, curLen + matchsticks[i])) {
                        return true;
                    }
                    vis[i] = false;//回溯
                }
            }
            return false;
        }


        static class _3rd {
            int n;
            int[] matchsticks;
            int maxLen;

            public boolean makesquare(int[] matchsticks) {
                n = matchsticks.length;
                int sum = 0;
                for (int x : matchsticks) sum += x;
                if (sum % 4 != 0) return false;
                this.matchsticks = matchsticks;
                maxLen = sum / 4;//正方形边长
                int[] sides = new int[4];//4条边的长度
                //逆序排序，不排序会超时
                this.matchsticks = IntStream.of(matchsticks)          // 变为 IntStream
                        .boxed()           // 装盒变为 Stream<Integer>
                        .sorted(Comparator.reverseOrder()) // 按自然序相反排序
                        .mapToInt(Integer::intValue)       // 变为 IntStream
                        .toArray();        // 又变回 int[]
                return dfs(0, sides);
            }

            public boolean dfs(int index, int[] sides) {
                if (index == matchsticks.length) {
                    return true;
                }
                for (int i = 0; i < sides.length; i++) {
                    sides[i] += matchsticks[index];
                    if (sides[i] <= maxLen && dfs(index + 1, sides)) {
                        return true;
                    }
                    sides[i] -= matchsticks[index];
                }
                return false;
            }
        }
    }

    static class _4th {
        int n;
        int[] matchsticks;
        int maxLen;

        public boolean makesquare(int[] matchsticks) {
            n = matchsticks.length;
            int sum = 0;
            for (int x : matchsticks) sum += x;
            if (sum % 4 != 0) return false;
            this.matchsticks = matchsticks;
            maxLen = sum / 4;//正方形边长
            int[] sides = new int[4];//4条边的长度
            //逆序排序，不排序会超时
            this.matchsticks = IntStream.of(matchsticks)          // 变为 IntStream
                    .boxed()           // 装盒变为 Stream<Integer>
                    .sorted(Comparator.reverseOrder()) // 按自然序相反排序
                    .mapToInt(Integer::intValue)       // 变为 IntStream
                    .toArray();        // 又变回 int[]
            return dfs(0, sides);
        }

        private boolean dfs(int index, int[] sides) {
            if (index >= matchsticks.length) {
                if (sides[0] == maxLen && sides[1] == maxLen && sides[2] == maxLen) {
                    return true;
                }
            }
            for (int i = 0; i < sides.length; i++) {
                if (sides[i] + matchsticks[index] > maxLen) {
                    continue;
                }
                sides[i] += matchsticks[index];
                if (dfs(index + 1, sides)) {
                    return true;
                }
                sides[i] -= matchsticks[index];
            }
            return false;
        }
    }

    static class _5th {

        public static void main(String[] args) {
            _5th handler = new _5th();
            int[] matchsticks = new int[]{1, 1, 2, 2, 2};
            Assert.assertTrue(handler.makesquare(matchsticks));
        }


        public boolean makesquare(int[] matchsticks) {
            int totalLen = Arrays.stream(matchsticks).sum();
            if (totalLen % 4 != 0) {
                return false;
            }
            int len = totalLen / 4, n = matchsticks.length;
            int[] dp = new int[1 << n];
            Arrays.fill(dp, -1);
            dp[0] = 0;
            for (int s = 1; s < (1 << n); s++) {
                for (int k = 0; k < n; k++) {
                    if ((s & (1 << k)) == 0) {
                        continue;
                    }
                    int s1 = s & ~(1 << k);
                    if (dp[s1] >= 0 && dp[s1] + matchsticks[k] <= len) {
                        dp[s] = (dp[s1] + matchsticks[k]) % len;
                        break;
                    }
                }
            }
            return dp[(1 << n) - 1] == 0;
        }


    }

    static class _6th {
        public boolean makesquare(int[] matchsticks) {
            if (matchsticks.length < 4) {
                return false;
            }
            int sum = 0, max = 0;
            for (int i = 0; i < matchsticks.length; i++) {
                sum += matchsticks[i];
                max = Math.max(max, matchsticks[i]);
            }
            if (sum % 4 != 0 || max > sum / 4) {
                return false;
            }
            sum >>= 2;
            max = 1 << matchsticks.length;
            int[] ans = new int[max];
            Arrays.fill(ans, -1);
            ans[0] = 0;
            for (int i = 1; i < max; i++) {
                for (int j = 0; j < matchsticks.length; j++) {
                    int pre = i ^ (1 << j);
                    if (ans[pre] >= 0 && pre < i && ans[pre] + matchsticks[j] <= sum) {
                        ans[i] = ans[pre] + matchsticks[j];
                        if (sum == ans[i]) {
                            ans[i] = 0;
                        }
                        break;//剪枝
                    }
                }
            }
            return ans[max - 1] == 0;
        }
    }

}
