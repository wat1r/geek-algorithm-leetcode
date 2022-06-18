package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _1022 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


//            Assert.assertEquals(4, handler.cal(Arrays.asList(1, 0, 0)));
//            Assert.assertEquals(5, handler.cal(Arrays.asList(1, 0, 1)));
//            Assert.assertEquals(6, handler.cal(Arrays.asList(1, 1, 0)));
//            Assert.assertEquals(7, handler.cal(Arrays.asList(1, 1, 1)));


            int[] arr = {1, 0, 1, 0, 1, 0, 1};
            TreeNode root = new TreeNode(1);
            TreeNode r1 = new TreeNode(0);
            TreeNode r2 = new TreeNode(1);
            root.left = r1;
            root.right = r2;
            TreeNode r3 = new TreeNode(0);
            TreeNode r4 = new TreeNode(1);
            r1.left = r3;
            r1.right = r4;
            TreeNode r5 = new TreeNode(0);
            TreeNode r6 = new TreeNode(1);
            r2.left = r5;
            r2.right = r6;
//            handler.sumRootToLeaf(root);

            TreeNode root1 = new TreeNode(1);
            root1.right = new TreeNode(0);
            handler.sumRootToLeaf(root1);


        }


        int res = 0;

        public int sumRootToLeaf(TreeNode root) {
            dfs(root, new ArrayList<>());
            return res;
        }


        private void dfs(TreeNode root, List<Integer> list) {
            list.add(root.val);
            if (root.left == null && root.right == null) {
                res += cal(list);
                return;
            }
            if (root.left != null) {
                dfs(root.left, list);
                list.remove(list.size() - 1);
            }
            if (root.right != null) {
                dfs(root.right, list);
                list.remove(list.size() - 1);
            }
        }


        private int cal(List<Integer> list) {
            int res = 0;
            for (int i = list.size() - 1; i >= 0; i--) {
                res |= (list.get(i) << (list.size() - 1 - i));
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        int res;

        public int sumRootToLeaf(TreeNode root) {
            dfs(root, new ArrayList<>());
            return res;
        }


        public void dfs(TreeNode root, List<Integer> paths) {
            paths.add(root.val);
            if (root.left == null && root.right == null) {
                res += cal(paths);
                return;
            }
            if (root.left != null) {
                dfs(root.left, paths);
                paths.remove(paths.size() - 1);
            }
            if (root.right != null) {
                dfs(root.right, paths);
                paths.remove(paths.size() - 1);
            }

        }

        private int cal(List<Integer> paths) {
            int t = 0, n = paths.size();
            for (int i = 0; i < n; i++) {
                t |= paths.get(i) << (n - i - 1);
            }
            return t;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int sumRootToLeaf(TreeNode root) {
            return dfs(root, 0);
        }

        public int dfs(TreeNode root, int res) {
            if (root == null) return 0;
            //res左移一位，让出低位给root.val
            res = res << 1 | root.val;
            if (root.left == null && root.right == null) {
                return res;
            }
            return dfs(root.left, res) + dfs(root.right, res);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            TreeNode root = TreeNodeIOUtils.transform("[1,0,1,0,1,0,1]");
//            handler.sumRootToLeaf(root);
        }


        public int sumRootToLeaf(TreeNode root) {
            Deque<TreeNode> stk = new ArrayDeque<>();
            int t = 0, res = 0;
            //prev记录上一轮访问过的节点
            TreeNode prev = null;
            while (!stk.isEmpty() || root != null) {
                while (root != null) {
                    //计算
                    t = t << 1 | root.val;
                    //左孩子一直进栈，直到没有左孩子
                    stk.push(root);
                    root = root.left;
                }
                //看下当前栈顶的节点
                root = stk.peek();
                //
                if (root.right == null || root.right == prev) {
                    //收集的条件
                    if (root.left == null && root.right == null) {
                        res += t;
                    }
                    //恢复，弹出，记录prev
                    t >>= 1;
                    stk.pop();
                    prev = root;
                    root = null;//标记
                } else {
                    root = root.right;
                }
            }
            return res;
        }

    }
}
