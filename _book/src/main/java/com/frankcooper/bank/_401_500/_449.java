package com.frankcooper.bank._401_500;

import java.util.*;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

public class _449 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        /**
         * Definition for a binary tree node.
         * public class TreeNode {
         * int val;
         * TreeNode left;
         * TreeNode right;
         * TreeNode(int x) { val = x; }
         * }
         */
        public class Codec {

            // Encodes a tree to a single string.
            public String serialize(TreeNode root) {
                if (root == null) return "[]";
                StringBuilder sb = new StringBuilder("[");
                Queue<TreeNode> q = new LinkedList<>();
                q.offer(root);
                while (!q.isEmpty()) {
                    TreeNode cur = q.poll();
                    if (cur != null) {
                        sb.append(cur.val).append(",");
                        q.offer(cur.left);
                        q.offer(cur.right);
                    } else {
                        sb.append("null").append(",");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("]");
                return sb.toString();
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

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            TreeNodeIOUtils.transform("[5,3,8,1,4,6,9]");
            Codec codec = new Codec();
//            codec.deserialize("1 3 4 5 6 8 9");
            codec.deserialize("5 3 8 1 4 6 9");

        }


        public static class Codec {


            // Encodes a tree to a single string.
            public String serialize(TreeNode root) {
                StringBuilder sb = new StringBuilder();
                dfs1(root, sb);
                return sb.toString();
            }


            private void dfs1(TreeNode root, StringBuilder sb) {
                if (root == null) {
                    return;
                }
                sb.append(root.val).append(" ");
                dfs1(root.left, sb);
                dfs1(root.right, sb);
            }

            // Decodes your encoded data to tree.
            public TreeNode deserialize(String data) {
                if (data.length() == 0) return null;
                String[] arr = data.split(" ");
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < arr.length; i++) {
                    list.add(Integer.parseInt(arr[i]));
                }
                TreeNode root = dfs2(list, 0, list.size() - 1);
                return root;
            }


            private TreeNode dfs2(List<Integer> list, int l, int r) {
                if (l > r) return null;
                TreeNode root = new TreeNode(list.get(l));
                int k = l + 1;
                while (k <= r && list.get(k) < list.get(l)) k++;
                root.left = dfs2(list, l + 1, k - 1);
                root.right = dfs2(list, k, r);
                return root;
            }

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
