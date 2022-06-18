package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.*;

public class _36 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        public boolean isValidSudoku(char[][] board) {
            int R = board.length, C = board[0].length;
            //行 列 块的map k:当前的行/列/块 的索引 [0-8] v:当前的行/列/块 包含的数字
            Map<Integer, Set<Integer>> rowsMap = new HashMap<>();
            Map<Integer, Set<Integer>> colsMap = new HashMap<>();
            Map<Integer, Set<Integer>> blocksMap = new HashMap<>();
            for (int i = 0; i < 9; i++) {
                rowsMap.put(i, new HashSet<>());
                colsMap.put(i, new HashSet<>());
                blocksMap.put(i, new HashSet<>());
            }
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    char cur = board[r][c];
                    if (cur == '.') continue;
                    int t = cur - '0';//当前这个点是个数字
                    int idx = r / 3 * 3 + c / 3;//小方块的索引与坐标的转换公式
                    //如果当前的行/列/块 含有重复的数字，不是一个有效的数独
                    if (rowsMap.get(r).contains(t) || colsMap.get(c).contains(t) || blocksMap.get(idx).contains(t))
                        return false;
                    rowsMap.get(r).add(t);
                    colsMap.get(c).add(t);
                    blocksMap.get(idx).add(t);
                }
            }
            return true;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public boolean isValidSudoku(char[][] board) {
            int R = board.length, C = board[0].length;
            boolean[][] rows = new boolean[R][C];
            boolean[][] cols = new boolean[R][C];
            boolean[][] blocks = new boolean[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    char cur = board[r][c];
                    if (cur == '.') continue;
                    int t = cur - '1';//1变成0 方便下面的下标，否则要改上面是array
                    int idx = r / 3 * 3 + c / 3;
                    //这种相当于是给每一行/列/块 安排了9个数字的位置排排好，
                    if (rows[r][t] || cols[c][t] || blocks[idx][t]) return false;
                    rows[r][t] = cols[c][t] = blocks[idx][t] = true;
                }
            }
            return true;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public boolean isValidSudoku(char[][] board) {
            int R = board.length, C = board[0].length;
            int[] rows = new int[R + 1], cols = new int[C + 1], blocks = new int[10];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    char cur = board[r][c];
                    if (cur == '.') continue;
                    int t = cur - '0';
                    int idx = r / 3 * 3 + c / 3;
                    if (((rows[r] >> t) & 1) == 1 || ((cols[c] >> t) & 1) == 1 || ((blocks[idx] >> t) & 1) == 1)
                        return false;
                    rows[r] |= (1 << t);
                    cols[c] |= (1 << t);
                    blocks[idx] |= (1 << t);
                }
            }

            return true;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
