



### 题目

[1037. 有效的回旋镖](https://leetcode.cn/problems/valid-boomerang/)

```java
1037. 有效的回旋镖
给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。

回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。

 

示例 1：

输入：points = [[1,1],[2,3],[3,2]]
输出：true
示例 2：

输入：points = [[1,1],[2,2],[3,3]]
输出：false
 

提示：

points.length == 3
points[i].length == 2
0 <= xi, yi <= 100
```



### 解法

### 方法1：向量叉乘比较斜率

```java
public boolean isBoomerang(int[][] points) {
    int x0 = points[0][0], y0 = points[0][1];
    int x1 = points[1][0], y1 = points[1][1];
    int x2 = points[2][0], y2 = points[2][1];
    return !((y1 - y0) * (x2 - x0) == (y2 - y0) * (x1 - x0));
}
```

