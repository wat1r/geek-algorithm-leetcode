package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.Arrays;

/*import java.util.*;
import org.junit.Assert;*/
public class _2679 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int matrixSum(int[][] nums) {
            for (int[] row : nums) {
                Arrays.sort(row);
            }
            int total = 0;
            for (int j = 0; j < nums[0].length; j++) {
                int t = 0;
                for (int i = 0; i < nums.length; i++) {
                    t = Math.max(t, nums[i][j]);
                }
                total += t;
            }
            return total;
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
