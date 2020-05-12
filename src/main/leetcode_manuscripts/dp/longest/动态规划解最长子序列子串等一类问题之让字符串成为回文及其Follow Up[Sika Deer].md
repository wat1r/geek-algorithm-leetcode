

## 动态规划解最长子序列子串等一类问题之让字符串成为回文及其Follow Up[Sika Deer]







### 0.源起

![1589122737432](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之Sika Deer.assets\1589122737432.png)

### 1.Step1

> 源问题，如上图，让字符串成为回文串的最少插入次数

#### 定义状态

- $n$为$str$的长度，$dp[n][n]$是一张二维表
- 定义:$dp[i][j]$表示的是子串$str[i][j]$最少添加多少字符可以使其整体变成一个回文，注意，是**最少**
- 要求的结果是$dp[0][n-1]$表示的是$str[0...n-1]$也就是这个字符从开头到结尾，要想形成回文，最少的添加字符的个数

> 问题来了，如何填写这张dp table呢？

- 分三大类讨论

- 1.$str[i...j]$只有一个字符，即$i==j$，如图，$str[i...j]=A$，一个字符要形成回文，很简单，不要向其增加字符，即其本身可以形成回文,即$dp[i][j]=0$
- 2.$str[i...j]$有两个字符
  - 2.1，当$str[i]==str[j]$，即两个字符相同时，其与场景1的情况一样，例如，$AA$不需要添加字符，即可使其成为回文，即$dp[i][j]=0$
  - 2.2，当$str[i]!=str[j]$,即两个字符不同时，例如，$AB$，可以在前面加一个$B$，变成$BAB$，也可以在后面加一个$A$，变成$ABA$，总之是只要添加一个字符，即可使其变成回文，即$dp[i][j]=1$

- 3.$str[i...j]$有三个或者三个以上字符，这就属于一般的情况了
  - 3.1.当$str[i]==str[j]$时，例如，$ABDCA$，只需要考虑$BDC$，也即要求$dp[i][j]$,转化为求$dp[i+1][j-1]$，只要搞定$str[i+1...j-1]$部分，使其变成回文，再加上$str[i]$与$str[j]$，整体就变成回文了，即转移方程：$dp[i][j]=dp[i+1][j-1]$
  - 3.2.当$str[i]!=str[j]$时，有两种方法使其变成回文，一种是先让$str[i...j-1]$变成回文，在$i$的左边添加$str[j]$，整体就会变成回文，例如$ABECD$，可以先考虑$ABEC$这部分，一个方案是$ABECEBA$，再在$ABECEBA$左边加上$str[j]$,即$D$,带上源字符的$D$,变成了$DABECEBAD$；另外一种方案，同理，先让$str[i+1...j]$变成回文，最后在右边加上$str[i]$，最终变成了整体回文，那么，动态转移方程式什么呢？
    - 要想使$str[i...j-1]$变成回文，其最少的添加次数是$dp[i][j-1]$，再加上往左侧补的字符，最终应该是$dp[i][j-1]+1$
    - 同理可得，$dp[i+1][j]+1$
    - $dp[i][j]=min(dp[i][j-1],dp[i+1][j])+1$

![Package Diagram](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\longest\动态规划解最长子序列子串等一类问题之让字符串成为回文及其Follow Up[Sika Deer].assets\Package Diagram.jpg)

#### 核心代码

- 生成$dp$ $table$的代码，注意遍历时，综合考虑了以上分析的三种情况
- 下文中需要使用到这个$buildDP$,做个一个抽离

```java
public int[][] buildDP(char[] chas) {
        // $dp[i][j]$表示子串$str[i...j]$范围内的最少添加多少个字符后，可以形成回文子串
        int n = chas.length;
        int[][] dp = new int[n][n];
        for (int j = 1; j < n; j++) {
            dp[j - 1][j] = (chas[j - 1] == chas[j]) ? 0 : 1;
            for (int i = j - 2; i >= 0; i--) {
                if (chas[i] == chas[j]) dp[i][j] = dp[i + 1][j - 1];
                else dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
            }
        }
        return dp;
    }
```

#### 整体代码

```java
    public int minInsertions(String s) {
        int[][] dp = buildDP(s.toCharArray());
        return dp[0][s.length() - 1];
    }
```

#### 复杂度分析

- 时间复杂度：$O(N^2)$，$N$是字符的长度

- 空间复杂度：$O(N^2)$，$dp$表的空间

### 2.Step2

#### 题目

> 给定一个字符串str，如果可以在str的任意位置添加字符，请返回在添加字符最少的情况下，让str整体都是回文字符串的结果。 

- 这个问题比$Step1$的问题多了一步，要求返回一种使其变成整体回文的结果，例如，源字符$AB$,返回一种添加最少字符，使其变成回文的一种方案，返回$ABA$或者$BAB$均可

#### 解题思路

- 准备一个$res$数组，长度是字符$s$本身的长度+$dp[0][n-1]$
- 运用双指针的想法，给$res$填充字符
  - 当$str[i]==str[j]$时，$res$直接追加即可
  - 当$str[i]!=str[j]$时，需要找到一个最少添加字符的方案
    - 当$dp[i][j - 1] < dp[i + 1][j]$时，即最终在生成字符的时候，在左侧填充一个$str[j]$
    - 当$dp[i][j - 1] >= dp[i + 1][j]$时，即最终在生成字符的时候，在右侧填充一个$str[i]$

#### 整体代码

```java
public String getPalindromeI(String s) {
        char[] chas = s.toCharArray();
        int n = chas.length;
        int[][] dp = buildDP(chas);
        int appendLen = dp[0][n - 1];
        char[] res = new char[n + appendLen];
        int i = 0, j = n - 1;
        int left = 0, right = res.length - 1;
        while (i <= j) {
            if (chas[i] == chas[j]) {
                res[left++] = chas[i++];
                res[right--] = chas[j--];
            } else {
                if (dp[i][j - 1] < dp[i + 1][j]) {
                    res[left++] = chas[j];
                    res[right--] = chas[j--];
                } else if (dp[i][j - 1] >= dp[i + 1][j]) {
                    res[left++] = chas[i];
                    res[right--] = chas[i++];
                }
            }
        }
        return String.valueOf(res);
    }
```

#### 复杂度分析

- 时间复杂度：$O(N^2)$，$N$是字符的长度，求$res$的过程是$O(N)$,总体是$O(N^2)$

- 空间复杂度：$O(N^2)$，$dp$表的空间

### 3.Step3

> 给定一个字符串str，再给定str的最长回文子序列字符串strlps，请返回在添加字符最少的情况下，让str整体都是回文字符串的一种结果。进阶问题比原问题多了一个参数，请做到时间复杂度比原问题的实现低
>
> **举例：**
>
> str="A1B21C"，strlps="121"，返回"AC1B2B1CA"或者"CA1B2B1AC"，总之，只要是添加的字符数最少，只返回其中一种结果即可

#### 题目分析

- 这个问题是给出了一个$seed$字符，生成最少添加字符形成回文后的其中一种结果

#### 解题思路

> 运用剥洋葱的方式去解决

- $str$的长度为$n$，$strlps$的长度为$m$,形成回文$res$的结果是$2*n-m$ ，因为$m$部分已经是回文了，不需要额外添加
- 以$str$=$A1BC22DE1F$,$strlps$=$1221$为例
- 洋葱的第0层是由$strlps[0]$与$strlps[m-1]$，组成，即$1...1$，从$str$的最左侧开始找，一直找到$strlps[0]=1$的字符为止，记为$leftPart$，为$A$,接着从$str$的最右侧开始找，一直找到$strlps[m-1]=1$的字符为止，记为$rightPart$，为$F$，
  - 将$leftPart+rightPart的逆序$，复制到$res$的左侧
  - 将$rightPart+leftPart的逆序$，复制到$res$的右侧
  - 结果变成了$AF......FA$
  - 在加上$strlps$,变成了$AF1......1FA$
- 洋葱进入第1层，即$2...2$,接着第一层的位置找$str$,$leftPart$为$BC$，$rightPart$为$DE$，
  - 将$leftPart+rightPart的逆序$，复制到$res$的左侧
  - 将$rightPart+leftPart的逆序$，复制到$res$的右侧
  - 结果变成了$BCED......DECB$
  - 在加上$strlps$,变成了$BCED2......2DECB$
  - 加上上一层，变成了$AF1BCED22DECB1FA$
- 洋葱进入第n层。。。
- 代码部分注释掉了一部分，将$resLeft = 0, resRight = 0;$定义为全局变量，好理解些
- 整体实现逻辑是双指针，游走，不过左右指针有很多对，嗯，只能是，细节是魔鬼啊

#### 整体代码

```java
//res array 的左右指针，定义为全局变量
    int resLeft = 0, resRight = 0;

    public String getPalindromeII(String str, String strlps) {

        char[] chas = str.toCharArray();//str 目标字符串
        char[] lps = strlps.toCharArray();//base 字符串
        int n = chas.length, m = lps.length;//len
        char[] res = new char[2 * n - m];//res字符串 arr
        int chasLeft = 0, chasRight = n - 1;//str 的左右指针
        int lpsLeft = 0, lpsRight = m - 1;//base 的左右指针
        resRight = res.length - 1;
        int tmpLeft = 0, tmpRight = 0;//tmp 左右指针
        while (lpsLeft <= lpsRight) {//循环遍历lps字符
            tmpLeft = chasLeft;
            tmpRight = chasRight;
            while (chas[chasLeft] != lps[lpsLeft]) chasLeft++;//如果lpsLeft在chas未出现,一直chasLeft++
            while (chas[chasRight] != lps[lpsRight]) chasRight--;//如果lpsRight在chas未出现,一直chasRight--
            build(res, chas, chasLeft, chasRight, tmpLeft, tmpRight);//组装
//            int len = chasLeft - tmpLeft + tmpRight - chasRight;
//            resLeft += len;
//            resRight -= len;
            res[resLeft++] = chas[chasLeft++];//添加当前的lpsLeft ==lpsRight对于的字符串
            res[resRight--] = chas[chasRight--];//添加当前的lpsLeft ==lpsRight对于的字符串
            lpsLeft++;//进入下一轮
            lpsRight--;
        }
        return String.valueOf(res);
    }

    /**
     * @param res       结果arr
     *                  //     * @param resLeft
     *                  //     * @param resRight
     * @param chas      str arr
     * @param chasLeft  str arr left pointer
     * @param chasRight str arr right pointer
     * @param tmpLeft   tmp left pointer
     * @param tmpRight  tmp right pointer
     */
    private void build(char[] res,
                       char[] chas, int chasLeft, int chasRight,
                       int tmpLeft, int tmpRight) {

        for (int i = tmpLeft; i < chasLeft; i++) {
            res[resLeft++] = chas[i];
            res[resRight--] = chas[i];
        }
        for (int j = tmpRight; j > chasRight; j--) {
            res[resLeft++] = chas[j];
            res[resRight--] = chas[j];
        }
    }
```

### Reference

-  程序员代码面试指南 

> 本文完

### 关联阅读



