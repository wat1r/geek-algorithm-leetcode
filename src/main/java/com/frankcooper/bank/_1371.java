package com.frankcooper.bank;

import com.frankcooper.swordoffer.utils.BitOpUtils;

import java.util.Arrays;

public class _1371 {
    static _1371 handler = new _1371();

    public static void main(String[] args) {
        handler.findTheLongestSubstring("eleetminicoworoep");
    }


    public int findTheLongestSubstring(String s) {
        int n = s.length();
        //大小为32的数组pos，记录当前状态status出现偶数个元音字符的符合条件的位置
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0; //表示00000的符合条件的位置
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c){
                case 'u': status ^= (1 << 0);break;
                case 'o': status ^= (1 << 1);break;
                case 'i': status ^= (1 << 2);break;
                case 'e': status ^= (1 << 3);break;
                case 'a': status ^= (1 << 4);break;
                default:break;
            }
            if (pos[status] >= 0) ans = Math.max(ans, i + 1 - pos[status]);
            else pos[status] = i + 1;
        }
        return ans;
    }


}
