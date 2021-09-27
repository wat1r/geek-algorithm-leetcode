package com.frankcooper.binary_tree;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/9/27 14:20
 * @description:
 */
public class BinaryTree {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4,5,null,null]");
            Assert.assertEquals(2, handler.maxDepth(root));
        }


        public int maxDepth(TreeNode root) {
            return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right) + 1);
        }
    }
}
