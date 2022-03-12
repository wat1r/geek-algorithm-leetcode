package com.frankcooper.bank._101_200;

import com.frankcooper.struct.ListNode;

public class _143 {


    static class _1st {


        public static void main(String[] args) {
            ListNode l0 = new ListNode(1);
            ListNode l1 = new ListNode(2);
            ListNode l2 = new ListNode(3);
            ListNode l3 = new ListNode(4);
            ListNode l4 = new ListNode(5);
            l0.next = l1;
            l1.next = l2;
            l2.next = l3;
            l3.next = l4;
            _1st handler = new _1st();
            handler.reorderList(l0);
        }


        public void reorderList(ListNode head) {
            if (head == null || head.next == null) return;
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode slow = head, fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode tmp = slow.next;
            slow.next = null;
            ListNode second = reverse(tmp);
            ListNode first = dummy.next;
            while (second != null) {
                ListNode l2 = second.next;
                second.next = first.next;
                first.next = second;
                first = second.next;
                second = l2;
            }
        }


        private ListNode reverse(ListNode head) {
            ListNode cur = head, pre = null, next;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }


    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public void reorderList(ListNode head) {
            ListNode cur = head;
            int cnt = 0;//链表的数量
            while (cur != null) {
                cnt++;
                cur = cur.next;
            }
            //头插的次数，偶数个的话-1
            int times = (cnt % 2 == 1) ? cnt / 2 : cnt / 2 - 1;
            cur = head;//重置cur节点
            for (int i = 0; i < times; i++) {
                ListNode t = head;//找到要移动的节点前面的那个节点
                for (int j = 2; j < cnt; j++) {
                    t = t.next;
                }
                //头插
                t.next.next = cur.next;
                cur.next = t.next;
                t.next = null;
                if (cur.next != null) cur = cur.next.next;
            }

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
