package com.frankcooper.bank._1001_1500;

import java.util.*;

public class _1475 {

    static _1475 handler = new _1475();

    public static void main(String[] args) {
        int[] p = {8, 4, 6, 2, 3};
        handler.finalPrices(p);
    }


    public int[] finalPrices(int[] prices) {

        Stack<Integer> stk = new Stack<>();
        List<Integer> res = new ArrayList<>();
        for (int i = prices.length - 1; i >= 0; --i) {
            int p = prices[i];
            while (!stk.isEmpty() && stk.peek() > p) stk.pop();
            if (!stk.isEmpty()) res.add(p - stk.peek());
            else res.add(p);
//            while (!stk.isEmpty() && stk.peek() == p) stk.pop();
            stk.push(p);
        }
        Collections.reverse(res);
        int[] ans = new int[prices.length];
        for (int i = 0; i < prices.length; i++) ans[i] = res.get(i);
        return ans;
    }

}
