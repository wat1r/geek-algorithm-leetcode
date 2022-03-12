package com.frankcooper.bank._401_500;

import java.util.*;

import org.junit.Assert;

public class _496 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};
            handler.nextGreaterElement(nums1, nums2);

        }


        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length];
            Stack<Integer> stk = new Stack<>();
            //k:nums2的当前元素，v:当前元素的next greater element
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums2) {
                while (!stk.isEmpty() && stk.peek() < x) {
                    map.put(stk.pop(), x);
                }
                stk.push(x);
            }
            //如果当前元素没有NGE，返回-1
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.getOrDefault(nums1[i], -1);
            }
            return res;
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length];
            Deque<Integer> stk = new ArrayDeque<>();
            //k:nums2的当前元素，v:当前元素的next greater element
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums2) {
                while (!stk.isEmpty() && stk.peekLast() < x) {
                    map.put(stk.pollLast(), x);
                }
                stk.addLast(x);
            }
            //如果当前元素没有NGE，返回-1
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.getOrDefault(nums1[i], -1);
            }
            return res;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};
            handler.nextGreaterElement(nums1, nums2);
        }

        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length];
            Deque<Integer> deque = new ArrayDeque<>();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = nums2.length - 1; i >= 0; --i) {
                int t = nums2[i];
                while (!deque.isEmpty() && deque.peekLast() <= t) {
                    deque.pollLast();
                }
                map.put(t, deque.isEmpty() ? -1 : deque.peekLast());
                deque.offerLast(t);
            }
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.get(nums1[i]);
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
