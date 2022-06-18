package com.frankcooper.platform.leetcode.bank._701_800;

public class _795 {


    static _795 handler = new _795();

    public static void main(String[] args) {

        int[] A = {2, 1, 4, 3};
        int x = 3;
        handler.count(A, x);
    }


    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        return count(A, R) - count(A, L - 1);
    }

    /**
     * count函数记录 A数组中所有元素小于等于x的子数组数量
     * 如 {2, 1, 4, 3}  x =3
     * {2}
     * {1}
     * {2,1}
     * {3}
     * 返回4
     *
     * @param A
     * @param x
     * @return
     */
    private int count(int[] A, int x) {
        int ans = 0, curr = 0;
        for (int a : A) {
            curr = (a <= x) ? curr + 1 : 0;
            ans += curr;
        }
        return ans;
    }

}
