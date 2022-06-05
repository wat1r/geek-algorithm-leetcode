



### 题目

[478. 在圆内随机生成点](https://leetcode.cn/problems/generate-random-point-in-a-circle/)

```java
478. 在圆内随机生成点
给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。

实现 Solution 类:

Solution(double radius, double x_center, double y_center) 用圆的半径 radius 和圆心的位置 (x_center, y_center) 初始化对象
randPoint() 返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
 

示例 1：

输入: 
["Solution","randPoint","randPoint","randPoint"]
[[1.0, 0.0, 0.0], [], [], []]
输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
解释:
Solution solution = new Solution(1.0, 0.0, 0.0);
solution.randPoint ();//返回[-0.02493，-0.38077]
solution.randPoint ();//返回[0.82314,0.38945]
solution.randPoint ();//返回[0.36572,0.17248]
 

提示：

0 < radius <= 108
-107 <= x_center, y_center <= 107
randPoint 最多被调用 3 * 104 次
```



### 解法

### 方法1：拒绝采样

```java
        class Solution {

            double sx, sy;
            double sr;

            public Solution(double radius, double x_center, double y_center) {
                sx = x_center;
                sy = y_center;
                sr = radius;
            }

            public double[] randPoint() {
                Random random = new Random();
                while (true) {
                    double tx = random.nextDouble() * 2 * sr - sr, ty = random.nextDouble() * 2 * sr - sr;
                    if (tx * tx + ty * ty <= sr * sr) {
                        return new double[]{sx + tx, sy + ty};
                    }
                }
            }
        }
```

### 方法2：极坐标

ρ= random ∗r

极坐标的的角度也是随机的 θ = 2∗π∗random

x = x\_center + ρ * cos(θ)

y = y\_center + ρ * sin(θ)

- [Explanation with Graphs why using Math.sqrt()](https://leetcode.com/problems/generate-random-point-in-a-circle/discuss/155650/Explanation-with-Graphs-why-using-Math.sqrt())

```java
        class Solution {

            double sx, sy;
            double sr;


            public Solution(double radius, double x_center, double y_center) {
                sx = x_center;
                sy = y_center;
                sr = radius;
            }

            public double[] randPoint() {
                Random random = new Random();
                double l = Math.sqrt(random.nextDouble()) * sr;
                double d = random.nextDouble() * 2 * Math.PI;
                double tx = sx + l * Math.cos(d);
                double ty = sy + l * Math.sin(d);
                return new double[]{tx, ty};
            }
        }
```







