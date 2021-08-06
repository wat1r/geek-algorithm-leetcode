package com.frankcooper.graph;

public class UnionFind {

    static class Graph {
        int V, E;//顶点与边的数量
        Edge[] edges;//边

        //初始化
        public Graph(int V, int E) {
            this.V = V;
            this.E = E;
            edges = new Edge[V];
            for (int i = 0; i < E; i++) {
                edges[i] = new Edge();
            }
        }

        class Edge {
            int u, v;//边的两个点
        }

        class Subset {
            int parent;
            int rank;
        }

        //路径压缩找到i的子集中的根节点
        int find(Subset[] subsets, int i) {
            if (subsets[i].parent != i) {
                subsets[i].parent = find(subsets, subsets[i].parent);
            }
            return subsets[i].parent;
        }

        void union(Subset[] subsets, int x, int y) {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);
            if (subsets[xroot].rank < subsets[yroot].rank) subsets[xroot].parent = yroot;
            else if (subsets[xroot].rank > subsets[yroot].rank) subsets[yroot].parent = xroot;
            else {
                subsets[xroot].parent = yroot;
                subsets[yroot].rank++;
            }
        }

        int isCycle(Graph graph) {
            //初始化subsets
            int V = graph.V, E = graph.E;
            Subset[] subsets = new Subset[V];
            for (int v = 0; v < V; v++) {
                subsets[v] = new Subset();
                subsets[v].parent = v;//根节点是其自身
                subsets[v].rank = 0;//初始化时秩为0
            }
            //遍历每一条边
            for (int e = 0; e < E; e++) {//边的索引
                Edge edge = graph.edges[e];//当前的边
                int x = edge.u, y = edge.v;
                int xroot = find(subsets, x);
                int yroot = find(subsets, y);
                if (xroot == yroot) return 1;//出现根一样的，出现了环
                union(subsets, xroot, yroot);
            }
            return 0;
        }

        public static void main(String[] args) {
        /* Let us create the following graph
            0
            | \
            | \
            1-----2 */

            int V = 3, E = 3;
            Graph graph = new Graph(V, E);

            // add edge 0-1
            graph.edges[0].u = 0;
            graph.edges[0].v = 1;

            // add edge 1-2
            graph.edges[1].u = 1;
            graph.edges[1].v = 2;

            // add edge 0-2
            graph.edges[2].u = 0;
            graph.edges[2].v = 2;

            if (graph.isCycle(graph) == 1)
                System.out.println("Graph contains cycle");
            else
                System.out.println("Graph doesn't contain cycle");
        }
    }
}
