package com.frankcooper.other;

import com.alibaba.fastjson.JSON;
import com.frankcooper.utils.PrintUtils;
import org.junit.Assert;

import java.util.*;
import java.util.concurrent.*;

public class WeChatSolution {


    static class _1st {

        static _1st handler = new _1st();

        double[] scores;
        double[][] userScores;
        int N, M;

        public static void main(String[] args) {
            handler.autoProcess();
            handler.dfs(new ArrayList<>());
            handler.find();
        }


        private void autoProcess() {
            Scanner in = new Scanner(System.in);
            N = in.nextInt();
            M = in.nextInt();
            scores = new double[N];
            for (int i = 0; i < N; i++) {
                scores[i] = in.nextDouble();
            }
            userScores = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    userScores[i][j] = in.nextDouble();
                }
            }

            PrintUtils.printMatrix(userScores);
        }


        List<int[]> resList = new ArrayList<>();

        private void dfs(List<Integer> list) {
            if (list.size() == M) {
                resList.add(new ArrayList<>(list).stream().mapToInt(Integer::intValue).toArray());
                return;
            }
            for (int i = 0; i < N; i++) {
                if (list.contains(i)) continue;
                list.add(i);
                dfs(list);
                list.remove(list.size() - 1);
            }
        }


        double max = 0.0;

        private void find() {
            for (int[] arr : resList) {
                Map<Integer, List<Integer>> vis = new HashMap<>();
                double tmp = 0.0;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        tmp += scores[arr[i]];
                        tmp += scores[arr[j]];
                        if (vis.get(arr[i]) == null || !vis.get(arr[i]).contains(arr[j])) {
                            tmp += userScores[arr[i]][arr[j]];
                            List<Integer> orDefault = vis.getOrDefault(arr[i], new ArrayList<Integer>());
                            orDefault.add(arr[j]);
                            vis.put(arr[i], orDefault);
                        }
                    }
                }
                if (tmp > max) {
                    max = tmp;
                    System.out.println(tmp);
                    System.out.println(JSON.toJSONString(arr));
                }
            }
        }

    }


    static class _2nd {
        public static void main(String[] args) {

            Long res = (long) Integer.MAX_VALUE * 60;
            System.out.println(res);

//            Timestamp timestamp = new Timestamp();
//            timestamp.getTime()
            System.out.println(Long.MAX_VALUE & 0xffffffffL);
            System.out.println(PrintUtils.addZeroForNum(Integer.toBinaryString(Integer.MAX_VALUE), 50));
            System.out.println(PrintUtils.addZeroForNum(Integer.toBinaryString(0xffffffff), 50));
            System.out.println(PrintUtils.addZeroForNum(Long.toBinaryString(0xffffffffL), 50));
            System.out.println(PrintUtils.addZeroForNum(Integer.toBinaryString(-1), 50));

            /**
             * (int)(timestamp.gettime/1000+endtime*60)&0xffffffff
             */
        }
    }

    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
            handler.process("aa23bb3c678");
            handler.process("a11b2");
        }


        private int[] process(String s) {
            List<Integer> list = new ArrayList<>();
            char[] chas = s.toCharArray();
            int l = 0, r = 0, n = s.length();
            while (r < n) {
                while (chas[l] >= 'a' && chas[l] <= 'z') {
                    l++;
                    r++;
                }
                while (r < n && chas[r] >= '0' && chas[r] <= '9') r++;
                list.add(Integer.valueOf(s.substring(l, r)));
                l = r;
            }
            int[] ans = new int[list.size()];
            for (int i = 0; i < list.size(); i++) ans[i] = list.get(i);
            return ans;

        }
    }


    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();

            handler.sqrt(9);
            System.exit(0);
            for (int i = 0; i < 21; i++) {
                long a = handler.sqrt(Long.valueOf(i));
                long b = a * a;
                if (b < i) {
                    b = (a + 1) * (a + 1);
                }
                if (i == 0) {
                    System.out.println(true);
                }
                if (b >= i && i < (a + 1) * (a + 1) && i > (a - 1) * (a - 1)) {
                    System.out.println(true);
                } else {
                    System.out.println("平方根" + a + "次方" + b + "对应整数" + i);

                }
            }

        }

        public long sqrt(long x) {
            long l = 0;
            long r = x;
            long ans = -1;
            while (l <= r) {
                long mid = l + (r - l) / 2;
                if (mid * mid <= x) {
                    ans = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }

            }
            return ans;
        }


        public int mySqrt(int x) {
            int l = 0, r = x;
            int ans = 0;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if ((long) m * m <= x) {
                    ans = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return ans;
        }


        public int mySqrt1(int x) {
            long l = 0, r = x;
            while (l <= r) {
                long m = l + (r - l) / 2;
                if (m * m > x) {
                    r = m - 1;
                } else if (m * m < x) {
                    l = m + 1;
                } else {
                    return (int) m;
                }
            }
            return (int) l - 1;
        }

        public int sqrt2(int x) {
            long l = 0, r = x;
            while (l < r) {
                long m = l + (r - l) / 2;
                if ((int) m * m >= x) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            return (int) (l * l == x ? l : l - 1);
        }


    }


    static class _5th {

        public static void main(String[] args) {
            _5th handler = new _5th();
            String a = "aabbccccccc";
            String b = "cccccccaabb";
            String c = "aabbffffffffffff";
            Assert.assertEquals(handler.process(a, b, c), 4);

        }


//        class Pair implements Comparable {
//            private int len;
//            private String str;
//
//
//            @Override
//            public int compareTo(Object o) {
//                Pair p = (Pair) o;
//                int res = this.len > p.len ? 1 : 0;
//                return res;
//            }
//        }


        /**
         * 求3个字符的LCS
         *
         * @param a
         * @param b
         * @param c
         * @return
         */
        public int process(String a, String b, String c) {
            int N = 500;
            int[][][] f = new int[N][N][N];
            int I = a.length(), J = b.length(), K = c.length();
            for (int i = 0; i <= I; i++)
                for (int j = 0; j <= J; j++)
                    for (int k = 0; k <= K; k++) {
                        if (i == 0 || j == 0 || k == 0) f[i][j][k] = 0;
                        else {
                            if (a.charAt(i - 1) == b.charAt(j - 1) && b.charAt(j - 1) == c.charAt(k - 1)) {
                                f[i][j][k] = f[i - 1][j - 1][k - 1] + 1;
                            } else {
                                f[i][j][k] = Math.max(f[i - 1][j][k], Math.max(f[i][j - 1][k], f[i][j][k - 1]));
//                                f[i][j][k] = Math.max(f[i][j][k], Math.max(f[i - 1][j - 1][k], Math.max(f[i - 1][j][k - 1], f[i][j - 1][k - 1])));
//                                f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j - 1][k - 1]);
                            }
                        }
                    }
            return f[I][J][K];
        }
    }


    static class _6th {
        static class Print {

            public static void main(String[] args) {
                Print print = new Print();
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        print.first();
                    }
                });
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        print.second();
                    }
                });
                Thread t3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        print.third();
                    }
                });
                t1.start();
                t2.start();
                t3.start();


            }


            private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
            volatile boolean first = true;
            volatile boolean second = false;
            volatile boolean third = false;
            private List<Integer> firstList = Arrays.asList(1, 2, 3, 4, 5);
            private List<Integer> secondList = Arrays.asList(6, 7, 8, 9, 10);
            private List<Integer> thirdList = Arrays.asList(11, 12, 13);

            public void first() {
                for (int i = 0; i < firstList.size(); i++) {
                    while (second || third) ;
                    System.out.print(firstList.get(i) + " ");
                    if (i + 1 < firstList.size()) {
                        i++;
                        System.out.print(firstList.get(i) + " ");
                    }
                    first = false;
                    second = true;
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }

            }

            public void second() {
                for (int i = 0; i < secondList.size(); i++) {
                    while (first || third) ;
                    System.out.print(secondList.get(i) + " ");
                    if (i + 1 < secondList.size()) {
                        i++;
                        System.out.print(secondList.get(i) + " ");
                    }
                    second = false;
                    third = true;
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            }

            public void third() {
                for (int i = 0; i < thirdList.size(); i++) {
                    while (first || second) ;
                    System.out.print(thirdList.get(i) + " ");
                    if (i + 1 < thirdList.size()) {
                        i++;
                        System.out.print(thirdList.get(i) + " ");
                    }
                    third = false;
                    first = true;
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            }


        }
    }


    static class _7th {
        static class Print {

            public static void main(String[] args) {
                Print print = new Print();
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        print.first();
                    }
                });
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        print.second();
                    }
                });
                Thread t3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        print.third();
                    }
                });
                t1.start();
                t2.start();
                t3.start();


            }


            private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
            volatile boolean first = true;
            volatile boolean second = false;
            volatile boolean third = false;
            private List<Integer> firstList = Arrays.asList(1, 1, 1, 1, 1);
            private List<Integer> secondList = Arrays.asList(2, 2, 2, 2, 2);
            private List<Integer> thirdList = Arrays.asList(3, 3, 3, 3, 3);

            public void first() {
                for (int i = 0; i < firstList.size(); i++) {
                    while (second || third) ;
                    System.out.print(firstList.get(i) + " ");
                    if (i + 1 < firstList.size()) {
                        i++;
                        System.out.print(firstList.get(i) + " ");
                    }
                    first = false;
                    second = true;
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }

            }

            public void second() {
                for (int i = 0; i < secondList.size(); i++) {
                    while (first || third) ;
                    System.out.print(secondList.get(i) + " ");
                    if (i + 1 < secondList.size()) {
                        i++;
                        System.out.print(secondList.get(i) + " ");
                    }
                    second = false;
                    third = true;
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            }

            public void third() {
                for (int i = 0; i < thirdList.size(); i++) {
                    while (first || second) ;
                    System.out.print(thirdList.get(i) + " ");
                    if (i + 1 < thirdList.size()) {
                        i++;
                        System.out.print(thirdList.get(i) + " ");
                    }
                    third = false;
                    first = true;
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            }


        }
    }


    static class _8th {

        public static void main(String[] args) {
            _8th handler = new _8th();
            List<String> list = new ArrayList<String>() {{
                add("python");
                add("hadoop");
                add("hadoop");
                add("hadoop");
                add("hadoop");
                add("java");
                add("java");
                add("java");
                add("java");
                add("java");
                add("java");
                add("java");
                add("python");
            }};
            handler.sort(list);
        }


        public Map<String, Long> sort(List<String> list) {
            ExecutorService pool = Executors.newFixedThreadPool(10);
            ConcurrentHashMap<String, Long> res = new ConcurrentHashMap<>();
            for (String s : list) {
                pool.submit(() -> {
                    res.put(s, res.getOrDefault(s, 0L) + 1L);
                });
            }
            pool.shutdown();
            List<Map.Entry<String, Long>> sortlist = new LinkedList<Map.Entry<String, Long>>(res.entrySet());
            Collections.sort(sortlist, (o1, o2) -> {
                int compare = o1.getValue().compareTo(o2.getValue());
                return compare;
            });
            Map<String, Long> result = new LinkedHashMap<>();
            for (Map.Entry<String, Long> entry : sortlist) {
                result.put(entry.getKey(), entry.getValue());
            }
            System.out.printf("%s\n", JSON.toJSONString(result));
            return result;
        }
    }

    static class _9th {
        /**
         * 小明为了交付版本 M天完成N个需求 小明很自信按顺序做需求 假设小明需要cost[i]的时间完成编号i的需求，
         * 且小明每天可以求助大神小刘最多一次小刘做需求不需要时间。假设小明在M天内消耗时间最多的那天用时T，求T最小值
         */
    }

}
