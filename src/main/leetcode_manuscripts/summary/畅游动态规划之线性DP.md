## 畅游动态规划之线性DP

### [121. 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

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
            //f[i][0]表示第i天手里有股票获得的最大利润
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

### [122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

![image-20210619181542539](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210619181542539.png)

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



### [188. 买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)

- 最多有K笔交易的限制

![image-20210619183900815](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210619183900815.png)

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

- 压缩空间

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



### [309. 最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)

![image-20210619215929902](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210619215929902.png)

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

![image-20210619220819248](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210619220819248.png)

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

### 1049.大盗阿福

![image-20210621185855747](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210621185855747.png)



![image-20210621185929547](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210621185929547.png)



```c++
#include <cstring>
#include <algorithm>
#include <iostream>
#include <cstdlib>

using namespace std;

const int N = 1000;

int t, n;
int w[N];
int f[N];
void fun1()
{
    scanf("%d", &t);
    while (t--)
    {
        scanf("%d", &n);
        for (int i = 1; i <= n; i++)
            scanf("%d", &w[i]);
        f[0] = 0, f[1] = w[1];
        for (int i = 2; i <= n; i++)
        {
            f[i] = max(f[i - 1], f[i - 2 + w[i]]);
        }
        printf("%d\n", f[n]);
    }
}

int g[N][N];
void fun2()
{
    scanf("%d", &t);
    while (t--)
    {
        scanf("%d", &n);
        for (int i = 1; i <= n; i++)
            scanf("%d", &w[i]);
        g[0][0] = 0, g[1][1] = w[1];
        for (int i = 2; i <= n; i++)
        {
            g[i][0] = max(g[i - 1][0], g[i - 1][1]);
            g[i][1] = g[i - 1][1] + w[i];
        }
        printf("%d\n", max(g[n][0], g[n][1]));
    }
}
```

### [1911. 最大子序列交替和](https://leetcode-cn.com/problems/maximum-alternating-subsequence-sum/)



- 状态定义
  - $f[i][0]$ 表示$[0...i-1]$个数中选偶数个数的最大交替和
  - $f[i][1]$ 表示$[0...i-1]$个数中选奇数个数的最大交替和

- 状态机表示如下：

![image-20210628203441588](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\summary\畅游动态规划之线性DP.assets\image-20210628203441588.png)



- 需要考察第$i$个数选|不选

```java
        //状态机DP
        public long maxAlternatingSum(int[] nums) {
            // f[i][0] 表示[]0...i-1]个数中选偶数个数的最大交替和
            //f[i][1]  表示[]0...i-1]个数中选奇数个数的最大交替和
            int n = nums.length;
            long[][] f = new long[n + 1][2];
            for (int i = 0; i <= n; i++) Arrays.fill(f[i], Integer.MIN_VALUE >> 1);//防止溢出
            f[0][0] = 0;
            for (int i = 1; i <= n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] - nums[i - 1]);
                f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] + nums[i - 1]);
            }
            return Math.max(f[n][0], f[n][1]);
        }
```

- 因为当前状态只依赖前一状态，可以去掉一维

```java
public long maxAlternatingSum(int[] nums) {
    int n = nums.length;
    long[] f = new long[2];
    f[0] = 0;
    f[1] = Integer.MIN_VALUE >> 1;
    for (int i = 1; i <= n; i++) {
        f[0] = Math.max(f[0], f[1] - nums[i - 1]);
        f[1] = Math.max(f[1], f[0] + nums[i - 1]);
    }
    return Math.max(f[0], f[1]);
}
```







