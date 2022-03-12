package com.frankcooper.other;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class WeChatPacket {

    static class _1st {
        static _1st handler = new _1st();

        public static void main(String[] args) {

            int n = 100;
            Random random = new Random();

//            handler.getDate(2021, 5);

            n = 1;
            while (n-- > 0) {
//                System.out.println();
                handler.gate();
//                handler.process(3", new BigDecimal("100")", new BigDecimal("50")", new BigDecimal("25"));
            }


//            handler.getDate(2021, 9);

        }

        /**
         *
         */

        private void gate() {
            Random random = new Random();
            int count = 3, base = 2000, delta = 101;
            double div = 100.0;
            String[] months = new String[]{"Jan", "Feb", "Mar", "Apr ", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String[] charger = new String[]{"FC  [03]", "NIU [04]", "WAY [07]", "TING[10]"};
            Arrays.sort(charger, Comparator.reverseOrder());
            int N = charger.length;
            int pos = 7;
            for (int i = 0; i < months.length; i++) {
                System.out.printf("[%s]     TOTAL", months[i]);
                int year = i < 4 ? 2022 : 2021;
                List<String> fris = getDate(year, i + 1);
                fris.forEach(fri -> print(fri, pos));
                System.out.printf("%s\n", "");
                while (N-- > 0) {
                    double total = base + random.nextInt(delta);
                    Double[] ratios = process(count, new BigDecimal("100"), new BigDecimal(total / 20 * 0.47), new BigDecimal(total / 20 * 0.24));
                    System.out.printf("%s %s %s %s %s  \n", print(charger[N] + ":", 5), print((int) total, pos - 3), print((int) (total * ratios[0] / div), pos)
                            , print((int) (total * ratios[1] / div), pos), print((int) (total * ratios[2] / div), pos));
                }
                N = 4;
            }
        }

        private Double[] process(int count, BigDecimal total, BigDecimal max, BigDecimal min) {
            DecimalFormat df = new DecimalFormat("#.00");
            Double[] res = new Double[count];
            BigDecimal sum = new BigDecimal("0.0");
            int idx = 0;
            while (count-- > 1) {
                BigDecimal p = BigDecimal.valueOf((Math.random() * (max.subtract(min)).doubleValue()) + min.doubleValue());
                res[idx++] = Double.valueOf(df.format(p));
                sum = sum.add(p);
            }
            res[idx] = Double.valueOf(df.format(total.subtract(sum)));
            return res;
        }


        /**
         * get date by year and month
         *
         * @param year
         * @param month
         * @return
         */
        private List<String> getDate(int year, int month) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            int fistDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2;//注意-1开始的是星期天
            int allDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int week = 7;  //要求的星期（1-7）
            int day = 0;
            List<String> res = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                day = (week - fistDayOfWeek) + i * 7;
                if (day < 1 || day > allDays) {
                    continue;
                }
                res.add(month + "/" + day);
            }
            return res;
        }

        private String print(String target, int pos) {
            System.out.printf("%" + pos + "s" + " ", target);
            return "";
        }

        private String print(int target, int pos) {
            print(String.valueOf(target), pos);
            return "";
        }


    }


    static class _2nd {

        static _2nd handler = new _2nd();


        public static void main(String[] args) {
            int[] arr = new int[]{1, 10, 100, 200, 500, 1000, 2000};
            for (int times : arr) {
                handler.benchmark(times, new RedPackage(30, 200.0));
            }
        }

        DecimalFormat df = new DecimalFormat("#.00");

        private void benchmark(int times, RedPackage rp) {
            int size = rp.remainSize, t = times;
            Double[] sum = new Double[size];
            Arrays.fill(sum, 0.0);
            while (times-- > 0) {
                while (rp.remainSize > 0) {
                    sum[rp.remainSize - 1] += handler.getRandomMoney(rp);
                }
                rp = new RedPackage(30, 200.0);
            }
            Double[] res = new Double[size];
            for (int i = 0; i < sum.length; i++) {
                res[i] = Double.valueOf(df.format(sum[i] / t));
            }
            System.out.printf("times:[%s]-->%s\n", String.format("%4d", t), Arrays.toString(res));
        }


        @Data
        @AllArgsConstructor
        static class RedPackage {
            private int remainSize;//还剩下的红包个数
            private double remainMoney;//还剩下的钱
        }

        public double getRandomMoney(RedPackage rp) {
            Random random = new Random();
            double min = 0.01, max = rp.remainMoney / rp.remainSize * 2;
            double curMoney = random.nextDouble() * max;
            curMoney = curMoney <= min ? 0.01 : curMoney;//单个红包不得少于0.01
            curMoney = Math.floor(curMoney * 100) / 100;
            rp.remainSize--;
            rp.remainMoney -= curMoney;
            return curMoney;
        }
    }
}
