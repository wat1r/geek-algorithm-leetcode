package com.frankcooper.platform.leetcode.bank._1_100;

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

    static class _3rd {
        /**
         * 借助set控制滑窗
         *
         * @param s
         * @return
         */
        public int lengthOfLongestSubstring(String s) {
            Set<Character> set = new HashSet<>();
            int maxLen = 0;//最大长度
            int i = 0, j = 0;//滑窗
            while (i < s.length() && j < s.length()) {
                //滑窗右区间j还可以外扩（不在set集合内，说明该字符满足非重复字符的条件）
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j++));//右移滑窗
                    maxLen = Math.max(maxLen, j - i);//更新滑窗大小
                } else {
                    //一直移动到不出现s.charAt(j)字符为止
                    set.remove(s.charAt(i++));
                }
            }
            return maxLen;
        }
    }

    static class _4th {

        public static void main(String[] args) {
            _4th handler = new _4th();
            String s = "abcabcbb";
            handler.lengthOfLongestSubstring(s);
        }

        public int lengthOfLongestSubstring(String s) {
            int n = s.length(), l = 0, r = 0;
            //数组控制滑窗,arr记录的当前字符从右往左看，第一次出现的位置（下标索引）
            int[] arr = new int[128];
            int maxLen = 0;
            while (r < n) {
                //窗口左边界出现位置（从右往左看，第一次出现该字符的位置）
                l = Math.max(l, arr[s.charAt(r)]);
                //更新窗口的大小
                maxLen = Math.max(maxLen, r - l + 1);
                //滑窗右边界进来时，不断更新/覆盖滑窗右边界字符的位置
                arr[s.charAt(r)] = 1 + r;
                r++;
            }
            return maxLen;
        }
    }

    static class _5th {
        public int lengthOfLongestSubstring(String s) {
            char[] ch = s.toCharArray();
            Set<Character> set = new HashSet<>();
            int n = ch.length;
            int j = 0;
            int res = 0;
            for (int i = 0; i < n; i++) {
                while (j < n && !set.contains(ch[j])) {
                    set.add(ch[j++]);
                }
                res = Math.max(res, j - i);
                set.remove(ch[i]);
            }
            return res;
        }
    }

    static class _6th {

        public int lengthOfLongestSubstring(String s) {
            int res = 0, l = 0, r = 0;
            int n = s.length();
            Set<Character> set = new HashSet<>();
            while (l < n && r < n) {
                if (!set.contains(s.charAt(r))) {
                    set.add(s.charAt(r++));
                    res = Math.max(res, r - l);
                } else {
                    set.remove(s.charAt(l++));
                }
            }
            return res;
        }
    }
}