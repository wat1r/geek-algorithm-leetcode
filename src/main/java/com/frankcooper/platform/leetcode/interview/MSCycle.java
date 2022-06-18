package com.frankcooper.platform.leetcode.interview;

import org.junit.Assert;

import java.util.*;

public class MSCycle {

    static MSCycle handler = new MSCycle();

    public static void main(String[] args) {
        String[] arr = new String[]{"AA00=10", "AB00=(AA00+AA01)*15", "AA01=20+AB00"};
        Assert.assertEquals(handler.process(arr), false);
        arr = new String[]{"AA00=10", "AA01=AA00+AB00", "AB00=15"};
        Assert.assertEquals(handler.process(arr), true);
    }

    private boolean process(String[] arr) {
        buildGraph(arr);
        return check();
    }


    HashMap<String, String> graph = new HashMap<>();
    HashMap<String, Integer> nodeMap = new HashMap<>();

    /**
     * 构建有向图
     *
     * @param arr
     */
    int idx = 0;

    private void buildGraph(String[] arr) {
        for (String a : arr) {
            String[] t = a.split("=");
            String v = t[0];
            if (!nodeMap.containsKey(v)) nodeMap.put(v, idx++);
            List<String> uList = extractNode(t[1]);
            if (uList == null || uList.size() == 0) continue;
            for (String u : uList) {
                if (!nodeMap.containsKey(u)) nodeMap.put(u, idx++);
                graph.put(u, v);
            }
        }
    }

    /**
     * 从表达式中抽取出 节点
     *
     * @param str
     */


    private List<String> extractNode(String str) {
        List<Character> symbols = new ArrayList<Character>() {{
            add('+');
            add('-');
            add('*');
            add('/');
            add('(');
            add(')');
        }};
        char[] chas = str.toCharArray();
        Deque<Character> q = new LinkedList<>();
        List<String> uList = new ArrayList<>();
        int i = 0, n = str.length();
        while (i <= n) {
            StringBuilder sb = new StringBuilder();
            if (!q.isEmpty() && (i == n || symbols.contains(chas[i]))) {
                while (!q.isEmpty()) sb.append(q.pop());
            }
            if (!sb.toString().equals("") && isVariable(sb.toString())) uList.add(sb.toString());
            if (i == n) break;
            if (!symbols.contains(chas[i])) q.offer(chas[i]);
            i++;
        }
        return uList;
    }


    private boolean isVariable(String u) {
        for (char c : u.toCharArray()) if (c >= 'A' && c <= 'Z') return true;
        return false;
    }


    /**
     * 判断图是有环，有环表示有循环的相互引用，返回false 无环返回true
     *
     * @return
     */
    private boolean check() {
        UnionFind uf = new UnionFind(nodeMap.keySet().size());
        for (String key : graph.keySet()) {
            Integer u = nodeMap.get(key), v = nodeMap.get(graph.get(key));
            if (u == null || v == null) continue;
            if (!uf.merge(u, v)) return false;
        }
        return true;
    }


    class UnionFind {
        private int[] parents;
        private int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            while (x != parents[x]) {
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }

        public boolean merge(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return false;
            if (ranks[rootX] > ranks[rootY]) parents[rootY] = rootX;
            if (ranks[rootX] < ranks[rootY]) parents[rootX] = rootY;
            if (ranks[rootX] == ranks[rootY]) {
                parents[rootY] = rootX;
                ranks[rootX]++;
            }
            return true;
        }
    }

    /**
     * boolean[] ans = new boolean[len]
     * T T
     * T
     * F
     * F
     * <p>
     * <p>
     * k ,v  --> 常数
     * = 右侧 的参数 个数  0 -> 2 个 - > 1
     * 0 ->
     * AA00=10+10 =20   () + - * /  =   假设
     * AB00=15
     *
     * @param arr
     */


    private void parse(String[] arr) {

    }

    private void cal(String str) {

    }

}
