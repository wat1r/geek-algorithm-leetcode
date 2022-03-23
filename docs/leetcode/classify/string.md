> 

## [6. Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion/)

```java
public String convert(String s, int numRows) {
    //r表示每一行的索引 [0,numRows-1]
    //flag是 1 或者 -1 按从上到下或者从下到上 两个方向区分正负
    int r = 0, flag = 1;
    //结果列表
    StringBuilder[] sb = new StringBuilder[numRows];
    for (int k = 0; k < numRows; k++) sb[k] = new StringBuilder();
    int i = 0;
    while (i < s.length()) {
        if (r == numRows) {
            flag = -flag;
            //区分nowrows 是否为1
            if (numRows == 1) r -= 1;
            else r -= 2;
        } else if (r == -1) {
            flag = -flag;
            if(numRows == 1) r+=1;
            else r+=2;
        }

        sb[r].append(s.charAt(i++));
        r += flag;
    }
    StringBuilder ans = new StringBuilder();
    for (int k = 0; k < numRows; k++) ans.append(sb[k]);
    return ans.toString();
}
```



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







