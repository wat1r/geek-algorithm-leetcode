package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _1195 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public List<Integer> largestValues(TreeNode root) {
            // write your code here
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            List<Integer> res = new ArrayList<>();
            while (!q.isEmpty()) {
                int size = q.size();
                int maxv = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    maxv = Math.max(maxv, cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                res.add(maxv);
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                int maxv = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    maxv = Math.max(maxv, cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                res.add(maxv);
            }
            return res;

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
