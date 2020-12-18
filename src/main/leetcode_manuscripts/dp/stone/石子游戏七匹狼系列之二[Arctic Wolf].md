## 石子游戏七匹狼系列之二[Arctic Wolf]



![image-20201217201430079](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之二[Arctic Wolf].assets\image-20201217201430079.png)



### 方法1：记忆化搜索

- **定义`dfs(piles,i,M)`为从第`i`堆石子开始拿，`x`在`[1...2*M]`内，当前玩家在剩余的这些石子中`[i:]`内能拿到的最大的石子数量**

#### 思路

- `memo[i][j] `当在区间`[i...n]`范围内，`M=j`时，当前玩家能获取到的最大的石子数量

- 当`i>=n`时，说明石子已经取完了，返回0

- 当`(n - i) <= 2 * M` 说明，当前点到最后`[i:]`，可以一次性被当前玩家拿完，这时候获取的石子数量是后缀和` suffixSum[i:]`

- 如果石子本轮拿最大`2*M`也拿不到石子的结尾，需要遍历：

  - `[1...2*M]`这个范围内，当前玩家本轮所能获取的最大的石子数量为`suffixSum[i:]`
  - 留给另外一个玩家的最大石子数量是`dfs(piles, i + x, Math.max(x, M)))`,其中，另外一个玩家需要从`i+x`位置拿(因为`[i...i+x-1]`这一段被当前玩家拿走了)，然后取` Math.max(x, M)`,作为`M`的参数
  - 上述的差值即为本轮当前玩家的最大值，因为要求**亚历克斯和李都发挥出最佳水平**

  - 记录最大值，写入`memo`

```java
int n;
int[][] memo;//memo[i][j] 当在区间[i...n]范围内，M=j时，当前玩家能获取到的最大的石子数量
int[] suffixSum;//后缀和，[i:]的和

public int stoneGameII(int[] piles) {
    n = piles.length;
    memo = new int[n][n];
    suffixSum = new int[n];
    suffixSum[n - 1] = piles[n - 1];//初始化最后一个后缀和
    for (int i = n - 2; i >= 0; i--) suffixSum[i] = suffixSum[i + 1] + piles[i];
    return dfs(piles, 0, 1);
}

/**
 *
 * @param piles
 * @param i [i:]的区间取石子
 * @param M [1...2*M]的范围
 * @return
 */
private int dfs(int[] piles, int i, int M) {
    if (i == n) return 0;//没有石子了
    if ((n - i) <= 2 * M) return suffixSum[i];//当前剩下的石子不足2*M,全部取走
    if (memo[i][M] != 0) return memo[i][M];//memo中有的话，直接返回
    int ans = 0;
    for (int x = 1; x <=2 * M; x++) {//遍历[1...2*M]范围内的所有可能性，记录获取的最大的石子数量
        // suffixSum[i] 表示当前位置到结束的石子数量，当前的总的石子数量
        // dfs(piles, i + x, Math.max(x, M)) 另外一个玩家从i+x开始取，所能获取的最大石子数量
        ans = Math.max(ans, suffixSum[i] - dfs(piles, i + x, Math.max(x, M)));
    }
    memo[i][M] = ans;
    return ans;
}
```

### 方法2：DP

#### 定义状态

- **`f[i][m]` 表示当在`[i...n-1]`这个石子的区间范围内，`M`取`m`时，当前玩家能获取到的最多的石子数量**

#### 转移方程

- 如果当前玩家从`[i...i+x-1]`选了这个范围的石子，留给另外一个玩家的选择范围是`[i+x...max(m,x)]`，而











### Q&A

- 





