package com.frankcooper.io;

import com.frankcooper.struct.ListNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListNodeIOUtils {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode node = Wrapper.stringToListNode(line);
            Wrapper.prettyPrintLinkedList(node);
        }
    }


    /**
     * [1,2,3,4,5]
     *
     * @param arrStr
     * @return
     */
    public static ListNode transform(String arrStr) {
        ListNode head = Wrapper.stringToListNode(arrStr);
        Wrapper.prettyPrintLinkedList(head);
        return head;
    }


    static class Wrapper {
        /**
         * Definition for singly-linked list.
         * public class ListNode {
         * int val;
         * ListNode next;
         * ListNode(int x) { val = x; }
         * }
         */

        public static int[] stringToIntegerArray(String input) {
            input = input.trim();
            input = input.substring(1, input.length() - 1);
            if (input.length() == 0) {
                return new int[0];
            }

            String[] parts = input.split(",");
            int[] output = new int[parts.length];
            for (int index = 0; index < parts.length; index++) {
                String part = parts[index].trim();
                output[index] = Integer.parseInt(part);
            }
            return output;
        }

        public static ListNode stringToListNode(String input) {
            // Generate array from the input
            int[] nodeValues = stringToIntegerArray(input);

            // Now convert that list into linked list
            ListNode dummyRoot = new ListNode(0);
            ListNode ptr = dummyRoot;
            for (int item : nodeValues) {
                ptr.next = new ListNode(item);
                ptr = ptr.next;
            }
            return dummyRoot.next;
        }

        public static void prettyPrintLinkedList(ListNode node) {
            while (node != null && node.next != null) {
                System.out.print(node.val + "->");
                node = node.next;
            }

            if (node != null) {
                System.out.println(node.val);
            } else {
                System.out.println("Empty LinkedList");
            }
        }
    }

}
