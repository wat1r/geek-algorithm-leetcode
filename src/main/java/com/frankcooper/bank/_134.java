package com.frankcooper.bank;

/**
 * @Date 2020/9/9
 * @Author Frank Cooper
 * @Description
 */
public class _134 {


    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curr = 0, total = 0;
        int n = gas.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            int delta = gas[i] - cost[i];
            total += delta;
            curr += delta;
            if (curr < 0) {
                start = i + 1;
                curr = 0;
            }
        }
        return total >= 0 ? start : -1;
    }
}
