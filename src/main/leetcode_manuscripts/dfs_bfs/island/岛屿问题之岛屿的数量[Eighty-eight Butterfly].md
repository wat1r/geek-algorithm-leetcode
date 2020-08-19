## 岛屿问题之岛屿的数量[Eighty-eight Butterfly]

![red-4500348_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之岛屿的数量[Eighty-eight Butterfly].assets\red-4500348_640.jpg)

### 0.前言

#### DFS模板

```python
def dfs( grid, int i, int j) {
    //不在grid范围内或者不为1（陆地），返回   
    if !inArea(grid, r, c) or grid[i][j]!=1: return
    grid[i][j] =2 //标记已被访问过
    // 访问上、下、左、右四个方向
    for d in directions:
        dfs(grid,i+d[0],j+d[1])
}
```

#### BFS模板

```python
def bfs(grid, i, j):
    queue = [[i,j]] # 定义queue
    while queue: 
        curr_i, curr_j = queue.pop(0) # 弹出栈顶
        for d in self.directions:
            next_i = curr_i + d[0] 
            next_j = curr_j + d[1] 
            if 0 <= next_i < m and 0 <= next_j < n and grid[next_i][next_j] == '1': # 访问上、下、左、右四个方向
                grid[next_i][next_j] = '2' # 标记已经访问过
                queue.append((next_i, next_j))
```

### 1.岛屿的数量

![image-20200812215814872](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\island\岛屿问题之岛屿的数量[Eighty-eight Butterfly].assets\image-20200812215814872.png)

#### 方法1：DFS

```python
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def numIslands(self, grid: List[List[str]]) -> int:
        def dfs(grid, i, j):
            if not i >= 0 or not i < m or not j >= 0 or not j < n or grid[i][j] != '1': # 退出条件，当不在grid中或者不是陆地（1）
                return
            grid[i][j] = '2' # 设置其被访问过
            for d in self.directions:
                next_i = i + d[0] # 下个节点的i
                next_j = j + d[1] # 下个节点的j
                print(next_i, next_j)
                dfs(grid, next_i, next_j)
        if not grid or len(grid) == 0 or len(grid[0]) == 0: return 0
        res = 0
        m = len(grid)
        n = len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    dfs(grid, i, j)
                    res += 1 # 一次dfs结束后+1
        return res
```

#### 方法2：BFS

```python
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def numIslands(self, grid: List[List[str]]) -> int:
        def bfs(grid, i, j):
            queue = collections.deque([(i, j)])
            while queue:
                curr_i, curr_j = queue.popleft()
                for d in self.directions:
                    next_i = curr_i + d[0]
                    next_j = curr_j + d[1]
                    if 0 <= next_i < m and 0 <= next_j < n and grid[next_i][next_j] == '1':
                        grid[next_i][next_j] = '2'
                        queue.append((next_i, next_j))
        if not grid or len(grid) == 0 or len(grid[0]) == 0: return 0
        res = 0
        m = len(grid)
        n = len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] != '1': continue
                bfs(grid, i, j)
                res += 1
        return res
```

#### 方法3：并查集

- 找出1的个数，count，如果两个1不是一个联通区域，但是相邻，联通之，$count--$

```python
    class UnionFind:
        parents = []
        ranks = []
        count = 0

        def __init__(self, m, n, grid):
            size = m * n
            self.parents = [i for i in range(size)]
            self.ranks = [0 for _ in range(size)]
            for i in range(m):
                for j in range(n):
                    if grid[i][j] == '1': self.count += 1

        def find(self, x):
            if x != self.parents[x]:
                self.parents[x] = self.find(self.parents[x])
            return self.parents[x]

        def union(self, x, y):
            root_x = self.find(x)
            root_y = self.find(y)
            if root_x == root_y: return
            if self.ranks[root_x] > self.ranks[root_y]: self.parents[root_y] = root_x
            if self.ranks[root_x] < self.ranks[root_y]: self.parents[root_x] = root_y
            if self.ranks[root_x] == self.ranks[root_y]:
                self.parents[root_x] = root_y
                self.ranks[root_x] += 1
            self.count -= 1

    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def numIslands(self, grid: List[List[str]]) -> int:
        if not grid or len(grid) == 0 or len(grid[0]) == 0: return 0
        m = len(grid)
        n = len(grid[0])
        uf = self.UnionFind(m, n, grid)
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    grid[i][j] = '2'
                    for d in self.directions:
                        next_i = i + d[0]
                        next_j = j + d[1]
                        if 0 <= next_i < m and 0 <= next_j < n and grid[next_i][next_j] == '1':
                            uf.union(i * n + j, next_i * n + next_j)

        return uf.count
```





### 2.岛屿的个数II

```
给定 n, m, 分别代表一个二维矩阵的行数和列数, 并给定一个大小为 k 的二元数组A. 初始二维矩阵全0. 二元数组A内的k个元素代表k次操作, 设第i个元素为 (A[i].x, A[i].y), 表示把二维矩阵中下标为A[i].x行A[i].y列的元素由海洋变为岛屿. 问在每次操作之后, 二维矩阵中岛屿的数量. 你需要返回一个大小为k的数组.
```

#### 方法1：并查集

##### 思路

- 准备一个并查集，修改$union$的逻辑：
  - 如果同在一个区域，共根节点的话，

```java
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
```















