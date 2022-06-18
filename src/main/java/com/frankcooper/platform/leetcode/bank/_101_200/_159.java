package com.frankcooper.platform.leetcode.bank._101_200;

import java.util.*;

public class _159 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int lengthOfLongestSubstringTwoDistinct(String s) {
            return helper(s, 2);
        }

        public int helper(String s, int k) {
            int res = 0;
            Map<Character, Integer> map = new HashMap<>();//k:字符，v:该字符最近一次出现的位置
            int l = 0;//左窗口
            for (int i = 0; i < s.length(); i++) {
                map.put(s.charAt(i), i);//将当前字符和字符的位置关系记录下来
                while (map.size() > k) {//总的字符开始超过k个
                    //如果 s[l]的 位置和l 不同，说明在[l+1 ... i ]之间又出现了字符s[l]，这是不能移除s[l],反之则移除s[l]
                    if (map.get(s.charAt(l)) == l) map.remove(s.charAt(l));
                    ++l;
                }
                res = Math.max(res, i - l + 1);
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int lengthOfLongestSubstringTwoDistinct(String s) {
            return helper(s, 2);
        }

        public int helper(String s, int k) {
            int res = 0;
            Map<Character, Integer> map = new HashMap<>();//k:字符，v:字符出现的次数
            int l = 0;//左边窗口的位置
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
                while (map.size() > k) {//字符的个数已经超过k个了，开始缩小左边窗口
                    char lc = s.charAt(l++);//left char
                    int t = map.get(lc) - 1;//left char的数量
                    if (t == 0) {//数量为0的时候，key被移除
                        map.remove(lc);
                    } else {
                        map.put(lc, t);//-1后数量再次更新进去
                    }
                }
                res = Math.max(res, i - l + 1);//计算长度
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            String s = "ecebaaa";
            handler.lengthOfLongestSubstringTwoDistinct(s);
        }


        //1. 若当前字符和前一个字符相同，继续循环。
        //2. 若不同，看当前字符和 right 指的字符是否相同
        //    (1) 若相同，left 不变，右边跳到 i - 1
        //    (2) 若不同，更新结果，left 变为 right+1，right 变为 i - 1
        //最后需要注意在循环结束后，还要比较结果 res 和 s.size() - left 的大小，返回大的，这是由于如果字符串是 "ecebaaa"，
        // 那么当 left=3 时，i=5,6 的时候，都是继续循环，当i加到7时，跳出了循环，而此时正确答案应为 "baaa" 这4个字符，而我们的结果 res 只更新到了 "ece" 这3个字符，所以最后要判断 s.size() - left 和结果 res 的大小。
        int lengthOfLongestSubstringTwoDistinct(String s) {
            int left = 0, right = -1, res = 0;
            char[] ch = s.toCharArray();
            for (int i = 1; i < s.length(); ++i) {
                if (ch[i] == ch[i - 1]) continue;
                if (right >= 0 && ch[right] != ch[i]) {
                    res = Math.max(res, i - left);
                    left = right + 1;
                }
                right = i - 1;
            }
            return Math.max(s.length() - left, res);
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
