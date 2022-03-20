package com.frankcooper.bank._1_100;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _23 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            int len = lists.length;
            return mergeKLists(lists, 0, len - 1);
        }

        private ListNode mergeKLists(ListNode[] lists, int l, int r) {
            if (l == r) return lists[l];
            int mid = l + (r - l) / 2;
            ListNode l1 = mergeKLists(lists, l, mid);
            ListNode l2 = mergeKLists(lists, mid + 1, r);
            return mergeTwoSortedListNode(l1, l2);
        }

        private ListNode mergeTwoSortedListNode(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            if (l1.val < l2.val) {
                l1.next = mergeTwoSortedListNode(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoSortedListNode(l1, l2.next);
                return l2;
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public ListNode mergeKLists(ListNode[] lists) {
            Queue<ListNode> pq = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
            for (ListNode l : lists) {
                if (l != null) pq.offer(l);
            }
            ListNode dummy = new ListNode(-1);
            ListNode curr = dummy;
            while (!pq.isEmpty()) {
                ListNode tmp = pq.poll();
                curr.next = tmp;
                curr = curr.next;
                if (tmp.next != null) pq.offer(tmp.next);
            }
            return dummy.next;
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
