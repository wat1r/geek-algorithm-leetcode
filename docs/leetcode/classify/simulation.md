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







## [168. Excel表列名称](https://leetcode-cn.com/problems/excel-sheet-column-title/)

```java
public String convertToTitle(int n) {

    StringBuilder res = new StringBuilder();
    while (n > 0) {
        n--;//从1开始的，需要偏离1
        res.append((char) ('A' + (n % 26)));
        n /= 26;
    }
    return res.reverse().toString();
}
```





## [171. Excel 表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/)

```java
public int titleToNumber(String s) {
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
        int c = s.charAt(i) - 'A' + 1;
        res = res * 26 + c;
    }
    return res;
}
```



## [468. 验证IP地址](https://leetcode.cn/problems/validate-ip-address/)

```java
        //case -> "2001:0db8:85a3:0:0:8A2E:0370:7334:"
        //case -> "1.1.1.1."
        //case -> "12..33.4"
        //case -> ""
        //case -> "192.0.0.1"
	public String validIPAddress(String queryIP) {
            String[] arr = queryIP.split("\\.");
            if (arr.length > 1) {
                if (queryIP.endsWith(".")) return "Neither";
                if (isIPv4(queryIP)) {
                    return "IPv4";
                }
            } else {
                if (queryIP.endsWith(":")) return "Neither";
                if (isIPv6(queryIP)) {
                    return "IPv6";
                }
            }
            return "Neither";
        }


        private boolean isIPv6(String ip) {
            String[] arr = ip.split(":");
            if (arr.length != 8) return false;
            for (String a : arr) {
                if (a.length() == 0 || a.length() > 4) return false;
                if (!checkIPv6Segment(a)) return false;
            }
            return true;
        }


        private boolean checkIPv6Segment(String s) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!Character.isDigit(c) && !(c >= 'a' && c <= 'f') && !(c >= 'A' && c <= 'F')) {
                    return false;
                }
            }
            return true;
        }


        private boolean isIPv4(String ip) {
            String[] arr = ip.split("\\.");
            if (arr.length != 4) return false;
            for (String a : arr) {
                if (a.length() == 0 || a.length() > 3) return false;
                if (a.startsWith("0") && a.length() > 1) return false;
                if (!isDigit(a)) return false;
                if (Integer.parseInt(a) >= 256 || Integer.parseInt(a) < 0) return false;
            }
            return true;
        }

        private boolean isDigit(String s) {
            for (int i = 0; i < s.length(); i++) {
                if (!((s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9))) {
                    return false;
                }
            }
            return true;
        }
```







## [796. 旋转字符串](https://leetcode-cn.com/problems/rotate-string/)

### 方法1：模拟+库函数

- 每次模拟一次翻转，判断是否相同

```java
        public boolean rotateString(String s, String goal) {
            String t = "";
            for (int i = 0; i < s.length(); i++) {
                t = s.substring(i + 1) + s.substring(0, i + 1);
                if (t.equals(goal)) return true;
            }
            return false;
        }
```





### 方法2：翻转点

- 按翻转点的下标索引计算

```java
       public boolean rotateString(String s, String goal) {
            if (s.length() != goal.length()) return false;
            for (int i = 0; i < s.length(); i++) {
                if (rotate(s, goal, i)) return true;
            }
            return false;
        }
        

        private boolean rotate(String s, String goal, int rotate) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != goal.charAt((i + rotate) % goal.length())) return false;
            }
            return true;
        }
```





### 方法3：一行

![](/imgs/leetcode/classify/image-20220407073202102.png)



```java
        public boolean rotateString(String s, String goal) {
            return s.length() == goal.length() && (s + s).contains(goal);
        }
```





## [953. 验证外星语词典](https://leetcode.cn/problems/verifying-an-alien-dictionary/)

### 方法1

```java
public boolean isAlienSorted(String[] words, String order) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < order.length(); i++) {
        map.put(order.charAt(i), i);
    }
    String[] targets = new String[words.length];
    int idx = 0;
    for (String word : words) {
        String t = "";
        for (int i = 0; i < word.length(); i++) {
            t += (char) (map.get(word.charAt(i)) + 'a');
        }
        targets[idx++] = t;
    }
    for (int i = 0; i < targets.length - 1; i++) {
        if (targets[i].compareTo(targets[i + 1]) > 0) {
            return false;
        }
    }
    return true;
}
```

### 方法2

```java
public boolean isAlienSorted(String[] words, String order) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < order.length(); i++) {
        map.put(order.charAt(i), i);
    }
    for (int i = 0; i < words.length - 1; i++) {
        String prev = words[i], cur = words[i + 1];
        boolean nonEqual = false;
        int len = Math.min(prev.length(), cur.length());
        for (int j = 0; j < len; j++) {
            if (map.get(prev.charAt(j)) > map.get(cur.charAt(j))) {
                return false;
            } else if (map.get(prev.charAt(j)) < map.get(cur.charAt(j))) {
                nonEqual = true;
                break;
            }
        }
        if (!nonEqual && prev.length() > cur.length()) {
            return false;
        }
    }
    return true;
}
```

### 方法3

```java
public boolean isAlienSorted(String[] words, String order) {
            int[] dict = new int[26];
            for (int i = 0; i < order.length(); i++) {
                dict[order.charAt(i) - 'a'] = i;
            }
            String[] mirror = words.clone();
            Arrays.sort(mirror, (a, b) -> {
                int m = a.length(), n = b.length();
                int i = 0, j = 0;
                while (i < m && j < n) {
                    char ac = a.charAt(i), bc = b.charAt(j);
                    if (ac != bc) return dict[ac - 'a'] - dict[bc - 'a'];
                    i++;
                    j++;
                }
                if (i < m) return 1;
                if (j < n) return -1;
                return 0;
            });
            for (int i = 0; i < words.length; i++) {
                if (!mirror[i].equals(words[i])) return false;
            }
            return true;
        }
```



### 方法4

```java
int[] dict = new int[26];

int check(String a, String b) {
    int m = a.length(), n = b.length();
    int i = 0, j = 0;
    while (i < m && j < n) {
        int ac = a.charAt(i) - 'a', bc = b.charAt(j) - 'a';
        if (ac != bc) return dict[ac] - dict[bc];
        i++;
        j++;
    }
    if (i < m) return 1;
    if (j < n) return -1;
    return 0;
}

public boolean isAlienSorted(String[] words, String order) {
    for (int i = 0; i < 26; i++) dict[order.charAt(i) - 'a'] = i;
    for (int i = 1; i < words.length; i++) {
        if (check(words[i - 1], words[i]) > 0) return false;
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



## [2194. Excel 表中某个范围内的单元格](https://leetcode-cn.com/problems/cells-in-a-range-on-an-excel-sheet/)



```java
public List<String> cellsInRange(String s) {
    List<String> res = new ArrayList<>();
    String[] arr = s.split(":");
    String start = arr[0], end = arr[1];
    for (char c = start.charAt(0); c <= end.charAt(0); c++) {
        for (char i = start.charAt(1); i <= end.charAt(1); i++) {
            res.add(c + "" + i);
        }
    }
    return res;
}
```





## [面试题 17.11. 单词距离](https://leetcode.cn/problems/find-closest-lcci/)

```java
    public int findClosest(String[] words, String word1, String word2) {
        int j =0,k  = 0;
        int dist =100010;
        for(int i= 0; i< words.length;i++){
            if(word1.equals(words[i])){
                j=i;
            }else if(word2.equals(words[i])){
                k=i;
            }
            if(j>0 && k >0){
                dist = Math.min(dist,Math.abs(j-k));
            }
        }
        return dist;
    }
```

