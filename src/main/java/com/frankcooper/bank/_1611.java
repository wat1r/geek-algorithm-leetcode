package com.frankcooper.bank;

import java.util.HashMap;
import java.util.Map;

public class _1611 {


    static _1611 handler = new _1611();


    public static void main(String[] args) {
        for (int n = 0; n <= 16; n++) {
            System.out.printf("n:%d--->res:%d\n",n,handler.minimumOneBitOperations(n));
        }
    }


    public int minimumOneBitOperations(int n) {
        if (n == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        int last = 1; //上一个2^k
        for (int i = 2; i <= n; i++) {
            if (i == 2 * last) {
                map.put(i, map.get(last) * 2 + 1);
                last = i;
            } else {
                map.put(i, map.get(last) - map.get(i % last));
            }
        }
        return map.get(n);
    }


}
