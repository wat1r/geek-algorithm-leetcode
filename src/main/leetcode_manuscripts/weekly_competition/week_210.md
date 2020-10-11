## week_210

### 5535. 括号的最大嵌套深度

#### 思路

- 这种一一对应的符号 `(` 与`)` 用栈处理比较常见，栈内峰值的时候有多少`(`，深度就有多大，如`8*((1*(5+6))*(8/6))`这个用例

```java
    public int maxDepth(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
                res = Math.max(res, stack.size());
            } else if (c == ')') {
                stack.pop();
            }
        }
        return res;
    }
```

### 5536. 最大网络秩

#### 思路

- `boolean[][] visited = new boolean[n][n]`记录`s`到`e`是否是直接相连的，方便下面进行-1，注意题目中的第三个用例，**注意并非所有的城市都需要连接起来。**
- 两层`for loop` 统计其相连的个数，注意部分城市间相连-1

```java
    public int maximalNetworkRank(int n, int[][] roads) {
        if (roads == null || roads.length == 0 || roads[0].length == 0) return 0;
        HashMap<Integer, Integer> graph = new HashMap<>();
        boolean[][] visited = new boolean[n][n];
        for (int[] road : roads) {
            int s = road[0], e = road[1];
            graph.put(s, graph.getOrDefault(s, 0) + 1);
            graph.put(e, graph.getOrDefault(e, 0) + 1);
            visited[s][e] = true;
            visited[e][s] = true;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int seg = 0;
                seg += graph.getOrDefault(i, 0);
                seg += graph.getOrDefault(j, 0);
                if (visited[i][j]) {
                    seg--;
                }
                System.out.printf("i:%d,j:%d,seg:%d\n", i, j, seg);
                res = Math.max(res, seg);
            }
        }
        return res;
    }
```

### 5537. 分割两个字符串得到回文串

![5537](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\weekly_competition\w_210.assets\5537-1602405225339.jpg)

```java
    public boolean checkPalindromeFormation(String a, String b) {
        //Situation 1 or  Situation 2
        if (validate(a) || validate(b)) return true;
        int n = a.length();
        int l = 0;
        //Situation 3
        while (a.charAt(l) == b.charAt(n - 1 - l)) l++;
        if (validate(a.substring(l, n - l)) || validate(b.substring(l, n - l))) return true;
        l = 0;
        //Situation 4
        while (b.charAt(l) == a.charAt(n - 1 - l)) l++;
        if (validate(b.substring(l, n - l)) || validate(a.substring(l, n - l))) return true;
        return false;
    }
	
	//双指针判断字符串是否是回文
    private boolean validate(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
```

>  超时

```java
/**
 * TLE 50%的用例
 *
 * @param a
 * @param b
 * @return
 */
public boolean checkPalindromeFormation1st(String a, String b) {
    long start = System.currentTimeMillis();
    int n = a.length();
    for (int i = 0; i < n; i++) {
        String aPrefix = a.substring(0, i);
        String aSuffix = a.substring(i);
        String bPrefix = b.substring(0, i);
        String bSuffix = b.substring(i);
        System.out.printf("aPrefix:%s,aSuffix:%s,bPrefix:%s,bSuffix:%s\n", aPrefix, aSuffix, bPrefix, bSuffix);
        if (validate(aPrefix + bSuffix) || validate(bPrefix + aSuffix)) {
            return true;
        }
    }
    System.out.println(System.currentTimeMillis() - start);
    return false;
}
    private boolean validate(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
```

