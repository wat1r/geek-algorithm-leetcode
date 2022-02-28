package com.frankcooper.bank._201_300;

import java.util.*;

import com.frankcooper.struct.ListNode;
import com.frankcooper.utils.ListNodeUtils;
import org.junit.Assert;

public class _203 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            ListNode head = ListNodeUtils.buildListNodeList(new int[]{1, 2, 6, 3, 4, 5, 6});
            int val = 6;
            head = ListNodeUtils.buildListNodeList(new int[]{7, 7, 7, 7});
            val = 7;
            handler.removeElements(head, val);

        }

        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy;
            while (prev.next != null) {
                if (prev.next.val == val) {
                    prev.next = prev.next.next;
                } else {
                    prev = prev.next;
                }
            }
            return dummy.next;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public ListNode removeElements(ListNode head, int val) {
            if (head == null) return null;
            head.next = removeElements(head.next, val);
            if (head.val == val) return head.next;
            else return head;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            ListNode head = ListNodeUtils.buildListNodeList(new int[]{1, 2, 6, 3, 4, 5, 6});
            handler.removeElements(head, 6);
        }

        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy;
            while (prev.next != null) {
                if (prev.next.val == val) {
                    prev.next = prev.next.next;
                } else {
                    prev = prev.next;
                }
            }
            return dummy.next;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public ListNode removeElements(ListNode head, int val) {
            if (head == null) return null;
            head.next = removeElements(head.next, val);
            if (head.val == val) return head.next;
            return head;
        }
    }
}
