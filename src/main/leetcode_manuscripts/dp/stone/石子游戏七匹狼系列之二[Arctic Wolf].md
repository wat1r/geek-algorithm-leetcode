## 石子游戏七匹狼系列之二[Arctic Wolf]



![image-20201217201430079](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之二[Arctic Wolf].assets\image-20201217201430079.png)



### 方法1：记忆化搜索











### 方法2：DP

#### 定义状态

- **`f[i][m]` 表示当在`[i...n-1]`这个石子的区间范围内，`M`取`m`时，当前玩家能获取到的最多的石子数量**

#### 转移方程

- 如果当前玩家从`[i...i+x-1]`选了这个范围的石子，留给另外一个玩家的选择范围是`[i+x...max(m,x)]`，而











### Q&A

- 





