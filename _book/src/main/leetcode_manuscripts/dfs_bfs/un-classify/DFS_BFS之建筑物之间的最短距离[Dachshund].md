## DFS_BFS之建筑物之间的最短距离[Dachshund]

![dachshund-3726491_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\DFS_BFS之建筑物之间的最短距离[Dachshund].assets\dachshund-3726491_640.jpg)

![image-20200926163120257](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\un-classify\DFS_BFS之建筑物之间的最短距离[Dachshund].assets\image-20200926163120257.png)



### 方法1:BFS

```java
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

```

