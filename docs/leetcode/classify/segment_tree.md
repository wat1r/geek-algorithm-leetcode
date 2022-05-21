



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





### 方法3.线段树

```java
   class NumArray {
            Node[] tr;

            class Node {
                int l, r, v;

                public Node(int l, int r) {
                    this.l = l;
                    this.r = r;
                }
            }


            void pushup(int u) {
                tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
            }

            void build(int u, int l, int r) {
                tr[u] = new Node(l, r);
                if (l == r) return;
                int mid = l + r >> 1;
                build(u << 1, l, mid);
                build(u << 1 | 1, mid + 1, r);
            }

            void update(int u, int index, int value) {
                if (tr[u].l == index && tr[u].r == index) {
                    tr[u].v += value;
                    return;
                }
                int mid = tr[u].l + tr[u].r >> 1;
                if (index <= mid) update(u << 1, index, value);
                else update(u << 1 | 1, index, value);
                pushup(u);
            }

            int query(int u, int L, int R) {
                if (L <= tr[u].l && tr[u].r <= R) return tr[u].v;
                int mid = tr[u].l + tr[u].r >> 1;
                int res = 0;
                if (L <= mid) res += query(u << 1, L, R);
                if (R > mid) res += query(u << 1 | 1, L, R);
                return res;
            }

            int[] nums;

            public NumArray(int[] nums) {
                int N = nums.length;
                this.nums = nums;
                tr = new Node[4 * N];
                build(1, 1, N);
                for (int i = 0; i < N; i++) {
                    update(1, i + 1, nums[i]);
                }
            }

            public void update(int index, int val) {
                update(1, index + 1, val - nums[index]);
                nums[index] = val;
            }

            public int sumRange(int left, int right) {
                return query(1, left + 1, right + 1);
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



## [729. 我的日程安排表 I](https://leetcode.cn/problems/my-calendar-i/)

### 方法1：线段树

> 动态开点，懒标记，[链接](https://leetcode.cn/problems/my-calendar-i/solution/by-ac_oier-1znx/)

```java
        class MyCalendar {

            //然一个比较实用的估点方式可以「尽可能的多开点数」，利用题目给定的空间上界和我们创建的自定义类
            // （结构体）的大小，尽可能的多开（ Java 的 128M 可以开到 5 * 10^6 以上）。


            class Node {
                int ls, rs;//分别代表当前节点的左右子节点在线段树数组 tr 中的下标
                int lazy;//懒标记
                int val;//当前节点有多少个数
            }

            int N = (int) 1e9 + 10;
            int cnt = 1;//动态开点的下标
            int M = 120010;
            Node[] tr = new Node[M];

            //
            void update(int u, int l, int r, int L, int R, int v) {
                //[l,r]区间被[L,R]覆盖
                if (L <= l && r <= R) {
                    tr[u].val += (r - l + 1) * v;
                    tr[u].lazy += v;
                    return;
                }
                lazyCreate(u);
                pushdown(u, r - l + 1);
                int mid = l + r >> 1;
                if (L <= mid) update(tr[u].ls, l, mid, L, R, v);
                if (R > mid) update(tr[u].rs, mid + 1, r, L, R, v);
                pushup(u);
            }

            int query(int u, int l, int r, int L, int R) {
                if (L <= l && r <= R) return tr[u].val;
                lazyCreate(u);
                pushdown(u, r - l + 1);
                int mid = l + r >> 1;
                int res = 0;
                if (L <= mid) res = query(tr[u].ls, l, mid, L, R);
                if (R > mid) res += query(tr[u].rs, mid + 1, r, L, R);
                return res;
            }

            void lazyCreate(int u) {
                if (tr[u] == null) {
                    tr[u] = new Node();
                }
                if (tr[u].ls == 0) {
                    tr[u].ls = ++cnt;
                    tr[tr[u].ls] = new Node();
                }
                if (tr[u].rs == 0) {
                    tr[u].rs = ++cnt;
                    tr[tr[u].rs] = new Node();
                }

            }


            void pushup(int u) {
                tr[u].val = tr[tr[u].ls].val + tr[tr[u].rs].val;
            }

            void pushdown(int u, int len) {
                tr[tr[u].ls].lazy += tr[u].lazy;
                tr[tr[u].rs].lazy += tr[u].lazy;
                tr[tr[u].ls].val += (len - len / 2) * tr[u].lazy;
                tr[tr[u].rs].val += len / 2 * tr[u].lazy;
                tr[u].lazy = 0;
            }


            public MyCalendar() {

            }

            public boolean book(int start, int end) {
                int res = query(1, 1, N + 1, start + 1, end);
                if (res > 0) return false;
                update(1, 1, N + 1, start + 1, end, 1);
                return true;
            }
        }
```



### 方法2：TreeMap

```java
class MyCalendar {

    TreeMap<Integer,Integer> tm;

    public MyCalendar() {
        tm =new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer prev = tm.floorKey(start);
        Integer next = tm.ceilingKey(start);
        if( ( prev== null || tm.get(prev) <= start) && (next ==null || end <= next) ){
            tm.put(start,end);
            return true;
        }
        return false;
    }
}

```

### 方法3：TreeSet

```java
        class MyCalendar {

            TreeSet<int[]> ts;

            public MyCalendar() {
                //按区间的左边界从小到大排序
                ts = new TreeSet<>((a, b) -> a[0] - b[0]);
            }

            public boolean book(int start, int end) {
                int[] prev = ts.floor(new int[]{start, end});
                int[] next = ts.ceiling(new int[]{start, end});
                if ((prev == null || prev[1] <= start) && (next == null || end <= next[0])) {
                    ts.add(new int[]{start, end});
                    return true;
                }
                return false;
            }
        }
```

#### 方法4：建树

```java
        static class MyCalendar {

            class Node {
                int start, end;//区间
                Node leftNode, rightNode;//左右孩子节点

                public Node(int s, int e) {
                    start = s;
                    end = e;
                }
            }

            Node root;


            public MyCalendar() {
                root = new Node(0, 0);
            }

            //每次从根节点去找
            public boolean book(int start, int end) {
                return update(root, start, end);
            }


            /**
             * @param root  树的根节点
             * @param start 待插入的区间的左端点
             * @param end   待插入的区间的右端点
             * @return
             */
            private boolean update(Node root, int start, int end) {
                //case1:
                //[start...end]
                //              [root_start.....root.end]
                //case2:
                //                                        [start...end]
                //              [root_start.....root.end]
                //case3.1:
                //                         [start...end]
                //              [root_start.....root.end]
                //case3.2:
                //          [start...end]
                //              [root_start.....root.end]
                Node cur = root;
                while (true) {
                    if (end <= cur.start) {
                        if (cur.leftNode == null) {
                            cur.leftNode = new Node(start, end);
                            return true;
                        }
                        cur = cur.leftNode;
                    } else if (start >= cur.end) {
                        if (cur.rightNode == null) {
                            cur.rightNode = new Node(start, end);
                            return true;
                        }
                        cur = cur.rightNode;
                    } else {
                        return false;
                    }
                }
            }
        }
```





## [731. 我的日程安排表 II](https://leetcode.cn/problems/my-calendar-ii/)

### 方法1：暴力



![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220521110550.png)



```java
static class MyCalendarTwo {

    List<int[]> calendar;//维护是当前的预订区间
    List<int[]> overlaps;//维护的重叠的区间也就是说2个calendar区间中都出现了这个区间


    public MyCalendarTwo() {
        calendar = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        //overlaps的区间与[start,end]有交集，说明当前的是三重预订，返回false
        for (int[] item : overlaps) {
            if (start < item[1] && end > item[0]) return false;
        }
        //遍历calendar 找重叠区间
        for (int[] item : calendar) {
            if (start < item[1] && end > item[0]) {
                overlaps.add(new int[]{Math.max(start, item[0]), Math.min(item[1], end)});
            }
        }

        calendar.add(new int[]{start, end});
        return true;
    }
}
```







### 方法2：TreeMap

> 有点差分的思想

```java
static class MyCalendarTwo {
    TreeMap<Integer, Integer> map;


    public MyCalendarTwo() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        //思路有点类似差分数组，相当于[start,end)这个区间内的元素出现了一次，都+1
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int count = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            count += e.getValue();
            //大于等于3次的预订次数的区间，需要恢复
            if (count >= 3) {
                map.put(start, map.getOrDefault(start, 0) - 1);
                if (map.get(start) == 0) {
                    map.remove(start);//这一步不移除的也不影响程序正确性
                }
                map.put(end, map.getOrDefault(end, 0) + 1);
                if (map.get(end) == 0) {
                    map.remove(end);
                }
                return false;
            }
        }
        return true;
    }
}
```



### 方法3：动态开点+懒标记线段树

```java
class MyCalendarTwo {

    //然一个比较实用的估点方式可以「尽可能的多开点数」，利用题目给定的空间上界和我们创建的自定义类
    // （结构体）的大小，尽可能的多开（ Java 的 128M 可以开到 5 * 10^6 以上）。

    class Node {
        int ls, rs;//分别代表当前节点的左右子节点在线段树数组 tr 中的下标
        int lazy;//懒标记
        int maxx;//当前区间的最大值
    }

    int N = (int) 1e9 + 10;
    int cnt = 1;//动态开点的下标
    int M = 120010;
    Node[] tr = new Node[M];

    //l,r 为当前节点存储的区间 L,R表示要修改的前，这个区间不会变，设置成大写
    void update(int u, int l, int r, int L, int R, int v) {
        //[l,r]区间被[L,R]覆盖
        if (L <= l && r <= R) {
            tr[u].maxx += v;
            tr[u].lazy += v;
            return;
        }
        lazyCreate(u);
        pushdown(u);
        int mid = l + r >> 1;
        if (L <= mid) update(tr[u].ls, l, mid, L, R, v);
        if (R > mid) update(tr[u].rs, mid + 1, r, L, R, v);
        pushup(u);
    }

    int query(int u, int l, int r, int L, int R) {
        if (L <= l && r <= R) return tr[u].maxx;
        lazyCreate(u);
        pushdown(u);
        int mid = l + r >> 1;
        int res = 0;
        if (L <= mid) res = Math.max(res, query(tr[u].ls, l, mid, L, R));
        if (R > mid) res = Math.max(res, query(tr[u].rs, mid + 1, r, L, R));
        return res;
    }

    void lazyCreate(int u) {
        if (tr[u] == null) {
            tr[u] = new Node();
        }
        if (tr[u].ls == 0) {
            tr[u].ls = ++cnt;
            tr[tr[u].ls] = new Node();
        }
        if (tr[u].rs == 0) {
            tr[u].rs = ++cnt;
            tr[tr[u].rs] = new Node();
        }

    }


    void pushup(int u) {
        tr[u].maxx = Math.max(tr[tr[u].ls].maxx, tr[tr[u].rs].maxx);
    }

    void pushdown(int u) {
        tr[tr[u].ls].lazy += tr[u].lazy;
        tr[tr[u].rs].lazy += tr[u].lazy;
        tr[tr[u].ls].maxx += tr[u].lazy;
        tr[tr[u].rs].maxx += tr[u].lazy;
        tr[u].lazy = 0;
    }


    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        int res = query(1, 1, N + 1, start + 1, end);
        if (res >= 2) return false;
        update(1, 1, N + 1, start + 1, end, 1);
        return true;
    }
}
```





## [732. 我的日程安排表 III](https://leetcode.cn/problems/my-calendar-iii/)

### 方法1：TreeMap

```java
class MyCalendarThree {

    TreeMap<Integer, Integer> tm;

    public MyCalendarThree() {
        tm = new TreeMap<>();
    }

    public int book(int start, int end) {
        tm.put(start, tm.getOrDefault(start, 0) + 1);
        tm.put(end, tm.getOrDefault(end, 0) - 1);
        int maxx = 0, count = 0;
        for (int t : tm.keySet()) {
            count += tm.get(t);
            maxx = Math.max(maxx, count);
        }
        return maxx;
    }
}
```





### 方法2：线段树

```java
class MyCalendarThree {

    //然一个比较实用的估点方式可以「尽可能的多开点数」，利用题目给定的空间上界和我们创建的自定义类
    // （结构体）的大小，尽可能的多开（ Java 的 128M 可以开到 5 * 10^6 以上）。

    class Node {
        int ls, rs;//分别代表当前节点的左右子节点在线段树数组 tr 中的下标
        int lazy;//懒标记
        int maxx;//当前区间的最大值
    }

    int N = (int) 1e9 + 10;
    int cnt = 1;//动态开点的下标
    int M = 120010;
    Node[] tr = new Node[M];

    //l,r 为当前节点存储的区间 L,R表示要修改的前，这个区间不会变，设置成大写
    void update(int u, int l, int r, int L, int R, int v) {
        //[l,r]区间被[L,R]覆盖
        if (L <= l && r <= R) {
            tr[u].maxx += v;
            tr[u].lazy += v;
            return;
        }
        lazyCreate(u);
        pushdown(u);
        int mid = l + r >> 1;
        if (L <= mid) update(tr[u].ls, l, mid, L, R, v);
        if (R > mid) update(tr[u].rs, mid + 1, r, L, R, v);
        pushup(u);
    }

    int query(int u, int l, int r, int L, int R) {
        if (L <= l && r <= R) return tr[u].maxx;
        lazyCreate(u);
        pushdown(u);
        int mid = l + r >> 1;
        int res = 0;
        if (L <= mid) res = Math.max(res, query(tr[u].ls, l, mid, L, R));
        if (R > mid) res = Math.max(res, query(tr[u].rs, mid + 1, r, L, R));
        return res;
    }

    void lazyCreate(int u) {
        if (tr[u] == null) {
            tr[u] = new Node();
        }
        if (tr[u].ls == 0) {
            tr[u].ls = ++cnt;
            tr[tr[u].ls] = new Node();
        }
        if (tr[u].rs == 0) {
            tr[u].rs = ++cnt;
            tr[tr[u].rs] = new Node();
        }

    }


    void pushup(int u) {
        tr[u].maxx = Math.max(tr[tr[u].ls].maxx, tr[tr[u].rs].maxx);
    }

    void pushdown(int u) {
        tr[tr[u].ls].lazy += tr[u].lazy;
        tr[tr[u].rs].lazy += tr[u].lazy;
        tr[tr[u].ls].maxx += tr[u].lazy;
        tr[tr[u].rs].maxx += tr[u].lazy;
        tr[u].lazy = 0;
    }


    public MyCalendarThree() {

    }

    public int book(int start, int end) {
        update(1, 1, N + 1, start + 1, end, 1);
        int res = query(1, 1, N + 1, 1, N + 1);
        return res;
    }
}
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





### 一些代码

```java
/*
    动态开点线段树（带懒标记）by Mikasa Mikoto
    step 1：init();
    区间修改，区间查询
    [l, r] 闭区间
*/
using TT = long long;
static const int N = 5e5 + 10;
static const TT M = 1e9 + 10;
//节点定义，根据需要修改
struct Node {
    int l, r;
    TT sum, lazy;
} tr[N] {};
static int idx , root;
//节点初始化，根据需要修改
inline int get_node() {
    ++ idx;
    tr[idx] = {};
    return idx;
}
//上传，根据需要修改,传left, right备用
inline void pushup(Node& u, Node& ls, Node& rs, TT left, TT right) {
    //TT mid = (right - left) / 2 + left;
    u.sum = ls.sum + rs.sum;
}
//下传，根据需要修改
//下传操作函数：
inline void eval(Node& u, TT left, TT right, TT lazy) {
    u.sum += (right - left + 1) * lazy;
    u.lazy += lazy;
}
//下传函数：
inline void pushdown(Node& u, Node& ls, Node& rs, TT left, TT right) {
    if(u.lazy) {
        TT mid = (right - left) / 2 + left; 
        eval(ls, left, mid, u.lazy);
        eval(rs, mid + 1, right, u.lazy);
        u.lazy = 0;
    }
}
inline void pushup(int u, TT left, TT right) {
    pushup(tr[u], tr[tr[u].l], tr[tr[u].r], left, right);
}
inline void pushdown(int u, TT left, TT right) {
    if(!tr[u].l) tr[u].l = get_node();
    if(!tr[u].r) tr[u].r = get_node();
    pushdown(tr[u], tr[tr[u].l], tr[tr[u].r], left, right);
}
//区间修改，当前区间的懒标记表示其所有子节点需要操作的信息（不包括自身）
void modify(int &u, TT l, TT r, TT L, TT R, TT d) {
    if(!u) u = get_node();
    if(r < L || l > R) return;
    if(l >= L && r <= R) {
        eval(tr[u], l, r, d);
        return;
    }
    pushdown(u, l ,r);
    TT mid = (r - l) / 2 + l;
    if(L <= mid) modify(tr[u].l, l, mid, L, R, d);
    if(R > mid) modify(tr[u].r, mid + 1, r, L, R, d);
    pushup(u, l, r);
}
//区间查询，返Node类型，通常不需要改
Node query(int u, TT l, TT r, TT L, TT R) {
    if(r < L || l > R || !u) return tr[0];
    if(l >= L && r <= R) return tr[u]; 
    Node res;
    pushdown(u, l, r);
    TT mid = (r - l) / 2 + l;
    if(R <= mid) res = query(tr[u].l, l, mid, L, R);
    else if(L > mid) res = query(tr[u].r, mid + 1, r, L, R);
    else {
        auto left = query(tr[u].l, l, mid, L, R), right = query(tr[u].r, mid + 1, r, L, R);
        pushup(res, left, right, l, r);
    }
    pushup(u, l, r);
    return res;
}
void init() {
    idx = 0;
    //memset(tr, 0, sizeof tr);
    root = get_node();
}
void modify(TT l, TT r, TT d) {modify(root, 0, M, l, r, d);}
Node query(TT l, TT r) {return query(root, 0, M, l, r);}

```

- 

```java
#include<bits/stdc++.h>
using namespace std;
long long n,m;//n:长度 m: 询问 
long long a[500001];
struct Tree{
    long long l,r;//l:左节点 r:右节点 
    long long v,lazy;//v:当前节点的值 lazy:懒标记，记录改变的值，递归传值 
}t[2000001];
inline long long read(){
    long long f=1,outt=0;char a=getchar();
    while(a>'9'||a<'0'){if(a=='-')f=-1;a=getchar();}
    while(a<='9'&&a>='0'){outt*=10;outt+=a-'0';a=getchar();}
    return f*outt;
}//读入优化 
inline void eval(long long p,long long k){
    t[p].lazy+=k;
    t[p].v+=k*(t[p].r-t[p].l+1);
} 
inline void pushdown(long long p){
    eval(p*2,t[p].lazy);
    eval(p*2+1,t[p].lazy);
    t[p].lazy=0;
}
void build(int p,int l,int r){
    t[p].l=l;t[p].r=r;
    if(l==r){
        t[p].v=a[l];return;
    }
    long long mid=(l+r)/2;//中点 
    build(p*2,l,mid);
    build(p*2+1,mid+1,r);
    t[p].v=t[p*2].v+t[p*2+1].v;
}
long long query(long long p,long long l){
    if(t[p].l==l&&t[p].r==l)return t[p].v;
    pushdown(p);
    long long mid=(t[p].l+t[p].r)/2;
    if(l<=mid)return query(p*2,l);
    if(l>mid)return query(p*2+1,l);
}
void update(long long p,long long l,long long r,long long v){
    if(t[p].l>=l&&t[p].r<=r){
        t[p].v+=v*(t[p].r-t[p].l+1);
        t[p].lazy+=v;
        return;
    }
    pushdown(p);
    long long mid=(t[p].r+t[p].l)/2;
    if(l<=mid)update(p*2,l,r,v); 
    if(r>mid) update(p*2+1,l,r,v);
    t[p].v=t[p*2].v+t[p*2+1].v;
}
void change(long long p,int x,int v){//单点修改，不必在意，是区间修改的子问题，连标记都不用(而且本题不需要)
    if(t[p].l==t[p].r){
        t[p].v+=v;return;
    }
    int mid=(t[p].r+t[p].l)/2;
    if(x<=mid)change(p*2,x,v);
    else change(p*2+1,x,v);
    t[p].v=t[p*2].v+t[p*2+1].v;
}
int main(){
    n=read();m=read();//读入 
    for(int i=1;i<=n;i++)
        a[i]=read();
    js(1,1,n);//建树 
    for(int i=1;i<=m;i++){
        long long pd=read();
        if(pd==2){
            long long ll=read();
            printf("%lld\n",query(1,ll));//查询ll的值 
        }
        else 
        if(pd==1){
            long long ll=read(),rr=read(),x=read();
            update(1,ll,rr,x);//修改从ll到rr的值加上x 
        }
        else
        if(pd==3){
            int k=read(),y=read();
            change(1,k,y);
        }
    }
    return 0; 
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
- [P3372 【模板】线段树 1 题解](https://www.luogu.com.cn/problem/solution/P3372)

