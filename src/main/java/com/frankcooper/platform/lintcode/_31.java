package com.frankcooper.platform.lintcode;

/*import java.util.*;
import org.junit.Assert;*/
public class _31 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int partitionArray(int[] nums, int k) {
            int l = 0, r = nums.length - 1;
            while (l <= r) {//出口条件是l = r +1
                while (l <= r && nums[l] < k) {
                    l++;
                }
                while (l <= r && nums[r] >= k) {
                    r--;
                }
                if (l < r) {
                    int t = nums[l];
                    nums[l] = nums[r];
                    nums[r] = t;
                    l++;
                    r--;
                }
            }
            return l;
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
