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

## [30. 串联所有单词的子串](https://leetcode.cn/problems/substring-with-concatenation-of-all-words/)

### 方法1：哈希+比较

```java
        public List<Integer> findSubstring(String s, String[] words) {
            Map<String, Integer> dict = new HashMap<>();//words的字典map，k为当前单词word v为出现的次数
            int len = 0, w_len = 0; //总的长度，当个单词的长度，每个单词的长度都是相等的，只需要记录一个即可
            for (String word : words) {
                len += word.length();
                w_len = word.length();
                dict.put(word, dict.getOrDefault(word, 0) + 1);
            }
            int n = s.length();
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i + len - 1 < n; i++) {
                Map<String, Integer> can = new HashMap<>();//候选的map
                String sub = s.substring(i, i + len);
                // System.out.println(sub);
                //以每次w_len的步长切分单词
                for (int j = 0; j < len; j += w_len) {
                    String seg = sub.substring(j, j + w_len);
                    can.put(seg, can.getOrDefault(seg, 0) + 1);
                }
                //dict与can一致的时候，说明可以形成
                if (dict.equals(can)) {
                    res.add(i);
                }
            }
            return res;
        }
```









## [38. 外观数列](https://leetcode-cn.com/problems/count-and-say/)

### 方法1.模拟

```java
public String countAndSay(int n) {
    String str = "1";
    for (int i = 2; i <= n; i++) {
        StringBuilder sb = new StringBuilder();
        char prev = str.charAt(0);
        int cnt = 1;
        for (int j = 1; j < str.length(); j++) {
            char cur = str.charAt(j);
            if (cur == prev) cnt++;
            else {
                sb.append(cnt).append(prev);
                cnt = 1;
                prev = cur;
            }
        }
        sb.append(cnt).append(prev);
        str = sb.toString();
    }
    return str;
}
```

另外一种写法：

```java
        public String countAndSay(int n) {
            String res = "1";
            for (int i = 2; i <= n; i++) {
                String cur = "";
                char[] ch = res.toCharArray();
                for (int j = 0; j < ch.length; ) {
                    int k = j + 1;
                    while (k < ch.length && ch[j] == ch[k]) k++;
                    int cnt = k - j;
                    cur += cnt + "" + ch[j];
                    j = k;
                }
                res = cur;
            }
            return res;
        }
```

### 方法2.打表

- 略



## [394. 字符串解码](https://leetcode-cn.com/problems/decode-string/)

```java
public String decodeString(String s) {

    LinkedList<Integer> numList = new LinkedList<>();
    LinkedList<String> strList = new LinkedList<>();
    int multi = 0;
    StringBuilder res = new StringBuilder();
    char[] chas = s.toCharArray();
    for (int i = 0; i < chas.length; i++) {
        char c = chas[i];
        if (c == '[') {
            numList.addLast(multi);
            strList.addLast(res.toString());
            multi = 0;
            res = new StringBuilder();
        } else if (c == ']') {
            StringBuilder tmp = new StringBuilder();
            int curMulti = numList.pollLast();
            for (int j = 0; j < curMulti; j++) {
                tmp.append(res);
            }
            res = new StringBuilder(strList.pollLast() + tmp);
        } else if (c >= '0' && c <= '9') {
            multi = multi * 10 + Integer.valueOf(c + "");
        } else {
            res.append(c);
        }
    }
    return res.toString();
}
```





## [395. 至少有 K 个重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/)

```java
public int longestSubstring(String s, int k) {
    if (s.length() < k) return 0;
    Map<Character, Integer> counter = new HashMap();
    for (char c : s.toCharArray()) {
        counter.put(c, counter.getOrDefault(c, 0) + 1);
    }
    for (char c : counter.keySet()) {
        if (counter.get(c) < k) {
            int res = 0;
            for (String t : s.split(String.valueOf(c))) {
                res = Math.max(res, longestSubstring(t, k));
            }
            return res;
        }
    }
    return s.length();
}
```



```java
public int longestSubstring(String s, int k) {
    if (s.length() < k) return 0;
    int[] hash = new int[26];
    for (char c : s.toCharArray()) hash[c - 'a']++;
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (hash[c - 'a'] < k && hash[c - 'a'] != 0) {
            String part1 = s.substring(0, i);//按当前点进行切分
            String part2 = s.substring(i + 1);
            res = Math.max(longestSubstring(part1, k), longestSubstring(part2, k));
            return res;
        }
    }
    return s.length();
}
```







## [468. 验证IP地址](https://leetcode-cn.com/problems/validate-ip-address/)



```java
static class _1st {
    public String validIPAddress(String IP) {
        String[] arr = IP.split("\\.");
        if (arr.length <= 1) {
            if (IP.endsWith(":")) return "Neither";
            arr = IP.split(":");
            boolean ipv6 = isIPV6(arr);
            if (ipv6) return "IPv6";
        } else {
            if (IP.endsWith(".")) return "Neither";
            boolean ipv4 = isIPV4(arr);
            if (ipv4) return "IPv4";
        }
        return "Neither";
    }


    private boolean isIPV4(String[] arr) {
        if (arr.length != 4) return false;
        for (String a : arr) {
            if (a.length() == 0 || a.length() > 3) return false;
            if (a.startsWith("0") && a.length() > 1) return false;
            if (!isDigit(a)) return false;
            if (Integer.parseInt(a) >= 256 || Integer.parseInt(a) < 0) return false;
        }
        return true;
    }


    private boolean isIPV6(String[] arr) {
        if (arr.length != 8) return false;
        for (String a : arr) {
            if (a.length() == 0 || a.length() > 4) return false;
            if (!checkIPV6(a)) return false;
        }
        return true;
    }


    private boolean isDigit(String a) {
        char[] chas = a.toCharArray();
        for (char c : chas) {
            int ic = (int) c - (int) '0';
            if (!(ic >= 0 && ic <= 9)) return false;
        }
        return true;
    }


    private boolean checkIPV6(String a) {
        for (int i = 0; i < a.length(); ++i) {
            char c = a.charAt(i);
            if (!Character.isDigit(c) && !('a' <= c && c <= 'f') && !('A' <= c && c <= 'F')) {
                return false;
            }
        }
        return true;
    }
}
```



## [415. 字符串相加](https://leetcode-cn.com/problems/add-strings/)





```java
public String addStrings(String num1, String num2) {
    int m = num1.length(), n = num2.length();
    int i = m - 1, j = n - 1;
    int carry = 0;
    StringBuilder res = new StringBuilder();
    while (i >= 0 || j >= 0 || carry != 0) {
        int tmp = 0;
        int first = i < 0 ? 0 : num1.charAt(i) - '0';
        int second = j < 0 ? 0 : num2.charAt(j) - '0';
        tmp = first + second + carry;
        carry = tmp / 10;
        int remain = tmp % 10;
        res.append(remain);
        i--;
        j--;
    }
    return res.reverse().toString();
}
```





## [804. 唯一摩尔斯密码词](https://leetcode-cn.com/problems/unique-morse-code-words/)

### 方法1:Hash

- 每个单词拼接成莫斯密码的字符，送到set中，去重

```java
        String[] dict = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};


        public int uniqueMorseRepresentations(String[] words) {
            Set<String> set = new HashSet<>();
            for (String word : words) {
                StringBuilder sb = new StringBuilder();
                for (char c : word.toCharArray()) {
                    sb.append(dict[c - 'a']);
                }
                set.add(sb.toString());
            }
            return set.size();
        }
```



### 方法2:位运算



![](/imgs/leetcode/classify/image-20220410085817240.png)



- 将当前的字符转成二进制，`-`表示二进制中的1，`.`表示二进制中的`0`,做法有点像[**二维矩阵的常见转换技巧**](https://cnwangzhou.gitbook.io/algorithm/zhuan-lan/er-wei-ju-zhen-de-chang-jian-zhuan-huan-ji-qiao)



```java
        int[] bin = new int[26];
        String[] dict = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        public int uniqueMorseRepresentations(String[] words) {
            //只做二进制的映射关系
            for (int i = 0; i < dict.length; i++) {
                encode(dict[i], i);
            }
            Set<Integer> set = new HashSet<>();
            for (String word : words) {
                int x = 0;
                for (int i = 0; i < word.length(); i++) {
                    //拿到word中当前字符的索引
                    int idx = word.charAt(i) - 'a';
                    //先将x向左移位len(dict[idx]) 将该字符对应的莫斯密码位的数拼接到x上
                    x = x << dict[idx].length() | bin[idx];
                }
              //  System.out.printf("%s->%4d->%10s", word, x, PrintUtils.toBinaryString(x, 10));
                set.add(x);
            }
            return set.size();
        }

        //将形如 -... 的莫斯密码 翻译成 二进制 -表示1 .表示0
        //-... -> 1000(2) -> 16(10)
        public int encode(String s, int idx) {
            int x = 0;
            int n = s.length();
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == '-') {
                    x |= (1 << (n - 1 - i));
                }
            }
            this.bin[idx] = x;
            return x;
        }
```



## [806. 写字符串需要的行数](https://leetcode-cn.com/problems/number-of-lines-to-write-string/)

```java
public int[] numberOfLines(int[] widths, String s) {
    //行数 最后一行个留下的字符的宽度
    int lines = 1, num = 0;
    int i = 0;
    while (i < s.length()) {
        int t = widths[s.charAt(i) - 'a'];
        num += t;
        if (num > 100) {
            lines++;
            num = t;
        }
        i++;
    }
    return new int[]{lines, num};
}
```





## [890. 查找和替换模式](https://leetcode.cn/problems/find-and-replace-pattern/)

### 方法1：两次Hash映射

```java
        public List<String> findAndReplacePattern(String[] words, String pattern) {
            List<String> res = new ArrayList<>();
            for (String word : words) {
                if (check(word, pattern) && check(pattern, word)) res.add(word);
            }
            return res;
        }


        public boolean check(String source, String target) {
            Map<Character, Character> map = new HashMap<>();
            for (int i = 0; i < source.length(); i++) {
                //相同字符映射的字符应该是唯一的
                char s = source.charAt(i), t = target.charAt(i);
                if (!map.containsKey(s)) {
                    map.put(s, t);
                } else if (map.get(s) != t) {
                    return false;
                }
            }
            return true;
        }
```
