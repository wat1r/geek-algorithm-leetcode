package com.frankcooper.other;

import com.alibaba.fastjson.JSONObject;
import com.frankcooper.utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameSolution {


    public static void main(String[] args) {


    }


    /**
     * 符合条件的一次击杀的方案有402120种，
     * 总的方案数为20^5
     * 概率为 402120 / (20^5 ) = 12.256625%
     */
    static class _1st {

        static _1st handler = new _1st();


        public static void main(String[] args) {
            int choices = handler.chance();
            System.out.println(choices / (20 ^ 5));
        }

        List<Integer> prime = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
                101, 103, 107, 109, 113);

        int N = 20;

        public int chance() {
            int[] nums = new int[20];
            for (int i = 1; i <= 20; i++) {
                nums[i - 1] = i;
            }
            List<List<Integer>> choices = permute(nums);
            System.out.println(choices.size());
            return choices.size();
        }

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> resList = new ArrayList<>();
            dfs(nums, resList, new ArrayList<>(), 0);
            return resList;
        }

        private void dfs(int[] nums, List<List<Integer>> resList, ArrayList<Integer> levelList, int sum) {
            if (levelList.size() == 5) {
                if (prime.contains(sum) && sum > 50) {
                    resList.add(new ArrayList<>(levelList));
                }
                return;
            }
            for (int num : nums) {
                levelList.add(num);
                dfs(nums, resList, levelList, sum + num);
                levelList.remove(levelList.size() - 1);
            }
        }


    }


    /**
     * 有两种顺序可以得到最大值 2550
     * [5,7,3,10,6,4,12]--->2550
     * [12,4,6,10,3,7,5]--->2550
     */
    static class _2nd {


        static _2nd handler = new _2nd();

        public static void main(String[] args) {
//            int[] follows = {3, 1, 5, 8};
            int[] arr = {3, 4, 5, 6, 7, 10, 12};
            int totalMax = 0;
            List<List<Integer>> resList = handler.permute(arr);
            for (List<Integer> res : resList) {
                int[] follows = new int[7];
                for (int i = 0; i < res.size(); i++) {
                    follows[i] = res.get(i);
                }
                int max = handler.maxHurt(follows);
                totalMax = Math.max(totalMax, max);
                System.out.println(JSONObject.toJSONString(follows) + "--->" + max);
            }

            System.out.println(totalMax);
        }


        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> resList = new ArrayList<>();
            dfs(nums, resList, new ArrayList<>());
            return resList;
        }

        private void dfs(int[] nums, List<List<Integer>> resList, ArrayList<Integer> levelList) {
            if (levelList.size() == nums.length) {
                resList.add(new ArrayList<>(levelList));
                return;
            }
            for (int num : nums) {
                if (levelList.contains(num)) continue;
                levelList.add(num);
                dfs(nums, resList, levelList);
                levelList.remove(levelList.size() - 1);
            }
        }


        public int maxHurt(int[] follows) {
            int n = follows.length;
            int[] val = new int[n + 2];
            val[0] = val[n + 1] = 1;
            for (int i = 1; i <= n; i++) val[i] = follows[i - 1];
            int[][] f = new int[n + 2][n + 2];
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 2; j <= n + 1; j++) {
                    for (int k = i + 1; k < j; k++) {
                        int sum = val[i] * val[k] * val[j];
                        sum += f[i][k] + f[k][j];
                        f[i][j] = Math.max(f[i][j], sum);
                    }
                }
            }
//            PrintUtils.printMatrix(f);
            return f[0][n + 1];
        }
    }


    /**
     * [0,1,2,3,5,8,9,11]
     * 0可以条1步都到1
     * 1可以跳[0,1,2]步，跳2步到3
     * 3可以跳[2,3,4]步，跳2步到5
     * 5可以跳[4,5,6]步。跳6步到11
     * 11可以跳[10,11,12]步，落水
     * 四个数为[1,3,5,11]
     * 可以拼成24点 --> (1+3)*(11-5) = 24
     * [2, 3, 5, 11] 也是一种，2*11+5-3 = 24
     */
    static class _3rd {

        static _3rd handler = new _3rd();

        public static void main(String[] args) {
            System.out.println(handler.judgePoint24(new int[]{1, 3, 5, 11}));
            System.out.println(handler.judgePoint24(new int[]{2, 3, 5, 11}));
        }


        public boolean judgePoint24(int[] nums) {
            return backTrack(nums, 0);
        }


        // 第一步：求出所有排列，一一验证
        public boolean backTrack(int[] nums, int index) {
            if (index == 4) {
                return judge(nums[0], nums[1], nums[2], nums[3]);
            }
            for (int i = index; i < 4; i++) {
                swap(nums, index, i);
                if (backTrack(nums, index + 1)) return true;
                swap(nums, index, i);
            }
            return false;
        }

        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        // 第二步：由于已经全排列，a、b、c、d 都是等价的，利用四种运算符选出三个数继续
        public boolean judge(double a, double b, double c, double d) {
            return judge(a + b, c, d) ||
                    judge(a - b, c, d) ||
                    judge(a * b, c, d) ||
                    judge(a / b, c, d);
        }

        // 第三步：a 是由两个数组成的，b、c 只表示一个数；a 与 b、c 不等价，b、c 等价
        public boolean judge(double a, double b, double c) {
            // 情况一：a 和 b(c) 组合，a 和 b(c) 不等价，- 号和 / 号需要考虑两种情况
            return judge(a + b, c) ||
                    judge(a - b, c) ||
                    judge(a * b, c) ||
                    judge(a / b, c) ||
                    judge(b - a, c) ||
                    judge(b / a, c) ||
                    // 情况二：b 和 c 组合
                    judge(a, b + c) ||
                    judge(a, b - c) ||
                    judge(a, b * c) ||
                    judge(a, b / c);
        }

        // 第四步：a 和 b 不等价
        public boolean judge(double a, double b) {
            return Math.abs(a + b - 24) < 0.001 ||
                    Math.abs(a - b - 24) < 0.001 ||
                    Math.abs(a * b - 24) < 0.001 ||
                    Math.abs(a / b - 24) < 0.001 ||
                    Math.abs(b - a - 24) < 0.001 ||
                    Math.abs(b / a - 24) < 0.001;
        }

    }


    /**
     * 最大的食物为242，
     * 最小花费是51,
     * 剩余 242-51 = 191
     */
    static class _4th {

        static _4th handler = new _4th();

        int m, n;

        public static void main(String[] args) {
            int[] nums = {29, 34, 33, 26, 31, 29, 29, 34, 23, 34, 23, 34, 27, 21, 25, 30};
            int maxFood = handler.getMaxFood(nums);
            int[][] grid = {{0, 4, 6, 7, 8, 2},
                    {5, 5, 6, 8, 7, 7},
                    {10, 12, 5, 1, 17, 9},
                    {1, 13, 3, 14, 15, 10},
                    {1, 15, 3, 4, 16, 14},
                    {5, 19, 13, 5, 16, 0}};
            System.out.println(maxFood);
            int minCost = handler.minCost(grid);
            System.out.println(minCost);
        }

        public int minCost(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int[][] dp = new int[m][n];
            for (int j = 1; j < n; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            }
            for (int i = 1; i < m; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] += grid[i][j];
                }
            }
            PrintUtils.printMatrix(dp);
            return dp[m - 1][n - 1];
        }






        public int getMaxFood(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
            return Math.max(helper(nums, 0, n - 2), helper(nums, 1, n - 1));
        }

        public int helper(int[] nums, int start, int end) {
            int n = nums.length;
            int dp_i_1 = 0, dp_i_2 = 0;
            int dp_i = 0;
            for (int i = end; i >= start; i--) {
                dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
                dp_i_2 = dp_i_1;
                dp_i_1 = dp_i;
            }
            return dp_i;
        }


    }


    /**
     * 150的食物可以都用完的话，也就是剩下0，答案是1200种
     * 150的食物必须至少保留1的话，答案是1170种
     */

    static class _5th {
        static _5th handler = new _5th();

        public static void main(String[] args) {
            int[] nums = {13, 16, 21, 22, 32, 44, 45, 49, 56, 66, 72, 83, 88, 91, 100, 113};
            List<List<Integer>> resList = handler.permute(nums);
            for (List<Integer> res : resList) {
                int sum = 0;
                for (int i : res) sum += i;
//                System.out.println(JSONObject.toJSONString(res) + "--->" + sum);
                if (sum > 150) {
                    System.out.println("ops");
                }
            }
            System.out.println(resList.size());
        }


        int targetSum = 3;
        int totalFood = 150;

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> resList = new ArrayList<>();
            dfs(nums, resList, new ArrayList<>(), 0);
            return resList;
        }

        private void dfs(int[] nums, List<List<Integer>> resList, ArrayList<Integer> levelList, int sum) {
            if (levelList.size() == targetSum) {
                if (sum < totalFood) {
                    resList.add(new ArrayList<>(levelList));
                }
                return;
            }
            for (int num : nums) {
                if (levelList.contains(num)) continue;
                levelList.add(num);
                dfs(nums, resList, levelList, sum + num);
                levelList.remove(levelList.size() - 1);
            }
        }


    }

}
