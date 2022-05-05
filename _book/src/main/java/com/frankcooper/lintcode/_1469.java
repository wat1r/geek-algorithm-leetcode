package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _1469 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int n = 5;
            int[] starts = {0, 0, 2, 2}, ends = {1, 2, 3, 4}, lens = {1, 2, 5, 6};
            Assert.assertEquals(11, handler.longestPath(n, starts, ends, lens));

        }

        class Node {
            int val;
            Map<Node, Integer> neighbors;

            public Node(int val) {
                this.val = val;
                this.neighbors = new HashMap<>();
            }
        }


        class Pair implements Comparable<Pair> {
            int val;
            Node node;

            public Pair(int val, Node node) {
                this.val = val;
                this.node = node;
            }

            @Override
            public int compareTo(Pair other) {
                return this.val - other.val;
            }
        }


        public int longestPath(int n, int[] starts, int[] ends, int[] lens) {
            Map<Integer, Node> graph = new HashMap<>();
            for (int i = 0; i < starts.length; i++) {
                int u = starts[i], v = ends[i], w = lens[i];
                graph.putIfAbsent(u, new Node(u));
                Node uNode = graph.get(u);
                graph.putIfAbsent(v, new Node(v));
                Node vNode = graph.get(v);
                graph.get(u).neighbors.put(vNode, w);
                graph.get(v).neighbors.put(uNode, w);
            }
            Node root = graph.get(0);
            Pair pair = bfs(n, root);
            return bfs(n, pair.node).val;
        }

        private Pair bfs(int n, Node root) {
            Pair res = new Pair(-1, null);
            boolean[] vis = new boolean[n];
            vis[root.val] = true;
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            pq.offer(new Pair(0, root));
            while (!pq.isEmpty()) {
                Pair cur = pq.poll();
                res = cur;
                for (Map.Entry<Node, Integer> e : cur.node.neighbors.entrySet()) {
                    if (vis[e.getKey().val]) continue;
                    vis[e.getKey().val] = true;
                    pq.offer(new Pair(cur.val + e.getValue(), e.getKey()));
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
