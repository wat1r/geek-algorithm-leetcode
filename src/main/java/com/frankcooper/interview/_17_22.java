package com.frankcooper.interview;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/8/29 20:01
 * Description
 */
public class _17_22 {


    static _17_22 handler = new _17_22();


    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        handler.findLadders(beginWord, endWord, wordList);
    }


    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        Set<String> words = new HashSet<>(wordList);
        Map<String, String> vis = new HashMap<>();
        List<String> res = new ArrayList<>();
        vis.put(beginWord, beginWord);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
//                res.add(cur);
                System.out.printf("%s\n", cur);
                if (cur.equals(endWord)) {
                    res.add(0, endWord);
                    String t = endWord;
                    while (!t.equals(beginWord)) {
                        t = vis.get(t);
                        res.add(0, t);
                    }
                    return res;
                }
                List<String> cans = transform(words, cur);
                for (String can : cans) {

                    if (!vis.containsKey(can)) {
                        vis.put(can, cur);
                        q.offer(can);
                    }

                }
            }
        }
        return new ArrayList<>();
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

}
