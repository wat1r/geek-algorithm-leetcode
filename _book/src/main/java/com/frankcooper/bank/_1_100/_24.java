package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _24 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy;
            while (prev.next != null && prev.next.next != null) {
                ListNode first = prev.next;
                ListNode second = prev.next.next;
                ListNode nxt = second.next;
                //step1
                second.next = first;
                //step2
                first.next = nxt;
                //step3
                prev.next = second;
                prev = first;
            }
            return dummy.next;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = head.next;
            head.next = swapPairs(newHead.next);
            newHead.next = head;
            return newHead;
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
