> 





## [20. 有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)

### 方法1:Stack

```java
public boolean isValid(String s) {
    Map<Character, Character> dict = new HashMap<>();
    dict.put(']', '[');
    dict.put(')', '(');
    dict.put('}', '{');
    Stack<Character> stk = new Stack<>();
    for (char c : s.toCharArray()) {
        if (c == '[' || c == '(' || c == '{') stk.push(c);
        else if (!stk.isEmpty() && dict.containsKey(c) && dict.get(c) == stk.peek()) stk.pop();
        else return false;
    }
    return stk.isEmpty();
}
```

### 方法2:Deque

- 详细参考[基础与技巧](/docs/leetcode/classify/basic_skill.md#Deque的主要使用方式)

```java
        public boolean isValid(String s) {
            Map<Character, Character> dict = new HashMap<Character, Character>() {{
                put(']', '[');
                put(')', '(');
                put('}', '{');
            }};
            Deque<Character> deque = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                //左边括号纷纷入栈
                if (c == '[' || c == '(' || c == '{') deque.addFirst(c);
                //如果找到了右边括号，且符合题意的右边括号，找到栈顶的字符看看是否不是成对的
                //dict.containsKey(c) 去掉也可以跑过
                else if (!deque.isEmpty() && dict.containsKey(c) && dict.get(c) == deque.peekFirst()) {
                    deque.pollFirst();
                } else return false;
            }
            //注意 s="[" 这个case，需要最后返回
            return deque.isEmpty();
        }
```

- 等价写法

```java
public boolean isValid(String s) {
    Map<Character, Character> dict = new HashMap<Character, Character>() {{
        put(']', '[');
        put(')', '(');
        put('}', '{');
    }};
    Deque<Character> deque = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
        //左边括号纷纷入栈
        if (c == '[' || c == '(' || c == '{') deque.push(c);
            //如果找到了右边括号，且符合题意的右边括号，找到栈顶的字符看看是否不是成对的
            //dict.containsKey(c) 去掉也可以跑过
        else if (!deque.isEmpty() && dict.containsKey(c) && dict.get(c) == deque.peek()) {
            deque.poll();
        } else return false;
    }
    //注意 s="[" 这个case，需要最后返回
    return deque.isEmpty();
}
```





## [32. 最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/)

### 方法1:栈

```java
public int longestValidParentheses(String s) {
    //stk存的是上一个不匹配的位置（下标）
    Deque<Integer> stk = new ArrayDeque<>();
    int res = 0;
    //[0...3]之间的长度是4，0也就是3-(-1)=4 -1为0位置往前推一个位置
    stk.push(-1);
    for (int i = 0; i < s.length(); i++) {
        // '('时，往栈里推下标
        if (s.charAt(i) == '(') stk.push(i);
        else {
            //')' 把上一个下标索引弹出 如果栈空了，说明当前的这个下标是上一个不匹配的位置
            int cur = stk.pop();
            if (stk.isEmpty()) stk.push(i);
                //栈不为空，计算当前i与上一个不匹配位置的距离
            else res = Math.max(res, i - stk.peek());
        }
    }
    return res;
}
```

### Follow Up：返回最长长度的下标索引

```java
static class _3rd {
    public static void main(String[] args) {
        _3rd handler = new _3rd();
        String s = ")()())()()(()()";
        handler.longestValidParentheses(s);
        //output:[[1,4],[6,9],[11,14]]
        System.out.println(JSON.toJSONString(resList));
    }


    static List<int[]> resList = new ArrayList<>();


    public int longestValidParentheses(String s) {
        //stk存的是上一个不匹配的位置（下标）
        Deque<Integer> stk = new ArrayDeque<>();
        int res = 0;
        //[0...3]之间的长度是4，0也就是3-(-1)=4 -1为0位置往前推一个位置
        stk.push(-1);
        for (int i = 0; i < s.length(); i++) {
            // '('时，往栈里推下标
            if (s.charAt(i) == '(') stk.push(i);
            else {
                //')' 把上一个下标索引弹出 如果栈空了，说明当前的这个下标是上一个不匹配的位置
                int cur = stk.pop();
                if (stk.isEmpty()) stk.push(i);
                    //栈不为空，计算当前i与上一个不匹配位置的距离
                else {
                    int t = i - stk.peek();
                    if (t >= res) {
                        if (t > res) resList.clear();
                        res = t;
                        resList.add(new int[]{stk.peek() + 1, i});
                    }
                }
            }
        }
        return res;
    }
}
```

### 方法2:DP

```java
public int longestValidParentheses(String s) {
    int N = s.length();
    int[] f = new int[N];
    int res = 0;
    for (int i = 1; i < N; i++) {
        if (s.charAt(i) == ')') {
            if (s.charAt(i - 1) == '(') {
                f[i] = 2;
                if (i - 2 >= 0) f[i] = f[i - 2] + 2;
            } else if (f[i - 1] > 0) {
                if ((i - f[i - 1] - 1) >= 0 && s.charAt(i - f[i - 1] - 1) == '(') {
                    f[i] = f[i - 1] + 2;
                    if ((i - f[i - 1] - 2) >= 0) {
                        f[i] = f[i] + f[i - f[i - 1] - 2];
                    }
                }
            }
        }
        res = Math.max(res, f[i]);
    }
    return res;
}
```

### 方法3:贪心

```java
public int longestValidParentheses(String s) {
    int n = s.length();
    int left = 0, right = 0;
    int res = 0;
    for (int i = 0; i < n; i++) {
        if (s.charAt(i) == '(') left++;
        else right++;
        if (left == right) res = Math.max(res, right * 2);
        else if (right > left) left = right = 0;
    }
    left = right = 0;
    for (int i = n - 1; i >= 0; --i) {
        if (s.charAt(i) == '(') left++;
        else right++;
        if (left == right) res = Math.max(res, left * 2);
        else if (left > right) left = right = 0;
    }
    return res;
}
```

- [最长有效括号-官解](https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/)



## [71. 简化路径](https://leetcode-cn.com/problems/simplify-path/)

```java
public String simplifyPath(String path) {
    // 双端队列
    Deque<String> queue = new LinkedList<>();
    // 分割字符
    String[] res = path.split("/");
    for (int i = 0; i < res.length; i++) {
        String s = res[i];
        // . 和 空 跳过
        if (s.equals(".") || s.equals("")) continue;
            //返回上一级目录
        else if (s.equals("..")) {
            if (!queue.isEmpty()) {
                queue.pollLast();
            }
        } else {//入栈
            queue.offer(s);
        }
    }
    // 拼接
    StringBuilder sb = new StringBuilder("/");
    while (!queue.isEmpty()) {
        sb.append(queue.poll());
        if (!queue.isEmpty()) {
            sb.append("/");
        }
    }
    // 判空
    return sb.toString().equals("") ? "/" : sb.toString();
}
```



## [224. 基本计算器](https://leetcode-cn.com/problems/basic-calculator/)

### 方法1：栈

```java
public int calculate(String s) {
    Deque<Integer> ops = new LinkedList<Integer>();
    ops.push(1);
    int sign = 1;

    int ret = 0;
    int n = s.length();
    int i = 0;
    while (i < n) {
        if (s.charAt(i) == ' ') {
            i++;
        } else if (s.charAt(i) == '+') {
            sign = ops.peek();
            i++;
        } else if (s.charAt(i) == '-') {
            sign = -ops.peek();
            i++;
        } else if (s.charAt(i) == '(') {
            ops.push(sign);
            i++;
        } else if (s.charAt(i) == ')') {
            ops.pop();
            i++;
        } else {
            long num = 0;
            while (i < n && Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
                i++;
            }
            ret += sign * num;
        }
    }
    return ret;
}
```





## [227. 基本计算器 II](https://leetcode-cn.com/problems/basic-calculator-ii/)

```java
public int calculate(String s) {
    Stack<Integer> stk = new Stack<>();
    List<Character> symbols = Arrays.asList('+', '-', '*', '/');
    char[] chas = s.toCharArray();
    int sign = 1, curr = 0;
    char symbol = '+';
    for (int i = 0; i < chas.length; i++) {
        if (Character.isDigit(chas[i])) {
            curr = curr * 10 + chas[i] - '0';
        }
        // System.out.printf("%s\n",chas[i]);
        if (symbols.contains(chas[i]) && chas[i] != ' ' || i == chas.length - 1) {
            switch (symbol) {
                case '+':
                    stk.push(curr);
                    break;
                case '-':
                    stk.push(-curr);
                    break;
                case '*':
                    stk.push(stk.pop() * curr);
                    break;
                case '/':
                    stk.push(stk.pop() / curr);
                    break;
            }
            symbol = chas[i];
            curr = 0;
        }
    }
    int ans = 0;
    while (!stk.isEmpty()) ans += stk.pop();
    return ans;
}
```





## [591. 标签验证器](https://leetcode-cn.com/problems/tag-validator/)

- [链接](https://leetcode-cn.com/problems/tag-validator/solution/by-stormsunshine-q0u2/)

```java
public boolean isValid(String code) {
    //<![CDATA[    ]]>     </
    final int CDATA_START_LENGTH = 9, CDATA_END_LENGTH = 3, END_TAG_LENGTH = 2;
    int len = code.length();
    Deque<String> stk = new ArrayDeque<>();
    int index = 0;
    while (index < len) {
        //case: <A></A><B></B> 该情况下说明还没有遍历完，标签已经找到闭合的
        if (index > 0 && stk.isEmpty()) {
            return false;
        }
        if (index + CDATA_START_LENGTH <= len && code.substring(index, index + CDATA_START_LENGTH).equals("<![CDATA[")) {
            index += CDATA_START_LENGTH;
            while (index + CDATA_END_LENGTH <= len && !code.substring(index, index + CDATA_END_LENGTH).equals("]]>")) {
                index++;
            }
            index += CDATA_END_LENGTH;
            if (index + CDATA_END_LENGTH > len) {
                return false;
            }
        } else if (index + END_TAG_LENGTH <= len && code.substring(index, index + END_TAG_LENGTH).equals("</")) {
            index += END_TAG_LENGTH;
            int start = index;
            while (index < len && code.charAt(index) != '>') {
                index++;
            }
            if (index >= len) return false;
            String endTag = code.substring(start, index);
            if (stk.isEmpty() || !stk.peek().equals(endTag)) return false;
            stk.pop();
            index++;
        } else if (code.charAt(index) == '<') {
            index++;
            int start = index;
            while (index < len && code.charAt(index) != '>') {
                index++;
            }
            //case1: <DIV><></></DIV>
            //case2: <AAAAAAAAAA></AAAAAAAAAA>
            if (index >= len || index == start || index - start > 9) {
                return false;
            }
            for (int i = start; i < index; i++) {
                char c = code.charAt(i);
                if (c < 'A' || c > 'Z') {
                    return false;
                }
            }
            String beginTag = code.substring(start, index);
            stk.push(beginTag);
            index++;
        } else {
            index++;
        }

    }
    return stk.isEmpty();
}
```





## [678. 有效的括号字符串](https://leetcode.cn/problems/valid-parenthesis-string/)

### 方法1：栈

```java
//遇到左括号，直接进栈，记录括号的位置。
//遇到星号，直接进栈，记录星号的位置。
//遇到右括号：
//a: 左括号栈里有元素，直接出栈。
//b: 左括号栈里无元素，*栈里有元素，直接出栈。无元素的话就已经匹配失败了。
public boolean checkValidString(String s) {
    Deque<Integer> left = new ArrayDeque<>();
    Deque<Integer> star = new ArrayDeque<>();
    int n = s.length();
    char[] ch = s.toCharArray();
    for (int i = 0; i < n; i++) {
        if (ch[i] == '(') left.push(i);
        else if (ch[i] == '*') star.push(i);
        else if (ch[i] == ')') {
            if (!left.isEmpty()) left.pop();
            else if (!star.isEmpty()) star.pop();
            else return false;
        }
    }
    while (!left.isEmpty()) {
        int leftPos = left.pop();//左括号的位置
        if (star.isEmpty()) return false;//没有*号，说明不够了
        int starPos = star.pop();//* 的位置
        if (leftPos > starPos) {// * 的位置不应该出现在当前的(的位置的前面
            return false;
        }
    }
    return true;
}
```

### 方法2：DP

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220509090924.png)

```java
public boolean checkValidString(String s) {
    int n = s.length();
    // f[i][j] 表示 考虑前 i个字符（字符下标从 1 开始），能否与 j 个右括号形成合法括号序列。
    boolean[][] f = new boolean[n + 1][n + 1];
    f[0][0] = true;
    for (int i = 1; i <= n; i++) {
        char c = s.charAt(i - 1);
        for (int j = 0; j <= i; j++) {
            if (c == '(') {
                if (j - 1 >= 0) f[i][j] = f[i - 1][j - 1];
            } else if (c == ')') {
                if (j + 1 <= i) f[i][j] = f[i - 1][j + 1];
            } else if (c == '*') {
                f[i][j] = f[i - 1][j];
                if (j - 1 >= 0) f[i][j] |= f[i - 1][j - 1];
                if (j + 1 <= i) f[i][j] |= f[i - 1][j + 1];
            }
        }
    }
    return f[n][0];
}
```





## [682. 棒球比赛](https://leetcode-cn.com/problems/baseball-game/)

### 方法1:栈

- 使用栈的方式求解，Deque的使用技巧参见：[Deque的主要使用方式](https://cnwangzhou.gitbook.io/algorithm/leetcode/basic_skill)

![](/imgs/leetcode/classify/image-20220326094856575.png)

```java
public int calPoints(String[] ops) {
    if (ops == null || ops.length == 0) return -1;
    Deque<Integer> stk = new ArrayDeque<>();
    for (String op : ops) {
        if (op.equals("D")) stk.push(stk.peek() * 2);
        else if (op.equals("C")) stk.poll();
            //遇到+号 弹出前两次
        else if (op.equals("+")) {
            int cur = stk.poll(), prev = stk.poll();
            stk.push(prev);
            stk.push(cur);
            stk.push(prev + cur);
        } else {
            stk.push(Integer.parseInt(op));
        }
    }
    int res = 0;
    while (!stk.isEmpty()) res += stk.poll();
    return res;
}
```

### 方法2:数组

```java
public int calPoints(String[] ops) {
    if (ops == null || ops.length == 0) return -1;
    int[] arr = new int[1005];
    int idx = 0;
    for (int i = 0; i < ops.length; i++, idx++) {
        String op = ops[i];
        if (op.equals("D")) arr[idx] = arr[idx - 1] * 2;
            //往前跳2次，for循环+1 达到向前移动一次的目的
        else if (op.equals("C")) idx -= 2;
        else if (op.equals("+")) arr[idx] = arr[idx - 1] + arr[idx - 2];
        else arr[idx] = Integer.parseInt(op);
    }
    int res = 0;
    //最后如果读到"C"的时候，idx会回退索引，但arr的元素并没有更新
    //参考case:[36", "28", "70", "65", "C", "+", "33", "-46", "84", "C"]
    for (int i = 0; i < idx; i++) res += arr[i];
    return res;
}
```





## [856. 括号的分数](https://leetcode.cn/problems/score-of-parentheses/)



#### 解释

```java
[(]                # 遇到 ( 往栈添加
[(, (]             # 继续添加
[(, 1]             # 遇到 ） 合成一个1
[(, 1, (]          # 遇到 ( 往栈添加
[(, 1, (, (]       # 继续添加
[(, 1, (, 1]       # 遇到 ） 合成一个1
[(, 1, 2]          # 遇到 ） ，结构就是（1）， 所以计算的话是 1 * 2
[6]                # 遇到 ） ，结构是（1，2）， 所以计算的话是 （1 + 2） * 2
```



```java
       public int scoreOfParentheses(String s) {
            Deque<Character> stk = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                //遇到 ( 时向 入栈
                if (c == '(') stk.push(c);
                    //雨大 ) 时 判断栈顶是不是 （
                else if (c == ')') {
                    //栈顶是 （  合并为1
                    if (stk.peek() == '(') {
                        stk.pop();
                        stk.push('1');
                    } else {//栈顶不是 （ 而是数字 累加
                        char b = stk.pop();
                        int t = 0;
                        while (b != '(') {
                            t += b - '0';
                            b = stk.pop();
                        }
                        //注意char 和 int类型的转换
                        stk.push((char) ('0' + 2 * t));
                    }
                }
            }
            int res = 0;
            while (!stk.isEmpty()) res += stk.pop() - '0';
            return res;
        }
```

另

```java
     public int scoreOfParentheses(String s) {
            Deque<Integer> stk = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                //stk中存储Integer 使用-1来标记
                if (c == '(') stk.push(-1);
                else if (c == ')') {
                    if (stk.peek() == -1) {
                        stk.pop();
                        stk.push(1);
                    } else {
                        int t = 0;
                        while (stk.peek() != -1) {
                            t += stk.pop();
                        }
                        stk.push(2 * t);
                    }
                }
            }
            int res = 0;
            while (!stk.isEmpty()) res += stk.pop();
            return res;
        }
```

- 官解，很棒的思路

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220528110823.png)

```java
        public int scoreOfParentheses(String S) {
            Stack<Integer> stack = new Stack();
            stack.push(0); // The score of the current frame
            for (char c : S.toCharArray()) {
                if (c == '(')
                    stack.push(0);
                else {
                    int v = stack.pop();
                    int w = stack.pop();
                    stack.push(w + Math.max(2 * v, 1));
                }
            }
            return stack.pop();
        }
```

- 另

事实上，只有 () 会对字符串 S 贡献实质的分数，其它的括号只会将分数乘二或者将分数累加。因此，我们可以找到每一个 () 对应的深度 x，那么答案就是 2^x 的累加和。

```java
        public int scoreOfParentheses(String S) {
            int res = 0, balance = 0;
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == '(') {
                    balance++;
                } else {
                    balance--;
                    if (S.charAt(i - 1) == '(') {
                        res += 1 << balance;
                    }
                }
            }
            return res;
        }
```



