## 动态规划解分割数组I[Red Fox]

> 欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。







$dp[i][j]$表示前





![image-20200727214902854](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解分割数组I[Red Fox].assets\image-20200727214902854.png)

#### 前缀和辅助数组

定义$prefix[i]$表示$nums$数组中前$i$个数的和，即$nums[0...i-1]$之前的和，此前缀和数组初始化$n+1$,$prefix[0]$冗余，最为辅助数组

