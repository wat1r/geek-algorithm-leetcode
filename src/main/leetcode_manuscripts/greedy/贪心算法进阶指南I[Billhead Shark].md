## 贪心算法进阶指南I[Billhead Shark]

![island-1956217_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\greedy\贪心算法进阶指南I[dd].assets\island-1956217_640.jpg)

































### 1.判断子序列

![image-20200908081226082](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\greedy\贪心算法进阶指南I[dd].assets\image-20200908081226082.png)

#### 思路

- 准备两个指针，`i`指针负责游走`t`字符串，`j`指针游走`s`字符串
- 当两个指针指向的`char`相等时，两个指针都`++`,否则只是`i`指针`++`
- 判断`j`指针是否到达`s`字符串的末尾

```java
    public boolean isSubsequence(String s, String t) {
        int j = 0;
        for (int i = 0; i < t.length() && j < s.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
            }
        }
        return j == s.length();
    }
```



### 2.摆动序列

![image-20200908084112880](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\greedy\贪心算法进阶指南I[Billhead Shark].assets\image-20200908084112880.png)

> 图来自官方

![image-20200908092757287](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\greedy\贪心算法进阶指南I[Billhead Shark].assets\image-20200908092757287.png)



**结论1：最长摆动序列的各节点之间不存在峰或者谷。 **

> 反证法：假设结论1不成立，那么通过在包含峰或者谷的两个节点之间添加此峰或谷节点，就能让该摆动序列的长度加1，与题设矛盾。

**结论2：最长摆动序列必可以以第一个节点开始，可以以最后一个节点结束。**

> 证明：若存在某个以第二个数为开始的摆动序列，这个摆动序列里的第二个数比第一个数大，而原始数组的第一个数比第二个数（也就是摆动序列的第一个数）大，则以原始数组的第一个数作为开始能使摆动序列长度+1；若摆动序列里的第二个数比第一个数大，而原始数组的第一个数比第二个数小，则选取原始数组的第一个数或者第二个数作为摆动序列的第一个数是等价的，不减少摆动序列长度。对于摆动序列中第二个数比第一个数小的情况对称可证

#### 思路

- 找到三个数中的局部最大，局部最小的趋势

#### 方法1

```java
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int prevDiff = nums[1] - nums[0];
        int res = prevDiff == 0 ? 1 : 2;
        for (int i = 2; i < nums.length; i++) {
            int nextDiff = nums[i] - nums[i - 1];
            if ((prevDiff <= 0 && nextDiff > 0) || (prevDiff >= 0 && nextDiff < 0)) {
                res++;
                prevDiff = nextDiff;
            }
        }
        return res;
    }
```

#### **复杂度分析**:

- 时间复杂度：$O(N)$,一遍扫描
- 空间复杂度： $O(1)$,没有使用到数组

#### 方法2

> 效率感人

- 先去重复数据，然后取相邻的三个数进行比较

```java
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (i < nums.length) list.add(nums[i]);//
        }
        int res = 2;
        Integer[] arr = new Integer[list.size()];
        list.toArray(arr);
        if (arr.length <= 2) return arr.length;
        for (int i = 1; i + 1 < arr.length; i++) {
            int prev = arr[i - 1], curr = arr[i], next = arr[i + 1];
            if ((prev < curr && curr > next) || (prev > curr && curr < next)) {
                res++;
            }
        }
        return res;
    }
```





































