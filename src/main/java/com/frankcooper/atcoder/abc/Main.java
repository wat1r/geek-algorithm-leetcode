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
        int N = fr.nextInt();
        process(N);
    }


    private static void process(int N) {
        int K = 10;
        //f dp[n][k] 是 f(k???…???)的值，n表示当前的数字的位数，k表示的收首位的数字
        long[][] dp = new long[N + 1][K];
        for (int k = 1; k <= 9; k++) dp[1][k] = 1;
        for (int i = 2; i <= N; i++) {
            for (int k = 1; k <= 9; k++) {
                for (int j = Math.max(1, k - 1); j <= Math.min(9, k + 1); j++) {
//                    System.out.printf("j=%d,k=%d\n", j, k);
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= MOD;
                }
            }
        }
        int res = 0;
        for (int k = 1; k <= 9; k++) {
            res += dp[N][k];
            res %= MOD;
        }
        System.out.println(res);
    }

}
