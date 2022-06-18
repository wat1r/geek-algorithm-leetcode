package com.frankcooper.platform.leetcode.swordoffer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Sword_55_II {
    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(1);
        maxQueue.push_back(2);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());

    }
}


/**
 * - 准备一个`data`的普通队列，与一个`help`的双端队列
 */

class MaxQueue {
    Queue<Integer> data;
    Deque<Integer> help;


    public MaxQueue() {
        data = new LinkedList<>();
        help = new LinkedList<>();
    }

    public int max_value() {
        if (help.isEmpty()) return -1;
        return help.peekFirst();
    }

    public void push_back(int value) {
        while (!help.isEmpty() && value > help.peekLast())
            help.pollLast();
        help.offer(value);
        data.offer(value);

    }

    public int pop_front() {
        if (data.isEmpty()) return -1;
        int pop = data.poll();
        if (!help.isEmpty() && pop == help.peekFirst()) help.pollFirst();
        return pop;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
