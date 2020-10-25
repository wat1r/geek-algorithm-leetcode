package com.frankcooper.bank;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/10/3 21:16
 * Description 欧拉回路
 */
public class _753 {


    static _753 handler = new _753();


    public static void main(String[] args) {

    }


    Set<Integer> visited = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    int k;
    int highest;


    public String crackSafe(int n, int k) {
        highest = (int) Math.pow(10, n - 1);
        this.k = k;
        dfs(0);
        for (int i = 1; i < n; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    private void dfs(int curr) {
        for (int x = 0; x < k; x++) {
            int next = curr * 10 + x;
            if (!visited.contains(next)) {
                visited.add(next);
                dfs(next % highest);
                sb.append(x);
            }
        }
    }


}
