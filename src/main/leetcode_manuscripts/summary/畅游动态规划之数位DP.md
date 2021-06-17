

### 前言

#### 一 使用

求满足一个性质的所有数时使用。

#### 二 思路

把整个数拆成每一位，从最高位向最低位看。
设当前这一位为xx，则当前位分成[0,x)[0,x)和xx两种情况

PS：为什么这里分成这一位为 x 和 <x 两种情况？
因为 <x 时后面可以任取，通常可以通过DP直接求出，这类情况就全部解决了。
为 x 时，后面的数有范围限制，不能任取。
第一种情况直接处理，剩下的就是当前位为xx的情况，则继续看后面的数位，重复这个操作。
直到最后一位时，剩下的就是原数，判断是否符合条件即可。

中间由于性质可能需要记录last，一般表示为之前的最高位的某些性质。

#### 三 模板

```c++
void init() // 根据题意做预处理。
{
    for(int i = 0; i <= 9; i ++) // 对第一位初始化
        f[1][i] = 1;

    // DP过程
}

int dp(int n)
{
    if(!n) return 1;
    vector<int> num;
    // 取出每一位数字，可以根据进制转化问题替换 10
    while(n) num.push_back(n % 10), n /= 10; 
    n = num.size();

    LL ans = 0;
    int last = 0;
    for(int i = n - 1; i >= 0; i --)
    {
        int x = num[i];

        // 分类讨论
    }

    return ans;
}
```
预处理通常为DP或组合数。
在分类讨论计算答案时，和预处理的DP过程类似。

#### 四 trick（技巧）

再求区间[a,b]中满足性质的数时，可以转化为求[1,b]的答案减[1,a - 1]的答案。

前导0会产生影响时，先求出最高位不为0的所有情况，最后处理最高位为0的情况。
eg：339. 圆形数字、1083. Windy数

在分类讨论时，有点类似倒着DP，和预处理过程相反，两者状态转移方程相似。
eg:1086. 恨7不成妻



### [1397. 找到所有好字符串](https://leetcode-cn.com/problems/find-all-good-strings/) 



### Windy数





### [902. 最大为 N 的数字组合](https://leetcode-cn.com/problems/numbers-at-most-n-given-digit-set/)



### [338.计数问题](https://www.acwing.com/problem/content/340/)

