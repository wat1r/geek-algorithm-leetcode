package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import com.frankcooper.struct.TreeNode;

public class _1315 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        int res = 0;

        public int sumEvenGrandparent(TreeNode root) {
            dfs(null, null, root);
            return res;
        }


        private void dfs(TreeNode grandfather, TreeNode father, TreeNode cur) {
            if (cur == null) return;
            if (grandfather != null && grandfather.val % 2 == 0) res += cur.val;
            dfs(father, cur, cur.left);
            dfs(father, cur, cur.right);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        int res = 0;

        public int sumEvenGrandparent(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                boolean isEven = (cur.val % 2 == 0);
                if (cur.left != null) {
                    q.offer(cur.left);
                    if (isEven) res +=cal(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                    if(isEven) res+=cal(cur.right);
                }
            }
            return res;
        }

        private int cal(TreeNode node) {
            int sum =0;
            if(node.left!=null)sum+=node.left.val;
            if(node.right!=null)sum+=node.right.val;
            return sum;
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
