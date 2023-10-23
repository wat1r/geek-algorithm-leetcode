package com.frankcooper.platform.leetcode.bank._101_200;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.struct.ListNode;

public class _160 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            ListNode   listA = ListNodeIOUtils.transform("[4,1,8,4,5]");
            ListNode   listB = ListNodeIOUtils.transform("[5,6,1,8,4,5]");
            handler.getIntersectionNode(listA,listB);
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

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode lA = headA, lB = headB;
            while (lA != lB) {
                lA = (lA == null) ? headB : lA.next;
                lB = (lB == null) ? headA : lB.next;
            }
            return lA;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
