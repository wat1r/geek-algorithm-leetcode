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
                List<String> candidates = transform(curWord, words);
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


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.mySqrt(9);
            handler.mySqrt(2147395599);
//            handler.mySqrt1(2);
        }

        public int mySqrt(int x) {
            long l = 0, r = x;
            while (l < r) {
                long m = l + (r - l) / 2;
                long t = m * m;
                if (t == x) return (int) m;
                else if (t > x) r = m;
                else if (t < x) l = m + 1;
            }
            return (int) (l * l == x ? l : l - 1);
        }

        public int mySqrt1(int x) {
            long l = 0, r = x;
            while (l < r) {
                long m = l + (r - l) / 2;
                if ((int) m * m >= x) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            return (int) (l * l == x ? l : l - 1);
        }
    }

    //887
    static class _2nd {
        int INF = Integer.MAX_VALUE >> 1;

        public int superEggDrop(int K, int N) {
            //f[i][j]：i层楼，j个鸡蛋的时候，找到临界楼层的操作次数
            int[][] f = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    f[i][j] = INF;
                }
            }
            for (int i = 1; i <= N; i++) f[i][1] = i;
            for (int j = 1; j <= K; j++) f[1][j] = 1;
            for (int i = 2; i <= N; i++) {
                for (int j = 2; j <= K; j++) {
                    int l = 1, r = i;
                    while (l < r) {
                        int mid = l + (r - l + 1) / 2;
                        int t1 = f[mid - 1][j - 1];
                        int t2 = f[i - mid][j];
                        if (t1 > t2) {
                            r = mid - 1;
                        } else {
                            l = mid;
                        }
                    }
                    f[i][j] = Math.min(f[i][j], Math.max(f[l - 1][j - 1], f[i - l][j]) + 1);
                }
            }

            return f[N][K];
        }
    }

    static class _3rd{

    }
}
