package com.frankcooper;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N = 500010;
    static int res = 0;
    static List<List<int[]>> adj = new ArrayList<>();
    static int[] dist = new int[N];
//    static int[] vis = new int[N];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int i = 1; i < n; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }
        dp(1, 0);
        System.out.println(res);
    }


    public static void dp(int u, int fa) {
        for (int[] ne : adj.get(u)) {
            int v = ne[0], w = ne[1];
            if (v == fa) continue;
            dp(v, u);
            res = Math.max(res, dist[u] + dist[v] + w);
            dist[u] = Math.max(dist[u], dist[v] + w);
        }
    }


}
