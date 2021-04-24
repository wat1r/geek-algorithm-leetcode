package com.frankcooper.other;

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

        }

        /**
         *
         */

        private void gate() {
            Random random = new Random();
            int count = 3, base = 2000, delta = 101;
            double div = 100.0;

            String[] months = new String[]{"Jan", "Feb", "Mar", "Apr ", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String[] charger = new String[]{"FC  ", "NIU ", "WAY ", "TING"};
            Arrays.sort(charger, Comparator.reverseOrder());
            int N = charger.length;
            int pos = 7;
            for (int i = 0; i < months.length; i++) {
                System.out.printf("[%s] TOTAL", months[i]);
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
//            System.out.println(Arrays.toString(res));
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


        private List<String> getDate(int year, int month) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            int fistDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            int allDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int week = 7;  //要求的星期（1-7）
            int day = 0;
            List<String> res = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                day = 1 + (week - fistDayOfWeek) + i * 7;
                if (day < 1 || day > allDays) {
                    continue;
                }
                res.add(month + "/" + day);
            }
            return res;
        }
    }
}
