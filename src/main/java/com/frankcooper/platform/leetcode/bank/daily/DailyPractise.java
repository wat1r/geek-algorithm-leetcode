package com.frankcooper.platform.leetcode.bank.daily;


import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Frank Cooper(wang zhou)
 * @Date: 2023/11/03/ 8:59
 * @description 日常
 */
public class DailyPractise {
    static class _23_11_03 {
        public static void main(String[] args) {
            _23_11_03 handler = new _23_11_03();
            TreeNode root = TreeNodeIOUtils.transform("root = [1,2,3,4,5,null,7]");
        }

        public Node connect(Node root) {
            if (root == null) {
                return root;
            }
            Queue<Node> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                Node nxt = null;
                for (int i = 0; i < size; i++) {
                    Node cur = q.poll();
                    cur.next = nxt;
                    if (cur.right != null) {
                        q.offer(cur.right);
                    }
                    if (cur.left != null) {
                        q.offer(cur.left);
                    }
                    nxt = cur;
                }
            }
            return root;
        }

        class Node {
            public int val;
            public Node left;
            public Node right;
            public Node next;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, Node _left, Node _right, Node _next) {
                val = _val;
                left = _left;
                right = _right;
                next = _next;
            }
        }

        ;

    }

    static class _23_11_14 {
        public static void main(String[] args) {


        }


        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            int INF = Integer.MAX_VALUE;
            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = (i == j) ? 0 : INF;
                }
            }
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                graph[u][v] = w;
                graph[v][u] = w;
            }

            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i == j || graph[i][k] == INF || graph[k][j] == INF) {
                            continue;
                        }
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
            int res = -1, minn = n + 1;
            for (int i = 0; i < n; i++) {
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j && graph[i][j] <= distanceThreshold) {
                        cnt++;
                    }
                }
                if (minn >= cnt) {
                    minn = cnt;
                    res = i;
                }
            }
            return res;
        }

    }

//    static class _23_11_03 {
//        public static void main(String[] args) {
//
//
//        }
//
//
//    }
}
