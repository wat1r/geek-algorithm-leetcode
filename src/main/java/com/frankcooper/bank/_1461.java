package com.frankcooper.bank;

import java.util.HashSet;

public class _1461 {

    static _1461 handler = new _1461();

    public static void main(String[] args) {
        handler.hasAllCodes("00110110", 2);
    }

    public boolean hasAllCodes(String s, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0, w = 0; i < s.length(); i++) {
            w = w * 2 + s.charAt(i) - '0';
            if (i >= k) w -= (s.charAt(i - k) - '0') << k;
            if (i >= k - 1) set.add(w);
        }
        return set.size() == (1 << k);

    }
}
