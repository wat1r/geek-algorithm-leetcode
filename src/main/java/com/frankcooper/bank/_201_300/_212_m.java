package com.frankcooper.bank._201_300;

import java.util.LinkedList;
import java.util.List;

/**
 * @Date 2020/7/24
 * @Author Frank Cooper
 * @Description
 */
public class _212_m {

    class Solution {
        char[][] _board;
        Trie root;
        List<String> res;

        class Trie {
            Trie[] next = new Trie[26];
            String word;
            int count;

            Trie() {
                next = new Trie[26];
                count = 0;
                word = null;
            }

            void addWord(String w, int index) {
                if (index == w.length()) {
                    word = w;
                } else {
                    char c = w.charAt(index);
                    if (next[c - 'a'] == null) {
                        count++;
                        next[c - 'a'] = new Trie();
                    }
                    next[c - 'a'].addWord(w, index + 1);
                }
            }
        }

        void dfs(int x, int y, Trie t) {
            char c = _board[x][y];
            Trie curr = t.next[c - 'a'];
            if (curr.word != null) {
                this.res.add(curr.word);
                curr.word = null;
            }

            _board[x][y] = '#';
            int[][] direct = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int i = 0; i < 4; i++) {
                int x1 = x + direct[i][0];
                int y1 = y + direct[i][1];
                if (x1 >= 0 && y1 >= 0 && x1 < _board.length && y1 < _board[0].length && _board[x1][y1] != '#' && curr.next[_board[x1][y1] - 'a'] != null) {
                    dfs(x1, y1, curr);
                }
            }
            _board[x][y] = c;
            if (curr.count == 0) {
                t.next[c - 'a'] = null;
                t.count--;
            }
        }

        public List<String> findWords(char[][] board, String[] words) {
            root = new Trie();
            for (int i = 0; i < words.length; i++) {
                root.addWord(words[i], 0);
            }
            this._board = board;
            res = new LinkedList<String>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (root.next[_board[i][j] - 'a'] != null) dfs(i, j, root);
                }
            }
            return res;
        }
    }
}
