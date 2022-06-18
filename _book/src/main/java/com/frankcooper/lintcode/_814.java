package com.frankcooper.platform.lintcode;

import java.util.*;

/**
 * // * 同leetcode 323
 * 814. 无向图中的最短路径
 */
public class _814 {


    public static void main(String[] args) {

    }


    class _1st {


        class UndirectedGraphNode {
            int label;
            List<UndirectedGraphNode> neighbors;

            UndirectedGraphNode(int x) {
                label = x;
                neighbors = new ArrayList<UndirectedGraphNode>();
            }
        }

        public int shortestPath(List<UndirectedGraphNode> graph, UndirectedGraphNode A, UndirectedGraphNode B) {
            Queue<UndirectedGraphNode> q = new LinkedList<>();
            Set<UndirectedGraphNode> visited = new HashSet<>();
            q.offer(A);
            visited.add(A);
            int res = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                res++;
                for (int i = 0; i < size; i++) {
                    UndirectedGraphNode curr = q.poll();
                    for (UndirectedGraphNode next : curr.neighbors) {
                        System.out.printf("%d-->%d\n", curr.label, next.label);
                        if (visited.contains(next)) continue;
                        if (next.label == B.label) return res;
                        q.offer(next);
                        visited.add(next);
                    }
                }
            }
            return -1;
        }


    }


    static class _2nd {


    }

}
