package com.frankcooper.bank;

import java.util.HashSet;
import java.util.*;

/**
 * @Date 2020/9/2
 * @Author Frank Cooper
 * @Description
 */
public class _827 {

    static _827 handler = new _827();

    public static void main(String[] args) {
        int[][] grid = {{1, 0}, {0, 1}};
        handler.largestIsland(grid);
    }


    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};
    int[][] grid;
    int N;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        N = grid.length;

        int index = 2;
        int[] area = new int[N * N + 2];
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                if (grid[r][c] == 1)
                    area[index] = dfs(r, c, index++);

        int ans = 0;
        for (int x : area) ans = Math.max(ans, x);
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                if (grid[r][c] == 0) {
                    Set<Integer> seen = new HashSet();
                    for (Integer move : neighbors(r, c))
                        if (grid[move / N][move % N] > 1)
                            seen.add(grid[move / N][move % N]);

                    int bns = 1;
                    for (int i : seen) bns += area[i];
                    ans = Math.max(ans, bns);
                }

        return ans;
    }

    public int dfs(int r, int c, int index) {
        int ans = 1;
        grid[r][c] = index;
        for (Integer move : neighbors(r, c)) {
            if (grid[move / N][move % N] == 1) {
                grid[move / N][move % N] = index;
                ans += dfs(move / N, move % N, index);
            }
        }

        return ans;
    }

    public List<Integer> neighbors(int r, int c) {
        List<Integer> ans = new ArrayList();
        for (int k = 0; k < 4; ++k) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < N && 0 <= nc && nc < N)
                ans.add(nr * N + nc);
        }

        return ans;
    }


    class _1st {

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        int m, n;

        public int largestIsland(int[][] grid) {

            m = grid.length;
            n = grid[0].length;
            //1.初始化UnionFind 并且做联通，当前点的down 和 right 方向
            UnionFind uf = new UnionFind(m * n);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        if ((i + 1) < m && grid[i + 1][j] == 1) {
                            uf.unoin((i + 1) * n + j, i * n + j);
                        }
                        if ((j + 1) < n && grid[i][j + 1] == 1) {
                            uf.unoin(i * n + (j + 1), i * n + j);
                        }
                    }
                }
            }
            //2.计算当前点的所在区域的面积，并记录最大值，满格子为1(陆地的情况)
            int[] areas = new int[m * n];
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int rootIdx = uf.find(i * n + j);
                    areas[rootIdx]++;
                    res = Math.max(res, areas[rootIdx]);
                }
            }

            //3.开始考虑0（海洋）的情况，我那个四个方向扩散
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        Set<Integer> rootSet = new HashSet<>();
                        int tmp = 1;//此点位0，改变其为1，陆地面积+1
                        for (int[] dir : dirs) {
                            int nextI = i + dir[0], nextJ = j + dir[1];//当前点的下一个点，四个方向
                            if (!inArea(nextI, nextJ)) continue;
                            int rootIdx = uf.find(nextI * n + nextJ);//找到当前点的共同父节点
                            if (grid[nextI][nextJ] == 1 && !rootSet.contains(rootIdx)) {//此父节点未被访问过
                                tmp += areas[rootIdx];
                                rootSet.add(rootIdx);
                            }
                        }
                        res = Math.max(res, tmp);//取最大值
                    }
                }
            }
            return res;
        }

        //判断坐标点是否在网格区域范围内
        private boolean inArea(int x, int y) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }


        class UnionFind {
            int[] parents;
            int[] ranks;

            public UnionFind(int n) {
                parents = new int[n];
                ranks = new int[n];
                for (int i = 0; i < n; i++) parents[i] = i;
            }


            public int find(int x) {
                if (x != parents[x]) {
                    parents[x] = find(parents[x]);
                }
                return parents[x];
            }

            public void unoin(int x, int y) {
                int rootX = find(x), rootY = find(y);
                if (rootX == rootY) return;
                if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
                if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
                if (ranks[rootX] == ranks[rootY]) {
                    parents[rootY] = rootX;
                    ranks[rootY]++;
                }
            }
        }
    }

}
