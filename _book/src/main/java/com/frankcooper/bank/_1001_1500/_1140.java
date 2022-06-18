package com.frankcooper.platform.leetcode.bank._1001_1500;

public class _1140 {


    public static void main(String[] args) {

    }

    //https://leetcode.com/problems/stone-game-ii/discuss/345354/Java-DP-with-memorization-easy-to-understand(with-explanation)
    int n;
    int[][] memo;//memo[i][j] 当在区间[i...n]范围内，M=j时，当前玩家能获取到的最大的石子数量
    int[] suffixSum;//后缀和，[i:]的和

    public int stoneGameII1st(int[] piles) {
        n = piles.length;
        memo = new int[n][n];
        suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];//初始化最后一个后缀和
        for (int i = n - 2; i >= 0; i--) suffixSum[i] = suffixSum[i + 1] + piles[i];
        return dfs(piles, 0, 1);
    }

    /**
     * @param piles
     * @param i     [i:]的区间取石子
     * @param M     [1...2*M]的范围
     * @return
     */
    private int dfs(int[] piles, int i, int M) {
        if (i == n) return 0;//没有石子了
        if ((n - i) <= 2 * M) return suffixSum[i];//当前剩下的石子不足2*M,全部取走
        if (memo[i][M] != 0) return memo[i][M];//memo中有的话，直接返回
        int ans = 0;
        for (int x = 1; x <= 2 * M; x++) {//遍历[1...2*M]范围内的所有可能性，记录获取的最大的石子数量
            // suffixSum[i] 表示当前位置到结束的石子数量，当前的总的石子数量
            // dfs(piles, i + x, Math.max(x, M)) 另外一个玩家从i+x开始取，所能获取的最大石子数量
            ans = Math.max(ans, suffixSum[i] - dfs(piles, i + x, Math.max(x, M)));
        }
        memo[i][M] = ans;
        return ans;
    }


    public int stoneGameII(int[] piles) {
        int N = piles.length;
        int preSum = 0;
        int[][] f = new int[N][N + 1];
        for (int i = N - 1; i >= 0; i--) {
            preSum += piles[i];
            for (int M = 1; M <= N; M++) {
                if (i + 2 * M >= N) f[i][M] = preSum;
                else {
                    for (int x = 1; x <= 2 * M; x++) {
                        f[i][M] = Math.max(f[i][M], preSum - f[i + x][Math.max(M, x)]);
                    }
                }

            }
        }
        return f[0][1];
    }


}
