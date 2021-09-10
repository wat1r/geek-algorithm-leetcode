package com.frankcooper.bank._101_200;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _160 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode l1 = headA, l2 = headB;
            while (l1 != l2) {
                l1 = (l1 == null) ? headB : l1.next;
                l2 = (l2 == null) ? headA : l2.next;
            }
            return l1;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode l1 = headA, l2 = headB;
            while (l1 != l2) {
                l1 = (l1 == null) ? headB : l1.next;
                l2 = (l2 == null) ? headA : l2.next;
            }
            return l1;
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
