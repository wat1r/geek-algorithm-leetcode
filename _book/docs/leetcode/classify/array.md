# 数组

>



## [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

- 翻转方向和标记已经访问的点，是本题的关键

```java
int R, C;
//右 下 左 上
int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

public List<Integer> spiralOrder(int[][] matrix) {
    R = matrix.length;
    C = matrix[0].length;
    int r = 0, c = 0, d = 0;
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < R * C; i++) {
        res.add(matrix[r][c]);
        matrix[r][c] = -101;//标记已经访问的点
        int nr = r + dirs[d][0], nc = c + dirs[d][1];
        //出边界或者遇到已经访问过点，翻转方向
        if (!inArea(nr, nc) || matrix[nr][nc] == -101) {
            d = (d + 1) % 4;//翻转方向，灵魂
            nr = r + dirs[d][0];
            nc = c + dirs[d][1];
        }
        r = nr;
        c = nc;

    }
    return res;
}
```