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







