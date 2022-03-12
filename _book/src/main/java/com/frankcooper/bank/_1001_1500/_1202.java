package com.frankcooper.bank._1001_1500;

import java.util.*;

public class _1202 {


    static _1202 handler = new _1202();


    public static void main(String[] args) {
        String s = "dcab";

        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 3));
        pairs.add(Arrays.asList(1, 2));
//        _2nd handler = new _2nd();
        _3rd handler = new _3rd();
        handler.smallestStringWithSwaps(s, pairs);
    }

    class _1st {

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            int n = s.length();
            UnionFind uf = new UnionFind(n);
            for (List<Integer> pair : pairs) uf.union(pair.get(0), pair.get(1));
            Map<Integer, List<Character>> graph = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int root = uf.find(i);
                List<Character> tmp = graph.getOrDefault(root, new ArrayList<>());
                tmp.add(s.charAt(i));
                graph.put(root, tmp);
            }
            for (Map.Entry<Integer, List<Character>> e : graph.entrySet()) {
                Collections.sort(e.getValue(), (o1, o2) -> o2 - o1);
            }
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < n; i++) {
                int root = uf.find(i);
                List<Character> tmp = graph.get(root);
                ans.append(tmp.remove(tmp.size() - 1));
            }
            return ans.toString();
        }


    }

    static class _2nd {

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            int n = s.length();
            UnionFind uf = new UnionFind(n);
            for (List<Integer> pair : pairs) uf.union(pair.get(0), pair.get(1));
            Map<Integer, PriorityQueue<Character>> graph = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int root = uf.find(i);
                graph.computeIfAbsent(root, k -> new PriorityQueue<>()).offer(s.charAt(i));
            }
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < n; i++) {
                PriorityQueue<Character> queue = graph.get(uf.find(i));
                ans.append(queue.poll());
            }
            return ans.toString();
        }


    }

    /**
     * bfs
     */
    static class _3rd {
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {

            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (List<Integer> pair : pairs) {
                List<Integer> list = graph.getOrDefault(pair.get(0), new ArrayList<>());
                list.add(pair.get(1));
                graph.putIfAbsent(pair.get(0), list);
                list = graph.getOrDefault(pair.get(1), new ArrayList<>());
                list.add(pair.get(0));
                graph.putIfAbsent(pair.get(1), list);
            }
            int n = s.length();
            char[] ans = new char[n];
            boolean[] vis = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (vis[i]) continue;
                queue.offer(i);
                List<Integer> li = new ArrayList<>();
                List<Character> lc = new ArrayList<>();
                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    if (vis[curr]) continue;
                    vis[curr] = true;
                    li.add(curr);
                    lc.add(s.charAt(curr));
                    List<Integer> next = graph.get(curr);
                    if (next != null) {
                        for (int ne : next) queue.offer(ne);
                    }
                }
                Collections.sort(li);
                Collections.sort(lc);
                for (int j = 0; j < lc.size(); j++) {
                    ans[li.get(j)] = lc.get(j);
                }
            }
            return new String(ans);
        }

    }


    static class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) parents[i] = i;
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return false;
            if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX; 
            if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
            if (ranks[rootX] == ranks[rootY]) {
                parents[rootY] = rootX;
                ranks[rootY]++;
            }
            return true;
        }

        public boolean connect(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            return rootX == rootY;
        }

    }

}
