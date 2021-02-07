package com.frankcooper.bank;

import java.util.*;

/**
 * @Date 2020/8/31
 * @Author Frank Cooper
 * @Description
 */
public class _332 {

    static class _1st {
        //收集结果返回
        List<String> resList = new ArrayList<>();

        public List<String> findItinerary(List<List<String>> tickets) {
            //生成graph
            Map<String, ArrayList<String>> graph = new HashMap<>();
            for (List<String> ticket : tickets) {
                String from = ticket.get(0);
                String to = ticket.get(1);
                graph.putIfAbsent(from, new ArrayList<>());
                graph.get(from).add(to);
            }
            //每个机场指向的机场按字典序排序
            //如 JFK->[LGB,LGA] 变成 JFK->[LGA,LGB]
            for (ArrayList<String> values : graph.values()) {
                Collections.sort(values);
            }
            dfs("JFK", graph);
            return resList;

        }

        /**
         * dfs
         *
         * @param candidate 当前去的机场
         * @param graph     整个机场的图
         */
        private void dfs(String candidate, Map<String, ArrayList<String>> graph) {
            ArrayList<String> nexts = graph.get(candidate);
            //当前机场如果还是有机场可以，一直去目标机场
            while (nexts != null && !nexts.isEmpty()) {
                //去过这个机场，就将其移除掉，因为排序了，每次取第一个
                String next = nexts.remove(0);
                dfs(next, graph);
            }
            System.out.println(candidate);
            //最先开始往resList加入的是哪些以当前机场为from，没有to可去的
            resList.add(0, candidate);
        }
    }


    /**
     * dfs 朴素版
     */
    static class _2nd {


        public List<String> findItinerary(List<List<String>> tickets) {
            List<String> res = new ArrayList<>();
            Map<String, List<String>> graph = new HashMap<>();
            for (List<String> t : tickets) {
                String u = t.get(0), v = t.get(1);
                graph.putIfAbsent(u, new ArrayList<>());
                graph.get(u).add(v);
            }
            for (List<String> values : graph.values()) Collections.sort(values);
            dfs(graph, res, "JFK");
            return res;
        }


        private void dfs(Map<String, List<String>> graph, List<String> res, String u) {
            List<String> nexts = graph.get(u);
            while (nexts != null && nexts.size() > 0) {
                String v = nexts.remove(0);
                dfs(graph, res, v);
            }
            res.add(0, u);
        }

    }


    /**
     * dfs PriorityQueue
     */
    static class _3rd {
        public List<String> findItinerary(List<List<String>> tickets) {
            Map<String, PriorityQueue<String>> graph = new HashMap<>();
            for (List<String> t : tickets) {
                String u = t.get(0), v = t.get(1);
                graph.putIfAbsent(u, new PriorityQueue<>());
                graph.get(u).offer(v);
            }
            Stack<String> stack = new Stack<>();
            dfs(graph, stack, "JFK");
            List<String> res = new ArrayList<>();
            while (!stack.isEmpty()) res.add(stack.pop());
            return res;
        }

        private void dfs(Map<String, PriorityQueue<String>> graph, Stack<String> stack, String u) {
            PriorityQueue<String> nexts = graph.get(u);
            while (nexts != null && nexts.size() > 0) {
                String v = nexts.poll();
                dfs(graph, stack, v);
            }
            stack.push(u);
        }
    }


}
