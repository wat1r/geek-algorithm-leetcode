# 动态规划

> 



## [5. 最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)

### 方法1:暴力递归

- `helper(String s, int start, int end)`
  - 表示`s`从`start`到`end`位置，是否有回文子串

- `base case`:
  - `start == end` : 相等时，说明只有一个字符了，返回`T`
  - `start +1== end`  ：两个字符的时候，比较两个字符是否相等

```java

        public String longestPalindrome(String s) {
            String ans = "";
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j < s.length(); j++) {
                    if (helper(s, i, j) && j - i + 1 > ans.length()) {
                        ans = s.substring(i, j + 1);
                    }
                }
            }
            return ans;
        }


        private boolean helper(String s, int start, int end) {
            if (start == end) return true;
            if (start + 1 == end) return s.charAt(start) == s.charAt(end);
            boolean ans = false;
            if (s.charAt(start) == s.charAt(end)) {
                ans = helper(s, start + 1, end - 1);
            }
            return ans;
        }
```

### 方法2:自顶向下记忆化递归(Top-down)

> 脱胎与方法1，添加记忆化

```java
  Boolean[][] memo;

public String longestPalindrome(String s) {
    memo = new Boolean[s.length()][s.length()];
    String ans = "";
    for (int i = 0; i < s.length(); i++) {
        for (int j = i; j < s.length(); j++) {
            if (helper(s, i, j) && j - i + 1 > ans.length()) {
                ans = s.substring(i, j + 1);
            }
        }
    }
    return ans;
}


private boolean helper(String s, int start, int end) {
    if (start == end) return true;
    if (start + 1 == end) return s.charAt(start) == s.charAt(end);
    if (memo[start][end] != null) return memo[start][end];
    boolean ans = false;
    if (s.charAt(start) == s.charAt(end)) {
        ans = helper(s, start + 1, end - 1);
    }
    return memo[start][end] = ans;
}
```

### 方法3:自底向上填表递归(Bottom-up)

- 其中`f[i][j]`表示`s`中，从`i`到`j`是否有回文子串
- `k`为遍历的字符长度，可以为`n`
  - 此时`i=0`,`j=i+k-1=0+n-1=n-1`

- 条件为当前字符`[i]==[j]`的时候，要么只有两个字符，要么砍头去尾，有回文子串

```java
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) return "";
            int n = s.length();
            boolean[][] f = new boolean[n][n];
            for (int i = 0; i < n; ++i) f[i][i] = true;
            int maxLen = 1, start = 0;
            for (int k = 2; k <= n; k++) {
                // System.out.printf("k:%d\n", k);
                for (int i = 0; i < n - k + 1; i++) {
                    int j = i + k - 1;
                    // System.out.printf("i:%d,j:%d\n", i, j);
                    if (s.charAt(i) == s.charAt(j) && (k == 2 || f[i + 1][j - 1])) {
                        f[i][j] = true;
                        if (maxLen < k) {
                            maxLen = k;
                            start = i;
                        }
                    }
                }
            }
            return s.substring(start, start + maxLen);
        }
```

另外一种写法

- `k`为遍历的字符长度，可以为`n` 即当`i=0`的时候

```java
        public String longestPalindrome(String s) {
            if (s == null || s.length() <= 0) return s;
            int n = s.length();
            boolean[][] f = new boolean[n][n];
            for (int i = 0; i < n; i++) f[i][i] = true;
            int maxLen = 1, start = 0;
            for (int i = n - 1; i >= 0; i--) {
                for (int k = 1; k < n - i; k++) {
                    int j = k + i;
                    if (s.charAt(i) == s.charAt(j)) {
                        f[i][j] = (k == 1) || f[i + 1][j - 1];
                    }
                    if (f[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        start = i;
                    }
                }
            }
            return s.substring(start, start + maxLen);
        }
```

另外一种写法

```java
public String longestPalindrome(String s) {
    int n = s.length();
    //f[j][i] 表示 s[j...i]这个区间的子串是否是回文，true是回文 false不是回文
    boolean[][] f = new boolean[n][n];
    int maxx = 0;//最长的回文子串的长度
    String res = "";//结果
    for (int i = 0; i < n; i++) {//枚举右区间
        for (int j = 0; j <= i; j++) {//枚举左区间，界限是右区间
            //s[i] == s[j]时，且去掉头尾的字符串是回文子串，当前s[j...i]也是回文子串
            if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || f[j + 1][i - 1])) {
                f[j][i] = true;
            }
            //更新长度s[0..2]长度是3 2-0+1=3
            if (f[j][i] && maxx < i - j + 1) {
                maxx = i - j + 1;
                res = s.substring(j, i + 1);
            }
        }
    }
    return res;
}
```



### 方法4:中心扩展法

```java
public String longestPalindrome(String s) {
    if (s == null || s.length() == 0) {
        return "";
    }
    int n = s.length();
    int start = 0, end = 0;
    for (int i = 0; i < n; i++) {
        //获取到当前点i 的奇回文和偶回文的最大长度
        int len1 = expandBySeed(s, i, i);
        int len2 = expandBySeed(s, i, i + 1);
        //取最大长度，然后扩展
        int len = Math.max(len1, len2);
        if (len > (end - start)) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}


/**
         * 由中心往两边扩散，返回满足最大回文的长度
         *
         * @param s
         * @param start
         * @param end
         * @return
         */
private int expandBySeed(String s, int start, int end) {
    int n = s.length();
    while (start >= 0 && end < n && s.charAt(start) == s.charAt(end)) {
        start--;
        end++;
    }
    return end - start - 1;
}
```

### 方法5:Manacher算法

> 本动态规划的文章着重讲动态规划，涉及马拉车算法的内容不详细展开，下面的代码取自weiwei大佬的[题解](https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/)

```java
public class Solution {

    public String longestPalindrome(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        // 得到预处理字符串
        String str = addBoundaries(s, '#');
        // 新字符串的长度
        int sLen = 2 * len + 1;

        // 数组 p 记录了扫描过的回文子串的信息
        int[] p = new int[sLen];

        // 双指针，它们是一一对应的，须同时更新
        int maxRight = 0;
        int center = 0;

        // 当前遍历的中心最大扩散步数，其值等于原始字符串的最长回文子串的长度
        int maxLen = 1;
        // 原始字符串的最长回文子串的起始位置，与 maxLen 必须同时更新        
        int start = 0;

        for (int i = 0; i < sLen; i++) {
            if (i < maxRight) {
                int mirror = 2 * center - i;
                // 这一行代码是 Manacher 算法的关键所在，要结合图形来理解
                p[i] = Math.min(maxRight - i, p[mirror]);
            }

            // 下一次尝试扩散的左右起点，能扩散的步数直接加到 p[i] 中
            int left = i - (1 + p[i]);
            int right = i + (1 + p[i]);

            // left >= 0 && right < sLen 保证不越界
            // str.charAt(left) == str.charAt(right) 表示可以扩散 1 次
            while (left >= 0 && right < sLen && str.charAt(left) == str.charAt(right)) {
                p[i]++;
                left--;
                right++;

            }
            // 根据 maxRight 的定义，它是遍历过的 i 的 i + p[i] 的最大者
            // 如果 maxRight 的值越大，进入上面 i < maxRight 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
            if (i + p[i] > maxRight) {
                // maxRight 和 center 需要同时更新
                maxRight = i + p[i];
                center = i;
            }
            if (p[i] > maxLen) {
                // 记录最长回文子串的长度和相应它在原始字符串中的起点
                maxLen = p[i];
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }


    /**
     * 创建预处理字符串
     *
     * @param s      原始字符串
     * @param divide 分隔字符
     * @return 使用分隔字符处理以后得到的字符串
     */
    private String addBoundaries(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(divide);
            stringBuilder.append(s.charAt(i));
        }
        stringBuilder.append(divide);
        return stringBuilder.toString();
    }
}

作者：liweiwei1419
链接：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```









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

### follow up:返回最大子数组和的起始索引

```java
        public int maxSubArray(int[] nums) {
            int n = nums.length;
            //全局最大子数组和的起始索引
            int start = 0, end = 0;
            //局部的最大子数组和的起始索引
            int subStart = 0, subEnd = 0;
            //全局最大值和局部最大值
            int maxx = nums[0], subMaxx = nums[0];
            for (int i = 1; i < n; i++) {
                //之前的数对当前的nums[i]有增强，扩大subEnd
                if (subMaxx > 0) {
                    subMaxx += nums[i];
                    subEnd++;
                } else {//之前subMaxx是负数，重新开始
                    subMaxx = nums[i];
                    subStart = i;
                    subEnd = i;
                }
                //更新全局的
                if (subMaxx > maxx) {
                    maxx = subMaxx;
                    start = subStart;
                    end = subEnd;
                }
            }
            System.out.printf("%d %d ", start, end);
            return maxx;
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



## [64. 最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)

### 方法1:记忆化递归

```java
int[][] cache;
int R, C;

int[][] dirs = {{1, 0}, {0, 1}};


public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0) return 0;
    R = grid.length;
    C = grid[0].length;//行 * 列
    cache = new int[R][C];
    for (int i = 0; i < R; i++) Arrays.fill(cache[i], -1);//初始化
    return dfs(grid, 0, 0);
}


private int dfs(int[][] grid, int r, int c) {
    if (r == R - 1 && c == C - 1) return grid[r][c];
    if (cache[r][c] != -1) return cache[r][c];
    int ans = Integer.MAX_VALUE / 2;// 设置一个最大值
    for (int[] d : dirs) {
        int nr = r + d[0], nc = c + d[1];
        if (!inArea(nr, nc)) continue;
        ans = Math.min(ans, dfs(grid, nr, nc));
    }
    return cache[r][c] = ans + grid[r][c];
}


private boolean inArea(int r, int c) {
    return r >= 0 && r < R && c >= 0 && c < C;
}
```

### 方法2:DP

```java
    public int minPathSum(int[][] grid) {
        int m =grid.length,n= grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] =grid[0][0];
        for(int i =1;i<m;i++) dp[i][0] =dp[i-1][0]+grid[i][0];
        for(int j =1;j<n;j++) dp[0][j] = dp[0][j-1]+grid[0][j];
        for(int i =1;i<m;i++){
            for(int j = 1;j<n;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
```

### Follow Up:打印路径

```java
public static void main(String[] args) {
    _1st handler = new _1st();
    int[][] grid = PrintUtils.processSymbol("[[1,3,1],[1,5,1],[2,2,1]]");
    handler.printShortestPath(grid);
}

int R, C;

public void printShortestPath(int[][] grid) {
    if (grid == null || grid.length == 0) return;
    R = grid.length;
    C = grid[0].length;//行 * 列
    int[][] dp = new int[R][C];
    dp[0][0] = grid[0][0];
    for (int i = 1; i < R; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
    for (int j = 1; j < C; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
    for (int i = 1; i < R; i++) {
        for (int j = 1; j < C; j++) {
            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }
    }
    dfs(dp, grid, R - 1, C - 1);
}

public void dfs(int[][] dp, int[][] grid, int i, int j) {
    if (i == 0 && j == 0) {
        System.out.printf("<-(%d,%d)", i, j);
        System.out.println();
    }
    boolean f = (i == R - 1 && j == C - 1);
    if (i >= 1 && grid[i][j] + dp[i - 1][j] == dp[i][j]) {
        System.out.printf((f ? "" : "<-") + "(%d,%d)", i, j);
        dfs(dp, grid, i - 1, j);
    }
    if (j >= 1 && grid[i][j] + dp[i][j - 1] == dp[i][j]) {
        System.out.printf((f ? "" : "<-") + "(%d,%d)", i, j);
        dfs(dp, grid, i, j - 1);
    }
}
```





## [72. 编辑距离](https://leetcode-cn.com/problems/edit-distance/)

### 方法1:DP

```java
/*dp*/
public int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    int[][] f = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) f[i][0] = i;
    for (int j = 0; j <= n; j++) f[0][j] = j;
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            char c1 = word1.charAt(i - 1), c2 = word2.charAt(j - 1);
            if (c1 == c2) f[i][j] = f[i - 1][j - 1];
            else {
                f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1])) + 1;
            }
        }
    }
    return f[m][n];
}
```



### 方法2:记忆化递归

```java
/*记忆化递归*/
int[][] cache;

public int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    cache = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) Arrays.fill(cache[i], -1);
    return dfs(word1, word2, m, n);
}

private int dfs(String word1, String word2, int m, int n) {
    if (m == 0 || n == 0) return m == 0 ? n : m;
    if (cache[m][n] != -1) return cache[m][n];
    int ans = 0;
    if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
        ans = dfs(word1, word2, m - 1, n - 1);
    } else {
        ans = Math.min(dfs(word1, word2, m - 1, n - 1),
                Math.min(dfs(word1, word2, m - 1, n), dfs(word1, word2, m, n - 1))) + 1;
    }
    return cache[m][n] = ans;
}
```



## [91. 解码方法](https://leetcode-cn.com/problems/decode-ways/)

```java
public int numDecodings(String s) {
    if (s == null || s.charAt(0) == '0') return 0;
    int n = s.length();
    char[] chas = s.toCharArray();
    int[] f = new int[n + 1];
    f[0] = f[1] = 1;
    for (int i = 2; i <= n; i++) {
        if (chas[i - 1] != '0') f[i] += f[i - 1];
        if (chas[i - 2] == '1' || chas[i - 2] == '2' && chas[i - 1] <= '6') f[i] += f[i - 2];
    }
    return f[n];
}
```



## [152. 乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/)

- 参考**[链接](https://leetcode-cn.com/problems/maximum-product-subarray/solution/dpfang-fa-xiang-jie-by-yang-cong-12/)**

```java
public int maxProduct(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    int maxx = nums[0], minn = nums[0], res = nums[0];
    for (int i = 1; i < nums.length; i++) {
        int t = maxx;
        maxx = Math.max(Math.max(nums[i], maxx * nums[i]), minn * nums[i]);
        minn = Math.min(Math.min(nums[i], minn * nums[i]), nums[i] * t);
        res = Math.max(res, maxx);
    }
    return res;
}
```













## [300. 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/)

### 一些名词

- 最长上升子序列($LIS$):`Longest Increasing Subsequence `
- 最长连续序列($LCS$):`Longest Consecutive Sequence `
- 最长连续递增序列($LCIS$):`Longest Continuous Increasing Subsequence`
- 最长公共子序列($LCS$):`Longest Common Subsequence`

> 子串与子序列区别：子串不可跳跃，子序列可以跳跃，如 “AC”是“ABCDEFG”的子序列，而不是子串。 而“ABC”则是其子串

![1589761403470.png](https://img-blog.csdnimg.cn/img_convert/6395bee1a1161b3192210d112ea4d786.png)



### 定义状态

-  $dp[i]$表示在区间$nums[0....i]$区间范围内的最长上升子序列的长度

![2020-05-18_082043.png](https://img-blog.csdnimg.cn/img_convert/06f331d72515c37c2c8a35a0f51b0551.png)


- 比较索引$i$与其前面出现的某个位置$j$的大小
  - 当$nums[i]<=nums[j]$，说明$j$处的值要比$i$处的值的，要是形成子序列则是$nums[0...j,i]$这样的结果，这时候$j$到$i$不能形成递增，**以$i$结尾的子序列所形成的最长子序列的长度等价于以$j$结尾的子序列所形成的最长子序列的长度**，即$dp[i]=dp[j]$
  - 当$nums[i]>nums[j]$，说明$j$处的值小于$i$处值，形成的子序列是$nums[0...j,i]$这样的结果，这时候从$j$到$i$是递增的，这时候需要在长度上+1，即$dp[i]=dp[j+1]$
  - 取上述两种情况的$max$，动态转移方程为： $dp[i] = Math.max(dp[i], dp[j] + 1)|0<=j<i<n$
  - 举例：如果遍历到$i$位置，在$[0-i]$ 区间内有$[0-j] j<i$ 当$nums[i]<=nums[j]$时，表示以$j$结束的子序列和$i$结束的子序列不能形成上升子序列，举 例：$[1,4,5,7,6,8]$，当$i$在$index$为$4$的位置，也就是$nums[i] =6$ ,$j $为$index$ 为$3$时，$nums[j] =7$ ,以$nums[j] $和$nums[i]$ 不能形成一个上升子序列

### 边界条件

- 当$nums[0...i]$只有一个元素时，即以这一个元素结尾的子序列，最长上升子序列是其自身，为$1$

### 核心代码

```java
 for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
```

### 方法1:DP

```java
 public int lengthOfLIS(int[] nums) {
        //dp[i]: 到i为止 (对于所有 j in [0, i], 记录max length of increasing subsequence
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        int res = 0;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //i 位置的数与[0,i]位置之间的数比较，如果大于进逻辑
                if (nums[i] > nums[j]) {
                    //等于dp[i]或者dp[j] + 1（j对应的值比i小）的最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
```

### 方法2：贪心+二分

- 准备一个辅助数组$tails$,其中$tails[i]$表示长度为$i+1$即$nums[0...i]$的序列尾部元素的值
- 辅助数组$tails$一定是严格单调递增的，即在$nums[0...j..i]$区间上，$tails[j]<tails[i]$，下面使用 反证法证明这个结论
  - 假设$nums[0...j..i]$这个区间上，$tails[j]>=tails[i]$,从$i$这个子序列向前删除$i-j$个元素，这时候长度变为$j$的子序列，这时候的尾部元素的值为$x$
  - 根据$tails$数组的定义，$x<tails[i]$
  - 而$x$又是$tails[j]$的值，即$x=tails[j]$
  - 得出$tails[i]>tails[j]$,这与一开始假设的条件矛盾，假设条件不成立

```java
 public int lengthOfLISII(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int end = 0;
        for (int i = 0; i < n; i++) {
            int l = 0, r = end;
            while (l < r) {
                int m = (l + r) / 2;
                if (tails[m] < nums[i]) l = m + 1;
                else r = m;
            }
            tails[l] = nums[i];
            if (end == r) end++;
        }
        return end;
    }
```

















## [416. 分割等和子集](https://leetcode-cn.com/problems/partition-equal-subset-sum/)

### 方法1：朴素版DP

- `dp[N][T+1]`,其中`N`是子集数组的大小，`T`是目标和，多放一个，从0开始的

- `dp[i][j]`表示在子集数组的区间范围内`[0...i]`之间选择若干个数，可以组成`j`
  - 当`j=0`的时候，`dp[i][0]`为`true`，当不选任何子集数组的数的时候，可以形成一直方案
  - 当`i=0`的时候，`dp[0][j]` 指的是当选第0个数的时候，能否等于`j`，显然在`nums[0]=j`的时候满足这种条件，其他都为`false`
- 一般情况，`dp[i][j]`对于，第`i`个数，有这两种情况:
  - 当`j>=nums[i]`的时候，说明`j`还可以拆解,可以选或者不选`i`这个数，只要有一种方案是`true`即可
    - 不选：`dp[i][j]= dp[i-1][j]`
    - 选:`dp[i][j]=dp[i-1][j-nums[i]]`
  - 当`j<nums[i]`的时候，说明`j`不可以拆解，我们肯定选不到`i`这个数了，也就是`dp[i][j]=dp[i-1][j]` 

总结就是：

![](/imgs/leetcode/classify/image-20220324150353538.png)

- `dp[N-1][T]`即答案

```java
        public boolean canPartition(int[] nums) {
            int N = nums.length;
            int sum = 0;
            for (int i : nums) sum += i;
            if (sum % 2 == 1) return false;
            int T = sum / 2;
            boolean[][] dp = new boolean[N][T + 1];
            for (int i = 0; i < N; i++) dp[i][0] = true;
            for (int j = 0; j <= T; j++) if (j == nums[0]) dp[0][j] = true;
            for (int i = 1; i < N; i++) {
                for (int j = 1; j <= T; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= nums[i]) dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
//            PrintUtils.printMatrix(dp);
            return dp[N - 1][T];
        }
```

> 打印

```java
[1,5,11,5]
	   0    1    2    3    4    5    6    7    8    9   10   11
0(1)   T    T    F    F    F    F    F    F    F    F    F    F 
1(5)   T    T    F    F    F    T    T    F    F    F    F    F 
2(11)  T    T    F    F    F    T    T    F    F    F    F    T 
3(5)   T    T    F    F    F    T    T    F    F    F    T    T 
```

`dp[3][11]`往上走到`dp[2][11]` 这一行用掉11 得到11-11 =0 结束

`dp[3][11]`往左走到`dp[3][6]`,这一行用掉5 得11-5 = 6 `dp[3][6]`一直往上走到`dp[1][6]`减去这一行的5  得到 6 -5 =1  到`dp[1][1]` 一直往上走到`dp[0][1]`  减去这一行的1 1-1 =0 结束  5 5 1 是形成11的一个组合

### 方法2：空间压缩O(1)DP

```java
        public boolean canPartition(int[] nums) {
            int N = nums.length;
            int sum = 0;
            for (int i : nums) sum += i;
            if (sum % 2 == 1) return false;
            int T = sum / 2;
            boolean[] dp = new boolean[T + 1];
            dp[0] = true;
            for (int num : nums) {
/*                for (int j = T; j >= 0; j--) {
                    if (j >= num) dp[j] = dp[j] || dp[j - num];
                }*/
                for (int j = T; j >= num; j--) {
                    dp[j] = dp[j] || dp[j - num];
                }

            }
//            PrintUtils.printMatrix(dp);
            return dp[T];
        }
```

关于倒序的遍历的问题：参考这篇：[背包问题之 01 背包问题（科普文，基础，背包九讲）](https://leetcode-cn.com/problems/coin-change/solution/bei-bao-wen-ti-zhi-01bei-bao-wen-ti-ke-pu-wen-ji-c/)





## [464. 我能赢吗](https://leetcode.cn/problems/can-i-win/)

### 方法0：朴素版回溯

> TLE，这种回溯的思路是值得借鉴的

```java
     
        //TLE
        //41/57
        //case:
        //20
        //210
        boolean[] vis;
        int maxChoosableInteger, desiredTotal;

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            this.maxChoosableInteger = maxChoosableInteger;
            this.desiredTotal = desiredTotal;
            for (int i = 1; i <= maxChoosableInteger; i++) {
                vis = new boolean[21];
                vis[i] = true;
                if (dfs(i)) return true;
            }
            return false;
        }


        /**
         * @param curSum 当前已经选的数的总和
         * @return
         */
        private boolean dfs(int curSum) {
            //已经达到目标数desiredTotal，返回true
            if (curSum >= desiredTotal) return true;
            //遍历从1 到 maxChoosableInteger的每一个数
            for (int i = 1; i <= maxChoosableInteger; i++) {
                if (vis[i]) continue;//被访问过，不需要继续
                vis[i] = true;//标记
                //如果curSum+i 即对手能赢得游戏，意味着当前玩家失败
                if (dfs(curSum + i)) {
                    return vis[i] = false;
                }
                vis[i] = false;//恢复
            }
            return true;
        }
```



### 方法1:回溯+状态压缩

- `1 <= maxChoosableInteger <= 20`数据范围，想到可能需要状态压缩

```java
       Boolean[] dp;
        int maxChoosableInteger;

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            //最大的可以选的数(maxChoosableInteger)大于等于最终目标数(desiredTotal),A先抽，直接拿desiredTotal，胜出
            if (desiredTotal <= maxChoosableInteger) return true;
            //[1...maxChoosableInteger]之间的总数的和小于最终目标数(desiredTotal),A和By将数选完，也不能凑成(desiredTotal),A赢不了，返回false
            if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
            this.maxChoosableInteger = maxChoosableInteger;
            dp = new Boolean[1 << maxChoosableInteger];
            return dfs(desiredTotal, 0);
        }

        /**
         * @param desiredTotal 当前离达成目标的原始desiredTotal还差多少
         * @param state        当前从1到maxChoosableInteger之间的每个数被选中的状态，在二进制下，1为选中，0为未选中
         * @return
         */
        private boolean dfs(int desiredTotal, int state) {
            //如果该state被标记过，返回
            if (dp[state] != null) return dp[state];
            //从 1 到 maxChoosableInteger 判断每一位是否被选中过
            for (int i = 1; i <= maxChoosableInteger; i++) {
                //当前位 需要判断下当前位是否是1（二进制下）
                int cur = 1 << (i - 1);
                if ((cur & state) == 0) {//当前位不是1是0，表示当前位可以选
                    //case1: 选中当前的数i后，可以达成最终的目标
                    //cas2:  选中当前的数i后，暂时不能达成目标，但是对手最终不能达成目标，意味着当前玩家最终可以达成目标
                    //cur | state 表示将当前数i选中 送到下一轮的状态中
                    if (desiredTotal - i <= 0 || !dfs(desiredTotal - i, cur | state)) {
                        return dp[state] = true;
                    }
                }
            }
            return dp[state] = false;
        }
```

### 方法2：回溯+HashMap

> 国际站的高赞的解法，[链接](https://leetcode.com/problems/can-i-win/discuss/95277/Java-solution-using-HashMap-with-detailed-explanation)

- `format`的技巧有点类似: [将矩阵当成二进制转化成十进制](https://cnwangzhou.gitbook.io/algorithm/zhuan-lan/er-wei-ju-zhen-de-chang-jian-zhuan-huan-ji-qiao#ji-qiao-2-jiang-ju-zhen-dang-cheng-er-jin-zhi-zhuan-hua-cheng-shi-jin-zhi)

```java
Map<Integer, Boolean> memo = new HashMap();
boolean[] vis;

public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    //最大的可以选的数(maxChoosableInteger)大于等于最终目标数(desiredTotal),A先抽，直接拿desiredTotal，胜出
    if (desiredTotal <= maxChoosableInteger) return true;
    //[1...maxChoosableInteger]之间的总数的和小于最终目标数(desiredTotal),A和By将数选完，也不能凑成(desiredTotal),A赢不了，返回false
    if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
    vis = new boolean[maxChoosableInteger + 1];
    return helper(desiredTotal);
}

public boolean helper(int desiredTotal) {
    if (desiredTotal <= 0) return false;
    int key = format(vis);
    if (!memo.containsKey(key)) {
        //遍历vis中的数，尝试没有被选中的
        for (int i = 1; i < vis.length; i++) {
            if (!vis[i]) {//i没有被选择
                vis[i] = true;//标记
                // 当前i被选中，留给对手的desiredTotal - i的空间，但是对手没法胜出
                if (!helper(desiredTotal - i)) {
                    memo.put(key, true);
                    vis[i] = false;//恢复
                    return true;
                }
                vis[i] = false;//恢复
            }
        }
        memo.put(key, false);
    }
    return memo.get(key);
}

// 遍历vis数组，每一位true的设置为1，相当于压缩
public int format(boolean[] vis) {
    int res = 0;
    for (boolean b : vis) {
        res <<= 1;
        if (b) res |= 1;
    }
    return res;
}
```

- 另，使用数组做key

> TLE

```java
        int[] state;
        Map<String, Boolean> memo = new HashMap<>();

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            if (desiredTotal <= maxChoosableInteger) return true;
            if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
            state = new int[maxChoosableInteger + 1];
            return dfs(desiredTotal);
        }

        private boolean dfs(int desiredTotal) {
            String key = Arrays.toString(state);
            if (memo.containsKey(key)) return memo.get(key);
//            System.out.printf("key:%s ", key);
            for (int i = 1; i < state.length; i++) {
                if (state[i] == 0) {
                    state[i] = 1;
//                    System.out.printf(" %d\n", desiredTotal);
                    if (desiredTotal - i <= 0 || !dfs(desiredTotal - i)) {
                        state[i] = 0;
                        memo.put(key, true);
                        return true;
                    }
                    state[i] = 0;
                }
            }
            memo.put(key, false);
            return false;
        }
```





## [467. 环绕字符串中唯一的子字符串](https://leetcode.cn/problems/unique-substrings-in-wraparound-string/)



```java
      //需要知道的性质：
        //性质1：
        //  以字母结尾的唯一子字符串的最大数目等于以该字母结尾的最大连续子字符串的长度。例如“abcd”，
        // 以“d”结尾的唯一子字符串的最大数目是4，显然它们是“abcd”、“bcd”、“cd”和“d”。
        //性质2：
        //如果有重叠，我们只需要考虑最长的一个，因为它覆盖了所有可能的子字符串。示例：“abcdbcd”，
        // 以“d”结尾的唯一子字符串的最大数目为4，并且由第二个“bcd”部分形成的所有子字符串都已包含在4个子字符串中。
        public int findSubstringInWraproundString(String p) {
            char[] ch = (" " + p).toCharArray();
            //dp[k] 表示 p 中以字符字符 k+'a' 结尾且在 s 中的子串的最长长度
            int[] dp = new int[26];
            int count = 1;
            for (int i = 1; i < ch.length; i++) {
                int k = ch[i] - 'a';
                if (check(ch[i - 1], ch[i])) {
                    count++;
                } else {
                    count = 1;
                }
                dp[k] = Math.max(dp[k], count);

            }
            int res = 0;
            for (int x : dp) res += x;
            return res;
        }

        private boolean check(char prev, char cur) {
            if (prev == 'z' && cur == 'a') return true;
            return cur - prev == 1;
        }
```









## [509. 斐波那契数](https://leetcode-cn.com/problems/fibonacci-number/)

#### 方法1:暴力递归

- 经典的种子题

```java
    public int fib(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        return fib(n-1)+fib(n-2);
    }
```

#### 方法2:自顶向下记忆化递归(Top-down)

```java
Integer[] memo;
public int fib(int n) {
    memo = new Integer[n+1];
    return recursive(n);
}   
private int recursive(int num){
    if(memo[num]!=null) return memo[num];
    if(num==0) return 0;
    if(num==1) return 1;
    return memo[num] = ( fib(num-1)+fib(num-2));
}
```

#### 方法3:自底向上填表DP(Bottom-up)

```java
public int fib(int n) {
    if(n<2) return n;
    int[] f= new int[n+1];
    f[0] = 0;
    f[1]= 1;
    for(int i = 2;i<=n;i++) f[i] =f[i-1]+f[i-2];
    return f[n];
}
```

#### 方法4:矩阵相乘+快速幂





## [691. 贴纸拼词](https://leetcode.cn/problems/stickers-to-spell-word/)

### 方法1:状态压缩+BFS

```java
   public int minStickers(String[] stickers, String target) {
            int n = target.length();
            //dp[state]表示二进制状态为state时的最小贴纸数量，当每一位都为1的时候，为最终要返回的结果
            //返回dp[(1<<n)-1]
            int[] dp = new int[1 << n];
            Queue<Integer> q = new LinkedList<>();
            q.offer(0);
            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int k = 0; k < size; k++) {
                    int base = q.poll();
                    //每一轮从stickers中摘取字符
                    for (String s : stickers) {
                        int state = base;//当前层是以base这一层作为基准开始扩散
//                        System.out.printf("level:%d ", level);
//                        PrintUtils.toBinaryString(state, 6);
                        int[] cnt = new int[26];
                        //统计s字符串中字母出现的次数
                        for (char c : s.toCharArray()) cnt[c - 'a']++;
                        //遍历target字符串
                        for (int i = 0; i < n; i++) {
                            char c = target.charAt(i);
                            //当前字符在s中出现过且target所在的位没有被填充
                            if (cnt[c - 'a'] != 0 && (state & 1 << i) == 0) {
                                cnt[c - 'a']--;
                                state |= 1 << i;//填充
                            }
                        }
                        //dp[state]不为0说明这个state之前已经被访问过
                        if (dp[state] != 0 || state == 0) continue;
                        //当前的state推入queue中保持等待下一层弹出
                        q.offer(state);
                        //该贴纸可以被选用，即用了该贴纸一次
                        dp[state] = dp[base] + 1;
                        //终止条件判断
                        if (state == (1 << n) - 1) return dp[state];
                    }
                }
                level++;
            }
            return -1;
        }
```



### 方法2：记忆化DFS

```java
//            String[] stickers = {"with", "example", "science"};
//            String target = "thehat";


Map<String, Integer> memo = new HashMap<>();
int[][] dict;
int n;


public int minStickers(String[] stickers, String target) {
    n = stickers.length;
    dict = new int[n][26];
    for (int i = 0; i < n; i++) {
        for (char c : stickers[i].toCharArray()) dict[i][c - 'a']++;
    }
    memo.put("", 0);
    return dfs(target);
}

private int dfs(String target) {
    if (memo.containsKey(target)) return memo.get(target);
    int ans = Integer.MAX_VALUE;
    int[] cnt = new int[26];
    //统计当前target中字符的出现次数
    for (char c : target.toCharArray()) cnt[c - 'a']++;
    for (int i = 0; i < n; i++) {
        if (dict[i][target.charAt(0) - 'a'] == 0) continue;
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 26; j++) {
            if (cnt[j] > 0) {
                //dict[i]中有哪些字符可以对当前的target字符串有贡献，移除掉这部分贡献，拼接剩下的字符
                //举例 thahat->aeht->th->""
                for (int k = 0; k < Math.max(0, cnt[j] - dict[i][j]); k++) {
                    sb.append((char) ('a' + j));
                }
            }
        }
        int t = dfs(sb.toString());
        if (t != -1) ans = Math.min(ans, t + 1);
    }
    memo.put(target, ans == Integer.MAX_VALUE ? -1 : ans);
    return memo.get(target);
}
```



### 方法3：位运算+DP

```java
     public int minStickers(String[] stickers, String target) {
            int n = target.length();
            //dp[state]表示二进制表示为state时的最小贴纸数量
            int[] dp = new int[1 << n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;//当空的字符，需要的贴纸数量为0
            //遍历每一位获取每一个子集
            for (int bit = 0; bit < (1 << n); bit++) {
                if (dp[bit] == Integer.MAX_VALUE) {
                    continue;
                }
                for (String s : stickers) {
                    int state = bit;//当前位从bit开始出发
                    for (char c : s.toCharArray()) {
                        for (int i = 0; i < n; i++) {
                            //target中有该字符且该字符没有被处理到
                            if (target.charAt(i) == c && ((state >> i) & 1) == 0) {
                                state |= 1 << i;
                                break;
                            }
                        }
                    }
                    //从bit转移到state 只需要一步也就是一次贴纸
                    dp[state] = Math.min(dp[state], dp[bit] + 1);
                }
            }
            return dp[(1 << n) - 1] == Integer.MAX_VALUE ? -1 : dp[(1 << n) - 1];
        }
```







## [730. 统计不同回文子序列](https://leetcode.cn/problems/count-different-palindromic-subsequences/)

### 方法1：三维数组+DP

- [统计不同回文子序列](https://leetcode.cn/problems/count-different-palindromic-subsequences/solution/tong-ji-bu-tong-hui-wen-zi-xu-lie-by-lee-7slg/)

```java
       public int countPalindromicSubsequences(String s) {
            int MOD = (int) 1e9 + 7;
            int n = s.length();
            //状态  dp(x,i,j) 表示在字符串区间 s[i:j] 中以字符 x 为开头和结尾的不同「回文串」总数，
            // 其中 s[i:j] 表示字符串 s 从下标 i 到下标 j 的子串（包含下标 i 和下标 j）
            int[][][] dp = new int[4][n][n];//只有 abcd四种字符
            for (int i = 0; i < n; i++) dp[s.charAt(i) - 'a'][i][i] = 1;
            for (int len = 2; len <= n; len++) {
                for (int i = 0; i + len <= n; i++) {
                    int j = i + len - 1;//选定i和j的区间 长度为len
                    for (char c = 'a'; c <= 'd'; c++) {
                        int k = c - 'a';
                        if (s.charAt(i) == c && s.charAt(j) == c) {//s[i]与s[j]均相同
                            dp[k][i][j] = (2 +
                                    //两段求和取模
                                    (dp[0][i + 1][j - 1] + dp[1][i + 1][j - 1]) % MOD +
                                    (dp[2][i + 1][j - 1] + dp[3][i + 1][j - 1]) % MOD
                            ) % MOD;
                        } else if (s.charAt(i) == c) {//s[j]不同
                            dp[k][i][j] = dp[k][i][j - 1];
                        } else if (s.charAt(j) == c) {//s[i]不同
                            dp[k][i][j] = dp[k][i + 1][j];
                        } else {//s[i]与s[j]均相不同
                            dp[k][i][j] = dp[k][i + 1][j - 1];
                        }
                    }
                }
            }
            int res = 0;
            //分步求和取模
            for (int k = 0; k < 4; k++) res = (res + dp[k][0][n - 1]) % MOD;
            return res;
        }
```

### 方法2：二维数组+DP

```java
        public int countPalindromicSubsequences(String s) {
            int MOD = (int) 1e9 + 7;
            int n = s.length();
            //定义 dp(i,j) 来表示字符串区间 s[i:j] 中的不同的回文串个数
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) dp[i][i] = 1;
            for (int len = 2; len <= n; len++) {
                for (int i = 0; i + len <= n; i++) {
                    int j = i + len - 1;
                    if (s.charAt(i) == s.charAt(j)) {
                        int lo = i + 1, hi = j - 1;
                        while (lo <= hi && s.charAt(lo) != s.charAt(i)) lo++;
                        while (lo <= hi && s.charAt(hi) != s.charAt(j)) hi--;
                        if (lo > hi) {//lo > hi
                            dp[i][j] = (2 + dp[i + 1][j - 1] * 2) % MOD;
                        } else if (lo == hi) {// lo = hi
                            dp[i][j] = (1 + dp[i + 1][j - 1] * 2) % MOD;
                        } else {//lo < hi
                            dp[i][j] = (dp[i + 1][j - 1] * 2 % MOD - dp[lo + 1][hi - 1] + MOD) % MOD;
                        }
                    } else {
                        //相减后可能会为负数 +MOD后恢复
                        dp[i][j] = ((dp[i][j - 1] + dp[i + 1][j]) % MOD - dp[i + 1][j - 1] + MOD) % MOD;
                    }
                }
            }
            return dp[0][n - 1];
        }
```







## [2222. 选择建筑的方案数](https://leetcode.cn/problems/number-of-ways-to-select-buildings/)

- 动态规划的解法，类似题目有970













## [6050. 字符串的总引力](https://leetcode-cn.com/problems/total-appeal-of-a-string/)

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com//imgs/leetcode/classify/20220501192107.png)

- `sum[i]`表示`s[0...i]`区间的字符产生的引力值
- `pos[c]`表示字符`c`最近一次出现的位置

```java
public long appealSum(String s) {
    int n = s.length();
    //sum[i]为字符s[0...i]的引力值之和
    int[] sum = new int[n];
    sum[0] = 1;
    //字符最近一次出现的位置
    int[] pos = new int[26];
    Arrays.fill(pos, -1);
    pos[s.charAt(0) - 'a'] = 0;
    long res = sum[0];
    for (int i = 1; i < n; i++) {
        char c = s.charAt(i);
        if (pos[c - 'a'] == -1) sum[i] = sum[i - 1] + i + 1;
        else sum[i] = sum[i - 1] + (i - pos[c - 'a']);//该字符最近一次出现的位置
        pos[c - 'a'] = i;
        res += sum[i];
    }
    return res;
}
```



- 优化

```java
public long appealSum(String s) {
    long res = 0;
    int[] pos = new int[26];
    int sum = 0;
    Arrays.fill(pos, -1);
    for (int i = 0; i < s.length(); i++) {
        int c = s.charAt(i) - 'a';
        sum += i - pos[c];
        res += sum;
        pos[c] = i;
    }
    return res;
}
```

- 优化
  - 记录每个字母上一次的下标，累加贡献，设定贡献为一个字母在子字符串第一次出现时产生贡献，后续重复出现不贡献；
  - 则每次贡献为 左边有 i - lastpos 种选法，右边有 n - i 种选法；
  - 初始位置设为-1；

```java
public long appealSum(String s) {
    int[] pos = new int[26];
    long sum = 0;
    Arrays.fill(pos, -1);
    int n = s.length();
    for (int i = 0; i < n; i++) {
        int c = s.charAt(i) - 'a';
        sum += (n - i) * (i - pos[c]);
        pos[c] = i;
    }
    return sum;
}
```

- dp的思路

```java
//TLE 63 / 76
public long appealSum(String s) {
    int[] pos = new int[26];
    int n = s.length();
    Arrays.fill(pos, -1);
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < 26; i++) map.put(i, -1);
    //pos[i]表示当前字符出现时，该字符前一出现的位置
    for (int i = 0; i < s.length(); i++) {
        int k = s.charAt(i) - 'a';
        pos[k] = map.get(k);
        map.put(k, i);
    }
    int[][] dp = new int[n][n];
    long res = dp[0][0] = 1;
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            if (j == 0) continue;
            if (i == j) {
                dp[i][j] = 1;
                res += 1;
            } else {
                if (pos[s.charAt(j) - 'a'] >= i && pos[s.charAt(j) - 'a'] < j) dp[i][j] = dp[i][j - 1];
                else dp[i][j] = dp[i][j - 1] + 1;
                res += dp[i][j];
            }
        }
    }
    return res;
}
```
