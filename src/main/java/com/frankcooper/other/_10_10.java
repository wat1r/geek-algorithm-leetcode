package com.frankcooper.other;

import com.frankcooper.bank._1;

import java.util.ArrayList;
import java.util.*;

public class _10_10 {


    static _10_10 handler = new _10_10();

    public static void main(String[] args) {
        List<Integer> arrList = new ArrayList<Integer>();
        arrList.add(3);
        arrList.add(5);
        arrList.add(7);
        arrList.add(2);
        Collections.sort(arrList);
        int idx = Collections.binarySearch(arrList, 6);

        System.out.println("end");


    }

    static class _1st {

        static _1st handler = new _1st();

        public static void main(String[] args) {
            String[] op = {"StreamRank", "getRankOfNumber", "track", "getRankOfNumber", "track", "track", "track", "getRankOfNumber", "getRankOfNumber"};
            int[][] arr = {{}, {1}, {0}, {0}, {2}, {3}, {2}, {2}, {3}};
            StreamRank streamRank = null;
            for (int i = 0; i < op.length; i++) {
                switch (op[i]) {
                    case "StreamRank":
                        streamRank = new StreamRank();
                        break;
                    case "getRankOfNumber":
                        streamRank.getRankOfNumber(arr[i][0]);
                        break;
                    case "track":
                        streamRank.track(arr[i][0]);
                        break;
                    default:
                        break;
                }
            }


        }

        static class StreamRank {
            private List<Integer> list;

            public StreamRank() {
                list = new ArrayList<>();
            }

            public void track(int x) {
                System.out.printf("track:%d\n", x);
                int idx = Collections.binarySearch(list, x);
                if (idx < 0) {
                    idx = -idx - 1;
                }
                list.add(idx, x);
            }

            public int getRankOfNumber(int x) {
                System.out.printf("getRankOfNumber:%d\n", x);
                int idx = Collections.binarySearch(list, x);
                if (idx < 0) {
                    idx = -idx - 1;
                }
                //这里的 x >= list.get(idx) 是为了处理 0 1 2 2  3  当 getRankOfNumber(2)的时候
                while (idx < list.size() && x >= list.get(idx)) {
                    idx++;
                }
                return idx;
            }
        }
    }

}
