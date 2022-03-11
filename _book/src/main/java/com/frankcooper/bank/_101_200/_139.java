package com.frankcooper.bank._101_200;

import java.util.*;

import org.junit.Assert;

public class _139 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }


        public boolean wordBreak(String s, List<String> wordDict) {
            if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
                return false;
            }
            int n = s.length();
            //f[i]表示以s[i-1]结尾的字符串能否拆分成wordDict
            boolean[] f = new boolean[n + 1];
            f[0] = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    if (f[j] && wordDict.contains(s.substring(j, i))) {
                        f[i] = true;
                        break;
                    }
                }
            }
            return f[n];
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        //set记录当前字符位置，不能匹配wordDict 的索引，那些返回为false的结果
        Set<Integer> set = new HashSet<>();
        String s;
        List<String> wordDict;

        public boolean wordBreak(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            return dfs(0);
        }


        private boolean dfs(int idx) {
            if (idx == s.length()) return true;
            if (set.contains(idx)) return false;
            for (int i = idx + 1; i <= s.length(); i++) {
                if (!wordDict.contains(s.substring(idx, i))) continue;
                if (dfs(i)) return true;
                else set.add(i);
            }
            set.add(idx);
            return false;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }


        Map<Integer, Boolean> cache = new HashMap<>();
        String s;
        List<String> wordDict;

        public boolean wordBreak(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            return dfs(0);
        }


        private boolean dfs(int idx) {
            if (cache.containsKey(idx)) return cache.get(idx);
            if (idx == s.length()) return true;
            for (int i = idx + 1; i <= s.length(); i++) {
                if (!wordDict.contains(s.substring(idx, i))) continue;
                if (dfs(i)) {
                    cache.put(i, true);
                    return true;
                } else {
                    cache.put(i, false);
                }

            }
            cache.put(idx, false);
            return false;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        //set记录当前字符位置，不能匹配wordDict 的索引，那些返回为false的结果
        Boolean[] cache;
        String s;
        List<String> wordDict;

        public boolean wordBreak(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            cache = new Boolean[s.length() + 1];
            return dfs(0);
        }


        private boolean dfs(int idx) {
            if (cache[idx] != null) return cache[idx];
            if (idx == s.length()) return true;
            for (int i = idx + 1; i <= s.length(); i++) {
                if (!wordDict.contains(s.substring(idx, i))) continue;
                if (dfs(i)) {
                    return cache[i] = true;
                } else {
                    cache[i] = false;
                }
            }
            return cache[idx] = false;
        }
    }

    static class _5th {
        public boolean wordBreak(String s, List<String> wordDict) {
            Queue<Integer> q = new LinkedList<>();
            Set<Integer> vis = new HashSet<>();
            q.offer(0);
            while (!q.isEmpty()) {
                int curIdx = q.poll();
                for (int i = curIdx; i <= s.length(); i++) {
                    if (vis.contains(i)) continue;
                    if (wordDict.contains(s.substring(curIdx, i))) {
                        if (i == s.length()) return true;
                        q.offer(i);
                        vis.add(i);
                    }
                }
            }
            return false;
        }
    }
}
