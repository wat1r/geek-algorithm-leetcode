## 动态规划解路径之不同路径[Chipmunk]



### 1.不同路径II

![image-20200706210119286](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\paths\动态规划解路径之不同路径[Chipmunk ].assets\image-20200706210119286.png)



#### 方法1：模板法DP

#### 元方法

利用模板法大体分三步（见上图的步骤标识）:

- 计算$dp$表的第一行
- 计算$dp$表的第一列
- 计算$dp$表的一般情况，直到填充完$dp$表，方可结束

#### 定义状态：

- $dp[i][j]$表示从$[0,0]$位置走到$[i,j]$位置的路径的总数，注意机器人只能向下或者向右
- 举例：
  - 第一行：如下图$step1$，当机器人只能向右移动时，这时能计算第一行的值，道理很简单：没有遇到障碍物，则到达当前位置的路径有一种，当遇到障碍物，当前点以及其右边所有点的路径都是不可达，也就是$0$，故：$dp[0][0...m-1]$ = ${0|1}$
  - 第一列：如下图$step2$，同理可得：$dp[0][0...n-1]$ = ${0|1}$
  - 一般情况：如下图$step3$，当到达$[i,j]$这个位置时，其只会来自两个点，正上方或者左方的点，坐标$[i-1,j]$与$[i,j-1]$,而到达这两个点的路径的条数分别为$dp[i-1,j]$与$dp[i,j-1]$,而很显然，要想到达$[i,j]$，只能通过$[i-1,j]$与$[i,j-1]$而来，故$dp[i,j]=dp[i-1,j]+dp[i,j-1]$

![63_1](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\paths\动态规划解路径之不同路径[Chipmunk ].assets\63_1.jpg)

```java
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {//第一列
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int j = 0; j < m; j++) {//第一行
            if (obstacleGrid[0][j] == 1) break;
            dp[0][j] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
       return dp[n - 1][m - 1];
    }
```

#### **复杂度分析**:

- 时间复杂度：$O(M*N) $ 
- 空间复杂度： $O(M*N)$  其中$M$和 $N$是矩阵的行列

### 方法2：压缩版DP(O(n))

- 凡是当前状态只依赖位于$dp$表中的上方、左方、左上方等状态时，也就是依赖的状态是部分位置的数据时，一般情况下可以做空间压缩到$O(n)$或者$O(1)$

#### 定义状态

- $dp[m]$其中$m$为列数，$dp[j]$表示从$[0,0]$到$[i,j]$的路径数
- 刚开始时，初始化第一行的$dp[0...m]$
- 一般情况下：

```java
 dp[j] = (j == 0) ? dp[j] : dp[j] + dp[j - 1];
```

- 其中当$j==0$时，说明是新开一行的第一个数，上面的$=$后面的$dp[j]$是当前位置的左边位置的值，$dp[j-1]$是当前位置上面的位置的值，滚动刷新$dp$数组

```JAVA
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        //列*行
        int m = obstacleGrid[0].length, n = obstacleGrid.length;
        int[] dp = new int[m];
        for (int j = 0; j < m; j++) {
            if (obstacleGrid[0][j] == 1) break;
            dp[j] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                dp[j] = (j == 0) ? dp[j] : dp[j] + dp[j - 1];
            }
        }
        return dp[m-1];
    }
```

#### **复杂度分析**:

- 时间复杂度：$O(M*N) $ 
- 空间复杂度： $O(M)$  其中$M$和 $N$是矩阵的行列，只需要数组$dp[m]$维持状态



### 2.不同路径

> 本题与上面一题比较，少了障碍物，考虑的点少一些，也是两种方法

![1594049129597](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\paths\动态规划解路径之不同路径[Chipmunk].assets\1594049129597.png)

#### 方法1：模板法DP

```java
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int j = 0; j < n; j++) dp[0][j] = 1;
    for (int i = 0; i < m; i++) dp[i][0] = 1;
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }
    return dp[m - 1][n - 1];
}
```

#### **复杂度分析**:

- 时间复杂度：$O(M*N) $ 
- 空间复杂度： $O(M*N)$  其中$M$和 $N$是矩阵的行列

#### 方法2：压缩版DP(O(n))

```java
public int uniquePaths1st(int m, int n) {
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) dp[i] = 1;
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j] + dp[j - 1];
        }
    }
    return dp[n - 1];
}
```

#### **复杂度分析**:

- 时间复杂度：$O(M*N) $ 
- 空间复杂度： $O(M)$  其中$M$和 $N$是矩阵的行列，只需要数组$dp[m]$维持状态



















