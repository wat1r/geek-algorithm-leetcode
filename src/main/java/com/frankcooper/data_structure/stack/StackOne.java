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


    static class _1st_3 {
        public static void main(String[] args) {
            _1st_3 newStack = new _1st_3();
            newStack.addElement(5);
            newStack.addElement(7);
            newStack.addElement(3);
            System.out.println("min element :: " + newStack.getMin());
            newStack.removeLastElement();
            newStack.addElement(2);
            newStack.addElement(9);
            System.out.println("last element :: " + newStack.getLastElement());
            newStack.addElement(0);
            System.out.println("min element :: " + newStack.getMin());
            newStack.removeLastElement();
            newStack.addElement(11);
            System.out.println("min element :: " + newStack.getMin());
        }

        static class Pair {
            int ele;
            int minEle;

            public Pair(int ele, int minEle) {
                this.ele = ele;
                this.minEle = minEle;
            }
        }

        int min = Integer.MAX_VALUE;
        List<Pair> stk = new ArrayList<>();

        public void addElement(int x) {
            if (stk.size() == 0 || x < min) {
                min = x;
            }
            Pair pair = new Pair(x, min);
            stk.add(pair);
            System.out.println(x + " inserted successfully");
        }

        public int getLastElement() {
            if (stk.size() == 0) {
                System.out.println("UnderFlow Error");
                return -1;
            }
            return stk.get(stk.size() - 1).ele;
        }

        public void removeLastElement() {
            if (stk.size() == 0) {
                System.out.println("UnderFlow Error");
            } else if (stk.size() == 1) {
                min = Integer.MAX_VALUE;
            } else {
                min = stk.get(stk.size() - 2).minEle;
            }
            stk.remove(stk.size() - 1);
            System.out.println("removed successfully");
        }


        public int getMin() {
            if (stk.size() == 0) {
                System.out.println("UnderFlow Error");
                return -1;
            }
            return stk.get(stk.size() - 1).minEle;
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
            System.out.println("Deleted Middle Element is  " + myStack.deleteMiddle());
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
            private DLLNode head;//头指针
            private DLLNode mid;//中间指针
            private int count;

            public MyStack() {
            }

            public void push(int data) {
                DLLNode node = new DLLNode(data);
                //当前的节点的next指针指向head 并将count++
                node.prev = null;
                node.next = head;
                count++;
                //如果只有一个元素，移动mid到当前节点
                if (count == 1) {
                    mid = node;
                } else {//如果不止一个元素，将head指针的prev指针指向当前节点
                    head.prev = node;
                    if (count % 2 != 0) {//odd 奇数 移动mid指针
                        mid = mid.prev;
                    }
                }
                //head指针前移
                head = node;
            }

            public int pop() {
                if (count == 0) {
                    System.out.println("Stack is empty!");
                    return -1;
                }
                int item = head.data;
                //移动head指针到下一个节点
                head = head.next;
                //head指针不为空的时候，prev指针置为空
                if (head != null) {
                    head.prev = null;
                }
                count--;
                if (count % 2 == 0) {//数量-1 ，偶数时，移动到当前mid的下一个节点
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


            public int deleteMiddle() {
                if (count == 0) {
                    System.out.println("Stack is empty!");
                    return -1;
                }
                int item = mid.data;
                mid.next.prev = mid.prev;
                mid.prev.next = mid.next;
                mid = mid.next;
                count--;
                return item;

            }




    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
