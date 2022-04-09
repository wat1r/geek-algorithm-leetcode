package com.frankcooper.bank._101_200;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _148 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;
            //快慢指针找分隔点，slow为下一段的起点，prev是slow的前一个节点
            ListNode prev = null, slow = head, fast = head;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            //切断
            prev.next = null;
            //分治（递归）
            ListNode l1 = sortList(head);
            ListNode l2 = sortList(slow);
            //合并
            return merge(l1, l2);
        }


        public ListNode merge(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode p = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }
            if (l1 != null) p.next = l1;
            if (l2 != null) p.next = l2;
            return dummy.next;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public ListNode sortList(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            int n = 0;
            while (head != null) {
                head = head.next;
                n++;
            }

            for (int step = 1; step < n; step <<= 1) {
                ListNode prev = dummy;
                ListNode cur = dummy.next;
                while (cur != null) {
                    ListNode left = cur;
                    ListNode right = split(left, step);
                    cur = split(right, step);
                    prev = merge(left, right, prev);
                }
            }

            return dummy.next;
        }

        private ListNode split(ListNode head, int step) {
            if (head == null) return null;

            for (int i = 1; head.next != null && i < step; i++) {
                head = head.next;
            }

            ListNode right = head.next;
            head.next = null;
            return right;
        }

        private ListNode merge(ListNode left, ListNode right, ListNode prev) {
            ListNode cur = prev;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    cur.next = left;
                    left = left.next;
                } else {
                    cur.next = right;
                    right = right.next;
                }
                cur = cur.next;
            }

            if (left != null) cur.next = left;
            else if (right != null) cur.next = right;
            while (cur.next != null) cur = cur.next;
            return cur;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        //quick sort
        public ListNode sortList(ListNode head) {
            return quickSort(head, null);
        }


        public ListNode partition(ListNode first, ListNode end) {
            if (first == end) return first;
            ListNode head = first, tmp = null, prev = head;
            int pivot = head.val;
            while (prev != end) {
                tmp = prev.next;
                if (tmp == end) break;
                if (tmp != null && tmp.val < pivot) {
                    prev.next = tmp.next;
                    tmp.next = head;
                    head = tmp;
                } else {
                    prev = tmp;
                }
            }
            return head;
        }

        public ListNode quickSort(ListNode start, ListNode end) {
            if (start == end) return start;
            ListNode partition = partition(start, end);
            ListNode p1 = quickSort(partition, start);
            ListNode p2 = quickSort(start.next, end);
            start.next = p2;
            return p1;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null)
                return head;
            ListNode fast = head.next, slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode tmp = slow.next;
            slow.next = null;
            ListNode left = sortList(head);
            ListNode right = sortList(tmp);
            ListNode h = new ListNode(0);
            ListNode res = h;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    h.next = left;
                    left = left.next;
                } else {
                    h.next = right;
                    right = right.next;
                }
                h = h.next;
            }
            h.next = left != null ? left : right;
            return res.next;
        }


    }
}
