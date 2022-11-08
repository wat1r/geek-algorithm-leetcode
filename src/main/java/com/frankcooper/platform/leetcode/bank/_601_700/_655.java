package com.frankcooper.platform.leetcode.bank._601_700;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class _655 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class Tuple {
            TreeNode node;
            int r;
            int c;

            public Tuple(TreeNode node, int r, int c) {
                this.node = node;
                this.r = r;
                this.c = c;
            }
        }

        public List<List<String>> printTree(TreeNode root) {
            int height = calDepth(root);
            int m = height + 1;
            int n = (1 << (height + 1)) - 1;
            List<List<String>> res = new ArrayList<List<String>>();
            for (int i = 0; i < m; i++) {
                List<String> row = new ArrayList<String>();
                for (int j = 0; j < n; j++) {
                    row.add("");
                }
                res.add(row);
            }
            Queue<Tuple> queue = new ArrayDeque<Tuple>();
            queue.offer(new Tuple(root, 0, (n - 1) / 2));
            while (!queue.isEmpty()) {
                Tuple t = queue.poll();
                TreeNode node = t.node;
                int r = t.r, c = t.c;
                res.get(r).set(c, Integer.toString(node.val));
                if (node.left != null) {
                    queue.offer(new Tuple(node.left, r + 1, c - (1 << (height - r - 1))));
                }
                if (node.right != null) {
                    queue.offer(new Tuple(node.right, r + 1, c + (1 << (height - r - 1))));
                }
            }
            return res;
        }

        public int calDepth(TreeNode root) {
            int res = -1;
            Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int len = queue.size();
                res++;
                while (len > 0) {
                    len--;
                    TreeNode t = queue.poll();
                    if (t.left != null) {
                        queue.offer(t.left);
                    }
                    if (t.right != null) {
                        queue.offer(t.right);
                    }
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,null,4]");
            handler.printTree(root);
        }

        public List<List<String>> printTree(TreeNode root) {
            int height = calDepth(root);
            int m = height + 1;//高度+1 为 行数
            int n = (1 << (height + 1)) - 1;// 列数
            List<List<String>> res = new ArrayList<List<String>>();
            for (int i = 0; i < m; i++) {
                List<String> row = new ArrayList<String>();
                for (int j = 0; j < n; j++) {
                    row.add("");
                }
                res.add(row);
            }
            dfs(res, root, 0, (n - 1) / 2, height);
            return res;
        }

        public int calDepth(TreeNode root) {
            int h = 0;
            if (root.left != null) {
                h = Math.max(h, calDepth(root.left) + 1);
            }
            if (root.right != null) {
                h = Math.max(h, calDepth(root.right) + 1);
            }
            return h;
        }

        //当前节点为C 左孩子节点为
        //已知父节点位置为 p，左子节点位置为 (p << 1) + 1，右子节点位置为 p + 1 << 1。每层宽度为最大值 - 最小值 + 1
        //https://leetcode.cn/problems/print-binary-tree/solution/by-stormsunshine-w657/
        public void dfs(List<List<String>> res, TreeNode root, int r, int c, int height) {
            res.get(r).set(c, Integer.toString(root.val));
            if (root.left != null) {
                dfs(res, root.left, r + 1, c - (1 << (height - r - 1)), height);
            }
            if (root.right != null) {
                dfs(res, root.right, r + 1, c + (1 << (height - r - 1)), height);
            }
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
