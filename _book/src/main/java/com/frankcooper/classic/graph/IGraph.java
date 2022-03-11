package com.frankcooper.classic.graph;

/**
 * Created by FrankCooper
 * Date 2020/9/20 9:02
 * Description
 */
public interface IGraph<E> {
    public int getNumOfVertex();//获取顶点的个数

    boolean insertVex(E v);//插入顶点

    boolean deleteVex(E v);//删除顶点

    int indexOfVex(E v);//定位顶点的位置

    E valueOfVex(int v);// 定位指定位置的顶点

    boolean insertEdge(int v1, int v2, int weight);//插入边

    boolean deleteEdge(int v1, int v2);//删除边

    int getEdge(int v1, int v2);//查找边

    String depthFirstSearch(int v);//深度优先搜索遍历

    String breadFirstSearch(int v);//广度优先搜索遍历

    public int[] dijkstra(int v);//查找源点到其它顶点的路径

}
