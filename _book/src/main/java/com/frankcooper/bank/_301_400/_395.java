package com.frankcooper.bank._301_400;

import org.junit.Assert;

import java.util.*;

public class _395 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public int longestSubstring(String s, int k) {
            int n = s.length();
            return dfs(s, 0, n - 1, k);
        }

        public int dfs(String s, int l, int r, int k) {
            int[] cnt = new int[26];
            for (int i = l; i <= r; i++) {
                cnt[s.charAt(i) - 'a']++;
            }

            char split = 0;
            for (int i = 0; i < 26; i++) {
                if (cnt[i] > 0 && cnt[i] < k) {
                    split = (char) (i + 'a');
                    break;
                }
            }
            if (split == 0) {
                return r - l + 1;
            }

            int i = l;
            int ret = 0;
            while (i <= r) {
                while (i <= r && s.charAt(i) == split) {
                    i++;
                }
                if (i > r) {
                    break;
                }
                int start = i;
                while (i <= r && s.charAt(i) != split) {
                    i++;
                }

                int length = dfs(s, start, i - 1, k);
                ret = Math.max(ret, length);
            }
            return ret;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "aaabb";
            int k = 3;
//            Assert.assertEquals(3, handler.longestSubstring(s, k));
            s = "ababbc";
            k = 2;
            Assert.assertEquals(5, handler.longestSubstring(s, k));
        }

        public int longestSubstring(String s, int k) {
            int ans = 0;
            int n = s.length();
            char[] cs = s.toCharArray();
            int[] cnt = new int[26];
            for (int p = 1; p <= 26; p++) {
                Arrays.fill(cnt, 0);
                // tot 代表 [j, i] 区间所有的字符种类数量；sum 代表满足「出现次数不少于 k」的字符种类数量
                for (int i = 0, j = 0, tot = 0, sum = 0; i < n; i++) {
                    int u = cs[i] - 'a';
                    cnt[u]++;
                    // 如果添加到 cnt 之后为 1，说明字符总数 +1
                    if (cnt[u] == 1) tot++;
                    // 如果添加到 cnt 之后等于 k，说明该字符从不达标变为达标，达标数量 + 1
                    if (cnt[u] == k) sum++;
                    // 当区间所包含的字符种类数量 tot 超过了当前限定的数量 p，那么我们要删除掉一些字母，即「左指针」右移
                    while (tot > p) {
                        int t = cs[j++] - 'a';
                        cnt[t]--;
                        // 如果添加到 cnt 之后为 0，说明字符总数-1
                        if (cnt[t] == 0) tot--;
                        // 如果添加到 cnt 之后等于 k - 1，说明该字符从达标变为不达标，达标数量 - 1
                        if (cnt[t] == k - 1) sum--;
                    }
                    // 当所有字符都符合要求，更新答案
                    if (tot == sum) ans = Math.max(ans, i - j + 1);
                }
            }
            return ans;
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String s = "aaabb";
            int k = 3;
//            Assert.assertEquals(3, handler.longestSubstring(s, k));
            s = "ababbc";
            k = 2;
            Assert.assertEquals(5, handler.longestSubstring(s, k));
        }


        public int longestSubstring(String s, int k) {
            if (s.length() < k) return 0;
            int[] hash = new int[26];
            for (char c : s.toCharArray()) hash[c - 'a']++;
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (hash[c - 'a'] < k && hash[c - 'a'] != 0) {
                    String part1 = s.substring(0, i);//按当前点进行切分
                    String part2 = s.substring(i + 1);
                    res = Math.max(longestSubstring(part1, k), longestSubstring(part2, k));
                    return res;
                }
            }
            return s.length();
        }


    }

    static class _4th {

        public static void main(String[] args) {
            _4th handler = new _4th();
            String s = "ababbc";
            int k = 2;
            Assert.assertEquals(5, handler.longestSubstring(s, k));
        }

        public int longestSubstring(String s, int k) {
            if (s.length() < k) return 0;
            Map<Character, Integer> counter = new HashMap();
            for (char c : s.toCharArray()) {
                counter.put(c, counter.getOrDefault(c, 0) + 1);
            }
            for (char c : counter.keySet()) {
                if (counter.get(c) < k) {
                    int res = 0;
                    for (String t : s.split(String.valueOf(c))) {
                        res = Math.max(res, longestSubstring(t, k));
                    }
                    return res;
                }
            }
            return s.length();
        }

    }

    static class _5th {
        public static void main(String[] args) {
            _5th handler = new _5th();
            String s = "ababbc";
            int k = 2;
            Assert.assertEquals(5, handler.longestSubstring(s, k));
        }

        public int longestSubstring(String s, int k) {
            if (s == null || s.length() == 0) return 0;
            char[] chars = new char[26];
            // record the frequency of each character
            for (int i = 0; i < s.length(); i += 1) chars[s.charAt(i) - 'a'] += 1;
            boolean flag = true;
            for (int i = 0; i < chars.length; i += 1) {
                if (chars[i] < k && chars[i] > 0) flag = false;
            }
            // return the length of string if this string is a valid string
            if (flag) return s.length();
            int result = 0;
            int start = 0, cur = 0;
            // otherwise we use all the infrequent elements as splits
            while (cur < s.length()) {
                if (chars[s.charAt(cur) - 'a'] < k) {
                    result = Math.max(result, longestSubstring(s.substring(start, cur), k));
                    start = cur + 1;
                }
                cur++;
            }
            result = Math.max(result, longestSubstring(s.substring(start), k));
            return result;
        }
    }

    static class _6th {
        public int longestSubstring(String s, int k) {
            if (s == null || s.length() == 0) return 0;
            if (k < 2) return s.length();
            return helper(s, 0, s.length(), k);
        }

        public int helper(String s, int l, int r, int k) {
            if (l >= r) return 0;

            // build freq map
            int[] freq = new int[26];
            for (int i = l; i < r; i++) freq[s.charAt(i) - 'a']++;

            // check if valid
            boolean valid = true;
            for (int i = 0; i < 26 && valid; i++) if (freq[i] > 0 && freq[i] < k) valid = false;
            if (valid) return r - l;

            // if not for each invalid character start a new split search
            int best = 0, start = l;
            for (int i = l; i < r; i++) {
                if (freq[s.charAt(i) - 'a'] < k) {
                    best = Math.max(best, helper(s, start, i, k));
                    start = i + 1;
                }
            }
            best = Math.max(best, helper(s, start, r, k));
            return best;
        }
    }
}
