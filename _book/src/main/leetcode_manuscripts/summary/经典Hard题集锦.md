# 经典Hard题集锦





## 数独





## N皇后I

```java
        List<List<String>> res = new ArrayList<>();
        int n;

        public List<List<String>> solveNQueens(int n) {
            this.n = n;
            int[] queens = new int[n];
            Arrays.fill(queens, -1);
            Set<Integer> cols = new HashSet<>();
            Set<Integer> dia1 = new HashSet<>();
            Set<Integer> dia2 = new HashSet<>();
            backtrack(queens, 0, cols, dia1, dia2);
            return res;
        }


        public void backtrack(int[] queens, int row, Set<Integer> cols, Set<Integer> dia1, Set<Integer> dia2) {
            if (row == n) {
                res.add(build(queens));
                return;
            }
            for (int i = 0; i < n; i++) {
                if (cols.contains(i)) continue;
                if (dia1.contains(row - i)) continue;
                if (dia2.contains(row + i)) continue;
                queens[row] = i;
                cols.add(i);
                dia1.add(row - i);
                dia2.add(row + i);
                backtrack(queens, row + 1, cols, dia1, dia2);
                queens[row] = -1;
                cols.remove(i);
                dia1.remove(row - i);
                dia2.remove(row + i);
            }
        }

        private List<String> build(int[] queens) {
            List<String> board = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[queens[i]] = 'Q';
                board.add(new String(row));
            }
            return board;
        }

```





## N皇后II

```java
        int n;

        public int totalNQueens(int n) {
            this.n = n;
            Set<Integer> cols = new HashSet<>();
            Set<Integer> dia1 = new HashSet<>();
            Set<Integer> dia2 = new HashSet<>();
            return backtrack(0, cols, dia1, dia2);
        }


        public int backtrack(int row, Set<Integer> cols, Set<Integer> dia1, Set<Integer> dia2) {
            if (row == n) return 1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (cols.contains(i)) continue;
                if (dia1.contains(row - i)) continue;
                if (dia2.contains(row + i)) continue;
                cols.add(i);
                dia1.add(row - i);
                dia2.add(row + i);
                count += backtrack(row + 1, cols, dia1, dia2);
                cols.remove(i);
                dia1.remove(row - i);
                dia2.remove(row + i);
            }
            return count;
        }
```













## 俄罗斯信封套套娃



## 编辑距离



## 接雨水





## 高楼丢鸡蛋





## LFU





- [1246. 删除回文子数组](https://leetcode-cn.com/problems/palindrome-removal/)



