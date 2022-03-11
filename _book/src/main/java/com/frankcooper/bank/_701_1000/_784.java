package com.frankcooper.bank._701_1000;

import java.util.ArrayList;
import java.util.List;

/*import java.util.*;
import org.junit.Assert;*/
public class _784 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "a1b2";
            handler.letterCasePermutation(s);

        }


        List<String> res = new ArrayList<>();

        public List<String> letterCasePermutation(String s) {
            int n = s.length();
            char[] chs = new char[n];
            dfs(chs, 0, n, s);
            return res;
        }


        private void dfs(char[] chs, int idx, int n, String s) {
            if (idx == n) {
                res.add(new String(chs));
                return;
            }
            chs[idx] = s.charAt(idx);
            dfs(chs, idx + 1, n, s);
            if (Character.isLetter(chs[idx])) {
                chs[idx] = (char) (s.charAt(idx) ^ ' ');
                dfs(chs, idx + 1, n, s);
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        List<String> res = new ArrayList<>();

        public List<String> letterCasePermutation(String s) {
            int n = s.length();
            char[] chas = s.toCharArray();
            dfs(chas, n, 0);
            return res;
        }

        private void dfs(char[] chas, int n, int startIdx) {
            res.add(new String(chas));
            for (int i = startIdx; i < n; i++) {
                if (Character.isLetter(chas[i])) {
                    char t = chas[i];
//                    chas[i] = (char)(chas[i] - 'a' >= 0 ? chas[i] - 32 : chas[i] + 32);
                    chas[i] ^= ' ';
                    dfs(chas, n, i + 1);
                    chas[i] = t;
                }
            }

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
