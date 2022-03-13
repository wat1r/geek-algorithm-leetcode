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



## [139. 单词拆分](https://leetcode-cn.com/problems/word-break/)

![](/imgs/leetcode/classify/image-20220313105908048.png)

### 方法1:记忆化DFS

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

