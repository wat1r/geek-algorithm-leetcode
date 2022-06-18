package com.frankcooper.platform.leetcode.bank._401_500;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _464 {


    static _464 handler = new _464();


    static class _1st {
        public static void main(String[] args) {

        }

        //dp[state] 表示状态state下，第一个人先选的情况下是否能赢的情况 true 能赢，false不能

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
            return dfs(maxChoosableInteger, desiredTotal, new Boolean[1 << maxChoosableInteger], 0);
        }

        /**
         * @param maxChoosableInteger
         * @param desiredTotal
         * @param dp
         * @param state
         * @return
         */
        private boolean dfs(int maxChoosableInteger, int desiredTotal, Boolean[] dp, int state) {
            if (dp[state] != null) return dp[state];
            for (int i = 1; i <= maxChoosableInteger; i++) {
                int cur = 1 << (i - 1); //拿到当前位
                if ((state & cur) == 0) { //如果当前位不在state集合中，没有被使用过
                    if (desiredTotal - i <= 0 || !dfs(maxChoosableInteger, desiredTotal - i, dp, state | cur)) {
                        return dp[state] = true;
                    }
                }
            }
            return dp[state] = false;
        }
    }

    //TLE 56/57
    //case:
    //20
    //152
    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int maxChoosableInteger = 10, desiredTotal = 11;
            Assert.assertFalse(handler.canIWin(maxChoosableInteger, desiredTotal));
        }

        int[] state;
        Map<String, Boolean> memo = new HashMap<>();

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            if (desiredTotal <= maxChoosableInteger) return true;
            if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
            state = new int[maxChoosableInteger + 1];
            return dfs(desiredTotal);
        }

        private boolean dfs(int desiredTotal) {
            String key = Arrays.toString(state);
            if (memo.containsKey(key)) return memo.get(key);
//            System.out.printf("key:%s ", key);
            for (int i = 1; i < state.length; i++) {
                if (state[i] == 0) {
                    state[i] = 1;
//                    System.out.printf(" %d\n", desiredTotal);
                    if (desiredTotal - i <= 0 || !dfs(desiredTotal - i)) {
                        state[i] = 0;
                        memo.put(key, true);
                        return true;
                    }
                    state[i] = 0;
                }
            }
            memo.put(key, false);
            return false;
        }
    }

    static class _3rd {
        //
        Boolean[] dp;
        int maxChoosableInteger;

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            //最大的可以选的数(maxChoosableInteger)大于等于最终目标数(desiredTotal),A先抽，直接拿desiredTotal，胜出
            if (desiredTotal <= maxChoosableInteger) return true;
            //[1...maxChoosableInteger]之间的总数的和小于最终目标数(desiredTotal),A和By将数选完，也不能凑成(desiredTotal),A赢不了，返回false
            if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
            this.maxChoosableInteger = maxChoosableInteger;
            dp = new Boolean[1 << maxChoosableInteger];
            return dfs(desiredTotal, 0);
        }

        /**
         * @param desiredTotal 当前离达成目标的原始desiredTotal还差多少
         * @param state        当前从1到maxChoosableInteger之间的每个数被选中的状态，在二进制下，1为选中，0为未选中
         * @return
         */
        private boolean dfs(int desiredTotal, int state) {
            //如果该state被标记过，返回
            if (dp[state] != null) return dp[state];
            //从 1 到 maxChoosableInteger 判断每一位是否被选中过
            for (int i = 1; i <= maxChoosableInteger; i++) {
                //当前位 需要判断下当前位是否是1（二进制下）
                int cur = 1 << (i - 1);
                if ((cur & state) == 0) {//当前位不是1是0，表示当前位可以选
                    //case1: 选中当前的数i后，可以达成最终的目标
                    //cas2:  选中当前的数i后，暂时不能达成目标，但是对手最终不能达成目标，意味着当前玩家最终可以达成目标
                    //cur | state 表示将当前数i选中 送到下一轮的状态中
                    if (desiredTotal - i <= 0 || !dfs(desiredTotal - i, cur | state)) {
                        return dp[state] = true;
                    }
                }
            }
            return dp[state] = false;
        }
    }

    static class _3rd_1 {
        public static void main(String[] args) {

        }

        //TLE
        //41/57
        //case:
        //20
        //210
        boolean[] vis;
        int maxChoosableInteger, desiredTotal;

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            this.maxChoosableInteger = maxChoosableInteger;
            this.desiredTotal = desiredTotal;
            for (int i = 1; i <= maxChoosableInteger; i++) {
                vis = new boolean[21];
                vis[i] = true;
                if (dfs(i)) return true;
            }
            return false;
        }


        /**
         * @param curSum 当前已经选的数的总和
         * @return
         */
        private boolean dfs(int curSum) {
            //已经达到目标数desiredTotal，返回true
            if (curSum >= desiredTotal) return true;
            //遍历从1 到 maxChoosableInteger的每一个数
            for (int i = 1; i <= maxChoosableInteger; i++) {
                if (vis[i]) continue;//被访问过，不需要继续
                vis[i] = true;//标记
                //如果curSum+i 即对手能赢得游戏，意味着当前玩家失败
                if (dfs(curSum + i)) {
                    return vis[i] = false;
                }
                vis[i] = false;//恢复
            }
            return true;
        }

    }

    static class _4th {
        Map<Integer, Boolean> memo = new HashMap();
        boolean[] vis;

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            //最大的可以选的数(maxChoosableInteger)大于等于最终目标数(desiredTotal),A先抽，直接拿desiredTotal，胜出
            if (desiredTotal <= maxChoosableInteger) return true;
            //[1...maxChoosableInteger]之间的总数的和小于最终目标数(desiredTotal),A和By将数选完，也不能凑成(desiredTotal),A赢不了，返回false
            if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
            vis = new boolean[maxChoosableInteger + 1];
            return helper(desiredTotal);
        }

        public boolean helper(int desiredTotal) {
            if (desiredTotal <= 0) return false;
            int key = format(vis);
            if (!memo.containsKey(key)) {
                //遍历vis中的数，尝试没有被选中的
                for (int i = 1; i < vis.length; i++) {
                    if (!vis[i]) {//i没有被选择
                        vis[i] = true;//标记
                        // 当前i被选中，留给对手的desiredTotal - i的空间，但是对手没法胜出
                        if (!helper(desiredTotal - i)) {
                            memo.put(key, true);
                            vis[i] = false;//恢复
                            return true;
                        }
                        vis[i] = false;//恢复
                    }
                }
                memo.put(key, false);
            }
            return memo.get(key);
        }

        // 遍历vis数组，每一位true的设置为1，相当于压缩
        public int format(boolean[] vis) {
            int res = 0;
            for (boolean b : vis) {
                res <<= 1;
                if (b) res |= 1;
            }
            return res;
        }
    }


    static class _5th_1 {

        //TLE
        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            if (desiredTotal <= 0) return true;
            if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) return false;
            return canIWin(desiredTotal, new int[maxChoosableInteger], new HashMap<>());
        }

        private boolean canIWin(int total, int[] state, HashMap<String, Boolean> hashMap) {
            String curr = Arrays.toString(state);
            if (hashMap.containsKey(curr)) return hashMap.get(curr);
            for (int i = 0; i < state.length; i++) {
                if (state[i] == 0) {
                    state[i] = 1;
                    if (total <= i + 1 || !canIWin(total - (i + 1), state, hashMap)) {
                        hashMap.put(curr, true);
                        state[i] = 0;
                        return true;
                    }
                    state[i] = 0;
                }
            }
            hashMap.put(curr, false);
            return false;
        }
    }


}
