## 扫描线

>

## [218. 天际线问题](https://leetcode-cn.com/problems/the-skyline-problem/)











## [1288. 删除被覆盖区间](https://leetcode.cn/problems/remove-covered-intervals/)

```java
public int removeCoveredIntervals(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> {
        if (a[0] == b[0]) return b[1] - a[1];
        return a[0] - b[0];
    });
    int prevEnd = 0, curEnd = 0;
    int res = 0;
    for (int[] t : intervals) {
        curEnd = t[1];
        if (curEnd > prevEnd) {
            res++;
            prevEnd = curEnd;
        }
    }
    return res;
}
```
