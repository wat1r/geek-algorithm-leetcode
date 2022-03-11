package com.frankcooper.classic.bellman_ford;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2020/9/20 11:46
 * Description
 * <p>
 * https://blog.csdn.net/afei__/article/details/83378472
 */
public class BellmanFord1st {


    public static void main(String[] args) {
        List<Vertex> list = getTestData();
        // 设置第0个顶点为起始点
        Vertex source = list.get(0);
        source.setDistance(0);
        // 贝文曼福德算法
        boolean flag = bellmanFord(list, source);
        // 打印结果
        System.out.println("是否存在解决方案：" + flag);
        for (int i = 0; i < list.size(); i++) {
            Vertex vertex = list.get(i);
            System.out.println(vertex.toString());
        }
    }

    public static boolean bellmanFord(List<Vertex> list, Vertex source) {
        // 1. 将所有边添加到一个队列中
        LinkedList<Edge> queue = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            queue.addAll(list.get(i).getNeighbors());
        }
        // 2. 需要执行 (V-1)*E 次松弛操作
        for (int i = 1; i < list.size(); i++) {
            System.out.printf("i:%d\n", i);
            for (Edge edge : queue) {
                System.out.printf("start:%s-->end:%s--->weigh:%d\n", edge.getStart(), edge.getEnd(), edge.getWeight());
                relax(edge);
            }
        }
        // 3. 验证是否存在权重为负数的环路
        for (Edge edge : queue) {
            Vertex u = edge.getStart();
            Vertex v = edge.getEnd();
            // 对于边 (u, v)，如果 v.distance > u.distance + weight，则说明是负数环路
            if (v.getDistance() > u.getDistance() + edge.getWeight()) {
                return false;
            }
        }
        return true;
    }

    public static void relax(Edge edge) {
        Vertex start = edge.getStart();
        Vertex end = edge.getEnd();
        int distance = start.getDistance() + edge.getWeight();
        if (end.getDistance() > distance) {
            end.setDistance(distance);
            end.setParent(start);
        }
    }

    public static List<Vertex> getTestData() {
        Vertex s = new Vertex('s');
        Vertex t = new Vertex('t');
        Vertex x = new Vertex('x');
        Vertex y = new Vertex('y');
        Vertex z = new Vertex('z');
        s.addNeighbor(t, 6); // s->t : 6
        s.addNeighbor(y, 7); // s->y : 7
        t.addNeighbor(x, 5); // t->x : 5
        t.addNeighbor(y, 8); // t->y : 8
        t.addNeighbor(z, -4); // t->z : -4
        x.addNeighbor(t, -2); // x->t : -2
        y.addNeighbor(x, -3); // y->x : -3
        y.addNeighbor(z, 9); // y->z : 9
        z.addNeighbor(s, 2); // z->s : 2
        z.addNeighbor(x, 7); // z->x : 7
        LinkedList<Vertex> list = new LinkedList<>();
        list.add(s);
        list.add(t);
        list.add(x);
        list.add(y);
        list.add(z);
        return list;
    }


    public static class Vertex {

        private char id; // 顶点的标识
        private LinkedList<Edge> edges; // 当前顶点可直接达到的边
        private Vertex parent; // 上一个顶点是谁(前驱)，用来记录路径的
        private int distance = Integer.MAX_VALUE; // 距离起始点的距离

        public Vertex(char id) {
            this.id = id;
            this.edges = new LinkedList<>();
        }

        public char getId() {
            return id;
        }

        public LinkedList<Edge> getNeighbors() {
            return edges;
        }

        public void addNeighbor(Vertex vertex, int weight) {
            edges.add(new Edge(this, vertex, weight)); // 起点均是当前顶点
        }

        public Vertex getParent() {
            return parent;
        }

        public void setParent(Vertex parent) {
            this.parent = parent;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public String toString() {
            return String.format("Vertex[%c]: distance is %d , predecessor is '%s'", id, distance, parent == null ? "null"
                    : parent.id);
        }

    }


    public static class Edge {

        private Vertex start;
        private Vertex end;
        private int weight; // 权重（边的长度）

        public Edge(Vertex start, Vertex end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public Vertex getStart() {
            return start;
        }

        public Vertex getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }
    }


}
