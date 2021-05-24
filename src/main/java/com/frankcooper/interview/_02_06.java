package com.frankcooper.interview;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _02_06 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
/*            ListNode l1 = new ListNode(1);
            ListNode l2 = new ListNode(2);
            l1.next = l2;
            handler.isPalindrome(l1);*/


/*            ListNode l1 = new ListNode(1);
            ListNode l2 = new ListNode(2);
            ListNode l3 = new ListNode(2);
            ListNode l4 = new ListNode(2);
            l1.next = l2;
            l2.next = l3;
            l3.next = l4;
            handler.isPalindrome(l1);*/


            ListNode l1 = new ListNode(1);
            ListNode l2 = new ListNode(2);
            ListNode l3 = new ListNode(3);
            ListNode l4 = new ListNode(2);
            ListNode l5 = new ListNode(1);
            l1.next = l2;
            l2.next = l3;
            l3.next = l4;
            l4.next = l5;
            handler.isPalindrome(l1);
        }


        public boolean isPalindrome(ListNode head) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode slow = dummy, fast = dummy;
            Stack<Integer> stk = new Stack<>();
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                stk.push(slow.val);
                fast = fast.next.next;
            }
            //判断奇偶回文
            ListNode p = fast.next == null ? slow.next : slow.next.next;
            while (p != null) {
                if (p.val != stk.pop()) return false;
                p = p.next;
            }
            return true;
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
