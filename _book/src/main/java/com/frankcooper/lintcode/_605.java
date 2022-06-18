package com.frankcooper.platform.lintcode;

import com.frankcooper.utils.PrintUtils;

import java.util.*;

public class _605 {

    public static void main(String[] args) {
//        _605 handler = new _605();
//        _1st handler = new _1st();
        _2nd handler = new _2nd();
//        _3rd handler = new _3rd();
//        int[][] seqs = PrintUtils.processSymbol("[[1,2],[1,3],[2,3]]");
//        handler.buildGraph(seqs);
//        handler.buildGraph1(seqs);
//        handler.getTopoOrder(seqs);

        int[] org = {1, 2, 3};
        int[][] seqs = {{1, 2}, {1, 3}};
//        org = new int[]{4, 1, 5, 2, 6, 3};
//        seqs = PrintUtils.processSymbol("[[5,2,6,3],[4,1,5,2]]");
//        org = new int[]{1};
//        seqs = new int[][]{};
//        org = new int[]{};
//        seqs = new int[][]{};
//        org = new int[]{1};
//        seqs = new int[][]{{1}};
        org = new int[]{1, 2, 3};
        seqs = PrintUtils.processSymbol("[[1,2],[1,3],[2,3]]");
        handler.sequenceReconstruction(org, seqs);
    }


    static class _1st {
        /**
         * https://www.lintcode.com/problem/605/note/232576
         */


        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            List<Integer> topoOrder = getTopoOrder(seqs);
            if (topoOrder == null || topoOrder.size() != org.length) return false;
            for (int i = 0; i < org.length; i++) {//比较构建的序列是否唯一
                if (org[i] != topoOrder.get(i)) return false;
            }
            return true;
        }


        /**
         * 收集topoOrder的序列，在之后与org一一比较
         *
         * @param seqs
         * @return
         */
        private List<Integer> getTopoOrder(int[][] seqs) {
            Map<Integer, Set<Integer>> g = buildGraph(seqs);
            Map<Integer, Integer> indegrees = getIndegrees(g);
            List<Integer> topoOrder = new ArrayList<>();
            Queue<Integer> q = new LinkedList<>();
            for (Integer curr : g.keySet()) {
                if (indegrees.get(curr) == 0) {//找入度为0的点
                    q.offer(curr);
                    topoOrder.add(curr);
                }
            }
            while (!q.isEmpty()) {
                if (q.size() > 1) return null;//当前的q中不止一个，说明不唯一，返回,要求graph里只有一个排列结果
                Integer curr = q.poll();
                for (Integer next : g.get(curr)) {
                    indegrees.put(next, indegrees.get(next) - 1);//弹出一个，入度-1
                    if (indegrees.get(next) == 0) {
                        q.offer(next);
                        topoOrder.add(next);
                    }
                }
            }
            return topoOrder;
        }


        /**
         * 构建graph
         * set去重用的 4-> 5  如果出现在不同的seq中，只记录一次
         * 如 [[2,4,5,7],[1,4,5,8]]这种，那么(4,5)就出现了两次
         *
         * @param seqs
         * @return
         */
        private Map<Integer, Set<Integer>> buildGraph(int[][] seqs) {
            Map<Integer, Set<Integer>> g = new HashMap<>();
            for (int[] edge : seqs) {//拿到每一条边
                for (int i = 0; i < edge.length; i++) {
                    g.putIfAbsent(edge[i], new HashSet<>());
                    if (i == 0) continue;//i从0开始的
                    g.get(edge[i - 1]).add(edge[i]);//只做前后的两个数字，挨着的
                }
            }
            return g;
        }


        /**
         * 拿到入度
         *
         * @param g
         * @return
         */
        private Map<Integer, Integer> getIndegrees(Map<Integer, Set<Integer>> g) {
            Map<Integer, Integer> indegrees = new HashMap<>();
            for (Integer curr : g.keySet()) {
                indegrees.putIfAbsent(curr, 0);
                for (Integer next : g.get(curr)) {//遍历当前点的所有邻居节点，更新邻居节点的入度
                    indegrees.put(next, indegrees.getOrDefault(next, 0) + 1);
                }
            }
            return indegrees;
        }

    }

    static class _2nd {

        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            Map<Integer, Set<Integer>> g = new HashMap<>();
            Map<Integer, Integer> indegrees = new HashMap<>();
            int n = org.length;
            int count = 0;
            for (int[] edge : seqs) {
                count += edge.length;
                if (edge.length >= 1 && outArea(edge[0], 0, n)) return false;
                if (edge.length == 1) {
                    g.putIfAbsent(edge[0], new HashSet<>());
                    indegrees.put(edge[0], indegrees.getOrDefault(edge[0], 0));
                }
                for (int i = 1; i < edge.length; i++) {
                    if (outArea(edge[i], 0, n)) return false;
                    int from = edge[i - 1], to = edge[i];
                    g.putIfAbsent(from, new HashSet<>());
                    if (g.get(from).add(to)) {
                        indegrees.put(to, indegrees.getOrDefault(to, 0) + 1);
                    }
                    indegrees.putIfAbsent(from, 0);
                }
            }
            if (count < n) return false;
            Queue<Integer> q = new LinkedList<>();
            for (int curr : g.keySet()) {
                if (indegrees.get(curr) == 0) q.offer(curr);
            }
            int idx = 0;
            while (!q.isEmpty()) {
                if (q.size() > 1) return false;
                int curr = q.poll();
                if (g.get(curr) == null || g.get(curr).isEmpty()) {
                    idx++;
                    continue;
                }
                for (int next : g.get(curr)) {
                    indegrees.put(next, indegrees.get(next) - 1);
                    if (indegrees.get(next) == 0) q.offer(next);
                }
                if (curr != org[idx]) return false;
                idx++;
            }
            return idx == org.length;
        }

        private boolean outArea(int t, int lower, int upper) {
            return t <= lower || t > upper;
        }

    }

    static class _3rd {
        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            // Write your code here
            Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
            Map<Integer, Integer> indegree = new HashMap<Integer, Integer>();

            for (int num : org) {
                map.put(num, new HashSet<Integer>());
                indegree.put(num, 0);
            }

            int n = org.length;
            int count = 0;
            for (int[] seq : seqs) {
                count += seq.length;
                if (seq.length >= 1 && (seq[0] <= 0 || seq[0] > n))
                    return false;
                for (int i = 1; i < seq.length; i++) {
                    if (seq[i] <= 0 || seq[i] > n)
                        return false;
                    if (map.get(seq[i - 1]).add(seq[i]))
                        indegree.put(seq[i], indegree.get(seq[i]) + 1);
                }
            }

            // case: [1], []
            if (count < n)
                return false;

            Queue<Integer> q = new ArrayDeque<Integer>();
            for (int key : indegree.keySet())
                if (indegree.get(key) == 0)
                    q.add(key);

            int cnt = 0;
            while (q.size() == 1) {
                int ele = q.poll();
                for (int next : map.get(ele)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) q.add(next);
                }
                if (ele != org[cnt]) {
                    return false;
                }
                cnt++;
            }

            return cnt == org.length;
        }
    }
}
