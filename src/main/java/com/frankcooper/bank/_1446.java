package com.frankcooper.bank;

public class _1446 {


    public int maxPower(String s) {
        int ans = 1;
        for (int l = 0; l < s.length(); ) {
            int r = l + 1;
            while (r < s.length() && s.charAt(r) == s.charAt(l)) r++;
            ans = Math.max(ans, r - l);
            l = r;
        }
        return  ans;


    }

}
