package com.frankcooper.coder;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
            //step3
            minPathP2A(P, A);

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

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        private boolean isCoast(int[] p) {
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


        private void minPathP2A(int[] P, int[] A) {
            Queue<int[]> q = new LinkedList<>();
            Map<String, Boolean> vis = new HashMap<>();
            q.offer(P);
            int level = -1;
            boolean find = false;
            while (!q.isEmpty() && !find) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] c = q.poll();
                    String k = c[0] + "#" + c[1];
                    vis.put(k, true);
                    if (c[0] == A[0] && c[1] == A[1]) {
                        find = true;
                    }
                    for (int[] d : dirs) {
                        int nx = c[0] + d[0], ny = c[1] + d[1];
                        if (nx >= 0 && nx < M && ny >= 0 && ny < N && grid[nx][ny] == 1) {
                            k = nx + "#" + ny;
                            if (vis.get(k) == null || !vis.get(k)) {
                                q.offer(new int[]{nx, ny});
                                vis.put(k, true);
                            }
                        }
                    }
                }
                level++;
            }
            System.out.printf("%d\n", level);

            //17
            //P[10,0]->A[16,9]  : [10,0]->[11,0]->[11,1]->[12,1]->[13,1]->[13,2]->[14,2]->[14,3]->[15,3]->[16,3]->[17,3]->[17,4]->[17,5]->[16,5]->[16,6]->[16,7]->[16,8]->[16,9]
            //A->E([17,9])
            //[16,9]->[17,9]
        }

        private void printPathP2A(int[] P, int[] A, int path) {

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


        static int[][] standard = new int[][]
                {{6, 5, 2, 6, 7, 9, 4, 5, 1, 3},
                        {3, 3, 3, 2, 3, 6, 7, 2, 7, 2},
                        {5, 10, 3, 2, 2, 3, 6, 5, 2, 8},
                        {4, 3, 6, 6, 6, 3, 8, 50, 3, 2},
                        {4, 7, 1, 2, 8, 2, 6, 4, 7, 5},
                        {5, 4, 2, 80, 2, 0, 2, 5, 7, 9},
                        {1, 3, 7, 6, 1, 9, 3, 3, 8, 9},
                        {3, 1, 8, 9, 3, 9, 0, 8, 1, 3},
                        {30, 2, 3, 3, 8, 5, 7, 2, 5, 6},
                        {5, 6, 6, 7, 2, 3, 3, 5, 3, 8}};
        static int[][] A = new int[][]
                {{0, 2, 9, 6, 5, 8, 8, 4, 5, 7},
                        {6, 5, 4, 2, 5, 6, 6, 7, 9, 1},
                        {3, 3, 9, 3, 4, 2, 5, 3, 6, 5},
                        {4, 2, 1, 1, 2, 3, 4, 6, 4, 3},
                        {5, 1, 4, 3, 7, 2, 5, 2, 3, 4},
                        {4, 3, 6, 6, 9, 6, 1, 6, 3, 2},
                        {3, 2, 3, 5, 2, 1, 3, 3, 4, 7},
                        {2, 6, 4, 5, 3, 6, 4, 7, 5, 3},
                        {3, 1, 2, 8, 2, 9, 8, 3, 9, 4},
                        {1, 3, 2, 7, 2, 6, 6, 1, 9, 6}};

        static int[][] B =
                {{4, 6, 7, 3, 2, 6, 2, 7, 3, 0},
                        {5, 4, 6, 5, 5, 3, 9, 6, 1, 3},
                        {9, 7, 3, 7, 2, 3, 6, 9, 7, 2},
                        {7, 3, 6, 5, 6, 4, 1, 4, 4, 3},
                        {5, 6, 7, 9, 5, 6, 5, 5, 2, 8},
                        {4, 8, 2, 1, 5, 7, 5, 2, 3, 2},
                        {4, 3, 5, 2, 5, 1, 4, 2, 3, 7},
                        {6, 4, 2, 3, 2, 5, 1, 6, 4, 6},
                        {9, 0, 4, 5, 8, 1, 5, 2, 1, 3},
                        {4, 3, 1, 6, 3, 3, 2, 1, 8, 9}};
        static int[][] C = new int[][]
                {{4, 3, 6, 6, 9, 6, 1, 6, 3, 2},
                        {2, 4, 4, 1, 7, 9, 6, 2, 9, 8},
                        {3, 2, 3, 5, 2, 1, 3, 3, 4, 7},
                        {2, 6, 4, 5, 3, 6, 4, 7, 5, 3},
                        {3, 1, 2, 8, 2, 9, 8, 3, 9, 4},
                        {2, 4, 3, 7, 6, 5, 6, 5, 2, 8},
                        {1, 3, 2, 7, 2, 6, 6, 1, 9, 6},
                        {3, 2, 1, 3, 6, 3, 9, 8, 5, 8},
                        {5, 6, 5, 6, 1, 7, 4, 2, 3, 8},
                        {0, 1, 3, 5, 3, 2, 6, 1, 5, 4}};
        static int[][] D = new int[][]
                {{4, 8, 2, 1, 5, 7, 5, 2, 3, 2},
                        {5, 4, 2, 3, 6, 2, 2, 3, 9, 6},
                        {4, 3, 5, 2, 5, 1, 4, 2, 3, 7},
                        {6, 4, 2, 3, 2, 5, 1, 6, 4, 6},
                        {9, 0, 4, 5, 8, 1, 5, 2, 1, 3},
                        {7, 6, 2, 4, 8, 6, 6, 2, 1, 5},
                        {4, 3, 1, 6, 3, 3, 2, 1, 8, 9},
                        {4, 7, 6, 5, 2, 6, 7, 7, 5, 6},
                        {9, 3, 5, 1, 5, 9, 4, 5, 3, 8},
                        {2, 4, 3, 1, 8, 5, 3, 2, 3, 0}};

        static int height = 3;


        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] dirsA = {{0, 1}, {1, 0}};//右，下
            Player playerA = new Player("A", 0, 0, M - 1, N - 1, dirsA, A);
            int[][] dirsB = {{0, -1}, {1, 0}};//左，下
            Player playerB = new Player("B", 0, N - 1, M - 1, 0, dirsB, B);
            int[][] dirsC = {{0, 1}, {-1, 0}};//右，上
            Player playerC = new Player("C", M - 1, 0, 0, N - 1, dirsC, C);
            int[][] dirsD = {{0, -1}, {-1, 0}};//左，上
            Player playerD = new Player("D", M - 1, N - 1, 0, 0, dirsD, D);
            List<Player> players = new ArrayList<Player>() {{
                add(playerA);
                add(playerB);
                add(playerC);
                add(playerD);
            }};
            for (Player player : players) {
                if (player.grid != null) {
                    handler.calMaxXOR(player);
                }
            }
        }

        static int M = standard.length, N = standard[0].length;

        private void calMaxXOR(Player player) {
            List<List<int[]>> allPaths = dfs(player.si, player.sj, player);
            int maxx = Integer.MIN_VALUE;
            for (List<int[]> path : allPaths) {
                int res = calXOR(path);
                maxx = Math.max(maxx, res);
            }
            if (maxx == Integer.MIN_VALUE) {
                System.out.printf("Player:[%s]-->can't pass\n", player.name);
            } else {
                System.out.printf("Player:[%s]-->max XOR :[%d]\n", player.name, maxx);
            }

        }

        private List<List<int[]>> dfs(int i, int j, Player player) {
            List<List<int[]>> res = new ArrayList<>();
            if (i == player.ei && j == player.ej) {
                List<int[]> path = new ArrayList<>();
                path.add(new int[]{i, j});
                res.add(path);
                return res;
            }
            for (int[] d : player.dirs) {
                int ni = i + d[0], nj = j + d[1];
                if (ni >= 0 && ni < M && nj >= 0 && nj < N && valid(i, j, ni, nj, player.grid)) {
//                    System.out.printf("->[%d,%d]", ni, nj);
                    List<List<int[]>> paths = dfs(ni, nj, player);
                    for (List<int[]> path : paths) {
                        path.add(0, new int[]{i, j});
                        res.add(path);
                    }
                }
            }
            return res;
        }

        private boolean valid(int i, int j, int ni, int nj, int[][] grid) {
            return Math.abs(grid[i][j] - grid[ni][nj]) <= height;
        }

        private int calXOR(List<int[]> path) {
            int res = 0;
            for (int[] x : path) {
                res ^= standard[x[0]][x[1]];
            }
            return res;
        }


        @Data
        @AllArgsConstructor
        public static class Player {
            private String name;
            //起始坐标
            private int si;
            private int sj;
            private int ei;
            private int ej;
            //移动的坐标
            private int[][] dirs;
            private int[][] grid;
        }

    }


    static class _3rd {

        static int M = 10, N = 10;
        static int targetXOR = 88;
        static int si = 0, sj = 0, ei = 9, ej = 9;
        static int MOD = 100;

        static int[][] grid = new int[M][N];


        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.exec();
        }


        private void exec() {
            build();
            int cnt = 0;
            List<List<int[]>> paths = dfs(si, sj);
            for (List<int[]> path : paths) {
                int xor = calXOR(path);
                System.out.printf("%d\n", xor);
                if (xor == targetXOR) {
                    cnt++;
                }
            }
            System.out.printf("cnt:%d\n", cnt % MOD);
        }


        private void build() {
            int idx = 1;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    grid[i][j] = idx++;
                }
            }
        }

        int[][] dirs = {{1, 0}, {0, 1}};

        private List<List<int[]>> dfs(int i, int j) {
            List<List<int[]>> res = new ArrayList<>();
            if (i == ei && j == ej) {
                List<int[]> path = new ArrayList<>();
                path.add(new int[]{i, j});
                res.add(path);
                return res;
            }
            for (int[] d : dirs) {
                int ni = i + d[0], nj = j + d[1];
                if (ni >= 0 && ni < M && nj >= 0 && nj < N) {
//                    System.out.printf("->[%d,%d]", ni, nj);
                    List<List<int[]>> paths = dfs(ni, nj);
                    for (List<int[]> path : paths) {
                        path.add(0, new int[]{i, j});
                        res.add(path);
                    }
                }
            }
            return res;
        }

        private int calXOR(List<int[]> path) {
            int res = 0;
            for (int[] x : path) {
                res ^= grid[x[0]][x[1]];
            }
            return res;
        }


    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            //
            System.out.println(handler.A01());

//            System.out.println((1>>2)+1);
//
            System.out.println((char) 'E' - 65);
//            System.out.println((char)  - 7);
            int ans1 = 7, ans2 = 14, ans3 = 15, ans4 = 4;
            System.out.println(  handler.A05(ans1,ans2,ans3,ans4));
        }

        int[] array = new int[]{13, 14, 15};

        public int A01() {
            fun(array);
            return sum(array);
        }

        private void fun(int[] array) {
            array = new int[]{16, 17, 18};
//            System.out.println(sum(array));
        }

        private int sum(int[] array) {
            int res = 0;
            for (int i : array) {
                res += i % 2 + 2;
            }
            return res - 1;
        }


        public int A02(int input) {
            int O = 14;
//            String res = String.format("")
            return 0;
        }
//
//        public int A03(float input) {
//            int i = 0;
//            if ((float) (input +  (1e-5))) {
//
//            }
//        }

        public String A05(int ans1, int ans2, int ans3, int ans4) {
            String res = "";
            res += (char) (ans1 + 65);
            res += (char) (ans2 + 65);
            res += (char) (ans3 + 65);
            res += (char) (ans4 + 65);
            return res;
        }


    }
}
