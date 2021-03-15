package com.frankcooper.interview;

import java.util.HashMap;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        //AA00 +AA01

        String[] arr = new String[]{"AA00=10", "AB00=(AA00+AA01)*15", "AB01=20+AB00"};


    }

    private boolean process(String[] arr) {
        buildGraph(arr);
        return check();

    }


//    class Node

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
            if (!nodeMap.containsKey(v)) nodeMap.put(v, idx);
            List<String> uList = extractNode(t[1]);
            if (uList == null || uList.size() == 0) continue;
            for (String u : uList) {

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


        return null;
    }


    /**
     * 判断图是有环，有环表示有循环的相互引用，返回false 无环返回true
     *
     * @return
     */
    private boolean check() {
        for (String key : graph.keySet()) {

        }

        return false;
    }


    class UnionFind {
        private int[] parents;
//        private int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
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
            parents[rootX] = rootY;
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
