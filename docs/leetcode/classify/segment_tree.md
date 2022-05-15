



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



### 方法1：树状数组

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

### 方法2：线段树

- 参考链接：[数据结构设计之线段树[White Rhinoceros]](https://blog.csdn.net/wat1r/article/details/124786384?spm=1001.2014.3001.5501)

```java
        class NumArray {

            int[] tree;
            int n;


            public NumArray(int[] nums) {
                n = nums.length;
                tree = new int[n * 4];
                build(nums, 0, 0, n - 1);
            }


            public void update(int index, int val) {
                update(0, 0, n - 1, index, val);
            }

            public int sumRange(int L, int R) {
                return query(0, 0, n - 1, L, R);
            }


            private void build(int[] nums, int node, int start, int end) {
                if (start == end) {
                    tree[node] = nums[start];
                    return;
                }
                int mid = start + (end - start) / 2;
                int left_node = node * 2 + 1;
                int right_node = node * 2 + 2;
                build(nums, left_node, start, mid);
                build(nums, right_node, mid + 1, end);
                tree[node] = tree[left_node] + tree[right_node];
            }

            private void update(int node, int start, int end, int idx, int val) {
                if (start == end) {
                    tree[node] = val;
                    return;
                }
                int mid = start + (end - start) / 2;
                int left_node = node * 2 + 1;
                int right_node = node * 2 + 2;
                if (idx <= mid) {
                    update(left_node, start, mid, idx, val);
                } else {
                    update(right_node, mid + 1, end, idx, val);
                }
                tree[node] = tree[left_node] + tree[right_node];
            }

            private int query(int node, int start, int end, int L, int R) {

                if (R < start || L > end) {
                    return 0;
                } else if (L <= start && end <= R) {
                    return tree[node];
                } else if (start == end) {
                    return tree[node];
                }
                int mid = start + (end - start) / 2;
                int left_node = node * 2 + 1;
                int right_node = node * 2 + 2;
                int sum_left = query(left_node, start, mid, L, R);
                int sum_right = query(right_node, mid + 1, end, L, R);
                return sum_left + sum_right;
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





## [715. Range 模块](https://leetcode.cn/problems/range-module/)



![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220515213939.png)







![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220515215407.png)

### 方法1：TreeMap

```java
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            //["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
            //[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
            //输出
            //[null, null, null, true, false, true]
            String[] ops = {"RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"};
            int[][] vals = {{}, {10, 20}, {14, 16}, {10, 14}, {13, 15}, {16, 17}};
            ops = new String[]{"RangeModule", "addRange", "addRange", "addRange", "addRange"};
            vals = new int[][]{{}, {10, 20}, {32, 40}, {20, 30}, {35, 50}};
            RangeModule rm = new RangeModule();
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                if (op.equals("RangeModule")) {
                    rm = new RangeModule();
                } else if (op.equals("addRange")) {
                    int left = vals[i][0], right = vals[i][1];
                    rm.addRange(left, right);
                } else if (op.equals("removeRange")) {
                    int left = vals[i][0], right = vals[i][1];
                    rm.removeRange(left, right);
                } else if (op.equals("queryRange")) {
                    int left = vals[i][0], right = vals[i][1];
                    boolean f = rm.queryRange(left, right);
                }
            }

        }

        static class RangeModule {

            //    返回小于等于key的第一个键：
            //    K floorKey(K key);
            //    返回大于或者等于key的第一个键：
            //    K ceilingKey(K key);

            //k:left v:right range的左右边界
            TreeMap<Integer, Integer> map;

            public RangeModule() {
                map = new TreeMap<>();
            }

            public void addRange(int left, int right) {
                if (left >= right) return;
                Integer start = map.floorKey(left);
                if (start == null) start = map.ceilingKey(left);
                while (start != null && start <= right) {
                    int end = map.get(start);
                    if (end >= left) {//存在overlap
                        map.remove(start);
                        if (start < left) left = start;
                        if (end > right) right = end;
                    }
                    start = map.ceilingKey(end);
                }
                map.put(left, right);
            }

            public boolean queryRange(int left, int right) {
                Integer start = map.floorKey(left);
                if (start == null) {
                    return false;
                }
                return map.get(start) >= right;
            }

            public void removeRange(int left, int right) {
                if (left >= right) return;
                Integer start = map.floorKey(left);
                if (start == null) start = map.ceilingKey(left);
                while (start != null && start < right) {
                    int end = map.get(start);
                    if (end >= left) {
                        map.remove(start);
                        if (start < left) map.put(start, left);
                        if (end > right) map.put(right, end);
                    }
                    start = map.ceilingKey(end);
                }
            }
        }
```



### 方法2：Segment Tree

```java
        class RangeModule {

            SegmentNode root;
            int max = (int) Math.pow(10, 9);


            public RangeModule() {
                root = new SegmentNode(0, max, false);
            }

            public void addRange(int l, int r) {
                update(root, l, r, true);
            }


            private boolean update(SegmentNode node, int l, int r, boolean state) {
                if (l <= node.l && node.r <= r) {
                    node.state = state;
                    node.left = null;
                    node.right = null;
                    return node.state;
                }
                if (l >= node.r || r <= node.l) return node.state;
                int mid = node.l + (node.r - node.l) / 2;
                if (node.left == null) {
                    node.left = new SegmentNode(node.l, mid, node.state);
                    node.right = new SegmentNode(mid, node.r, node.state);
                }
                boolean lflag = update(node.left, l, r, state);
                boolean rflag = update(node.right, l, r, state);
                node.state = lflag && rflag;
                return node.state;
            }

            public boolean queryRange(int l, int r) {
                return query(root, l, r);
            }

            private boolean query(SegmentNode node, int l, int r) {
                if (l >= node.r || r <= node.l) return true;
                if ((l <= node.l && r >= node.r) || node.left == null) return node.state;
                int mid = node.l + (node.r - node.l) / 2;
                if (r <= mid) {
                    return query(node.left, l, r);
                } else if (l >= mid) {
                    return query(node.right, l, r);
                } else {
                    return query(node.left, l, r) && query(node.right, l, r);
                }
            }


            public void removeRange(int l, int r) {
                update(root, l, r, false);
            }


        }


        class SegmentNode {
            public int l;
            public int r;
            public boolean state;
            public SegmentNode left;
            public SegmentNode right;

            public SegmentNode(int l, int r, boolean state) {
                this.l = l;
                this.r = r;
                this.state = state;
            }
        }
```

