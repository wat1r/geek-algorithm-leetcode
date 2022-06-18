package com.frankcooper.platform.leetcode.bank._101_200;

import com.alibaba.fastjson.JSON;

import java.util.*;

//127. 单词接龙 127. Word Ladder Medium
public class _127 {

    public static void main(String[] args) {
        _127 handler = new _127();

        Set<String> words = new HashSet<>(Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        String beginWord = "hit";
        String endWord = "cog";
//        handler.transform(words, beginWord);
//        handler.ladderLength2nd(beginWord, "cog", new ArrayList<>(words));
//        words = new HashSet<>(Arrays.asList(new String[]{"a", "b", "c"}));
//        beginWord = "a";
//        String endWord = "c";
//        handler.ladderLength2nd(beginWord, endWord, new ArrayList<>(words));
        handler.testTwo();

    }


    private void testTwo() {
        _2nd second = new _2nd();
        Set<String> words = new HashSet<>(Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        String beginWord = "hit";
        String endWord = "cog";
        second.ladderLength(beginWord, endWord, new ArrayList<>(words));
    }


    /**
     * 双向bfs
     */
    class _2nd {


        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) return 0;
            Set<String> beginSet = new HashSet<>();
            Set<String> endSet = new HashSet<>();
            Set<String> visSet = new HashSet<>();
            beginSet.add(beginWord);
            endSet.add(endWord);
            int steps = 1;
            while (!beginSet.isEmpty() && !endSet.isEmpty()) {
                if (beginSet.size() > endSet.size()) {
                    Set<String> tmpSet = beginSet;
                    beginSet = endSet;
                    endSet = tmpSet;
                }
                Set<String> tmpSet = new HashSet<>();
//                System.out.printf("beginSet:%s\n", JSON.toJSONString(beginSet));
//                System.out.printf("endSet:%s\n", JSON.toJSONString(endSet));
                for (String word : beginSet) {
                    char[] chas = word.toCharArray();
                    for (int i = 0; i < chas.length; i++) {
                        char src = chas[i];
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == src) continue;
                            chas[i] = c;
                            String nextWord = String.valueOf(chas);
                            //已经找到了
                            if (endSet.contains(nextWord)) {
                                return steps + 1;
                            }
                            //没有被访问过，且单词在wordSet中，添加进tmpSet ，本轮结束后赋值给beginSet
                            if (!visSet.contains(nextWord) && wordSet.contains(nextWord)) {
                                tmpSet.add(nextWord);
                                visSet.add(nextWord);
                            }
                        }
                        chas[i] = src;
                    }
                }
                beginSet = tmpSet;
                steps++;
            }
            return 0;
        }

    }


    /*
    #### 方法1：BFS
    - 准备一个`transform(Set<String> words, String word)` 函数，生成的是与当前`word`差距一个字符的单词，此单词存在于`words`中，返回的是个`List`
    - 准备一个`Queue`,与记录到达`endWord`的步数`steps`
      - 一开始将`beginWord`推进`Queue`，`steps=1`
      - 当`Queue`不为空，`for loop` 当前的`size` ，弹出元素记为`cur`，通过`transform` 获取到潜在的单词
        - 如果潜在单词有`endWord`，返回`steps`
        - 如果潜在单词没有`endWord`，将当前的潜在单词推进`Queue`
        - 每一层的`while`循环 `steps+1`表示走了几个单词
         */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> words = new HashSet<>(wordList);
        queue.offer(beginWord);
        int steps = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                List<String> candidates = transform(words, cur);
                for (String candidate : candidates) {
                    if (candidate.equals(endWord)) return steps;
                    queue.offer(candidate);
                }
            }
        }
        return 0;
    }


    /**
     * 生成目标的word的所有潜在的word，
     * 如hit -->ait bit ...zit但是排除了hit本身
     * hit --> hat hbt... hzt但是排除了hit本身
     * words 含有的上面生成的潜在的word进行收集
     *
     * @param words
     * @param word
     * @return
     */
    private List<String> transform(Set<String> words, String word) {
        List<String> resList = new ArrayList<>();
        StringBuffer sb = new StringBuffer(word);
        for (int i = 0; i < sb.length(); i++) {
            char tmp = sb.charAt(i);//记录下索引位置下的char，下面的for loop中会剔除掉这个
            for (char c = 'a'; c <= 'z'; c++) {
                if (tmp == c) continue;//word本身
                sb.setCharAt(i, c);//改变i的值
                String canditate = sb.toString();
                //如果words含有canditate，将其加入到结果集中
                if (words.remove(canditate)) resList.add(canditate);
            }
            sb.setCharAt(i, tmp);//结束本轮loop后，恢复原样
        }
        return resList;
    }


    /*
    方法2：BFS+HashMap
    - 时间复杂度：`O(M×N)`，其中 `M` 是单词的长度 `N` 是单词表中单词的总数。找到所有的变换需要对每个单词做 `M` 次操作。同时，最坏情况下广度优先搜索也要访问所有的 `N` 个单词。
    - 空间复杂度：`O(M×N)`，要在 `map` 字典中记录每个单词的 `M` 个通用状态。访问数组的大小是 `N`。广搜队列最坏情况下需要存储`N` 个单词。
     */

    /*public int ladderLength2nd(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) return 0;
        int n = beginWord.length();
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < n; i++) {
                String candidate = word.substring(0, i) + "*" + word.substring(i + 1, n);
                List<String> tmpList = map.getOrDefault(candidate, new ArrayList<>());
                tmpList.add(word);
                map.put(candidate, tmpList);
            }
        }
        Queue<String> queue = new LinkedList<>();
        Map<String, Boolean> visited = new HashMap<>();
        queue.offer(beginWord);
        visited.put(beginWord, true);
        int steps = 0;
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            steps++;
//            int size = queue.size();
            for (int i = 0; i < n; i++) {
                String candidate = cur.substring(0, i) + "*" + cur.substring(i + 1, n);
                List<String> candidates = map.getOrDefault(candidate, new ArrayList<>());
                System.out.println(JSON.toJSON(candidates));
                for (String c : candidates) {
                    if (endWord.equals(c)) {
                        System.out.println(String.format("*result:%d", steps));
                        return endWord.length() < 2 ? steps + 1 : steps;
                    }
                    if (!visited.containsKey(c)) {
                        visited.put(c, true);
                        queue.offer(c);

                    }
                }
            }
        }
        System.out.println(String.format("result:%d", steps));
        return steps;
    }*/

}


