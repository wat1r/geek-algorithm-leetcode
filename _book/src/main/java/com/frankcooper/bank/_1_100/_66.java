package com.frankcooper.bank._1_100;

/*import java.util.*;
import org.junit.Assert;*/
public class _66 {

    static class _1st {
        public static void main(String[] args) {
//            _1st handler = new _1st();


        }


        /**
         * 66. 加一 Easy
         *
         * @param digits
         * @return
         */
        public int[] plusOne(int[] digits) {
            if (digits.length == 0) {
                return digits;
            }
            digits[digits.length - 1] += 1;
            //Check index digit.length-1 to 1
            for (int i = digits.length - 1; i > 0; i--) {
                if (digits[i] == 10) {
                    digits[i] = 0;
                    digits[i - 1] += 1;
                } else {
                    return digits;
                }
            }

            //Check index 0. If ==0, set it to 0 and carry over 1
            if (digits[0] == 10) {
                int[] output = new int[digits.length + 1];
                output[0] = 1;
                return output;
            } else {
                return digits;
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
