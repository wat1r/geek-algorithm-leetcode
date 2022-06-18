package com.frankcooper.platform.leetcode.bank._1_100;


// import java.util.*;
// import org.junit.Assert;


import org.junit.Assert;

public class _6 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String s = "PAYPALISHIRING";
            int numRows = 3;
//            handler.convert(s, numRows);
            Assert.assertEquals(handler.convert(s, numRows), "PAHNAPLSIIGYIR");
//            s = "AB";
//            numRows = 1;
//            Assert.assertEquals(handler.convert(s, numRows), "AB");

        }


        public String convert(String s, int numRows) {
            //r表示每一行的索引 [0,numRows-1]
            //flag是 1 或者 -1 按从上到下或者从下到上 两个方向区分正负
            int r = 0, flag = 1;
            //结果列表
            StringBuilder[] sb = new StringBuilder[numRows];
            for (int k = 0; k < numRows; k++) sb[k] = new StringBuilder();
            int i = 0;
            while (i < s.length()) {
                if (r == numRows) {
                    flag = -flag;
                    //区分nowrows 是否为1
                    if (numRows == 1) r -= 1;
                    else r -= 2;
                } else if (r == -1) {
                    flag = -flag;
                    if (numRows == 1) r += 1;
                    else r += 2;
                }

                sb[r].append(s.charAt(i++));
                r += flag;
            }
            StringBuilder ans = new StringBuilder();
            for (int k = 0; k < numRows; k++) ans.append(sb[k]);
            return ans.toString();
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public String convert(String s, int numRows) {

            StringBuilder[] sb = new StringBuilder[numRows];
            for (int k = 0; k < numRows; k++) sb[k] = new StringBuilder();
            int r = 0, i = 0;
            int flag = 1;
            while (i < s.length()) {
                if (r == numRows) {
                    flag = -flag;
                    if (numRows == 1) r -= 1;
                    else r -= 2;
                } else if (r == -1) {
                    flag = -flag;
                    if (numRows == 1) r += 1;
                    else r += 2;
                }
                sb[r].append(s.charAt(i++));
                r += flag;
            }
            StringBuilder res = new StringBuilder();
            for (int k = 0; k < numRows; k++) res.append(sb[k]);
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
