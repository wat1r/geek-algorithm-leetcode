package com.frankcooper.platform.leetcode.swordoffer_II;

import com.frankcooper.struct.ListNode;

/*import java.util.*;
import org.junit.Assert;*/
public class Sword_II_029 {

    //同 708
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            ListNode head = ListNodeIOUtils.transform("[3,4,1]");
//            ListNode l1 = head.next.next;
//            l1.next = head;
//            handler.insert(head, 2);
//            ListNode head = ListNodeIOUtils.transform("[1]");
//            head.next = head;
//            handler.insert(head, 0);
            //[1,3,5]
            //0
//            ListNode head = ListNodeIOUtils.transform("[1,3,5]");
//            ListNode l5 = head.next.next;
//            l5.next = head;
//            handler.insert(head, 0);
            //[1,3,5]
            //3
            //ans：[1,3,3,5]
        }


        public ListNode insert(ListNode head, int insertVal) {
            if (head == null) {
                ListNode insertNode = new ListNode(insertVal);
                insertNode.next = insertNode;
                return insertNode;
            }
            ListNode cur = head;
            while (cur.next != head) {
                if (cur.val < cur.next.val) {
                    if (insertVal >= cur.val && insertVal <= cur.next.val) {
                        insert(insertVal, cur);
                        return head;
                    }
                }
                if (cur.val > cur.next.val) {
                    if ((insertVal < cur.val && insertVal < cur.next.val) || insertVal > cur.val) {
                        insert(insertVal, cur);
                        return head;
                    }
                }
                cur = cur.next;
            }
            insert(insertVal, cur);
            return head;
        }


        private static void insert(int insertVal, ListNode cur) {
            ListNode insertNode = new ListNode(insertVal);
            ListNode nxt = cur.next;
            cur.next = insertNode;
            insertNode.next = nxt;
        }


    }


//    static class _2nd {
//        public Node insert(Node head, int insertVal) {
//            if (head == null) {
//                Node insertNode = new Node(insertVal);
//                insertNode.next = insertNode;
//                return insertNode;
//            }
//            Node cur = head;
//            while (cur.next != head) {
//                if (cur.val < cur.next.val) {
//                    if (insertVal >= cur.val && insertVal <= cur.next.val) {
//                        insert(insertVal, cur);
//                        return head;
//                    }
//                }
//                if (cur.val > cur.next.val) {
//                    if (insertVal < cur.val && insertVal < cur.next.val) {
//                        insert(insertVal, cur);
//                        return head;
//                    }
//                    if (insertVal > cur.val) {
//                        insert(insertVal, cur);
//                        return head;
//                    }
//                }
//                cur = cur.next;
//            }
//            insert(insertVal, cur);
//            return head;
//        }
//
//        private void insert(int insertVal, Node cur) {
//            Node insertNode = new Node(insertVal);
//            Node nxt = cur.next;
//            cur.next = insertNode;
//            insertNode.next = nxt;
//        }
//    }


    static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
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



