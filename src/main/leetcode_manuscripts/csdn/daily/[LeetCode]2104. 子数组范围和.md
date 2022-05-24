

## [LeetCode]2104. 子数组范围和



## 题目

[2104. 子数组范围和](https://leetcode.cn/problems/sum-of-subarray-ranges/)

```java
2104. 子数组范围和
给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。

返回 nums 中 所有 子数组范围的 和 。

子数组是数组中一个连续 非空 的元素序列。

 

示例 1：

输入：nums = [1,2,3]
输出：4
解释：nums 的 6 个子数组如下所示：
[1]，范围 = 最大 - 最小 = 1 - 1 = 0 
[2]，范围 = 2 - 2 = 0
[3]，范围 = 3 - 3 = 0
[1,2]，范围 = 2 - 1 = 1
[2,3]，范围 = 3 - 2 = 1
[1,2,3]，范围 = 3 - 1 = 2
所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
示例 2：

输入：nums = [1,3,3]
输出：4
解释：nums 的 6 个子数组如下所示：
[1]，范围 = 最大 - 最小 = 1 - 1 = 0
[3]，范围 = 3 - 3 = 0
[3]，范围 = 3 - 3 = 0
[1,3]，范围 = 3 - 1 = 2
[3,3]，范围 = 3 - 3 = 0
[1,3,3]，范围 = 3 - 1 = 2
所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
示例 3：

输入：nums = [4,-2,-3,4,1]
输出：59
解释：nums 中所有子数组范围的和是 59
 

提示：

1 <= nums.length <= 1000
-109 <= nums[i] <= 109
 

进阶：你可以设计一种时间复杂度为 O(n) 的解决方案吗？
```





## 解法

### 方法1：单调栈计算贡献值

- 思路来着leetcode高赞题解

```java
public long subArrayRanges(int[] nums) {
    int n = nums.length;
    //计算nums[i]在 nums中 作为最大值和最小值 所出现的最大区间大小
    //换言之，需要找到nums[i] 作为最大值，找到左边第一个比nums[i]小的索引j 找到右边第一个比nums[i]小的索引k 区间范围为[k-j]
    //同理，需要找到nums[i] 作为最小值，找到左边第一个比nums[i]大的索引j 找到右边第一个比nums[i]大的索引k 区间范围为[k-j]
    //这里因为做了两遍的比较，需要特别注意 在 严格相等的时候，只能取一边，另外一边如果重复取，则会重复
    long[] maxx = getCnt(nums, false);
    long[] minn = getCnt(nums, true);
    long res = 0;
    //计算当前元素作为最大值和最小值时，出现的次数，计算出「贡献值」
    for (int i = 0; i < n; i++) {
        res += nums[i] * (maxx[i] - minn[i]);
    }
    return res;
}

public long[] getCnt(int[] nums, boolean flag) {
    int n = nums.length;
    int[] left = new int[n], right = new int[n];
    Deque<Integer> stk = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
        //为true时维持单调递增栈，遇到非严格递减趋势时，弹出栈顶元素「下标」
        //这时候找的是左边比小于等于nums[i]的索引，如果左边没有符合条件的下标则设置为-1，即索引0往左的一个位置
        while (!stk.isEmpty() && (flag ? nums[stk.peek()] >= nums[i] : nums[stk.peek()] <= nums[i])) {
            stk.pop();
        }
        left[i] = stk.isEmpty() ? -1 : stk.peek();
        stk.push(i);
    }
    stk.clear();
    //为true时维持单调递增栈，遇到非严格递减趋势时，弹出栈顶元素「下标」
    //这时候找的是右边比小于(此处没有等于)nums[i]的索引，如果左边没有符合条件的下标则设置为n，即索引「len(nums)」往右的一个位置
    for (int i = n - 1; i >= 0; i--) {
        while (!stk.isEmpty() && (flag ? nums[stk.peek()] > nums[i] : nums[stk.peek()] < nums[i])) {
            stk.pop();
        }
        right[i] = stk.isEmpty() ? n : stk.peek();
        stk.push(i);
    }
    long[] res = new long[n];
    //左侧有 (i - left[i]) 和选择，右侧有(right[i] - i)个选择
    //根据乘法原理,(i - left[i])*(right[i] - i)
    for (int i = 0; i < n; i++) {
        res[i] = 1L * (i - left[i]) * (right[i] - i);
    }
    return res;
}
```





