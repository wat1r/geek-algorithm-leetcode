package com.frankcooper.platform.leetcode.bank._101_200;

import java.util.*;

public class _187 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }

        public List<String> findRepeatedDnaSequences(String s) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length() - 9; i++) {
                String sequences = s.substring(i, i + 10);
                map.put(sequences, map.getOrDefault(sequences, 0) + 1);
            }
            ArrayList<String> res = new ArrayList<>();
            for (Map.Entry<String, Integer> e : map.entrySet()) {
                if (e.getValue() > 1) {
                    res.add(e.getKey());
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
            handler.findRepeatedDnaSequences(s);
        }

        static final int L = 10;
        Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
            put('A', 0); //00
            put('C', 1); //01
            put('G', 2); //10
            put('T', 3); //11
        }};

        public List<String> findRepeatedDnaSequences(String s) {
            List<String> ans = new ArrayList<String>();
            int n = s.length();
            if (n <= L) {
                return ans;
            }
            int x = 0;
            for (int i = 0; i < L - 1; ++i) {
                x = (x << 2) | bin.get(s.charAt(i));
            }

            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int i = 0; i <= n - L; ++i) {
                x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
                cnt.put(x, cnt.getOrDefault(x, 0) + 1);
                if (cnt.get(x) == 2) {
                    ans.add(s.substring(i, i + L));
                }
            }
            return ans;
        }

//        作者：力扣官方题解
//        链接：https://leetcode.cn/problems/repeated-dna-sequences/solutions/1035568/zhong-fu-de-dnaxu-lie-by-leetcode-soluti-z8zn/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

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
