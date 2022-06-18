package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.*;

public class _403 {


    /**
     * 记忆化递归
     */
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        //k ： 当前的点 ， v ： 当前点可以跳跃的步数
        HashMap<Integer, Set<Integer>> vis = new HashMap<>();//prev这个点 以及这个点跳跃的步数 组成的状态
        HashSet<Integer> pos = new HashSet<>();//当前点的集合
        int end;

        public boolean canCross(int[] stones) {
            this.end = stones[stones.length - 1];
            for (int x : stones) {
                pos.add(x);
                vis.put(x, new HashSet<>());
            }
            return dfs(0, 1);//前一个位置从0开始，跳跃1步
        }

        /**
         * @param prev 前一个所在的位置
         * @param step 在前一个所在的位置上，下一步的步数
         * @return
         */
        public boolean dfs(int prev, int step) {
            int cur = prev + step;//当前要跳的点
            if (step < 0 || !pos.contains(cur)) return false; //步数或者当前点不在集合
            if (vis.get(prev).contains(step)) return false;//这种组合状态被搜索过
            vis.get(prev).add(step);
            if (cur == end) return true;//跳跃到终点位置
            //三种选择 cur作为下一轮的prev
            return dfs(cur, step - 1) || dfs(cur, step) || dfs(cur, step + 1);
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        int[] ds = {-1, 0, 1};//要跳跃的距离

        public boolean canCross(int[] stones) {
            int n = stones.length;
            //f[i][j] 从第i个石头能否执行跳跃到j步的操作 能执行:true 不能执行:false
            boolean[][] f = new boolean[n][n + 1];//因为有k+1跳法 步数需要+1
            f[0][1] = true;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    int diff = stones[i] - stones[j];
                    if (diff < 0 || diff > n || !f[j][diff]) continue;
                    f[i][diff] = true;
                    if (diff - 1 >= 0) f[i][diff - 1] = true;
                    if (diff + 1 <= n) f[i][diff + 1] = true;
                    if (i == n - 1) return true;//提前返回
                }
            }
            return false;
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
