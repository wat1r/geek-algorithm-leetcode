package com.frankcooper.lintcode;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @Date 2020/8/18
 * @Author Frank Cooper
 * @Description
 */
public class _804 {

    static _804 handler = new _804();
    _2nd h = new _2nd();


    public static void main(String[] args) {

        int[][] grid = {{1, 1, 0, 0, 0}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 1, 1}};
//        handler.numDistinctIslands2(grid);
        grid = new int[][]{{1, 1, 1, 1}, {1, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 1, 1}, {1, 1, 0, 1}};


        handler.two();

    }


    private void two() {
        int[][] grid = new int[][]{{1, 1, 1, 1}, {1, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 1, 1}, {1, 1, 0, 1}};
        new _2nd().numDistinctIslands2(grid);
    }


    class Base {
        public int numDistinctIslands2(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int m = grid.length, n = grid[0].length;
            Set<String> res = new HashSet<>();

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 1) {
                        List<Point> island = new ArrayList<>();
                        dfs(grid, i, j, island);
                        res.add(getUnique(island));
                    }
                }
            }

            return res.size();
        }

        private void dfs(int[][] grid, int x, int y, List<Point> island) {
            int m = grid.length, n = grid[0].length;

            island.add(new Point(x, y));
            grid[x][y] = 0;

            int[] dirs = {-1, 0, 1, 0, -1};
            for (int i = 0; i < 4; ++i) {
                int _x = x + dirs[i], _y = y + dirs[i + 1];
                if (_x >= 0 && _x < m && _y >= 0 && _y < n && grid[_x][_y] == 1) {
                    dfs(grid, _x, _y, island);
                }
            }
        }


        private String getUnique(List<Point> island) {
            List<String> sameIslands = new ArrayList<>();

            int[][] trans = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

            for (int i = 0; i < 4; ++i) {
                List<Point> l1 = new ArrayList<>(), l2 = new ArrayList<>();

                for (Point point : island) {
                    int x = point.x, y = point.y;
                    l1.add(new Point(x * trans[i][0], y * trans[i][1]));
                    l2.add(new Point(y * trans[i][0], x * trans[i][1]));
                }
                sameIslands.add(getStr(l1));
                sameIslands.add(getStr(l2));
            }

            Collections.sort(sameIslands);
            System.out.println(JSON.toJSONString(sameIslands));
            return sameIslands.get(0);
        }

        private String getStr(List<Point> island) {

//        System.out.println(JSON.toJSONString(island));

            island.sort((a, b) -> {
                if (a.x != b.x) {
                    return a.x - b.x;
                }
                return a.y - b.y;
            });

            StringBuilder sb = new StringBuilder();
            int x = island.get(0).x, y = island.get(0).y;

            for (Point point : island) {
                sb.append(point.x - x).append(",").append(point.y - y).append(" ");
            }
//        System.out.println(sb.toString());
            return sb.toString();
        }
    }


    class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    class _1st {

        int m, n;
        int[][] actions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        public int numDistinctIslands2(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
            Set<String> set = new HashSet<>();
            m = grid.length;
            n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        List<Point> islands = new ArrayList<>();
                        dfs(grid, i, j, islands);
                        set.add(getProperIsland(islands));
                    }
                }
            }
            return set.size();
        }

        private void dfs(int[][] grid, int i, int j, List<Point> islands) {
            islands.add(new Point(i, j));
            grid[i][j] = 2;
            int[] dir = {-1, 0, 1, 0, -1};
            for (int k = 0; k < 4; ++k) {
                int nextI = i + dir[k];
                int nextJ = j + dir[k + 1];
                if (!inArea(nextI, nextJ) || grid[nextI][nextJ] != 1) continue;
                dfs(grid, nextI, nextJ, islands);
            }

        }

        //判断是否在grid的范围内，是否越界
        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }


        /**
         * 获取目标的island，只拿一个
         * @param islands 候选的island列表
         * @return
         */
        private String getProperIsland(List<Point> islands) {
            List<String> sameIslands = new ArrayList<>();
            for (int[] action : actions) {
                List<Point> originList = new ArrayList<>();//原始的list
                List<Point> flatList = new ArrayList<>();//折叠的list
                for (Point point : islands) {
                    int nextX = point.x, nextY = point.y;
                    originList.add(new Point(nextX * action[0], nextY * action[1]));
                    flatList.add(new Point(nextY * action[0], nextX * action[1]));
                }
                sameIslands.add(processList(originList));
                sameIslands.add(processList(flatList));
            }
            Collections.sort(sameIslands);
            return sameIslands.get(0);
        }

        /**
         * 处理当前的list，排序后生成路径
         * @param list
         * @return
         */
        private String processList(List<Point> list) {
            list.sort((o1, o2) -> {
                if (o1.x != o2.x) return o1.x - o2.x;
                return o1.y - o2.y;
            });
            Point base = list.get(0);
            int baseX = base.x, baseY = base.y;
            StringBuilder res = new StringBuilder();
            for (Point point : list) {
                res.append(point.x - baseX).append(",").append(point.y - baseY).append(" ");
            }
            return res.toString();
        }
    }


    class _2nd {


        int m, n;
        int[][] actions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int[] dir = {-1, 0, 1, 0, -1};

        public int numDistinctIslands2(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
            Set<String> set = new HashSet<>();
            m = grid.length;
            n = grid[0].length;
            List<Point> islands = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) bfs(grid, i, j, islands);
                    if (islands.size() > 0) {
                        set.add(getProperIsland(islands));
                        islands.clear();
                    }
                }
            }
            return set.size();
        }

        private void bfs(int[][] grid, int i, int j, List<Point> islands) {
            Queue<Point> queue = new LinkedList<>();
            Point root = new Point(i, j);
            queue.add(root);
            islands.add(root);
            grid[i][j] = 2;
            while (!queue.isEmpty()) {
                Point curr = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nextI = curr.x + dir[k];
                    int nextJ = curr.y + dir[k + 1];
                    if (!inArea(nextI, nextJ) || grid[nextI][nextJ] != 1) continue;
                    grid[nextI][nextJ] = 2;
                    Point next = new Point(nextI, nextJ);
                    islands.add(next);
                    queue.add(next);
                }
            }
        }

        private boolean inArea(int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }


        private String getProperIsland(List<Point> islands) {
            List<String> sameIslands = new ArrayList<>();
            for (int[] action : actions) {
                List<Point> originList = new ArrayList<>();
                List<Point> flatList = new ArrayList<>();
                for (Point point : islands) {
                    int nextX = point.x, nextY = point.y;
                    originList.add(new Point(nextX * action[0], nextY * action[1]));
                    flatList.add(new Point(nextY * action[0], nextX * action[1]));
                }
                sameIslands.add(processList(originList));
                sameIslands.add(processList(flatList));
            }
            Collections.sort(sameIslands);
            return sameIslands.get(0);
        }


        private String processList(List<Point> list) {
            list.sort((o1, o2) -> {
                if (o1.x != o2.x) return o1.x - o2.x;
                return o1.y - o2.y;
            });
            Point base = list.get(0);
            int baseX = base.x, baseY = base.y;
            StringBuilder res = new StringBuilder();
            for (Point point : list) {
                res.append(point.x - baseX).append(",").append(point.y - baseY).append(" ");
            }
            return res.toString();
        }

    }


}
