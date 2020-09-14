package com.frankcooper.other.prim;

import java.util.*;

/**
 * @Date 2020/9/14
 * @Author Frank Cooper
 * @Description
 */
public class PrimMST1ST {


    static class Pair {
        int weigh;
        int end;

        public Pair(int weigh, int end) {
            this.weigh = weigh;
            this.end = end;
        }
    }


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
            graph.get(start).add(new Pair(weigh, end));
            graph.get(end).add(new Pair(weigh, start));
        }
        System.out.println(prim(1, graph));
    }


    static int V = 7;

    public static void testOne() {


        int E = 10;
        int[][] edges = {{0, 1, 4},
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
        Map<Integer, List<Pair>> graph = new HashMap<>();
        for (int i = 0; i < E; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            int weigh = edges[i][2];
            graph.putIfAbsent(start, new ArrayList<>());
            graph.putIfAbsent(end, new ArrayList<>());
            graph.get(start).add(new Pair(weigh, end));
            graph.get(end).add(new Pair(weigh, start));
        }
        prim(0, graph);


    }


    public static int prim(int start, Map<Integer, List<Pair>> graph) {
        boolean[] visited = new boolean[V];
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((o1, o2) -> o1.weigh - o2.weigh);
        minHeap.offer(new Pair(0, start));
        int minCost = 0;
        while (!minHeap.isEmpty()) {
            Pair curr = minHeap.poll();
            start = curr.end;
            if (visited[start]) continue;
            visited[start] = true;
            minCost += curr.weigh;
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
