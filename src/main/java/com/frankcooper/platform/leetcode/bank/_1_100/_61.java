package com.frankcooper.platform.leetcode.bank._1_100;

import com.frankcooper.struct.ListNode;

public class _61 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null) return head;
            int n = 1;
            ListNode cur = head;
            //指针到末尾，记录总的个数
            while (cur.next != null) {
                cur = cur.next;
                n++;
            }
            cur.next = head;//尾部和头部相连，形成环
            int j = n - k % n - 1;//要分隔的点
            cur = head;
            for (int i = 0; i < j; i++) {
                cur = cur.next;
            }
            ListNode newHead = cur.next;
            cur.next = null;
            return newHead;
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
