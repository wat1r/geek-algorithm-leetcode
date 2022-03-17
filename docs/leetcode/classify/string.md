> 

## [8. 字符串转换整数 (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/)

```java
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            System.out.println(Integer.MAX_VALUE); //2147483647
            System.out.println(Integer.MIN_VALUE);//-2147483648
            Assert.assertEquals(0, handler.myAtoi("words and 987"));
            Assert.assertEquals(42, handler.myAtoi("42"));
            Assert.assertEquals(-42, handler.myAtoi("    -42"));
            Assert.assertEquals(2147483647, handler.myAtoi("2147483649"));
            Assert.assertEquals(-2147483648, handler.myAtoi("   -2147483649  "));

        }


        public int myAtoi(String s) {
            s = s.trim();
            if (s == null || s.length() == 0) return 0;
            int sign = 1;
            int start = 0;
            if (s.charAt(0) == '+') {
                start = 1;
                sign = 1;
            } else if (s.charAt(0) == '-') {
                start = 1;
                sign = -1;
            }
            long res = 0;
            for (int i = start; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) return sign * (int) res;
                res = res * 10 + s.charAt(i) - '0';
                if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
            }
            return sign * (int) res;
        }
```