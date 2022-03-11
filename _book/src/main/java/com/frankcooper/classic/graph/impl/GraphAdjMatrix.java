package com.frankcooper.classic.graph.impl;

import com.frankcooper.classic.graph.IGraph;

import java.lang.reflect.Array;

/**
 * Created by FrankCooper
 * Date 2020/9/20 9:03
 * Description
 */

public class GraphAdjMatrix<E> implements IGraph<E> {
    private E[] vexs;// 存储图的顶点的一维数组
    private int[][] edges;// 存储图的边的二维数组
    private int numOfVexs;// 顶点的实际数量
    private int maxNumOfVexs;// 顶点的最大数量
    private boolean[] visited;// 判断顶点是否被访问过

    @SuppressWarnings("unchecked")
    public GraphAdjMatrix(int maxNumOfVexs, Class<E> type) {
        this.maxNumOfVexs = maxNumOfVexs;
        edges = new int[maxNumOfVexs][maxNumOfVexs];
        vexs = (E[]) Array.newInstance(type, maxNumOfVexs);
    }

    // 得到顶点的数目
    public int getNumOfVertex() {
        return numOfVexs;
    }

    // 插入顶点
    public boolean insertVex(E v) {
        if (numOfVexs >= maxNumOfVexs)
            return false;
        vexs[numOfVexs++] = v;
        return true;
    }

    // 删除顶点
    public boolean deleteVex(E v) {
        for (int i = 0; i < numOfVexs; i++) {
            if (vexs[i].equals(v)) {
                for (int j = i; j < numOfVexs - 1; j++) {
                    vexs[j] = vexs[j + 1];
                }
                vexs[numOfVexs - 1] = null;
                for (int col = i; col < numOfVexs - 1; col++) {
                    for (int row = 0; row < numOfVexs; row++) {
                        edges[col][row] = edges[col + 1][row];
                    }
                }
                for (int row = i; row < numOfVexs - 1; row++) {
                    for (int col = 0; col < numOfVexs; col++) {
                        edges[col][row] = edges[col][row + 1];
                    }
                }
                numOfVexs--;
                return true;
            }
        }
        return false;
    }

    // 定位顶点的位置
    public int indexOfVex(E v) {
        for (int i = 0; i < numOfVexs; i++) {
            if (vexs[i].equals(v)) {
                return i;
            }
        }
        return -1;
    }

    // 定位指定位置的顶点
    public E valueOfVex(int v) {
        if (v < 0 || v >= numOfVexs)
            return null;
        return vexs[v];
    }

    // 插入边
    public boolean insertEdge(int v1, int v2, int weight) {
        if (v1 < 0 || v2 < 0 || v1 >= numOfVexs || v2 >= numOfVexs)
            throw new ArrayIndexOutOfBoundsException();
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        return true;
    }

    // 删除边
    public boolean deleteEdge(int v1, int v2) {
        if (v1 < 0 || v2 < 0 || v1 >= numOfVexs || v2 >= numOfVexs)
            throw new ArrayIndexOutOfBoundsException();
        edges[v1][v2] = 0;
        edges[v2][v1] = 0;
        return true;
    }

    // 查找边
    public int getEdge(int v1, int v2) {
        if (v1 < 0 || v2 < 0 || v1 >= numOfVexs || v2 >= numOfVexs)
            throw new ArrayIndexOutOfBoundsException();
        return edges[v1][v2];
    }

    // 深度优先搜索遍历
    public String depthFirstSearch(int v) {

        return null;
    }

    // 广度优先搜索遍历
    public String breadFirstSearch(int v) {
        return null;
    }

    // 实现Dijkstra算法
    public int[] dijkstra(int v) {
        return null;
    }

}
