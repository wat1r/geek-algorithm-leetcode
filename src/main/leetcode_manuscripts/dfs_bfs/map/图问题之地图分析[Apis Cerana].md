## 图问题之地图分析[Apis Cerana]

![bumblebee-151708_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\map\图问题之地图分析[Apis Cerana].assets\bumblebee-151708_640.png)



![image-20200826100336963](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\map\图问题之地图分析[Apis Cerana].assets\image-20200826100336963.png)

### 0.2.BFS模板

```python
def bfs(){
    q.push(head);//一般为q这种优先队列来处理bfs问题
    level;//记录层数
    while(!q.empty()){
		size = q.size;
        for i in range(size):
                temp=q.front;//弹出元素
                q.pop(); 
                if(temp为目标状态)输出解 
                if(temp不合法)continue;
                if(temp合法)q.push(temp+Δ);
        level++;//for loop 结束后层数扩张一层
    }
}
一般也会设置一些visit[] 来记录元素访问与否，做剪枝
```





> 这一题类似01矩阵

### 方法1：BFS（记录层数）

- 需要做一次额外判断，当$grid$全是陆地或者全是海洋时，返回-1
- $bfs$的循环要找到海洋的点的坐标
- 初始化时有多个出发源点，本题是为陆地的坐标点

#### 如下图的$case$

![image-20200826093500697](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\map\图问题之地图分析[Apis Cerana].assets\image-20200826093500697.png)

- 初始化的$queue$装入的是陆地（也就是值为1）的坐标，

```java
public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) queue.add(new int[]{i, j});
            }
        }
        //全为0或者全为1的时候返回-1
        if (queue.isEmpty() || queue.size() == m * n) return -1;
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int level = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int currX = curr[0], currY = curr[1];
                for (int[] d : dirs) {
                    int nextX = currX + d[0], nextY = currY + d[1];
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] == 1) continue;
                    queue.offer(new int[]{nextX, nextY});
                    grid[nextX][nextY] = 1;
                }
            }
            level++;
        }
        return level;
    }
```

### 方法2：BFS（不记录层数）

- 记录最大值，原地修改$grid$

```java
public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) queue.add(new int[]{i, j});
            }
        }
        //全为0或者全为1的时候返回-1
        if (queue.isEmpty() || queue.size() == m * n) return -1;
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int res = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int currX = curr[0], currY = curr[1];
                for (int[] d : dirs) {
                    int nextX = currX + d[0], nextY = currY + d[1];
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] != 0) continue;
                    queue.offer(new int[]{nextX, nextY});
                    grid[nextX][nextY] = grid[currX][currY] + 1;
                    res = Math.max(res, grid[nextX][nextY] - 1);
                }
            }
        }
        return res;
    }

```











































