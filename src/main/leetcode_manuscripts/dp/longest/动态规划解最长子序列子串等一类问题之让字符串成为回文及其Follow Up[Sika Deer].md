

## 动态规划解最长子序列子串等一类问题之让字符串成为回文及其Follow Up[Sika Deer]







### 0.源起

![1589122737432](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之Sika Deer.assets\1589122737432.png)

### 1.让字符串成为回文串的最少插入次数

#### 定义状态

- $n$为$str$的长度，$dp[n][n]$是一张二维表

- 定义:$dp[i][j]$表示的是子串$str[i][j]$最少添加多少字符可以使其整体变成一个回文，注意，是**最少**

> 问题来了，如何填写这张dp table呢？



![Package Diagram](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之让字符串成为回文及其Follow Up[Sika Deer].assets\Package Diagram.jpg)









$dp[i][j]$表示子串$str[i...j]$范围内的最少添加多少个字符后，可以形成回文子串











> 梅花鹿（学名：Cervus nippon）
> 　驯鹿（学名：Rangifer tarandus），又名角鹿
> 麋鹿（学名：Elaphurus davidianus）又名“四不象”
>  驼鹿（英文名称：moose）：是世界上最大的鹿科动物
>  　马鹿（学名：Cervus elaphus）是仅次于驼鹿的大型鹿类
>   黇鹿（学名：Dama dama）是鹿科的一种反刍动物，
>   　 水鹿（学名：Rusa unicolor）体型粗壮接近马鹿
>   普度鹿是偶蹄目、鹿科动物。它身体圆小
>    坡鹿（拉丁学名：Cervus eldii），是印度泽鹿的同属
>    白唇鹿（学名：Gervus albirostris）：体形高大，体长约2米
>    豚鹿（学名：Axis porcinus）也叫芦蒿鹿，体形中





