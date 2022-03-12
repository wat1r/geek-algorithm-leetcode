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

-  只要关注当前位置$i$与其前一个的位置$i-1$的值的大小：
   - $nums[i]＞nums[i-1]$，$i$至少可以与$i-1$形成一个连续递增序列，因为它们俩挨着，并且是递增的，长度上是$dp[i-1]+1$
   - $nums[i]＜=nums[i-1]$,这时候，不能形成连续递增序列，后一个数要比前一个数小，呈下降的趋势，注意$=$不认为是递增的

#### 方法1:DP($O(N)$)

```java
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);//至少可以与其自身形成递增序列
        int max = 1;
        for (int i = 1; i ＜ n; i++) {
            if (nums[i] ＞ nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
```

#### 方法2:DP($O(1)$)

- 因为只会依赖于$i$与$i-1$这两个状态，借助一个常数的级别的一维数组$dp[2]$，辅以奇偶数的小技巧

```java
public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int max = 1;
        int[] dp = new int[2];
        dp[0] = 1;
        for (int i = 1; i ＜ n; i++) {
            dp[i % 2] = 1;//前一个状态值都会被覆盖，需要重新初始化
            if (nums[i] ＞ nums[i - 1]) {
                dp[i % 2] += dp[(i - 1) % 2];//当前状态依赖前一状态，需要再前一状态上累加
            }
            max = Math.max(max, dp[i % 2]);
        }
        return max;
    }
```

### 2.最长递增子序列的个数

![1590505435058](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之最长连续递增序列[Reindeer].assets\1590505435058.png)

>  与300题主体类似，本题的难点是记录下组合的方式

#### 定义状态

- $dp[i]$表示以$i$位置结尾，即$nums[i]$值结尾的，最长连续递增序列的长度

- $dp$初始化为1，因为$nums[i]$自身可以形成一个长度为1的最长递增序列

- 遍历$[0...i]$,再套一层$[0...j]$,其中$j＜i$

  -  当$nums[j]＜nums[i]$,说明以$[...j,i]$这段可以形成最长递增序列，长度是$dp[j]+1$,其中$dp[j]$是以$j$为结尾的最长递增序列的长度
  -  当$nums[j]≥nums[i]$,以$[...j,i]$是不能形成最长递增序列的，为$dp[i]$,其被初始化为1了

- 接下来要统计最长递增序列的个数,准备长度$n$的数组$counter$，**定义$count[i]$为以$nums[i]$结尾的最长递增子序列的组合数量，这其中有两个限定条件，一是以$nums[i]$结尾，二是最长递增子序列，不是最长的不是这个$counter$数组考虑的**，举例，`1,2,4,3,5,4,7,2`，最长递增序列有`1,2,4,5,7;1,2,3,5,7;1,2,3,4,7`三种情况，以$nums[6]=7$结尾的$counter[6]=3$

- 下面是如何生成这个$counter$数组：

  -  总体要满足$nums[i] ＞ nums[j]$,才有意义，这样可以形成递增序列
  -  当$dp[j] + 1＞dp[i]$,说明第一次找到以$nums[i]$为结尾的最长递增子序列，长度为$dp[j] + 1$，进而可以推出$counter[i] = counter[j]$, 以$nums[i]$结尾的最长递增子序列的组合数=以$nums[j]$结尾的最长递增子序列的组合数,这个可以这么理解：当$[...j]$形成的组合数是值的话，其每一种组合结尾补上$[i]$,即$[...j,i]$,对于组合数本身是没有增加的，还是$A$值，唯独只是递增子序列的长度$+1$了
  -  当$dp[j] + 1=dp[i]$,说明这个长度已经找到过一次了，$counter[i] += counter[j]$，现在的组合方式+$counter[j]$的组合方式

  ![673_leetcode](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之最长连续递增序列[Reindeer].assets\673_leetcode-1590626196059.jpg)

#### 整体代码

```java
 public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        int[] counter = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(counter, 1);
        int max = 0;
        for (int i = 0; i ＜ n; i++) {
            for (int j = 0; j ＜ i; j++) {
                if (nums[i] ＞ nums[j]) {
                    if (dp[j] + 1 ＞ dp[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        counter[i] = counter[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        counter[i] += counter[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        int res = 0;
        for (int i = 0; i ＜ n; i++) {
            if (dp[i] == max) res += counter[i];
        }
        return res;
    }
```

#### 复杂度分析

- **时间复杂度**：$O(N^2)$，两个$for loop$
- 空间复杂度：$O(N)$，$dp$与$counter$数组长度$N$

#### 推荐阅读