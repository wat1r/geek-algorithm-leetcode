package com.frankcooper.bank._701_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _830 {


    static _830 handler = new _830();

    public static void main(String[] args) {
//        handler.largeGroupPositions("abcdddeeeeaabbbcd");
        handler.largeGroupPositions("aa");
    }


    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int l = 0, r = 1, n = s.length();
        char[] chas = s.toCharArray();
        while (l <= r && r < n) {
            while (r < n && chas[r] == chas[l]) r++;
            if ((r - l) >= 3) res.add(Arrays.asList(l, r - 1));
            l = r;
            r++;
        }
        return res;
    }


}
