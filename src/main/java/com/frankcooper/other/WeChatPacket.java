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
            int N = 4;
            String[] months = new String[]{"Jan", "Feb", "Mar", "Apr ", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String[] charger = new String[]{"FC  ", "NIU ", "WAY ", "TING"};
            for (String month : months) {
                System.out.printf("$$ [%s] $$\n", month);
                while (N-- > 0) {
                    double total = base + random.nextInt(delta);
                    Double[] ratios = process(count, new BigDecimal("100"), new BigDecimal(total / 20 * 0.475), new BigDecimal(total / 20 * 0.24));
                    System.out.printf("%s:%d,%d,%d,%d\n", charger[N], (int) total, (int) (total * ratios[0] / div)
                            , (int) (total * ratios[1] / div), (int) (total * ratios[2] / div));
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
    }
}
