package com.frankcooper.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/8/18
 * @Author Frank Cooper
 * @Description
 */
public class _434 {
    static _434 baseHandler = new _434();

    public static void main(String[] args) {
        baseHandler.one();
    }


    public void one() {
        _1st handler = new _1st();
        int n = 4, m = 5;
        Point[] operators = new Point[4];
        operators[0] = new Point(1, 1);
        operators[1] = new Point(1, 2);
        operators[2] = new Point(1, 3);
        operators[3] = new Point(1, 4);
        handler.numIslands2(4, 5, operators);
    }


    class _1st {
        int count = 0;

        class UnionFind {
            int[] parents;

            public UnionFind(int n) {
                parents = new int[n];
                for (int i = 0; i < n; i++) parents[i] = i;
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                //修改union的逻辑：当x与y不属于一个联通区域时，将当前的两个点进行unoin，并将count--
                if (rootX != rootY) {
                    parents[rootX] = rootY;
                    count--;
                }
            }

            public int find(int x) {
                if (x != parents[x]) {
                    parents[x] = find(parents[x]);
                }
                return parents[x];
            }
        }

        int[][] grid;
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public List<Integer> numIslands2(int n, int m, Point[] operators) {
            List<Integer> res = new ArrayList<>();
            if (operators == null || operators.length == 0) return res;
            grid = new int[n][m];
            UnionFind uf = new UnionFind(n * m);
            for (Point point : operators) {
                int currX = point.x, currY = point.y;
                if (grid[currX][currY] == 1) {
                    res.add(count);
                    continue;
                }
                count++;//+1 后续会-1
                grid[currX][currY] = 1;
                for (int[] direction : directions) {
                    int nextX = currX + direction[0], nextY = currY + direction[1];
                    //找到当前点的相连点，如果也是1的话，证明被访问过，联通之
                    if (inArea(nextX, nextY, n, m) && grid[nextX][nextY] == 1) {
                        int curr = currX * m + currY;
                        int next = nextX * m + nextY;
                        //联通当前点与下一个位置的点。二维坐标转一维
                        System.out.println(String.format("curr:%d,next:%d", curr, next));
                        uf.union(curr, next);

                    }
                }
                res.add(count);
            }
            return res;
        }

        private boolean inArea(int i, int j, int n, int m) {
            return i >= 0 && i < n && j >= 0 && j < m;
        }

    }


    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

}
