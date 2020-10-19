## Week_211











#### 1626. 无矛盾的最佳球队

> 贪心+dp （类似300题最长上升子序列）

#### 思路：

- 将分数与年龄的数组转成二维数组`arr`, [0] 存年龄，[1]存分数
- 将`arr`排序，按年龄从小到大排序，如果年龄相同，再按分数从小到大排序
- 初始化一个`dp`数组，`dp[i]`表示以`i`结尾的球员结束，所能拿到的的最大的分数
- 贪心：从当前`i`前一个`i-1`开始，向前遍历，找到`dp[i]`值最大的，里面的一层`for loop`结束后，加上`arr[i][1]`的值，记录遍历过程的的最大值`max`，最后返回这个`max`

```java
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{ages[i], scores[i]};
        }
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        int max = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j][1] <= arr[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += arr[i][1];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
```

#### 另外一种方法

```java
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) arr[i] = new int[]{ages[i], scores[i]};
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i][1];
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j][1] <= arr[i][1]) dp[i] = Math.max(dp[i], dp[j] + arr[i][1]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

```

