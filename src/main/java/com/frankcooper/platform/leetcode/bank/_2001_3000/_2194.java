import java.util.*;
import org.junit.Assert;
public class _2194 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();


        }

        public List<String> cellsInRange(String s) {
            List<String> res = new ArrayList<>();
            String[] arr = s.split(":");
            String start = arr[0], end = arr[1];
            for (char c = start.charAt(0); c <= end.charAt(0); c++) {
                for (char i = start.charAt(1); i <= end.charAt(1); i++) {
                    res.add(c + "" + i);
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


