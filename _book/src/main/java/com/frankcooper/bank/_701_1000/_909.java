package com.frankcooper.bank._701_1000;

import java.util.*;

import com.frankcooper.utils.PrintUtils;

public class _909 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[][] board = PrintUtils.processSymbol("[[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]");
//            handler.transform(board);

            handler.snakesAndLadders(board);
        }


        int[] nums;
        int n;

        public int snakesAndLadders(int[][] board) {
            this.n = board.length;
            this.nums = new int[n * n + 1];
            transform(board);
            Set<Integer> vis = new HashSet<>();
            Queue<Integer> q = new LinkedList<>();
            q.offer(1);//起点
            int step = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int cur = q.poll();
                    if (cur == n * n) return step;
                    if (!vis.contains(cur)) {
                        bfs(q, cur);
                        vis.add(cur);
                    }
                }
                step++;
            }
            return -1;
        }


        private void bfs(Queue<Integer> q, int cur) {
            int N = 6;
            for (int i = 1; i <= N && (cur + i) < nums.length; i++) {
                if (nums[cur + i] != -1) q.offer(nums[cur + i]);
                else q.offer(cur + i);
            }
        }


        //将一维的转成二维的
        private void transform(int[][] board) {
            int idx = 0;
            for (int i = n - 1; i >= 0; i--) {
                int r = n - i;
                if (r % 2 == 1) {//奇数行,从左到右
                    for (int j = 0; j < n; j++) nums[++idx] = board[i][j];
                } else {//偶数行 ，从右到做
                    for (int j = n - 1; j >= 0; j--) nums[++idx] = board[i][j];
                }
            }
//            for (int num : nums) {
//                System.out.printf("%d ", num);
//            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
