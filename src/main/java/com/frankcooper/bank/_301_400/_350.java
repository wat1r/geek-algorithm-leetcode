package com.frankcooper.bank._301_400;

import java.util.*;

/**
 * @Date 2020/7/14
 * @Author Frank Cooper
 * @Description
 */
public class _350 {


    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }


    public int[] intersect2nd(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0, m = nums1.length, n = nums2.length;
        int index = 0;
        int[] tmp = new int[Math.min(m, n)];
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] > nums2[j]) j++;
            else if (nums1[i] == nums2[j]) {
                tmp[index++] = nums1[i];
                i++;
                j++;
            }
        }
        return Arrays.copyOfRange(tmp, 0, index);
    }

    static class _1st {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0, j = 0, m = nums1.length, n = nums2.length;
            int index = 0;
            int[] tmp = new int[Math.min(m, n)];
            while (i < m && j < n) {
                if (nums1[i] < nums2[j]) i++;
                else if (nums1[i] > nums2[j]) j++;
                else if (nums1[i] == nums2[j]) {
                    tmp[index++] = nums1[i];
                    i++;
                    j++;
                }
            }
            return Arrays.copyOfRange(tmp, 0, index);
        }
    }


    static class _2nd {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0, j = 0, m = nums1.length, n = nums2.length;
            int[] res = new int[Math.min(m, n)];
            int idx = 0;
            while (i < m && j < n) {
                if (nums1[i] == nums2[j]) {
                    res[idx++] = nums1[i];
                    i++;
                    j++;
                } else if (nums1[i] > nums2[j]) {
                    j++;
                } else {
                    i++;
                }
            }
            return Arrays.copyOfRange(res, 0, idx);
        }
    }


}
