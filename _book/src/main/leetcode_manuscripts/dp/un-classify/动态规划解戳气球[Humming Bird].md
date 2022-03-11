## 动态规划解戳气球[Humming Bird]

![hummingbird-295026_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\un-classify\动态规划解戳气球.assets\hummingbird-295026_640-1599901143571.png)





### 方法1:DP 

#### 定义

$dp[i][j]$是$(i,j)$左开右开区间上的所能获得的最大的硬币数

#### 转移方程

$$dp[i][j] = \begin{cases}
\max\limits_{ x =i+1}^{j-1} val[i]*val[k]*val[j]+dp[i][k]+dp[k][j] & i<j-1 \\
0 & i>=j-1 \\
\end{cases}$$

![image-20200912162754206](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\un-classify\动态规划解戳气球.assets\image-20200912162754206.png)

#### 思路

- 准备一个头尾冗余的数组val[]

- 注意k的左边不一定是k-1，k的右边也不一定是k+1

```java
    public int maxCoins(int[] nums) {

        int n = nums.length;
        //弄一个辅助数组
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) val[i] = nums[i - 1];
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][n + 1];
    }
```





