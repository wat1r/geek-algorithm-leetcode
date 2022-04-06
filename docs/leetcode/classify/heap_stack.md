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

