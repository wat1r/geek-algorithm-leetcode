## 在一个有向图中找环-PART2

[在一个有向图中找环](https://blog.csdn.net/wat1r/article/details/119426207)，已经介绍过，本篇是PART2，使用另外一种DFS方式，包装了结构体

![image-20210926090413794](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\graph\在一个有向图中找环-PART2.assets\image-20210926090413794.png)



### CycleInDirectedGraph

```java
import java.util.HashSet;
import java.util.Set;

public class CycleInDirectedGraph {


    public boolean hasCycle(Graph<Integer> graph) {
        Set<Vertex<Integer>> whiteSet = new HashSet<>();
        Set<Vertex<Integer>> graySet = new HashSet<>();
        Set<Vertex<Integer>> blackSet = new HashSet<>();
        whiteSet.addAll(graph.getAllVertex());
        while (whiteSet.size() != 0) {
            Vertex<Integer> cur = whiteSet.iterator().next();
            if (dfs(cur, whiteSet, graySet, blackSet)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(Vertex<Integer> cur, Set<Vertex<Integer>> whiteSet, Set<Vertex<Integer>> graySet, Set<Vertex<Integer>> blackSet) {
        //move current to gray set from white set and then explore it.
        moveVertex(cur, whiteSet, graySet);
        for (Vertex<Integer> neighbor : cur.getAdjacentVertexes()) {
            //if in black set means already explored so continue.
            if (blackSet.contains(neighbor)) continue;
            //if in gray set then cycle found.
            if (graySet.contains(neighbor)) return true;
            if (dfs(neighbor, whiteSet, graySet, blackSet)) return true;
        }
        //move vertex from gray set to black set when done exploring.
        moveVertex(cur, graySet, blackSet);
        return false;
    }


    /**
     * move vertex from srcSet to destSet
     */
    private void moveVertex(Vertex<Integer> vertex, Set<Vertex<Integer>> srcSet, Set<Vertex<Integer>> destSet) {
        srcSet.remove(vertex);
        destSet.add(vertex);
    }


    public static void main(String args[]) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(4, 1);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        CycleInDirectedGraph cdg = new CycleInDirectedGraph();
        System.out.println(cdg.hasCycle(graph));
    }
}
```

### Graph

```java
package com.frankcooper.classify.graph;

import java.util.*;


public class Graph<T> {

    private List<Edge<T>> allEdges;
    private Map<Long, Vertex<T>> allVertex;
    boolean isDirected = false;

    public Graph(boolean isDirected) {
        allEdges = new ArrayList<Edge<T>>();
        allVertex = new HashMap<Long, Vertex<T>>();
        this.isDirected = isDirected;
    }

    public void addEdge(long id1, long id2) {
        addEdge(id1, id2, 0);
    }

    //This works only for directed graph because for undirected graph we can end up
    //adding edges two times to allEdges
    public void addVertex(Vertex<T> vertex) {
        if (allVertex.containsKey(vertex.getId())) {
            return;
        }
        allVertex.put(vertex.getId(), vertex);
        for (Edge<T> edge : vertex.getEdges()) {
            allEdges.add(edge);
        }
    }

    public Vertex<T> addSingleVertex(long id) {
        if (allVertex.containsKey(id)) {
            return allVertex.get(id);
        }
        Vertex<T> v = new Vertex<T>(id);
        allVertex.put(id, v);
        return v;
    }

    public Vertex<T> getVertex(long id) {
        return allVertex.get(id);
    }

    public void addEdge(long id1, long id2, int weight) {
        Vertex<T> vertex1 = null;
        if (allVertex.containsKey(id1)) {
            vertex1 = allVertex.get(id1);
        } else {
            vertex1 = new Vertex<T>(id1);
            allVertex.put(id1, vertex1);
        }
        Vertex<T> vertex2 = null;
        if (allVertex.containsKey(id2)) {
            vertex2 = allVertex.get(id2);
        } else {
            vertex2 = new Vertex<T>(id2);
            allVertex.put(id2, vertex2);
        }

        Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
        allEdges.add(edge);
        vertex1.addAdjacentVertex(edge, vertex2);
        if (!isDirected) {
            vertex2.addAdjacentVertex(edge, vertex1);
        }

    }

    public List<Edge<T>> getAllEdges() {
        return allEdges;
    }

    public Collection<Vertex<T>> getAllVertex() {
        return allVertex.values();
    }

    public void setDataForVertex(long id, T data) {
        if (allVertex.containsKey(id)) {
            Vertex<T> vertex = allVertex.get(id);
            vertex.setData(data);
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Edge<T> edge : getAllEdges()) {
            buffer.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}


class Vertex<T> {
    long id;
    private T data;
    private List<Edge<T>> edges = new ArrayList<>();
    private List<Vertex<T>> adjacentVertex = new ArrayList<>();

    Vertex(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void addAdjacentVertex(Edge<T> e, Vertex<T> v) {
        edges.add(e);
        adjacentVertex.add(v);
    }

    public String toString() {
        return String.valueOf(id);
    }

    public List<Vertex<T>> getAdjacentVertexes() {
        return adjacentVertex;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public int getDegree() {
        return edges.size();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id != other.id)
            return false;
        return true;
    }
}

class Edge<T> {
    private boolean isDirected = false;
    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private int weight;

    Edge(Vertex<T> vertex1, Vertex<T> vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
        this.isDirected = isDirected;
    }

    Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
    }

    Vertex<T> getVertex1() {
        return vertex1;
    }

    Vertex<T> getVertex2() {
        return vertex2;
    }

    int getWeight() {
        return weight;
    }

    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
        result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (vertex1 == null) {
            if (other.vertex1 != null)
                return false;
        } else if (!vertex1.equals(other.vertex1))
            return false;
        if (vertex2 == null) {
            if (other.vertex2 != null)
                return false;
        } else if (!vertex2.equals(other.vertex2))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1
                + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
    }
}
```



## Reference

- [Detect Cycle in Directed Graph Algorithm](https://www.youtube.com/watch?v=rKQaZuoUR4M&t=1s&ab_channel=TusharRoy-CodingMadeSimple)

- [Detect Cycle in a Directed Graph](https://www.geeksforgeeks.org/detect-cycle-in-a-graph/)

- [Struct](
  https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/CycleInDirectedGraph.java)