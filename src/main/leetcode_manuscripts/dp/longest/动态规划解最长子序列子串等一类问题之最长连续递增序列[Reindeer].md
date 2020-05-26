## 动态规划解最长子序列子串等一类问题之最长连续递增序列[Reindeer]

### 一些名词

- 最长上升子序列($LIS$):`Longest Increasing Subsequence `
- 最长连续序列($LCS$):`Longest Consecutive Sequence `
- 最长连续递增序列($LCIS$):`Longest Continuous Increasing Subsequence`
- 最长公共子序列($LCS$):`Longest Common Subsequence`

> 子串与子序列区别：子串不可跳跃，子序列可以跳跃，如 “AC”是“ABCDEFG”的子序列，而不是子串。 而“ABC”则是其子串

### 1.最长连续递增序列

![1590505325894](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之最长上升子序列[Reindeer].assets\1590505325894.png)

> 题目要求*最长连续递增序列*，序列本身可以跳跃，但是题意要求*连续*，也即是不可跳跃

#### 定义状态

-  $dp[i]$表示以$i$位置结尾，即$nums[i]$值结尾的，最长连续递增序列的长度 

- 只要关注当前位置$i$与其前一个的位置$i-1$的值的大小：
  - $nums[i]>nums[i-1]$，$i$至少可以与$i-1$形成一个连续递增序列，因为它们俩挨着，并且是递增的，长度上是$dp[i-1]+1$
  - $nums[i]<=nums[i-1]$,这时候，不能形成连续递增序列，后一个数要比前一个数小，呈下降的趋势，注意$=$不认为是递增的

#### 方法1:DP($O(N)$)

```java

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);//至少可以与其自身形成递增序列
        int max = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
```

#### 方法2:DP($O(1)$)



### 2.最长递增子序列的个数

![1590505435058](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之最长连续递增序列[Reindeer].assets\1590505435058.png)