## 动态规划之最长重复子数组[White Tiger]

![tiger-3497203_1280](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划之最长重复子数组[White Tiger].assets\tiger-3497203_1280.jpg)

> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉。**



![image-20200701200859403](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划之最长重复子数组.assets\image-20200701200859403.png)

> 此题与求两个字符串的最长公共子串很类似，子串不可跳跃

### 方法1：从前向后DP（模板法DP）

- 题目要求**两个数组中公共的、长度最长的子数组的长度**



![image-20200701202232847](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\动态规划之最长重复子数组.assets\image-20200701202232847.png)

#### 定义状态：

- $dp[i][j]$表示是$A[0...i-1]$与$B[0...j-1]$这两个子数组(*注意：子数组要求连续，类似于字符串的子串*)的**最长公共子数组的长度**
- 这里我们定义$dp[m+1][n+1]$，放长一个字符，然后，从$1$开始遍历
- 当$A[i-1]!=B[j-1]$时，如$A[1,2]$与$B[3,2,1]$ 此时的$A[i-1]!=B[j-1]=>A[1]!=B[2]=>2!=1$,这是以$A[i-1]$与$B[j-1]$结尾的子数组，没公共子数组，因为最末尾的数不相等
- 当$A[i-1]==B[j-1]$ 时，如$A[1,2,3,2]$与$B[3,2]$，此时$A[i-1]==B[j-1]=>A[3]==B[1]=>2==2$,这是以$A[i-1]$与$B[j-1]$结尾的子数组,有公共子数组，这时候$dp[i][j]$只依赖$dp[i-1][j-1]$,这个值以及在前面的推断$dp$表的过程中求解出来了，$dp[i-1][j-1]+1$即是$dp[i][j]$的答案
- 故：
  - $A[i-1]!=B[j-1]$  => $dp[i][j]=0$
  - $A[i-1]==B[j-1]$  =>$dp[i-1][j-1]+1$
- 在此过程中记录下$max$值，即为题目所求的解

#### 边界条件：

- 利用模板法大体分三步（见上图的步骤标识）:
  - 计算$dp$表的第一行
  - 计算$dp$表的第一列
  - 计算$dp$表的一般情况，直到填充完$dp$表，方可结束

- 举例：
  - 对于第一行，$A:[1,2]$与$B:[3]$,这时$dp[1][2]$表示第$1$行，第$2$列 ，因为$A[1]!=B[0]$ 即$2!=3$ ,$dp[1][2]$ = $0$,  这是第一行的一种情况，还有一种情况：$A:[1,2,3]$与$B:[3]$,这时$dp[1][3]$表示第$1$行，第$3$列 ，因为$A[2]==B[0]$ 即$3==3$ ,$dp[1][3]$ = $dp[0][2]$ + $1$ =$1$
  - 对于第一列，$A:[1]$与$B:[3,2]$可以得到$dp[2][1]$ 即$dp$表第$2$行，第$1$列的值，因为 $A[0]!=B[1]$ 即 $1!=2$, $dp[2][1]$=$0$;这是第一列的一种情况，还有一种情况：如果对于$A:[1]$与$B:[3,2,1]$,此时因为$A[0]=B[2]$ 这时$dp[3][1]$,即第$3$行，第$1$列的值 $dp[3][1]$ = $dp[2][0]$+$1$=$1$
  - 对于一般情况：$A:[1,2,3,2]$与$B:[3,2]$,这时$dp[2][4]$表示第$2$行，第$4$列 ，因为$A[3]!=B[1]$ 即$2==2$  , $dp[2][4]$ = $dp[1][3]$+$1$ = $1$+$1$ =$2$

#### 整体代码：

```java
    public int findLength(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;
        int res = 0;
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
```

#### **复杂度分析**:

- 时间复杂度：$O(M*N) $ 
- 空间复杂度： $O(M*N)$  其中$M$和 $N$是$A$ $B$数组的长度

### 方法2：从后向前DP

> 区别于方法1，此方法是从后向前遍历

#### 定义状态：

- $dp[i][j]$表示 $A[i...m-1]$与$B[j...n-1]$这两个子数组的**最长公共子数组的长度**
- 很容易得到$dp[i][j]$依赖$dp[i+1][j+1]$
  - $A[i]!=B[j]$  => $dp[i][j]=0$
  - $A[i]==B[j]$  =>$dp[i+1][j+1]+1$

- 注意$dp[i][j]$初始化为$dp[m+1][n+1]$

#### 整体代码：

```java
    public int findLength(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;
        int res = 0;
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (A[i] == B[j]) dp[i][j] = dp[i + 1][j + 1] + 1;
                else dp[i][j] = 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
```

#### **复杂度分析**:

- 时间复杂度：$O(M*N) $ 
- 空间复杂度： $O(M*N)$  其中$M$和 $N$是$A$ $B$数组的长度

### 方法3：压缩版DP(O(1))

```
[0,0,1,0,0]
[0,0,0,1,0]
[0,0,0,0,1]
[0,0,0,2,0]
[0,0,3,0,0]
```

- 调整成一维数组,$dp[j]$表示的是  **$A[0...i-1]$, $B[0...j-1]$的最长公共子数组的长度**
- 上图中为一维数组的打印过程

#### 完整代码:

```java
    public int findLength3rd(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;
        int res = 0;
        int m = A.length, n = B.length;
        int[] dp = new int[n + 1];
//        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                if (A[i - 1] == B[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }
//                stack.push(dp[j]);
                res = Math.max(res, dp[j]);
            }
//            System.out.println(JSON.toJSONString(stack));
//            stack.clear();
        }
        return res;
    }
```

#### **复杂度分析**:

- 时间复杂度：$O(M*N) $ 
- 空间复杂度： $O(N)$  其中$M$和 $N$是$A$ $B$数组的长度

### 方法4：滑动窗口

- 在其他文章中体现，本文暂跳过





