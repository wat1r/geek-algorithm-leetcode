package com.frankcooper.nowcoder.swordoffer;

import com.frankcooper.struct.ListNode;

public class JZ {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public ListNode EntryNodeOfLoop(ListNode pHead) {
            ListNode slow = pHead, fast = pHead;
            boolean f = false;//标志位
            while (fast.next != null && fast.next.next != null) {
                //f的标志位要是true的时候，并且slow==fast
                //初始时，slow和fast是相等的，如果没有标志位会提前退出
                if (slow == fast && f) break;
                slow = slow.next;
                fast = fast.next.next;
                f = true;//设置标志位
            }
            //如果slow和fast不是同一个节点 或者 slow和fast相同，但是是初始时的slow = pHead, fast = pHead
            if (slow != fast || !f) return null;
            slow = pHead;//slow回到起点，slow和fast步调一致，第一个相遇的点就是环形链表入口
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();

        }

        public ListNode EntryNodeOfLoop(ListNode pHead) {
            if (pHead == null || pHead.next == null) return null;
            //开始时 slow和fast错开一个位置
            ListNode slow = pHead, fast = pHead.next;
            while (fast.next != null && fast.next.next != null) {
                if (slow == fast) break;
                slow = slow.next;
                fast = fast.next.next;
            }
            //如果这时候不是第一次相遇，而fast已经到链表的末尾了，说明没有环
            if (slow != fast) return null;
            //头节点开始，且
            ListNode cur = pHead;
            while (cur != slow.next) {
                cur = cur.next;
                slow = slow.next;
            }
            return cur;
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
