package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

public class _230 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        List<Integer> list = new ArrayList<>();

        public int kthSmallest(TreeNode root, int k) {
            dfs(root);
            return list.get(k - 1);
        }

        private void dfs(TreeNode root) {
            if (root == null) return;
            dfs(root.left);
            list.add(root.val);
            dfs(root.right);
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        //迭代，一直压到left没有为止
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stk = new Stack<>();
            while (root != null) {
                stk.add(root);
                root = root.left;
            }
            while (k != 0) {
                TreeNode cur = stk.pop();
                if (--k == 0) return cur.val;
                TreeNode p = cur.right;
                while (p != null) {
                    stk.add(p);
                    p = p.left;
                }
            }
            return -1;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        int res = -1;
        int k;

        public int kthSmallest(TreeNode root, int k) {
            this.k = k;
            dfs(root);
            return res;
        }

        private void dfs(TreeNode root) {
            if (root.left != null) dfs(root.left);
            if (--k == 0) {
                res = root.val;
                return;
            }
            if (root.right != null) dfs(root.right);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stk = new Stack<>();
            int cnt = 0;
            while (!stk.isEmpty() || root != null) {
                if (root != null) {
                    stk.push(root);
                    root = root.left;
                } else {
                    TreeNode cur = stk.pop();
                    if (++cnt == k) return cur.val;
                    root = cur.right;
                }
            }
            return -1;
        }
    }

    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            TreeNode root = TreeNodeIOUtils.transform("[5,3,6,2,4,null,null,1]");
            int k = 3;
            handler.kthSmallest(root, k);
        }

        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stk = new Stack<>();
            int cnt = 0;
            while (!stk.isEmpty() || root != null) {
                if (root != null) {
                    stk.push(root);
                    root = root.left;
                } else {
                    TreeNode cur = stk.pop();
                    if (++cnt == k) {
                        return cur.val;
                    }
                    root = cur.right;
                }
            }
            return -1;
        }
    }
}
