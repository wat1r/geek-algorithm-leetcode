## 畅游动态规划之区间DP

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





### 

