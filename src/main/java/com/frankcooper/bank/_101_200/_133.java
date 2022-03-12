package com.frankcooper.bank._101_200;

import java.util.*;

/**
 * @Date 2020/8/31
 * @Author Frank Cooper
 * @Description
 */
public class _133 {


    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);
        Node mirror = new Node(node.val, new ArrayList<>());
        map.put(node, mirror);
        for (Node c : node.neighbors) map.get(node).neighbors.add(dfs(c, map));
        return mirror;
    }


    public Node cloneGraph1st(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node mirror = new Node(node.val, new ArrayList<>());
        map.put(node, mirror);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node c : curr.neighbors) {
                if (!map.containsKey(c)) {
                    map.put(c, new Node(c.val, new ArrayList<>()));
                    queue.offer(c);
                }
                map.get(curr).neighbors.add(map.get(c));
            }
        }
        return mirror;
    }


    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }


}
