## 【重温经典】单词拆分 II



![image-20210825202837310](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210825202837310.png)

#### 方法1:切片函数+记忆化搜索

- 不带记忆化TLE

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



#### 方法2:下标切分+记忆化搜索

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
         *
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

#### 方法3：DP预处理+回溯

- 效率最高

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





