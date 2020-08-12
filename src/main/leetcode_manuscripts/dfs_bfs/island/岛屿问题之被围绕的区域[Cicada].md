## 岛屿问题之被围绕的区域[Cicada]

![magicicada-2307575_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之被围绕的区域[Cicada].assets\magicicada-2307575_640.png)



![image-20200811091744468](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之被围绕的区域[Cicada].assets\image-20200811091744468.png)



### 方法1：DFS

![image-20200811093508100](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之被围绕的区域[Cicada].assets\image-20200811093508100.png)

#### 思路

要找出**绿色填充块**这样的$O$,但是**黄色填充块**和**红色填充块**这样的$O$不能被找出来，所以做个区分

- 将**黄色填充块**和**红色填充块**的$O$设置成一个标记符号`#`
- 将**绿色填充块**保持不变

待打标记结束后，$O$变成$X$,也就是绿色填充块设置为$X$，`#`设置为$O$，进行复原，也就是黄色填充块和红色填充块复原

#### 关键点

代码实现上注意两点

- 从边缘处且$O$处进入
- 当越界或者不为$O$时（为$X$或者`#`），返回

```java
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int m, n;

        public void solve(char[][] board) {
            if (board == null || board.length == 0) return;
            m = board.length;//行
            n = board[0].length;//列
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O') {
                        dfs(board, i, j);
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    }
                }
            }
        }
        private void dfs(char[][] board, int i, int j) {
            if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || board[i][j] != 'O') {
                return;
            }
            board[i][j] = '#';
            for (int[] direction : directions) {
                dfs(board, i + direction[0], j + direction[1]);
            }
        }
```







### 方法2：并查集





```java
 static class UnionFind {
            int[] parent;

            public UnionFind(int initValue) {
                parent = new int[initValue];
                for (int i = 0; i < initValue; i++) {
                    parent[i] = -1;
                }
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX != rootY) {
                    parent[rootX] = rootY;
                }
            }

            public int find(int x) {
                int rootX = x;
                while (parent[rootX] != -1) {
                    rootX = parent[rootX];
                }
                return rootX;
            }
        }
```



#### 主题代码

```java
int m, n;
int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

public void solve(char[][] board) {
    if (board == null || board.length == 0) return;
    System.out.println(JSON.toJSONString(board));
    m = board.length;
    n = board[0].length;
    int initValue = m * n + 1;
    UnionFind unionFind = new UnionFind(initValue);
    int dummy = m * n;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (board[i][j] == 'O') {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    unionFind.union(node(i, j), dummy);
                } else {
                    for (int k = 0; k < directions.length; k++) {
                        int nextI = i + directions[k][0];
                        int nextJ = j + directions[k][1];
                        //                                System.out.println(String.format("%d:%d", nextI, nextJ));
                        if ((nextI > 0  || nextI < m  || nextJ > 0  || nextJ < n) && board[nextI][nextJ] == 'O') {
                            unionFind.union(node(i, j), node(nextI, nextJ));
                        }
                        //                                if (i + directions[i][0] > 0&&board[i])
                    }
                    //                            if (i > 0 && board[i - 1][j] == 'O') {
                    //                                unionFind.union(node(i, j), node(i - 1, j));
                    //                            }
                    //                            if (i < m - 1 && board[i + 1][j] == 'O') {
                    //                                unionFind.union(node(i, j), node(i + 1, j));
                    //                            }
                    //                            if (j > 0 && board[i][j - 1] == 'O') {
                    //                                unionFind.union(node(i, j), node(i, j - 1));
                    //                            }
                    //                            if (j < n - 1 && board[i][j + 1] == 'O') {
                    //                                unionFind.union(node(i, j), node(i, j + 1));
                    //                            }
                }
            }
        }
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (unionFind.find(node(i, j)) == unionFind.find(dummy)) {
                board[i][j] = 'O';
            } else {
                board[i][j] = 'X';
            }
        }
    }
    System.out.println(JSON.toJSONString(board));
}

public int node(int i, int j) {
    return i * n + j;
}
```









### 方法3：BFS



```java
 class Position {
            int i, j;

            public Position(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }

        int m, n;

        public void solve(char[][] board) {
            System.out.println(JSON.toJSONString(board));
            if (board == null || board.length == 0) return;
            m = board.length;
            n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 1) {
                        System.out.println("ee");
                    }
                    if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O') {
                        bfs(board, i, j);
                    }
                }
            }
            System.out.println(JSON.toJSONString(board));

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    }
                }
            }
            System.out.println(JSON.toJSONString(board));

        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        private void bfs(char[][] board, int i, int j) {
            Queue<Position> queue = new LinkedList<>();
            queue.offer(new Position(i, j));
            board[i][j] = '#';
            while (!queue.isEmpty()) {
                Position curr = queue.poll();
                for (int k = 0; k < directions.length; k++) {
                    int nextI = curr.i + directions[k][0];
                    int nextJ = curr.j + directions[k][1];
                    System.out.println(nextI + ":" + nextJ);
                    if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) continue;
                    if (board[nextI][nextJ] == 'O') {
                        queue.offer(new Position(nextI, nextJ));
                        board[nextI][nextJ] = '#';
                    }
                }
            }
        }
```





















### Reference

https://blog.csdn.net/jc514984625/article/details/52049000?utm_source=blogxgwz6