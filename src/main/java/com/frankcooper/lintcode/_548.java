package com.frankcooper.lintcode;

import java.util.*;

import org.junit.Assert;

public class _548 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums1 = {1, 2, 2, 1};
            int[] nums2 = {2, 2};
            handler.intersection(nums1,nums2);
        }


        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            List<Integer> resList = new ArrayList<>();
            int i = 0, j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    resList.add(nums1[i]);
                    i++;
                    j++;
                } else if (nums1[i] > nums2[j]) j++;
                else i++;
            }
            int[] res = new int[resList.size()];

            for (int k = 0; k < resList.size(); k++) {
                res[k] = resList.get(k);
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
