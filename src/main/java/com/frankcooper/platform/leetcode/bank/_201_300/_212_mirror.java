package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/7/23
 * @Author Frank Cooper
 * @Description
 */
public class _212_mirror {

    static _212_mirror handler = new _212_mirror();

    public static void main(String[] args) {
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
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (root.next[board[i][j] - 'a'] != null) {
                    backtracking(board, i, j, root, visited);
                }
            }
        }

        return result;
    }

    private void backtracking(char[][] board, int i, int j, TrieNode root, boolean[][] visited) {
        char c = board[i][j];
        TrieNode curr = root.next[c - 'a'];
        if (curr != null && curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }
//        board[i][j] = '#';
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int nextI = i + directions[k][0];
            int nextJ = j + directions[k][1];
            if ((nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) || visited[nextI][nextJ]) continue;
            if (curr != null) backtracking(board, nextI, nextJ, curr, visited);
        }
//        board[i][j] = c;
        visited[i][j] = false;
    }
}
