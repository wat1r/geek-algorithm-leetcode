package com.frankcooper.bank;

import java.util.Arrays;
import java.util.Collections;

public class _767 {

    static _767 handler = new _767();

    public static void main(String[] args) {
//        handler.reorganizeString("aaab");
//        handler.reorganizeString("aab");
        handler.reorganizeString("vvvlo");
    }


    public String reorganizeString(String S) {
        int n = S.length();
        Integer[] arr = new Integer[26];
        Arrays.fill(arr, 0);
        for (char c : S.toCharArray()) {
            arr[c - 97]++;
        }
        Arrays.sort(arr, Collections.reverseOrder());
        if (arr[0] > (n + 1) / 2) return "";
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while (cnt != n) {
            for (int i = 0; i < 26; i++) {
                if (arr[i] != 0) {
                    sb.append((char) (i + 97));
                    cnt++;
                    arr[i]--;
                }
            }
        }
        return sb.toString();
    }

}
