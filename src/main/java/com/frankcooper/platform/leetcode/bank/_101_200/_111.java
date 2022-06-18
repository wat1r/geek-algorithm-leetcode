package com.frankcooper.platform.leetcode.bank._101_200;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

import java.util.*;

/**
 * @Date 2020/7/30
 * @Author Frank Cooper
 * @Description
 */
public class _111 {
    static _111 handler = new _111();

    public static void main(String[] args) {

    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int tmp = Integer.MAX_VALUE;
        if (root.left != null) tmp = Math.min(tmp, minDepth(root.left));
        if (root.right != null) tmp = Math.min(tmp, minDepth(root.right));
        return tmp + 1;
    }

    public int minDepth2nd(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left == null && curr.right == null) return level;
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return level;
    }


    static class _3rd {
        public int minDepth(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            int res = Integer.MAX_VALUE;
            if (root.left != null) res = Math.min(res, minDepth(root.left));
            if (root.right != null) res = Math.min(res, minDepth(root.right));
            return res + 1;
        }
    }

    static class _4th {


        public static void main(String[] args) {
            _4th handler = new _4th();
            TreeNode root = TreeNodeIOUtils.transform("[3,9,20,null,null,15,7]");
//            TreeNode root = TreeNodeIOUtils.transform("[2,null,3,null,4,null,5,null,6]");
            int k = 2;
//            handler.getBottomKMinDepth(root, k);
            List<Integer> list = Arrays.asList(7, 2, 3, 3, 4, 5);
            handler.getBottomKMinDepth(root, 2);
        }

        //k:叶子节点， v:叶子节点的最小深度
        Map<TreeNode, Integer> map = new HashMap<>();

        public int getBottomKMinDepth(TreeNode root, int k) {
            //遍历获取map
            helper(root, 0);
            List<Integer> list = new ArrayList<>(map.values());
            int res = getBottomK(list, k);
            return res;
        }

        private int getBottomK(List<Integer> list, int k) {
            //大根堆，从栈顶到栈底 依次从大到小
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int x : list) {
                if (!pq.isEmpty() && pq.size() >= k && pq.peek() > x) pq.poll();
                if (pq.isEmpty() || pq.size() < k) pq.offer(x);
            }
            return pq.isEmpty() ? -1 : pq.peek();
        }


        private int helper(TreeNode root, int depth) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) {
                map.put(root, depth + 1);
                return 1;
            }
            int res = Integer.MAX_VALUE;
            if (root.left != null) res = Math.min(res, helper(root.left, depth + 1));
            if (root.right != null) res = Math.min(res, helper(root.right, depth + 1));
            return res + 1;
        }
    }


}
