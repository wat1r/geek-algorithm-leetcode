package com.frankcooper.nowcoder.swordoffer;

import com.frankcooper.struct.TreeNode;

/*import java.util.*;
import org.junit.Assert;*/
public class JZ82 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) return false;
            return dfs(root, sum);
        }

        private boolean dfs(TreeNode root, int sum) {
            //当前节点是叶子节点，开始比较val是否相等
            if (root.left == null && root.right == null && root.val == sum) return true;
            boolean res = false;
            //只要左右子树有一个是true即可
            if (root.left != null) res = res || dfs(root.left, sum - root.val);
            if (root.right != null) res = res || dfs(root.right, sum - root.val);
            return res;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) return false;
            //当前的节点是叶子节点，比较剩下的sum值是否相等
            if (root.left == null && root.right == null) return root.val == sum;
            //左右两棵子树只要一个符合即可
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
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
