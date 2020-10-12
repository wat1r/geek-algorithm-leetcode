package com.frankcooper.bank;

import java.util.*;

public class _773 {


    static _773 handler = new _773();


    public static void main(String[] args) {

        String str = "012345";
//        handler.swap(str, 0, 1);
//        handler.swap(str, 0, 3);

        int[][] board = {{1, 2, 3}, {4, 0, 5}};
        board = new int[][]{{3, 2, 4}, {1, 5, 0}};
//        handler.slidingPuzzle(board);
        handler.testTwo();
    }


    private void testTwo() {

        int[][] board = {{4, 1, 2}, {5, 0, 3}};
        _2nd test = new _2nd();
        test.slidingPuzzle(board);


    }


    /**
     * A* Search
     */
    class _2nd {


        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int R = 2, C = 3;

        public int slidingPuzzle(int[][] board) {
            PriorityQueue<State> pq = new PriorityQueue<>();
            Set<State> visited = new HashSet<>();
            int zeroI = 0, zeroJ = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] == 0) {
                        zeroI = i;
                        zeroJ = j;
                        break;
                    }
                }
            }
            State start = new State(board, 0, zeroI, zeroJ);
            pq.offer(start);
            visited.add(start);
            while (!pq.isEmpty()) {
                State curr = pq.remove();
                if (curr.isGoal()) return curr.taken;
                for (int[] dir : dirs) {
                    int nextZeroI = curr.zeroI + dir[0], nextZeroJ = curr.zeroJ + dir[1];
                    State next = curr.swap(nextZeroI, nextZeroJ);
                    if (next == null || visited.contains(next)) continue;
                    pq.offer(next);
                    visited.add(next);
                }
            }
            return -1;
        }


        private class State implements Comparable<State> {

            int[][] board;
            int taken;
            int zeroI;
            int zeroJ;

            public State(int[][] board, int taken, int zeroI, int zeroJ) {
                this.board = new int[R][C];
                for (int i = 0; i < R; ++i) {
                    for (int j = 0; j < C; ++j) {
                        this.board[i][j] = board[i][j];
                    }
                }
                this.taken = taken;
                this.zeroI = zeroI;
                this.zeroJ = zeroJ;
            }


            public State swap(int i, int j) {
                if (!inArea(i, j)) return null;
                State state = new State(this.board, this.taken + 1, i, j);
                int tmp = state.board[i][j];
                state.board[i][j] = state.board[zeroI][zeroJ];
                state.board[zeroI][zeroJ] = tmp;
                return state;
            }


            public int distance() {
                int dist = 0;
                for (int i = 0; i < R; ++i) {
                    for (int j = 0; j < C; ++j) {
                        if (this.board[i][j] == 0) continue;
                        int idx = this.board[i][j] - 1;
                        int r = idx / C;
                        int c = idx % C;
                        dist += Math.abs(r - i) + Math.abs(c - j);
                    }
                }
                return dist;
            }

            public boolean isGoal() {
                System.out.printf("dist:%d\n", distance());
                return distance() == 0;
            }


            @Override
            public int hashCode() {
                return Arrays.deepHashCode(this.board);
            }

            @Override
            public boolean equals(Object obj) {
                State that = (State) obj;
                return Arrays.deepEquals(that.board, this.board);
            }

            @Override
            public int compareTo(State that) {
                return this.distance() + this.taken - that.distance() - that.taken;
            }


            private boolean inArea(int i, int j) {
                return i >= 0 && i < R && j >= 0 && j < C;
            }
        }


    }


    /**
     * basic
     */
    class _1st {
        int[][] dirs = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};


        public int slidingPuzzle(int[][] board) {
            String start = "";
            int m = board.length, n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    start += board[i][j];
                }
            }
            String end = "123450";
            Queue<String> queue = new LinkedList<>();
            queue.offer(start);
            Set<String> visited = new HashSet<>();
            visited.add(start);
            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    //找到目标位置，退出
                    String curr = queue.poll();
                    if (curr.equals(end)) {
                        return level;
                    }
                    //找到0对应想idx
                    int zeroIdx = curr.indexOf("0");
                    for (int d : dirs[zeroIdx]) {
                        //swap 判断next
                        String next = swap(curr, zeroIdx, d);
//                    System.out.printf("curr:%s-->next:%s\n", curr, next);
                        if (!visited.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
//            PrintUtils.printMatrix(board);
                level++;
            }
            return -1;
        }

        /**
         * 交换一个 str的i与j未知的字符，生成一个新的字符返回
         *
         * @param str
         * @param i
         * @param j
         * @return
         */
        private String swap(String str, int i, int j) {
            StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(i, str.charAt(j));
            sb.setCharAt(j, str.charAt(i));
            return sb.toString();
        }
    }


}
