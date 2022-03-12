package com.frankcooper.swordoffer;

import java.util.*;

import com.frankcooper.struct.Node;
import org.junit.Assert;

public class Sword_36 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        Node prev, head;

        public Node treeToDoublyList(Node root) {
            if (root == null) return null;
            dfs(root);
            head.left = prev;
            prev.right = head;
            return head;
        }


        private void dfs(Node cur) {
            if (cur == null) return;
            dfs(cur.left);
            if (prev == null) head = cur;
            else prev.right = cur;
            cur.left = prev;
            prev = cur;
            dfs(cur.right);
        }

    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
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
