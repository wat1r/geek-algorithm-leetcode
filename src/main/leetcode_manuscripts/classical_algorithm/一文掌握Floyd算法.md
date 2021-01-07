## 一文掌握Floyd算法

![halftone-744401_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\classical_algorithm\一文掌握Floyd算法.assets\halftone-744401_640.png)

#### 结构体

```c++
typedef struct struct_graph{
    char vexs[MAXN];
    int vexnum;//顶点数 
    int edgnum;//边数 
    int matirx[MAXN][MAXN];//邻接矩阵 
} Graph;
```

#### 伪代码

```c++
//这里是弗洛伊德算法的核心部分 
    //k为中间点 
    for(k = 0; k < G.vexnum; k++){
        //v为起点 
        for(v = 0 ; v < G.vexnum; v++){
            //w为终点 
            for(w =0; w < G.vexnum; w++){
                if(D[v][w] > (D[v][k] + D[k][w])){
                    D[v][w] = D[v][k] + D[k][w];//更新最小路径 
                    P[v][w] = P[v][k];//更新最小路径中间顶点 
                }
            }
        }
    }
```

#### 思路

- 用数组`dist[i][j]`来记录`i,j`之间的最短距离。初始化`dist[i][j]`若`i=j`则`dist[i][j]`=0
  - 若`i,j`之间有边连接则的$dist[i][j]$值为该边的权值，否则`dist[i][j]`的值为`INF`(无穷大)
- 对所有的`k`值从`1`到`n`,修正任意两点之间的最短距离,计算`dist[i][k]`+`dist[k][j]`的值，若小于`dist[i][j]`,则`dist[i][j]`= `dist[i][k]`+`dist[k][j]`，否则`dist[i][j]`的值不变

![floyd](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\classical_algorithm\一文掌握Floyd算法.assets\floyd.png)





### 模板代码

#### 核心代码

```java
		int N = 4;//顶点的个数
        int E = 8;//边的条数
        int[][] G = new int[N][N];//顶点之间最短距离的矩阵

        int[][] path = new int[N][N];//路径的矩阵
        int[][] dist = new int[N][N];//距离的矩阵
		/*
         * floyd最短路径。
         * 即，统计图中各个顶点间的最短路径。
         *
         * 参数说明：
         *     path -- 路径。path[i][j]=k表示，"顶点i"到"顶点j"的最短路径会经过顶点k。
         *     dist -- 长度数组。即，dist[i][j]=sum表示，"顶点i"到"顶点j"的最短路径的长度是sum。
         */
        public void floyd(int[][] path, int[][] dist) {
            // 初始化  N // 顶点集合
            //G 邻接矩阵 i =j 时为0 初始化时， 没有路径时是INF 其他位置是u->v的权值
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = G[i][j];    // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
//                    if (G[i][j] != INF && G[i][j] != 0) path[i][j] = j; // "顶点i"到"顶点j"的最短路径是经过顶点j。
                }
            }
            // 计算最短路径
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                        int tmp = (dist[i][k] == INF || dist[k][j] == INF) ? INF : (dist[i][k] + dist[k][j]);
                        if (dist[i][j] > tmp) {
                            // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                            dist[i][j] = tmp;
                            // "i到j最短路径"对应的路径，经过k
                            path[i][j] = k;
                        }
                    }
                }
            }
        }
```

#### 测试

```java
       
		public void testOne() {
            init();
            initEdge();
            PrintUtils.printMatrix(G, 10);
            PrintUtils.printMatrix(path, 1);
            floyd(path, dist);
            printHelper();
        }
		void init() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j) G[i][j] = INF;
                    path[i][j] = -1;
                }
            }
        }
		//初始化边，生成graph，使用的是图例的demo
        private void initEdge() {
            Edge[] edges = new Edge[E];
            edges[0] = new Edge(0, 1, 5);
            edges[1] = new Edge(0, 3, 7);
            edges[2] = new Edge(1, 2, 4);
            edges[3] = new Edge(1, 3, 2);
            edges[4] = new Edge(2, 0, 3);
            edges[5] = new Edge(2, 1, 3);
            edges[6] = new Edge(2, 3, 2);
            edges[7] = new Edge(3, 2, 1);
            for (int i = 0; i < E; i++) {
                G[edges[i].u][edges[i].v] = edges[i].w;
            }
        }
		//打印
		private void printHelper() {
            // 打印floyd最短路径的结果
            System.out.printf("floyd dist: \n");
            PrintUtils.printMatrix(dist, 2);
            System.out.printf("floyd path: \n");
            PrintUtils.printMatrix(path, 2);
            int u = 3, v = 0;
            System.out.printf("%d--->", u);
            printPath(path, u, v);
            System.out.printf("%d\n", v);
        }
public class PrintUtils {
    public static void printMatrix(int[][] matrix, int d) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                System.out.print(String.format("%" + d + "s", matrix[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    } 
}
```

#### 番外

```java
    int[][] path;

    /**
     * 递归求i-->j的路径
     *
     * @param i 起点
     * @param j 终点
     */
    public void findPath(int i, int j) {
        int k = path[i][j];
        if (k == -1) return; //i->j没有直达路径的时候，为-1
        findPath(i, k);//i->k
        System.out.printf("%d ", k);
        findPath(k, j);//k->j
    }
```

### Reference

- 
   https://www.cnblogs.com/skywang12345/p/3711532.html?utm_source=tuicool&utm_medium=referral
