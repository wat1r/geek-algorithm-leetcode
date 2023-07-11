package com.frankcooper.platform.leetcode.bank.week;

import org.junit.Assert;

/*import java.util.*;
import org.junit.Assert;*/
public class Week353 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
//            Assert.assertEquals(3, handler.maximumJumps(new int[]{1, 3, 6, 4, 1, 2}, 2));
//            Assert.assertEquals(-1, handler.maximumJumps(new int[]{0, 2, 1, 3}, 1));
            Assert.assertEquals(1, handler.maximumJumps(new int[]{1, 0, 2}, 1));
        }


        public int maximumJumps(int[] nums, int target) {
            int res = -1, n = nums.length;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int t = nums[j] - nums[i];
                    if (t >= -target && t <= target) {
                        if (res == -1) {
                            res += 2;
                        } else {
                            res++;
                        }
                        i = j - 1;
                        break;
                    }
                    if (j == n - 1) {
                        return -1;
                    }
                }
                if (i == n - 1) {
                    return res;
                }
            }
            return -1;
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
