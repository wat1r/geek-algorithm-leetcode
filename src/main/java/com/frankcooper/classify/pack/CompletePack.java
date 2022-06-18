package com.frankcooper.classify.pack;

import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2020/3/25 23:22
 * Description
 */
public class CompletePack {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();//物品的件数N
        int V = scanner.nextInt();//背包的容量V
        int[] v = new int[N + 1];//长度为N的数组，第i个元素表示第i件物品的体积
        int[] w = new int[N + 1];//长度为N的数组，第i个元素表示第i件物品的价值
        for (int i = 1; i <= N; i++) {//N行 每行两个数，空格分开，v[i]表示是第i个物品的体积，w[i]是第i个物品的价值
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }
        scanner.close();
//        System.out.println(completePackProcess1st(N, V, v, w));
        System.out.println(completePackProcess2nd(N, V, v, w));
        System.out.println(completePackProcess3rd(N, V, v, w));
        System.out.println(completePackProcess4th(N, V, v, w));
    }

    //##### 方法1：`DP`二维数组三层循环版
    public static int completePackProcess1st(int N, int V, int[] v, int[] w) {
        int[][] dp = new int[N + 1][V + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= N; i++) {//for loop每一个物品
            for (int j = 1; j <= V; j++) {//for loop背包的重量
                for (int k = 0; k * v[i] <= j; k++) {//每个物品的个数，背包的不能无限大
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * v[i]] + k * w[i]);
                }
            }
        }
        return dp[N][V];
    }



    //##### 方法2：`DP`一维数组三层循环版
    public static int completePackProcess2nd(int N, int V, int[] v, int[] w) {
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {//for loop每一个物品
            for (int j = 1; j <= V; j++) {//for loop背包的重量
                for (int k = 0; k * v[i] <= j; k++) {//每个物品的个数，背包的不能无限大
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }
        return dp[V];
    }

    //##### 方法3：`DP`二维数组两层循环版
    public static int completePackProcess3rd(int N, int V, int[] v, int[] w) {
        int[][] dp = new int[N + 1][V + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j > v[i]) dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - v[i]] + w[i]);
            }
        }
        return dp[N][V];
    }

    //##### 方法4：`DP`一维数组两层循环版
    public static int completePackProcess4th(int N, int V, int[] v, int[] w) {
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = v[i]; j <= V; j++) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        return dp[V];
    }
    /*
    (https://imgchr.com/i/8x8suF)

##### 方法1：`DP`二维数组三层循环版

```java
public static int completePackProcess1st(int N, int V, int[] v, int[] w) {
        int[][] dp = new int[N + 1][V + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= N; i++) {//for loop每一个物品
            for (int j = 1; j <= V; j++) {//for loop背包的重量
                for (int k = 0; k * v[i] <= j; k++) {//每个物品的个数，背包的不能无限大
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * v[i]] + k * w[i]);
                }
            }
        }
        return dp[N][V];
    }
```



##### 方法2：`DP`一维数组三层循环版

```JAVA
 public static int completePackProcess2nd(int N, int V, int[] v, int[] w) {
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {//for loop每一个物品
            for (int j = 1; j <= V; j++) {//for loop背包的重量
                for (int k = 0; k * v[i] <= j; k++) {//每个物品的个数，背包的不能无限大
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }
        return dp[V];
    }
```

##### 方法3：`DP`二维数组两层循环版

$f[i , j ]  \qquad   =max( f[i-1,j] , f[i-1,j-v]+w ,  f[i-1,j-2*v]+2*w , f[i-1,j-3*v]+3*w , .....)$

$f[i , j-v]= max( \qquad\qquad\;\;    f[i-1,j-v]   ,  \qquad f[i-1,j-2*v] + w ,  \qquad f[i-1,j-2*v]+2*w , .....)$
由上两式，可得出如下递推关系：
                    $    f[i][j]=max(f[i,j-v]+w , f[i-1][j]) $

```java
for(int i = 1 ; i <=n ;i++)
for(int j = 0 ; j <=m ;j++)
{
    f[i][j] = f[i-1][j];
    if(k*v[i]<=j)
        f[i][j]=max(f[i][j],f[i][j-v[i]]+w[i]);
}
```

- $01$背包问题

```java
for(int i = 1 ; i <= n ; i++)
for(int j = 0 ; j <= m ; j ++)
{
    f[i][j] = f[i-1][j];
    if(j-v[i]>=0)
        f[i][j] = max(f[i][j],f[i-1][j-v[i]]+w[i]);
}

```

```java
两个代码其实只有一句不同（注意下标）
f[i][j] = max(f[i][j],f[i-1][j-v[i]]+w[i]);//01背包
f[i][j] = max(f[i][j],f[i][j-v[i]]+w[i]);//完全背包问题
```

- 完全背包问题可以优化成如下：

```java
 for(int i = 1 ; i<=n ;i++)
    for(int j = v[i] ; j<=m ;j++)//注意了，这里的j是从小到大枚举，和01背包不一样
    {
            f[j] = max(f[j],f[j-v[i]]+w[i]);
    }
```



```java
    public static int completePackProcess3rd(int N, int V, int[] v, int[] w) {
        int[][] dp = new int[N + 1][V + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= v[i]) dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - v[i]] + w[i]);
            }
        }
        return dp[N][V];
    }

```



##### 方法4：`DP`一维数组两层循环版

- 下面描述了这个过程，有点像每一层叠油，九九乘法表

![8xJalV.jpg](https://s1.ax1x.com/2020/03/25/8xJalV.jpg)

- **最优方法,时间复杂度O(n*V),空间复杂度为一维数组**

使用了滚动数组,并且只是01背包代码的修改而已

回想一下我们学01背包滚动数组版本的时候,是不是要求数组第二维的j一定要从后往前,从大到小来遍历,就是为了防止新数据被新数据覆盖

我们只允许旧数据被新数据覆盖(拿一次还是不拿),那么新数据被新数据覆盖在实际层面怎么理解呢,就是(拿j次还是拿j-1次)

所以接下来我们在01背包代码的基础上让第二维的j从小到大,从前往后

如果说新数据覆盖新数据更好,那么我就给你覆盖,也就是如果已经拿了几个这种物品了,如果再拿几个会更好,那我就给你拿



#####  方法4：`DP`一维数组两层循环版

```java
    public static int completePackProcess4th(int N, int V, int[] v, int[] w) {
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = v[i]; j <= V; j++) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        return dp[V];
    }
```
     */
}
