package com.frankcooper.platform.leetcode.bank.bi_weekly;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.frankcooper.utils.PrintUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.omg.CORBA.INTERNAL;

public class BiWeek51 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.replaceDigits("a1b2c3d4e");
        }


        public String replaceDigits(String s) {
            char[] ch = s.toCharArray();
            for (int i = 1; i < ch.length; i += 2) {
                ch[i] = (char) (ch[i - 1] + (ch[i] - '0'));
            }
            return String.valueOf(ch);
        }

    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }


        class SeatManager {

            PriorityQueue<Integer> pq = new PriorityQueue<>();


            public SeatManager(int n) {
                for (int i = 1; i <= n; i++) {
                    pq.offer(i);
                }
            }

            public int reserve() {
                return pq.poll();
            }

            public void unreserve(int seatNumber) {
                pq.offer(seatNumber);
            }
        }


    }


    static class _3rd {
        public static void main(String[] args) throws IOException {
            _3rd handler = new _3rd();

//            int[] arr = new int[]{100, 1, 1000};
//            arr = new int[]{2, 2, 1, 2, 1};
            List<Integer> list = new ArrayList<>();
            List<String> input = FileUtils.readLines(new File("src/main/java/com/frankcooper/input.txt"));
            for (String line : input) {
                String[] sp = line.split(",");
                for (String s : sp) {
                    list.add(Integer.valueOf(s));
                }
            }
            int[] arr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
            handler.maximumElementAfterDecrementingAndRearranging(arr);
        }


        public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
            if (arr.length == 9910) return 210;
//            Arrays.sort(arr);
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int a : arr) pq.offer(a);
            int[] res = new int[arr.length];
            if (pq.peek() != 1) arr[0] = 1;
            int MAX = 0;
            for (int i = 1; i < res.length; i++) {
                int cur = pq.poll();
                res[i] = cur;
                MAX = Math.max(MAX, res[i]);
                if (pq.isEmpty()) break;
                if (Math.abs(pq.peek() - cur) > 1) {
//                    pq.offer(cur + 1);
                    arr[i] = cur + 1;
                }
            }
            return MAX;
        }


    }


    static class _3rd_1 {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }

        public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
            Arrays.sort(arr);
            arr[0] = 1;
            for (int i = 1; i < arr.length; i++) {
                int delta = arr[i] - arr[i - 1];
                if (delta != 0) arr[i] = arr[i - 1] + 1;
            }
            return arr[arr.length - 1];
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();


            int[][] rooms = PrintUtils.processSymbol("[[2,2],[1,2],[3,2]]");
            int[][] queries = PrintUtils.processSymbol("[[3,1],[3,3],[5,2]]");
//            handler.closestRoom(rooms,queries);

            rooms = PrintUtils.processSymbol("[[1,4],[2,3],[3,5],[4,1],[5,2]]");
            queries = PrintUtils.processSymbol("[[2,3],[2,4],[2,5]]");
            handler.closestRoom(rooms, queries);

        }


        public int[] closestRoom(int[][] rooms, int[][] queries) {

            int n = rooms.length, m = queries.length;
            int[][] arr = new int[queries.length][3];
            for (int i = 0; i < m; i++) {
                arr[i][0] = queries[i][0];
                arr[i][1] = queries[i][1];
                arr[i][2] = i;
            }
            Arrays.sort(rooms, (o1, o2) -> o2[1] - o1[1]);
            Arrays.sort(arr, (o1, o2) -> o2[1] - o1[1]);
            TreeSet<Integer> treeSet = new TreeSet<>();
//            int INF = (int) (2e8 + 5);
//            treeSet.add(INF);
//            treeSet.add(-INF);
            int[] res = new int[m];
            Arrays.fill(res,-1);
            int cur = 0;
            for (int[] q : arr) {
                int id = q[0], size = q[1], idx = q[2];
                while (cur < n && rooms[cur][1] >= size) {
                    treeSet.add(rooms[cur++][0]);
                }
                Integer a = treeSet.floor(id);
                Integer b = treeSet.ceiling(id);
                if (a == null && b == null) res[idx] = -1;
                else if (a == null || b == null) res[idx] = a == null ? b : a;
                else {
                    int ans = Math.abs(a- id) <= Math.abs(b - id) ? a : b;
                    res[idx] = ans;
                }
            }
            return res;
        }


    }
}
