package com.frankcooper.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week257 {


    //    1995. 统计特殊四元组
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        //暴力枚举
        public int countQuadruplets(int[] nums) {
            if (nums.length < 4) return 0;
            int res = 0;
            for (int a = 0; a < nums.length - 3; a++) {
                for (int b = a + 1; b < nums.length - 2; b++) {
                    for (int c = b + 1; c < nums.length - 1; c++) {
                        for (int d = c; d < nums.length; d++) {
                            int sum = nums[a] + nums[b] + nums[c];
                            if (sum == nums[d]) {
                                res++;
                            }
                        }
                    }
                }
            }
            return res;
        }
    }

    static class _1st_1 {


        public static void main(String[] args) {
            _1st_1 handler = new _1st_1();
            int[] nums = {1, 1, 1, 3, 5};
            nums = new int[]{1, 1, 1, 3, 5, 7, 8};
            handler.countQuadruplets(nums);
        }

        public int countQuadruplets(int[] nums) {
            int n = nums.length, ans = 0;
            int[] cnt = new int[10010];
            for (int c = n - 2; c >= 2; c--) {
                cnt[nums[c + 1]]++;
                for (int a = 0; a < n; a++) {
                    for (int b = a + 1; b < c; b++) {
                        ans += cnt[nums[a] + nums[b] + nums[c]];
                    }
                }
            }
            return ans;
        }
    }


    static class _1st_2 {
        public int countQuadruplets(int[] nums) {
            int n = nums.length;
            int res = 0;
            int[] cnt = new int[5050];
            for (int b = n - 3; b >= 1; b--) {
                for (int d = b + 2; d < n; d++) {
                    int c = b + 1;
                    cnt[nums[d] - nums[c] + 100]++;
                }
                for (int a = 0; a < b; a++) {
                    res += cnt[nums[a] + nums[b] + 100];
                }
            }
            return res;
        }
    }

    static class _1st_3 {
        public static void main(String[] args) {

        }


        public int countQuadruplets(int[] nums) {
            int n = nums.length;
//            定义 f[i][j][k] 为考虑前 i 个物品（下标从 1 开始），凑成数值恰好 j，使用个数恰好为 k 的方案数
            int[][][] f = new int[n + 1][110][4];
            f[0][0][0] = 1;
            for (int i = 1; i <= n; i++) {
                int t = nums[i - 1];
                for (int j = 0; j < 110; j++) {
                    for (int k = 0; k < 4; k++) {
                        f[i][j][k] += f[i - 1][j][k];
                        if (j - t >= 0 && k - 1 >= 0) {
                            f[i][j][k] += f[i - 1][j - t][k - 1];
                        }
                    }
                }
            }
            int res = 0;
            for (int i = 3; i < n; i++) {
                res += f[i][nums[i]][3];
            }
            return res;
        }


        static class _1st_4 {

            public static void main(String[] args) {
                _1st_4 handler = new _1st_4();
                int[] nums = new int[]{3, 3, 6, 4, 5};
                handler.countQuadruplets(nums);
            }


            public int countQuadruplets(int[] nums) {
                int n = nums.length;
                int res = 0;
                int[] cnt = new int[5050];
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        res += cnt[nums[j] - nums[i] + 100];
                    }
                    for (int k = 0; k < i; k++) {
                        cnt[nums[k] + nums[i] + 100]++;
                    }
                }
                return res;
            }

        }

    }

    //    1996. 游戏中弱角色的数量
    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            //case
            // [[1,5],[10,7],[4,3],[2,6]]
            // [[5,5],[6,3],[3,6]]
            //[[2,2],[3,3]]
            //[[1,1],[2,1],[2,2],[1,2],[2,3]]
            //[[7,9],[10,7],[6,9],[10,4],[7,5],[7,10]]

            //expected:
            //3
            //0
            //1
            //2
            //2


            int[][] ps = {{1, 5}, {10, 7}, {4, 3}, {2, 6}};
//            Assert.assertEquals(3, handler.numberOfWeakCharacters(ps));
//            ps = new int[][]{{1, 1}, {2, 1}, {2, 2}, {1, 2}, {2, 3}};
//            ps = new int[][]{{1, 1}, {2, 1}, {2, 2}, {1, 2}};
//            Assert.assertEquals(2, handler.numberOfWeakCharacters(ps));
            ps = new int[][]{{7, 9}, {10, 7}, {6, 9}, {10, 4}, {7, 5}, {7, 10}};
//            Assert.assertEquals(2, handler.numberOfWeakCharacters(ps));
            ps = new int[][]{{7, 7}, {1, 2}, {9, 7}, {7, 3}, {3, 10}, {9, 8}, {8, 10}, {4, 3}, {1, 5}, {1, 5}};
            Assert.assertEquals(6, handler.numberOfWeakCharacters(ps));

        }

        public int numberOfWeakCharacters(int[][] ps) {
            int res = 0;
            Arrays.sort(ps, ((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0]));
            Deque<int[]> stk = new ArrayDeque<>();
            for (int i = 0; i < ps.length; i++) {
                while (i < ps.length && !stk.isEmpty() && stk.peek()[0] >= ps[i][0] && stk.peek()[1] >= ps[i][1]) {
                    for (int[] c : stk) {
                        if (c[0] > ps[i][0] && c[1] > ps[i][1]) {
                            res++;
                            //break 参考这个case
                            //{{7, 7}, {1, 2}, {9, 7}, {7, 3}, {3, 10}, {9, 8}, {8, 10}, {4, 3}, {1, 5}, {1, 5}};
                            break;
                        }
                    }
                    i++;
                }
                if (i < ps.length) stk.push(ps[i]);
            }
            return res;
        }
    }


    //1997. 访问完所有房间的第一天
    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nextVisit = {0, 0, 2};
//            Assert.assertEquals(6, handler.firstDayBeenInAllRooms(nextVisit));
            nextVisit = new int[]{0, 1, 2, 0};
//            Assert.assertEquals(6, handler.firstDayBeenInAllRooms(nextVisit));
            //[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
            //TLE
            nextVisit = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            handler.firstDayBeenInAllRooms(nextVisit);

        }


        //TLE WA
        public int firstDayBeenInAllRooms(int[] nextVisit) {
            int MOD = (int) 1e9 + 7;
            int n = nextVisit.length;
            //访问过的房间数量
            int v = 0;
            //访问房间的次数
            int[] cnt = new int[n];
            int day = 0;
            int i = nextVisit[day];
            while (v < n) {
//                System.out.printf("%d ", i);
                cnt[i]++;
                if (cnt[i] == 1) {
                    v++;
                }
                if (v == n) break;
                if (cnt[i] % 2 == 1) {
                    i = nextVisit[i];
                } else {
                    i = (i + 1) % n;
                }
                day++;
                day %= MOD;
                System.out.printf("%d ", v);
            }
            return day % MOD;
        }
    }

    static class _3rd_1 {
        //https://leetcode-cn.com/problems/first-day-where-you-have-been-in-all-the-rooms/solution/c-si-wei-ti-xu-lie-dong-tai-gui-hua-by-e-q902/
        public int firstDayBeenInAllRooms(int[] nextVisit) {
            int MOD = (int) 1e9 + 7;
            int n = nextVisit.length;
            //f[i]表示第一次到达i房间的天数
            long[] f = new long[n];
            f[0] = 0;
            //f[i] = f[i-1]+1  偶数时从前一个房间来
            //奇数时
            for (int i = 1; i < n; i++) {
                f[i] = (MOD + f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1) % MOD;
            }
            return (int) f[n - 1] % MOD;
        }
    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
