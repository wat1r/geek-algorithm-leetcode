package com.frankcooper.bank;

import com.frankcooper.struct.ListNode;

public class _725 {

    static class _1st {
        public ListNode[] splitListToParts(ListNode root, int k) {

            int sum = 0;
            ListNode dummy = new ListNode(-1);
            dummy.next = root;
            ListNode curr = root;
            while (curr != null) {
                sum++;
                curr = curr.next;
            }
            int avg = sum / k;
            int carry = sum % k;
            ListNode[] res = new ListNode[k];
            curr = root;
            // ListNode pre =null;
            System.out.printf("k:%d,avg:%d,carry:%d\n", k, avg, carry);
            for (int i = 0; i < k; i++) {
                int t;
                // if(i==0) t = avg+carry;
                // else t = avg ;
                ListNode head = new ListNode(-1);
                ListNode p = head;
                for (int j = 0; j < avg + (i < carry ? 1 : 0) && curr != null; j++) {
                    System.out.printf("%d\n", curr.val);
                    p.next = new ListNode(curr.val);
                    p = p.next;
                    if (curr != null) curr = curr.next;
                }
                res[i] = head.next;
            }

            return res;


        }
    }
}
