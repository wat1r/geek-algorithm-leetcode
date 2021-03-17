package com.frankcooper.bank._1001_2000;

import java.util.HashMap;
import java.util.Map;

public class _1510 {

    public boolean winnerSquareGame1st(int n) {
        boolean[] f = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                if (!f[i - k * k]) {
                    //当前玩家拿走k*k的石子后，留给另一玩家的石子数量是（i-k*k），另外一玩家如果输了，当前玩家便能赢
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }


    Map<Integer, Boolean> cache = new HashMap<>();

    public boolean winnerSquareGame(int n) {
        //石子只剩下1个的时候，当前玩家拿走这1个石子，不剩下石子，当前玩家赢得比赛
        if (n == 1) return true;
        //如果n被记录过，说明这棵n的子树被搜索过，对于当前玩家的结果是value
        if (cache.containsKey(n)) return cache.get(n);
        for (int i = 1, j = 1; j <= n; i++, j = i * i) {
            //当前玩家拿走j,另外一个玩家则变成 (n-j) 如果 另外一个玩家输掉游戏，说明当前玩家赢得游戏，返回true
            if (!winnerSquareGame(n - j)) {
                cache.put(n, true);
                return cache.get(n);
            }
        }
        cache.put(n, false);
        return cache.get(n);
    }


    static _1510 handler = new _1510();


    public static void main(String[] args) {
        handler.winnerSquareGame(1);
    }


}

