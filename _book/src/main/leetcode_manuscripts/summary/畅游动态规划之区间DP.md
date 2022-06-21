## 畅游动态规划之区间DP

### 模板

#### 写法1

```java
for (int i = n; i >= 1; --i) {
	for (int j = i + 1; j <= n; ++j) {
		for (int k = i; k < j; ++k) {
			dp[i,j] = max/min(dp[i,j], dp[i,k] + dp[k+1, j] + cost)
		}
	}
}
```

这种写法就是常规的写法，枚举$i$为子区间左边界，枚举$j$为子区间有边界，枚举$k$为分界点。要注意由于要求的是$dp[1,n]$，所以$i$必须从大往小遍历，$j$必须从小往大遍历。这样在状态转移方程中利用的就是已求解的$dp$状态。

#### 写法2

```java
for (int len = 2; len <= n; ++len) {
	for (int i = 1; i + len - 1  <= n; ++i) {
		int j = i + len - 1;
		for (int k = i; k < j; ++k) {
			dp[i,j] = max/min(dp[i,j], dp[i,k] + dp[k+1, j] + cost)
		}
	}
}
```

这种写法最常见，枚举$len$为区间长度，枚举i为区间左端点，由此可以计算出区间右端点$j$，枚举$k$为分界点。区间长度从$2$到$n$，跟上一种写法相同。这种写法的正确性可能不如上一种那么直观，它从小到大枚举出所有区间，在求解大区间时，状态转移方程中利用的状态都是小区间的状态，必定在它之前被求解，所以也是正确的。

- 另

```java
for (int len = 2; len <= n; len++) {//区间长度
    for (int i = 1; i <= n; i++) { //枚举起点
        int j = i + len - 1; //区间终点
        if (j >= n) break; //防止越界
        for (int k = i+1; k < j; k++) { //枚举分割点，开始转移
            dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + w[i][j]);
        }
    }
}
```

### [282.石子合并](https://www.acwing.com/problem/content/284/)

![image-20210620065706854](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210620065706854.png)

```c++
#include <algorithm>
#include <iostream>
#include <cstring>

using namespace std;

const int N = 310;

int n;       //石子的堆数
int a[N];    //每一个石子的重量
int s[N];    //前缀和
int f[N][N]; //f[l][r] 表示从 [l,r] 合并成一堆的代价

void init()
{
    memset(f, 0x3f, sizeof(f)); //求区间的最小值，初始化为最大
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        cin >> a[i];            //输入的每个石子重量
        s[i] = s[i - 1] + a[i]; //求前缀和
        f[i][i] = 0;            //合并一个石子本身的价值是0
    }
}

int main(int argc, char const *argv[])
{
    init();
    for (int len = 2; len <= n; len++)//枚举区间长度
    {
        for (int l = 1; l + len - 1 <= n; l++)//区间起点
        {
            int r = l + len - 1;//区间终点
            for (int k = l; k < r; k++)//枚举
            {
                f[l][r] = min(f[l][r], f[l][k] + f[k + 1][r] + s[r] - s[l - 1]);
            }
        }
    }
    cout << f[1][n] << endl;
    return 0;
}

```

### [1068.环形石子合并](https://www.acwing.com/activity/content/16/)

![image-20210620173429127](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210620173429127.png)



```c++
#include <cstring>
#include <algorithm>
#include <iostream>
#include <cstdlib>

using namespace std;

const int N = 410;
int n;       //石子的个数
int a[N];    //每个石子的质量
int s[N];    //前缀和
int f[N][N]; //f[l][r]表示[l,r]区间范围合并成一堆后的最小值
int g[N][N]; //g[l][r]表示[l,r]区间范围合并成一堆后的最大值
int INF = 0x3f3f3f3f;

void init()
{
    memset(f, INF, sizeof(f));
    memset(g, -INF, sizeof(g));
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        cin >> a[i];
        a[i + n] = a[i];
    }
    for (int i = 1; i <= 2 * n; i++)
    {
        s[i] = s[i - 1] + a[i];
        f[i][i] = 0;
        g[i][i] = 0;
    }
}

int main(int argc, char const *argv[])
{
    for (int len = 2; len <= n; len++)
    {
        for (int l = 1; l + len - 1 <= 2 * n; l++)
        {
            int r = l + len - 1;
            for (int k = l; k < r; k++)
            {
                f[l][r] = min(f[l][r], f[l][k] + f[k + 1][r] + s[r] - s[l - 1]);
                g[l][r] = max(g[l][r], f[l][k] + g[k + 1][r] + s[r] - s[l - 1]);
            }
        }
    }
    int maxv = -INF, minv = INF;
    for (int i = 1; i <= n; i++)
    {
        minv = min(minv, f[i][i + n - 1]);
        maxv = max(maxv, f[i][i + n - 1]);
    }
    cout << minv << maxv << endl;
    return 0;
}

```



### [320.能量项链](https://www.acwing.com/problem/content/322/)

![image-20210620164950349](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210620164950349.png)

```c++
#include <cstring>
#include <algorithm>
#include <iostream>
#include <cstdlib>

using namespace std;

const int N = 210;
int n;
int a[N];    //a[i]表示第i颗珠子的能量值
int f[N][N]; //f[l][r]表示合并[l,r]范围内珠子获得的最大的能量值

int main(int argc, char const *argv[])
{
    //处理输入
    cin >> n; //珠子的数量
    for (int i = 1; i <= n; i++)
    {
        cin >> a[i];
        a[i + n] = a[i]; //复制一遍区间，环形链表拍平，处理成线性
    }
    for (int len = 3; len <= n + 1; len++)
    {
        for (int l = 1; l + len - 1 <= 2 * n; l++)
        {
            int r = l + len - 1;
            for (int k = l + 1; k < r; k++)
            {
                f[l][r] = max(f[l][r], f[l][k] + f[k][r] + a[l] * a[k] * a[r]);
            }
        }
    }
    int res = 0;
    for (int i = 1; i <= n; i++)
    {
        res = max(res, f[i][i + n]);
    }
    cout << res << endl;
    return 0;
}

```





## [1000. 合并石头的最低成本](https://leetcode.cn/problems/minimum-cost-to-merge-stones/)

- 国际站的[链接](https://leetcode.com/problems/minimum-cost-to-merge-stones/discuss/247657/JAVA-Bottom-Up-%2B-Top-Down-DP-With-Explaination)

> **初始的问题：如果将相邻的两堆石子最终合并成一堆，最小花费是多少？**

对于$[3,2,4,1]$,如果采用贪心的方式去做，每次都去合并相对较小的两堆石子，$[「3,2」,4,1]$=>$[5,「4,1」]$

=>$[5,5]$=>$[10]$,第一次花费$1+4 = 5 $, 第二次花费$4+1=5$，第三次花费$5+5$ ,花费总计为$20$

上面的例子看起来还能行得通，当遇到这个例子的时候$[6,4,4,6]$,如果采用的是贪心的方式求解：

```java
[6,4,4,6]=>[6,「4,4」,6]=>[「6,8」,6]=>[14,6]=>[20] //最小花费是4+4=8 6+8=14 14+6=20  总计42 
```

但是有一种更小花费的做法：

```java
[「6,4」,4,6]=>[10,「4,6」]=>[10,10]=>[20]  //最小花费是6+4=10 4+6=10 10+10=20 总计40
```

我们不知道那合并哪两堆，但是知道合并两堆的总和的花费，不论如何划分两堆，两堆的总和的结果总是恒定的

```java
[3 | 2, 4, 1]
3 + 7 = 10
[3 , 2 | 4, 1]
5 + 5 = 10
[3 , 2, 4 | 1]
9 + 1 = 10
```

**如何获取划分两堆的最小花费？**

可以考虑将左部分划到1堆中，并最小化右部分的划到1堆中的花费，这个是典型的子问题

##### 状态

- $dp[i][j]$表示合并$[i...j]$堆到1堆时花费的最小代价

##### 转移方程

- $dp[i][j]=min(dp[i][j],dp[i][k]+dp[k+1][j])(i<=k<j)$ 

##### 初始化

- 当$i==j$时，$dp[i][i]=0$,因为本身就是一堆了，合并的代价为0

##### 返回

- $dp[1][n]$为结果，表示合并$[1...n]$区间的石子，最小的代价是多少

















- 解释何种情况下能做到「**找出把所有石头合并成一堆**」

例如$[3,5,1,2,6]$，$K=3$,第一次合并$K$个数，合并前三个数$[3,5,1]$，得到$[3+5+1=9]$,对剩下的$[9,2,6]$进行合并，得到$[9+2+6=17]$,总成本$9+17=26$，在合并的过程中，第一次用掉了$K$个石子，这$K$个石子会剩下的石子列表中的$K-1$个合并成一堆，再在剩下的石子列表中取$K-1$个石子合并成一堆，如此反复，直到取完所有的石子，记取$K-1$个石子的行为，为$m$次，如果能恰好取完相当于$K+m(K-1)=n$  => $(K-1)+m(K-1)=n-1$也就是说 $(n-1)%(K-1)=0$，即可以满足条件







