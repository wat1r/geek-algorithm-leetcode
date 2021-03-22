package com.frankcooper.bank.week;

import java.util.*;

import com.frankcooper.swordoffer.utils.PrintUtils;
import org.junit.*;

public class _233 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int maxAscendingSum(int[] nums) {
            int ans = nums[0];
            int sum = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1]) sum += nums[i];
                else sum = nums[i];
                ans = Math.max(ans, sum);
            }
            return ans;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[][] orders = PrintUtils.processSymbol("[[10,5,0],[15,2,1],[25,1,1],[30,4,0]]");
            Assert.assertEquals(handler.getNumberOfBacklogOrders(orders), 6);
            orders = PrintUtils.processSymbol("[[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]");
            Assert.assertEquals(handler.getNumberOfBacklogOrders(orders), 999999984);
            orders = PrintUtils.processSymbol("[[26,7,0],[16,1,1],[14,20,0],[23,15,1],[24,26,0],[19,4,1],[1,1,0]]");
            Assert.assertEquals(handler.getNumberOfBacklogOrders(orders), 34);
            orders = PrintUtils.processSymbol("[[944925198,885003661,0],[852263791,981056352,0],[16300530,415829909,0],[940927944,713835606,0],[606389372,407474168,1],[139563740,85382287,1],[700244880,901922025,1],[972900669,15506445,0],[576578542,65339074,0],[45972021,293765308,0],[464403992,97750995,0],[29659852,536508041,0],[799523481,299864737,0],[711908211,480514887,1],[354125407,677598767,1],[279004011,688916331,0],[263524013,64622178,0],[375395974,460070320,0],[971786816,379275200,1],[577774472,214070125,1],[987757349,711231195,0]]");
            Assert.assertEquals(handler.getNumberOfBacklogOrders(orders), 83062672);


        }

        int MOD = 1_000_000_007;

        public int getNumberOfBacklogOrders(int[][] orders) {
            List<Order> list = new LinkedList<>();
            for (int i = 0; i < orders.length; i++) list.add(new Order(orders[i][0], orders[i][1], orders[i][2]));
            PriorityQueue<Order> buyQ = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);
            PriorityQueue<Order> sellQ = new PriorityQueue<>((o1, o2) -> o1.price - o2.price);
            for (Order o : list) {
                int amount = o.amount;
                if (o.orderType == 0) {//buy
                    while (!sellQ.isEmpty() && o.price >= sellQ.peek().price) {
                        Order currSell = sellQ.poll();
                        if (amount <= currSell.amount) {
                            currSell.amount -= amount;
                            amount = 0;
                        } else {
                            amount -= currSell.amount;
                            currSell.amount = 0;
                        }
                        if (currSell.amount > 0) {
                            sellQ.offer(currSell);
                            if (amount == 0) break;
                        }
                    }
                    if (amount > 0) buyQ.offer(new Order(o.price, amount, o.orderType));
//                    buyQ.offer(new Order(o.price, amount, o.orderType));
                } else if (o.orderType == 1) {
                    while (!buyQ.isEmpty() && buyQ.peek().price >= o.price) {
                        Order currBuy = buyQ.poll();
                        if (amount <= currBuy.amount) {
                            currBuy.amount -= amount;
                            amount = 0;
                        } else {
                            amount -= currBuy.amount;
                            currBuy.amount = 0;
                        }
                        if (currBuy.amount > 0) {
                            buyQ.offer(currBuy);
                            if (amount == 0) break;
                        }
                    }
                    if (amount > 0) sellQ.offer(new Order(o.price, amount, o.orderType));
                }
            }
            int ans = 0;
            while (!buyQ.isEmpty()) {
                ans += (buyQ.poll().amount) % MOD;
                ans %= MOD;
            }
            while (!sellQ.isEmpty()) {
                ans += (sellQ.poll().amount) % MOD;
                ans %= MOD;
            }
            return ans % MOD;
        }

        class Order {
            int price, amount, orderType;

            public Order(int price, int amount, int orderType) {
                this.price = price;
                this.amount = amount;
                this.orderType = orderType;
            }
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
