package com.frankcooper.bank._1501_2000;

import java.util.*;
import org.junit.Assert;
public class _2006 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public int countKDifference(int[] nums, int k) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    if(Math.abs(nums[i]-nums[j])==k) res++;
                }
            }
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int countKDifference(int[] nums, int k) {
            int[] counter = new int[105];
            int res = 0;
            for (int x : nums) {
                if(x - k >=1) res += counter[x-k];
                if(x + k <=100) res +=counter[x+k];
                counter[x]++;
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }

        public int countKDifference(int[] nums, int k) {
            int res = 0 ;
            Map<Integer,Integer> map = new HashMap<>();
            for (int x : nums) {
                res +=map.getOrDefault(x-k,0)+map.getOrDefault(x+k,0);
                map.put(x,map.getOrDefault(x,0)+1);
            }
            return res;
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
