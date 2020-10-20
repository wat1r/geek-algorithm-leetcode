## 树形DP之没有上司的舞会

![image-20201020195542551](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\tree\树形DP之没有上司的舞会.assets\image-20201020195542551.png)

### 思路

> 树形DP

状态表示：

- `f[u][0]`表示所有以`u`为根的子树中，不选`u`这个根，所获得的最大快乐指数

- `f[u][1]`表示所有以`u`为根的子树中，选`u`这个根，所获得的最大快乐指数

状态转移：

- 根节点`u`不选时，其直接的子节点可以选可以不选 ：`f[u][0]`= `sum{max(f[son][0],f[son][1])}`
- 根节点`u`选择时，其直接的子节点不能选：`f[u][1] =sum{f[son][0]}`

> 数组表示邻接链表

```java
import java.util.Arrays;
import java.util.Scanner;

class Main {

        static Main main = new Main();

        public static void main(String[] args) {
            main.process();
        }
     
        int n = 6010; //N名员工
        int[] happy = new int[n]; //员工的快乐指数 H
        int[] head = new int[n]; //
        int[] edge = new int[n];//边
        int[] next = new int[n];//
        int idx = 0;
        int[][] f = new int[n][2];
        boolean[] hasFather = new boolean[n];

        private void process() {
            Scanner in = new Scanner(System.in);
            n = in.nextInt();
            for (int i = 1; i <= n; i++) happy[i] = in.nextInt();
            Arrays.fill(head, -1);
            for (int i = 0; i < n - 1; i++) {
                int a = in.nextInt(), b = in.nextInt();
                add(b, a);
                hasFather[a] = true;
            }
            int root = 1;
            while (hasFather[root]) root++;
            dfs(root);
            System.out.println(Math.max(f[root][0], f[root][1]));
        }

        private void dfs(int u) {
            f[u][1] = happy[u];
            for (int i = head[u]; i != -1; i = next[i]) {
                int j = edge[i];
                dfs(j);
                f[u][1] += f[j][0];
                f[u][0] += Math.max(f[j][1], f[j][0]);
            }


        }

        /**
        	数组表示邻接表
         * @param a father
         * @param b son
         */
        private void add(int a, int b) {
            edge[idx] = b;
            next[idx] = head[a];
            head[a] = idx++;
        }

    }
```









### Reference

- [头插法尾插法构造邻接表](https://blog.csdn.net/qq_36345036/article/details/76976157)

- https://www.cnblogs.com/wenruo/p/4680930.html