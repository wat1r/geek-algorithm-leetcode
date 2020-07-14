## 动态规划解路径之最小路径和[Flying Squirrel]



64/120

### 三角形最小路径和

![2020-07-14_212734](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\paths\动态规划解路径之最小路径和[Flying Squirrel].assets\2020-07-14_212734.jpg)



#### 方法1：从底到顶DP

![2020-07-14_213431](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\paths\动态规划解路径之最小路径和[Flying Squirrel].assets\2020-07-14_213431.jpg)

#### 定义状态：

- $dp[i][j]$表示点$[i,j]$到最后一行(也就是第$n$行，实际的数是从$1$到$n$行)的最小路径和,其中$i,j$表示的是第几行第几列的数，都是从$1$开始的

> 每一步只能移动到下一行中相邻的结点上。
>
> **相邻的结点** 在这里指的是 `下标` 与 `上一层结点下标` 相同或者等于 `上一层结点下标 + 1` 的两个结点。

- 从上面的定义很容易得出$[i,j]$这个点的状态依赖于$[i+1,j]$(正下方的点)，和$[i+1,j+1]$(右下方的点)，取两个中的最小值+$matrix[i][j]$这个值，因此动态转移方程:$dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+$$matrix[i][j]$

#### 边界条件:

- 第1行第1列的数需要被加上送到$[0,0]$这个点的数，这个点的数作为结果返回，三角是$n$行，需要在此基础上多出来一行，存$dp[0][0]$

```java
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int n = triangle.size();
        int[][] dp = new int[n+1][n+1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
```















