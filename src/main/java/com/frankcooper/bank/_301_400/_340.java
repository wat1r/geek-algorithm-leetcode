package com.frankcooper.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _340 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "eceba";
            int k = 2;
            handler.lengthOfLongestSubstringKDistinct(s, k);

        }


        public int lengthOfLongestSubstringKDistinct(String s, int k) {
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

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int lengthOfLongestSubstringKDistinct(String s, int k) {
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


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }



    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
