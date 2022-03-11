package com.frankcooper.classic.prim;

import java.util.*;

/**
 * @Date 2020/9/14
 * @Author Frank Cooper
 * @Description
 */
public class PrimMST1ST {


    public static void main(String[] args) {
//        mianTest();
        testOne();
    }


    public static void mianTest() {
        Scanner scanner = new Scanner(System.in);
        int V = scanner.nextInt();
        int E = scanner.nextInt();
        Map<Integer, List<Pair>> graph = new HashMap<>();
        for (int i = 0; i < E; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int weigh = scanner.nextInt();
            graph.putIfAbsent(start, new ArrayList<>());
            graph.putIfAbsent(end, new ArrayList<>());
//            graph.get(start).add(new Pair(weigh, end));
//            graph.get(end).add(new Pair(weigh, start));
        }
        System.out.println(prim(1, graph));
    }


    static class Pair {
        int start;//from
        int end;//to
        int weigh;//weigh

        public Pair(int start, int end, int weigh) {
            this.start = start;
            this.end = end;
            this.weigh = weigh;
        }
    }

    static int V = 7;//顶点或者端点的个数

    public static void testOne() {
        int E = 10;//端点之间形成的边的个数
        int[][] edges = {{0, 1, 4},//边的详细信息 from to weigh
                {0, 5, 8},
                {1, 2, 8},
                {1, 5, 11},
                {2, 3, 3},
                {2, 6, 2},
                {3, 4, 3},
                {4, 5, 8},
                {4, 6, 6},
                {5, 6, 7},
        };
        //构造graph
        Map<Integer, List<Pair>> graph = new HashMap<>();
        for (int i = 0; i < E; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            int weigh = edges[i][2];
            graph.putIfAbsent(start, new ArrayList<>());
            graph.putIfAbsent(end, new ArrayList<>());
            graph.get(start).add(new Pair(start, end, weigh));
            graph.get(end).add(new Pair(end, start, weigh));
        }
        int minCost = prim(0, graph);
        System.out.println(minCost);

        for (Pair pair : mst) {
            System.out.println(String.format("from:%d,to:%d,weigh:%d", pair.start,
                    pair.end, pair.weigh));
        }


    }

    //最小生成树
    public static List<Pair> mst = new ArrayList<>();


    public static int prim(int start, Map<Integer, List<Pair>> graph) {
        //记录每个端点是否被访问过
        boolean[] visited = new boolean[V];
        //按权值从小到大的堆
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((o1, o2) -> o1.weigh - o2.weigh);
        //开始的点，触发堆的轮转
        minHeap.offer(new Pair(-1, start, 0));
        //最小路径和
        int minCost = 0;
        while (!minHeap.isEmpty()) {
            //弹出堆顶的最小权值在Pair
            Pair curr = minHeap.poll();
            //拿到其端点
            start = curr.end;
            //端点被访问过，返回
            if (visited[start]) continue;
            if (curr.start != -1) mst.add(curr);
            //标记
            visited[start] = true;
            //更新权值
            minCost += curr.weigh;
            //遍历端点的候选端点，如果候选端点被访问过，不需要加入到堆中
            List<Pair> candidates = graph.get(start);
            for (int i = 0; i < candidates.size(); ++i) {
                if (!visited[candidates.get(i).end]) {
                    minHeap.offer(candidates.get(i));
                }
            }
        }
        return minCost;
    }


}
