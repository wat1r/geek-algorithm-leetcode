package com.frankcooper.platform.leetcode.bank._1_100;

import java.util.ArrayList;
import java.util.List;

public class _89 {


    public static void main(String[] args) {
        _1st handler = new _1st();
        handler.grayCode(2);
        handler.grayCode(3);
    }


    static class _1st {

        public List<Integer> grayCode(int n) {
            List<Integer> res = new ArrayList<>();
            res.add(0);
            for (int i = 0; i < n; i++) {
                int add = 1 << i;
                for (int j = res.size() - 1; j >= 0; j--) {
                    res.add(res.get(j)+add);
                }
            }
//            for (int g : res) {
//                System.out.println(PrintUtils.addZeroForNum(Integer.toBinaryString(g), 3));
//            }
//            System.out.println("------------------");
            return res;
        }
    }

}
