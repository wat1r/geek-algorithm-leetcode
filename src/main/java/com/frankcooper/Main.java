package com.frankcooper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            String[] choices = br.readLine().split(" ");
            for (int j = 0; j < choices.length; j++) {
                int cur = Integer.parseInt(choices[j]);
                if (!vis[cur]) {
                    vis[cur] = true;
                    System.out.printf("%d ", cur);
                    break;
                }
            }
        }
    }

}
