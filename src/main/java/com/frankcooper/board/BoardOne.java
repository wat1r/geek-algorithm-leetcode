package com.frankcooper.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BoardOne {
    // Working program with FastReader
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

    // python interactive_runner.py python3 testing_tool.py 0 -- java Solution
    static class UnionFind {
        int[] parent;
        int[] rank;
        int n;

        UnionFind(int n) {
            this.n = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] == x) return x;
            parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;
            ;
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootX] = rootY;
                rank[rootY]++;
            }
            return true;
        }
    }

    static void floydWarshall(int N, int[][] distArr) {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (distArr[i][k] != Integer.MAX_VALUE && distArr[k][j] != Integer.MAX_VALUE) {
                        distArr[i][j] = Math.min(distArr[i][j], distArr[i][k] + distArr[k][j]);
                    }
                }
            }
        }
    }

    static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int M = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        String[] A = new String[3];
        for (int i = 0; i < 3; i++) {
            A[i] = fr.next();
        }
        String[] B = new String[3];
        for (int i = 0; i < 3; i++) {
            B[i] = fr.next();
        }
        Set<String[]> set = new HashSet<>();
        set.add(new String[]{A[1], A[2], A[0]});
        set.add(new String[]{A[0], A[1], A[2]});
        set.add(new String[]{A[2], A[0], A[1]});
        for (String[] s : set) {
            if (Arrays.equals(s, B)) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }
}


