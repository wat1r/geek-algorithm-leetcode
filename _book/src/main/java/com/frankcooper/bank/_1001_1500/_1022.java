package com.frankcooper.bank._1001_1500;

import java.util.*;

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
