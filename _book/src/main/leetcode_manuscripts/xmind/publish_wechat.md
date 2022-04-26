





## 图解417太平洋大西洋水流问题(附岛屿问题链接)






> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉，欢评论区提供其他语言的版本**





### 

![](/imgs/leetcode/classify/image-20220427064502883.png)

- 从四个边缘开始反向往内部搜索
- 上下分别与`Pacific`和`Atlantic`接壤
- 左右分别与`Pacific`和`Atlantic`接壤

```java
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int R, C;
        int[][] heights;

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            List<List<Integer>> res = new ArrayList<>();
            R = heights.length;
            C = heights[0].length;
            this.heights = heights;
            boolean[][] visP = new boolean[R][C];
            boolean[][] visA = new boolean[R][C];
            for (int c = 0; c < C; c++) {
                dfs(0, c, visP);
                dfs(R - 1, c, visA);
            }
            for (int r = 0; r < R; r++) {
                dfs(r, 0, visP);
                dfs(r, C - 1, visA);
            }
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (visP[r][c] && visA[r][c]) {
                        res.add(Arrays.asList(r, c));
                    }
                }
            }
            return res;
        }


        public void dfs(int r, int c, boolean[][] vis) {
            vis[r][c] = true;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                //下一个坐标需要满足3个条件
                //1.在区域范围内
                //2.比上一个位置(r,c)的值要大，因为我们从外层逆着水流方向找的
                //3.没有被访问过
                if (inArea(nr, nc) && !vis[nr][nc] && heights[nr][nc] >= heights[r][c]) {
                    dfs(nr, nc, vis);
                }
            }
        }

        private boolean inArea(int r, int c) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
```

### 岛屿问题合辑

- [岛屿问题之岛屿的数量Eighty-eight Butterfly](https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-wen-ti-zhi-dao-yu-de-shu-liang-eighty-eight/)

- [ 岛屿问题之最大人工岛Danaus Genutia](https://leetcode-cn.com/problems/making-a-large-island/solution/dao-yu-wen-ti-zhi-zui-da-ren-gong-dao-danaus-genut/)

- [岛屿问题之岛屿的周长面积Morpho Cypris Aphrodite](https://leetcode-cn.com/problems/island-perimeter/solution/dao-yu-wen-ti-zhi-dao-yu-de-zhou-chang-mian-ji-mor/)

- [岛屿问题之岛屿的周长面积Morpho Cypris Aphrodite](https://leetcode-cn.com/problems/island-perimeter/solution/dao-yu-wen-ti-zhi-dao-yu-de-zhou-chang-mian-ji-mor/)

- [岛屿问题之不同岛屿的数量Monarch Butterfly](https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-wen-ti-zhi-bu-tong-dao-yu-de-shu-liang-mona/)

- [岛屿问题之被围绕的区域[Cicada]](https://leetcode-cn.com/problems/surrounded-regions/solution/dao-yu-wen-ti-zhi-bei-wei-rao-de-qu-yu-cicada-by-2/)

- [搜索与图论之FloodFill-统计子岛屿](https://leetcode-cn.com/problems/count-sub-islands/)

### 搜索与图论问题合辑

1.[搜索与图论之FloodFill](https://blog.csdn.net/wat1r/article/details/113702607)

2.[搜索与图论之最短路](https://blog.csdn.net/wat1r/article/details/113729703)

3.[搜索与图论之欧拉回路与欧拉路径](https://blog.csdn.net/wat1r/article/details/113853334)

4.[搜索与图论之拓扑排序](https://blog.csdn.net/wat1r/article/details/113871449)

### 更多阅读

- 算法题解的链接地址： [gitbook](https://cnwangzhou.gitbook.io/algorithm/)

- [个人主页【阿飞算法】](https://blog.csdn.net/wat1r/article/details/117533156) 加我好友，进群一起交流~
