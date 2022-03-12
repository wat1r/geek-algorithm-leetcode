package com.frankcooper.bank._1_100;


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
//            Assert.assertEquals(handler.convert(s, numRows), "PAHNAPLSIIGYIR");
            s = "AB";
            numRows = 1;
            Assert.assertEquals(handler.convert(s, numRows), "AB");

        }


        public String convert(String s, int numRows) {
            int r = 0, flag = 1;
            StringBuilder[] sb = new StringBuilder[numRows];
            for (int k = 0; k < numRows; k++) sb[k] = new StringBuilder();
            int i = 0;
            while (i < s.length()) {
                if (r == numRows) {
                    flag = -flag;
                    if (numRows == 1) r -= 1;
                    else r -= 2;
                } else if (r == -1) {
                    flag = -flag;
                    if(numRows == 1) r+=1;
                    else r+=2;
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
