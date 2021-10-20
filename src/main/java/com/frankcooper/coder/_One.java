package com.frankcooper.coder;

import lombok.AllArgsConstructor;
import lombok.Data;

public class _One {

    static class _1st {
        static int M, N;
        static int[][] grid;
        static int[] P;

        public static void main(String[] args) {
            _1st handler = new _1st();
            grid = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 1},
                    {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 1, 1},
                    {1, 1, 1, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {1, 1, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 1, 0, 1, 1, 1},
                    {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 1, 1, 0, 0, 2, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
            int Px = 10, Py = 0;//P点
            P = new int[]{Px, Py};
            M = grid.length;
            N = grid[0].length;
            handler.exec();

        }

        private void exec() {
            //step1
            int[] E = findE();
            //step2
            int[] A = findA(E);

        }


        private int[] findE() {
            int[] res = new int[2];
            int minDis = Integer.MAX_VALUE;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] == 0) {
                        Pair pair = minDistance(new int[]{i, j});
                        if (pair.dis < minDis) {
                            minDis = pair.dis;
                            res = new int[]{pair.x, pair.y};
                        }
                    }
                }
            }
            System.out.printf("E:%d--->%d\n", res[0], res[1]);
            return res;
        }

        private Pair minDistance(int[] p0) {
            int res = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] == 1) {
                        res += calDistance(p0, new int[]{i, j});
                    }
                }
            }
            System.out.printf("%d->%d,%d\n", res, p0[0], p0[1]);
            return new Pair(p0[0], p0[1], res);
        }


        private int[] findA(int[] E) {
            int[] A = null;
            int minDis = Integer.MAX_VALUE;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (isCoast(new int[]{i, j})) {
                        int dis = calDistance(E, new int[]{i, j});
                        System.out.printf("%d--->%d,%d\n", dis, i, j);
                        if (dis <= minDis) {
                            if (dis == minDis) {
                                int curPA = calDistance(P, new int[]{i, j});
                                int prePA = calDistance(P, A);
                                if (curPA < prePA) {
                                    A = new int[]{i, j};
                                }
                            } else {
                                A = new int[]{i, j};
                            }
                            minDis = dis;

                        }
                    }
                }
            }
            System.out.printf("A:%d-->%d\n", A[0], A[1]);
            return A;
        }

        private boolean isCoast(int[] p) {
            int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            if (grid[p[0]][p[1]] == 1) {
                for (int[] d : dirs) {
                    int nx = p[0] + d[0], ny = p[1] + d[1];
                    if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                        if (grid[nx][ny] == 0) return true;
                    }
                }
            }
            return false;
        }


        private int minPathP2A(int[] P, int[] A) {

            return 0;
        }

        private int minPathA2E(int[] A, int[] E) {

            return 0;
        }

        private int calDistance(int[] p0, int[] p1) {
            return Math.abs(p0[0] - p1[0]) + Math.abs(p0[1] - p1[1]);
        }


        @Data
        @AllArgsConstructor
        static class Pair {
            private int x;
            private int y;
            private int dis;
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
