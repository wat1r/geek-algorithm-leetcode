package com.frankcooper.bank._601_700;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/9/19 11:19
 * Description
 */
public class _638 {

    static _638 handler = new _638();


    public static void main(String[] args) {

        List<Integer> price = Arrays.asList(2, 5);

        List<List<Integer>> special = new ArrayList<List<Integer>>() {{
            add(Arrays.asList(3, 0, 5));
            add(Arrays.asList(1, 2, 10));
        }};
        List<Integer> needs = Arrays.asList(3, 2);

        handler.shoppingOffers(price, special, needs);


    }

    Map<List<Integer>, Integer> memo = new HashMap<>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(needs, special, price);
    }

    private int dfs(List<Integer> needs, List<List<Integer>> special, List<Integer> price) {
        if (memo.containsKey(needs)) return memo.get(needs);
        int tmp = calculate(needs, price);
        int i = 0;
        for (List<Integer> s : special) {
            List<Integer> mirror = new ArrayList<>(needs);
            for (i = 0; i < needs.size(); i++) {
                int diff = mirror.get(i) - s.get(i);
                if (diff < 0) {
                    break;
                }
                mirror.set(i, diff);
            }
            //这一轮，i已经++，取的是special每一组的最后一个元素，也就是这个大礼包的价值
            if (i == needs.size()) {
                tmp = Math.min(tmp, s.get(i) + dfs(mirror, special, price));
            }
        }
        memo.put(needs, tmp);
        return tmp;
    }


    private int calculate(List<Integer> needs, List<Integer> price) {
        int sum = 0;
        for (int i = 0; i < price.size(); ++i) {
            sum += needs.get(i) * price.get(i);
        }
        return sum;
    }


}
