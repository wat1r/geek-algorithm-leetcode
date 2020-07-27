package com.frankcooper.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/7/27
 * @Author Frank Cooper
 * @Description
 */
public class _212_m_2 {


    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word = null;
        int count = 0;
    }


    List<String> result = new ArrayList<>();
    int m;//行
    int n;//列
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {
        //1.构造前缀树，遍历words，在里层遍历每一个word
        addWords(words);
        //2.对单元格进行回溯
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (root.next[board[i][j] - 'a'] != null) {
                    backtracking(board, i, j, root);
                }
            }
        }


        return result;
    }

    private void backtracking(char[][] board, int i, int j, TrieNode root) {
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
            if (curr != null) backtracking(board, nextI, nextJ, curr);
        }
        board[i][j] = c;
        if (curr != null && curr.count == 0) {
            root.next[c - 'a'] = null;
            root.count--;
        }
    }

    private void addWords(String[] words) {

        for (String word : words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (curr.next[c - 'a'] == null) {
                    curr.count++;
                    curr.next[c - 'a'] = new TrieNode();
                }
                curr = curr.next[c - 'a'];
            }
            curr.word = word;
        }
    }


}
