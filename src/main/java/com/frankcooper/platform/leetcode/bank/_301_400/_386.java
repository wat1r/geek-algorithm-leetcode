package com.frankcooper.platform.leetcode.bank._301_400;

import java.util.*;

public class _386 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
            handler.lexicalOrder(13);
        }

        List<Integer> res = new ArrayList<>();

        public List<Integer> lexicalOrder(int n) {
            dfs(0, n);
            return res;
        }

        private void dfs(int prev, int n) {
            int cur = prev * 10;
            if (prev > n) return;
            for (int i = 0; i < 10; i++) {
                int next = cur + i;
                if (next <= n && next != 0) {
                    res.add(next);
                    dfs(next, n);
                }
            }
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.lexicalOrder(13);
        }

        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            int x = 1;
            //list的大小=n
            while (res.size() < n) {
                //每次将当前层 *10进入下一层
                while (x <= n) {
                    res.add(x);
                    x *= 10;
                }
                //如果当前层的元素已经从9跃升到10这个阶梯或者当前层元素比n大，返回上一层
                while (x % 10 == 9 || x > n) {
                    x /= 10;
                }
                //当前层遍历完，递进1
                x++;
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                dfs(i, n, res);
            }
            return res;
        }

        private void dfs(int i, int n, List<Integer> res) {
            if (i > n) return;
            res.add(i);
            for (int j = 0; j <= 9; j++) {
                dfs(i * 10 + j, n, res);
            }
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }


        class TrieNode {
            boolean isNum;
            Map<Character, TrieNode> map;

            public TrieNode() {
                this.isNum = false;
                this.map = new HashMap<>();
            }


            public void insert(int num) {
                TrieNode cur = this;
                String s = String.valueOf(num);
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if (!cur.map.containsKey(c)) {
                        cur.map.put(c, new TrieNode());
                    }
                    cur = cur.map.get(c);
                }
                cur.isNum = true;
            }

            public void lexicalOrder(TrieNode root, StringBuilder sb, List<Integer> res) {
                if (root.isNum) res.add(Integer.parseInt(sb.toString()));
                for (Map.Entry<Character, TrieNode> e : root.map.entrySet()) {
                    sb.append(e.getKey());
                    lexicalOrder(e.getValue(), sb, res);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }


        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            TrieNode root = new TrieNode();
            for (int i = 1; i <= n; i++) root.insert(i);
            root.lexicalOrder(root, new StringBuilder(), res);
            return res;
        }

    }
}
