## 二叉树之不同的二叉搜索树[Buffalo]

### 1.不同的二叉搜索树

![image-20200729085901239](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之不同的二叉搜索树[Buffalo].assets\image-20200729085901239.png)



#### 定义状态

定义两个函数：

**$G(n)$:从1到n的连续整数形成的序列，组成不同的二叉搜索树的个数**

**$F[i,n]$,以$i$为$root$节点，从1到n的连续整数形成的序列，组成不同的二叉搜索树的个数 ** 其中1<=i<=n

#### 转移方程



![表格](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之不同的二叉搜索树[Buffalo].assets\表格.jpg)

$G(n)$可以选其中的某一个$i$作为根节点的所有情况的和，即$G(n)$=$sum(F[i,n])$,其中1<=i<=n

因为只需要二叉树的结构相同，元素的排列顺序不用考虑，如上图的红色虚线框中的部分，元素相同，但是因为结构相同，算做一种结构，$F[i,n]$可以通过左右部分各自数量的乘积而得，如下图，可以得到$F[i,n]$=$G(i-1)$*$G(n-i)$,注意$G$函数的定义，再次参见上面的定义

![image-20200729090504425](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之不同的二叉搜索树[Buffalo].assets\image-20200729090504425.png)

有了上面的结论，$G(n)$=$sum(F[i,n])$,其中1<=i<=n就容易求得了

$G(n)$=$F[1,n]$+$F[2,n]$+...+$F[i,n]$+...+$F[n-1,n]$+$F[n,n]$

等价于

$G(n)$=$G(1)$X$G(n-1)$+$G(2)$X$G(n-2)$+...+$G(i)$X$G(n-i)$+...+$G(n-1)$X$G(0)$

#### 边界

$G(0)$:表示一个元素都没有形成的二叉搜索树，只有一种情况，令其为1

$G(1)$:表示只有个元素形成的二叉搜索树，只有一种情况，令其为1

#### 完整代码

```java
  public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
```

#### 复杂度分析

- 时间复杂度：$O(N)$, 一次遍历即可，$N$为整数的大小
- 空间复杂度：$O(N)$, 数组$dp$的大小

#### 总结

很多牛叉的算法大多借助了一些辅助函数，如$KMP$算法中的$next$数组，本题也是如此，定义$G(n)$和$F[i,n]$,让问题很容易得到描述和分解

### 2.不同的二叉搜索树II