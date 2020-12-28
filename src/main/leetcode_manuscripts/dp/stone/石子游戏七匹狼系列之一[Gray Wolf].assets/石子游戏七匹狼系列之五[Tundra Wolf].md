## 石子游戏七匹狼系列之五[Tundra Wolf]



![moon-5544297_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之一[Gray Wolf].assets\石子游戏七匹狼系列之五[Tundra Wolf].assets\moon-5544297_960_720.jpg)

![image-20201228205030541](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之一[Gray Wolf].assets\石子游戏七匹狼系列之五[Tundra Wolf].assets\image-20201228205030541.png)

### 方法1：记忆化搜索

- 超时

```java
Map<Pair, Integer> cache = new HashMap<>();
    int[] preSum;


    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        preSum = new int[n + 1];
        for (int i = 0; i < n; i++) preSum[i + 1] = preSum[i] + stoneValue[i];
        return dfs(stoneValue, 0, n - 1);
    }

    private int dfs(int[] stoneValue, int i, int j) {
        if (i == j) return 0;
        Pair curr = new Pair(i, j);
        if (cache.get(curr) != null) return cache.get(curr);
        cache.put(curr, 0);
        for (int k = i + 1; k <= j; k++) {
            int l = preSum[k] - preSum[i];
            int r = preSum[j + 1] - preSum[k];
            if (l < r) {
                cache.put(curr, Math.max(cache.get(curr), l + dfs(stoneValue, i, k - 1)));
            } else if (l > r) {
                cache.put(curr, Math.max(cache.get(curr), r + dfs(stoneValue, k, j)));
            } else if (l == r) {
                cache.put(curr, Math.max(cache.get(curr), l + Math.max(dfs(stoneValue, i, k - 1), dfs(stoneValue, k, j))));
            }
        }
        return cache.get(curr);
    }


    class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
```





```java
Integer[][] dp;
int[] preSum;


public int stoneGameV(int[] stoneValue) {
    int N = stoneValue.length;
    dp = new Integer[N][N];
    preSum = new int[N + 1];
    for (int i = 0; i < N; i++) preSum[i + 1] = preSum[i] + stoneValue[i];
    return dfs(stoneValue, 0, N - 1);
}

private int dfs(int[] stoneValue, int l, int r) {
    if (l == r) return 0;
    if (dp[l][r] != null) return dp[l][r];
    int ans = 0;
    for (int i = l; i < r; i++) {
        int lValue = preSum[i + 1] - preSum[l];
        int rValue = preSum[r + 1] - preSum[i + 1];
        if (lValue > rValue) {
            ans = Math.max(ans, rValue + dfs(stoneValue, i + 1, r));
        } else if (lValue < rValue) {
            ans = Math.max(ans, lValue + dfs(stoneValue, l, i));
        } else if (lValue == rValue) {
            ans = Math.max(ans, lValue + Math.max(dfs(stoneValue, i + 1, r), dfs(stoneValue, l, i)));
        }
    }
    return dp[l][r] =ans;
}
```