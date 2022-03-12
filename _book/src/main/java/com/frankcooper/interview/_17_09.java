package com.frankcooper.interview;

import com.frankcooper.classic.prim.PrimMST;

import java.util.PriorityQueue;

import java.util.*;

import org.junit.Assert;

public class _17_09 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.getKthMagicNumber(10);

        }


        public int getKthMagicNumber(int k) {
            int a = 0, b = 0, c = 0;//每个因子对应的次数
            int fa = 3, fb = 5, fc = 7;//每个因子
            int[] arr = new int[k];
            arr[0] = 1;
            for (int i = 1; i < k; i++) {
                arr[i] = Math.min(arr[a] * fa, Math.min(arr[b] * fb, arr[c] * fc));
                if (arr[i] == arr[a] * fa) a++;
                if (arr[i] == arr[b] * fb) b++;
                if (arr[i] == arr[c] * fc) c++;
            }
            return arr[k - 1];
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }

        public int getKthMagicNumber(int k) {
            PriorityQueue<Long> pq = new PriorityQueue<>();
            pq.add(1L);
            Set<Long> set = new HashSet<>();
            while (true) {
                long t = pq.poll();
                if (!set.contains(t)) {
                    set.add(t);
                    pq.add(t * 3);
                    pq.add(t * 5);
                    pq.add(t * 7);
                }
                if (set.size() == k) {
                    return (int) t;
                }
            }
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
