## 动态规划解路径之不同路径[Chipmunk]



![image-20200706210119286](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\paths\动态规划解路径之不同路径[Chipmunk ].assets\image-20200706210119286.png)



### 方法1：模板法DP

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



