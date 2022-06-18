package com.frankcooper.platform.lintcode;

import com.frankcooper.struct.ListNode;

public class _904 {

    static class _1st {
        public ListNode plusOne(ListNode head) {
            // Write your code here
            ListNode curr = head;
            int sum = 0;
            StringBuilder sb = new StringBuilder();
            while (curr != null) {
                sb.append(curr.val);
                curr = curr.next;
            }
            sum += 1;
            String res = String.valueOf(sum);
            ListNode dummy = new ListNode(-1);
            curr = null;
            ListNode pre = dummy;
            dummy.next = curr;
            for (int i = 0; i < res.length(); i++) {
                curr = new ListNode(res.charAt(i) - '0');
                pre.next = curr;
                pre = curr;
                curr = curr.next;
            }
            return dummy.next;
        }
    }
}


