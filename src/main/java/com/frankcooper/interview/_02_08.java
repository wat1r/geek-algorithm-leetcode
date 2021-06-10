package com.frankcooper.interview;

import com.frankcooper.struct.ListNode;

import java.util.List;

import java.util.*;

import org.junit.Assert;

public class _02_08 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * 同142题
         * @param head
         * @return
         */
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null) return null;
            ListNode slow = head, fast = head.next;
            while (fast.next != null && fast.next.next != null) {
                if (slow == fast) break;
                slow = slow.next;
                fast = fast.next.next;
            }
            if (slow != fast) return null;
            ListNode cur = head;
            while (cur != slow.next) {
                cur = cur.next;
                slow = slow.next;
            }
            return cur;
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
