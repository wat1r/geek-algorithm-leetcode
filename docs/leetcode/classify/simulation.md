# 模拟

> 

## [2028. 找出缺失的观测数据](https://leetcode-cn.com/problems/find-missing-observations/)

```java
        public int[] missingRolls(int[] rolls, int mean, int n) {
            int m = rolls.length;
            int tot = (n + m) * mean;
            for (int x : rolls) tot -= x;
            if (tot < n || tot > 6 * n) return new int[]{};
            int[] res = new int[n];
            Arrays.fill(res, tot / n);
            if (tot / n * n < tot) {
                int diff = tot - (tot / n * n);
                int idx = 0;
                while (diff-- > 0) res[idx++]++;
            }
            return res;
        }
```

