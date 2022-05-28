



### 题目

[1856. 子数组最小乘积的最大值](https://leetcode.cn/problems/maximum-subarray-min-product/)

```java
1856. 子数组最小乘积的最大值
一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。

比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。

请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。

子数组 定义为一个数组的 连续 部分。

 

示例 1：

输入：nums = [1,2,3,2]
输出：14
解释：最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
2 * (2+3+2) = 2 * 7 = 14 。
示例 2：

输入：nums = [2,3,3,1,2]
输出：18
解释：最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
3 * (3+3) = 3 * 6 = 18 。
示例 3：

输入：nums = [3,1,5,6,4,2]
输出：60
解释：最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
4 * (5+6+4) = 4 * 15 = 60 。
 

提示：

1 <= nums.length <= 105
1 <= nums[i] <= 107
```



## 解法

### 方法1

```java
        //AC
        public int maxSumMinProduct(int[] nums) {
            int MOD = (int) 1e9 + 7;
            int n = nums.length;
            long[] s = new long[n + 1];
            for (int i = 0; i < n; i++) {
                s[i + 1] = s[i] + nums[i];
            }
            int[] left = new int[n], right = new int[n];
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!stk.isEmpty() && nums[stk.peek()] > nums[i]) {
                    stk.pop();
                }
                left[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(i);
            }
            stk.clear();
            for (int i = n - 1; i >= 0; --i) {
                while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                    stk.pop();
                }
                right[i] = stk.isEmpty() ? n : stk.peek();
                stk.push(i);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                int l = left[i], r = right[i];
                res = Math.max(res, 1L * (s[r] - s[l + 1]) * nums[i]);
            }
            return (int) (res % MOD);
        }
```



- 另，设置哨兵

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220528194208.png)

```java
public int maxSumMinProduct(int[] src) {
    int MOD = (int) 1e9 + 7;
    //设置前后两个哨兵 0 0 
    int[] nums = new int[src.length + 2];
    for (int i = 0; i < src.length; i++) {
        nums[i + 1] = src[i];
    }
    int n = nums.length;
    long[] pre = new long[n + 1];
    for (int i = 0; i < n; i++) {
        pre[i + 1] = pre[i] + nums[i];
    }
    Deque<Integer> stk = new ArrayDeque<>();
    //右边第一个比它小的元素的下标
    int[] right = new int[n];
    for (int i = 0; i < n; i++) {
        while (!stk.isEmpty() && nums[i] < nums[stk.peek()]) {
            right[stk.pop()] = i;
        }
        stk.push(i);
    }
    stk.clear();
    //左边第一个比它小的元素的下标
    int[] left = new int[n];
    for (int i = n - 1; i >= 0; --i) {
        while (!stk.isEmpty() && nums[i] < nums[stk.peek()]) {
            left[stk.pop()] = i;
        }
        stk.push(i);
    }
    long res = 0;
    for (int i = 0; i < n; i++) {
        int l = left[i], r = right[i];
        res = Math.max(res, nums[i] * (pre[r] - pre[l + 1]));
    }
    return (int) (res % MOD);
}
```

### 方法2：一次单调栈操作

```java
        public int maxSumMinProduct(int[] nums) {
            int MOD = (int) 1e9 + 7;
            int n = nums.length;
            // left 是严格定义的，left[i] 是左侧最近的严格小于 nums[i] 的元素下标
            // right 是非严格定义的，right[i] 是右侧最近的小于等于 nums[i] 的元素下标
            int[] left = new int[n], right = new int[n];
            Arrays.fill(right, n - 1);
            Deque<Integer> stk = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                    right[stk.pop()] = i - 1;
                }
                if (!stk.isEmpty()) {
                    left[i] = stk.peek() + 1;
                }
                stk.push(i);
            }
            long[] pre = new long[n + 1];
            for (int i = 0; i < n; i++) {
                pre[i + 1] = pre[i] + nums[i];
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, 1L * (pre[right[i] + 1] - pre[left[i]]) * nums[i]);
            }
            return (int) (res % MOD);
        }
```





