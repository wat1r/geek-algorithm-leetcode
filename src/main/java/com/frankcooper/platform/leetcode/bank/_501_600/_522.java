package com.frankcooper.platform.leetcode.bank._501_600;

import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

public class _522 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            String[] strs = {"aba", "cdc", "eae"};
            String[] strs = {"abcd"};
            handler.findLUSlength(strs);

        }


        public int findLUSlength(String[] strs) {
            Map<String, Integer> map = new HashMap<>();
            for (String str : strs) {
                int n = str.length();
                //从低位到高位，如果该位位1 表示这一位的字符会被扣掉，不会组成下面的seq的子序列
                //例如 abcd 如果 为0000表示 abcd均可用
                //         如果  为0001表示 a这一个字符不可用 bcd可用
                //         如果  为1001表示，ad这两个字符不可用 bc可用
                for (int i = 0; i < (1 << n); i++) {
                    String seq = "";
                    for (int j = 0; j < n; j++) {
                        if (((i >> j) & 1) == 0) {
                            seq += str.charAt(j);
                        }
                    }
                    System.out.printf("i:%d,bit:%3s,%s\n", i, PrintUtils.toBinaryString(i, 4), seq);
                    map.put(seq, map.getOrDefault(seq, 0) + 1);
                }
            }
            int res = -1;
            //统一只有孤立的一个字序列的字符串
            for (String k : map.keySet()) {
                if (map.get(k) == 1) res = Math.max(res, k.length());
            }
            return res;
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            String x = "abd", y = "abcde";
//            handler.isSubsequence(x, y);
            String[] strs = {"bd", "e", "abc", "ab", "abc"};
//            handler.findLUSlength(strs);
            Assert.assertEquals(-1, handler.findLUSlength(new String[]{"aaa", "aaa", "aa"}));
        }

        //如果s是t的一个子序列，返回true，否则返回false
        public boolean isSubsequence(String s, String t) {
            int i = 0, j = 0;
            for (; i < s.length() && j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                }
            }
            return i == s.length();
        }

        public int findLUSlength(String[] strs) {
            int res = -1;
            for (int i = 0, j; i < strs.length; i++) {
                for (j = 0; j < strs.length; j++) {
                    if (j == i)
                        continue;
                    // isSubsequence(String s, String t) 表示s是否是t的一个子序列
                    // 提前break说明str[i] 已经是 strs[j]的子序列
                    // 结合  「最长特殊序列 定义如下：该序列为某字符串 独有的最长子序列（即不能是其他字符串的子序列）。」
                    //str[i] 即是 str[i] 也是 str[j] 的子序列，不满足 「独有」的条件
                    if (isSubsequence(strs[i], strs[j])) {
                        System.out.printf("%s %s\n", strs[i], strs[j]);
                        break;
                    }

                }
                if (j == strs.length)
                    res = Math.max(res, strs[i].length());
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
