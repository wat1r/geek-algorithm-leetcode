package com.frankcooper.bank.week;

import java.util.*;
//import com.frankcooper.swordoffer.utils.PrintUtils;

import org.junit.Assert;

public class Week236 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums = {51, 38, 73, 21, 27, 55, 18, 15, 79, 29, 13, 45, 8, -73, -92, -20, -50, -60, -70};
            handler.arraySign(nums);

        }


        public int arraySign(int[] nums) {
//            int sign = 1;
            int postive = 0, negtive = 0;
            for (int x : nums) {
                if (x == 0) return 0;
                if (x > 0) postive++;
                else negtive++;
            }
            return negtive % 2 == 0 ? 1 : -1;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.findTheWinner(6, 5);
        }


        public int findTheWinner(int n, int k) {

            List<Integer> list = new LinkedList<>();
            for (int i = 1; i <= n; i++) list.add(i);
            int t = n;
            int i = 0;
            while (t > 1) {
                int next = (i + k) % t - 1;
                list.remove((i - 1 + k) % t);
                i = next == -1 ? 0 : next;
                t--;
            }
            return list.get(0);
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] o = {0, 1, 0, 1, 3, 1, 1, 1, 0, 2, 0};
            o = new int[]{0, 1, 2, 3, 0};
            /**
             * [0,1,2,3,0]  //2
             * [0,1,1,3,3,0]  //0
             * [0,2,1,0,3,0]  //2
             * [0,1,0,1,3,1,1,1,0,2,0] //1
             * [0,3,3,0,3,2,2,0,0,3,0] //1
             * [0,0,3,1,0,1,0,2,3,1,0] //2
             */
            o = new int[]{0, 0, 3, 1, 0, 1, 0, 2, 3, 1, 0};
            o = new int[]{0, 1, 2, 3, 0};
            handler.minSideJumps(o);
        }


        public int minSideJumps(int[] obstacles) {
            int n = obstacles.length;
            int[][] dp = new int[n][3];//dp[i][j]表示处在i位置，j赛道，最少侧跳数量
            for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
            dp[0][0] = dp[0][2] = 1;
            dp[0][1] = 0;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    if (obstacles[i] == j + 1) continue;
                    dp[i][j] = dp[i - 1][j];
                }

                for (int j = 0; j < 3; j++) {
                    if (obstacles[i] == j + 1) continue;
                    int x = (j + 1) % 3, y = (j + 2) % 3;
                    dp[i][j] = Math.min(dp[i][j], Math.min(dp[i][x], dp[i][y]) + 1);
                }
            }
            int res = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
            return res;

        }




      /*  public int minSideJumps(int[] obstacles) {
            Set<Integer> set = new HashSet<>();
            int cnt = 0, last = 0, two = 0;
            for (int i = 0; i < obstacles.length; i++) {
                if (set.size() == 3) {
                    if (last == 2) {
                        cnt++;
                    } else {
                        cnt += 2;
                    }
                    set.clear();
                    continue;
                }
                if (obstacles[i] != 0) {
                    set.add(obstacles[i]);
                    last = obstacles[i];
                    if (obstacles[i] == 2) two = 1;
                }
            }
            if (cnt == 0 && two == 1) cnt = 1;
//            if (last != 2) cnt++;
            return cnt;
        }*/
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        class MKAverage {

            int size;
            int m, k;
            int[] arr;

            public MKAverage(int m, int k) {
                this.m = m;
                this.k = k;
                this.size = 0;
                this.arr = new int[m];
            }

            public void addElement(int num) {
                arr[size % m] = num;
                size++;
            }

            public int calculateMKAverage() {
                if (size < m) return -1;
                //这一步是暴力枚举方法的关键，不能直接用arr去sort。因为calculateMKAverage()函数可能调用2次以上
                int[] tmp = Arrays.copyOfRange(arr, 0, m);
                Arrays.sort(tmp);
                long sum = 0;
                for (int j = k; j < m - k; j++) {
                    sum += tmp[j];
                }
                return (int) (sum / (m - 2 * k));
            }
        }
    }

    static class _4th_1 {
        public static void main(String[] args) {

        }
    }

    static class _4th_2 {
        public static void main(String[] args) {

        }

        class MKAverage {
            int m, k, size;

            PriorityQueue<Integer> maxPart = new PriorityQueue<>();//小根堆
            PriorityQueue<Integer> minPart = new PriorityQueue<>((o1, o2) -> o2 - o1);//大根堆

            public MKAverage(int m, int k) {

            }

            public void addElement(int num) {

            }

            public int calculateMKAverage() {

                return 0;
            }
        }
    }
}
