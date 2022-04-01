package com.frankcooper.bank._201_300;

import java.util.*;

import com.frankcooper.struct.TreeNode;
import org.junit.Assert;

public class _297 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


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


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public class Codec {

            // Encodes a tree to a single string.
            public String serialize(TreeNode root) {
                if (root == null) return "null";
                return root.val + "," + serialize(root.left) + "," + serialize(root.right);
            }

            // Decodes your encoded data to tree.
            public TreeNode deserialize(String data) {
                Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
                return dfs(queue);
            }

            private TreeNode dfs(Queue<String> queue) {
                String v = queue.poll();
                if ("null".equals(v)) return null;
                TreeNode node = new TreeNode(Integer.parseInt(v));
                node.left = dfs(queue);
                node.right = dfs(queue);
                return node;
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
