package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import org.junit.Assert;

public class Week263 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles";
//            Assert.assertTrue(handler.areNumbersAscending(s));
            s = "hello world 5 x 5";
            Assert.assertFalse(handler.areNumbersAscending(s));

        }


        public boolean areNumbersAscending(String s) {
            int prev = -1;
            int l = 0, r = 0;
            while (r < s.length()) {
                while (!Character.isDigit(s.charAt(l))) {
                    l++;
                    if (l >= s.length()) return true;
                }
                r = l;
                while (r < s.length() && Character.isDigit(s.charAt(r))) {
                    r++;
                }
                int num = Integer.parseInt(s.substring(l, r));
                if (prev != -1 && num <= prev) {
                    return false;
                }
                prev = num;
                l = r;
            }
            return true;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        class Bank {

            long[] balance;

            public Bank(long[] balance) {
                this.balance = balance;
            }

            public boolean transfer(int account1, int account2, long money) {
                if (!check(account1) || !check(account2)) return false;
                if (balance[account1 - 1] < money) return false;
                balance[account1 - 1] -= money;
                balance[account2 - 1] += money;
                return true;
            }

            public boolean deposit(int account, long money) {
                if (!check(account)) return false;
                balance[account - 1] += money;
                return true;
            }

            public boolean withdraw(int account, long money) {
                if (!check(account)) return false;
                if (balance[account - 1] < money) return false;
                balance[account - 1] -= money;
                return true;
            }

            private boolean check(int account) {
                if (account <= 0 || account > balance.length) return false;
                return true;
            }
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums = new int[]{3, 2, 1, 5};
            Assert.assertEquals(6, handler.countMaxOrSubsets(nums));
        }

        public int countMaxOrSubsets(int[] nums) {
            int n = nums.length;
            //maxx：最大的子集的按位或后的值，cnt：该maxx下，出现的子集的次数
            int maxx = 0, cnt = 0;
            //从 二进制 1枚举到 1<<n
            for (int i = 1; i <= (1 << n); i++) {
                //idx：nums的下标，t：这一轮下来的子集按位或后的值，bit:该二进制
                int idx = 0, t = 0, bit = i;
                //计算位中为1的结果按或后的结果
                while (bit > 0 && idx < n) {
                    if ((bit & 1) == 1) {
                        t |= nums[idx];
                    }
                    bit >>= 1;
                    idx++;
                }
                //更新maxx 和 cnt  如果有新的maxx值，cnt重新开
                if (t >= maxx) {
                    if (t == maxx) cnt++;
                    else cnt = 1;
                    maxx = t;
                }
            }
            return cnt;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int secondMinimum(int n, int[][] edges, int time, int change) {
            List<Integer>[] graph = new List[n + 1];
            for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                graph[u].add(v);
                graph[v].add(u);
            }
            // path[i][0] 表示从 1 到 i 的最短路长度，path[i][1] 表示从 1 到 i 的严格次短路长度
            int[][] path = new int[n + 1][2];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(path[i], Integer.MAX_VALUE);
            }
            path[1][0] = 0;
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{1, 0});
            while (path[n][1] == Integer.MAX_VALUE) {
                int[] arr = queue.poll();
                //cur表示当前结点，len表示到达当前结点需要走的总路程
                int cur = arr[0], len = arr[1];
                //更新到达相邻结点的最短总路程
                for (int next : graph[cur]) {
                    if (len + 1 < path[next][0]) {
                        path[next][0] = len + 1;
                        //这里更新完最短总路程后，为什么不进行比较
                        // 原path[next][0]与path[next][1]的大小，从而更新次短总路程？
                        //答：最短总路程总是先到达的，故不可能存在次短总路程先到达，然后最短总路程才到达
                        queue.offer(new int[]{next, len + 1});
                    } else if (len + 1 > path[next][0] && len + 1 < path[next][1]) {
                        //更新次短总路程
                        path[next][1] = len + 1;
                        //用于更新next结点相邻结点的次短总路程
                        queue.offer(new int[]{next, len + 1});
                    }
                }
            }
            int res = 0;
            //计算走了 path[n][1] 步，共需要等待多少红灯和共需要多少时间
            for (int i = 0; i < path[n][1]; i++) {
                //经过 (2 * change) 灯由绿灯变成绿灯，并且维持 change 秒
                //如果 res 不在该范围到达，就无法到达后立即出发，需要等红灯
                //等待时间为，一个 (2 * change) 周期，减去 到达时间
                if (res % (2 * change) >= change) {
                    res = res + (2 * change - res % (2 * change));
                }
                res = res + time;
            }
            return res;
        }
        /*
         * 假设到达节点 i 的时间为 t_i，则到达节点 j 的时间为t_j = t_i + change + wait
         *
         * 若 t_i/change 整除为偶数，因为起始0时刻为绿灯，那么当前绿灯，无需等待时间，wait = 0。
         * 若 t_i/change 整除为奇数那么到达 i 时 已经/恰好 红灯，但是还需要等待wait = change - (t_i % change)时间变为绿灯。
         */
    }
}
