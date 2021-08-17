package com.frankcooper.utils;

import java.lang.*;
import java.util.*;
import java.io.*;
import java.math.*;

public class MainInput {


    static class Main {
        public static void main(String[] args) {
            MyScanner sc = new MyScanner();
            out = new PrintWriter(new BufferedOutputStream(System.out));
            int n = sc.nextInt();
            out.println(n);
            out.close();
        }

        //-----------PrintWriter for faster output---------------------------------
        public static PrintWriter out;

        //-----------MyScanner class for faster input----------
        public static class MyScanner {
            BufferedReader br;
            StringTokenizer st;

            public MyScanner() {
                br = new BufferedReader(new InputStreamReader(System.in));
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
    }
}
