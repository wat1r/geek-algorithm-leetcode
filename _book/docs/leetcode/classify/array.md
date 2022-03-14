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

## [599. 两个列表的最小索引总和](https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/)

```java
public String[] findRestaurant(String[] list1, String[] list2) {
    //map k:list1的string v:string的索引，因为题目中没有指出来list1的string是否有重复，忽略了重复的情况
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < list1.length; i++) map.put(list1[i], i);
    //结果集list
    List<String> list = new ArrayList<>();
    //索引和的最小值，根据数据范围取
    int minn = 2005;
    //遍历list2
    for (int i = 0; i < list2.length; i++) {
        //list2中有这个string
        if (map.containsKey(list2[i])) {
            int j = map.get(list2[i]);
            //case1:比minn还要小，重新更新
            if (i + j < minn) {
                minn = i + j;
                list.clear();
                list.add(list2[i]);
            } else if (i + j == minn) {//case2:和minn相等，添加
                list.add(list2[i]);
            }
        }
    }
    //收集结果
    String[] res = new String[list.size()];
    for (int i = 0; i < res.length; i++) res[i] = list.get(i);
    return res;
}
```