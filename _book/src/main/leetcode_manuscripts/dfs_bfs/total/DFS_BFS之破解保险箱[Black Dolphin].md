## DFS_BFS之破解保险箱[Black Dolphin]







### 0.背景

> #### 如何在一个最短的串内枚举所有的n位k进制数排列
>
> 其实这个问题是一个在数学中早已被研究透彻的问题了。
> 这种序列称之为 **de Bruijn序列**。

### 方法1:Hierholzer

#### **Hierholzer 算法过程**：

1. 选择任一顶点为起点，遍历所有相邻边。
2. 深度搜索，访问相邻顶点。将经过的边都删除。
3. 如果当前顶点没有相邻边，则将顶点入栈。
4. 栈中的顶点倒序输出，就是从起点出发的欧拉回路。

```java
  Set<Integer> visited = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    int k;
    int highest;


    public String crackSafe(int n, int k) {
        highest = (int) Math.pow(10, n - 1);
        this.k = k;
        dfs(0);
        for (int i = 1; i < n; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    private void dfs(int curr) {
        for (int x = 0; x < k; x++) {
            int next = curr * 10 + x;
            if (!visited.contains(next)) {
                visited.add(next);
                dfs(next % highest);
                sb.append(x);
            }
        }
    }
```









### Reference

- https://zhuanlan.zhihu.com/p/108411618

- https://leetcode-cn.com/problems/cracking-the-safe/solution/cpp-xiang-jie-ti-mu-bei-jing-hierholzer-suan-fa-by/

- https://leetcode-cn.com/problems/cracking-the-safe/solution/zhuan-hua-wei-ou-la-hui-lu-wen-ti-hierholzer-suan-/
- https://leetcode-cn.com/problems/cracking-the-safe/solution/po-jie-bao-xian-xiang-by-leetcode-solution/