

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



### 1081.度的数量



```c++
求给定区间 [X,Y] 中满足下列条件的整数个数：这个数恰好等于 K 个互不相等的 B 的整数次幂之和。

例如，设 X=15,Y=20,K=2,B=2，则有且仅有下列三个数满足题意：

17=2^4+2^0
18=2^4+2^1
20=2^4+2^2
输入格式
第一行包含两个整数 X 和 Y，接下来两行包含整数 K 和 B。

输出格式
只包含一个整数，表示满足条件的数的个数。

数据范围
1≤X≤Y≤231−1,
1≤K≤20,
2≤B≤10
  
输入样例：
15 20
2
2
输出样例：
3
输入样例：
489199 894799999
15
3
输出样例：
3876
  
  1 65 2 4   65：1001
  1 97 2 4   97：1201
  1 85 2 4   85：1111
  
```

- 这个数恰好等于 K 个互不相等的 B 的整数次幂之和 等价于 求数字n在B进制下是否由K个1其他为全是0组成

![image-20210617194847549](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210617194847549.png)

```java

首先 对于第 i 位数字 x 有三种情况

x = 0 ：则 i 位上只能取 0 ，所以直接讨论 i-1 位就可以了
x = 1 ：则 i 位上取 0 的时候，后面i-1位都可以随意取值 ，取 1 的时候，后面i- 1位要再小于题目的数的前提下取值，并且能取 k-last-1 个 1 ，
x > 1 ：则 i 位上 可以取 1 ，0 ，并且后面 i-1 位可以随便取。
这里 i-1位随便取 和 对于i-1 位讨论的区别，就体现在前一个直接用组合数 f[i-1][k-last]就可以，后面则需要再进入循环去讨论。

最后对于最后一位进行单独讨论，如果对于 最后一位时 ，所有的k个1都已经取好了，也就是k==last了，才做res++。
```

![image-20210617195732116](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210617195732116.png)



```c++
#include <vector>
#include <cstring>
#include <iostream>

using namespace std;

const int N = 35;

int f[N][N]; //f[i][j]从i个数中选j个数
int K, B;
void init()
{
    for (int i = 0; i < N; i++)
        for (int j = 0; j <= i; j++)
            if (!j)
                f[i][j] = 1;
            else
                f[i][j] = f[i - 1][j] + f[i - 1][j - 1];
}

int dp(int n)
{
    if (!n)
        return 0;
    vector<int> nums;
    while (n)
    {
        nums.push_back(n % B);
        n /= B;
    }
    // printf("%s",nums);
    int res = 0;
    int last = 0;                              //记录有多少个1了
    for (int i = nums.size() - 1; i >= 0; --i) //从高位到低位
    {
        int x = nums[i]; //得到第i位上的数
        if (x)           //求左边分支的数的个数
        {
            res += f[i][K - last]; //如果是0的方案数
            if (x > 1)             //x>1
            {
                if (K - last - 1 >= 0)
                    res += f[i][K - last - 1];
                break; //0 和 1 的情况都判断了, 若果x > 1的话,就没有方案了，直接break掉
            }
            else //x ==1
            {
                last++;
                if (last > K)
                    break;
            }
        }
        if (!i && last == K) //最右侧分支上的方案数
            res++;
    }

    return res;
}

int main(int argc, char const *argv[])
{

    cout << "hello" << endl;
    init();
    int l, r;
    l = 15, r = 20;
    K = 2, B = 2;
    // cin >> l >> r >> K >> B;
    l = 489199, r = 894799999;
    K = 15, B = 3;
    l = 1 ,r = 85 ;
    K = 2 ,B = 4;

    cout << dp(r) - dp(l - 1) << endl;
    return 0;
}

```





### 1082.数字游戏

```c++
科协里最近很流行数字游戏。

某人命名了一种不降数，这种数字必须满足从左到右各位数字呈非下降关系，如 123，446。

现在大家决定玩一个游戏，指定一个整数闭区间 [a,b]，问这个区间内有多少个不降数。

输入格式
输入包含多组测试数据。

每组数据占一行，包含两个整数 a 和 b。

输出格式
每行给出一组测试数据的答案，即 [a,b] 之间有多少不降数。

数据范围
1≤a≤b≤231−1

输入样例：
1 9
1 19
输出样例：
9
18

```



![image-20210617221211554](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210617221211554.png)





![image-20210617230800931](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210617230800931.png)



```c++
l = 1 , r =5728  
@"f[4][0]=220--> res:0\r\n"
@"f[4][1]=165--> res:220\r\n"
@"f[4][2]=120--> res:385\r\n"
@"f[4][3]=84--> res:505\r\n"
@"f[4][4]=56--> res:589\r\n"
@"f[3][5]=15--> res:645\r\n"
@"f[3][6]=10--> res:660\r\n"
当 i= 3 后 就break掉了 即在处理高位7 728的时候，固定高位为7 2<7 不能构成不降数，因此break退出循环结束
```



#### 主体代码

```c++
#include <vector>
#include <cstring>
#include <iostream>

using namespace std;

const int N = 15;

int f[N][N];//f[i][j]表示一共i位，最高位为j的数的个数

void init()
{
    for (int i = 0; i <= 9; i++)
        f[1][i] = 1;
    for (int i = 2; i < N; i++)
    {
        for (int j = 0; j <= 9; j++)
        {
            for (int k = j; k <= 9; k++)
            { 
                f[i][j]+=f[i-1][k];
            }
        }
    }
}

int dp(int n ){
    if(!n) return 1;
    vector<int> nums;
    while(n){
        nums.push_back(n%10);
        n/=10;
    }
    int res = 0 ;
    int last = 0;
    for(int i =nums.size()-1;i>=0;--i){
        int x = nums[i];
        for(int j =last;j<x;j++){
            printf("f[%d][%d]=%d--> res:%d\n",i+1,j,f[i+1][j],res);
            res+=f[i+1][j];
        }
        if(x<last) break;
        last = x;
        if(!i) res++ ;
    }


    return res;
}


int main(int argc, char const *argv[])
{

    init();
    int l , r ;
    // while (cin>>l>>r)
    // {
    //     cout<<dp(r)-dp(l-1)<<endl;
    // }
    l = 1 ,r =2557;
    printf("%d-->%d\n",l,r);
    l  = 1 ,r = 5728;
    printf("%d-->%d\n",l,r);
    cout<<dp(r)-dp(l-1)<<endl;

    return 0;
}

```

### 1083.Windy数

```c++
描述
Windy 定义了一种 Windy 数：不含前导零且相邻两个数字之差至少为 2 的正整数被称为 Windy 数。
Windy 想知道，在 A 和 B 之间，包括 A 和 B，总共有多少个 Windy 数？
输入格式
共一行，包含两个整数 A 和 B。
输出格式
输出一个整数，表示答案。
数据范围
1 ≤ A ≤ B ≤ 2 × 10^9 
输入样例1：
1 10
1
输出样例1：
9
1
输入样例2：
25 50
1
输出样例2：
20

```



![image-20210618085103230](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210618085103230.png)













### [1397. 找到所有好字符串](https://leetcode-cn.com/problems/find-all-good-strings/) 



### 





### [902. 最大为 N 的数字组合](https://leetcode-cn.com/problems/numbers-at-most-n-given-digit-set/)



### [338.计数问题](https://www.acwing.com/problem/content/340/)







