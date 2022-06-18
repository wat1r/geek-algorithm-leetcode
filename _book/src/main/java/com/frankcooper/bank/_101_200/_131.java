package com.frankcooper.platform.leetcode.bank._101_200;


import java.util.*;


public class _131 {

    static class _1st {
        List<List<String>> res = new ArrayList<>();

        public List<List<String>> partition(String s) {
            dfs(s, 0, s.length(), new ArrayList<>());
            return res;
        }


        private void dfs(String s, int curr, int total, ArrayList<String> sub) {
            if (curr == total) {
                res.add(new ArrayList<>(sub));
                return;
            }
            for (int i = curr; i < total; i++) {
                if (isPalindrome(s, curr, i)) {
                    sub.add(s.substring(curr, i + 1));
                    dfs(s, i + 1, total, sub);
                    sub.remove(sub.size() - 1);
                }
            }
        }

        private boolean isPalindrome(String s, int l, int r) {
            while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
            return true;
        }
    }


    static class _2nd {


        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.partition("aab");
        }


        Map<String, List<List<String>>> memo = new HashMap<>();

        public List<List<String>> partition(String s) {
            List<List<String>> dfs = dfs(s);
            return dfs;
        }

        private List<List<String>> dfs(String s) {
            if (memo.containsKey(s)) return memo.get(s);
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.length() == 0) res.add(new ArrayList<>());
            for (int i = 0; i < s.length(); i++) {
                if (isPalindrome(s, 0, i)) {
                    String left = s.substring(0, i + 1);
                    for (List<String> rightList : dfs(s.substring(i + 1))) {
                        List<String> sub = new ArrayList<>();
                        sub.add(left);
                        sub.addAll(rightList);
                        res.add(sub);
                    }
                }
            }
            memo.put(s, res);
            return res;
        }


        private boolean isPalindrome(String s, int l, int r) {
            while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
            return true;
        }
    }


    static class _3rd {


        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.partition("aab");
        }


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
    }


    static class _4th {

        public static void main(String[] args) {
            _4th handler = new _4th();
            handler.partition("aab");
        }


        List<List<String>> res = new ArrayList<>();

        public List<List<String>> partition(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            for (int j = 0; j < n; j++) {
                for (int i = 0; i <= j; i++) {
                    if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) dp[i][j] = true;
                }
            }
            dfs(s, 0, n, new ArrayList<>(), dp);
            return res;
        }

        private void dfs(String s, int i, int n, List<String> sub, boolean[][] dp) {
            if (i == n) {
                res.add(new ArrayList<>(sub));
                return;
            }
            for (int j = i; j < n; j++) {
                if (dp[i][j]) {
                    sub.add(s.substring(i, j + 1));
                    dfs(s, j + 1, n, sub, dp);
                    sub.remove(sub.size() - 1);
                }
            }
        }
    }


    static class _5th {
        public static void main(String[] args) {
            String s = "aab";
            s = "abbab";
            _5th handler = new _5th();
            handler.partition(s);
        }

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
    }


    static class _6th {

        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();

        public List<List<String>> partition(String s) {
            dfs(s, 0);
            return res;
        }

        public void dfs(String s, int index) {
            if (index >= s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = index; i < s.length(); i++) {
                if (!check(s, index, i)) continue;
                path.add(s.substring(index, i + 1));
                dfs(s, i + 1);
                path.remove(path.size() - 1);
            }
        }


        private boolean check(String s, int l, int r) {
            while (l < r) {
                if (s.charAt(l) != s.charAt(r)) return false;
                l++;
                r--;
            }
            return true;
        }

    }


    static class _7th {
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
    }


}
