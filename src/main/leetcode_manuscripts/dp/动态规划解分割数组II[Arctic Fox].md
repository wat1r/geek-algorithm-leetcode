## 动态规划解分割数组II[Arctic Fox]

> 欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。

![watercolor-3734881_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解分割数组II[Arctic Fox].assets\watercolor-3734881_960_720.jpg)

![image-20200728190440746](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解分割数组II[Arctic Fox].assets\image-20200728190440746.png)

![image-20200728211412151](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解分割数组II[Arctic Fox].assets\image-20200728211412151.png)



### 定义状态

**$dp[i]$：数组的前$i$个数即$nums[0,1...i-1]$,被切了$K-1$刀，分割成$K$个数组，每个数组的值变成最大值，分割后的最大和**，如上图，当被分成$K=3$个部分时，第一部分的最大值为$15$，第二部分为$9$，第三部分$10$，每一部分的每个值都上升为当前部分的局部$max$，红色字体为新的值，累加后，求其最大值

### 转移方程

![image-20200728211805764](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解分割数组II[Arctic Fox].assets\image-20200728211805764.png)

要想求$dp[i]$,这是**数组的前$i$个数即$nums[0,1...i-1]$,被切了$K-1WWW$刀，分割成$K$个数组，每个数组的值变成最大值，分割后的最大和**

- 求$dp[i-1]$,表示数组的前$i$个数即$nums[0,1...i-2]$，第二部分是$nums[i-1]$,也就是说 $dp[i-1]$+$max(nums[i-1])$*$(i-(i-1))$
- 求$dp[i-2]$,表示数组的前$i-1$个数即$nums[0,1...i-3]$，第二部分是$nums[i-2...i-1]$,也就是说 $dp[i-2]$+$max(nums[i-2...i-1])$*$(i-(i-2))$
- 求$dp[i-3]$,表示数组的前$i-2$个数即$nums[0,1...i-4]$，第二部分是$nums[i-3...i-1]$,也就是说 $dp[i-3]$+$max(nums[i-3...i-1])$*$(i-(i-3))$
- ...
- 求$dp[0]$,表示数组的前$1$个数即$nums[0,0]$，第二部分是$nums[0...i-1]$,也就是说 $dp[0]$+$max(nums[0...i-1])$*$(i-(0))$

**求上面的的最大值**

可以推导出$dp[i]$=$max$($dp[i]$,$dp[j]+(i-j)*MAX$),其中$MAX$是$nums[j...i-1]$范围内的局部最大值，一旦找到最大值，该范围内的所有值都改成这个局部最大值$MAX$，其中0=<$j$<$i$

### 初始化边界

$i-j$如果大于$K$前面的部分已经被分割成了$K$部分了，后面$nums[i...n]$这部分将没有办法被涵盖进来，一个条件:$i-j$<=$K$

$j$>=0，这个没啥好说的

$dp$初始化的时候容量为$n+1$，要求的$dp[n]$表示**数组的前$n$个数即$nums[0,1...n]$,被切了$K-1$刀，分割成$K$个数组，每个数组的值变成最大值，分割后的最大和**

### 编码技巧

- $i$从做到有遍历，$j$起始为$i-1$从右往左遍历
- 注意记录局部的最大值$MAX$

### 完整代码

```java
    public int maxSumAfterPartitioning(int[] A, int K) {
        int n = A.length;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int j = i - 1;
            int max = dp[i];
            while ((i - j) <= K && j >= 0) {
                max = Math.max(max, A[j]);
                dp[i] = Math.max(dp[i], dp[j] + (i - j) * max);
                j--;
            }
        }
        return dp[n];
    }
```

### 总结

- 动态规划的题目，需要想到一些基本的情况，可以像数学归纳法一样思考





