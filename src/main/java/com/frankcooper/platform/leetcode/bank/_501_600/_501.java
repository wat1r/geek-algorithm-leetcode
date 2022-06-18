package com.frankcooper.platform.leetcode.bank._501_600;


import com.frankcooper.struct.TreeNode;

import java.util.*;

public class _501 {

    static class _1st {
        public static void main(String[] args) {
            TreeNode t1 = new TreeNode(1);
            TreeNode t2 = new TreeNode(2);
            TreeNode t3 = new TreeNode(2);
            t1.right = t2;
            t2.left = t3;
            _1st handler = new _1st();
            handler.findMode(t1);

        }


        public int[] findMode(TreeNode root) {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            Map<Integer, Integer> cnt = new HashMap<>();
            Stack<TreeNode> stk = new Stack<>();
            while (!stk.isEmpty() || root != null) {
                while (root != null) {
                    stk.push(root);
                    root = root.left;
                }
                root = stk.pop();
                cnt.put(root.val, cnt.getOrDefault(root.val, 0) + 1);
                Set<Integer> set = map.getOrDefault(cnt.get(root.val), new HashSet<>());
                set.add(root.val);
                map.put(cnt.get(root.val), set);
                root = root.right;
            }
            int size = 0;
            for (Integer i : map.keySet()) {
                if (size < i) size = map.get(i).size();
            }
            System.out.printf("%d\n", size);
            int[] arr = new int[size];
            int idx = 0;
            for (int i : map.get(size)) {
                arr[idx++] = i;
            }
            return arr;
        }
    }

    static class _2nd {

        List<Integer> list = new ArrayList<>();
        int cnt = 0, curr = 0, maxCnt = 0; // 当前出现的 val 的次数， 当前的值 目前最大的出现次数

        public int[] findMode(TreeNode root) {
            inOrder(root);
            int[] arr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
            return arr;
        }

        private void inOrder(TreeNode root) {
            if (root == null) return;
            inOrder(root.left);
            int val = root.val;
            if (val == curr) {
                cnt++;
            } else {
                cnt = 1;
                curr = val;
            }
            if (cnt == maxCnt) {//
                list.add(val);
            } else if (cnt > maxCnt) {
                maxCnt = cnt;
                list.clear();
                list.add(val);
            }

            inOrder(root.right);
        }
    }
}
