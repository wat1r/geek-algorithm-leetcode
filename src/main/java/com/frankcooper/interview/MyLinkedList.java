package com.frankcooper.interview;

import java.util.Stack;
//用队列实现栈
public class MyLinkedList {



    private Stack<Integer> data;
    private Stack<Integer> help;


    public MyLinkedList(Stack<Integer> data, Stack<Integer> help) {
        data = new Stack<>();
        help = new Stack<>();
    }

    public void push(int x) {
        help.push(x);
    }

    public int pop() {
        if (data.isEmpty() && help.isEmpty()) throw new RuntimeException("the stack is empty!");
        if (data.isEmpty()) {
            while (!help.isEmpty()) {
                data.push(help.pop());
            }
        }
        return data.pop();
    }

//    public int peek() {
//

//    }
}
