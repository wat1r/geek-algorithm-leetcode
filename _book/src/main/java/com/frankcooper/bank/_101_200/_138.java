package com.frankcooper.bank._101_200;

import java.util.HashMap;

public class _138 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public Node copyRandomList(Node head) {
            HashMap<Node, Node> map = new HashMap<>();
            Node cur = head;
            while (cur != null) {
                map.put(cur, new Node(cur.val, cur.next, cur.random));
                cur = cur.next;
            }
            cur = head;
            while (cur != null) {
                map.get(cur).next = map.get(cur.next);
                map.get(cur).random = map.get(cur.random);
                cur = cur.next;
            }
            return map.get(head);
        }

        class Node {
            public int val;
            public Node next;
            public Node random;

            public Node() {
            }

            public Node(int _val, Node _next, Node _random) {
                val = _val;
                next = _next;
                random = _random;
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
