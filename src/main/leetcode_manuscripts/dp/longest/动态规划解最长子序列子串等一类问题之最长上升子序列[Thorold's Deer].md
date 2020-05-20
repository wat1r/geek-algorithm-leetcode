## 动态规划解最长子序列子串等一类问题之最长上升子序列[Thorold's Deer]
### 一些名词

- 最长上升子序列($LIS$):`Longest Increasing Subsequence `
- 最长连续序列($LCS$):`Longest Consecutive Sequence `
- 最长连续递增序列($LCIS$):`Longest Continuous Increasing Subsequence`
- 最长公共子序列($LCS$):`Longest Common Subsequence`

> 子串与子序列区别：子串不可跳跃，子序列可以跳跃，如 “AC”是“ABCDEFG”的子序列，而不是子串。 而“ABC”则是其子串

![1589761403470](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之最长子序列[Thorold's Deer].assets\1589761403470.png)


### 定义状态

-  $dp[i]$表示在区间$nums[0....i]$区间范围内的最长上升子序列的长度

![1589760872066](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之最长子序列[Thorold's Deer].assets\2020-05-18_082043.png)

- 比较索引$i$与其前面出现的某个位置$j$的大小
  - 当$nums[i]<=nums[j]$，说明$j$处的值要比$i$处的值的，要是形成子序列则是$nums[0...j,i]$这样的结果，这时候$j$到$i$不能形成递增，**以$i$结尾的子序列所形成的最长子序列的长度等价于以$j$结尾的子序列所形成的最长子序列的长度**，即$dp[i]=dp[j]$
  - 当$nums[i]>nums[j]$，说明$j$处的值小于$i$处值，形成的子序列是$nums[0...j,i]$这样的结果，这时候从$j$到$i$是递增的，这时候需要在长度上+1，即$dp[i]=dp[j+1]$
  - 取上述两种情况的$max$，动态转移方程为： $dp[i] = Math.max(dp[i], dp[j] + 1)|0<=j<i<n$
  - 举例：如果遍历到$i$位置，在$[0-i]$ 区间内有$[0-j] j<i$ 当$nums[i]<=nums[j]$时，表示以$j$结束的子序列和$i$结束的子序列不能形成上升子序列，举 例：$[1,4,5,7,6,8]$，当$i$在$index$为$4$的位置，也就是$nums[i] =6$ ,$j $为$index$ 为$3$时，$nums[j] =7$ ,以$nums[j] $和$nums[i]$ 不能形成一个上升子序列

### 边界条件

- 当$nums[0...i]$只有一个元素时，即以这一个元素结尾的子序列，最长上升子序列是其自身，为$1$

### 核心代码

```java
 for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
```

### 方法1:DP

```java
 public int lengthOfLIS(int[] nums) {
        //dp[i]: 到i为止 (对于所有 j in [0, i], 记录max length of increasing subsequence
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        int res = 0;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //i 位置的数与[0,i]位置之间的数比较，如果大于进逻辑
                if (nums[i] > nums[j]) {
                    //等于dp[i]或者dp[j] + 1（j对应的值比i小）的最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
```





方法2：贪心+二分

- 准备一个辅助数组$tails$,其中$tails[i]$表示长度为$i+1$即$nums[0...i]$的序列尾部元素的值
- 辅助数组$tails$一定是严格单调递增的，即在$nums[0...j..i]$区间上，$tails[j]<tails[i]$，下面使用 反证法证明这个结论
  - 假设$nums[0...j..i]$这个区间上，$tails[j]>=tails[i]$,从$i$这个子序列向前删除$i-j$个元素，这时候长度变为$j$的子序列，这时候的尾部元素的值为$x$
  - 根据$tails$数组的定义，$x<tails[i]$
  - 而$x$又是$tails[j]$的值，即$x=tails[j]$
  - 得出$tails[i]>tails[j]$,这与一开始假设的条件矛盾，假设条件不成立

```java
 public int lengthOfLISII(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int end = 0;
        for (int i = 0; i < n; i++) {
            int l = 0, r = end;
            while (l < r) {
                int m = (l + r) / 2;
                if (tails[m] < nums[i]) l = m + 1;
                else r = m;
            }
            tails[l] = nums[i];
            if (end == r) end++;
        }
        return end;
    }
```

### 关联阅读







