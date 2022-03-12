package com.frankcooper.bank._1001_1500;

import com.frankcooper.struct.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _1019 {


    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Stack<Integer> stk = new Stack<>();
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            while (!stk.isEmpty() && list.get(stk.peek()) < list.get(i)) {
                int idx = stk.pop();
                res[idx] = list.get(i);
            }
            stk.push(i);
        }
        return res;
    }

}
