package com.frankcooper.bank._1001_1500;

import java.util.Arrays;

public class _1460 {

    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i = 0; i < target.length; i++) {
            if (i > arr.length) return false;
            if (arr[i] != target[i]) return false;
        }
        return true;
    }


}
