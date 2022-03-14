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



## [53. 最大子数组和](https://leetcode-cn.com/problems/maximum-subarray/)

### 方法1:DP

```java
public int maxSubArray(int[] nums) {
    int n = nums.length;
    //f[i] 表：以 nums[i] 结尾的连续子数组的最大和
    int[] f = new int[n];
    f[0] = nums[0];
    int res = f[0];
    for (int i = 1; i < n; i++) {
        if (f[i - 1] > 0) {
            f[i] = f[i - 1] + nums[i];
        } else {
            f[i] = nums[i];
        }
        res = Math.max(res, f[i]);
    }
    return res;
}
```

- 也可以不判断，硬比较

```java
public int maxSubArray(int[] nums) {
    int n = nums.length;
    int[] f = new int[n];
    f[0] = nums[0];
    int res = f[0];
    for (int i = 1; i < n; i++) {
        f[i] = Math.max(f[i-1]+nums[i],nums[i]);
        res = Math.max(res, f[i]);
    }
    return res;
}
```

## [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/)

### 方法1:记忆化递归

```java
        //memo[i]表示从索引为i的位置，能否跳跃到最后一个下标
        Boolean[] memo ;
        int n ;

        public boolean canJump(int[] nums) {
            n = nums.length;
            memo = new Boolean[n];//初始化
            return helper(nums,0);
        }

        private boolean helper(int[] nums ,int idx ){
            //出口，索引idx到达最后一个下标的位置或者超过了最后一个下标
            if(idx >=n-1) return true;
            //记忆化剪枝
            if(memo[idx]!=null) return memo[idx];
            //step表示可以跳跃的步数 [1,nums[idx]]这个区间范围内
            for(int step=1;step<=nums[idx];step++){
                if(helper(nums,idx+step)){//如果可以跳跃，idx+step是为true
                    return  memo[idx+step] = true;
                }
            }
            //不能跳跃，idx为false
            return memo[idx] =false;
        }
```

### 方法2:记忆化递归

- 用`Index`的`enum`来记录某个坐标是否可以到达最末尾的位置，有三类值：
   - `GOOD`:可以跳到末尾位置
    - `BAD`:不可以跳到末尾位置
    - `UNKNOWN`:不知道是否可以跳到末尾位置
- 一开始的时候都是`UNKNOWN`状态，最末尾的位置是`GOOD`状态，因为可以由自己跳到自己的位置
- 出口时判断，是否是`GOOD`状态，计算`memo`,返回`true`的时候记录`GOOD`状态，返回`false`时记录`BAD`状态

```java
    Index[] memo;

    public boolean canJump2nd(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[nums.length - 1] = Index.GOOD;
        return helper(nums, 0);
    }

    private boolean helper(int[] nums, int pos) {
        if (memo[pos] != Index.UNKNOWN) return memo[pos] == Index.GOOD;
        int furtherPos = Math.min(pos + nums[pos], nums.length - 1);
        for (int i = pos + 1; i <= furtherPos; i++) {
            if (helper2nd(nums, i)) {
                memo[pos] = Index.GOOD;
                return true;
            }
        }
        memo[pos] = Index.BAD;
        return false;
    }


    enum Index {
        GOOD, BAD, UNKNOWN
    }
```

### 方法3:贪心

```java
public boolean canJump4th(int[] nums) {
    int n = nums.length;
    //最后一个位置
    int lastPos = n - 1;
    //倒数第二个位置开始
    for (int i = n - 2; i >= 0; i--) {
        //如果当前的位置能跳跃到上一个位置lastPos，更新上一个位置lastPos
        if (i + nums[i] >= lastPos) {
            lastPos = i;
        }
    }
    //是否到开头了
    return lastPos == 0;
}
```

## [45. 跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/)

### 方法1:记忆化递归

```java
int N;
Integer[] memo;//返回当前位置索引curr跳到最后位置的最小步数

public int jump(int[] nums) {
    N = nums.length;
    memo = new Integer[N];
    return helper(nums, 0);
}

/**
 * 返回当前位置索引curr跳到最后位置的最小步数
 *
 * @param nums 数组
 * @param curr 当前所处的位置
 * @return
 */
public int helper(int[] nums, int curr) {
    //当curr跳到末尾或者跳过末尾的时候，递归结束
    if (curr >= N - 1) return 0;
    if (memo[curr] != null) return memo[curr];
    int ans = Integer.MAX_VALUE / 2;
    //对于当前的curr索引，最多可以跳nums[curr]步，最少我们从1开始起跳，0没有意义，出去了
    for (int i = 1; i <= nums[curr]; i++) {
        //当前的索引是curr，可以跳i步，到i+curr ，每做一次，就跳了一次
        ans = Math.min(ans, helper(nums, i + curr) + 1);
    }
    return memo[curr] = ans;
}
```



### 方法2:DP

```java
        public int jump(int[] nums) {
            int N = nums.length, INF = Integer.MAX_VALUE / 2;
            int[] f = new int[N];
            //初始化
            f[0] = 0;
            for (int i = 1; i < N; i++) {
                f[i] = INF;//初始化一个最大值
                //从j跳到i，如果j 加上 j能跳的步数 nums[j] 比i位置还要远
                //min{f[i],j跳步的位置这个位置之前花了f[j]+这一步}
                for (int j = 0; j < i; j++) {
                    if (j + nums[j] >= i) f[i] = Math.min(f[i], f[j] + 1);
                }
            }
            return f[N - 1];
        }
```



## [1306. 跳跃游戏 III](https://leetcode-cn.com/problems/jump-game-iii/)

### 方法1:记忆化递归

- 准备一个函数：`dfs(int[] arr, int curPos, boolean[] visited)`
  - 其中`curPos`表示当前访问的位置
  - `visited`表示当前的`curPos`位置有无被访问过
- 出口条件：
  - 当前`curPos`越界了，也就是不在`[0,len-1]`范围内时，返回`false`
  - 当前`curPos`的访问过，返回`false`
  - 当`arr[curPos]==0`时，表示找到了，返回`true`
- 探索左边和右边位置

```java
public boolean canReach(int[] arr, int start) {
    boolean[] visited = new boolean[arr.length];
    return dfs(arr, start, visited);
}

private boolean dfs(int[] arr, int curPos, boolean[] visited) {
    if (curPos < 0 || curPos >= arr.length || visited[curPos]) return false;
    if (arr[curPos] == 0) return true;
    visited[curPos] = true;
    return dfs(arr, curPos - arr[curPos], visited) || dfs(arr, curPos + arr[curPos], visited);
}
```

### 方法2:BFS

- 准备一个`bool`类型的数组`visited`表示当前的下标有无被访问过
- 准备一个`queue`，转这个`queue`
  - 取到这一轮的总的`size`大小，进行`for loop`
  - 弹出当前的`curPos`,如果`arr[curPos]== 0`说明找到了，返回`true`
  - 分别渠道左右两边去找，`curPos`的位置不越界并且`leftPos`和`rightPos`未被访问过
  - 访问后要设置下`visited`的属性，并且将位置放置于`queue`中

```java
public boolean canReach(int[] arr, int start) {
    LinkedList<Integer> queue = new LinkedList<>();
    int n = arr.length;
    boolean[] visited = new boolean[n];
    queue.add(start);
    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int curPos = queue.removeFirst();
            int curValue = arr[curPos];
            if (curValue == 0) return true;
            int leftPos = curPos - curValue;
            if (leftPos >= 0 && !visited[leftPos]) {
                visited[leftPos] = true;
                queue.addFirst(leftPos);
            }
            int rightPos = curPos + curValue;
            if (rightPos < n && !visited[rightPos]) {
                visited[rightPos] = true;
                queue.addFirst(rightPos);
            }
        }
    }
    return false;
}
```