package com.frankcooper.platform.leetcode.interview;

import com.frankcooper.struct.ListNode;
import com.frankcooper.utils.ListNodeUtils;

public class _02_04 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] arr = new int[]{1, 4, 3, 2, 5, 2};
            ListNode head = new ListNode(-1);
            ListNode dummy = head;
            for (int i = 0; i < arr.length; i++) {
                int cur = arr[i];
                ListNode curNode = new ListNode(cur);
                head.next = curNode;
                head = head.next;
            }
            handler.partition(dummy.next, 3);
        }


        public ListNode partition(ListNode head, int x) {
            ListNode left = new ListNode(-1), right = new ListNode(-1);
            ListNode leftDummy = left, rightDummy = right;
            while (head != null) {
                if (head.val < x) {
                    left.next = head;
                    left = left.next;
                } else {
                    right.next = head;
                    right = right.next;
                }
                head = head.next;
            }
            //比如[1,4,3,2,5,2] x=3这个例子, leftDummy->1->2->2 如果没有断开操作,
            // 那 rightDummy->4->3->5->2就不正确了,因为原链表中5->2,没有断开,导致后面拼接操作会把这个链表成环
            right.next = null;
            left.next = rightDummy.next;
            return leftDummy.next;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] arr = new int[]{1, 4, 3, 2, 5, 2};
            ListNode head = ListNodeUtils.buildListNodeList(arr);
            handler.partition(head, 3);
        }

        /**
         * 类似荷兰国旗的想法，prev指针指向小于x的 ，cur游走进行判断
         *
         * @param head
         * @param x
         * @return
         */
        public ListNode partition(ListNode head, int x) {
            ListNode prev = head, cur = head;
            while (cur != null) {
                if (cur.val < x) {//当前节点的值需要进行交换
                    int t = prev.val;//先存下来prev的val
                    prev.val = cur.val;//swap
                    cur.val = t;
                    prev = prev.next;//prev跳到下一个指针
                }
                cur = cur.next;//cur一直游走
            }
            return head;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        public ListNode partition(ListNode head, int x) {
            if (head == null) return null;
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = head;
            head = head.next;
            while (head != null) {
                if (head.val < x) {
                    prev.next = head.next;
                    head.next = dummy.next;//当前节点的指向 dummy节点的下一个节点
                    dummy.next = head;//当前要移动的点
                    head = prev.next;
                } else {
                    prev = prev.next;//顺序移动
                    head = head.next;//顺序移动
                }
            }
            return dummy.next;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public ListNode partition(ListNode head, int x) {
            if (head == null) return null;
            ListNode cur = head;
            while (cur.next != null) {
                if (cur.next.val < x) {
                    ListNode t = cur.next;
                    cur.next = cur.next.next;
                    t.next = head;
                    head = t;
                } else {
                    cur = cur.next;
                }
            }
            return head;
        }
    }
}
