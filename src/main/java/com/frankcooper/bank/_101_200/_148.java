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
