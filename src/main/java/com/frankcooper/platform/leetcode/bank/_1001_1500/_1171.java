package com.frankcooper.platform.leetcode.bank._1001_1500;

import java.util.*;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _1171 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            ListNode head = ListNodeIOUtils.transform("[1,2,-3,3,1]");
            handler.removeZeroSumSublists(head);

        }

        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            int prefix = 0;
            Map<Integer, ListNode> map = new HashMap<>();
            for (ListNode node = dummy; node != null; node = node.next) {
                prefix += node.val;
                map.put(prefix, node);
            }
            prefix = 0;
            for (ListNode node = dummy; node != null; node = node.next) {
                prefix += node.val;
                node.next = map.get(prefix).next;
            }
            return dummy.next;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            Map<Integer, ListNode> map = new HashMap<>();
            int sum = 0;
            ListNode p = head;
            map.put(0, dummy);
            while (p != null) {
                sum += p.val;
                if (map.containsKey(sum)) {
                    ListNode node = map.get(sum);
                    ListNode t = node.next;
                    node.next = p.next;
                    int tv = sum;
                    while (t != p) {
                        tv += t.val;
                        map.remove(tv);
                        t = t.next;
                    }
                } else {
                    map.put(sum, p);
                }
                p = p.next;
            }
            return dummy.next;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public ListNode removeZeroSumSublists(ListNode head) {
            if (head == null) return null;
            if (head.next == null) return head.val == 0 ? null : head;
            if (head.val == 0) return removeZeroSumSublists(head.next);
            ListNode cur = head.next;
            int num = head.val;
            while (cur != null) {
                num += cur.val;
                if (num == 0) break;
                cur = cur.next;
            }
            if (num == 0) return removeZeroSumSublists(cur.next);
            head.next = removeZeroSumSublists(head.next);
            return head;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
