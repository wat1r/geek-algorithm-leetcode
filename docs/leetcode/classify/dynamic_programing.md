# 动态规划



## [121. 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

### 方法1:朴素版DP

- 股票只能买卖一次

- `dp[i][0|1]`
  - `i`表示第`i`天
  - `0`表示第`i`天没有股票的状态[无股票]
  - `1`表示第`i`天持有股票的状态[有股票]
- 规定卖出的时候(`sell`)获利，`+prices[i]`,买入的时候，暂时算负债(`buy`)，`-prices[i]`
- `dp[i][0]`表示第`i`天无股票，可能是昨天也没有股票，状态持续到今天，为`dp[i-1][0]`，也可能是昨天持有股票，但是卖出了(`sell`)，为`dp[i-1][1]+prices[i]`
  - 这时为  `dp[i][0]` = max{`dp[i-1][0]`,`dp[i-1][1]+prices[i]`}
- `dp[i][1]`表示第`i`天有股票，可能是昨天就有股票，状态持续到今天，为`dp[i-1][1]`,也可能是昨天没有持有股票，但是买入了(`buy`), 为`dp[i-1][0]-prices[i]` ,因为只有一次买入卖出的机会，
  `dp[i-1][0]`表示`i-1`天的时候，是无股票的，在`i`天的时候买入了股票，也就是说要必须在`i`天之后的某一天卖出股票，所以很显然`dp[i-1][0]=0`
  - 这时为`dp[i][1]` = max{`dp[i-1][1]`,`-prices[i]`}

```java
        public int maxProfit(int[] prices) {
            int n = prices.length;
            //f[i][0]表示第i天手里没有股票获得的最大利润
            //f[i][1]表示第i天手里有股票获得的最大利润
            int[][] f = new int[n][2];
            f[0][0] = 0;
            f[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
                f[i][1] = Math.max(f[i - 1][1], prices[i]);
            }
            return f[n - 1][0];
        }
```

### 方法2:空间压缩DP

- 由于值依赖前一天的收益情况（有无股票的状态）,`f`只需要来回滚动即可，去掉一维

```java
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[] f = new int[2];
            f[0] = 0;//无股票状态
            f[1] = -prices[0];//有股票状态
            for (int i = 1; i < n; i++) {
                f[0] = Math.max(f[0], f[1] + prices[i]);
                f[1] = Math.max(f[1], -prices[i]);
            }
            return f[0];
        }
```



## [122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

### 方法1:朴素版DP

![](/imgs/leetcode/classify/image-20210619181542539.png)

- 股票可以多次买卖

```java
        public int maxProfit(int[] prices) {
            int n = prices.length;
            //f[i][0]表示第i天手里没有股票获得的最大利润
            //f[i][0]表示第i天手里有股票获得的最大利润
            int[][] f = new int[n][2];
            f[0][0] = 0;
            f[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
                f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i]);
            }
            return f[n - 1][0];
        }
```

### 方法2:空间压缩DP

> **去掉一维**

```java
int tmp = f_i_0;
f_i_0 = Math.max(f_i_0, f_i_1 + prices[i]);
f_i_1 = Math.max(f_i_1, tmp - prices[i]);
```

          - `f_i_0` 是昨天无股票`f_i_0` 或者是昨天持有股票，今天卖出了`sell`,卖出相当于盈利为`f_i_1+prices(i)`
            - 这时为 `f_i_0 = max(f_i_0, f_i_1 + prices[i])`
                    - `f_i_1`是昨天持有股票`f_i_1`,或者是昨天无股票状态，今天买入了股票`buy`，买入相当于负债，但是需要提前记录下`tmp =f_i_0`,因为上面的转移方程已经改变了`f_i_0`的值
            - 这时为`f_i_1 = max(f_i_1, tmp - prices[i])`

```java
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int f_i_0 = 0, f_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int tmp = f_i_0;
            f_i_0 = Math.max(f_i_0, f_i_1 + prices[i]);
            f_i_1 = Math.max(f_i_1, tmp - prices[i]);
        }
        return f_i_0;
    }
```

- 另外一种写法

```java
          public int maxProfit(int[] prices) {
                int n = prices.length;
                int[] f = new int[2];
                f[0] = 0;
                f[1] = -prices[0];
                for (int i = 1; i < n; i++) {
                    f[0] = Math.max(f[0], f[1] + prices[i]);
                    f[1] = Math.max(f[1], f[0] - prices[i]);
                }
                return f[0];
            }
```

## [188. 买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)

### 方法1:朴素版DP

- 最多有K笔交易的限制

![](/imgs/leetcode/classify/image-20210619183900815.png)

```java
        public int maxProfit(int k, int[] prices) {
            int n = prices.length;
            int[][][] f = new int[n + 1][k + 1][2];
            for (int j = 0; j <= k; j++) f[0][j][1] = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1] + prices[i - 1]);
                    f[i][j][1] = Math.max(f[i - 1][j][1], f[i - 1][j - 1][0] - prices[i - 1]);
                }
            }
            return f[n][k][0];
        }
```

### 方法2:压缩空间DP

```java
public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    int[][] f = new int[k + 1][2];
    for (int j = 0; j <= k; j++) f[j][1] = Integer.MIN_VALUE;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= k; j++) {
            f[j][0] = Math.max(f[j][0], f[j][1] + prices[i - 1]);
            f[j][1] = Math.max(f[j][1], f[j - 1][0] - prices[i - 1]);
        }
    }
    return f[k][0];
}
```



## [309. 最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)

### 方法1:朴素版DP

![](/imgs/leetcode/classify/image-20210619215929902.png)

```java
public int maxProfit(int[] prices) {
    int n = prices.length;
    int[][] f = new int[n + 1][3];
    int _INF = Integer.MIN_VALUE;
    f[0][0] = _INF;
    f[0][1] = _INF;
    f[0][2] = 0;
    for (int i = 1; i <= n; i++) {
        f[i][1] = Math.max(f[i - 1][1], f[i - 1][2] - prices[i - 1]);
        f[i][0] = f[i - 1][1] + prices[i - 1];
        f[i][2] = Math.max(f[i - 1][2], f[i - 1][0]);
    }
    return Math.max(f[n][0], f[n][2]);
}
```

![](/imgs/leetcode/classify/image-20210619220819248.png)

```java
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] f = new int[n + 1][2];
            f[0][0] = 0;
            f[0][1] = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i - 1]);
                f[i][1] = Math.max(f[i - 1][1], (i > 1 ? f[i - 2][0] : 0) - prices[i - 1]);
            }
            return f[n][0];
        }
```

### 
