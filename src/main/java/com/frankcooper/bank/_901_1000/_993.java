package com.frankcooper.bank._901_1000;

import java.util.*;

import com.frankcooper.struct.TreeNode;

public class _993 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            /**
             * root = [1,2,3,null,4,null,5], x = 5, y = 4
             */

            TreeNode root = new TreeNode(1);
            TreeNode r2 = new TreeNode(2);
            TreeNode r3 = new TreeNode(3);
            root.left = r2;
            root.right = r3;
            TreeNode r4 = new TreeNode(4);
            TreeNode r5 = new TreeNode(5);
            r2.right = r4;
            r3.right = r5;
            int x = 5, y = 4;
            handler.isCousins(root, x, y);
        }


      /*  class Pair {
            private TreeNode cur;
            private TreeNode parent;

            public Pair(TreeNode cur, TreeNode parent) {
                this.cur = cur;
                this.parent = parent;
            }


        }*/

        public boolean isCousins(TreeNode root, int x, int y) {
            Deque<TreeNode[]> q = new LinkedList<>();//存当前节点，父亲节点
            q.offer(new TreeNode[]{root, null});
            while (!q.isEmpty()) {
                int size = q.size();
                int fx = 0, fy = 0;//x y 有没有出现
                TreeNode[] candidates = new TreeNode[2];//x y 的父亲节点
                for (int i = 0; i < size; i++) {
                    TreeNode[] poll = q.poll();
                    TreeNode cur = poll[0], parent = poll[1];
                    if (cur.val == x) {
                        fx = 1;
                        candidates[0] = parent;
                    } else if (cur.val == y) {
                        fy = 1;
                        candidates[1] = parent;
                    }
                    if (cur.left != null) q.offer(new TreeNode[]{cur.left, cur});
                    if (cur.right != null) q.offer(new TreeNode[]{cur.right, cur});
                }
                if (fx == 0 && fy == 0) continue;
                if ((fx ^ fy) == 1) return false;
                if ((fx & fy) == 1) return candidates[0] != candidates[1];
            }
            return false;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        TreeNode[] ps = new TreeNode[2];//x ,y 对应的两个节点的parent节点
        int[] ds = new int[2];//x, y 对应的节点的深度
        int x, y;

        public boolean isCousins(TreeNode root, int x, int y) {
            this.x = x;
            this.y = y;
            dfs(root, 0, null);
            return ds[0] == ds[1] && ps[0] != ps[1];//深度相同，且父亲节点不同

        }

        /**
         * @param node   当前遍历到的节点
         * @param depth  当前节点的深度
         * @param parent 当前节点的父亲节点
         */
        public void dfs(TreeNode node, int depth, TreeNode parent) {
            if (node != null) {
                if (node.val == x) {//找到x
                    ds[0] = depth;
                    ps[0] = parent;
                } else if (node.val == y) {//找到y
                    ds[1] = depth;
                    ps[1] = parent;
                }
                dfs(node.left, depth + 1, node);
                dfs(node.right, depth + 1, node);
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
