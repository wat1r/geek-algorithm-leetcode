package com.frankcooper.platform.luogu;

import java.util.Scanner;

/*import java.util.*;
import org.junit.Assert;*/
public class _P2440 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }


        static class Main {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt(), k = sc.nextInt();
                int[] L = new int[n];
                int index = 0;
                while (index < n) {
                    L[index++] = sc.nextInt();
                }
                int lo = 0, hi = (int) 1e8;
                while (lo < hi) {
                    int mid = lo + (hi - lo + 1) / 2;
                    if (check(L, k, mid)) lo = mid;
                    else hi = mid - 1;
                }
                System.out.println(lo);
            }

            //给定一个长度为l的木棒,是否能裁剪出超过k段的木棒
            private static boolean check(int[] L, int k, int l) {
                int count = 0;
                for (int x : L) {
                    count += x / l;
                    if (count >= k) return true;
                }
                return count >= k;

            }
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
