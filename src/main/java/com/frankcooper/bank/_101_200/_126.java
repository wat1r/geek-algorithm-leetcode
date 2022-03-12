package com.frankcooper.bank._101_200;


import com.alibaba.fastjson.JSON;

import java.util.*;

//126. 单词接龙 II 126. Word Ladder II Hard
//Reference:https://leetcode-cn.com/problems/word-ladder-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-3/
public class _126 {

    public static void main(String[] args) {
        _126 handler = new _126();
        Set<String> words = new HashSet<>(Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        String beginWord = "hit";
        String endWord = "cog";
        handler.findLadders3rd(beginWord, endWord, new ArrayList<>(words));

    }


    /*
        #### 方法1：DFS
        - 遇到大数据量的wordList会超时
     */

    int min1st = Integer.MAX_VALUE;

    public List<List<String>> findLadders1st(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> resultList = new ArrayList<>();//最终的结果集
        List<String> tmpList = new ArrayList<>();//当前的每一层的结果集
        tmpList.add(beginWord);
        dfs1st(beginWord, endWord, wordList, tmpList, resultList);
        return resultList;
    }

    private void dfs1st(String beginWord, String endWord, List<String> wordList, List<String> tmpList, List<List<String>> resultList) {
        //出口逻辑 当min的大于size(tmpList)时，说明当前的min不是最小的，有比当前的min更小的
        //1.清空掉已经收集的results结果，将新的tmpList赋给results,重置min
        //2.当min==size(tmpList)时，说明刚好，结果收集即可
        //3.当size(tmpList)>min时，说明还没有结束dfs，但是已经有最少的路径，为min的步长，所以当前的没必要继续探索下去
        if (beginWord.equals(endWord)) {
            if (min1st > tmpList.size()) {
                resultList.clear();
                min1st = tmpList.size();
                resultList.add(new ArrayList<>(tmpList));
            } else if (min1st == tmpList.size()) {
                resultList.add(new ArrayList<>(tmpList));
            }
            return;
        }
        if (tmpList.size() >= min1st) {
            return;
        }
        for (int i = 0; i < wordList.size(); i++) {
            String curWord = wordList.get(i);
            if (tmpList.contains(curWord)) continue;
            if (checkIfOneCharDiff(beginWord, curWord)) {//curWord是不是和beginWord差着一个char
                tmpList.add(curWord);
                dfs1st(curWord, endWord, wordList, tmpList, resultList);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }


    /**
     * 判断srcWord与destWord是否只是差了一个字符，是返回ture，否则返回false
     *
     * @param srcWord
     * @param destWord
     * @return
     */
    private boolean checkIfOneCharDiff(String srcWord, String destWord) {
        int count = 0;
        for (int i = 0; i < srcWord.length(); i++) {
            if (srcWord.charAt(i) != destWord.charAt(i)) count++;
            if (count == 2) return false;
        }
        return count == 1;
    }


    /*
    #### 方法2：DFS
    - 拿来了127题的transform方法，主题逻辑同方法1，但是还是回超时
     */
    int min2nd = Integer.MAX_VALUE;

    public List<List<String>> findLadders2nd(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> resultList = new ArrayList<>();//最终的结果集
        List<String> tmpList = new ArrayList<>();//当前的每一层的结果集
        tmpList.add(beginWord);
        dfs2nd(beginWord, endWord, wordList, tmpList, resultList);
        return resultList;
    }

    private void dfs2nd(String beginWord, String endWord, List<String> wordList, List<String> tmpList, List<List<String>> resultList) {
        //出口逻辑 当min的大于size(tmpList)时，说明当前的min不是最小的，有比当前的min更小的
        //1.清空掉已经收集的results结果，将新的tmpList赋给results,重置min
        //2.当min==size(tmpList)时，说明刚好，结果收集即可
        //3.当size(tmpList)>min时，说明还没有结束dfs，但是已经有最少的路径，为min的步长，所以当前的没必要继续探索下去
        if (beginWord.equals(endWord)) {
            if (min2nd > tmpList.size()) {
                resultList.clear();
                min2nd = tmpList.size();
                resultList.add(new ArrayList<>(tmpList));
            } else if (min2nd == tmpList.size()) {
                resultList.add(new ArrayList<>(tmpList));
            }
            return;
        }
        if (tmpList.size() >= min2nd) {
            return;
        }

        Set<String> words = new HashSet<>(wordList);
        List<String> candidates = transform(words, beginWord);
        for (String candidate : candidates) {
            if (tmpList.contains(candidate)) continue;
            tmpList.add(candidate);
            dfs2nd(candidate, endWord, wordList, tmpList, resultList);
            tmpList.remove(tmpList.size() - 1);
        }

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
        System.out.println(String.format("res:%s",

                JSON.toJSONString(resList)));
        return resList;
    }


    /*
   ##### 方法3:BFS+DFS
    - 执行超时
     */

    int min3rd = 0;

    public List<List<String>> findLadders3rd(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> resultList = new ArrayList<>();//最终的结果集
        if (!wordList.contains(endWord)) return resultList;
        HashMap<String, List<String>> map = bfs3rd(beginWord, endWord, wordList);
        List<String> tmpList = new ArrayList<>();//当前的每一层的结果集
        tmpList.add(beginWord);
        dfs3rd(beginWord, endWord, map, tmpList, resultList);
        return resultList;
    }

    private void dfs3rd(String beginWord, String endWord, HashMap<String, List<String>> map, List<String> tmpList, List<List<String>> resultList) {
        //出口条件
        if (beginWord.equals(endWord)) {
            System.out.println(JSON.toJSON(tmpList));
            resultList.add(new ArrayList<>(tmpList));
            return;
        }
        if (tmpList.size() > min3rd) return;
        List<String> candidates = map.getOrDefault(beginWord, new ArrayList<>());
        for (String candidate : candidates) {
            if (tmpList.contains(candidate)) continue;
            tmpList.add(candidate);
            dfs3rd(candidate, endWord, map, tmpList, resultList);
            tmpList.remove(tmpList.size() - 1);
        }
    }


    /*
       每个word后对应的邻接的words
       - `map<key,value>` `k` 是当前的`curWord`，`v`是与当前的 `curWord`差着一个字符的`wordList`的候选单词列表
       - 此过程中，记录下`min3rd`的大小，也就是最小的路径

     */
    public HashMap<String, List<String>> bfs3rd(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<String>> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        boolean isFound = false;
        queue.offer(beginWord);
        HashSet<String> wordSet = new HashSet<>(wordList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            min3rd++;
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                List<String> candidates = transform3rd(wordSet, curWord);
//                transform(wordSet, curWord);
//                List<String> candidates = getNeighbors(curWord, wordSet);
                map.put(curWord, candidates);
                for (String candidate : candidates) {
                    if (candidate.equals(endWord)) isFound = true;
                    queue.offer(candidate);
                }
            }
            if (isFound) break;
        }
        return map;
    }

    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch)
                    continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        System.out.println(String.format("res:%s", JSON.toJSONString(res)));
        return res;
    }

    /**
     * 区别于transform方法
     * if (words.contains(canditate)) resList.add(canditate);
     * if (words.remove(canditate)) resList.add(canditate);
     *
     * @param words
     * @param word
     * @return
     */
    private List<String> transform3rd(Set<String> words, String word) {
        List<String> resList = new ArrayList<>();
        StringBuffer sb = new StringBuffer(word);
        for (int i = 0; i < sb.length(); i++) {
            char tmp = sb.charAt(i);//记录下索引位置下的char，下面的for loop中会剔除掉这个
            for (char c = 'a'; c <= 'z'; c++) {
                if (tmp == c) continue;//word本身
                sb.setCharAt(i, c);//改变i的值
                String canditate = sb.toString();
                //如果words含有canditate，将其加入到结果集中
                if (words.contains(canditate)) resList.add(canditate);
            }
            sb.setCharAt(i, tmp);//结束本轮loop后，恢复原样
        }
//        System.out.println(String.format("res:%s", JSON.toJSONString(resList)));
        return resList;
    }

    /*
    ##### 方法4:BFS
     */
    public List<List<String>> findLadders4th(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> resultList = new ArrayList<>();//最终的结果集
        if (!wordList.contains(endWord)) return resultList;
        bfs4th(beginWord, endWord, wordList, resultList);
        return resultList;
    }

    private void bfs4th(String beginWord, String endWord, List<String> wordList, List<List<String>> resultList) {
        Queue<List<String>> queue = new LinkedList<>();//存储是单词的路径
        List<String> paths = new ArrayList<>();
        paths.add(beginWord);
        queue.offer(paths);
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();//访问过的列表
        visited.add(beginWord);
        boolean isFound = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> subVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                List<String> curPath = queue.poll();//弹出当前的路径
                String end = curPath.get(curPath.size() - 1);//取最末尾的一个
                List<String> candidates = transform3rd(wordSet, end);
                for (String candidate : candidates) {
                    if (!visited.contains(candidate)) {
                        if (candidate.equals(endWord)) {
                            isFound = true;
                            curPath.add(candidate);
                            resultList.add(new ArrayList<>(curPath));
                            curPath.remove(curPath.size() - 1);
                        }
                        curPath.add(candidate);
                        queue.offer(new ArrayList<>(curPath));
                        curPath.remove(curPath.size() - 1);
                        subVisited.add(candidate);
                    }
                }
            }
            visited.addAll(subVisited);
            if (isFound) break;
        }

    }


}
