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