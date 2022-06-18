package com.frankcooper.platform.leetcode.bank._401_500;

public class _405 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            handler.toHex(26);
        }

        public String toHex(int num) {
            if (num == 0) return "0";
            // String res ="";
            char[] chas = "0123456789abcdef".toCharArray();
            StringBuilder sb = new StringBuilder();
            while (num != 0) {
                int t = num & ((1 << 4) - 1);
                sb.append(chas[t]);
                num >>>= 4;
            }
            return sb.reverse().toString();
        }


     /*   public String toHex(int num) {
            if (num == 0) {
                return "0";
            }   // 0特殊处理
            char[] hex = "0123456789abcdef".toCharArray();  // 相当于映射关系
            StringBuilder ans = new StringBuilder();
            while (num != 0) {
                int temp = num & 0xf;   // 取低4位的十进制值
                ans.append(hex[temp]);  // 映射对应字符
                num >>>= 4;             // 逻辑右移4位
            }
            // while的循环条件保证了不会出现前导0
            // 但是从低位开始转换多了一步reverse反转
            return ans.reverse().toString();
        }
*/

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
