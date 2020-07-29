## 动态规划解分割数组I[Red Fox]

> 欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。

![nature-3879646_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解分割数组I[Red Fox].assets\nature-3879646_960_720.jpg)

写本题，源自看到的官方的一句描述"**「将数组分割为 m*m* 段，求……」是动态规划题目常见的问法。**",让我想起了之前写的**1043. 分隔数组以得到最大和**，早期写的题解，风格更像一种草稿，但还是有很多扣友鼓励似的阅读点赞，感谢感谢，笔芯~

![image-20200728082139072](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解分割数组I[Red Fox].assets\image-20200728082139072.png)



### 定义状态

$dp[i][j]$表示前$i$个数即$nums[0...i-1]$之间的数被分成$j$段，所组成的$j$个子数组各自和的最大值中的最小值

### 转移方程

![image-20200728085040860](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解分割数组I[Red Fox].assets\image-20200728085040860.png)

思考动态规划的类的问题，有点像道家的**道生一，一生二，二生三，三生万物**，从特殊情况到一般情况，推演归纳

回到本题也是如此，目标是求$dp[i][j]$,再读一遍此定义：**表示前$i$个数即$nums[0...i-1]$之间的数被分成$j$段，所组成的$j$个子数组各自和的最大值中的最小值**，我们能不能求其在这$nums[0...i-1]$中试着用剪刀，减出来两端，分成两部分来考虑？

- 第二段：可以想象成第$j$段，如图上的$nums[k,k+1,....i-1]$,因为我们取的是前$i$个数，数组下标从$0$开始的，这部分的和很显然就是$sum(nums[k,k+1,...i-1])$

- 第一段：可以想象成前$j-1$段，那这一段呢？既然是$j-1$段，其实和$j$段没什么大的区别，可以转化成$dp$，**也就是$dp[k][j-1]$,表示前$k$个数即$nums[0...k-1]$之间的数被分成$j-1$段，所组成的$j-1$个子数组各自和的最大值的最小值**

- 上面两端的最小值便是$dp[i][j]$的结果，即$dp[i][j]$= $max$($dp[k][j-1]$,$sum[k...i-1]$),然后这只是其中随便剪的一刀，也就是$k$有很多种可能，$k$的范围可以从$0$取到$i-1$，超过$i-1$没有意义，如果$k$>$i-1$，表示前$k$个数，但是目标只考虑到前$i$个数

- 最终的转移方程：

  ​		$dp[i][j]$=$min$[$max$($dp[k][j-1],sum[k...i-1]$)] ,其中 0=<$k$<=$i-1$

### 边界

- $dp[0][0]$表示前0个数被分成0段，这在逻辑上是讲不通的，如何出现这种场景呢，也就是整个$nums[0...i-1]$被分成$j=1$段，那么$dp[k][j-1]$就变成了$dp[0][0]$，取其与$sum[k...i-1]$的最大值，只要让$dp[0][0]$=0就不会影响最后的结果
- $j$>$i$,没有意义，因为，前$i$个数字不可能被分成超过$i$段，因为一个数字只能最多被分成一组，每个数组单独一组的话，也就是$i$组，分不出大于$i$组的情况，在变遍历的过程中，$j$的取值应该是$min(m,i)$,$m$是题目中给出的分割数的上限
- 最外层的$min$如果初始值为0,会影响到结果，设置一个$MAX$值，每次的

### 前缀和辅助数组

定义$prefix[i]$表示$nums$数组中前$i$个数的和，即$nums[0...i-1]$之前的和，此前缀和数组初始化$n+1$,$prefix[0]$冗余，最为辅助数组

![image-20200727214902854](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划解分割数组I[Red Fox].assets\image-20200727214902854.png)

以图中的例子举例，打印的$dp$如下

```
[[0,MAX,MAX],
[MAX,7,MAX],
[MAX,9,7],
[MAX,14,7],
[MAX,24,14],
[MAX,32,18]]
```

### 一个小函数

- 填充二维数组，遍历行，对每个列进行填充

```java
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
```

### 完整代码

```java
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = nums[i] + prefix[i];
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(m, i); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], prefix[i] - prefix[k]));
                }
            }
        }
//        System.out.println(JSON.toJSONString(dp));
        return dp[n][m];
    }
```

#### **复杂度分析**:

- 时间复杂度：$O(M*N*N)$  ，其中$N$是数组$nums$的长度，$M$是要分割的段数，三层$for$  $loop$ ， $k$最大到$N$
- 空间复杂度： $O(M*N)$ $dp$的空间





