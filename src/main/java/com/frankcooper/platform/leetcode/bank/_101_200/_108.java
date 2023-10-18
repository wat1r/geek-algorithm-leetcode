package com.frankcooper.platform.leetcode.bank._101_200;

import com.frankcooper.struct.TreeNode;

/*import java.util.*;
import org.junit.Assert;*/
public class _108 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }
            return dfs(nums, 0, nums.length - 1);
        }

        private TreeNode dfs(int[] nums, int start, int end) {
            if (start == end) {
                return new TreeNode(nums[start]);
            }
            if (start > end || end >= nums.length) {
                return null;
            }
            int mid = start + (end - start) / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = dfs(nums, start, mid - 1);
            node.right = dfs(nums, mid + 1, end);
            return node;
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
