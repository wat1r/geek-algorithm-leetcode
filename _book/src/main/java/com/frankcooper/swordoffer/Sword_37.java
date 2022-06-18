package com.frankcooper.platform.leetcode.swordoffer;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class Sword_37 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


            List<Integer> list = new ArrayList<Integer>() {{
                add(1);
                add(2);
            }};
            Map<Integer, Integer> map = new HashMap<Integer, Integer>() {{
                put(1, 1);
                put(2, 2);
            }};

            Set<Integer> set = new HashSet<Integer>() {{
                add(1);
                add(2);
            }};


        }


        public class Codec {

            // Encodes a tree to a single string.
            public String serialize(TreeNode root) {
                if (root == null) return "[]";
                StringBuilder res = new StringBuilder("[");
                Queue<TreeNode> q = new LinkedList<>();
                q.offer(root);
                while (!q.isEmpty()) {
                    TreeNode cur = q.poll();
                    if (cur != null) {
                        res.append(cur.val + ",");
                        q.offer(cur.left);
                        q.offer(cur.right);
                    } else {
                        res.append("null,");
                    }
                }
                res.deleteCharAt(res.length() - 1);
                res.append("]");
                return res.toString();
            }

            // Decodes your encoded data to tree.
            public TreeNode deserialize(String data) {
                if (data.equals("[]")) return null;
                String[] arr = data.substring(1, data.length() - 1).split(",");
                TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
                Queue<TreeNode> q = new LinkedList<>();
                q.offer(root);
                int idx = 1;
                while (!q.isEmpty()) {
                    TreeNode cur = q.poll();
                    if (!arr[idx].equals("null")) {
                        cur.left = new TreeNode(Integer.parseInt(arr[idx]));
                        q.offer(cur.left);
                    }
                    idx++;
                    if (!arr[idx].equals("null")) {
                        cur.right = new TreeNode(Integer.parseInt(arr[idx]));
                        q.offer(cur.right);
                    }
                    idx++;
                }
                return root;
            }
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
