package com.frankcooper.bank._101_200;

import com.frankcooper.struct.ListNode;

import java.util.HashMap;
import java.util.Map;

public class _138 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public Node copyRandomList(Node head) {
            if (head == null) return null;
            HashMap<Node, Node> map = new HashMap<>();
            Node newHead = new Node(head.val);
            map.put(head, newHead);
            while (head != null) {
                Node mirror = map.get(head);
                if (head.next != null) {
                    map.putIfAbsent(head.next, new Node(head.next.val));
                    mirror.next = map.get(head.next);
                }
                if (head.random != null) {
                    map.putIfAbsent(head.random, new Node(head.random.val));
                    mirror.random = map.get(head.random);
                }
                head = head.next;
            }
            return newHead;
        }

    }

    static class Node {
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

        public Node(int val) {
            this.val = val;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        Map<Node, Node> map = new HashMap<>();

        public Node copyRandomList(Node head) {
            if (head == null) return null;
            return dfs(head);
        }


        private Node dfs(Node cur) {
            if (cur == null) return null;
            if (map.containsKey(cur)) return map.get(cur);
            //复制一个节点
            Node mirror = new Node(cur.val);
            //记忆化
            map.put(cur, mirror);
            //next 和 random 指针处理
            mirror.next = dfs(cur.next);
            mirror.random = dfs(cur.random);
            return mirror;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public Node copyRandomList(Node head) {
            Node cur = head;
            while (cur != null) {
                Node mirror = new Node(cur.val);
                mirror.next = cur.next;
                cur.next = mirror;
                cur = cur.next.next;
            }
            cur = head;
            while (cur != null) {
                if (cur.random != null) {
                    cur.next.random = cur.random.next;
                }
                cur = cur.next.next;
            }
            Node dummy = new Node(-1);
            Node p = dummy;
            cur = head;
            while (cur != null) {
                Node mirror = cur.next;
                p.next = mirror;
                p = p.next;
                cur.next = mirror.next;
                cur = cur.next;
            }
            return dummy.next;

        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
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
    }
}
