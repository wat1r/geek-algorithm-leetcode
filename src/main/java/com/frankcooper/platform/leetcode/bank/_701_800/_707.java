package com.frankcooper.platform.leetcode.bank._701_800;


public class _707 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        class ListNode {
            int val;
            ListNode next;

            public ListNode(int val) {
                this.val = val;
            }
        }


        class MyLinkedList {

            ListNode head;
            int size;

            /**
             * Initialize your data structure here.
             */
            public MyLinkedList() {
                size = 0;
                head = new ListNode(0);
            }

            /**
             * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
             */
            public int get(int index) {
                if (index < 0 || index >= size) return -1;
                ListNode curr = head;
                for (int i = 0; i <= index; i++) curr = curr.next;
                return curr.val;
            }

            /**
             * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
             */
            public void addAtHead(int val) {
                addAtIndex(0, val);
            }

            /**
             * Append a node of value val to the last element of the linked list.
             */
            public void addAtTail(int val) {
                addAtIndex(size, val);
            }

            /**
             * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
             */
            public void addAtIndex(int index, int val) {
                if (index > size) return;
                if (index < 0) index = 0;
                ListNode prev = head, curr = head.next;
                for (int i = 1; i <= index; i++) {
                    prev = curr;
                    curr = curr.next;
                }
                size++;
                ListNode newNode = new ListNode(val);
                prev.next = newNode;
                newNode.next = curr;
            }

            /**
             * Delete the index-th node in the linked list, if the index is valid.
             */
            public void deleteAtIndex(int index) {
                if (index < 0 || index >= size) return;
                size--;
                ListNode curr = head;
                for (int i = 0; i < index; ++i) curr = curr.next;
                curr.next = curr.next.next;

            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
