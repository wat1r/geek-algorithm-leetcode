package com.frankcooper.bank._301_400;

import java.util.*;

public class _399 {
    static _399 handler = new _399();


    public static void main(String[] args) {
        List<List<String>> eqs = new ArrayList<>();
        eqs.add(Arrays.asList("a", "b"));
        eqs.add(Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> qus = new ArrayList<>();
        qus.add(Arrays.asList("a", "c"));
        qus.add(Arrays.asList("b", "a"));
        qus.add(Arrays.asList("a", "e"));
        qus.add(Arrays.asList("a", "a"));
        qus.add(Arrays.asList("x", "x"));
        handler.calcEquation(eqs, values, qus);

    }


    public double[] calcEquation(List<List<String>> eqs, double[] values, List<List<String>> qus) {
        int cnt = 0, n = eqs.size();
        Map<String, Integer> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(eqs.get(i).get(0))) graph.put(eqs.get(i).get(0), cnt++);
            if (!graph.containsKey(eqs.get(i).get(1))) graph.put(eqs.get(i).get(1), cnt++);
        }

        List<Pair>[] edges = new List[cnt];
//        Arrays.fill(edges, new ArrayList<Pair>());
        for (int i = 0; i < cnt; i++) {
            edges[i] = new ArrayList<Pair>();
        }
        for (int i = 0; i < n; i++) {
            int s = graph.get(eqs.get(i).get(0)), e = graph.get(eqs.get(i).get(1));//起始点
            edges[s].add(new Pair(e, values[i]));
            edges[e].add(new Pair(s, 1.0 / values[i]));
        }
        int m = qus.size();
        double[] res = new double[m];
        for (int i = 0; i < m; i++) {
            List<String> qu = qus.get(i);
            double tmp = -1.0;//默认-1.0,有字符不在graph中的
            if (graph.containsKey(qu.get(0)) && graph.containsKey(qu.get(1))) {
                int s = graph.get(qu.get(0)), e = graph.get(qu.get(1));
                if (s == e) tmp = 1.0;//相等的时候
                else {//不相等的时候
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(s);
                    double[] arr = new double[cnt];
                    Arrays.fill(arr, -1.0);
                    arr[s] = 1.0;
                    while (!queue.isEmpty() && arr[e] < 0) {
                        int x = queue.poll();
                        for (Pair pair : edges[x]) {
                            int y = pair.idx;
                            double val = pair.val;
                            if (arr[y] < 0) {
                                arr[y] = arr[x] * val;
                                queue.offer(y);
                            }
                        }
                    }
                    tmp = arr[e];
                }
            }
            res[i] = tmp;
        }
        return res;
    }


    class Pair {
        private int idx;
        private double val;

        public Pair(int idx, double val) {
            this.idx = idx;
            this.val = val;
        }
    }


}
