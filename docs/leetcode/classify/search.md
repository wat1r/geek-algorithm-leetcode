# 搜索



## [10. 正则表达式匹配](https://leetcode-cn.com/problems/regular-expression-matching/)

```java
int[][] cache;


public boolean isMatch(String s, String p) {
    cache = new int[s.length() + 1][p.length() + 1];
    char[] ss = s.toCharArray(), pp = p.toCharArray();
    return isMatch(ss, 0, pp, 0);

}

private boolean isMatch(char[] ss, int s1, char[] pp, int p1) {
    if (p1 >= pp.length) return s1 >= ss.length;
    if (cache[s1][p1] != 0) return cache[s1][p1] > 0;
    boolean f = s1 < ss.length && (ss[s1] == pp[p1] || pp[p1] == '.');
    boolean res = true;
    if (pp.length - p1 >= 2 && pp[p1 + 1] == '*') {
        res = isMatch(ss, s1, pp, p1 + 2) || (f && isMatch(ss, s1 + 1, pp, p1));
        if (res) cache[s1][p1] = 1;
        else cache[s1][p1] = -1;
        return res;
    }
    res = f && isMatch(ss, s1 + 1, pp, p1 + 1);
    if (res) cache[s1][p1] = 1;
    else cache[s1][p1] = -1;
    return res;
}
```







## [17. 电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)

```java
       //map映射 数字和字母键
        Map<Character, List<String>> map;
        //结果集
        List<String> res = new ArrayList<>();

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) return res;
            //准备map
            map = new HashMap<Character, List<String>>() {{
                put('2', Arrays.asList("a", "b", "c"));
                put('3', Arrays.asList("d", "e", "f"));
                put('4', Arrays.asList("g", "h", "i"));
                put('5', Arrays.asList("j", "k", "l"));
                put('6', Arrays.asList("m", "n", "o"));
                put('7', Arrays.asList("p", "q", "r", "s"));
                put('8', Arrays.asList("t", "u", "v"));
                put('9', Arrays.asList("w", "x", "y", "z"));
            }};
            dfs(new ArrayList<>(), digits.toCharArray(), 0);
            return res;


        }

        /**
         * @param sub  当前手机的字符的list
         * @param chas digits
         * @param idx  当前递归到了digits[i] i的位置
         */
        private void dfs(List<String> sub, char[] chas, int idx) {
            //出口：sub 和 chas的size一样的时，说明这一轮要结束了
            if (chas.length == sub.size()) {
                StringBuilder sb = new StringBuilder();
                for (String s : sub) sb.append(s);
                res.add(sb.toString());
                return;
            }
            //获取到当前的idx对应的键盘字母
            List<String> candidates = map.get(chas[idx]);
            //遍历
            for (String can : candidates) {
                sub.add(can);//添加
                dfs(sub, chas, idx + 1);//下一个索引
                sub.remove(sub.size() - 1);//恢复
            }
        }
```









## [39. 组合总和](https://leetcode-cn.com/problems/combination-sum/)

### 方法1:回溯

```java
List<List<Integer>> res = new ArrayList<>();


public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);
    dfs(candidates, target, 0, new ArrayList<>());
    return res;
}


private void dfs(int[] nums, int target, int idx, List<Integer> sub) {
    if (target < 0) return;
    if (target == 0) {
        res.add(new ArrayList<>(sub));
        return;
    }

    for (int i = idx; i < nums.length; i++) {
        sub.add(nums[i]);
        dfs(nums, target - nums[i], i, sub);
        sub.remove(sub.size() - 1);
    }
}
```



### 方法2:回溯（累加）

```java

     List<List<Integer>> res = new ArrayList<>();
        int target;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            this.target = target;
            dfs(candidates, 0, 0, new ArrayList<>());
            return res;
        }


        private void dfs(int[] nums, int sum, int idx, List<Integer> sub) {
            if (sum > this.target) return;
            if (sum == this.target) {
                res.add(new ArrayList<>(sub));
                return;
            }

            for (int i = idx; i < nums.length; i++) {
                sub.add(nums[i]);
                dfs(nums, sum + nums[i], i, sub);
                sub.remove(sub.size() - 1);
            }
        }
```



## [40. 组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/)

### 方法1:回溯（递减）

```java
List<List<Integer>> res = new ArrayList<>();

public List<List<Integer>> combinationSum2(int[] nums, int t) {
    Arrays.sort(nums);//sort
    dfs(nums, 0, t, new ArrayList<>());
    return res;
}

private void dfs(int[] nums, int idx, int t, List<Integer> sub) {
    if (t < 0) return;
    if (t == 0) {
        res.add(new ArrayList<>(sub));
        return;
    }

    for (int i = idx; i < nums.length; i++) {
        if (i > idx && nums[i - 1] == nums[i]) continue;//skip duplicate candidate
        sub.add(nums[i]);
        dfs(nums, i + 1, t - nums[i], sub);
        sub.remove(sub.size() - 1);
    }
}
```

### 方法2:回溯（累加）

```java
List<List<Integer>> res = new ArrayList<>();
int t;

public List<List<Integer>> combinationSum2(int[] nums, int t) {
    Arrays.sort(nums);//sort
    this.t = t;
    dfs(nums, 0, 0, new ArrayList<>());
    return res;
}

private void dfs(int[] nums, int idx, int sum, List<Integer> sub) {
    if (sum > t) return;
    if (sum == t) {
        res.add(new ArrayList<>(sub));
        return;
    }

    for (int i = idx; i < nums.length; i++) {
        if (i > idx && nums[i - 1] == nums[i]) continue;//skip duplicate candidate
        sub.add(nums[i]);
        dfs(nums, i + 1, sum + nums[i], sub);
        sub.remove(sub.size() - 1);
    }
}
```



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





```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums == null || nums.length == 0) return res;
    boolean[] vis = new boolean[nums.length];
    dfs(nums, res, new ArrayList<>(), vis);
    return res;
}

/**
 * @param nums 源数组
 * @param res  结果集
 * @param sub  每一层的子结果集
 * @param vis  记录当前的元素是否被访问过
 */
private void dfs(int[] nums, List<List<Integer>> res, List<Integer> sub, boolean[] vis) {
    //当子结果集的大小等于源数组的长度时，即源数组整个已经访问结束，排列结束，开始收集结果
    if (sub.size() == nums.length) {
        res.add(new ArrayList<>(sub));
        return;
    }
    //for loop 整个源数组
    for (int i = 0; i < nums.length; i++) {
        if (vis[i]) continue;//当前元素被访问过，跳过
        vis[i] = true;//记录被访问过
        sub.add(nums[i]);//添加到子结果集
        dfs(nums, res, sub, vis);//进入下一层深搜
        sub.remove(sub.size() - 1);//从当前的子结果集移除
        vis[i] = false;//从被访问过的列表中移除
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



## [78. 子集](https://leetcode-cn.com/problems/subsets/)

### 方法1:回溯

```java
List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            res.add(new ArrayList<>());
            dfs(nums, new ArrayList<>(), 0);
            return res;

        }


        private void dfs(int[] nums, List<Integer> sub, int idx) {
            for (int i = idx; i < nums.length; i++) {
                sub.add(nums[i]);
                res.add(new ArrayList<>(sub));
                dfs(nums, sub, i + 1);
                sub.remove(sub.size() - 1);
            }
        }
```



## [90. 子集 II](https://leetcode-cn.com/problems/subsets-ii/)

### 方法1:回溯

```java

        List<List<Integer>> res = new ArrayList<>();


        public List<List<Integer>> subsetsWithDup(int[] nums) {
            if (nums == null || nums.length == 0) return res;
            Arrays.sort(nums);
            res.add(new ArrayList<>());
            dfs(new ArrayList<>(), nums, 0);
            return res;
        }


        private void dfs(List<Integer> sub, int[] nums, int idx) {
            for (int i = idx; i < nums.length; i++) {
                if (i > idx && nums[i - 1] == nums[i]) continue;
                sub.add(nums[i]);
                res.add(new ArrayList<>(sub));
                sub.forEach(System.out::print);
                System.out.println();
                dfs(sub, nums, i + 1);
                sub.remove(sub.size() - 1);
            }
        }
```





## [93. 复原 IP 地址](https://leetcode-cn.com/problems/restore-ip-addresses/)



```java
    List<String> results = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, new ArrayList<>(), 0);
        return results;
    }

    private void backtrack(String s, ArrayList<String> segment, int index) {
        if (segment.size() == 4 && index == s.length()) {
            results.add(String.join(".", segment));
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (index + i > s.length() || segment.size() > 4) break;
            String curr = s.substring(index, index + i);
            if ((i == 3 && Integer.parseInt(curr) > 255) || (curr.startsWith("0") && curr.length() > 1)) continue;
            segment.add(curr);
            backtrack(s, segment, index + i);
            segment.remove(segment.size() - 1);
        }
    }
```





```java
      List<String> results = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            backtracing(s, "", 0);
            return results;
        }

        private void backtracing(String s, String path, int segs) {
            if (s.isEmpty() || segs == 4) {
                if (s.isEmpty() && segs == 4) results.add(path.substring(1));
                return;
            }
            int n = s.charAt(0) == '0' ? 1 : 3;
            for (int i = 1; i <= n && i <= s.length(); i++) {
                String sub = s.substring(0, i);
                if (Integer.valueOf(sub) <= 255) {
                    backtracing(s.substring(i), path + "." + sub, segs + 1);
                }
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



## [386. 字典序排数](https://leetcode-cn.com/problems/lexicographical-numbers/)

### 方法1:O(1)空间迭代

```java
public List<Integer> lexicalOrder(int n) {
    List<Integer> res = new ArrayList<>();
    int x = 1;
    //list的大小=n
    while (res.size() < n) {
        //每次将当前层 *10进入下一层
        while (x <= n) {
            res.add(x);
            x *= 10;
        }
        //如果当前层的元素已经从9跃升到10这个阶梯或者当前层元素比n大，返回上一层
        while (x % 10 == 9 || x > n) {
            x /= 10;
        }
        //当前层遍历完，递进1
        x++;
    }
    return res;
}
```

### 方法2:DFS 

```java
List<Integer> res = new ArrayList<>();

public List<Integer> lexicalOrder(int n) {
    dfs(0, n);
    return res;
}

private void dfs(int prev, int n) {
    int cur = prev * 10;
    if (prev > n) return;
    for (int i = 0; i < 10; i++) {
        int next = cur + i;
        if (next <= n && next != 0) {
            res.add(next);
            dfs(next, n);
        }
    }
}
```

- 另外一种写法

```java
        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                dfs(i, n, res);
            }
            return res;
        }

        private void dfs(int i, int n, List<Integer> res) {
            if (i > n) return;
            res.add(i);
            for (int j = 0; j <= 9; j++) {
                dfs(i * 10 + j, n, res);
            }
        }
```





### 方法3:Trie+DFS

- Trie的写法也很有意思

```java
class TrieNode {
    boolean isNum;
    Map<Character, TrieNode> map;

    public TrieNode() {
        this.isNum = false;
        this.map = new HashMap<>();
    }


    public void insert(int num) {
        TrieNode cur = this;
        String s = String.valueOf(num);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new TrieNode());
            }
            cur = cur.map.get(c);
        }
        cur.isNum = true;
    }

    public void lexicalOrder(TrieNode root, StringBuilder sb, List<Integer> res) {
        if (root.isNum) res.add(Integer.parseInt(sb.toString()));
        for (Map.Entry<Character, TrieNode> e : root.map.entrySet()) {
            sb.append(e.getKey());
            lexicalOrder(e.getValue(), sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}


public List<Integer> lexicalOrder(int n) {
    List<Integer> res = new ArrayList<>();
    TrieNode root = new TrieNode();
    for (int i = 1; i <= n; i++) root.insert(i);
    root.lexicalOrder(root, new StringBuilder(), res);
    return res;
}
```



### 总结

- 只有方法1是符合题意的O(1)空间复杂度



## [427. 建立四叉树](https://leetcode-cn.com/problems/construct-quad-tree/)

![](/imgs/leetcode/classify/image-20220429070729525.png)

```java
  public Node construct(int[][] grid) {
            return construct(grid, 0, 0, grid.length - 1, grid[0].length - 1);
        }

        private Node construct(int[][] grid, int r1, int c1, int r2, int c2) {
            if (r1 > r2 || c1 > c2) return null;
            if (isLeafNode(grid, r1, c1, r2, c2)) {
                return new Node(grid[r1][c1] == 1, true, null, null, null, null);
            }
            int rowMid = r1 + (r2 - r1) / 2;
            int colMid = c1 + (c2 - c1) / 2;
            return new Node(false, false,
                    construct(grid, r1, c1, rowMid, colMid),
                    construct(grid, r1, colMid + 1, rowMid, c2),
                    construct(grid, rowMid + 1, c1, r2, colMid),
                    construct(grid, rowMid + 1, colMid + 1, r2, c2)
            );
        }


        private boolean isLeafNode(int[][] grid, int r1, int c1, int r2, int c2) {
            int val = grid[r1][c1];
            for (int r = r1; r <= r2; r++) {
                for (int c = c1; c <= c2; c++) {
                    if (grid[r][c] != val) return false;
                }
            }
            return true;
        }
```

- 另外一种写法

```java
    public Node construct(int[][] grid) {
            return dfs(grid, 0, 0, grid.length);
        }


        private Node dfs(int[][] grid, int r, int c, int offset) {
            if (offset == 1) {
                return new Node(grid[r][c] != 0, true, null, null, null, null);
            }
            int half = offset / 2;
            Node newNode = new Node();
            Node topLeft = dfs(grid, r, c, half);
            Node topRight = dfs(grid, r, c + half, half);
            Node bottomLeft = dfs(grid, r + half, c, half);
            Node bottomRight = dfs(grid, r + half, c + half, half);
            if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                    && topLeft.val == topRight.val && topRight.val == bottomLeft.val
                    && bottomLeft.val == bottomRight.val) {
                newNode.isLeaf = true;
                newNode.val = topLeft.val;
            } else {
                newNode.topLeft = topLeft;
                newNode.topRight = topRight;
                newNode.bottomLeft = bottomLeft;
                newNode.bottomRight = bottomRight;
            }
            return newNode;
        }
```



## [433. 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/)

### 方法1:BFS

```java
Set<String> bankSet;

public int minMutation(String start, String end, String[] bank) {
    bankSet = new HashSet<>(Arrays.asList(bank));
    if (!bankSet.contains(end)) return -1;
    int step = 0;
    Queue<String> q = new LinkedList<>();
    q.offer(start);
    while (!q.isEmpty()) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            String u = q.poll();
            List<String> vs = transform(u);
            for (String v : vs) {
                if (v.equals(end)) return step;
                q.offer(v);
            }
        }
        step++;
    }
    return -1;
}

private List<String> transform(String src) {
    List<String> res = new ArrayList<>();
    char[] gen = {'A', 'C', 'G', 'T'};
    for (int i = 0; i < src.length(); i++) {
        char c = src.charAt(i);
        for (int j = 0; j < gen.length; j++) {
            if (gen[j] == c) continue;
            String s = src.substring(0, i) + gen[j] + src.substring(i + 1);
            if (!bankSet.contains(s)) continue;
            res.add(s);
        }
    }
    return res;
}
```

### 方法2:回溯

```java
int minStep = Integer.MAX_VALUE;//最小的步数
Set<String> bankSet;
Set<String> pathSet;


public int minMutation(String start, String end, String[] bank) {
    bankSet = new HashSet<>(Arrays.asList(bank));
    pathSet = new HashSet<>();
    if (!bankSet.contains(end)) return -1;
    backtracing(start, end, 0);
    return minStep == Integer.MAX_VALUE ? -1 : minStep;
}

private void backtracing(String start, String end, int step) {
    //找到了end基因，开始更新最小步数
    if (start.equals(end)) {
        minStep = Math.min(step, minStep);
        return;
    }
    //遍历可能的基因库
    for (String str : bankSet) {
        int diff = 0;//不同基因的数量
        for (int i = 0; i < str.length(); i++) {
            if (start.charAt(i) != str.charAt(i)) {
                diff++;
                if (diff > 1) break;
            }
        }
        //基因的变化是1且当前的基因没有出现在pathSet中，可以进入下一层
        if (diff == 1 && !pathSet.contains(str)) {
            pathSet.add(str);//添加
            backtracing(str, end, step + 1);//步数+1
            pathSet.remove(str);//回溯
        }
    }
}
```







## [752. 打开转盘锁](https://leetcode-cn.com/problems/open-the-lock/)

### 方法1:双向BFS

```java
public int openLock(String[] deadends, String target) {
    Set<String> beginSet = new HashSet<>();
    Set<String> endSet = new HashSet<>();
    Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
    //使用了额外的visitedSet来装被访问过的str，也可以使用deadSet来访问
    Set<String> visitedSet = new HashSet<>();
    beginSet.add("0000");
    endSet.add(target);
    int level = 0;
    while (!beginSet.isEmpty() && !endSet.isEmpty()) {
        System.out.printf("beginSet:%s\n", JSON.toJSONString(beginSet));
        System.out.printf("endSet:%s\n", JSON.toJSONString(endSet));
        Set<String> tmpSet = new HashSet<>();
        for (String str : beginSet) {
            if (endSet.contains(str)) return level;
            if (deadSet.contains(str)) continue;
            visitedSet.add(str);
            for (int i = 0; i < 4; i++) {
                char c = str.charAt(i);
                String upStr = str.substring(0, i) + ((c == '9' ? '0' : c + 1) - 48) + str.substring(i + 1);
                String downStr = str.substring(0, i) + ((c == '0' ? '9' : c - 1) - 48) + str.substring(i + 1);
                if (!deadSet.contains(upStr) && !visitedSet.contains(upStr)) {
                    tmpSet.add(upStr);
                }
                if (!deadSet.contains(downStr) && !visitedSet.contains(downStr)) {
                    tmpSet.add(downStr);
                }
            }
        }
        level++;
        //轮转，上述的for loop中使用的是beginSet,相当于使用beginSet在beginSet与endSet中间来回跳跃
        beginSet = endSet;
        //本轮的tmpSet值赋值给beginSet
        //相当于tmpSet给endSet后备，endSet给beginSet后备
        endSet = tmpSet;
    }
    return -1;
}
```







## [473. 火柴拼正方形](https://leetcode.cn/problems/matchsticks-to-square/)

### 方法1：回溯

```java
boolean[] vis;
int[] matchsticks;
int maxLen;
int n;

public boolean makesquare(int[] matchsticks) {
    n = matchsticks.length;
    int sum = 0;
    for (int x : matchsticks) sum += x;
    if (sum % 4 != 0) return false;
    maxLen = sum / 4;//正方形边长
    vis = new boolean[n];//每个火柴的访问情况
    this.matchsticks = matchsticks;
    return dfs(0, 0, 0);
}

//squareIndex:当前处理到的边的编号，从0~3 index:当前处理的matchsticks的哪根火柴，curLen 当前这条边所积累的边长
public boolean dfs(int squareIndex, int index, int curLen) {
    if (squareIndex == 4) return true;
    if (curLen == maxLen) {
        return dfs(squareIndex + 1, 0, 0);
    }
    //遍历每一根火柴
    for (int i = index; i < n; i++) {
        //当前的火车没有被使用过且当前的边长+即将被使用的火柴的长度，不会越界，这根火柴可以被使用
        if (!vis[i] && curLen + matchsticks[i] <= maxLen) {
            vis[i] = true;//标记
            //如果当前的火柴可以使用，那就进入到下一根火车
            if (dfs(squareIndex, i + 1, curLen + matchsticks[i])) {
                return true;
            }
            vis[i] = false;//回溯
        }
    }
    return false;
}
```

- 另，逆序排序写法

```java
int n;
int[] matchsticks;
int maxLen;

public boolean makesquare(int[] matchsticks) {
    n = matchsticks.length;
    int sum = 0;
    for (int x : matchsticks) sum += x;
    if (sum % 4 != 0) return false;
    this.matchsticks = matchsticks;
    maxLen = sum / 4;//正方形边长
    int[] sides = new int[4];//4条边的长度
    //逆序排序，不排序会超时
    this.matchsticks = IntStream.of(matchsticks)          // 变为 IntStream
            .boxed()           // 装盒变为 Stream<Integer>
            .sorted(Comparator.reverseOrder()) // 按自然序相反排序
            .mapToInt(Integer::intValue)       // 变为 IntStream
            .toArray();        // 又变回 int[]
    return dfs(0, sides);
}

public boolean dfs(int index, int[] sides) {
    if (index == matchsticks.length) {
        return true;
    }
    for (int i = 0; i < sides.length; i++) {
        sides[i] += matchsticks[index];
        if (sides[i] <= maxLen && dfs(index + 1, sides)) {
            return true;
        }
        sides[i] -= matchsticks[index];
    }
    return false;
}
```

- 另

```java
int n;
int[] matchsticks;
int maxLen;

public boolean makesquare(int[] matchsticks) {
    n = matchsticks.length;
    int sum = 0;
    for (int x : matchsticks) sum += x;
    if (sum % 4 != 0) return false;
    this.matchsticks = matchsticks;
    maxLen = sum / 4;//正方形边长
    int[] sides = new int[4];//4条边的长度
    //逆序排序，不排序会超时
    this.matchsticks = IntStream.of(matchsticks)          // 变为 IntStream
            .boxed()           // 装盒变为 Stream<Integer>
            .sorted(Comparator.reverseOrder()) // 按自然序相反排序
            .mapToInt(Integer::intValue)       // 变为 IntStream
            .toArray();        // 又变回 int[]
    return dfs(0, sides);
}

private boolean dfs(int index, int[] sides) {
    if (index >= matchsticks.length) {
        if (sides[0] == maxLen && sides[1] == maxLen && sides[2] == maxLen) {
            return true;
        }
    }
    for (int i = 0; i < sides.length; i++) {
        if (sides[i] + matchsticks[index] > maxLen) {
            continue;
        }
        sides[i] += matchsticks[index];
        if (dfs(index + 1, sides)) {
            return true;
        }
        sides[i] -= matchsticks[index];
    }
    return false;
}
```

### 方法2：动态规划+状态压缩

> 思路来自官解

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220601082607.png)

```java
      public boolean makesquare(int[] matchsticks) {
            int totalLen = Arrays.stream(matchsticks).sum();
            if (totalLen % 4 != 0) {
                return false;
            }
            int len = totalLen / 4, n = matchsticks.length;
            int[] dp = new int[1 << n];
            Arrays.fill(dp, -1);
            dp[0] = 0;
            for (int s = 1; s < (1 << n); s++) {
                for (int k = 0; k < n; k++) {
                    if ((s & (1 << k)) == 0) {
                        continue;
                    }
                    int s1 = s & ~(1 << k);
                    if (dp[s1] >= 0 && dp[s1] + matchsticks[k] <= len) {
                        dp[s] = (dp[s1] + matchsticks[k]) % len;
                        break;
                    }
                }
            }
            return dp[(1 << n) - 1] == 0;
        }
```



## [2044. 统计按位或能得到最大值的子集数目](https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/)

### 方法1:DFS枚举所有子集统计

```java
//k:子集或后的值，v:该值出现的次数
Map<Integer, Integer> map = new HashMap<>();

public int countMaxOrSubsets(int[] nums) {
    int maxx = -1;
    subsets(nums);
    for (List<Integer> sub : res) {
        int t = 0;
        for (int x : sub) t |= x;//是或「|」 不是异或 「^」
        maxx = Math.max(maxx, t);
        map.put(t, map.getOrDefault(t, 0) + 1);
    }
    return map.get(maxx);
}


List<List<Integer>> res = new ArrayList<>();
//枚举所有子集
public List<List<Integer>> subsets(int[] nums) {
    if (nums == null || nums.length == 0) return res;
    dfs(new ArrayList<>(), nums, 0);
    return res;
}

private void dfs(List<Integer> sub, int[] nums, int idx) {
    if (idx == nums.length) {
        res.add(new ArrayList<>(sub));
        return;
    }
    sub.add(nums[idx]);
    dfs(sub, nums, idx + 1);
    sub.remove(sub.size() - 1);
    dfs(sub, nums, idx + 1);
}
```





## [5289. 公平分发饼干](https://leetcode.cn/problems/fair-distribution-of-cookies/)

### 方法1：回溯

```java

        int n;//cookies的大小
        int totalMax = Integer.MAX_VALUE;
        int k;//孩子的数量

        public int distributeCookies(int[] cookies, int k) {
            n = cookies.length;
            this.k = k;
            //让大的饼干在前面
            cookies = IntStream.of(cookies)          // 变为 IntStream
                    .boxed()           // 装盒变为 Stream<Integer>
                    .sorted(Comparator.reverseOrder()) // 按自然序相反排序
                    .mapToInt(Integer::intValue)       // 变为 IntStream
                    .toArray();        // 又变回 int[]
            int[] arr = new int[k];
            backtrace(0, arr, cookies);
            return totalMax;

        }


        /**
         * @param index   当前处理到的cookies数据的下标
         * @param arr     当前k个小孩，每人一个袋子，每个袋子里的饼干的总数
         * @param cookies 饼干的数组
         */
        private void backtrace(int index, int[] arr, int[] cookies) {
            if (index == n) {//处理的下标来到cookies数组的最后，说明这时候所有的饼干都分完了
                int maxx = 0;//记录当前这种分法下的最大饼干
                for (int x : arr) maxx = Math.max(maxx, x);
                totalMax = Math.min(totalMax, maxx);//全局的最小饼干数量
                return;
            }
            //统计当前的小孩还没有分到饼干的数量
            int empty = 0;
            for (int x : arr) {
                if (x == 0) empty++;
            }
            //剩余的可供分配的饼干的数量是 n-index个，但是要分饼干的小孩有empty个，不能保证剩下的小孩每人都分到饼干
            if (empty > n - index) return;
            for (int i = 0; i < k; i++) {
                //规定index为0的饼干即第一块饼干分配给第一个小孩
                if (i > 0 && index == 0) {
                    return;
                }
                //当前小孩的数量和上一个小孩的数量相等（饼干数量），此种结果重复了
                if (i > 0 && arr[i] == arr[i - 1]) continue;
                //当前小孩拿的饼干，比之前获取到的全局最小值还要大，说明当前的这种方案下，肯定不如totalMax的方案下的分配方式
                if (arr[i] + cookies[index] > totalMax) continue;
                arr[i] += cookies[index];
                backtrace(index + 1, arr, cookies);
                arr[i] -= cookies[index];
            }
        }
```







## 岛屿系列问题

### [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)

[岛屿问题之岛屿的数量Eighty-eight Butterfly](https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-wen-ti-zhi-dao-yu-de-shu-liang-eighty-eight/)

### [827. 最大人工岛](https://leetcode-cn.com/problems/making-a-large-island/)

[ 岛屿问题之最大人工岛Danaus Genutia](https://leetcode-cn.com/problems/making-a-large-island/solution/dao-yu-wen-ti-zhi-zui-da-ren-gong-dao-danaus-genut/)

### [695. 岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/)

[岛屿问题之岛屿的周长面积Morpho Cypris Aphrodite](https://leetcode-cn.com/problems/island-perimeter/solution/dao-yu-wen-ti-zhi-dao-yu-de-zhou-chang-mian-ji-mor/)

### [463. 岛屿的周长](https://leetcode-cn.com/problems/island-perimeter/)

[岛屿问题之岛屿的周长面积Morpho Cypris Aphrodite](https://leetcode-cn.com/problems/island-perimeter/solution/dao-yu-wen-ti-zhi-dao-yu-de-zhou-chang-mian-ji-mor/)

### [1254. 统计封闭岛屿的数目](https://leetcode-cn.com/problems/number-of-closed-islands/)

[岛屿问题之不同岛屿的数量Monarch Butterfly](https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-wen-ti-zhi-bu-tong-dao-yu-de-shu-liang-mona/)

### [130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)

[岛屿问题之被围绕的区域[Cicada]](https://leetcode-cn.com/problems/surrounded-regions/solution/dao-yu-wen-ti-zhi-bei-wei-rao-de-qu-yu-cicada-by-2/)

### [1905. 统计子岛屿](https://leetcode-cn.com/problems/count-sub-islands/)

[搜索与图论之FloodFill-统计子岛屿](https://leetcode-cn.com/problems/count-sub-islands/)

### [417. 太平洋大西洋水流问题](https://leetcode-cn.com/problems/pacific-atlantic-water-flow/)

### 方法1:DFS

![](/imgs/leetcode/classify/image-20220427092158521.png)

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

### 方法2：BFS

```java
int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
int R, C;

public List<List<Integer>> pacificAtlantic(int[][] heights) {
    List<List<Integer>> res = new LinkedList<>();
    if (heights == null || heights.length == 0 || heights[0].length == 0) return res;
    R = heights.length;
    C = heights[0].length;
    boolean[][] visP = new boolean[R][C];
    boolean[][] visA = new boolean[R][C];
    Queue<int[]> qP = new LinkedList<>();
    Queue<int[]> qA = new LinkedList<>();
    for (int r = 0; r < R; r++) { //行
        qP.offer(new int[]{r, 0});
        qA.offer(new int[]{r, C - 1});
        visP[r][0] = true;
        visA[r][C - 1] = true;
    }
    for (int c = 0; c < C; c++) { //列
        qP.offer(new int[]{0, c});
        qA.offer(new int[]{R - 1, c});
        visP[0][c] = true;
        visA[R - 1][c] = true;
    }
    bfs(heights, qP, visP);
    bfs(heights, qA, visA);
    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
            if (visP[r][c] && visA[r][c]) res.add(Arrays.asList(r, c));
        }
    }
    return res;
}

public void bfs(int[][] heights, Queue<int[]> queue, boolean[][] vis) {
    while (!queue.isEmpty()) {
        int[] cur = queue.poll();
        int r = cur[0], c = cur[1];
        for (int[] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (inArea(nr, nc) && !vis[nr][nc] && heights[nr][nc] >= heights[r][c]) {
                vis[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
    }
}

private boolean inArea(int r, int c) {
    return r >= 0 && r < R && c >= 0 && c < C;
}
```





## [面试题 08.09. 括号](https://leetcode.cn/problems/bracket-lcci/)

```java
List<String> res = new ArrayList<>();
int n;

public List<String> generateParenthesis(int n) {
    this.n = n;
    dfs("", 0, 0);
    return res;
}

//传StringBuilder 不可以
public void dfs(String s, int left, int right) {
    if (left == n && right == n) {
        res.add(s);
        return;
    }
    //left ++  ++left 均不可以
    if (left < n) dfs(s + "(", left + 1, right);
    //right < left
    if (right < left) dfs(s + ")", left, right + 1);
}
```

- 另

```java
        List<String> res = new ArrayList<>();
        int n;

        public List<String> generateParenthesis(int n) {
            this.n = n;
            dfs("", 0, 0);
            return res;
        }

        //传StringBuilder 不可以
        public void dfs(String s, int left, int right) {
            if (left == n && right == n) {
                res.add(s);
                return;
            }
            if (left > n || right > n) return;
            if (right > left) return;
            dfs(s + "(", left + 1, right);
            //right < left
            dfs(s + ")", left, right + 1);
        }
```



## [剑指 Offer II 114. 外星文字典](https://leetcode.cn/problems/Jf1JuT/)

> 注意题意中这两点的描述

字符串 s 字典顺序小于 字符串 t 有两种情况：

- 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
- 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。

实际举例：

以下两种情况不存在合法字母顺序：

- 字母之间的顺序关系存在由至少 22 个字母组成的环，例如words=[“a",“b",“a"]；

- 相邻两个单词满足后面的单词是前面的单词的前缀，且后面的单词的长度小于前面的单词的长度，例如 words=[“ab",“a"]。

### 方法1：拓扑排序+BFS

```java
        Map<Character, List<Character>> edges = new HashMap<>();//字符间边的关系
        Map<Character, Integer> indegrees = new HashMap<>();//统计某个字符的入度
        boolean valid = true;//判断是否需要提前退出

        public String alienOrder(String[] words) {
            //建图，完成拓扑排序的准备工作
            int n = words.length;
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    edges.putIfAbsent(c, new ArrayList<>());//给每个字符添加一个相邻边
                }
            }
            for (int i = 1; i < n && valid; i++) {
                addEdge(words[i - 1], words[i]);
            }
            if (!valid) return "";
            //bfs
            Queue<Character> q = new LinkedList<>();
            for (char u : edges.keySet()) {//将入度为0的字符加入到队列中
                if (!indegrees.containsKey(u)) {
                    q.offer(u);
                }
            }
            StringBuilder sb = new StringBuilder();//记录弹出的顺序
            while (!q.isEmpty()) {
                char u = q.poll();
                sb.append(u);
                for (char v : edges.get(u)) {//遍历u的邻居
                    indegrees.put(v, indegrees.get(v) - 1);
                    if (indegrees.get(v) == 0) {//入度为0后，该节点转入队列中
                        q.offer(v);
                    }
                }
            }
            //["z","x","a","zb","zx"]
            //对于前四个字符串 排序是zxab 但是来了zx后 x又得排序到b之后，但是b之前已经出现了x，是矛盾的
            //这个case范围的是"" 如果不加上判断，返回的是"b", "b"的邻居"x"在入度减一后并没有立马减少为0，bfs提前结束
            return sb.length() == edges.size() ? sb.toString() : "";
        }


        public void addEdge(String prev, String cur) {
            int m = prev.length(), n = cur.length();
            int len = Math.min(m, n);
            int i = 0;
            for (; i < len; i++) {
                char u = prev.charAt(i), v = cur.charAt(i);
                if (u != v) {
                    if (u == 'b') {
                        System.out.println();
                    }
                    edges.get(u).add(v);
                    indegrees.put(v, indegrees.getOrDefault(v, 0) + 1);
                    break;
                }
            }
            //如果"abc" "ab"的这种case，提前返回""
            if (i == len && m > n) {
                valid = false;
            }
        }
```



### 方法2：拓扑排序+DFS

官方dfs拓扑排序的思路：

由于拓扑排序的顺序和搜索完成的顺序相反，因此需要使用一个栈存储所有已经搜索完成的节点。深度优先搜索的过程中需要维护每个节点的状态，每个节点的状态可能有三种情况：「未访问」、「访问中」和「已访问」。初始时，所有节点的状态都是「未访问」。

每一轮搜索时，任意选取一个「未访问」的节点 u，从节点 u 开始深度优先搜索。将节点 u的状态更新为「访问中」，对于每个与节点 u 相邻的节点 v，判断节点 v 的状态，执行如下操作：

- 如果节点 v的状态是「未访问」，则继续搜索节点 v；

- 如果节点 v 的状态是「访问中」，则找到有向图中的环，因此不存在拓扑排序；

- 如果节点 v 的状态是「已访问」，则节点 v 已经搜索完成并入栈，节点 u 尚未入栈，因此节点 u 的拓扑顺序一定在节点 v 的前面，不需要执行任何操作。

```java
     static final int VISITING = 1, VISITED = 2;
        Map<Character, List<Character>> edges = new HashMap<>();//字符间边的关系
        Map<Character, Integer> states = new HashMap<>();//统计某个字符的状态
        boolean valid = true;
        char[] paths;
        int index;

        public String alienOrder(String[] words) {
            //建图，完成拓扑排序的准备工作
            int n = words.length;
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    edges.putIfAbsent(c, new ArrayList<>());//给每个字符添加一个相邻边
                }
            }
            for (int i = 1; i < n && valid; i++) {
                addEdge(words[i - 1], words[i]);
            }
            if (!valid) return "";
            //dfs
            paths = new char[edges.size()];
            index = edges.size() - 1;
            for (char u : edges.keySet()) {
                if (!states.containsKey(u)) {
                    dfs(u);
                }
            }
            if (!valid) return "";
            return String.valueOf(paths);
        }

        private void dfs(char u) {
            states.put(u, VISITING);//当前节点标记为「访问中」
            for (char v : edges.get(u)) {
                if (!states.containsKey(v)) {//节点v没有被访问
                    dfs(v);//继续遍历v
                    if (!valid) return;//如果发现有不符合条件的 ，提前结束
                } else if (states.get(v) == VISITING) //第二次进入v，说明有环
                {
                    valid = false;
                    return;
                }
            }
            states.put(u, VISITED);//u这个节点是安全的，标记为「已访问」
            paths[index--] = u;//记录u在栈的位置
        }


        public void addEdge(String prev, String cur) {
            int m = prev.length(), n = cur.length();
            int len = Math.min(m, n);
            int i = 0;
            for (; i < len; i++) {
                char u = prev.charAt(i), v = cur.charAt(i);
                if (u != v) {
                    edges.get(u).add(v);
                    break;
                }
            }
            if (i == len && m > n) {
                valid = false;
            }
        }
```





## [面试题 08.06. 汉诺塔问题](https://leetcode.cn/problems/hanota-lcci/)

### 方法1：递归

- [链接](https://leetcode.cn/problems/hanota-lcci/solution/tu-jie-yi-nuo-ta-de-gu-shi-ju-shuo-dang-64ge-pan-z/)

```java
public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
    int remain = A.size();
    move(remain, A, B, C);
}


public void move(int remain, List<Integer> A, List<Integer> B, List<Integer> C) {
    if (remain == 1) {
        int t = A.remove(A.size() - 1);
        C.add(t);
        return;
    }
    move(remain - 1, A, C, B);
    int t = A.remove(A.size() - 1);
    C.add(t);
    move(remain - 1, B, A, C);

}
```

















### 搜索与图论问题合辑

1.[搜索与图论之FloodFill](https://blog.csdn.net/wat1r/article/details/113702607)

2.[搜索与图论之最短路](https://blog.csdn.net/wat1r/article/details/113729703)

3.[搜索与图论之欧拉回路与欧拉路径](https://blog.csdn.net/wat1r/article/details/113853334)

4.[搜索与图论之拓扑排序](https://blog.csdn.net/wat1r/article/details/113871449)