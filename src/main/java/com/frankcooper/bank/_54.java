package com.frankcooper.bank;

import com.frankcooper.swordoffer.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class _54 {


    public static void main(String[] args) {
        _54 handler = new _54();
        int[][] matrix = PrintUtils.processSymbol("[[1,2,3],[4,5,6],[7,8,9]]");
        handler.spiralOrder(matrix);
    }


    int R, C;
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Integer> spiralOrder(int[][] matrix) {
        R = matrix.length;
        C = matrix[0].length;
        int r = 0, c = 0, d = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < R * C; i++) {
            res.add(matrix[r][c]);
            matrix[r][c] = -101;
            int nr = r + dirs[d][0], nc = c + dirs[d][1];
            if (!inArea(nr, nc) || matrix[nr][nc] == -101) {
                d = (d + 1) % 4;
                nr = r + dirs[d][0];
                nc = c + dirs[d][1];
            }
            r = nr;
            c = nc;

        }
        return res;
    }


    private boolean inArea(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }


}
