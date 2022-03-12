## 动态规划解拆分整数I[Silver Fox]



![fuchs-1615043_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解拆分整数I[Silver Fox].assets\fuchs-1615043_960_720.jpg)

![image-20200730084113546](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解拆分整数I[Silver Fox].assets\image-20200730084113546.png)



### 定义状态

**$dp[i]$表示将$i$拆分成至少2个正整数的和后，这些整数乘起来后得到的乘积**

### 转移方程

先从一般情况来思考：

- $dp[0]$没有什么意义，因为下标从0开始，才出现这个值，设置其为0

- $dp[1]$ 1不可以再次被拆分为更小的正整数，设置其为0
- $dp[2]$，2刚好只有一种拆法，拆成两个1，$dp[2]$=1*1=1
- ...
- $dp[i]$,思考$dp[i]$，可以想象在其范围内，我们先已经拆出来一个$j$,从$[1...j]$作为一个整体1，见下图情景1，剩下的部分也就是$[j+1....i]$,可以有两种选择
  - 继续当成一个整体，为整体2，其值为$i-j$, 显然$dp[i]$=$j$X$(i-j)$
  - 继续拆分，但是我们也不知道能拆分成多少份，但是我们不管，拆分的结果就是$dp[i-j]$（再读一遍状态的定义），见下图情景2，$dp[i]$=$j$X$dp[i-j]$
  - 因此 $dp[i]$=$j$X$max(i-j,dp[i-j])$

- $dp[n]$，要返回的值

### 边界

上面已经讨论过了

![image-20200730084027235](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解拆分整数I[Silver Fox].assets\image-20200730084027235.png)

### 完整代码

```java
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], j * Math.max(i - j, dp[i - j]));
            }
        }
        return dp[n];
    }
```

