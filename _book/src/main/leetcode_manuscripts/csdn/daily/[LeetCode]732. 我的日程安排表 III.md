## [LeetCode]732. 我的日程安排表 III





## 题目

[732. 我的日程安排表 III](https://leetcode.cn/problems/my-calendar-iii/)

```java
732. 我的日程安排表 III
当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。

给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。

实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。

MyCalendarThree() 初始化对象。
int book(int start, int end) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
 

示例：

输入：
["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
输出：
[null, 1, 1, 2, 3, 3, 3]

解释：
MyCalendarThree myCalendarThree = new MyCalendarThree();
myCalendarThree.book(10, 20); // 返回 1 ，第一个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
myCalendarThree.book(50, 60); // 返回 1 ，第二个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
myCalendarThree.book(10, 40); // 返回 2 ，第三个日程安排 [10, 40) 与第一个日程安排相交，所以最大 k 次预订是 2 次预订。
myCalendarThree.book(5, 15); // 返回 3 ，剩下的日程安排的最大 k 次预订是 3 次预订。
myCalendarThree.book(5, 10); // 返回 3
myCalendarThree.book(25, 55); // 返回 3
 

提示：

0 <= start < end <= 109
每个测试用例，调用 book 函数最多不超过 400次
```







## 解法

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

