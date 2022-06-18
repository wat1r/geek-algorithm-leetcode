package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _16_20 {

    /**
     * TLE
     */
    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            handler.getValidT9Words("8733", new String[]{"tree", "used"});

        }

        Map<Character, List<String>> dict = new HashMap<>();
        char[] chas;
        Set<String> set;
        List<String> res = new ArrayList<>();


        public List<String> getValidT9Words(String num, String[] words) {
            chas = num.toCharArray();
            set = new HashSet<>(Arrays.asList(words));
            process();
            return res;
        }


        public void process() {
            dict.put('2', Arrays.asList("a", "b", "c"));
            dict.put('3', Arrays.asList("d", "e", "f"));
            dict.put('4', Arrays.asList("g", "h", "i"));
            dict.put('5', Arrays.asList("j", "k", "l"));
            dict.put('6', Arrays.asList("m", "n", "o"));
            dict.put('7', Arrays.asList("p", "q", "r", "s"));
            dict.put('8', Arrays.asList("t", "u", "v"));
            dict.put('9', Arrays.asList("w", "x", "y", "z"));
            dfs(new ArrayList<String>(), 0);
        }


        private void dfs(List<String> sub, int idx) {
            if (sub.size() == chas.length) {
                StringBuilder sb = new StringBuilder();
                for (String s : sub) sb.append(s);
                if (set.contains(sb.toString())) {
                    res.add(sb.toString());
                }
                return;
            }
            for (String seed : dict.get(chas[idx])) {
                sub.add(seed);
                dfs(sub, idx + 1);
                sub.remove(sub.size() - 1);
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.getValidT9Words("8733", new String[]{"tree", "used"});
        }

        public List<String> getValidT9Words(String num, String[] words) {
            List<String> res = new ArrayList<>();
            char[] map = {'2', '2', '2', '3', '3', '3', '4', '4', '4', '5', '5', '5', '6', '6', '6', '7', '7', '7', '7', '8', '8', '8', '9', '9', '9', '9'};
            for (String word : words) {
                int index = 0;
                boolean flag = true;
                for (char c : word.toCharArray()) {
                    char n = map[c - 'a'];
                    if (n != num.charAt(index++)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) res.add(word);
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
