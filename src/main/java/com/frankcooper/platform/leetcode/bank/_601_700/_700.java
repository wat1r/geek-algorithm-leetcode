package com.frankcooper.platform.leetcode.bank._601_700;

import com.frankcooper.struct.TreeNode;

/*import java.util.*;
import org.junit.Assert;*/
public class _700 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) return null;
            if (root.val == val) return root;
            else if (root.val > val) return searchBST(root.left, val);
            else return searchBST(root.right, val);
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
