package com.frankcooper.bank._701_800;

import java.util.ArrayList;
import java.util.List;

public class _763 {

    public List<Integer> partitionLabels(String S) {
        int[] arr = new int[26];
        char[] chas = S.toCharArray();
        for (int i = 0; i < chas.length; i++) arr[chas[i] - 'a'] = i;
        int l = 0, r = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < chas.length; i++) {
            r = Math.max(r, arr[chas[i] - 'a']);
            if (i == r) {
                ans.add(r - l + 1);
                l = i + 1;
            }
        }
        return ans;
    }


}
