



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



### 方法3:珂朵莉树

- [算法学习笔记(15): 珂朵莉树](https://zhuanlan.zhihu.com/p/106353082)
- [线段树的代替算法——珂朵莉树](https://www.codetd.com/article/13707910)

```c++
struct ChthollyNode {
    int l, r;
    mutable int v;
    ChthollyNode(int l, int r, int v) : l(l), r(r), v(v) {}
    bool operator<(const ChthollyNode &o) const {return l < o.l; }
};
class RangeModule {
public:
    std::set<ChthollyNode> tr;

    std::set<ChthollyNode>::iterator split(int pos) {
        auto it = tr.lower_bound(ChthollyNode(pos, 0, 0)); // 寻找左边大于等于pos的第一个节点
        if (it != tr.end() && it -> l == pos) return it;
        it--;
        int l = it -> l, r = it -> r, v = it -> v;
        tr.erase(it);
        tr.insert(ChthollyNode(l, pos - 1, v));
        return tr.insert(ChthollyNode(pos, r, v)).first;
    }

    void assign(int l, int r, int v) {
        auto itr = split(r + 1);
        auto itl = split(l);
        tr.erase(itl, itr);
        tr.insert(ChthollyNode(l, r, v));
    }

    bool check(int l, int r) {
        auto itr = split(r + 1);
        auto itl = split(l);
        auto it = itl;
        for (; it != itr; it++) {
            if (it -> v == 0) return false;
        }
        return true;
    }

    RangeModule() {
        tr.insert(ChthollyNode(1, 1e9, 0));
    }
    
    void addRange(int left, int right) {
        assign(left, right - 1, 1);
    }
    
    bool queryRange(int left, int right) {
        return check(left, right - 1);
    }
    
    void removeRange(int left, int right) {
        assign(left, right - 1, 0);
    }
};

```







## [850. 矩形面积 II](https://leetcode.cn/problems/rectangle-area-ii/)











## [2276. 统计区间中的整数数目](https://leetcode.cn/problems/count-integers-in-intervals/)



### 方法1：TreeMap合并区间

- 图2解释了合并多个区间，也就是while的条件

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220517224700.png)



![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220517212100.png)

```java
class CountIntervals {
            int sum;
            //k : 每个区间的左端点，v:这个区间[l,r]
            TreeMap<Integer, int[]> map;


            public CountIntervals() {
                sum = 0;
                map = new TreeMap<>();
            }
            //返回小于等于key的第一个元素：
            //    Map.Entry<K,V> floorEntry(K key);

            public void add(int left, int right) {
                //返回小于等于right的key的第一个元素
                Map.Entry<Integer, int[]> e = map.floorEntry(right);
                // l，r分别记录此次操作后最终的区间的 左边值、右边值
                int l = left;
                int r = right;
                //当前的元素找到了，且元素的右区间比当前的[l,r]的左区间要大
                while (e != null && e.getValue()[1] >= l) {
                    // 删除区间，同时对sum做减法，e区间即将要被删除
                    sum -= e.getValue()[1] - e.getValue()[0] + 1;
                    //e 和 [l,r]来更新一个新的区间
                    l = Math.min(l, e.getValue()[0]);
                    r = Math.max(r, e.getValue()[1]);
                    //移除e这个原来的区间
                    map.remove(e.getKey());
                    //继续找小于等于right的key的第一个元素
                    e = map.floorEntry(right);
                }
                // 一次操作过后，添加最终的区间，对sum做加法
                map.put(l, new int[]{l, r});
                sum += r - l + 1;
            }

            public int count() {
                return sum;
            }

        }
```



### 方法2：TreeSet合并区间

思路来自风雨大佬

```java
static class CountIntervals {
    TreeSet<Interval> set;
    int nums;

    public CountIntervals() {
        set = new TreeSet<>();
        nums = 0;
    }

    public void add(int left, int right) {
        //tailSet()方法返回包含指定元素的指定元素（作为参数传递）之后的树集的所有元素。
        //booleanValue参数是可选的。默认值为true。
        //如果false作为a传递booleanValue，则该方法将返回指定后的所有元素，element而不包括指定的element
        //起始需要找的区间，按[0,left]
        //返回的是 比left 都要大的区间（这些区间的右端点比left更大）
        Iterator<Interval> it = set.tailSet(new Interval(0, left), false).iterator();
        while (it.hasNext()) {
            Interval interval = it.next();
            //当前的right比iterator中的左区间要小，说明
            // current:[left,right] 与 iterator中的[left,right]没有相交叉的区间，可以提前退出了
            if (right < interval.left) {
                break;
            }
            //更新区间，左区间更左，右区间更右
            left = Math.min(left, interval.left);
            right = Math.max(right, interval.right);
            //当前区间开始移除
            it.remove();
            //数量更新
            nums -= (interval.right - interval.left + 1);
        }
        set.add(new Interval(left, right));
        nums += right - left + 1;
    }

    public int count() {
        return nums;
    }
}

static class Interval implements Comparable<Interval> {
    int left;
    int right;

    public Interval(int left, int right) {
        this.left = left;
        this.right = right;
    }

    //按右区间从小到大排序，如果右区间相同则按左区间从小到大排序
    public int compareTo(Interval that) {
        if (this.right == that.right)
            return this.left - that.left;
        return this.right - that.right;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
```

- 另一种写法

```java
        static class CountIntervals {

            TreeSet<int[]> ts;
            int sum;

            public CountIntervals() {
                ts = new TreeSet<>((a, b) -> a[1] - b[1]);
                sum = 0;
            }

            public void add(int left, int right) {
                Iterator<int[]> it = ts.tailSet(new int[]{0, left}).iterator();
                while (it.hasNext()) {
                    int[] interval = it.next();
                    if (right < interval[0]) {
                        break;
                    }
                    left = Math.min(left, interval[0]);
                    right = Math.max(right, interval[1]);
                    it.remove();
                    sum -= interval[1] - interval[0] + 1;
                }
                ts.add(new int[]{left, right});
                sum += right - left + 1;
            }

            public int count() {
                return sum;
            }
        }
```





权值线段树

- P1908逆序对







#### Reference

- [TreeSet与TreeMap使用指南](https://blog.csdn.net/wat1r/article/details/124831320)

- [2020-05-17 绿校服权值线段树与动态开点](https://www.bilibili.com/video/BV1Zg4y1q7aY?spm_id_from=333.337.search-card.all.click)
- [线段树专题](https://www.bilibili.com/video/BV1Si4y117dy/?spm_id_from=autoNext)

- [【neko】数据结构 线段树【算法编程#6】](https://www.bilibili.com/video/BV1yF411p7Bt?spm_id_from=333.337.search-card.all.click)
- [什么是线段树](https://www.bilibili.com/video/BV1NS4y1S77N?spm_id_from=333.337.search-card.all.click)
