package com.frankcooper.platform.leetcode.bank._1001_1500;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

/*import java.util.*;
import org.junit.Assert;*/
public class _1026 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            TreeNode root = TreeNodeIOUtils.transform("[8,3,10,1,6,null,14,null,null,4,7,13]");
            handler.maxAncestorDiff(root);

        }

        public int maxAncestorDiff(TreeNode root) {
            return dfs(root, root.val, root.val);
        }

        public int dfs(TreeNode root, int minn, int maxx) {
            if (root == null) {
                return 0;
            }
            int diff = Math.max(Math.abs(root.val - minn), Math.abs(root.val - maxx));
            minn = Math.min(minn, root.val);
            maxx = Math.max(maxx, root.val);
            diff = Math.max(dfs(root.left, minn, maxx), diff);
            diff = Math.max(dfs(root.right, minn, maxx), diff);
            return diff;
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
