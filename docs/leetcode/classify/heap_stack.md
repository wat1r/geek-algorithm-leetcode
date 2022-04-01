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

