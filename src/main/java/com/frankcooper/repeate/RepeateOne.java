package com.frankcooper.repeate;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/6/13 21:47
 * Description
 */
public class RepeateOne {

    static RepeateOne handler = new RepeateOne();

    public static void main(String[] args) {

    }


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || !wordList.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> words = new HashSet<>(wordList);
        queue.offer(beginWord);
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                List<String> candidates = transform(curWord,words);
                for (String candidate : candidates) {
                    if (candidate.equals(endWord)) {
                        return res;
                    }
                    queue.offer(candidate);
                }
            }

        }
        return 0;
    }


    public List<String> transform(String word, Set<String> words) {
        List<String> candidates = new ArrayList<>();
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < sb.length(); i++) {
            char tmp = sb.charAt(i);
            for (char c = 'a'; c <= 'z'; c++) {
                if (tmp == c) continue;
                sb.setCharAt(i, c);
                String potential = new String(sb);
                if (words.remove(potential)) {
                    candidates.add(potential);
                }
            }
            sb.setCharAt(i, tmp);
        }
        return candidates;
    }


}
