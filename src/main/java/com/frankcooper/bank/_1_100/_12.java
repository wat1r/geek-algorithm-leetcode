package com.frankcooper.bank._1_100;

import java.util.*;

import org.junit.Assert;

public class _12 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int num = 58;
            handler.intToRoman(num);
        }


        public String intToRoman(int num) {
            int[] arabic_nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};//关键的罗马数字节点
            String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};//对应的罗马数字
            int i = 0;
            StringBuilder res = new StringBuilder();
            while (i < arabic_nums.length) {
                while (num >= arabic_nums[i]) {//从大到小遍历，贪心判断当前的值是否可以再被缩减
                    num -= arabic_nums[i];
                    res.append(romans[i]);
                }
                i++;
            }
            return res.toString();
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            handler.intToRoman(1019);
        }


        public String intToRoman(int num) {
            String[][] dict = {//准备 关键节点的 硬编码的罗马数字 ，0 位置为""
                    // 1~9
                    {
                            "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"
                    },
                    // 10~90
                    {
                            "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"
                    },
                    // 100~900
                    {
                            "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"
                    },
                    // 1000~3000
                    {
                            "", "M", "MM", "MMM"
                    }
            };
            StringBuilder res = new StringBuilder();
            int i = 0;
            while (num > 0) {
                int last = num % 10;//每次拿到num的最后面的数，找到后添加
                res.insert(0, dict[i][last]);
                num /= 10;//移除最末位
                i++;
            }
            return res.toString();
        }

    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
