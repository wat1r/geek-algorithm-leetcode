package com.frankcooper.bank._1501_2000;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * https://leetcode-cn.com/problems/minimum-one-bit-operations-to-make-integers-zero/solution/gen-zhao-ti-shi-zhao-gui-lu-kan-liao-bie-ren-de-ge/

 https://leetcode-cn.com/problems/minimum-one-bit-operations-to-make-integers-zero/solution/ti-mu-ben-shen-shi-qiu-jie-n-shi-di-ji-ge-ge-lei-m/
 */

public class _1611 {


    static _1611 handler = new _1611();


    public static void main(String[] args) {
        for (int n = 0; n <= 16; n++) {
            System.out.printf("n:%d--->res:%d\n", n, handler.minimumOneBitOperations(n));
        }
    }


    public int minimumOneBitOperations1st(int n) {
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

    HashMap<Integer, Integer> map = new HashMap<>();
    TreeSet<Integer> set = new TreeSet<>();

    public int minimumOneBitOperations2nd(int n) {
        map.put(0, 0);
        int last = 1;
        while (last <= n) {
            map.put(last, map.get(last / 2) * 2 + 1);
            set.add(last);
            last *= 2;
        }
        return helper(n);
    }

    private int helper(int n) {
        if (map.containsKey(n)) return map.get(n);
        int low = set.floor(n);
        return helper(low) - helper(n % low);
    }


    public int minimumOneBitOperations(int n) {
        int res = 0;
        while (n != 0) {
            res ^= n;
            n >>= 1;
        }
        return res;
    }


}
