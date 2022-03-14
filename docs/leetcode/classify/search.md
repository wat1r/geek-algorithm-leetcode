# 搜索



## [46. 全排列](https://leetcode-cn.com/problems/permutations/)

### 方法1：dfs

```java
   List<List<Integer>> resList = new ArrayList<>();
  public List<List<Integer>> permute(int[] nums) {
       
        if (nums == null || nums.length == 0) return resList;
        List<Integer> levelList = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) levelList.add(num);
        backtrace(levelList, n, 0);
        return resList;
    }

    private void backtrace( List<Integer> levelList, int n, int index) {
        if (index == n) {
            resList.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = index; i < n; i++) {
            Collections.swap(levelList, index, i);
            backtrace( levelList, n, index + 1);
            Collections.swap(levelList, index, i);
        }
    }
```



## [47. 全排列 II](https://leetcode-cn.com/problems/permutations-ii/)

### 方法1:DFS

![](/imgs/leetcode/classify/image-20220314222903235.png)

```java
List<List<Integer>> res = new ArrayList<>();

public List<List<Integer>> permuteUnique(int[] nums) {
    if (nums == null || nums.length == 0) return res;
    Arrays.sort(nums);
    dfs(new ArrayList<>(), nums, new boolean[nums.length]);
    return res;
}

private void dfs(List<Integer> sub, int[] nums, boolean[] vis) {
    if (sub.size() == nums.length) {
        sub.forEach(System.out::print);
        System.out.println();
        res.add(new ArrayList<>(sub));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if (vis[i]) continue;
        //!vis[i-1] 防止{0,3,3,3}这种排列
        if (i > 0 && nums[i - 1] == nums[i] && !vis[i - 1]) continue;
        vis[i] = true;
        sub.add(nums[i]);
        dfs(sub, nums, vis);
        sub.remove(sub.size() - 1);
        vis[i] = false;

    }
}
```







## [139. 单词拆分](https://leetcode-cn.com/problems/word-break/)

![](/imgs/leetcode/classify/image-20220313105908048.png)

### 方法1:记忆化DFS(使用map)

```java
Map<Integer, Boolean> cache = new HashMap<>();
List<String> wordDict;

public boolean wordBreak(String s, List<String> wordDict) {
    this.wordDict = wordDict;
    return dfs(s, 0);
}

/**
 * 下标索引从i开始到len(s)结束，能否由wordDict字典形成这个单词
 *
 * @param s
 * @param i
 * @return
 */
private boolean dfs(String s, int i) {
    if (cache.containsKey(i)) return cache.get(i);
    if (i == s.length()) return true;
    for (int j = i + 1; j <= s.length(); j++) {
        //[i,j)是取头不取尾，如leetcode取[0,4)取的是leet
        String candidate = s.substring(i, j);
        System.out.println(candidate);
        //当前这个候选单词没有出现在wordDict里
        if (!wordDict.contains(candidate)) continue;
        //从j这个索引出发，继续找，如果找到了，则将j的索引结果存到cache返回 true
        //从j这个索引出发，继续找，如果没到了，则将j的索引结果存到cache[false] 这时候不需要返回 当前没找到还可以找其他的索引开始的
        if (dfs(s, j)) {
            cache.put(j, true);
            return true;
        } else {
            cache.put(j, false);
        }
    }
    /**
     * 比如catsandog与["cats", "dog", "sand", "and", "cat"]的case中
     * cat/sand/og当 i=7的时候，也就是og中的o这个字符
     */
    cache.put(i, false);
    return false;
}
```

### 方法2:记忆化DFS(使用set)

- 可以在记忆化上做很多手脚，上面的map是存储的索引的结果，可以是false也可以是true，本方法的set指的是那些返回false的结果

```java
        List<String> wordDict;
        //存字符s的下标索引，这些索引例如idx, [idx,s.length)这个范围内的单词都不能由wordDict的单词组成
        Set<Integer> set = new HashSet<>();

        public boolean wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            return dfs(s, 0);
        }

        private boolean dfs(String s, int i) {
            if (set.contains(i)) return false;
            if (i == s.length()) return true;
            for (int j = i + 1; j <= s.length(); j++) {
                String candidate = s.substring(i, j);
                if (!wordDict.contains(candidate)) continue;
                if (dfs(s, j)) return true;
                else set.add(j);
            }
            set.add(i);
            return false;
        }
```

### 方法3:记忆化DFS(使用array)

- 类似方法1，采用Boolean[]的方式

```java
        Boolean[] cache;
        List<String> wordDict;

        public boolean wordBreak(String s, List<String> wordDict) {
            cache = new Boolean[s.length() + 1];//多放一个位置
            this.wordDict = wordDict;
            return dfs(s, 0);
        }

        /**
         * 下标索引从i开始到len(s)结束，能否由wordDict字典形成这个单词
         *
         * @param s
         * @param i
         * @return
         */
        private boolean dfs(String s, int i) {
            if (i == s.length()) return true;
            if (cache[i] != null) return cache[i];
            for (int j = i + 1; j <= s.length(); j++) {
                //[i,j)是取头不取尾，如leetcode取[0,4)取的是leet
                String candidate = s.substring(i, j);
                //当前这个候选单词没有出现在wordDict里
                if (!wordDict.contains(candidate)) continue;
                //从j这个索引出发，继续找，如果找到了，则将j的索引结果存到cache返回 true
                //从j这个索引出发，继续找，如果没到了，则将j的索引结果存到cache[false] 这时候不需要返回 当前没找到还可以找其他的索引开始的
                if (dfs(s, j)) {
                    return cache[j] = true;
                } else {
                    cache[j] = false;
                }
            }
            return cache[i] = false;
        }
```

### 方法4:记忆化BFS

```java
public boolean wordBreak(String s, List<String> wordDict) {
    //记录当前处理到的索引
    Queue<Integer> q = new LinkedList<>();
    //当前索引idx [idx,s.length())不能由wordDict里的单词形成
    Set<Integer> vis = new HashSet<>();
    q.offer(0);
    while (!q.isEmpty()) {
        int i = q.poll();
        for (int j = i + 1; j <= s.length(); j++) {
            if (vis.contains(j)) continue;
            String can = s.substring(i, j);
            if (!wordDict.contains(can)) continue;
            if (j == s.length()) return true;
            q.offer(j);
            vis.add(j);
        }
    }
    return false;
}
```

### 方法5:DP

```java
        public boolean wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            //f[i]表示以s[i-1]结尾的字符串能否拆分成wordDict
            boolean[] f = new boolean[n + 1];
            f[0] = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    String can = s.substring(j, i);
//                    System.out.println(can);
                    if (f[j] && wordDict.contains(can)) {
                        f[i] = true;
                        break;
                    }
                }
            }
            return f[n];
        }
```

## [140. 单词拆分 II](https://leetcode-cn.com/problems/word-break-ii/)

### 分析

- 这一题是139题的进阶题，需要列出所有的组成方案

### 方法1:回溯（StringBuilder）

```java
        List<String> res = new ArrayList<>();
        Set<String> wordSet;

        public List<String> wordBreak(String s, List<String> wordDict) {
            wordSet = new HashSet<>(wordDict);
            dfs(s, 0, new StringBuilder());
            return res;
        }

        private void dfs(String s, int idx, StringBuilder cur) {
            int n = s.length();
            //出口函数
            if (idx == n) {
                res.add(cur.toString());
                return;
            }
            for (int i = idx; i < n; i++) {
                //取头不取尾
                String can = s.substring(idx, i + 1);
                if (wordSet.contains(can)) {
                    //存cur要追加单词的前的位置
                    int j = cur.length();
                    //如果是第一个单词，不用加空格
                    if (j == 0) {
                        cur.append(can);
                    } else {
                        cur.append(" ").append(can);
                    }
                    //从i+1开始
                    dfs(s, i + 1, cur);
                    //移除j之后的添加单词，回溯
                    cur.delete(j, cur.length());
                }
            }
        }

```

### 方法2：记忆化DFS

```java
        Map<String, List<String>> cache = new HashMap<>();
        List<String> wordDict;

        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            return dfs(s);
        }


        private List<String> dfs(String s) {
            if (cache.containsKey(s)) {//如果cache中有s的映射，返回
                return cache.get(s);
            }
            List<String> res = new ArrayList<>();
            if (s.length() == 0) {//已经到s的末尾，返回
                res.add("");
                return res;
            }
            for (String word : wordDict) {//遍历wordDict
                if (s.startsWith(word)) {
                    List<String> subList = dfs(s.substring(word.length()));//当前的word是一种可能切分，
                    for (String sub : subList) {//组装word 和sub， 如果是两个单词，隔开
                        res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                    }
                }
            }
            cache.put(s, res);//更新cache，避免重复搜索
            return res;
        }
```

### 方法3：记忆化DFS(剪枝)

```java
        //记忆化hashmap
        Map<Integer, List<String>> cache = new HashMap<>();
        List<String> wordDict;//可以做成HashSet
        int maxLen = 0;

        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            for (String word : wordDict) {
                //最大单词长度，下面的dfs做切分的时候，超过这个单词的长度变得没有意义，一个小的优化点
                if (word.length() > maxLen) maxLen = word.length();
            }
            return dfs(s, 0);
        }

        /**
         * @param s
         * @param start 当前处理到单词的下标索引
         * @return
         */
        private List<String> dfs(String s, int start) {
            //注意用start索引做key，s做key的时候不可行
            if (cache.containsKey(start)) return cache.get(start);
            List<String> res = new ArrayList<>();
            if (start == s.length()) res.add("");//当前的下标到达s的末尾
            for (int i = start; i < start + maxLen && i < s.length(); i++) {//探测式的切分
                if (wordDict.contains(s.substring(start, i + 1))) {
                    //剩下的的单词列表
                    List<String> remainList = dfs(s, i + 1);
                    for (String remain : remainList) {
                        //加上当前切分的单词
                        if ("".equals(remain)) res.add(s.substring(start, i + 1));
                        else res.add(s.substring(start, i + 1) + " " + remain);
                    }
                }
            }
            cache.put(start, res);
            return res;
        }
```

### 方法4:DP

```java
        List<String> resList = new ArrayList<>();
        boolean[] dp;
        List<String> wordDict;

        public List<String> wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            this.wordDict = wordDict;
            //dp[i] 表示「长度」为 i 的 s 前缀子串可以拆分成 wordDict 中的单词
            this.dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    //[0-j]部分和[j-i]部分都可以，[0-i]部分也必然可以，i这个结束
                    if (dp[j] && wordDict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            //保证到[0-n]可以被拆分
            if (dp[n]) {
                dfs(s, n, new ArrayList<>());
                return resList;
            }
            return resList;
        }

        /**
         * @param s
         * @param index     当前处理到s的下标索引
         * @param levelList
         */
        private void dfs(String s, int index, List<String> levelList) {
            if (index == 0) {//s全部探测完，开始组装leveList
                StringBuilder sb = new StringBuilder();
                for (int i = levelList.size() - 1; i >= 0; i--) {
                    sb.append(levelList.get(i)).append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                resList.add(sb.toString());//收集结果
                return;
            }
            for (int i = 0; i < index; i++) {
                if (dp[i]) {//当前的[0-i]是可以被拆分的
                    String sub = s.substring(i, index);
                    if (wordDict.contains(sub)) {
                        levelList.add(sub);//添加
                        dfs(s, i, levelList);
                        levelList.remove(levelList.size() - 1);//回溯
                    }
                }
            }
        }
```



## [131. 分割回文串](https://leetcode-cn.com/problems/palindrome-partitioning/)

### 方法1:DFS

```java
List<List<String>> res = new ArrayList<>();

public List<List<String>> partition(String s) {
    dfs(s, 0, new ArrayList<>());
    return res;
}


private void dfs(String s, int idx, List<String> sub) {
    if (idx == s.length()) {
        res.add(new ArrayList<>(sub));
        return;
    }
    for (int i = idx; i < s.length(); i++) {
        if (isPalindrome(s, idx, i)) {
            sub.add(s.substring(idx, i + 1));
            dfs(s, i + 1, sub);
            sub.remove(sub.size() - 1);
        }
    }
}

private boolean isPalindrome(String s, int l, int r) {
    while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
    return true;
}
```

### 方法2:DP预处理+DFS

```java
        List<List<String>> res = new ArrayList<>();

        public List<List<String>> partition(String s) {
            if (s == null || s.length() == 0) return res;
            int n = s.length();
            boolean[][] f = new boolean[n][n];
            for (int j = 0; j < n; j++) {
                f[j][j] = true;
                for (int i = 0; i < j; i++) {
                    if (s.charAt(j) == s.charAt(i) && (j - i <= 2 || f[i + 1][j - 1])) f[i][j] = true;
                }
            }
            dfs(s, 0, new ArrayList<String>(), f);
            return res;
        }

        private void dfs(String s, int idx, List<String> sub, boolean[][] f) {
            if (idx >= s.length()) {
                res.add(new ArrayList<>(sub));
                return;
            }
            for (int i = idx; i < s.length(); i++) {
                System.out.printf("idx:%d,i:%d\n", idx, i);
                if (f[idx][i]) {
                    sub.add(s.substring(idx, i + 1));
                    dfs(s, i + 1, sub, f);
                    sub.remove(sub.size() - 1);
                }
            }
        }
```

### 方法3:记忆化DFS

```java
public List<List<String>> partition(String s) {
    return partition(s, new HashMap<>());
}

private List<List<String>> partition(String s, Map<String, List<List<String>>> memory) {
    if (memory.containsKey(s)) return memory.get(s);

    List<List<String>> result = new ArrayList<>();

    if (s.isEmpty()) result.add(Collections.emptyList());

    for (int i = 0; i < s.length(); i++) {
        if (isPalindrome(s, 0, i)) {
            String left = s.substring(0, i + 1);
            for (List<String> right : partition(s.substring(i + 1), memory)) {
                List<String> subResult = new ArrayList<>();
                subResult.add(left);
                subResult.addAll(right);
                result.add(subResult);
            }
        }
    }

    memory.put(s, result);

    return memory.get(s);
}

private boolean isPalindrome(String s, int start, int end) {
    while (start <= end) {
        if (s.charAt(start) != s.charAt(end)) return false;
        start++;
        end--;
    }
    return true;
}
```



## [12. 整数转罗马数字](https://leetcode-cn.com/problems/integer-to-roman/)

### 方法1

```java
public String intToRoman(int num) {
    int[] arabic_nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};//关键的罗马数字节点
    String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};//对应的罗马数字
    int i = 0;
    StringBuilder res = new StringBuilder();
    while (i < arabic_nums.length) {
        while (num >= arabic_nums[i]) {//从大到小遍历，贪心判断当前的值是否可以再被缩减
            num -= arabic_nums[i];
            res.append(romans[i]);
        }
        i++;
    }
    return res.toString();
}
```

### 方法2

```java
public String intToRoman(int num) {
    String[][] dict = {//准备 关键节点的 硬编码的罗马数字 ，0 位置为""
            // 1~9
            {
                    "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"
            },
            // 10~90
            {
                    "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"
            },
            // 100~900
            {
                    "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"
            },
            // 1000~3000
            {
                    "", "M", "MM", "MMM"
            }
    };
    StringBuilder res = new StringBuilder();
    int i = 0;
    while (num > 0) {
        int last = num % 10;//每次拿到num的最后面的数，找到后添加
        res.insert(0, dict[i][last]);
        num /= 10;//移除最末位
        i++;
    }
    return res.toString();
}
```



## [13. 罗马数字转整数](https://leetcode-cn.com/problems/roman-to-integer/)

```java
public int romanToInt(String s) {
    Map<String, Integer> map = new HashMap<>();
    map.put("I", 1);
    map.put("IV", 4);
    map.put("V", 5);
    map.put("IX", 9);
    map.put("X", 10);
    map.put("XL", 40);
    map.put("L", 50);
    map.put("XC", 90);
    map.put("C", 100);
    map.put("CD", 400);
    map.put("D", 500);
    map.put("CM", 900);
    map.put("M", 1000);

    int res = 0;
    for (int i = 0; i < s.length(); ) {
        if ((i + 1) < s.length() && map.containsKey(s.substring(i, i + 2))) {
            System.out.printf("%d,%s\n",i,s.substring(i, i + 2));
            res += map.get(s.substring(i, i + 2));
            i += 2;
        } else {
            res += map.get(s.substring(i, i + 1));
            i += 1;
        }
    }
    return res;
}
```







## [273. 整数转换英文表示](https://leetcode-cn.com/problems/integer-to-english-words/)

```java
private final String[] THOUSAND = {"", "Thousand", "Million", "Billion"};
private final String[] LESS_THAN_TWENTY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
private final String[] HUNDRED = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

public String numberToWords(int num) {
    if (num == 0) return "Zero";
    StringBuilder res = new StringBuilder();
    int index = 0;
    while (num > 0) {
        if (num % 1000 != 0) {
            StringBuilder sb = new StringBuilder();
            helper(num % 1000, sb);
            res.insert(0, sb.append(THOUSAND[index]).append(" "));
        }
        index++;
        num /= 1000;
    }
    return res.toString().trim();
}


private void helper(int num, StringBuilder sb) {
    if (num == 0) return;
    if (num < 20) sb.append(LESS_THAN_TWENTY[num]).append(" ");
    else if (num < 100) {
        sb.append(HUNDRED[num / 10]).append(" ");
        helper(num % 10, sb);
    } else {
        sb.append(LESS_THAN_TWENTY[num / 100]).append(" Hundred").append(" ");
        helper(num % 100, sb);
    }
}
```





## [面试题 16.08. 整数的英语表示](https://leetcode-cn.com/problems/english-int-lcci/)

```java
private final String[] THOUSAND = {"", "Thousand", "Million", "Billion"};
private final String[] LESS_THAN_TWENTY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
private final String[] HUNDRED = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

public String numberToWords(int num) {
    if (num == 0) return "Zero";
    StringBuilder res = new StringBuilder();
    int index = 0;
    while (num > 0) {
        if (num % 1000 != 0) {
            StringBuilder sb = new StringBuilder();
            helper(num % 1000, sb);
            res.insert(0, sb.append(THOUSAND[index]).append(" "));
        }
        index++;
        num /= 1000;
    }
    return res.toString().trim();
}


private void helper(int num, StringBuilder sb) {
    if (num == 0) return;
    if (num < 20) sb.append(LESS_THAN_TWENTY[num]).append(" ");
    else if (num < 100) {
        sb.append(HUNDRED[num / 10]).append(" ");
        helper(num % 10, sb);
    } else {
        sb.append(LESS_THAN_TWENTY[num / 100]).append(" Hundred").append(" ");
        helper(num % 100, sb);
    }
}
```