## 一文掌握Floyd算法



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



#### 模板代码

```java
        /*
         * floyd最短路径。
         * 即，统计图中各个顶点间的最短路径。
         *
         * 参数说明：
         *     path -- 路径。path[i][j]=k表示，"顶点i"到"顶点j"的最短路径会经过顶点k。
         *     dist -- 长度数组。即，dist[i][j]=sum表示，"顶点i"到"顶点j"的最短路径的长度是sum。
         */
        public void floyd(int[][] path, int[][] dist) {

            // 初始化  mVexs // 顶点集合
            //mMatrix 邻接矩阵 i =j 时为0 初始化时， 没有路径时是INF 其他位置是u->v的权值
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {
                    dist[i][j] = mMatrix[i][j];    // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
                    path[i][j] = j;                // "顶点i"到"顶点j"的最短路径是经过顶点j。
                }
            }

            // 计算最短路径
            for (int k = 0; k < mVexs.length; k++) {
                for (int i = 0; i < mVexs.length; i++) {
                    for (int j = 0; j < mVexs.length; j++) {
                        // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                        int tmp = (dist[i][k] == INF || dist[k][j] == INF) ? INF : (dist[i][k] + dist[k][j]);
                        if (dist[i][j] > tmp) {
                            // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                            dist[i][j] = tmp;
                            // "i到j最短路径"对应的路径，经过k
                            path[i][j] = path[i][k];
                        }
                    }
                }
            }

            // 打印floyd最短路径的结果
            System.out.printf("floyd: \n");
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++)
                    System.out.printf("%2d  ", dist[i][j]);
                System.out.printf("\n");
            }
        }

    }
```



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
