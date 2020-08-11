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

