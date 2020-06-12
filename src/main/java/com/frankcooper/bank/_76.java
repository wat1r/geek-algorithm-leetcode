package com.frankcooper.bank;

public class _76 {

    static _76 handler = new _76();

    public static void main(String[] args) {

    }


    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        int left = 0, right = 0, len = Integer.MAX_VALUE;
        String res = "";
        int n = s.length();
        int[] source = new int[128];
        int[] target = new int[128];
        for (char c : t.toCharArray()) target[c]++;
        while (right < n) {
            if (!valid(source, target)) source[s.charAt(right++)]++;
            while (valid(source, target)) {
                if (right - left < len) {
                    len = Math.min(len, right - left);
                    res = s.substring(left, right);
                }
                source[s.charAt(left++)]--;
            }
        }
        return res;
    }


    private boolean valid(int[] source, int[] target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] < target[i]) return false;
        }
        return true;
    }

}
