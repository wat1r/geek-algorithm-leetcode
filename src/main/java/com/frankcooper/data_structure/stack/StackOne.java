package com.frankcooper.data_structure.stack;

import java.util.*;

import org.junit.Assert;

public class StackOne {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int arr[] = {11, 13, 21, 3};
            int n = arr.length;
            handler.printNGE(arr, n);

        }

        public void printNGE(int arr[], int n) {
            int next, i, j;
            for (i = 0; i < n; i++) {
                next = -1;
                for (j = i + 1; j < n; j++) {
                    if (arr[i] < arr[j]) {
                        next = arr[j];
                        break;
                    }
                }
                System.out.println(arr[i] + " -- " + next);
            }
        }


    }

    static class _1st_1 {
        public static void main(String[] args) {
            _1st_1 handler = new _1st_1();
            int arr[] = {11, 13, 21, 3};
            arr = new int[]{4, 5, 2, 25};
            handler.printNGE(arr);
        }


        public void printNGE(int[] arr) {
            Stack<Integer> stk = new Stack<>();
            int ele, next;
            //第一个元素进栈
            stk.push(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                next = arr[i];
                if (!stk.isEmpty()) {
                    //栈不为空，弹出栈顶元素赋值给ele
                    ele = stk.pop();
                    //如果栈顶元素比next小，打印ele->next 不断弹出栈顶元素，直到栈空
                    while (ele < next) {
                        System.out.printf("%d ---> %d \n", ele, next);
                        if (stk.isEmpty()) break;
                        ele = stk.pop();
                    }
                    //如果当前元素大于next,将当前元素入栈
                    if (ele > next) {
                        stk.push(ele);
                    }
                }
                stk.push(next);
            }
            while (!stk.isEmpty()) {
                ele = stk.pop();
                next = -1;
                System.out.printf("%d ---> %d \n", ele, next);
            }
        }

    }


    static class _1st_2 {
        public static void main(String[] args) {
            _1st_2 handler = new _1st_2();
            int[] arr = new int[]{11, 13, 21, 3};
            handler.printNGE(arr);
        }

        public void printNGE(int[] arr) {
            Stack<Integer> stk = new Stack<>();
            int[] res = new int[arr.length];
            for (int i = arr.length - 1; i >= 0; --i) {
                if (!stk.isEmpty()) {
                    while (!stk.isEmpty() && stk.peek() <= arr[i]) {
                        stk.pop();
                    }
                }
                res[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(arr[i]);
            }
            for (int i = 0; i < res.length; i++) {
                System.out.printf("%d ---> %d \n", arr[i], res[i]);
            }

        }
    }

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.test();
        }

        private void test() {
            MyStack myStack = new MyStack();
            myStack.push(11);
            myStack.push(22);
            myStack.push(33);
            myStack.push(44);
            myStack.push(55);
            myStack.push(66);
            myStack.push(77);
            System.out.println("Item popped is " + myStack.pop());
            System.out.println("Item popped is " + myStack.pop());
            System.out.println("Middle Element is " + myStack.findMiddle());
        }


        class DLLNode {
            private DLLNode prev;
            private DLLNode next;
            private int data;

            public DLLNode(int data) {
                this.data = data;
            }
        }

        class MyStack {
            private DLLNode head;
            private DLLNode mid;
            private int count;

            public MyStack() {
            }

            public void push(int data) {
                DLLNode node = new DLLNode(data);
                node.prev = null;
                node.next = head;
                count++;
                if (count == 1) {
                    mid = node;
                } else {
                    head.prev = node;
                    if (count % 2 != 0) {//odd 奇数
                        mid = mid.prev;
                    }
                }
                head = node;
            }

            public int pop() {
                if (count == 0) {
                    System.out.println("Stack is empty!");
                    return -1;
                }
                int item = head.data;
                head = head.next;
                if (head != null) {
                    head.prev = null;
                }
                count--;
                if (count % 2 == 0) {
                    mid = mid.next;
                }
                return item;
            }


            public int findMiddle() {
                if (count == 0) {
                    System.out.println("Stack is empty!");
                    return -1;
                }
                return mid.data;
            }




        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
