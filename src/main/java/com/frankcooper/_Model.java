package com.frankcooper;

/*import java.util.*;
import org.junit.Assert;*/
public class _Model {

    static class _1st {
        public static void main(String[] args) {
//            _1st handler = new _1st();

            long start = System.currentTimeMillis();
            for (int i = 0; i < 10000000; i++) {
                System.out.println(i);
            }
            long end = System.currentTimeMillis();
            System.out.println("共耗时" + (end - start) + "毫秒");

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
