package com.frankcooper.platform.leetcode.bank.week;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;

public class Week293 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

            List<String> expected = Arrays.asList("a", "b", "a");
            Assert.assertEquals(expected, handler.removeAnagrams(new String[]{"a", "b", "a"}));


        }


        public List<String> removeAnagrams(String[] words) {
            List<String> res = new ArrayList<>();
            Set<String> set = new HashSet<>();
            for (String word : words) {
                char[] ch = word.toCharArray();
                Arrays.sort(ch);
                if (!set.contains(String.valueOf(ch))) {
                    res.add(word);
                    set.add(String.valueOf(ch));
                }
            }
            return res;
        }


    }

    static class _1st_1 {
        public static void main(String[] args) {

        }

        public List<String> removeAnagrams(String[] words) {
            List<String> res = new ArrayList<>();
            int n = words.length;
            for (int i = 0; i < n; i++) {
                int j = i;
                res.add(words[i]);
                while (j < n && check(words[i], words[j])) {
                    j++;
                }
                i = j - 1;
            }
            return res;
        }

        public boolean check(String s, String t) {
            char[] chs = s.toCharArray();
            char[] cht = t.toCharArray();
            Arrays.sort(chs);
            Arrays.sort(cht);
            return String.valueOf(chs).equals(String.valueOf(cht));
        }
    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int bottom = 2, top = 9;
            int[] special = {4, 6};
            handler.maxConsecutive(bottom, top, special);
//            bottom = 2;
//            top = 9;
//            special = new int[]{4, 6};
        }

        public int maxConsecutive(int bottom, int top, int[] special) {
            int maxx = 0;
            Arrays.sort(special);
            int index = 0;
            for (int i = bottom; i <= top; ) {
                int b = i;
                if (index < special.length && i < special[index]) {
                    i = special[index];
                }
                if (index >= special.length && b == i) {
                    i = top + 1;
                }
                int t = i - b;
                i++;
                index++;
                maxx = Math.max(maxx, t);
            }
            return maxx;
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            int[] cs = new int[]{1, 5, 3};
            handler.largestCombination(cs);
        }

        //统计这些candidates上二进制为1的个数
        public int largestCombination(int[] candidates) {
            int n = 24;
            int[] bits = new int[n];
            int maxx = 0;
            for (int c : candidates) {
                for (int i = 0; i < n; i++) {
                    //当前位数上是否是1
                    if (((1 << i) & c) != 0) {
                        bits[i]++;
                    }
                    maxx = Math.max(maxx, bits[i]);
                }
            }
            return maxx;
        }

    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
            String[] ops = {"CountIntervals", "add", "add", "count", "add", "count"};
            int[][] vals = {{}, {2, 3}, {7, 10}, {}, {5, 8}, {}};
            CountIntervals ci = new CountIntervals();
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                if (op.equals("add")) {
                    int l = vals[i][0], r = vals[i][1];
                    ci.add(l, r);
                } else if (op.equals("count")) {
                    System.out.println(ci.count());
                }
            }
        }
        //珂朵莉树

        //`Map.Entry<K,V>	ceilingEntry(K key) `一个键-值映射关系，
        // 它与大于等于给定键的最小键关联；如果不存在这样的键，则返回 null。


        static class CountIntervals {
            TreeMap<Integer, Integer> m = new TreeMap<>();
            int cnt;

            public CountIntervals() {
            }

            public void add(int left, int right) {
                // 遍历所有被 [left,right] 覆盖到的区间（部分覆盖也算）
                for (Map.Entry<Integer, Integer> e = m.ceilingEntry(left);
                     e != null && e.getValue() <= right;
                     e = m.ceilingEntry(left)) {
                    int l = e.getValue(), r = e.getKey();
                    left = Math.min(left, l);   // 合并后的新区间，其左端点为所有被覆盖的区间的左端点的最小值
                    right = Math.max(right, r); // 合并后的新区间，其右端点为所有被覆盖的区间的右端点的最大值
                    cnt -= r - l + 1;
                    m.remove(r);
                }
                cnt += right - left + 1;
                m.put(right, left); // 所有被覆盖到的区间与 [left,right] 合并成一个新区间
            }

            public int count() {
                return cnt;
            }
        }


    }

    static class _4th_1 {


        public static void main(String[] args) {
            _4th_1 handler = new _4th_1();
            String[] ops = new String[]{"add", "add", "add", "add"};
            //[805, 957] ->  {"15":[15,925],"805":[935,994],"995":[995,1000]}
            int[][] vals = new int[][]{{15, 25}, {35, 84}, {95, 100}, {20, 40}};
            CountIntervals ci = new CountIntervals();
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                if (op.equals("add")) {
                    int l = vals[i][0], r = vals[i][1];
                    ci.add(l, r);
                    System.out.printf("%d -> %s ->  %s\n", i, Arrays.toString(vals[i]), JSONObject.toJSON(ci.tm));
                } else if (op.equals("count")) {
                    System.out.println(ci.count());
//                    if (ci.count() == 1640) {
//                        System.out.printf("%d\n", i);
//                    }
                }
            }

        }


        static class CountIntervals {
            TreeMap<Integer, Integer> tm;
            int sum;

            public CountIntervals() {
                sum = 0;
                tm = new TreeMap<>();
            }

            public void add(int left, int right) {
                // System.out.println(String.format("%d %d %d %s",left,right,sum,tm.toString()));
                sum += right - left + 1;
                Integer f = tm.floorKey(left);
                if (f != null) {
                    int f1 = tm.get(f);
                    if (f1 >= left) {
                        tm.remove(f);
                        int min = Math.min(right, f1);
                        sum -= min - left + 1;
                        left = f;
                        right = Math.max(right, f1);
                    }
                }
                while (tm.floorKey(right) != null && tm.floorKey(right) > left) {
                    f = tm.floorKey(right);
                    int f1 = tm.get(f);
                    sum -= Math.min(f1, right) - f + 1;
                    tm.remove(f);
                    right = Math.max(f1, right);
                }
                tm.put(left, right);
                // System.out.println(String.format("%d %s",sum,tm.toString()));
            }

            public int count() {
                return sum;
            }
        }
    }

    static class _4th_2 {


        public static void main(String[] args) {
//            TreeSet<Interval> ts = new TreeSet<>();
//            Interval i1 = new Interval(2, 10);
//            Interval i2 = new Interval(7, 15);
//            Interval i3 = new Interval(4, 10);
//            ts.add(i1);
//            ts.add(i2);
//            ts.add(i3);
//            System.out.printf("%s\n", ts.toString());
            String[] ops = new String[]{"add", "add", "add", "add"};
            //[805, 957] ->  {"15":[15,925],"805":[935,994],"995":[995,1000]}
            int[][] vals = new int[][]{{15, 25}, {35, 84}, {95, 100}, {20, 40}};
            CountIntervals ci = new CountIntervals();
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                if (op.equals("add")) {
                    int l = vals[i][0], r = vals[i][1];
                    ci.add(l, r);
                    System.out.printf("%d -> %s ->  %s\n", i, Arrays.toString(vals[i]), JSONObject.toJSON(ci.set));
                } else if (op.equals("count")) {
                    System.out.println(ci.count());
//                    if (ci.count() == 1640) {
//                        System.out.printf("%d\n", i);
//                    }
                }
            }

        }

        static class CountIntervals {
            TreeSet<Interval> set;
            int nums;

            public CountIntervals() {
                set = new TreeSet<>();
                nums = 0;
            }

            public void add(int left, int right) {
                //tailSet()方法返回包含指定元素的指定元素（作为参数传递）之后的树集的所有元素。
                //booleanValue参数是可选的。默认值为true。
                //如果false作为a传递booleanValue，则该方法将返回指定后的所有元素，element而不包括指定的element
                //起始需要找的区间，按[0,left]
                //返回的是 比left 都要大的区间（这些区间的右端点比left更大）
                Iterator<Interval> it = set.tailSet(new Interval(0, left), false).iterator();
                while (it.hasNext()) {
                    Interval interval = it.next();
                    //当前的right比iterator中的左区间要小，说明
                    // current:[left,right] 与 iterator中的[left,right]没有相交叉的区间，可以提前退出了
                    if (right < interval.left) {
                        break;
                    }
                    //更新区间，左区间更左，右区间更右
                    left = Math.min(left, interval.left);
                    right = Math.max(right, interval.right);
                    //当前区间开始移除
                    it.remove();
                    //数量更新
                    nums -= (interval.right - interval.left + 1);
                }
                set.add(new Interval(left, right));
                nums += right - left + 1;
            }

            public int count() {
                return nums;
            }
        }

        static class Interval implements Comparable<Interval> {
            int left;
            int right;

            public Interval(int left, int right) {
                this.left = left;
                this.right = right;
            }

            //按右区间从小到大排序，如果右区间相同则按左区间从小到大排序
            public int compareTo(Interval that) {
                if (this.right == that.right)
                    return this.left - that.left;
                return this.right - that.right;
            }

            @Override
            public String toString() {
                return "Interval{" +
                        "left=" + left +
                        ", right=" + right +
                        '}';
            }
        }
    }

    static class _4th_3 {


        public static void main(String[] args) {
            String[] ops = {"CountIntervals", "add", "add", "count", "add", "count"};
            int[][] vals = {{}, {2, 3}, {7, 10}, {}, {5, 8}, {}};
            ops = new String[]{"add", "add", "add"};
            vals = new int[][]{{6, 12}, {10, 35}, {32, 40}};
            ops = new String[]{"CountIntervals", "add", "add", "add", "count", "count", "count", "add", "add", "add", "count", "add", "count", "add", "count", "count", "count", "count", "count", "add", "add", "add", "add", "count", "add", "count", "add", "count", "count", "add", "count", "count", "add", "count", "count", "count", "add", "add", "add", "count", "add", "add", "add", "add", "count", "add", "count", "count", "add", "add", "add", "add", "add", "count", "count", "add", "add", "count", "count", "count", "add", "add", "add", "add", "count", "add", "add", "add", "count", "count", "count", "count", "add", "count", "count", "add", "add", "count", "add", "count", "count", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "add", "count", "count", "count", "add", "count", "add", "add", "count", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count", "count", "count", "add", "count", "add", "add", "add", "count", "add", "count", "count", "count", "add", "count", "count", "count", "add", "count", "count", "add", "count", "add"};
            vals = new int[][]{{}, {365, 897}, {261, 627}, {781, 884}, {}, {}, {}, {328, 495}, {224, 925}, {228, 464}, {}, {416, 451}, {}, {747, 749}, {}, {}, {}, {}, {}, {740, 757}, {51, 552}, {20, 896}, {459, 712}, {}, {383, 670}, {}, {701, 924}, {}, {}, {392, 591}, {}, {}, {935, 994}, {}, {}, {}, {398, 525}, {335, 881}, {243, 517}, {}, {995, 1000}, {15, 335}, {430, 490}, {376, 681}, {}, {733, 841}, {}, {}, {603, 633}, {974, 978}, {466, 786}, {241, 753}, {259, 887}, {}, {}, {410, 514}, {173, 300}, {}, {}, {}, {805, 957}, {272, 805}, {723, 858}, {113, 118}, {}, {426, 987}, {318, 997}, {741, 978}, {}, {}, {}, {}, {701, 992}, {}, {}, {562, 766}, {987, 1000}, {}, {929, 929}, {}, {}, {926, 931}, {913, 975}, {}, {930, 962}, {707, 914}, {688, 757}, {}, {430, 433}, {452, 683}, {794, 919}, {799, 991}, {}, {}, {}, {658, 731}, {}, {328, 686}, {998, 999}, {}, {455, 938}, {981, 988}, {}, {92, 699}, {311, 690}, {916, 920}, {}, {213, 339}, {605, 961}, {719, 902}, {}, {}, {}, {129, 833}, {}, {844, 926}, {940, 956}, {148, 182}, {}, {163, 885}, {}, {}, {}, {532, 886}, {}, {}, {}, {306, 906}, {}, {}, {948, 963}, {}, {116, 853}};
            ops = new String[]{"add", "add", "add", "add"};
            //[805, 957] ->  {"15":[15,925],"805":[935,994],"995":[995,1000]}
            vals = new int[][]{{15, 25}, {35, 84}, {95, 100}, {20, 40}};
            CountIntervals ci = new CountIntervals();
            for (int i = 0; i < ops.length; i++) {
                String op = ops[i];
                if (op.equals("add")) {
                    int l = vals[i][0], r = vals[i][1];
                    ci.add(l, r);
                    System.out.printf("%d -> %s ->  %s\n", i, Arrays.toString(vals[i]), JSONObject.toJSON(ci.map));
                } else if (op.equals("count")) {
                    System.out.println(ci.count());
//                    if (ci.count() == 1640) {
//                        System.out.printf("%d\n", i);
//                    }
                }
            }
        }

        static class CountIntervals {
            int sum;
            //k : 每个区间的左端点，v:这个区间[l,r]
            TreeMap<Integer, int[]> map;


            public CountIntervals() {
                sum = 0;
                map = new TreeMap<>();
            }
            //返回小于等于key的第一个元素：
            //    Map.Entry<K,V> floorEntry(K key);

            public void add(int left, int right) {
                //返回小于等于right的key的第一个元素
                Map.Entry<Integer, int[]> e = map.floorEntry(right);
                // l，r分别记录此次操作后最终的区间的 左边值、右边值
                int l = left;
                int r = right;
                //当前的元素找到了，且元素的右区间比当前的[l,r]的左区间要大
                while (e != null && e.getValue()[1] >= l) {
                    // 删除区间，同时对sum做减法，e区间即将要被删除
                    sum -= e.getValue()[1] - e.getValue()[0] + 1;
                    //e 和 [l,r]来更新一个新的区间
                    l = Math.min(l, e.getValue()[0]);
                    r = Math.max(r, e.getValue()[1]);
                    //移除e这个原来的区间
                    map.remove(e.getKey());
                    //继续找小于等于right的key的第一个元素
                    e = map.floorEntry(right);
                }
                // 一次操作过后，添加最终的区间，对sum做加法
                map.put(l, new int[]{l, r});
                sum += r - l + 1;
            }

            public int count() {
                return sum;
            }

        }

    }


    static class _4th_4 {
        public static void main(String[] args) {

        }

        static class CountIntervals {

            TreeSet<int[]> ts;
            int sum;

            public CountIntervals() {
                ts = new TreeSet<>((a, b) -> a[1] - b[1]);
                sum = 0;
            }

            public void add(int left, int right) {
                Iterator<int[]> it = ts.tailSet(new int[]{0, left}).iterator();
                while (it.hasNext()) {
                    int[] interval = it.next();
                    if (right < interval[0]) {
                        break;
                    }
                    left = Math.min(left, interval[0]);
                    right = Math.max(right, interval[1]);
                    it.remove();
                    sum -= interval[1] - interval[0] + 1;
                }
                ts.add(new int[]{left, right});
                sum += right - left + 1;
            }

            public int count() {
                return sum;
            }
        }


    }

    //线段树，动态开点
    static class _4th_5 {
        class CountIntervals {
            CountIntervals left, right;
            int l, r, cnt;

            public CountIntervals() {
                l = 1;
                r = (int) 1e9;
            }

            CountIntervals(int l, int r) {
                this.l = l;
                this.r = r;
            }

            public void add(int L, int R) { // 为方便区分变量名，将递归中始终不变的入参改为大写（视作常量）
                if (cnt == r - l + 1) return; // 当前节点已被完整覆盖，无需执行任何操作
                if (L <= l && r <= R) { // 当前节点已被区间 [L,R] 完整覆盖，不再继续递归
                    cnt = r - l + 1;
                    return;
                }
                int mid = (l + r) / 2;
                if (left == null) left = new CountIntervals(l, mid); // 动态开点
                if (right == null) right = new CountIntervals(mid + 1, r); // 动态开点
                if (L <= mid) left.add(L, R);
                if (mid < R) right.add(L, R);
                cnt = left.cnt + right.cnt;
            }

            public int count() {
                return cnt;
            }
        }

    }
}
