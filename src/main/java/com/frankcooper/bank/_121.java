package com.frankcooper.bank;

/**
 * Created by FrankCooper
 * Date 2020/3/9 19:21
 * Description
 */
public class _121 {
    public static void main(String[] args) {

    }


    /*    #####  方法1：一次遍历，找到最低点
        -  最多只允许完成一笔交易（即买入和卖出一支股票）
        - 找到这么多天的一个最低的股票价格`minPrice`，一遍遍历过程中始终找到最小的
        - 假设当前天`i`的`price[i]-minPrice`一直找最大，即可得到结果

        > - 时间复杂度：`O(n)`，只需要遍历一次。
        > - 空间复杂度：`O(1)`，只使用了常数个变量。
     */
    public int maxProfit1st(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }



}
