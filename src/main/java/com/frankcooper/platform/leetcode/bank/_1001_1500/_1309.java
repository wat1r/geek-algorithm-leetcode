package com.frankcooper.platform.leetcode.bank._1001_1500;

import org.junit.Assert;

public class _1309 {
    static _1309 handler = new _1309();


    public static void main(String[] args) {
        String s = "10#11#12";
//        System.out.println(String.format("s:%s--->%s", s, handler.freqAlphabets(s)));
//        s = "1326#";
//        System.out.println(String.format("s:%s--->%s", s, handler.freqAlphabets(s)));
//        s = "25#";
//        System.out.println(String.format("s:%s--->%s", s, handler.freqAlphabets(s)));
        s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#";
        System.out.println(handler.freqAlphabets(s));
        Assert.assertEquals(handler.freqAlphabets(s), "abcdefghijklmnopqrstuvwxyz");


    }


    public String freqAlphabets(String s) {
        StringBuffer sb = new StringBuffer();
        int index = 0, n = s.length();
        while (index < n) {
            if (index + 2 < n && s.charAt(index + 2) == '#') {
                sb.append((char) (Integer.valueOf(s.substring(index, index + 2)) + 96));
                index += 3;
            } else {
                sb.append((char) (Integer.valueOf(s.substring(index, index + 1)) + 96));
                index += 1;
            }

        }
        return sb.toString();
    }
}
