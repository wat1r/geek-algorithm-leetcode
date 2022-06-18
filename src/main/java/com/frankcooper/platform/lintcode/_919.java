package com.frankcooper.platform.lintcode;


import java.util.*;

import com.frankcooper.struct.Interval;
import org.junit.Assert;

/**
 * 同 _253
 */
public class _919 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            List<Interval> list = new ArrayList<Interval>() {{
                add(new Interval(1, 4));
                add(new Interval(2, 8));
                add(new Interval(3, 4));
                add(new Interval(5, 7));
                add(new Interval(5, 9));
            }};
            Assert.assertEquals(handler.minMeetingRooms(list), 3);
        }

        public int minMeetingRooms(List<Interval> list) {
            list.sort(((o1, o2) -> o1.start - o2.start));
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o1 - o2));
            pq.offer(list.get(0).end);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).start > pq.peek()) {
                    pq.poll();
                }
                pq.offer(list.get(i).end);
            }
            return pq.size();
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
