package com.frankcooper.utils;

import com.frankcooper.struct.ListNode;

public class ListNodeUtils {

    /**
     * 返回头结点，传入数组
     *
     * @param arr
     * @return
     */
    public static ListNode buildListNodeList(int[] arr) {
        ListNode head = new ListNode(-1);
        ListNode dummy = head;
        for (int i = 0; i < arr.length; i++) {
            head.next = new ListNode(arr[i]);
            head = head.next;
        }
        return dummy.next;
    }
}
