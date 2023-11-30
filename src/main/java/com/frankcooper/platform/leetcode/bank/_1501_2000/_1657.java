package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;

import static java.nio.charset.StandardCharsets.ISO_8859_1;


public class _1657 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public boolean closeStrings(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            int sMask = 0, tMask = 0;
            int[] sCnt = new int[26], tCnt = new int[26];
            for (byte c : s.getBytes(ISO_8859_1)) { // 比 toCharArray 更快
                sMask |= 1 << (c - 'a'); // 记录 s 中有字符 c
                sCnt[c - 'a']++;
            }
            for (byte c : t.getBytes(ISO_8859_1)) {
                tMask |= 1 << (c - 'a'); // 记录 t 中有字符 c
                tCnt[c - 'a']++;
            }

            Arrays.sort(sCnt);
            Arrays.sort(tCnt);
            return sMask == tMask && Arrays.equals(sCnt, tCnt);
        }

//        作者：灵茶山艾府
//        链接：https://leetcode.cn/problems/determine-if-two-strings-are-close/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
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
