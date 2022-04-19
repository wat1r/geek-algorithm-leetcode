package com.frankcooper.bank.bi_weekly;

import org.junit.Assert;

import java.util.Arrays;

public class BiWeek76 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public int findClosestNumber(int[] nums) {
            int res = 100010;
            for (int x : nums) {
                if (Math.abs(x) < Math.abs(res)) {
                    res = x;
                } else if (Math.abs(x) == Math.abs(res) && res < x) {
                    res = x;
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int total = 20, cost1 = 10, cost2 = 5;
            Assert.assertEquals(9, handler.waysToBuyPensPencils(total, cost1, cost2));
        }

        public long waysToBuyPensPencils(int total, int cost1, int cost2) {
            long ways = 0;
            for (int i = 0; i <= total / cost1; i++) {
                int remain = total - cost1 * i;
                ways += 1 + remain / cost2;
            }
            return ways;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String[] ops = new String[]{"ATM", "deposit", "withdraw", "deposit", "withdraw", "withdraw"};
//            String[][] nums =new String[] {{}, {{0, 0, 1, 2, 1}}, {600}, {{0, 1, 0, 1, 1}}, {600}, {550}}
            int[] nums_0 = new int[]{};
            int[] nums_1 = new int[]{0, 0, 1, 2, 1};
            int[] nums_2 = new int[]{600};
            int[] nums_3 = new int[]{0, 1, 0, 1, 1};
            int[] nums_4 = new int[]{600};
            int[] nums_5 = new int[]{550};
            int[][] nums = new int[][]{nums_0, nums_1, nums_2, nums_3, nums_4, nums_5};
            ATM atm = new ATM();
            for (int i = 0; i < ops.length; i++) {
                if (ops[i].equals("ATM")) atm = new ATM();
                else if (ops[i].equals("deposit")) {
                    atm.deposit(nums[i]);
                } else if (ops[i].equals("withdraw")) {
                    int[] res = atm.withdraw(nums[i][0]);
                    System.out.println(Arrays.toString(res));
                }
            }


        }

        //WA
        static class ATM {

            long[] counter;
            long[] money;

            public ATM() {
                //0-5 分别表示 20 50 100 200 500的面额的数量
                counter = new long[5];
                money = new long[]{20, 50, 100, 200, 500};
            }


            public void deposit(int[] banknotesCount) {
                for (int i = 0; i < banknotesCount.length; i++) {
                    counter[i] += banknotesCount[i];
                }
            }

            public int[] withdraw(int amount) {
                long[] res = new long[5];
                long[] t = Arrays.copyOf(counter, counter.length);
                for (int i = money.length - 1; i >= 0; i--) {
                    long cnt = amount / money[i];
                    if (cnt != 0 && t[i] >= cnt) {
                        t[i] -= cnt;
                        res[i] = cnt;
                        amount -= money[i] * cnt;
                    }
                    if (amount == 0) {
                        counter = t;
                        return Arrays.stream(res).mapToInt(x -> (int) x).toArray();
                    }

                }
                return new int[]{-1};
            }
        }
    }

    static class _3rd_1 {
        class ATM {
            //面值
            long[] banknotes = new long[]{20, 50, 100, 200, 500};
            long[] counter;//每种面值的计数器

            public ATM() {
                counter = new long[5];
            }

            public void deposit(int[] banknotesCount) {
                for (int i = 0; i < banknotesCount.length; i++) {
                    counter[i] += banknotesCount[i];
                }
            }

            public int[] withdraw(int amount) {
                long[] res = new long[5];
                int idx = 4;//从大到小的元素面值的索引
                while (amount > 0 && idx >= 0) {
                    //拿走该面值的数量
                    long cnt = Math.min(amount / banknotes[idx], counter[idx]);
                    res[idx] = cnt;
                    amount -= cnt * banknotes[idx];
                    idx--;
                }
                if (amount != 0) return new int[]{-1};
                else {
                    for (int i = 0; i < res.length; i++) {
                        counter[i] -= res[i];
                    }
                    return Arrays.stream(res).mapToInt(i -> (int) i).toArray();
                }

            }
        }

    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
