package com.frankcooper.bank._401_500;

public class _464 {


    static _464 handler = new _464();


    public static void main(String[] args) {

    }

    //dp[state] 表示状态state下，第一个人先选的情况下是否能赢的情况 true 能赢，false不能

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        return dfs(maxChoosableInteger, desiredTotal, new Boolean[1 << maxChoosableInteger], 0);
    }

    /**
     * @param maxChoosableInteger
     * @param desiredTotal
     * @param dp
     * @param state
     * @return
     */
    private boolean dfs(int maxChoosableInteger, int desiredTotal, Boolean[] dp, int state) {
        if (dp[state] != null) return dp[state];
        for (int i = 1; i <= maxChoosableInteger; i++) {
            int curr = 1 << (i - 1); //拿到当前位
            if ((state & curr) == 0) { //如果当前位不在state集合中，没有被使用过
                if (desiredTotal - i <= 0 || !dfs(maxChoosableInteger, desiredTotal - i, dp, state | curr)) {
                    dp[state] = true;
                    return true;
                }
            }
        }
        dp[state] = false;
        return false;
    }


}
