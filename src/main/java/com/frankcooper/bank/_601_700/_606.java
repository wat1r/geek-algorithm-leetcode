package com.frankcooper.bank._601_700;

import com.frankcooper.io.TreeNodeIOUtils;
import com.frankcooper.struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class _606 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public String tree2str(TreeNode t) {
            if (t == null) return "";//当前节点为空，返回""
            if (t.left == null && t.right == null) return t.val + "";//当前节点没有左右孩子节点，即叶子节点，返回这个值
            if (t.right == null) return t.val + "(" + tree2str(t.left) + ")";//当前节点只有左孩子，没有右孩子，给左孩子加上"()",右孩子不加
            return t.val + "(" + tree2str(t.left) + ")" + "(" + tree2str(t.right) + ")";//左孩子有或者没有都加 "()" || "(leftChild)"
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            TreeNode root = TreeNodeIOUtils.transform("[1,2,3,4]");
            handler.tree2str(root);
        }

        public String tree2str(TreeNode t) {
            //标识节点
            TreeNode end = new TreeNode(-1);
            StringBuilder sb = new StringBuilder();
            Deque<TreeNode> stk = new ArrayDeque<>();
            stk.push(t);
            while (!stk.isEmpty()) {
                TreeNode node = stk.pop();
                if (node == end) {//当当前节点是标识节点，开始添加右括号
                    sb.append(')');
                    continue;
                }
                sb.append('(').append(node.val);
                stk.push(end);
                //当前节点的的左节点空，右节点非空，左节点加"()"
                if (node.left == null && node.right != null) sb.append("()");
                //左节点先出栈，入的时候先入右节点，后入左节点
                if (node.right != null) stk.push(node.right);
                if (node.left != null) stk.push(node.left);
            }
            //去掉首位
            return sb.substring(1, sb.length() - 1);
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public String tree2str(TreeNode t) {
            StringBuilder sb = new StringBuilder();
            Deque<TreeNode> stk = new ArrayDeque<>();
            stk.push(t);
            //使用set来标识是否访问过，控制")"边界
            Set<TreeNode> vis = new HashSet<>();
            while (!stk.isEmpty()) {
                TreeNode node = stk.pop();
                if (vis.contains(node)) {
                    sb.append(')');
                    continue;
                }
                sb.append('(').append(node.val);
                stk.push(node);
                //当前节点的的左节点空，右节点非空，左节点加"()"
                if (node.left == null && node.right != null) sb.append("()");
                //左节点先出栈，入的时候先入右节点，后入左节点
                if (node.right != null) stk.push(node.right);
                if (node.left != null) stk.push(node.left);
                vis.add(node);
            }
            //去掉首位
            return sb.substring(1, sb.length() - 1);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
