## 石子游戏七匹狼系列之五[Tundra Wolf]



![moon-5544297_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之一[Gray Wolf].assets\石子游戏七匹狼系列之五[Tundra Wolf].assets\moon-5544297_960_720.jpg)

![image-20201228205030541](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之一[Gray Wolf].assets\石子游戏七匹狼系列之五[Tundra Wolf].assets\image-20201228205030541.png)

### 方法1：记忆化搜索DP

#### 状态定义

**`dp[l][r]` 表示`Alice`在石子区间`[l...r]`这个范围内，所能获得的最大的分数**

#### 转移方程

当`Alice`从中间的某处，将这两堆石子分开，变成`[l...i]`与`[i+1...r]`时，`Bob`会拿走其中的一堆:

- `sum[l:i]`<`sum[i+1:r]` 当左部分的和小于右部分的和，`Bob`会丢弃掉右部分，`Alice`拿走`sum[l:i]`即`dp[l][r]=dp[l][i]+sum[l:i]`,  `Alice`在`[l...i]`这个区间所能获得的最大分数+这一轮获得的左部分的分数和
- `sum[l:i]>sum[i+1:r]`，当左部分的和大于右部分的和，`Bob`会丢弃掉左部分，`Alice`拿走`sum[l+1:r]`即`dp[l][r]=dp[i+1][r]+sum[i+1:r]`,  `Alice`在`[i+1...r]`这个区间所能获得的最大分数+这一轮获得的左部分的分数和
- `sum[l:i]=sum[i+1:r]`，当左部分的和等于右部分的和，`Bob`让`Alice`选择左或者右，需要确认左右部分的最大值，即`max{dp[l][i],dp[i+1][r]}`  ,再加上本轮所获取的分数即，`sum[l:i]` 或者，`sum[i+1:r]`

- 要求的是`dp[0][n-1]`这个值，即在`[0...n-1]`这个区间范围内，`Alice`所能获取的最大分数

#### 思路

- 利用前缀和，来计算区间和

> TLE

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

> AC

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







