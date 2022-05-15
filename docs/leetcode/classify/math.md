# 数学

> 







## [357. 统计各位数字都不同的数字个数](https://leetcode-cn.com/problems/count-numbers-with-unique-digits/)

```java
public int countNumbersWithUniqueDigits(int n) {
    if (n == 0) return 1;
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 10;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - 2]) * (10 - (i - 1));
    }
    return dp[n];
}
```





```java
public int countNumbersWithUniqueDigits(int n) {
    if (n == 0) return 1;
    int ans = 10;
    for (int i = 2, last = 9; i <= n; i++) {
        int cur = last * (10 - i + 1);
        ans += cur;
        last = cur;
    }
    return ans;
}
```





```java
public int countNumbersWithUniqueDigits(int n) {
    if (n == 0) return 1;
    if (n == 1) return 10;
    int prev = 10;
    for (int i = 9, curr = 9; --n > 0; prev += curr *= i--) ;
    return prev;
}
```





## [396. 旋转函数](https://leetcode-cn.com/problems/rotate-function/)

- `F(0) = 0 * A[0] + 1 * A[1] + 2 * A[2] +...+ i * A[i] +...+ (n - 1) * A[n - 1]`
- `F(1) = 0 * A[n - 1] + 1 * A[0] + 2 * A[1] +...+ (i + 1) * A[i] +...+ (n - 1) * A[n - 2]`

错位相减，拿`F(1)`中的第二位减去`F(0)`中的第一位

`F(1)-F(0)=0 * A[n - 1] + (1 * A[0]-0 * A[0]) + (2 * A[1]-1 * A[1]) +...+ ((i + 1) * A[i]-i * A[i]) +...+ ((n - 1) * A[n - 2] -(n - 2) * A[n - 2])-(n - 1) * A[n - 1]`

整理得到 

`F(1)-F(0)= A[0]+A[1]+...+A[i]+...A[n-2]-(n-1)A[n-1]`

多配一个`A[n-1]`

`F(1)-F(0)= A[0]+A[1]+...+A[i]+...A[n-2]+A[n-1]-  n* A[n-1]`

`F(1)-F(0)= sum{A[0]:A[n-1]}-  n* A[n-1]`

推广到一般情况，`A[n-1]`换成`A[n-k]`

`F(k+1)-F(k)= sum{A[0]:A[n-1]}-n*A[n-k]`

```java
        public int maxRotateFunction(int[] nums) {
            int n = nums.length, sum = 0;
            int t = 0;
            for (int i = 0; i < n; i++) {
                sum += nums[i];
                t += i * nums[i];
            }
            int res = t;
            for (int i = 1; i < n; i++) {
                int nxt = t + sum - n * nums[n - i];
                res = Math.max(res, nxt);
                t = nxt;
            }
            return res;
        }
```











## [479. 最大回文数乘积](https://leetcode-cn.com/problems/largest-palindrome-product/)

```java
public int largestPalindrome(int n) {
    if (n == 1) return 9;
    int MOD = 1337;
    int maxx = (int) (Math.pow(10, n) - 1);
    for (int i = maxx; i >= 0; i--) {
        long num = i, t = i;
        while (t > 0) {
            num = num * 10 + (t % 10);
            t /= 10;
        }
        for (long j = maxx; j * j >= num; --j) {
            if (num % j == 0) {
                return (int) (num % MOD);
            }
        }
    }
    return -1;
}
```







## [728. 自除数](https://leetcode-cn.com/problems/self-dividing-numbers/)

### 方法1:模拟

```java
```





## [812. 最大三角形面积](https://leetcode.cn/problems/largest-triangle-area/)

- [三角形面积公式](https://baike.baidu.com/item/%E4%B8%89%E8%A7%92%E5%BD%A2%E9%9D%A2%E7%A7%AF%E5%85%AC%E5%BC%8F/8491990)

### 方法1：海伦公式+暴力

```java
public double largestTriangleArea(int[][] points) {
    int n = points.length;
    double maxx = 0.0;
    for (int i = 0; i < n; i++) {
        int x1 = points[i][0], y1 = points[i][1];
        for (int j = i + 1; j < n; j++) {
            int x2 = points[j][0], y2 = points[j][1];
            double a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
            for (int k = j + 1; k < n; k++) {
                int x3 = points[k][0], y3 = points[k][1];
                double b = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
                double c = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
                double s = (a + b + c) * 0.5;
                //使用绝对值
                //case：{{35, -23}, {-12, -48}, {-34, -40}, {21, -25}, {-35, -44}, {24, 1}, {16, -9}, {41, 4}, {-36, -49}, {42, -49}, {-37, -20}, {-35, 11}, {-2, -36}, {18, 21}, {18, 8}, {-24, 14}, {-23, -11}, {-8, 44}, {-19, -3}, {0, -10}, {-21, -4}, {23, 18}, {20, 11}, {-42, 24}, {6, -19}};
                double t = Math.sqrt(s * Math.abs((s - a)) * Math.abs((s - b)) * Math.abs((s - c)));
                maxx = Math.max(maxx, t);
            }
        }
    }
    return maxx;
}
```



### 方法2：鞋带公式

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220515080716.png)

```java
public double largestTriangleArea(int[][] points) {
    int n = points.length;
    double maxx = 0.0;
    for (int i = 0; i < n; i++) {
        int x1 = points[i][0], y1 = points[i][1];
        for (int j = i + 1; j < n; j++) {
            int x2 = points[j][0], y2 = points[j][1];
            for (int k = j + 1; k < n; k++) {
                int x3 = points[k][0], y3 = points[k][1];
                double t = 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - y1 * x2 - y2 * x3 - y3 * x1);
                maxx = Math.max(maxx, t);
            }
        }
    }
    return maxx;
}
```



### 方法3：两边a,b,这两边夹角C

```java
        //精度问题
        //case:{{2, 5}, {7, 7}, {10, 8}, {10, 10}, {1, 1}}
        public double largestTriangleArea(int[][] points) {
            int n = points.length;
            double maxx = 0.0;
            for (int i = 0; i < n; i++) {
                int x1 = points[i][0], y1 = points[i][1];
                for (int j = i + 1; j < n; j++) {
                    int x2 = points[j][0], y2 = points[j][1];
                    double a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
                    for (int k = j + 1; k < n; k++) {
                        int x3 = points[k][0], y3 = points[k][1];
                        double b = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
                        double c = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
                        double cos = Math.abs((a * a + b * b - c * c)) / (2 * a * b);
                        double sin = Math.sqrt((double) 1 - cos * cos);
                        double t = 0.5 * a * b * sin;
                        maxx = Math.max(maxx, t);
                    }
                }
            }
            return maxx;
        }
```









## [908. 最小差值 I](https://leetcode-cn.com/problems/smallest-range-i/)

- [链接](https://leetcode-cn.com/problems/smallest-range-i/solution/zui-xiao-chai-zhi-i-by-leetcode-solution-7lcl/)

```java
        public int smallestRangeI(int[] nums, int k) {
            int minn = 10010, maxx = -10010;
            for (int x : nums) {
                minn = Math.min(minn, x);
                maxx = Math.max(maxx, x);
            }
            return maxx - minn <= 2 * k ? 0 : maxx - minn - 2 * k;
        }
```

lambda写法

```java
public int smallestRangeI(int[] nums, int k) {
    int minn = Arrays.stream(nums).min().getAsInt();
    int maxx = Arrays.stream(nums).max().getAsInt();
    return Math.max(0, maxx - minn - 2 * k);
}
```



## [910. 最小差值 II](https://leetcode-cn.com/problems/smallest-range-ii/)

- [链接](https://leetcode-cn.com/problems/smallest-range-ii/solution/tai-nan-liao-zhi-neng-hua-tu-ping-zhi-jue-by-user8/)

```java
public int smallestRangeII(int[] nums, int k) {
    int n = nums.length;
    Arrays.sort(nums);
    int res = nums[n - 1] - nums[0];
    for (int i = 0; i < n - 1; i++) {
        int maxx = Math.max(nums[i] + k, nums[n - 1] - k);
        int minn = Math.min(nums[i + 1] - k, nums[0] + k);
        int diff = maxx - minn;
        res = Math.min(res, diff);
    }
    return res;
}
```





## [1823. 找出游戏的获胜者](https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/)

- 约瑟夫环，同[剑指 Offer 62. 圆圈中最后剩下的数字](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/)

```java
public int findTheWinner(int n, int k) {
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= n; i++) list.add(i);
    int index = 0;
    while (list.size() > 1) {
        index = (index + k - 1) % list.size();
        list.remove(index);
    }
    return list.get(0);
}
```

- 动态规划参考**[剑指 Offer 62. 圆圈中最后剩下的数字（数学 / 动态规划，清晰图解）](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/jian-zhi-offer-62-yuan-quan-zhong-zui-ho-dcow/)**
- 参考链接**[换个角度举例解决约瑟夫环](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/huan-ge-jiao-du-ju-li-jie-jue-yue-se-fu-huan-by-as/)**

```java
public int findTheWinner(int n, int k) {
    int[] f = new int[n + 1];
    f[0] = 0;
    for (int i = 1; i <= n; i++) {
        f[i] = (f[i - 1] + k) % i;
    }
    return f[n] + 1;
}
```

- 动态规划优化

```java
public int findTheWinner(int n, int k) {
    int x = 0;
    for (int i = 2; i <= n; i++) {
        x = (x + k) % i;
    }
    return x + 1;
}
```





## [2121. 相同元素的间隔之和](https://leetcode-cn.com/problems/intervals-between-identical-elements/)



```java
public long[] getDistances(int[] arr) {
    // k: arr[i] v: i
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
        List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
        list.add(i);
        map.put(arr[i], list);
    }
    long[] intervals = new long[arr.length];
    for (List<Integer> ls : map.values()) {
        long sum = 0;
        for (int index : ls) sum += index - ls.get(0);
        intervals[ls.get(0)] = sum;
        for (int i = 1; i < ls.size(); i++) {
            sum += (2 * i - ls.size()) * (ls.get(i) - ls.get(i - 1));
            intervals[ls.get(i)] = sum;
        }
    }
    return intervals;
}
```

