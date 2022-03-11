## 图问题之01矩阵[Hornet]





![bee-161866_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\map\图问题之01矩阵[Hornet].assets\bee-161866_640.png)



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



### 方法1：BFS

#### 思路

- 此题难点是记录距离，但是这里的距离的是$queue$的层数，一层距离为1, 可以想象成每次以一个坐标为中心，上下左右如水波纹一样的扩散叠加，每层叠加，准备一个初始化为0的变量$dist$,每一层结束就+1
- 当发现当前坐标是1的时候，赋值$dist$
- 准备一个$visited$数组，记录该点坐标有没有被访问过

#### 举例转这个case

![script](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dfs_bfs\map\图问题之01矩阵[Hornet].assets\script.jpg)

```java
    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//四个方向
    int m, n;

    public int[][] updateMatrix(int[][] matrix) {

        m = matrix.length;
        n = matrix[0].length;

        int[][] res = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    //初始化时，queue有多个源点
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //当前层数的所有节点都需要拿出来
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                //当前的curr坐标
                int currX = curr[0], currY = curr[1];
                if (matrix[currX][currY] == 1) res[currX][currY] = dist; //赋值
                for (int[] d : directions) {
                    //由单当前的curr坐标生出的next坐标，上下左右四个方向
                    int nextX = currX + d[0];
                    int nextY = currY + d[1];
                    //坐标越界或是已经放进queue中，跳过
                    if (!inArea(nextX, nextY) || visited[nextX][nextY]) {
                        continue;
                    }
                    //放进queue中且标记被放进去
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
            dist++;//每一层后+1
        }
        return res;
    }
```







