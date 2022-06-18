package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.*;

public class _17 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        //map映射 数字和字母键
        Map<Character, List<String>> map;
        //结果集
        List<String> res = new ArrayList<>();

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) return res;
            //准备map
            map = new HashMap<Character, List<String>>() {{
                put('2', Arrays.asList("a", "b", "c"));
                put('3', Arrays.asList("d", "e", "f"));
                put('4', Arrays.asList("g", "h", "i"));
                put('5', Arrays.asList("j", "k", "l"));
                put('6', Arrays.asList("m", "n", "o"));
                put('7', Arrays.asList("p", "q", "r", "s"));
                put('8', Arrays.asList("t", "u", "v"));
                put('9', Arrays.asList("w", "x", "y", "z"));
            }};
            dfs(new ArrayList<>(), digits.toCharArray(), 0);
            return res;


        }

        /**
         * @param sub  当前手机的字符的list
         * @param chas digits
         * @param idx  当前递归到了digits[i] i的位置
         */
        private void dfs(List<String> sub, char[] chas, int idx) {
            //出口：sub 和 chas的size一样的时，说明这一轮要结束了
            if (chas.length == sub.size()) {
                StringBuilder sb = new StringBuilder();
                for (String s : sub) sb.append(s);
                res.add(sb.toString());
                return;
            }
            //获取到当前的idx对应的键盘字母
            List<String> candidates = map.get(chas[idx]);
            //遍历
            for (String can : candidates) {
                sub.add(can);//添加
                dfs(sub, chas, idx + 1);//下一个索引
                sub.remove(sub.size() - 1);//恢复
            }
        }

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
