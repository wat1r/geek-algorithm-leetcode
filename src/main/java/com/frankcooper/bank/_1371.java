package com.frankcooper.bank;

import java.util.Arrays;

public class _1371 {
    static _1371 handler = new _1371();

    public static void main(String[] args) {
//        handler.findTheLongestSubstring1st("eleetminicoworoep");
//        handler.findTheLongestSubstring("tamntyyaw");
        handler.findTheLongestSubstring("leetcodeisgreat");
    }


    public int findTheLongestSubstring1st(String s) {
        int n = s.length();
        //大小为32的数组pos，记录当前状态status出现的最早的的位置
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
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


    public int findTheLongestSubstring(String s) {
        Integer INF = Integer.MAX_VALUE;
        int[] pre=new int[1<<5];
        Arrays.fill(pre,INF);
        pre[0]=-1;
        int size=s.length(),status=0,ans=0;
        for(int i=0;i<size;i++){
            char ch=s.charAt(i);
            switch(ch){
                case 'a':status^=1<<0;break;
                case 'e':status^=1<<1;break;
                case 'i':status^=1<<2;break;
                case 'o':status^=1<<3; break;
                case 'u':status^=1<<4;break;
            }
            if(pre[status]==INF){
                pre[status]=i;
            }else{
                ans=Math.max(ans,i-pre[status]);
            }
        }
        return ans;
    }










}
