package com.frankcooper.bank._301_400;

import java.util.*;

import org.junit.Assert;

public class _334 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public boolean increasingTriplet(int[] nums) {
            if (nums.length < 3) return false;
            int maxx = Integer.MAX_VALUE;
            int minn = Integer.MAX_VALUE;
            for (int x : nums) {
                if (x <= minn) minn = x;
                else if (x <= maxx) maxx = x;
                else return true;
            }
            return false;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int[] nums = new int[]{2,1,5,0,4,6};
            handler.increasingTriplet(nums);
        }

        public boolean increasingTriplet(int[] nums) {
            if(nums.length<3) return false;
            int maxx = Integer.MAX_VALUE;
            int minn = Integer.MAX_VALUE;
            for(int x : nums){
                if(x<=minn) minn =x;//首先拿到最小的
                else if(x<=maxx) maxx= x;
                else return true;
            }
            return false;
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
