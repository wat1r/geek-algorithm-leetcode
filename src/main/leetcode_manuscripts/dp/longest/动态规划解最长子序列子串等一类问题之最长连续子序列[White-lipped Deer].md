## 动态规划解最长子序列子串等一类问题之最长连续子序列[White-lipped Deer]



### 1.最长连续序列



![1591142573977](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之最长连续子序列[White-lipped Deer].assets\1591142573977.png)

#### 方法1:Sort+Compare

- 
- 先排序，题意要求连续序列，即可以比较$nums[i]$ 与 $nums[i - 1]$，如果不相等，表示是递增的趋势，相等则反之，递增后需要判断是否连续，即相邻的元素差值是否为$1$

- 下面的代码处理边界$case$ 如$[-1,0]$,不会比较$max$与$cur$的值，需要在最后一道防线拦截一次

```java
 Math.max(max,cur);
```

#### 完整代码

```java
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int n = nums.length;
        int max = 1, cur = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i - 1] + 1 == nums[i]) cur++;
                else {
                    max = Math.max(max, cur);
                    cur = 1;
                }
            }
        }
        return Math.max(max, cur);
    }
```

#### 复杂度分析

- 时间复杂度：$O(Nlog(N))$，$N$是数组的长度，排序的复杂度
- 空间复杂度：$O(1)$，常量级别的空间

#### 方法2:Hash

- 准备一个$set$,首先将所有的$num$装进$set$
- $for loop$ 数组，如果当前遍历到的元素$num-1$不在$set$中，说明这是一段新的可能出现的递增序列，变量$curNum$置为$num$，$while$循环判断$curNum+1$是否在$set$中，是则表示是连续的
- 记录$max$值

```java
	public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int max = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {//判断set不包含当前元素-1的值，跳过已经计算的最长递增序列
                int curNum = num;
                int curCnt = 1;
                while (set.contains(curNum + 1)) {
                    curNum += 1;
                    curCnt += 1;
                }
                max = Math.max(max,curCnt);
            }
        }
        return max;
    }
```



#### 复杂度分析

- 时间复杂度：$O(N)$,尽管在 $for$ 循环中嵌套了一个 $while$ 循环，时间复杂度看起来像是二次方级别的。但其实它是线性的算法。因为只有当 $curNum$ 遇到了一个序列的开始，$ while$ 循环才会被执行（也就是$ curNum-1$ 不在数组 $nums$ 里）， $while$ 循环在整个运行过程中只会被迭代$N$次。这意味着尽管看起来时间复杂度为 $O(N^2)$ ， 实际这个嵌套循环只会运行 $O(N+N)=O(N)$ 次。所有的计算都是线性时间的，所以总的时间复杂度是$O(N)$的 
- 空间复杂度：$O(1)$，常量级别的空间

### 2. 最长上升连续子序列 



![1591142393101](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之最长连续子序列[White-lipped Deer].assets\1591142393101.png)

>  使用 `O(n)` 时间和` O(1)` 额外空间来解决 

- $O(1)$的空间复杂度来处理，会比较麻烦，需要压缩空间，题意中的最长上升连续子序列，定义为**从左到右和从右到左**均可以
- 准备两个数组，大小都为$2$：
  - $start$记录从左到右的连续递增子序列的最长长度
  - $end$记录从右到做的连续递增子序列的最长长度

```java
  public int longestIncreasingContinuousSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        //从左到右,start
        int[] start = new int[2];
        Arrays.fill(start, 1);
        int maxStart = 1;
        for (int i = 1; i < n; i++) {
            start[i % 2] = 1;
            if (nums[i] > nums[i - 1]) {
                start[i % 2] += start[(i - 1) % 2];
            }
            maxStart = Math.max(maxStart, start[i % 2]);
        }
        //从右到做,end
        int[] end = new int[2];
        Arrays.fill(end, 1);
        int maxEnd = 1;
        for (int i = n - 2; i >= 0; i--) {
            end[i % 2] = 1;
            if (nums[i] > nums[i + 1]) {
                end[i % 2] += end[(i + 1) % 2];
            }
            maxEnd = Math.max(maxEnd, end[i % 2]);
        }

        return Math.max(maxStart, maxEnd);
    }
```

### 推荐阅读

