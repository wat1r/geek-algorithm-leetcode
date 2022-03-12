package com.frankcooper.bank._1001_1500;

import java.util.*;

import com.frankcooper.struct.pri.CustomFunction;
import org.junit.Assert;

public class _1237 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
            List<List<Integer>>  res = new ArrayList<>();
            int n = 1000;
            for (int x = 1; x <= n; x++) {
                int l = 1, r = n;

                while (l < r) {
                    int mid = (l + r) / 2;
                    int t = customfunction.f(x, mid);
                    if (t < z) l = mid + 1;
                    else r = mid;
                }
                if (customfunction.f(x, l) == z) {
                    res.add(Arrays.asList(x, l));
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
