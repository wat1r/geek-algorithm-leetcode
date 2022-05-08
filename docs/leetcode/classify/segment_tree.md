



> 线段树与树状数组





## 树状数组

- [链接](https://www.cnblogs.com/Last--Whisper/p/13823614.html)

### 模板

```java
public class BinaryIndexedTree {

    int N = 10010;
    int[] tree = new int[N];

    int lowbit(int x) {
        return -x & x;
    }

    int query(int x) {
        int sum = 0;
        for (int i = x; i >= 0; i -= lowbit(i)) sum += tree[i];
        return sum;
    }

    void update(int x, int v) {
        for (int i = x; i <= N; i += lowbit(i)) tree[i] += v;
    }

    int query(int l, int r) {
        return query(r + 1) - query(l);
    }

}
```











## [307. 区域和检索 - 数组可修改](https://leetcode-cn.com/problems/range-sum-query-mutable/)

```java
class NumArray {

    int[] tree;
    int n;
    int[] nums;

    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[n + 1];
        this.nums = nums;
        for (int i = 0; i < n; i++) add(i + 1, nums[i]);

    }

    public void update(int index, int val) {
        // 原有的值是 nums[i]，要使得修改为 val，需要增加 val - nums[i]
        add(index + 1, val - nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }


    private int query(int x) {
        int sum = 0;
        for (int i = x; i > 0; i -= lowbit(i)) sum += tree[i];
        return sum;
    }


    private void add(int x, int v) {
        for (int i = x; i <= n; i += lowbit(i)) {
            tree[i] += v;
        }
    }


    private int lowbit(int x) {
        return -x & x;
    }


}
```





## [327. 区间和的个数](https://leetcode-cn.com/problems/count-of-range-sum/)

### 方法1：树状数组

```java

        public int countRangeSum(int[] nums, int lower, int upper) {
            int n = nums.length;
            long[] preSum = new long[n + 1];
            for (int i = 1; i <= n; i++) preSum[i] = preSum[i - 1] + nums[i - 1];
            Set<Long> set = new TreeSet<>();
            for (long x : preSum) {
                set.add(x);
                set.add(x - lower);
                set.add(x - upper);
            }
            int M = 1;
            Map<Long, Integer> map = new HashMap<>();
            for (long x : set) {
                map.put(x, M++);
            }
            BIT bit = new BIT(M);
            int res = 0;
            for (long x : preSum) {
                int lo = map.get(x - upper), hi = map.get(x - lower);
                int index = map.get(x);
                res += bit.query(hi) - bit.query(lo - 1);
                bit.update(index, 1);
            }
            return res;

        }


        static class BIT {
            int[] tree;
            int n;

            public BIT(int n) {
                this.n = n;
                this.tree = new int[n + 1];
            }

            public static int lowbit(int x) {
                return x & (-x);
            }

            public void update(int x, int d) {
                while (x <= n) {
                    tree[x] += d;
                    x += lowbit(x);
                }
            }

            public int query(int x) {
                int ans = 0;
                while (x != 0) {
                    ans += tree[x];
                    x -= lowbit(x);
                }
                return ans;
            }
        }
```

