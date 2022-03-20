package com.frankcooper.bank._1_100;

import com.frankcooper.io.ListNodeIOUtils;
import com.frankcooper.struct.ListNode;

public class _82 {

    static class _1st {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode pre = dummy, curr;
            while (pre.next != null) {
                curr = pre.next;
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                if (curr != pre.next) {
                    pre.next = curr.next;
                } else {
                    pre = pre.next;
                }
            }
            return dummy.next;
        }
    }

    static class _2nd {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            if (head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                return deleteDuplicates(head.next);
            } else {
                head.next = deleteDuplicates(head.next);
                return head;
            }
        }
    }

    static class _3rd {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode pre = dummy, curr;
            while (pre.next != null) {
                curr = pre.next;
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                if (curr != pre.next) {
                    pre.next = curr.next;
                } else {
                    pre = pre.next;
                }

            }
            return dummy.next;
        }
    }


    static class _4th {

        public static void main(String[] args) {
            _4th handler = new _4th();
            ListNode head = ListNodeIOUtils.transform("[1,2,3,3,4,4,5]");
            handler.deleteDuplicates(head);
        }

        public ListNode deleteDuplicates(ListNode head) {
            //哑结点
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            //prev 和 cur节点
            ListNode prev = dummy, cur;
            while (prev.next != null) {//prev的next指针不为空
                cur = prev.next;//每一轮的cur是prev的后一个
                //当cur的后之后的cur相同跳过之后的
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                //需要判断链接状态，如果是相同的说明上一步没有重复的，不同的话，说明有重复的
                if (prev.next != cur) {
                    prev.next = cur.next;
                } else {
                    prev = prev.next;
                }
            }
            return dummy.next;
        }
    }
}
