package com.frankcooper.bank._1001_1500;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _1302 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public int deepestLeavesSum(TreeNode root) {
            int sum = 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    sum += cur.val;
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
            }
            return sum;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        int maxDepth = -1, res = 0;

        public int deepestLeavesSum(TreeNode root) {
            dfs(root, 0);
            return res;
        }

        private void dfs(TreeNode cur, int depth) {
            if (cur == null) return;
            dfs(cur.left, depth + 1);
            if (depth >= maxDepth) {
                if (depth > maxDepth) res = 0;
                maxDepth = depth;
                res += cur.val;
            }
            dfs(cur.right, depth + 1);
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
