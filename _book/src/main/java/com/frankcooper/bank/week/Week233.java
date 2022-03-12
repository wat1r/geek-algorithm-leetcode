package com.frankcooper.bank.week;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.*;

public class Week233 {

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
            int n = 4, index = 2, maxSum = 6;
//            Assert.assertEquals(handler.maxValue(n, index, maxSum), 2);
            n = 6;
            index = 1;
            maxSum = 10;
            n = 1;
            index = 0;
            maxSum = 780055968;
            /**
             * 2
             * 3
             * 155230825
             */
            Assert.assertEquals(handler.maxValue(n, index, maxSum), 2);
        }


        public int maxValue(int n, int index, int maxSum) {
            int diff = maxSum - n, left = index, right = index;
            int res = 1, dl = 0, dr = 0;
            while (diff > 0) {                     //当还有剩余砖块时
                if (--left >= 0) dl++;              //尚未到达左边界
                if (++right < n) dr++;              //尚未到达右边界
                if (left < 0 && right >= n) {            //当到达左边界和右边界时 及时退出
                    res += diff % n == 0 ? diff / n : diff / n + 1; //把剩余的砖块均分了，直接退出
                    return res;
                }
                res += 1;          //层数更新
                diff -= (dl + dr + 1); //使顶层堆成严格正三角形所需的砖块数(左边所需+右边所需+index处1个)
            }
            return res;
        }


        /*     *//**
         * @param n      数组的所有的个数
         * @param index  目标索引位置
         * @param maxSum 最大值
         * @return
         *//*
        public int maxValue(int n, int index, int maxSum) {
            int l = 1, r = maxSum + 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (calSum(m, n, index) <= maxSum) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            return l - 1;
        }

        private long calSum(int x, int n, int index) {
            long sum = n - 1 + x;
            long left = Math.min(x - 1, index);
            sum += ((x - 1) + (x - left)) * left / 2;
            long right = Math.min(x - 1, n - index - 1);
            sum += ((x - 1) + (x - right)) * right / 2;
            sum -= left + right;
            return sum;
        }*/


       /* public int maxValue(int n, int index, int maxSum) {
            int l = index, r = index;
            int ans = 1;
            // 整个数组一开始全部填充为1，
            // rest记录先全部填充1后，剩下1的个数
            int rest = maxSum - n;
            while (l > 0 || r < n - 1) {
                int len = r - l + 1;
                if (rest >= len) {
                    // 当前[l,r]范围全部+1
                    rest -= len;
                    ans++;
                    // 往左右两边扩
                    l = Math.max(0, l - 1);
                    r = Math.min(n - 1, r + 1);
                } else {
                    break;
                }
            }
            // 扩大到整个数组之后，剩余的值“雨露均沾”一下
            ans += rest / n;
            return ans;
        }*/
    }

    /*
    https://leetcode-cn.com/problems/count-pairs-with-xor-in-a-range/solution/java01zi-dian-shu-by-simon123-t-eqv8/
     */
    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        public int countPairs(int[] nums, int low, int high) {
            int res = 0;
            Trie root = new Trie();
            for (int num : nums) {
                res += query(root, num, high + 1) - query(root, num, low);
                insert(root, num);
            }
            return res;
        }

        public int query(Trie root, int num, int low) {
            int res = 0;
            for (int i = 30; i >= 0; --i) {
                if (root == null) return res;
                int nbit = (num >> i) & 1;//low的第i位的值 0 1
                int lbit = (low >> i) & 1;//num的第i位的值 0 1
                if (lbit == 1) {//low的第i位为1的时候
                    if (root.children[nbit] != null) {
                        res += root.children[nbit].count;
                    }
                    root = root.children[nbit ^ 1];//统计了nbit的，需要统计1^nbit的
                } else {
                    root = root.children[nbit];
                }
            }
            return res;

        }

        //生成字典树，并统计个数
        public void insert(Trie root, int num) {
            Trie curr = root;
            for (int i = 30; i >= 0; --i) {
                int bit = (num >> i) & 1;
                if (curr.children[bit] == null) {
                    curr.children[bit] = new Trie();
                }
                curr = curr.children[bit];
                curr.count++;
            }
        }


        class Trie {
            int count = 0;
            Trie[] children;

            public Trie() {
                this.children = new Trie[2];
            }
        }
    }
}
