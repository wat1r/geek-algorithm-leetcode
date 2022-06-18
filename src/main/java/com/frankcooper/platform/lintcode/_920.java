package com.frankcooper.platform.lintcode;

import java.util.*;

import com.frankcooper.struct.Interval;
import org.junit.Assert;

/**
 * 同 _252
 */
public class _920 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            intervals = [(0,30),(5,10),(15,20)]
            List<Interval> list = new ArrayList<Interval>() {{
                add(new Interval(0, 30));
                add(new Interval(5, 10));
                add(new Interval(15, 20));
            }};
            Assert.assertEquals(handler.canAttendMeetings(list), false);
            list = new ArrayList<Interval>() {{
                add(new Interval(5, 8));
                add(new Interval(9, 15));
            }};
            Assert.assertEquals(handler.canAttendMeetings(list), true);
            list = new ArrayList<Interval>() {{
                add(new Interval(1, 2));
                add(new Interval(2, 4));
                add(new Interval(4, 7));
            }};
            Assert.assertEquals(handler.canAttendMeetings(list), true);
        }

        public boolean canAttendMeetings(List<Interval> list) {
            list.sort(((o1, o2) -> o1.start - o2.start));
            for (int i = 0; i < list.size() - 1; i++) {
                Interval curr = list.get(i), next = list.get(i + 1);
                if (curr.end > next.start) return false;
            }
            return true;
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
