package com.frankcooper.platform.leetcode.bank._901_1000;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

//901. 股票价格跨度 901. Online Stock Span
public class _901 {
    public static void main(String[] args) {

        StockSpanner S = new StockSpanner();
        S.next(100);
        S.next(80);
        S.next(60);
        S.next(70);
        S.next(60);
        S.next(75);
        S.next(85);

    }


}

/*
    - 维护一个单调递减栈`prices` 收集的是`price` 从栈底到栈顶，从大到小，
    - 维护一个辅助栈 `counts` 收集的是当前的`price`的价格跨度，也就是

    >  今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。

    - 如何转这个`prices` 栈：
      - 当栈为空并且栈顶的元素`<=` 待进来的`price`时，弹出`prices`栈顶元素，并将`counts`的结果做累加，送给待进来的`price`
      - `prices`与`counts` 元素一一对应

    `price`时，弹出`prices`栈顶元素，并将`counts`的结果做累加，送给待进来的`price`
          - `prices`与`counts` 元素一一对应
 */

class StockSpanner {

    private Stack<Integer> prices;
    private Stack<Integer> counts;

    public StockSpanner() {
        prices = new Stack<>();
        counts = new Stack<>();
    }

    public int next(int price) {
        int res = 1;
        while (!prices.isEmpty() && prices.peek() <= price) {
            prices.pop();
            res += counts.pop();
        }
        prices.push(price);
        counts.push(res);
        return res;
    }
}


class StockSpanner1st {

    private Stack<Pair> stack;

    public StockSpanner1st() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int count = 1;
        while (!stack.isEmpty() || stack.peek().price <= price) {
            Pair cur = stack.pop();
            count += cur.count;
        }
        stack.push(new Pair(price, count));
        return count;
    }

    class Pair {
        public int price;
        public int count;

        public Pair(int price, int count) {
            this.price = price;
            this.count = count;
        }
    }

    static class _1st {
        public static void main(String[] args) {
            StockSpanner stockSpanner = new StockSpanner();
            stockSpanner.next(100); // 返回 1
            stockSpanner.next(80);  // 返回 1
            stockSpanner.next(60);  // 返回 1
            stockSpanner.next(70);  // 返回 2
            stockSpanner.next(60);  // 返回 1
            stockSpanner.next(75);  // 返回 4 ，因为截至今天的最后 4 个股价 (包括今天的股价 75) 都小于或等于今天的股价。
            stockSpanner.next(85);  // 返回 6
        }

        static class StockSpanner {
            Deque<int[]> d = new ArrayDeque<>();
            int cur = 0;

            public int next(int price) {
                while (!d.isEmpty() && d.peekLast()[1] <= price) {
                    d.pollLast();
                }
                int prev = d.isEmpty() ? -1 : d.peekLast()[0];
                int ans = cur - prev;
                d.addLast(new int[]{cur++, price});
                return ans;
            }
        }


    }
}

