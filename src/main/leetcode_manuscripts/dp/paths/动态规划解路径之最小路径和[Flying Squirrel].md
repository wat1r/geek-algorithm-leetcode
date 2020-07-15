## 动态规划解路径之最小路径和[Flying Squirrel]

![fantasy-3665703_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\paths\动态规划解路径之最小路径和[Flying Squirrel].assets\fantasy-3665703_640.jpg)

### 1.三角形最小路径和

![2020-07-14_212734](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\paths\动态规划解路径之最小路径和[Flying Squirrel].assets\2020-07-14_212734.jpg)



#### 方法1：从底到顶DP-I

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

#### 方法2：从底到顶DP-II

![2020-07-15_091915](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\paths\动态规划解路径之最小路径和[Flying Squirrel].assets\2020-07-15_091915.jpg)

#### 定义状态：

- $dp[i][j]$表示点$[i,j]$到上图中的底部假想虚线的最小路径和，,其中$i,j$表示的是第几行第几列的数，都是从$0$开始的

- 动态转移方程:$dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+$$matrix[i][j]$，参见方法1的过程

#### 边界条件:

- 这里区别于方法1的是，行列的下标到$n-1$止，在构造$dp$的过程中不同

```
int[][] dp = new int[n+1][n+1]; //方法1
int[][] dp = new int[n][n];     //方法2
```

- 所有需要做一个初始的$base$，以扩展到普遍的情况，很显然，可以想到最后一行(也就是索引为$n-1$的行)到假想虚线的最小路径和，为这一行本身，通过这一行直接往下越级
- 一般情况就很好得到了，见图，$dp$完全是和$triangle$映射都的，我们要求的$dp[0][0]$为坐标$[0,0]$到底部假想虚线的最小路径和（穿越了整个$triangle$） 



```java
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int j = 0; j <= n - 1; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
```

#### 方法3：空间压缩DP

- 大部分DP类型的题可以做空间压缩，将空间复杂度做到$O(N)$
- 很多初学者不太理解或是不会写这种，其实没那么玄乎，只是在编码技巧的时候少用了数组空间，用一个数组来回滚动，拿本题举例

```java
二维的是这样的
 dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
可以拿掉一维，变成
  dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
```

- 不过有人又开始问了，为何拿掉了$i$这一维，而不是$j$这一维，注意二维的后面的都是$i+1$,也就是说这个不依赖发生变化，去掉即可

```java
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int n = triangle.size();
        int[] dp = new int[n];
        for (int j = 0; j <= n - 1; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
```

#### **复杂度分析**:

- 时间复杂度：$O(N*N) $ 
- 空间复杂度： $O(N)$ $N$是三角形的行数



### 2.最小路径和

TO BE CONTINUED