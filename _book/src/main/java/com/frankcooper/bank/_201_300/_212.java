package com.frankcooper.bank._201_300;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Date 2020/7/23
 * @Author Frank Cooper
 * @Description
 */
public class _212 {

    static class _1st {

        public static void main(String[] args) {
            _1st handler = new _1st();
            char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
            String[] words = {"oath", "pea", "eat", "rain"};
            handler.findWords(board, words);
        }


        class TrieNode {
            private TrieNode[] next = new TrieNode[26];
            private String word = null;
        }


        List<String> result = new ArrayList<>();
        int m;//行
        int n;//列
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public List<String> findWords(char[][] board, String[] words) {

            //1.构造前缀树，遍历words，在里层遍历每一个word
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode curr = root;
                for (char c : word.toCharArray()) {
                    if (curr.next[c - 'a'] == null) {
                        curr.next[c - 'a'] = new TrieNode();
                    }
                    curr = curr.next[c - 'a'];
                }
                curr.word = word;
            }
            //2.对单元格进行回溯
            m = board.length;
            n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (root.next[board[i][j] - 'a'] != null) {
                        backtracing(board, i, j, root);
                    }
                }
            }

            return result;
        }

        private void backtracing(char[][] board, int i, int j, TrieNode root) {
            char c = board[i][j];
            TrieNode curr = root.next[c - 'a'];
            if (curr != null && curr.word != null) {
                result.add(curr.word);
                curr.word = null;
            }
            board[i][j] = '#';
            for (int k = 0; k < 4; k++) {
                int nextI = i + directions[k][0];
                int nextJ = j + directions[k][1];
                if ((nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) || board[nextI][nextJ] == '#') continue;
                if (curr != null) backtracing(board, nextI, nextJ, curr);
            }
            board[i][j] = c;
        }
    }

    static class _2nd {


        public static void main(String[] args) {
            _2nd handler = new _2nd();
            char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
            String[] words = {"oath", "pea", "eat", "rain"};
            handler.findWords(board, words);
        }


        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int m, n;

        List<String> res = new ArrayList<>();

        public List<String> findWords(char[][] board, String[] words) {
            this.m = board.length;
            this.n = board[0].length;
            for (String word : words) {
                if (exist(board, word)) res.add(word);
            }
            return res;
        }


        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0) && dfs(word, board, i, j, 0)) return true;
                }
            }
            return false;
        }

        private boolean dfs(String word, char[][] board, int i, int j, int idx) {
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(idx))
                return false;
            if (idx == word.length() - 1) return true;
            char t = board[i][j];
            board[i][j] = '#';
            for (int d = 0; d < dx.length; d++) {
                if (dfs(word, board, i + dx[d], j + dy[d], idx + 1)) {
                    board[i][j] = t;//这里和79题不一样，需要恢复现场
                    return true;
                }
            }
            board[i][j] = t;
            return false;
        }
    }


    static class _3rd {

        class TrieNode {
            TrieNode[] next = new TrieNode[26];
            String word;
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int m, n;
        List<String> res = new ArrayList<>();

        public List<String> findWords(char[][] board, String[] words) {
            this.m = board.length;
            this.n = board[0].length;
            TrieNode root = buildTrie(words);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(board, i, j, root);
                }
            }
            return res;
        }

        private void dfs(char[][] board, int i, int j, TrieNode p) {
            if (i < 0 || i >= m || j < 0 || j >= n) return;
            char c = board[i][j];
            //当前的字符被搜索过（设置成#）|| 没有可供备选的单词了
            if (c == '#' || p.next[c - 'a'] == null) return;
            p = p.next[c - 'a'];//当前的节点存在，选word不是null的加入到结果集
            if (p.word != null) {
                res.add(p.word);
                //去重，如[["o","a","b","n"],["o","t","a","e"],["a","h","k","r"],["a","f","l","v"]]
                //["oa","oaa"]
                //输出["oa","oa","oaa"] 其中有"oa"重复了
                p.word = null;
            }
            //进入下一层
            board[i][j] = '#';
            for (int d = 0; d < dx.length; d++) {
                dfs(board, i + dx[d], j + dy[d], p);
            }
            board[i][j] = c;
        }


        //建Trie，返回根节点,记录Trie上的单词
        private TrieNode buildTrie(String[] words) {
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode cur = root;//每一个单词从根节点出发
                for (char c : word.toCharArray()) {
                    if (cur.next[c - 'a'] == null) cur.next[c - 'a'] = new TrieNode();
                    cur = cur.next[c - 'a'];
                }
                cur.word = word;
            }
            return root;
        }


    }


    static class _4th {
        class TrieNode {
            public TrieNode[] next = new TrieNode[26];
            public String word = "";

            public TrieNode() {
            }
        }

        class Trie {
            private TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public void insert(String word) {
                TrieNode node = root;
                for (char c : word.toCharArray()) {
                    if (node.next[c - 'a'] == null) {
                        node.next[c - 'a'] = new TrieNode();
                    }
                    node = node.next[c - 'a'];
                }
                node.word = word;
            }

            public boolean search(String word) {
                TrieNode node = root;
                for (char c : word.toCharArray()) {
                    if (node.next[c - 'a'] == null) return false;
                    node = node.next[c - 'a'];
                }
                return node.word.equals(word);
            }

            public boolean startsWith(String prefix) {
                TrieNode node = root;
                for (char c : prefix.toCharArray()) {
                    if (node.next[c - 'a'] == null) return false;
                    node = node.next[c - 'a'];
                }
                return true;
            }
        }


        Set<String> res = new HashSet<String>();//去重
        int m, n;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        public List<String> findWords(char[][] board, String[] words) {
            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);//构建Trie
            }
            m = board.length;
            n = board[0].length;
            boolean[][] visited = new boolean[m][n];//记录是否访问
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(board, visited, "", i, j, trie);
                }
            }
            return new ArrayList<>(res);
        }

        public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
            if (x < 0 || x >= m || y < 0 || y >= n) return;//越界
            if (visited[x][y]) return;//访问过
            str += board[x][y];
            if (!trie.startsWith(str)) return;//当前的str在Trie中不存在
            if (trie.search(str)) {//找打了一个str
                res.add(str);
            }
            visited[x][y] = true;
            for (int d = 0; d < dx.length; d++) {
                dfs(board, visited, str, x + dx[d], y + dy[d], trie);
            }
            visited[x][y] = false;
        }
    }

}
