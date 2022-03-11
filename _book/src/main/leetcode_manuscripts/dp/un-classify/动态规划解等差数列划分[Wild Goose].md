## 动态规划解等差数列划分[Wild Goose]

![goose-20316_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\un-classify\动态规划解等差数列划分[].assets\goose-20316_640.jpg)







### 方法1:暴力

```java
    public int numberOfArithmeticSlices1st(int[] A) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            int delta = A[i + 1] - A[i];
            for (int j = i + 2; j < n; j++) {
                if (A[j] - A[j - 1] == delta) res++;
                else break;
            }
        }
        return res;
    }
```



### 方法2:递归

```java
    int res = 0;

    public int numberOfArithmeticSlices2nd(int[] A) {
        helper(A, A.length - 1);
        return res;
    }

    private int helper(int[] A, int i) {
        if (i < 2) return 0;
        int tmp = 0;
        if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
            tmp += helper(A, i - 1) + 1;
            res += tmp;
        } else {
            helper(A, i - 1);
        }
        return tmp;
    }

```

### 方法3:DP

```java
    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        int res = 0;
        int[] dp = new int[n];
        for (int i = 2; i < n; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }
        return res;
    }

```

