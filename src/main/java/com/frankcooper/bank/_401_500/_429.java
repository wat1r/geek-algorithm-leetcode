package com.frankcooper.bank._401_500;

import com.frankcooper.struct.Node;

import java.util.*;
import java.util.Queue;

public class _429 {


    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> sub = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                Node curr = q.poll();
                sub.add(curr.val);
                if (curr.children != null && curr.children.size() != 0) {
                    for (int j = 0; j < curr.children.size(); j++) {
                        q.offer(curr.children.get(j));
                    }
                }
            }
            res.add(new ArrayList<>(sub));
        }
        return res;
    }

    static class _1st {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            Queue<Node> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> sub = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Node cur = q.poll();
                    sub.add(cur.val);
                    for (Node child : cur.children) {
                        q.offer(child);
                    }
                }
                res.add(new ArrayList<>(sub));
            }
            return res;
        }
    }

    static class _2nd {

        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> levelOrder(Node root) {
            dfs(root, 0);
            return res;
        }

        /**
         * @param root  当前处理到的节点
         * @param level 层数
         */
        private void dfs(Node root, int level) {
            if (root == null) return;
            if (level == res.size()) res.add(new ArrayList<>());
            res.get(level).add(root.val);
            for (Node child : root.children) dfs(child, level + 1);
        }
    }


}
