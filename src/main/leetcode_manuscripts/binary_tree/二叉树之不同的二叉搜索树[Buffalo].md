## 二叉树之不同的二叉搜索树[Buffalo]

> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。**

![buffalo-1235227_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之不同的二叉搜索树[Buffalo].assets\buffalo-1235227_960_720.jpg)

### 1.不同的二叉搜索树

>  二叉搜索树

二叉搜索树是一种节点值之间具有一定数量级次序的二叉树，对于树中每个节点：

- 若其左子树存在，则其左子树中每个节点的值都不大于该节点值；
- 若其右子树存在，则其右子树中每个节点的值都不小于该节点值。

![image-20200729085901239](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之不同的二叉搜索树[Buffalo].assets\image-20200729085901239.png)



#### 方法1：暴力递归

```java
        public int numTrees(int n) {
            return dfs(n);
        }
        private int dfs(int n) {
            if (n == 0 || n == 1) return 1;
            int cnt = 0;
            for (int i = 0; i <= n - 1; i++) {
                cnt += dfs(i) * dfs(n - 1 - i);
            }
            return cnt;
        }
```



#### 方法2：记忆化搜索

```java
Map<Integer, Integer> cache = new HashMap<>();

public int numTrees(int n) {
    return dfs(n);
}


private int dfs(int n) {
    if (cache.containsKey(n)) return cache.get(n);
    if (n == 0 || n == 1) return 1;
    int cnt = 0;
    for (int i = 0; i <= n - 1; i++) {
        cnt += dfs(i) * dfs(n - 1 - i);
    }
    cache.put(n,cnt);
    return cnt;
}
```

#### 定义状态

定义两个函数：

**$G(n)$:从1到n的连续整数形成的序列，组成不同的二叉搜索树的个数**

**$F[i,n]$,以$i$为$root$节点，从1到n的连续整数形成的序列，组成不同的二叉搜索树的个数 ** 其中1<=i<=n

#### 转移方程

![表格 (1)](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之不同的二叉搜索树[Buffalo].assets\表格 (1).jpg)



$G(n)$可以选其中的某一个$i$作为根节点的所有情况的和，即$G(n)$=$sum(F[i,n])$,其中1<=i<=n

$F[i,n]$可以通过左右部分各自数量的乘积而得，如下图，可以得到$F[i,n]$=$G(i-1)$*$G(n-i)$,注意$G$函数的定义，再次参见上面的定义

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

![image-20200729204913645](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\binary_tree\二叉树之不同的二叉搜索树[Buffalo].assets\image-20200729204913645.png)

#### 定义函数

$helper(start,end)$ 表示从区间$[start...end]$之间形成的二叉搜索树的列表

每次从$[start...end]$选择一个数，为$curr$节点，作为根节点，并递归生成左右两部分

- 左半部分，从$[start...i-1]$,生成所有可能的二叉搜索树列表
- 右半部分，从$[i+1...end]$生成所有可能的二叉搜索树列表
- 将左右部分组合到根节点的左右两侧并将结果收集到$result$列表，组合数可以参见上面一题的结果，就是$size(leftPart)$X$size(rightPart)$

#### 出口

当$start$>$end$s时，说明遇到了$[]$或者一个节点的的情况，添加$null$，后续追加到其所在的根节点

```java
   public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return helper(1, n);

    }
    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftPart = helper(start, i - 1);
            List<TreeNode> rightPart = helper(i + 1, end);
            for (TreeNode left : leftPart) {
                for (TreeNode right : rightPart) {
                    TreeNode curr = new TreeNode(i);
                    curr.left = left;
                    curr.right = right;
                    result.add(curr);
                }
            }
        }
//        System.out.println(JSON.toJSONString(result));
        return result;
    }
```

- 动态规划做法$TODO$

  

