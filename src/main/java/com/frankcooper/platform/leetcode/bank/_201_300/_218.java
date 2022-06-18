package com.frankcooper.platform.leetcode.bank._201_300;

import java.util.*;

public class _218 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();

        }


        /**
         * 218. 天际线问题 Hard
         *
         * @param buildings
         * @return
         */
        public List<int[]> getSkyline(int[][] buildings) {
            List<int[]> resList = new ArrayList<>();
            if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0) {
                return resList;
            }
            //定义比较器，先按pos从小到大排序，pos相等，按height从小到大排
            PriorityQueue<Point> queue = new PriorityQueue<>((o1, o2) -> {
                if (o1.pos != o2.pos) {
                    return o1.pos - o2.pos;
                }
                if (o1.height != o2.height) {
                    return o1.height - o2.height;
                }
                return 0;
            });
            //生成queue，<第一个位置，负高度>，<第二个位置，正高度>
            for (int i = 0; i < buildings.length; i++) {
                queue.offer(new Point(buildings[i][0], -buildings[i][2]));
                queue.offer(new Point(buildings[i][1], buildings[i][2]));
            }
            PriorityQueue<Integer> maxHeightQueue = new PriorityQueue<>(Comparator.reverseOrder());
            maxHeightQueue.offer(0);
            int prePeak = maxHeightQueue.peek();
            while (!queue.isEmpty()) {
                //当当前高度是负数，说明是上升的，加到maxHeightQueue，反之移除掉
                Point point = queue.poll();
                if (point.height < 0) {
                    maxHeightQueue.offer(-point.height);
                } else {
                    maxHeightQueue.remove(point.height);
                }
                int curPeak = maxHeightQueue.peek();
                if (curPeak != prePeak) {
                    resList.add(new int[]{point.pos, curPeak});
                    prePeak = curPeak;
                }
            }
            return resList;
        }

        class Point {
            int pos, height;

            public Point(int pos, int height) {
                this.pos = pos;
                this.height = height;
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
