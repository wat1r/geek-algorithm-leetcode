package com.frankcooper.platform.leetcode.bank._2001_3000;

import java.util.*;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;

public class _2028 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            int[] rolls = new int[]{1, 5, 6};
            int mean = 3;
            int n = 4;
//            handler.missingRolls(rolls, mean, n);
            //case : TLE
//[4,5,6,2,3,6,5,4,6,4,5,1,6,3,1,4,5,5,3,2,3,5,3,2,1,5,4,3,5,1,5]
//            4
//            40
            rolls = new int[]{4, 5, 6, 2, 3, 6, 5, 4, 6, 4, 5, 1, 6, 3, 1, 4, 5, 5, 3, 2, 3, 5, 3, 2, 1, 5, 4, 3, 5, 1, 5};
            mean = 4;
            n = 40;
            handler.missingRolls(rolls, mean, n);

//            int target = 9;
//            int n = 4;
//            handler.find(new ArrayList<>(), target, 0, n);
//            System.out.println(JSON.toJSONString(res));
        }

        //TLE
        public int[] missingRolls(int[] rolls, int mean, int n) {
            Arrays.toString(rolls);
            int sum1 = 0, m = rolls.length;
            for (int x : rolls) sum1 += x;
            int sum2 = (n + m) * mean - sum1;
            if (sum2 < n || sum2 > n * 6) return new int[]{};
            find(new ArrayList<>(), sum2, 0, n);
            if (res.get(0).isEmpty()) return new int[]{};
            int[] ans = new int[n];
//            System.out.println(JSON.toJSONString(res));
            for (List<Integer> sub : res) {
                for (int x : sub) System.out.printf("%d ", x);
                System.out.println();
            }
            for (int i = 0; i < res.get(0).size(); i++) {
                ans[i] = res.get(0).get(i);
            }
            return ans;
        }

        static List<List<Integer>> res = new ArrayList<>();

        public void find(List<Integer> list, int target, int sum, int n) {
            if (res.size() != 0) return;
            if (sum == target && list.size() < n) return;
            if (sum > target || list.size() > n) return;
            if (list.size() == n && sum == target) {
                res.add(new ArrayList<>(list));
                return;
            }
            for (int i = 6; i >= 1; i--) {
                list.add(i);
                find(list, target, sum + i, n);
                list.remove(list.size() - 1);
            }

        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        public int[] missingRolls(int[] rolls, int mean, int n) {
            int m = rolls.length;
            int tot = (n + m) * mean;
            for (int x : rolls) tot -= x;
            if (tot < n || tot > 6 * n) return new int[]{};
            int[] res = new int[n];
            Arrays.fill(res, tot / n);
            if (tot / n * n < tot) {
                int diff = tot - (tot / n * n);
                int idx = 0;
                while (diff-- > 0) res[idx++]++;
            }
            return res;
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
