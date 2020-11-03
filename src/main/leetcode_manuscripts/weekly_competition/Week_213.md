



### [1640. 能否连接形成数组](https://leetcode-cn.com/problems/check-array-formation-through-concatenation/)



### [1641. 统计字典序元音字符串的数目](https://leetcode-cn.com/problems/count-sorted-vowel-strings/)



### [1642. 可以到达的最远建筑](https://leetcode-cn.com/problems/furthest-building-you-can-reach/)





### [1643. 第 K 条最小指令](https://leetcode-cn.com/problems/kth-smallest-instructions/)

```java
        public String kthSmallestPath(int[] destination, int k) {
            int C = destination[1], R = destination[0]; //列 行
            int h = C, v = R; //注意顺序，h取得C
            int[][] dp = new int[h + v][h]; //dp[i][j]表示从i中挑选j个数，j<=i 否则没有意义
            dp[0][0] = 1;// 0!/[0!*(0-0)!] = 1
            for (int i = 1; i < h + v; i++) {
                dp[i][0] = 1;//i!/[i!*0!] =1
                for (int j = 1; j <= i && j < h; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];//C(n, m) = C(n-1, m) + C(n-1, m-1)
                }
            }
//            PrintUtils.printMatrix(dp);
            StringBuilder sb = new StringBuilder();
            while (h > 0 && v > 0) {
                int mid = dp[h + v - 1][h - 1]; //假定高位为H， 那么就从剩下的位数中 h+v-1中选 h-1个H
                if (k > mid) { //选上所有的H 都不能到K，那就跳过本轮
                    sb.append("V");
                    v--;
                    k -= mid; //跳过的数更新k
                } else {
                    sb.append("H");
                    h--;
                }
            }
            if (h == 0) for (int i = 0; i < v; i++) sb.append("V");//h最先没的，说明v还有，补充"V"
            if (v == 0) for (int j = 0; j < h; j++) sb.append("H");//v最先没的，说明h还有，补充"H"
            return sb.toString();
        }
```

