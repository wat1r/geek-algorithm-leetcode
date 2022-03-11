## 畅游动态规划之树形DP

### 1072.树的最长路径

![image-20210621202147053](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210621202147053.png)



```c++
#include <cstring>
#include <algorithm>
#include <iostream>
#include <cstdlib>

using namespace std;

const int N = 10010;
int M = 2 * N;

int n, ans;
bool vis[N];

int h[N];  //邻接表 当前边的编号
int e[N];  //当前边的终点
int w[N];  //当前边的权值
int ne[N]; //下一条边的编号
int idx;

void add(int u, int v, int weight)
{
    e[++idx] = v;
    w[idx] = weight;
    ne[idx] = h[u];
    h[u] = idx;
}

int dfs(int u)
{
    vis[u] = true;                         //标记u被访问过
    int d1 = 0, d2 = 0;                    //d1 和 d2 分别记录u往下搜索时记录的最大和次大路径长度
    for (int i = h[u]; i != -1; i = ne[i]) //遍历每一条表
    {
        int v = e[u]; //u指向的点 v
        if (vis[v])   //v被访问过，不再访问
            continue;
        int d = dfs(v) + w[i]; //更新v向下搜索获取的最大路径长度
        if (d >= d1)//更新最大和次大
        {
            d2 = d1;
            d1 = d;
        }
        else if (d > d2)
        {
            d2 = d;
        }
    }
    ans = max(ans, d1 + d2);//更新全局
    return d1;
}

int main(int argc, char const *argv[])
{
    memset(h, 0, sizeof(h));
    cin >> n;
    for (int i = 1; i < n; i++)
    {
        int u, v, weight;
        cin >> u >> v >> weight;
        add(u, v, weight);
        add(v, u, weight);
    }
    dfs(1);
    cout << ans << endl;
    return 0;
}

```



### [285.没有上司的舞会](https://www.acwing.com/problem/content/287/)

![image-20210621203041506](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210621203041506.png)

![image-20210621203452579](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210621203452579.png)

```c++
#include <cstring>
#include <algorithm>
#include <iostream>
#include <cstdlib>

using namespace std;

const int N = 6010;

//采用邻接点存储
int w[N];
int a[N][N];
int b[N];

bool fa[N];

int f[N][N];

void dfs(int u)
{
    f[u][1] = w[u];
    for (int i = 0; i < b[u]; i++)
    {
        int son = a[u][i];
        dfs(son);
        f[u][0] += max(f[son][0], f[son][1]);
        f[u][1] += f[son][0];
    }
}

int main(int argc, char const *argv[])
{
    int n;
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        cin >> w[i];
    }
    for (int i = 0; i < n - 1; i++)
    {
        int x, y;
        cin >> x >> y;
        a[y][b[y]++] = x; //y的邻接点个数存如b数组 y的邻接点x存入a数组
        fa[x] = true;
    }
    int root = 1;
    while (fa[root])
    {
        root++;
    }
    // cout << root << endl;
    dfs(root);
    cout << max(f[root][0], f[root][1]) << endl;
    return 0;
}

```







### 树的重心

![image-20210621210536850](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210621210536850.png)

### 树的中心