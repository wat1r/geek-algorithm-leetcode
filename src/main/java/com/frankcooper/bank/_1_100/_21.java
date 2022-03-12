package com.frankcooper.bank._1_100;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _21 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
            if (l1.val > l2.val) {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            } else {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return l1 == null ? l2 : l1;
            }
            ListNode head = new ListNode(0);
            ListNode node = head;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    node.next = l1;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    l2 = l2.next;
                }
                node = node.next;
            }
            if (l1 != null) {
                node.next = l1;
            } else {
                node.next = l2;
            }
            return head.next;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null || list2 == null) return list1 == null ? list2 : list1;
            if (list1.val > list2.val) {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            } else {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null || list2 == null) return list1 == null ? list2 : list1;
            if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            } else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
    }
}
