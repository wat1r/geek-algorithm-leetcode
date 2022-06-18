package com.frankcooper.platform.leetcode.bank.week;

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
    //扩展：952. 按公因数计算最大组件大小
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


    //1998. 数组的最大公因数排序
    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            int[] nums = new int[]{10, 5, 9, 3, 15};
//            handler.gcdSort(nums);
            Assert.assertTrue(handler.gcdSort(new int[]{10, 3, 9, 6, 8}));

        }


        int N = 100010;

        class UnionFind {
            int[] parent;
            int[] rank;

            public UnionFind(int[] nums) {
                parent = new int[N];
                rank = new int[N];
                for (int i = 2; i < N; i++) {
                    parent[i] = i;
                }
                Arrays.fill(rank, 1);
            }

            public int find(int i) {
                if (parent[i] != i) parent[i] = find(parent[i]);
                return parent[i];
            }

            public void union(int i, int j) {
                int rootx = find(i);
                int rooty = find(j);
                if (rootx != rooty) {
                    if (rank[rootx] > rank[rooty]) {
                        parent[rooty] = rootx;
                        rank[rootx]++;
                    } else if (rank[rooty] > rank[rootx]) {
                        parent[rootx] = rooty;
                        rank[rooty]++;
                    } else {
                        parent[rooty] = rootx;
                        rank[rootx]++;
                    }
                }
            }
        }

        //1. 如果a和b的公因数大于1，则可以交换，
        // 同理b和c的公因数大于1，b和c也可以交换，a和b和c在一个集合里，它们之间任意都可以交换
        //2.在做公因数的时候，对于当前x，将它的质因子也一并合并，比如21，质因子是3，7， 21 3 7 都在一个集合里
        //比如说15 15 3 5 都在一个集合里 相当于 21 3 7  15 3 5 这些数都在一个集合里，因为gca(21,15)=3
        //3.并查集合并后，在一个集合里的都可以交换，开出额外的数组mirror，将其排序，与原数组nums每一个进行比较
        //相同则说明在在一个集合里，不同即返回false
        //4.举例{10, 3, 9, 6, 8} 10 和 3 这两个数乍一看好像不能调换因为 10 =2*5 3=3 之间好像没有交集
        //但是一旦牵扯到6这个数 6 =2*3 gcd(6,3)=3>1 6和3可以调换 gcd(6,10)=2 6和10可以调换，参考上面的结论1
        //3和10也可以交换 3 6 10 属于一个集合 处理到6的时候，将9的parent从9挂到了10下，9和3又有联系。3的parent是9 相当于3的parent变成10
        public boolean gcdSort(int[] nums) {
            UnionFind uf = new UnionFind(nums);
            for (int x : nums) {
                int j = x;
                for (int i = 2; i <= x / i; i++) {
                    boolean flag = false;
                    while (x % i == 0) {
                        x /= i;
                        flag = true;
                    }
                    if (flag) uf.union(j, i);
                }
                if (x > 1) uf.union(j, x);
            }
            int[] mirror = Arrays.copyOf(nums, nums.length);
            Arrays.sort(mirror);
            for (int i = 0; i < mirror.length; i++) {
                if (uf.find(mirror[i]) != uf.find(nums[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
