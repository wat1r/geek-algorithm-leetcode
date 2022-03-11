## Hash之统计一个数组的好对字数目（两种解法，一次遍历）





### 方法1

```java
        int MOD = (int) 1e9 + 7;

        public int countNicePairs(int[] nums) {
            long res = 0;
            Map<Integer, Long> map = new HashMap<>();//标记成Long类型
            for (int x : nums) {
                int diff = x - rev(x);
                if (map.containsKey(diff)) res = (res + map.get(diff)) % MOD; //取MOD 每次进来的一个数都可以与前面和其相同的数，组成一对
                map.put(diff, map.getOrDefault(diff, 0L) + 1);
            }
            return (int) res % MOD;

        }


        private int rev(int x) {
            int res = 0;
            while (x > 0) {
                res = res * 10 + x % 10;
                x /= 10;
            }
            return res;
        }
```



### 方法2

```java
        int MOD = (int) 1e9 + 7;

        public int countNicePairs(int[] nums) {
            Map<Integer, Long> map = new HashMap<>();//标记成long类型
            long res = 0;
            for (int x : nums) {
                int diff = x - rev(x);
                map.put(diff, map.getOrDefault(diff, 0L) + 1);
            }
            for (int diff : map.keySet()) {
                if (map.get(diff) > 1) {
                    long t = map.get(diff);
                    res += t * (t - 1) / 2 % MOD;
                }
            }
            return (int) res % MOD;

        }


        private int rev(int x) {
            int res = 0;
            while (x > 0) {
                res = res * 10 + x % 10;
                x /= 10;
            }
            return res;
        }
```

