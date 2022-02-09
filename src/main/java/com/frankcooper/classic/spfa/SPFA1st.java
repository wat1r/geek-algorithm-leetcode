package com.frankcooper.classic.spfa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Shortest Path Faster Algorithm
 * <p>
 * https://blog.csdn.net/qq_35644234/article/details/61614581
 * <p>
 * https://www.jianshu.com/p/544d7d801355
 */
public class SPFA1st {

    static SPFA1st handler = new SPFA1st();


    public static void main(String[] args) {
        handler.testOne();
    }


    public void testOne() {
        Vertex V1 = new Vertex("V1");
        Vertex V2 = new Vertex("V2");
        Vertex V3 = new Vertex("V3");
        Vertex V4 = new Vertex("V4");
        Vertex V5 = new Vertex("V5");
        Vertex V6 = new Vertex("V6");
        Graph graph = new Graph();
        graph.addVertex(V1, V2, V3, V4, V5, V6);
        graph.addEdge(V1, V6, 100);
        graph.addEdge(V1, V5, 30);
        graph.addEdge(V1, V3, 10);
        graph.addEdge(V5, V6, 60);
        graph.addEdge(V5, V4, 20);
        graph.addEdge(V4, V6, 10);
        graph.addEdge(V2, V3, 5);
        graph.addEdge(V3, V4, 50);
        List<Dis> disList = graph.spfa(V1);
        System.out.println(Arrays.stream("起点  终点    最短路径    长度".split("\\s+")).collect(Collectors.joining("\t")));
        disList.forEach(dis -> System.out.println(String.format("%s\t%s\t%s", V1.getKey(), dis.getV().getKey(),
                dis.getPath().stream().map(e -> e.getEnd().getKey() + ":" + e.getWeight()))));

    }


    /**
     * 计算辅助类
     */
    @Accessors(chain = true)
    @Data
    static class Dis {
        Vertex v;
        int dis;
        List<Edge> path;

        public boolean isMax() {
            return dis == Integer.MAX_VALUE;
        }

        public Dis setMax() {
            this.dis = Integer.MAX_VALUE;
            return this;
        }

    }


    /**
     * 图
     */
    static class Graph {
        private List<Vertex> topList = new ArrayList();
        private Map<Vertex, List<Edge>> edgeMap = new HashMap();

        public Graph addVertex(Vertex... vertexes) {
            topList.addAll(Arrays.asList(vertexes));
            return this;
        }

        public Graph addEdge(Vertex start, Vertex end, int weight) {
            Edge edge = new Edge(start, end, weight);
            List<Edge> list = edgeMap.get(start);
            if (list == null) {
                list = new ArrayList();
                edgeMap.put(start, list);
            }
            list.add(edge);
            return this;
        }

        public List<Dis> spfa(Vertex start) {
            List<Dis> disList = new ArrayList<>();
            for (Vertex v : topList) {
                Dis dis = new Dis().setV(v).setPath(new ArrayList<>());
                disList.add(v.equals(start) ? dis.setDis(0) : dis.setMax());
            }
            List<Vertex> stack = new LinkedList<>();
            stack.add(start);
            while (stack.size() > 0) {
                Vertex v = stack.remove(0);
                Dis vDis = disList.stream().filter(d -> d.v.equals(v)).findFirst().get();
                Map<Vertex, List<Edge>> collect = edgeMap.getOrDefault(v, new ArrayList<>()).stream().collect(Collectors.groupingBy(Edge::getEnd));
                for (Map.Entry<Vertex, List<Edge>> entry : collect.entrySet()) {
                    Vertex kk = entry.getKey();
                    List<Edge> vv = entry.getValue();
                    Edge minEdge = vv.stream().min(Comparator.comparing(Edge::getWeight)).get();
                    Dis minDis = disList.stream().filter(d -> d.v.equals(kk)).findFirst().get();
                    if (vDis.dis + minEdge.weight < minDis.dis) {
                        minDis.dis = vDis.dis + minEdge.weight;
                        minDis.path.clear();
                        minDis.path.addAll(vDis.path);
                        minDis.path.add(minEdge);
                        if (!stack.contains(minDis.v)) {
                            stack.add(minDis.v);
                        }
                    }
                }


            }
            return disList;
        }
    }


    /**
     * 顶点
     */
    @Data
    @AllArgsConstructor
    static class Vertex {
        public final String key;
    }

    /**
     * 路径
     */
    @Data
    @AllArgsConstructor
    static class Edge {
        public final Vertex start;
        public final Vertex end;
        public final int weight;
    }


}
