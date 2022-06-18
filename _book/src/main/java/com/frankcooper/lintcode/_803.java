package com.frankcooper.platform.lintcode;

import com.frankcooper.utils.PrintUtils;

import java.util.*;
import java.util.Set;

public class _803 {

    static _803 handler = new _803();

    public static void main(String[] args) {
        handler.testOne();
    }


    private void testOne() {
        _2nd second = new _2nd();
        int[][] grid = {{1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 1}, {0, 1, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 1}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 0, 0, 1}, {0, 1, 1, 1, 1, 0}};
//        second.shortestDistance(grid);
//        handler.shortestDistance(grid);

        _4th  fourth = new _4th();
        fourth.shortestDistance(grid);
    }


    class _4th {

        int m, n;
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[][] dist;//累积距离场，不能用dist其他位置的值来更新，而是需要直接加上和建筑物之间的距离
        int[][] cnt;//某个位置已经计算过的建筑数量
        int buildingCnt = 0;//总的建筑数量

        public int shortestDistance(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            dist = new int[m][n];
            cnt = new int[m][n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 1) {
                        bfs(grid, i, j);
                    }

                }
            }
            PrintUtils.printMatrix(grid);
            PrintUtils.printMatrix(dist);
            PrintUtils.printMatrix(cnt);
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 0 && cnt[i][j] == buildingCnt) {
                        res = Math.min(res, dist[i][j]);
                    }
                }
            }
            return res == Integer.MAX_VALUE ? -1 : res;
        }

        private void bfs(int[][] grid, int i, int j) {
            buildingCnt++;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i * n + j);
            boolean[][] vis = new boolean[m][n];
            int level = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int k = 0; k < size; ++k) {
                    int currIdx = queue.poll();
                    int currR = currIdx / n, currC = currIdx % n;
                    for (int[] dir : directions) {
                        int nextR = currR + dir[0], nextC = currC + dir[1];
                        if (inArea(nextR, nextC) && grid[nextR][nextC] == 0 && !vis[nextR][nextC]) {
                            dist[nextR][nextC] += level;
                            cnt[nextR][nextC]++;
                            vis[nextR][nextC] = true;
                            queue.offer(nextR * n + nextC);
                        }
                    }
                }
                level++;
            }

        }

        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }

    }


    class _3rd {


        /**
         * @param grid: the 2D grid
         * @return: the shortest distance
         */
        public int shortestDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length, n = grid[0].length;
            int[][] totalDistance = new int[m][n];
            int step = 0, res = 0;

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 1) {
                        res = bfs(grid, i, j, step, totalDistance);
                        step--;
                    }
                }
            }

            return res == Integer.MAX_VALUE ? -1 : res;
        }

        private int bfs(int[][] grid, int x, int y, int step, int[][] totalDistance) {
            int res = Integer.MAX_VALUE, m = grid.length, n = grid[0].length;
            ;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(x * n + y);

            int curDis = 0;
            int[] dirs = {-1, 0, 1, 0, -1};

            while (!queue.isEmpty()) {
                int l = queue.size();
                curDis++;
                while (l-- != 0) {
                    int t = queue.poll();
                    x = t / n;
                    y = t % n;

                    for (int i = 0; i < 4; ++i) {
                        int _x = x + dirs[i], _y = y + dirs[i + 1];
                        if (_x >= 0 && _x < m && _y >= 0 && _y < n && grid[_x][_y] == step) {
                            queue.offer(_x * n + _y);
                            totalDistance[_x][_y] += curDis;
                            grid[_x][_y]--;
                            res = Math.min(res, totalDistance[_x][_y]);
                        }
                    }
                }
            }
            return res;
        }
    }


    class _2nd {


        public int shortestDistance(int[][] grid) {
            int len = grid[0].length;
            Map<Integer, Set<Integer>> houseToBuildingMap = new HashMap<>();
            int buildingCnt = 0;
            Queue<Integer> q = new LinkedList<>();

            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < len; c++) {
                    if (grid[r][c] == 1) {
                        int index = r * len + c;
                        q.offer(index);
                        buildingCnt++;
                        Set<Integer> buildingSet = new HashSet<>();
                        buildingSet.add(index);
                        houseToBuildingMap.put(index, buildingSet);
                    }
                }
            }

            System.out.println(buildingCnt);

            int[] dr = new int[]{1, 0, -1, 0};
            int[] dc = new int[]{0, 1, 0, -1};

            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int currIndex = q.poll();
                    for (int j = 0; j < 4; j++) {
                        int currR = currIndex / len;
                        int currC = currIndex % len;
                        int newR = currR + dr[j];
                        int newC = currC + dc[j];
                        if (isValid(grid, newR, newC)) {
                            int newIndex = newR * len + newC;

                            houseToBuildingMap.putIfAbsent(newIndex, new HashSet<Integer>());
                            for (int building : houseToBuildingMap.get(currIndex)) {
                                houseToBuildingMap.get(newIndex).add(building);
                            }
                            if (houseToBuildingMap.get(newIndex).size() == buildingCnt) {
                                return calculateTotalDistance(grid, newR, newC);
                            }
                            q.offer(newIndex);
                        }
                    }

                }
            }

            return -1;
        }

        private boolean isValid(int[][] grid, int r, int c) {
            return 0 <= r && r < grid.length && 0 <= c
                    && c < grid[0].length && grid[r][c] == 0;
        }

        private int calculateTotalDistance(int[][] grid, int R, int C) {
            int totalDistance = 0;
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    if (grid[r][c] == 1) {
                        totalDistance += Math.abs(r - R) + Math.abs(c - C);
                    }
                }
            }
            System.out.printf("r:%d,c:%d,dis:%d\n", R, C, totalDistance);
            return totalDistance;
        }

    }


    int m, n;
    int[][] grid;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};


    public int shortestDistance(int[][] grid) {
        System.out.printf("--------------------------\n");
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        //计算总的建筑物的数量，及将建筑物的idx 存进queue中
        int buildingsCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.grid[i][j] == 1) {
                    int idx = i * n + j;
                    queue.offer(idx);
                    buildingsCnt++;
                    map.putIfAbsent(idx, new HashSet<>());
                    map.get(idx).add(idx);
                }
            }
        }
        while (!queue.isEmpty()) {
            Set<Integer> vis = new HashSet<>();
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int currIdx = queue.poll();
                vis.add(currIdx);
                for (int[] dir : directions) {
                    int currR = currIdx / n, currC = currIdx % n;
                    int nextR = currR + dir[0], nextC = currC + dir[1];
                    int nextIdx = nextR * n + nextC;
                    if (!vis.contains(nextIdx) && inArea(nextR, nextC) && this.grid[nextR][nextC] != 2) {
                        map.putIfAbsent(nextIdx, new HashSet<>());
                        for (int building : map.get(currIdx)) {
                            map.get(nextIdx).add(building);
                        }

                        if (buildingsCnt == map.get(nextIdx).size()) {
                            return caculateDistance(nextR, nextC);
                        }
                        if (this.grid[nextR][nextC] == 0) {
                            queue.offer(nextIdx);
                            vis.add(nextIdx);
                        }
                    }
                }
            }
        }
        return -1;
    }


    private boolean inArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }


    private int caculateDistance(int r, int c) {
        int distance = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    distance += Math.abs(i - r) + Math.abs(j - c);
                }
            }
        }
        System.out.printf("r:%d,c:%d,dis:%d\n", r, c, distance);
        return distance;
    }


}
