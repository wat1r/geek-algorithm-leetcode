## [LeetCode]731. 我的日程安排表 II

## 题目

[731. 我的日程安排表 II](https://leetcode.cn/problems/my-calendar-ii/)

```java
731. 我的日程安排表 II
实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。

MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。

当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。

每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。

请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)

 

示例：

MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(50, 60); // returns true
MyCalendar.book(10, 40); // returns true
MyCalendar.book(5, 15); // returns false
MyCalendar.book(5, 10); // returns true
MyCalendar.book(25, 55); // returns true
解释： 
前两个日程安排可以添加至日历中。 第三个日程安排会导致双重预订，但可以添加至日历中。
第四个日程安排活动（5,15）不能添加至日历中，因为它会导致三重预订。
第五个日程安排（5,10）可以添加至日历中，因为它未使用已经双重预订的时间10。
第六个日程安排（25,55）可以添加至日历中，因为时间 [25,40] 将和第三个日程安排双重预订；
时间 [40,50] 将单独预订，时间 [50,55）将和第二个日程安排双重预订。
 

提示：

每个测试用例，调用 MyCalendar.book 函数最多不超过 1000次。
调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。
```



## 解法

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





> 长按或扫码关注我的公众号:「**阿飞算法**」，一起加油、一起学习进步！

![在这里插入图片描述](https://img-blog.csdnimg.cn/0fadbbfa21be4e3e89fadff7764012cd.png)



