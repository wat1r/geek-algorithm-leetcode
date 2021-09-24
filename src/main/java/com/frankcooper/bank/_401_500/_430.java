package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _430 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public Node flatten(Node head) {
            dfs(head);
            return head;
        }

        private Node dfs(Node head) {
            Node cur = head;

            Node last = null;

            while (cur != null) {
                Node nxt = cur.next;
                if (cur.child != null) {
                    Node childLast = dfs(cur.child);

                    nxt = cur.next;
                    //切断当前节点与child节点
                    cur.next = cur.child;
                    cur.child.prev = cur;
                    //如果nxt节点不为null，将child的最后一个节点和nxt节点接上
                    if (nxt != null) {
                        childLast.next = nxt;
                        nxt.prev = childLast;
                    }
                    //断开child节点，last节点后移
                    cur.child = null;
                    last = childLast;
                } else {
                    last = cur;//last节点后移
                }
                cur = nxt;//cur节点后移

            }

            return last;
        }


        class Node {
            public int val;
            public Node prev;
            public Node next;
            public Node child;


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
