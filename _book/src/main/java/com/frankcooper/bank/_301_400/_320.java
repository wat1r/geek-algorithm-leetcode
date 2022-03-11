package com.frankcooper.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _320 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.generateAbbreviations("word");
        }

        List<String> res = new ArrayList<>();

        public List<String> generateAbbreviations(String word) {
            dfs(word, new StringBuilder(), 0, 0);
            return res;
        }

        private void dfs(String src, StringBuilder cur, int tailNum, int idx) {
            //tailNum 表示尾部现在有多少个连续的缩写，i 表示遍历到哪了
            if (idx == src.length()) {
                if (tailNum > 0) {
                    cur.append(tailNum);
                    res.add(cur.toString());
                    return;
                }
            }
            int curLen = cur.length();
            dfs(src, cur, tailNum + 1, idx + 1);
            cur.deleteCharAt(cur.length() - 1);
            if (tailNum > 0) {
                cur.append(tailNum);
            }
            cur.append(src.charAt(idx));
            dfs(src, cur, 0, idx + 1);
            cur.deleteCharAt(cur.length() - 1);

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
