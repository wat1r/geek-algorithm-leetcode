package com.frankcooper.platform.leetcode.interview;

import java.util.*;

public class _08_07 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.permutation("qwe");
        }


        List<String> list = new ArrayList<>();

        public String[] permutation(String S) {
            dfs(S, new StringBuilder());
            String[] res = new String[list.size()];
            return list.toArray(res);
        }


        /**
         * @param S   源字符
         * @param cur 当前收集到的字符
         */
        private void dfs(String S, StringBuilder cur) {
            if (cur.length() == S.length()) {//当前收集到的字符达到源字符长度，本轮结束
                list.add(cur.toString());
                return;
            }
            for (int i = 0; i < S.length(); i++) {//遍历源字符
                //题意说字符都没有重复的，当前收集的字符cur如果包含了待加入的字符，说明该字符在上一轮已经加入过了
                if (cur.toString().contains(S.charAt(i) + "")) continue;
                cur.append(S.charAt(i));//添加
                dfs(S, cur);//下一层
                cur.deleteCharAt(cur.length() - 1);//回溯
            }
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.permutation("qwe");
        }


        List<String> list = new ArrayList<>();

        public String[] permutation(String S) {
            char[] chas = S.toCharArray();
            dfs(chas,0);
            String[] res = new String[list.size()];
            return list.toArray(res);
        }


        /**
         *
         * @param chas
         * @param idx 当前遍历的chas的下标
         */
        private void dfs(char[] chas, int idx) {
            if (idx == chas.length - 1) {//返回
                list.add(String.valueOf(chas));
                return;
            }
            for (int i = idx; i < chas.length; i++) {
                swap(chas, i, idx);//交换
                dfs(chas, idx + 1);//当前的idx+1 进入下一轮
                swap(chas, i, idx);//回溯
            }
        }

        private void swap(char[] chas, int i, int j) {
            char t = chas[i];
            chas[i] = chas[j];
            chas[j] = t;
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
