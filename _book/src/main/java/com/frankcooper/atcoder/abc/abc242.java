package com.frankcooper.atcoder.abc;


import java.util.Arrays;

public class abc242 {


    static class A {

        public static void main(String[] args) {
            Main.FastReader fr = new Main.FastReader();
            int A = Integer.parseInt(fr.next()), B = Integer.parseInt(fr.next()), C = Integer.parseInt(fr.next()), X = Integer.parseInt(fr.next());
            if (X > B) {
                System.out.printf("%.12f\n", 0.0);
            } else if (X <= A) {
                System.out.printf("%.12f\n", 1.0);
            } else {
                double res = C * 1.0 / (B - A);
                System.out.printf("%.12f\n", res);
            }

        }
    }

    static class B {
        public static void main(String[] args) {
            Main.FastReader fr = new Main.FastReader();
            String S = fr.next();
            char[] ch = S.toCharArray();
            Arrays.sort(ch);
            System.out.println(String.valueOf(ch));
        }


    }


    static class C {

    }

    static class D {
        public static void main(String[] args) {
            Main.FastReader fr = new Main.FastReader();
            String s = fr.next();
            String abc = "ABC";
            int q = fr.nextInt();
            for (int i = 0; i < q; i++) {
                long t = fr.nextLong();
                long k = fr.nextLong();
                k--;
                int si = 0;
                if (t <= 60) {
                    long b = 1L << t;
                    si = (int) (k / b);
                    k %= b;
                }
                long r = Long.bitCount(k);
                long l = t - r;
                int idx = ((int) ((l + 2 * r) % 3) + abc.indexOf(s.charAt(si))) % 3;
                System.out.println(abc.charAt(idx));
            }
        }
    }

    static class E {
    }

    static class F {
    }

    static class G {
    }

    static class Ex {
    }
}
