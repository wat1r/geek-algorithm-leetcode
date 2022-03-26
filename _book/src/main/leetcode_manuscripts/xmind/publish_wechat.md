





## 图解682棒球比赛






> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉，欢评论区提供其他语言的版本**



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

### 更多阅读

- 算法题解的链接地址： [gitbook](https://cnwangzhou.gitbook.io/algorithm/)

- [个人主页【阿飞算法】](https://blog.csdn.net/wat1r/article/details/117533156) 加我好友，进群一起交流~
