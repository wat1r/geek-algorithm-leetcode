package com.frankcooper.platform.leetcode.bank._1501_2000;

import java.util.*;

import org.junit.Assert;
public class _1726 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }
        public int tupleSameProduct(int[] nums) {
            Map<Integer,Integer> m = new HashMap<>();
            for(int i = 0; i<nums.length-1;i++){
                for(int j = i+1;j<nums.length;j++){
                    int x = nums[i], y= nums[j];
                    m.put(x*y, m.getOrDefault(x*y,0)+1);
                }
            }
            int res =0;
            for(int k : m.keySet()){
                int v = m.get(k);
                if(v>=2){
                    res+= v*(v-1)/2 * 8;
                }
            }
            return res;
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
