## 并查集应用之冗余链接II[German Shepherd Dog]

![dog-3545138_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\union_find\并查集应用之冗余链接II[German Shepherd Dog].assets\dog-3545138_640.jpg)

![image-20200917210138832](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\union_find\并查集应用之冗余链接II[German Shepherd Dog].assets\image-20200917210138832.png)



> 引自weiwei大佬

有根树指满足以下条件的 有向图。该树 只有 一个根结点，所有其他结点都是该根结点的后继 。每一个结点 只有 一个父结点，除了根结点没有父结点。

有根树的特点：

- 只有唯一的一个入度为 0 的结点，它是根结点；
- 不是根结点的其它所有的结点入度为 1；
- 不可能存在入度为 2的结点。





![image-20200917210122633](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\union_find\并查集应用之冗余链接II[German Shepherd Dog].assets\image-20200917210122633.png)

![image-20200917210459836](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\union_find\并查集应用之冗余链接II[German Shepherd Dog].assets\image-20200917210459836.png)

#### 思路

- 处理好入度
- 先找到入度为2的节点（如果有的话），尝试着删除这条边后，可会成环
- 找到入度为1的节点，尝试着删除这条边后，可会成环，原因参见上图的case A 与case A‘ 对比
- 题意**返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案**，所以需要倒序遍历

```java
  public int[] findRedundantDirectedConnection(int[][] edges) {

        int n = edges.length;
        //预处理，准备当前节点的入度
        int[] indegrees = new int[n + 1];
        for (int[] edge : edges) {
            indegrees[edge[1]]++;
        }
        //入度为2
        for (int i = n - 1; i >= 0; i--) {
            if (indegrees[edges[i][1]] == 2) {
                if (!hasCycle(edges, n, i)) {
                    return edges[i];
                }
            }
        }
        //入度为1
        for (int i = n - 1; i >= 0; i--) {
            if (indegrees[edges[i][1]] == 1) {
                if (!hasCycle(edges, n, i)) {
                    return edges[i];
                }
            }
        }
        return null;
    }
    /**
     * 判断移除当前的idx，也就是edges[idx]这条边后，有没有环出现
     *
     * @param edges
     * @param n
     * @param idx
     * @return
     */
    private boolean hasCycle(int[][] edges, int n, int idx) {
        UnionFind uf = new UnionFind(n + 1);
        for (int i = 0; i < n; ++i) {
            //跳过的方式是，该索引节点不加入到联通分量中，相当于remove这条edge
            if (i == idx) continue;
            //开始合并，如果两个点已经是联通的，union他们，会返回false
            //这时候第二次联通会失败，说明两个点已经在一个联通分量了，这时候说明有环，返回true
            if (!uf.unoin(edges[i][0], edges[i][1])) {
                return true;
            }
        }
        return false;
    }

```

#### $UnionFind$

```java
   class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) parents[i] = i;
        }


        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        /**
         * 是否合并成功，如果根节点相同，说明已经联通过了，不需要再次联通，返回false
         * 如果根节点不同，开始联通，也就是能联通，返回true
         *
         * @param x
         * @param y
         * @return
         */
        public boolean unoin(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return false;
            if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
            if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
            if (ranks[rootX] == ranks[rootY]) {
                parents[rootY] = rootX;
                ranks[rootY]++;
            }
            return true;
        }
    }
```



### Reference

- [原文地址：树边，前向边，后向边，横叉边](https://www.cnblogs.com/gongpixin/p/5003049.html)