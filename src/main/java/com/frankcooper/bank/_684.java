package com.frankcooper.bank;

/**
 * @Date 2020/8/12
 * @Author Frank Cooper
 * @Description
 */
public class _684 {


    int[] parents;

    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) return new int[]{0, 0};
        int n = edges.length + 1; //注意此处下标多放一个
        init(n);
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            if ((!union(x, y))) {//第二次出现了联通的边时，表示已经找到了
                return edge;
            }
        }
        return new int[]{0, 0};
    }

    //初始化parents
    public void init(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

//    //递归版路径压缩，找到x的根节点
//    public int find(int x) {
//        if (x != parents[x]) {
//            parents[x] = find(parents[x]);
//        }
//        return parents[x];
//    }
    //非递归版路径压缩
    public int find(int x) {
        int rootX = x;
        while (rootX != parents[rootX]) {
            rootX = parents[rootX];
        }
        int curr = x;
        while (curr != rootX) {
            int next = parents[curr];
            parents[curr] = rootX;
            curr = next;
        }
        return rootX;
    }


    //改写union方法，第一次当x与y没有联通时，将其设置联通关系，返回ture
    //第二次x和y的跟节点发现一致时，他们已经联通了，返回false
    public boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) return false;
        parents[rootX] = rootY;
        return true;
    }
}
