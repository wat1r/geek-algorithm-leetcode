# 模拟

> 





## [36. 有效的数独](https://leetcode-cn.com/problems/valid-sudoku/)

### 方法1

```java
public boolean isValidSudoku(char[][] board) {
    int R = board.length, C = board[0].length;
    int[] rows = new int[R + 1], cols = new int[C + 1], blocks = new int[10];
    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
            char cur = board[r][c];
            if (cur == '.') continue;
            int t = cur - '0';
            int idx = r / 3 * 3 + c / 3;
            if (((rows[r] >> t) & 1) == 1 || ((cols[c] >> t) & 1) == 1 || ((blocks[idx] >> t) & 1) == 1)
                return false;
            rows[r] |= (1 << t);
            cols[c] |= (1 << t);
            blocks[idx] |= (1 << t);
        }
    }

    return true;
}
```

另外一种写法

```java
      public boolean isValidSudoku(char[][] board) {
            int R = board.length, C = board[0].length;
            boolean[][] rows = new boolean[R][C];
            boolean[][] cols = new boolean[R][C];
            boolean[][] blocks = new boolean[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    char cur = board[r][c];
                    if (cur == '.') continue;
                    int t = cur - '1';//1变成0 方便下面的下标，否则要改上面是array
                    int idx = r / 3 * 3 + c / 3;
                    //这种相当于是给每一行/列/块 安排了9个数字的位置排排好，
                    if (rows[r][t] || cols[c][t] || blocks[idx][t]) return false;
                    rows[r][t] = cols[c][t] = blocks[idx][t] = true;
                }
            }
            return true;
        }
```

### 方法2

```java
       public boolean isValidSudoku(char[][] board) {
            int R = board.length, C = board[0].length;
            //行 列 块的map k:当前的行/列/块 的索引 [0-8] v:当前的行/列/块 包含的数字
            Map<Integer, Set<Integer>> rowsMap = new HashMap<>();
            Map<Integer, Set<Integer>> colsMap = new HashMap<>();
            Map<Integer, Set<Integer>> blocksMap = new HashMap<>();
            for (int i = 0; i < 9; i++) {
                rowsMap.put(i, new HashSet<>());
                colsMap.put(i, new HashSet<>());
                blocksMap.put(i, new HashSet<>());
            }
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    char cur = board[r][c];
                    if (cur == '.') continue;
                    int t = cur - '0';//当前这个点是个数字
                    int idx = r / 3 * 3 + c / 3;//小方块的索引与坐标的转换公式
                    //如果当前的行/列/块 含有重复的数字，不是一个有效的数独
                    if (rowsMap.get(r).contains(t) || colsMap.get(c).contains(t) || blocksMap.get(idx).contains(t))
                        return false;
                    rowsMap.get(r).add(t);
                    colsMap.get(c).add(t);
                    blocksMap.get(idx).add(t);
                }
            }
            return true;
        }

```













## [2028. 找出缺失的观测数据](https://leetcode-cn.com/problems/find-missing-observations/)

```java
        public int[] missingRolls(int[] rolls, int mean, int n) {
            int m = rolls.length;
            int tot = (n + m) * mean;
            for (int x : rolls) tot -= x;
            if (tot < n || tot > 6 * n) return new int[]{};
            int[] res = new int[n];
            Arrays.fill(res, tot / n);
            if (tot / n * n < tot) {
                int diff = tot - (tot / n * n);
                int idx = 0;
                while (diff-- > 0) res[idx++]++;
            }
            return res;
        }
```

