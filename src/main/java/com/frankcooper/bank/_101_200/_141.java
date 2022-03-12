package com.frankcooper.bank._101_200;

import java.util.*;

import com.frankcooper.struct.ListNode;
import com.frankcooper.utils.ListNodeUtils;
import org.junit.Assert;

public class _141 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast.next != null && fast.next.next != null) {
                if (slow == fast) {
                    return true;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow == fast;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            ListNode head = ListNodeUtils.buildListNodeList(new int[]{3, 2, 0, -4});
            ListNode l1 = head.next;
            ListNode l2 = l1.next;
            ListNode l3 = l2.next;
            l3.next = l1;
            handler.hasCycle(head);
        }

        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) return false;
            ListNode slow = head, fast = head.next;
            while (fast.next != null && fast.next.next != null) {
                if (slow == fast) return true;
                slow = slow.next;
                fast = fast.next.next;
            }
            return false;
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
