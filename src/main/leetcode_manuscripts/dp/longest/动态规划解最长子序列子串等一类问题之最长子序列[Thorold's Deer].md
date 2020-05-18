## 动态规划解最长子序列子串等一类问题之最长子序列[Thorold's Deer]
### 一些名词

- 最长连续递增序列($LCIS$):`Longest Continuous Increasing Subsequence`
- 最长上升子序列($LIS$):`Longest Increasing Subsequence `
- 最长连续序列($LCS$):`Longest Consecutive Sequence `
- 最长公共子序列($LCS$):`Longest Common Subsequence`

> 子串与子序列区别：子串不可跳跃，子序列可以跳跃，如 “AC”是“ABCDEFG”的子序列，而不是子串。 而“ABC”则是其子串

![1589726004493](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之最长子序列[Thorold's Deer].assets\1589726004493.png)

### 定义状态

-  $dp[i]$表示在区间$nums[0....i]$区间范围内的最长上升子序列的长度













