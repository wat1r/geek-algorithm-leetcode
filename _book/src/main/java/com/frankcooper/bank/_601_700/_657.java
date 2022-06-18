package com.frankcooper.platform.leetcode.bank._601_700;

/**
 * @Date 2020/8/28
 * @Author Frank Cooper
 * @Description
 */
public class _657 {


    public boolean judgeCircle(String moves) {
        if (moves == null | moves.length() == 0) return false;
        int i = 0, j = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'R':
                    i++;
                    break;
                case 'L':
                    i--;
                    break;
                case 'U':
                    j--;
                    break;
                case 'D':
                    j++;
                    break;

                default:
                    break;
            }
        }
        return i == 0 && j == 0;
    }


}
