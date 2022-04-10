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
        String[] arr = fr.nextLine().split("\\s+");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        int K = Integer.parseInt(arr[2]);
        int S = Integer.parseInt(arr[3]);
        int T = Integer.parseInt(arr[4]);
        int X = Integer.parseInt(arr[5]);
//        System.out.printf("N=%d M=%d K=%d S=%d T=%d X=%d\n", N, M, K, S, T, X);
        for (int i = 0; i < M; i++) {
            arr = fr.nextLine().split("\\s+");
            int u = Integer.parseInt(arr[0]), v = Integer.parseInt(arr[1]);
//            System.out.printf("%d %d\n", u, v);
        }

    }

}
