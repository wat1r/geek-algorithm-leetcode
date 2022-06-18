package com.frankcooper.platform.leetcode.bank.bi_weekly;

import org.junit.Assert;

import java.util.*;

public class BiWeek77 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int countPrefixes(String[] words, String s) {
            int res = 0;
            for (String word : words) {
                if (word.length() > s.length()) continue;
                if (word.equals(s.substring(0, word.length()))) res++;
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = {2, 5, 3, 9, 5, 3};
            handler.minimumAverageDifference(nums);
        }

        //注意使用long类型
        public int minimumAverageDifference(int[] nums) {
            int n = nums.length;
            long[] pre = new long[n + 1];
            for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];
            long minn = 100010;
            int index = 0;
            for (int i = 0; i < n; i++) {
                long front = (int) (pre[i + 1] / (i + 1));
                long back = i == n - 1 ? 0 : (long) ((pre[n] - pre[i + 1]) / (n - i - 1));
                long diff = Math.abs(front - back);
                if (diff < minn) {
                    minn = diff;
                    index = i;
                }
            }
            return index;
        }
    }

    //WA
    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int m = 4, n = 6, guards[][] = {{0, 0}, {1, 1}, {2, 3}}, walls[][] = {{0, 1}, {2, 2}, {1, 4}};
//
//            Assert.assertEquals(7, handler.countUnguarded(m, n, guards, walls));

            //2
            //7
            //[[1,5],[1,1],[1,6],[0,2]]
            //[[0,6],[0,3],[0,5]]
            //ans :1

            m = 2;
            n = 7;
            guards = new int[][]{{1, 5}, {1, 1}, {1, 6}, {0, 2}};
            walls = new int[][]{{0, 6}, {0, 3}, {0, 5}};
//            Assert.assertEquals(1, handler.countUnguarded(m, n, guards, walls));

            m = 4;
            n = 3;
            guards = new int[][]{{1, 0}};
            walls = new int[][]{{0, 0}, {1, 2}, {0, 2}, {2, 1}, {0, 1}, {2, 2}};
//            Assert.assertEquals(2, handler.countUnguarded(m, n, guards, walls));
            m = 9;
            n = 6;
            guards = new int[][]{{8, 2}};
            walls = new int[][]{{2, 3}, {2, 4}, {6, 5}, {2, 0}, {5, 3}, {7, 5}, {4, 2}, {3, 0}};
            Assert.assertEquals(37, handler.countUnguarded(m, n, guards, walls));


        }

        public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {


            //0 表示没有被保卫的 1 表示保卫了 -1表示 wall 2 表示guard

            int[][] grid = new int[m][n];
            Map<Integer, List<Integer>> wallRowMap = new HashMap<>();
            Map<Integer, List<Integer>> wallColMap = new HashMap<>();
            for (int[] wall : walls) {
                int x = wall[0], y = wall[1];
                grid[x][y] = -1;
                List<Integer> rowList = wallRowMap.getOrDefault(x, new ArrayList<>());
                rowList.add(y);
                wallRowMap.put(x, rowList);
                List<Integer> colList = wallColMap.getOrDefault(y, new ArrayList<>());
                colList.add(x);
                wallColMap.put(y, colList);
            }
            for (Map.Entry<Integer, List<Integer>> e : wallRowMap.entrySet()) {
                Collections.sort(e.getValue());
            }
            for (Map.Entry<Integer, List<Integer>> e : wallColMap.entrySet()) {
                Collections.sort(e.getValue());
            }
            int cnt = 0;
            for (int[] guard : guards) {
                int x = guard[0], y = guard[1];
                grid[x][y] = 2;
                List<Integer> rowList = wallRowMap.get(x);
                int[] t = binarySearch(rowList, y, new int[]{0, n});
                for (int j = t[0]; j < t[1]; j++) {
                    if (grid[x][j] == 0) {
                        grid[x][j] = 1;
                    }
                }
                List<Integer> colList = wallColMap.get(y);
                t = binarySearch(colList, y, new int[]{0, m});
                for (int i = t[0]; i < t[1]; i++) {
                    if (grid[i][y] == 0) {
                        grid[i][y] = 1;
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) cnt++;
                }
            }
            return cnt;
        }


        private int[] binarySearch(List<Integer> list, int target, int[] res) {
            if (list == null) return res;
            int start = res[0], end = res[1];
            int l = 0, r = list.size();
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (list.get(mid) > target) {
                    r = mid - 1;
                    end = list.get(mid);
                } else {
                    l = mid + 1;
                    start = list.get(mid);
                }
            }
            if (l < list.size() && end > list.get(l)) end = list.get(l);
            return new int[]{start, end};
        }
    }

    static class _3rd_1 {
        public static void main(String[] args) {

        }

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
            int ans = 0;
            //用于存储当前格子的状态(围墙W/守卫G/守卫守护的范围I)
            char[][] grid = new char[m][n];
            //将守卫放到所在的位置上
            for (int[] guard : guards) {
                grid[guard[0]][guard[1]] = 'G';
            }
            //将墙立到所在的位置上
            for (int[] wall : walls) {
                grid[wall[0]][wall[1]] = 'W';
            }
        /*
        对于每一个守卫，会有三种情况
        1；前方什么都没有，我们可以一直看下去
        2；前方有一堵墙，会阻碍我们的视线
        3；前方又有一个守卫，这时候我们视线方向已经被前方的守卫所看到
         */
            for (int[] guard : guards) {
                int x = guard[0], y = guard[1];
                for (int i = 0; i < 4; i++) {
                    //用方向数组实现
                    int nx = x + direction[i][0], ny = y + direction[i][1];
                    //判断是否越界
                    while (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] != 'G' && grid[nx][ny] != 'W') {
                        //前方不是守卫也不是墙，我们继续沿着这个方向看过去，并且将视野所及标为I
                        grid[nx][ny] = 'I';
                        //继续朝着该方向向前看
                        nx += direction[i][0];
                        ny += direction[i][1];
                    }
                }
            }
            //不是墙，守卫，视野所及区域都为答案
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] != 'G' && grid[i][j] != 'W' && grid[i][j] != 'I') ans++;
                }
            }
            return ans;
        }

    }

    static class _4th {
        public static void main(String[] args) {

        }

        public int maximumMinutes(int[][] grid) {

            return 0;
        }
    }
}
