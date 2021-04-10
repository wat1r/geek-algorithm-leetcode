package com.frankcooper.bank._1001_2000;

import com.frankcooper.utils.PrintUtils;

import java.util.*;

public class _1743 {

    public static void main(String[] args) {
        _1st handler = new _1st();
        int[][] edges = PrintUtils.processSymbol("[[2,1],[3,4],[3,2]]");
        handler.restoreArray(edges);
    }

    static class _1st {

        public int[] restoreArray(int[][] edges) {
            int n = edges.length;
            Map<Integer, Set<Integer>> g = new HashMap<>();//构建graph，做无向图
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                g.putIfAbsent(u, new HashSet<>());
                g.putIfAbsent(v, new HashSet<>());
                g.get(u).add(v);
                g.get(v).add(u);
            }
            Set<Integer> vis = new HashSet<>();//控制元素重复访问的set
            int start = 0;//处理当前的点
            int[] ans = new int[n + 1];//结果数组
            int idx = 0;
            for (Integer curr : g.keySet()) {
                if (g.get(curr).size() == 1) {//找一个size为1的
                    start = curr;
                    vis.add(start);
                    ans[idx++] = start;
                    break;
                }
            }
            while (vis.size() < n + 1) {
                for (int next : g.get(start)) {//遍历当前点的邻居节点
                    if (!vis.contains(next)) {
                        vis.add(next);
                        ans[idx++] = next;
                        start = next;
                        break;
                    }
                }
            }
            return ans;
        }


    }
}
