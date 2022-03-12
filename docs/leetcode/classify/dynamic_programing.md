# 动态规划



## [121. 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

### 分析

> ***只能买卖一次***

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

### 分析

> ***可以买卖多次***

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

## [123. 买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/)

### 分析

> ***最多只能买卖两次***

### 方法1:朴素版DP

```java
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int maxK = 2;
        int[][][] dp = new int[n][maxK + 1][2];
        dp[0][1][0] = 0;//交易了一次 手里没有股票
        dp[0][1][1] = -prices[0];//交易了一次 手里有股票
        dp[0][2][0] = 0;//交易了两次 手里没有股票
        dp[0][2][1] = -prices[0];//交易了两次 手里有股票
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= maxK; k++) {//最多两次
                //今天在交易了k次的情况下，手里没有股票 =
                // max{昨天在交易了k次的情况下，手里没有股票 , 昨天在交易了k次的情况下，手里有股票 + 出售股票的收益}
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                //今天在交易了k次的情况下，手里有股票 =
                // max{昨天在交易了k次的情况下，手里有股票 , 昨天在交易了k次的情况下，手里没有股票 + 买入了股票产生的负收益}
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        //k次，最后一天，手里没有股票 后价值
        return dp[n - 1][maxK][0];
    }

```

## [188. 买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)

### 分析

> ***基于上一种的最多买卖2次，这里允许2变成一般的次数k，最多买卖k次***

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

### 分析

> ***可以买卖多次，但是卖出有一天冷冻期***

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

## [714. 买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

### 分析

> ***可以买卖多次，但是每次卖出收取手续费fee***

### 方法1:朴素版DP

```java
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        //f[i][0]表示第i天手里没有股票，持有的总收益
        //f[i][1]表示第i天手里有股票，持有的总收益
        //规定买入（buy）的时候利润为负 即-prices[i]
        //规定卖出（sell）的时候利润为正，即+prices[i]
        //卖出（sell）的时候需要扣除手续费，为-fee
        int[][] f = new int[n][2];
        f[0][0] = 0;
        f[0][1] = -prices[0];
        for(int i =1;i<n;i++){
            f[i][0] = Math.max(f[i-1][0],f[i-1][1]+prices[i]-fee);
            f[i][1] =Math.max(f[i-1][1], f[i-1][0]-prices[i]);
        }
        return f[n-1][0];
    }
```

- 不过也可以，在处理`fee`的时候，上面的做法是卖出（`sell`）的时候，算扣除的手续费`-fee`，也可以在买入（`buy`）的时候算利润`-fee`，有如下代码

```java
       //基于上一种方法，fee`的话相当于在卖入`buy`的时候算负债，或者在卖出`sell`的时候扣除得到的利润
        //当前方法是在买入（buy）的时候算负债-fee,注意在初始化f[0][1]时的处理，
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            //f[i][0]表示第i天手里没有股票，持有的总收益
            //f[i][1]表示第i天手里有股票，持有的总收益
            //规定买入（buy）的时候利润为负 即-prices[i]
            //规定卖出（sell）的时候利润为正，即+prices[i]
            //卖出（sell）的时候需要扣除手续费，为-fee
            int[][] f = new int[n][2];
            f[0][0] = 0;
            f[0][1] = -prices[0] - fee;//买入(buy),股票的价值为prices[i]，需要负债手续费-fee
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
                f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i] - fee);//买入的时候算负债和手续费
            }
            return f[n - 1][0];
        }
```

### 方法2:空间压缩DP

- 基于上面方法的第一段代码，去掉一维，改成如下的形式：

```java
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            //f[0] 是当前无股票状态时 持有的总收益
            //f[1] 是当前有股票状态时 持有的总收益
            int[] f = new int[2];
            f[0] = 0;
            f[1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[0] = Math.max(f[0], f[1] + prices[i] - fee);
                f[1] = Math.max(f[1], f[0] - prices[i]);
            }
            return f[0];
        }
```

- 或者基于变量的方式有下面的改写方式：

```java
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            //f[0] 是当前无股票状态时 持有的总收益
            //f[1] 是当前有股票状态时 持有的总收益
            int f_i_0 = 0, f_i_1 = -prices[0];
            //从 0 开始 1 开始都可以
            for (int i = 0; i < n; i++) {
                //暂存下变量值，在紧接着这个值会给覆盖掉
                int t = f_i_0;
                f_i_0 = Math.max(f_i_0, f_i_1 + prices[i] - fee);
                f_i_1 = Math.max(f_i_1, t - prices[i]);
            }
            return f_i_0;
        }
```



