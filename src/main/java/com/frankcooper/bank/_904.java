package com.frankcooper.bank;

import java.util.HashSet;
import java.util.Set;

public class _904 {


    static _904 handler = new _904();

    public static void main(String[] args) {
        handler.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4});
    }


    /**
     * 有重复计算
     * @param tree
     * @return
     */
    public int totalFruit(int[] tree) {

        Set<Integer> set = new HashSet<>();
        int n = tree.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && (set.size() < 2 || (set.size() == 2 && set.contains(tree[j])))) {
                set.add(tree[j++]);
            }
            if (j == n && (j - i) >= ans) return j-i;
            ans = Math.max(ans, j - i);
            set.clear();
        }
        return ans;
    }
}
