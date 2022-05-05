package com.frankcooper.bank._701_1000;

import java.util.*;

import org.junit.Assert;

public class _739 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int[] dailyTemperatures(int[] T) {
            int n = T.length;
            int[] res = new int[n];
            //存的下标索引
            Stack<Integer> stk = new Stack<>();
            for (int i = 0; i < n; i++) {
                //弹栈 while循环， 如果栈顶的温度比当前的温度小，这个当前的温度是满足题意的首次出现的最高温度
                while (!stk.isEmpty() && T[stk.peek()] < T[i]) {
                    int idx = stk.pop();//之前的那个索引
                    res[idx] = i - idx;//间隔的天数
                }
                stk.push(i);//入栈
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int[] dailyTemperatures(int[] T) {
            int n = T.length;
            int[] res = new int[n];
            //存的下标索引
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                //弹栈 while循环， 如果栈顶的温度比当前的温度小，这个当前的温度是满足题意的首次出现的最高温度
                while (!stk.isEmpty() && T[stk.peekFirst()] < T[i]) {
                    int idx = stk.pollFirst();//之前的那个索引
                    res[idx] = i - idx;//间隔的天数
                }
                stk.addFirst(i);//入栈
            }
            return res;
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
