1

## 数据结构设计之最大最小栈[Indian Rhinoceros]

 ![pexels-photo](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计之最大最小栈[Indian Rhinoceros].assets\pexels-photo.jpg)

###  1.最小栈

![1589294269253](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计之最大最小栈[Indian Rhinoceros].assets\1589294269253.png)

#### 方法1：辅助栈[浪费空间]

- 基础版辅助栈，准备一个`data`, 数据栈，准备一个`help` 辅助栈

- 其中`data`的存数据的，`help`辅助栈用来存最小值，在`push`操作时，`help`如果栈顶元素大于待`push`的元素，将待`push`的元素塞进`help`中，如果不是，则重复塞一次`help`的栈顶元素，注意`help`为空的时候特殊处理下

```java
  class MinStack {
        private Stack<Integer> data;
        private Stack<Integer> help;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data = new Stack<>();
            help = new Stack<>();
        }

        public void push(int x) {
            data.push(x);
            if (help.isEmpty()) help.push(x);
            else if (help.peek() < x) help.push(help.peek());
            else help.push(x);
        }

        public void pop() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            data.pop();
            help.pop();
        }

        public int top() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            return data.peek();

        }

        public int getMin() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            return help.peek();
        }
    }
```

#### 方法2：辅助栈[不浪费空间]

-  升级版本辅助栈，当`push` 进去的时候，
   - 当`help`的栈顶元素比新来的元素小的时候，这个时候保持`help`不变
   - 当`help`的栈顶元素大于等于新来的元素时，`help`同步要推一份新的元素进来
   - 当`help`元素为空的时候，也需要往里推
- `pop`的时候，`pop`的元素是否和`help` 的元素有重叠，有就将`help`的元素`pop`出去，没有就维持不动，`data`栈正常`pop`

```java
class MinStack {
    Stack<Integer> data;
    Stack<Integer> help;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        data = new Stack<>();
        help = new Stack<>();
    }

    public void push(int x) {
        if (help.isEmpty() || help.peek() >= x) {
            help.push(x);
        }
        data.push(x);

    }

    public void pop() {
        if (data.isEmpty()) throw new RuntimeException("The stack is empty");
        int pop = data.pop();
        if (pop == help.peek()) {
            help.pop();
        }
    }

    public int top() {
        if (data.isEmpty()) throw new RuntimeException("The stack is empty");
        return data.peek();
    }

    public int getMin() {
        if (help.isEmpty()) throw new RuntimeException("The stack is empty");
        return help.peek();
    }
}
```

### 2.最大栈

#### 方法1：辅助栈[不浪费空间]

\- `LC`上做的，想法和最小栈的第二种方法类似

```java
Stack<Integer> data;
Stack<Integer> help;

public MaxStack() {
    // do intialization if necessary
    data = new Stack<>();
    help = new Stack<>();
}

/*
 * @param number: An integer
 * @return: nothing
 */
public void push(int x) {
    // write your code here
    if (help.isEmpty() || help.peek() <= x) {
        help.push(x);
    }
    data.push(x);
}

public int pop() {
    if (data.isEmpty()) throw new RuntimeException("The stack is empty");
    int cur = data.pop();
    if (!help.isEmpty() && cur == help.peek()) help.pop();
    return cur;
}

/*
 * @return: An integer
 */
public int top() {
    if (data.isEmpty()) throw new RuntimeException("The stack is empty");
    return data.peek();
}

/*
 * @return: An integer
 */
public int peekMax() {
    if (help.isEmpty()) throw new RuntimeException("The stack is empty");
    return help.peek();
}

/*
 * @return: An integer
 */
public int popMax() {
    if (help.isEmpty()) throw new RuntimeException("The stack is empty");
    data.pop();
    return help.pop();
}
```

> 本文完

### 关联阅读

