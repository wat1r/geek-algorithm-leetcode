package com.frankcooper.lintcode;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _97 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int maxDepth(TreeNode root) {
            return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
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
