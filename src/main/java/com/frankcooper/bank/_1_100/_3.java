package com.frankcooper.bank._1_100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _3 {
    static _3 handler = new _3();

    public static void main(String[] args) {
        handler.lengthOfLongestSubstring2nd("pwwkew");
    }

    public int lengthOfLongestSubstring1st(String s) {
        int res = 0, left = 0, right = 0;
        int n = s.length();
        Set<Character> set = new HashSet<>();
        while (right < n && left < n) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right++));
                res = Math.max(res, right - left);
            } else {
                set.remove(s.charAt(left++));
            }
        }
        return res;
    }

    public int lengthOfLongestSubstring2nd(String s) {
        int res = 0, left = 0, right = 0;
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        while (right < n) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)));
            }
            res = Math.max(res, right - left + 1);
            map.put(s.charAt(right), 1 + right++);
        }
        return res;
    }


    public int lengthOfLongestSubstring3rd(String s) {
        int res = 0, left = 0, right = 0;
        int n = s.length();
        int[] helper = new int[128];
        while (right < n) {
            left = Math.max(left, helper[s.charAt(right)]);
            res = Math.max(res, right - left + 1);
            helper[s.charAt(right)] = 1 + right++;
        }
        return res;
    }


    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.lengthOfLongestSubstring("abcabcbb");
        }

        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int l = 0, ans = 0;
            for (int r = 0; r < s.length(); r++) {
                // System.out.printf("%d\n",r);
                if (map.containsKey(s.charAt(r))) l = Math.max(l, map.get(s.charAt(r)) + 1);
                map.put(s.charAt(r), r);
                ans = Math.max(ans, r - l + 1);
            }
            return ans;
        }
    }


    static class _2nd {

        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.lengthOfLongestSubstring("abcabcbb");
        }


        public int lengthOfLongestSubstring(String s) {
            int res = 0, left = 0, right = 0;
            int n = s.length();
            int[] helper = new int[128];
            while (right < n) {
                left = Math.max(left, helper[s.charAt(right)]);
                res = Math.max(res, right - left + 1);
                helper[s.charAt(right)] = 1 + right++;
            }
            return res;
        }
    }
}