package com.frankcooper.lintcode;

import java.util.*;

public class _892 {
    public static void main(String[] args) {
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
//        words = new String[]{"zy", "zx"};
        words = new String[]{"abc", "ab"};
        _892 handler = new _892();
        handler.alienOrder(words);
    }


    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> g = new HashMap<>();
        int[] indegree = new int[26];
        boolean isSamePrefix = buildGraph(g, indegree, words);
        if (isSamePrefix) return "";
        return bfs(g, indegree);

    }


    private boolean buildGraph(Map<Character, Set<Character>> g, int[] indegree, String[] words) {
        for (String word : words) for (char w : word.toCharArray()) g.putIfAbsent(w, new HashSet<>());
        boolean isSamePrefix = true;
        for (int i = 1; i < words.length; i++) {
            String fw = words[i - 1], sw = words[i];//前后两个相邻的单词
            int len = Math.min(fw.length(), sw.length());
            int j = 0;
            for (; j < len; j++) {
                if (fw.charAt(j) != sw.charAt(j)) {//两个单词的字符不相等
                    char u = fw.charAt(j), v = sw.charAt(j);//u是出 v是入 u->v
                    if (!g.get(u).contains(v)) {//图里没有的话加入，更新入度
                        g.get(u).add(v);
                        indegree[v - 'a']++;
                    }
                    isSamePrefix = false;
                    break;//之后的已经没有比较意义了
                }
            }
            if (j == len && fw.length() > sw.length()) {
                isSamePrefix = true;
                return isSamePrefix;
            }
        }
        return isSamePrefix;
    }

    private String bfs(Map<Character, Set<Character>> g, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for (char c : g.keySet()) {
            if (indegree[c - 'a'] == 0) {
                q.offer(c);
                sb.append(c);
            }
        }

        while (!q.isEmpty()) {
            char u = q.poll();
//            if (g.get(u) == null || g.get(u).isEmpty()) continue;
            for (char v : g.get(u)) {
                indegree[v - 'a']--;
                if (indegree[v - 'a'] == 0) {
                    q.offer(v);
                    sb.append(v);
                }
            }
        }
        return sb.toString().length() == g.size() ? sb.toString() : "";
    }


}
