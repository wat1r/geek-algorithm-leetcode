package com.frankcooper.bank;

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


}
