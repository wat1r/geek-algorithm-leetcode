package com.frankcooper.atcoder.abc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static int MOD = 998244353;

    public static void main(String[] args) {
        FastReader fr = new FastReader();
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
