package com.frankcooper.bank._201_300;

import java.util.*;

import com.frankcooper.struct.ListNode;
import org.junit.Assert;

public class _234 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode mid = slow.next;//找到后半部分的起点
            slow.next = null;
            ListNode right = reverseList(mid);//反转后半部分
            ListNode left = head; //前半部分的节点
            while (left != null && right != null) {//后半部分的节点
                if (left.val != right.val) {
                    return false;
                }
                left = left.next;
                right = right.next;
            }
            return true;
        }

        public ListNode reverseList(ListNode head) {
            ListNode cur = head, next, prev = null;
            while (cur != null) {
                // 记录后继结点
                next = cur.next;
                // 后继指针逆向
                cur.next = prev;
                // 记录当前结点
                prev = cur;
                // 下一结点成为当前结点
                cur = next;
            }
            return prev;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) return true;
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode mid = slow.next;
            ListNode rptr = reverse(mid);
            ListNode lptr = head;
            while (lptr != null && rptr != null) {
                if (lptr.val != rptr.val) return false;
                lptr = lptr.next;
                rptr = rptr.next;
            }
            return true;

        }


        private ListNode reverse(ListNode head) {
            ListNode prev = null, cur = head;
            while (cur != null) {
                ListNode nxt = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nxt;
            }
            return prev;
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
