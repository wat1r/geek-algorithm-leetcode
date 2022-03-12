package com.frankcooper.bank._101_200;

import com.frankcooper.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/12 17:01
 * @description:
 */
public class _103 {

    static class _1st {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int level = 0;
            while (!queue.isEmpty()) {
                List<Integer> levelList = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    if (level % 2 == 0) levelList.add(cur.val);
                    else levelList.add(0, cur.val);
                    if (cur.left != null) queue.offer(cur.left);
                    if (cur.right != null) queue.offer(cur.right);
                }
                level++;
                result.add(levelList);
            }
            return result;
        }
    }
}
