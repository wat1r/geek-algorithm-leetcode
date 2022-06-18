package com.frankcooper.platform.leetcode.bank._201_300;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

/*import java.util.*;
import org.junit.Assert;*/
public class _226 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode root = TreeNodeIOUtils.transform("[4,2,7,1,3,6,9]");
            handler.invertTree(root);

        }

        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            TreeNode l = invertTree(root.left);
            TreeNode r = invertTree(root.right);
            root.left = r;
            root.right = l;
            return root;
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
