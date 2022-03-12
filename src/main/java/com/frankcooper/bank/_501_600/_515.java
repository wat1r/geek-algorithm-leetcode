package com.frankcooper.bank._501_600;

import java.util.*;

import com.frankcooper.struct.TreeNode;

public class _515 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                int t = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    t = Math.max(t, cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                res.add(t);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
