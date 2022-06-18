package com.frankcooper.platform.leetcode.bank._401_500;

import com.frankcooper.struct.ListNode;

import java.util.Stack;

/**
 * @Date 2020/8/3
 * @Author Frank Cooper
 * @Description
 */
public class _445 {
    static _445 handler = new _445();

    public static void main(String[] args) {
//        handler.addDigits(3182);

        handler.testOne();
    }


    private void testOne() {
        ListNode _7 = new ListNode(7);
        ListNode _2 = new ListNode(2);
        ListNode _4_1 = new ListNode(4);
        ListNode _3 = new ListNode(3);
        _7.next = _2;
        _2.next = _4_1;
        _4_1.next = _3;

        ListNode _5 = new ListNode(5);
        ListNode _6 = new ListNode(6);
        ListNode _4_2 = new ListNode(4);

        _5.next = _6;
        _6.next = _4_2;

        handler.addTwoNumbers(_7, _5);

    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int sum = 0;
            int x = s1.isEmpty() ? 0 : s1.pop();
            int y = s2.isEmpty() ? 0 : s2.pop();
            sum = x + y + carry;
            carry = sum / 10;
            ListNode curr = new ListNode(sum % 10);
            curr.next = head;
            head = curr;
        }
        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next = head;
            head = curr;
        }
        return head;
    }


//    public int addDigits(int num) {
//        while (num >= 10) {
//            int remain = 0;
//            while (num > 0) {
//                remain += num % 10;
//                num /= 10;
//            }
//            num = remain;
//        }
//        return num;
//    }

}
