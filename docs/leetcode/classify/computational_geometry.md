> 





## [883. 三维形体投影面积](https://leetcode-cn.com/problems/projection-area-of-3d-shapes/)



#### 需要明白的预设：

- 每个cube的体积是`1✖️1✖️1`
- `grid`的长度和宽度相等，构成正方体

#### 举例

![](/imgs/leetcode/classify/image-20220426143327599.png)

#### 

如上图，拿`[[2,2,2],[2,1,2],[2,2,2]] `举例

- 在`[0,0]`点，高度为`2`，在在`[0,2]`点，高度为`2`，在`[0,1]点，高度为`2`

- 在`[1,0]`点，高度为`2`，在在`[1,1]`点，高度为`1`，在`[1,2]`点，高度为`2`

- 在`[2,0]`点，高度为`2`，在在`[2,1]`点，高度为`2`，在`[2,2]`点，高度为`2`



#### 解法

- `xy` 平面的投影面积等于网格上非零数值的数目；
- `yz` 平面的投影面积等于网格上每一行最大数值之和；
- `zx` 平面的投影面积等于网格上每一列最大数值之和。

```java
        public int projectionArea(int[][] grid) {
            int xy = 0, yz = 0, zx = 0;
            int R = grid.length, C = grid[0].length;
            for (int r = 0; r < R; r++) {
                int yzHeight = 0, zxHeight = 0;
                for (int c = 0; c < C; c++) {
                    xy += (grid[r][c] != 0 ? 1 : 0);//有多少不是0的格子就有多大的面积
                    yzHeight = Math.max(yzHeight, grid[r][c]);
                    zxHeight = Math.max(zxHeight, grid[c][r]);//调转行列坐标
                }
                yz += yzHeight;
                zx += zxHeight;
            }
            return xy + yz + zx;
        }
```







### Reference

- [数学：凸包算法详解](https://www.cnblogs.com/aiguona/p/7232243.html)

- [计算几何入门3 凸包与旋转卡壳算法](https://www.bilibili.com/video/BV195411Z7zE?spm_id_from=333.337.search-card.all.click)

- [凸包](https://oi-wiki.org/geometry/convex-hull/)

