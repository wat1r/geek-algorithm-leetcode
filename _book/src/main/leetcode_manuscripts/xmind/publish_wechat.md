





## 图解933最近的请求次数(附Deque用法)






> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉，欢评论区提供其他语言的版本**

## Deque的主要使用方式

Deque有三种使用形式：

```java
//普通队列(一端进另一端出):
Queue queue = new LinkedList()或Deque deque = new LinkedList()
//双端队列(两端都可进出)
Deque deque = new LinkedList()
//堆栈
Deque deque = new LinkedList()
```

- Deque是一个线性collection，支持在两端插入和移除元素。名称 deque 是“double ended queue（双端队列）”的缩写，通常读为“deck”。大多数 Deque 实现对于它们能够包含的元素数没有固定限制，但此接口既支持有容量限制的双端队列，也支持没有固定大小限制的双端队列。

- 此接口定义在双端队列两端访问元素的方法。提供插入、移除和检查元素的方法。每种方法都存在两种形式：一种形式在操作失败时抛出异常，另一种形式返回一个特殊值（null 或 false，具体取决于操作）。插入操作的后一种形式是专为使用有容量限制的 Deque 实现设计的；在大多数实现中，插入操作不能失败。
  下面是12种方法：

![](/imgs/leetcode/classify/image-20210831181556668.png)

- Deque接口扩展(继承)了 Queue 接口。在将双端队列用作队列时，将得到 FIFO（先进先出）行为。将元素添加到双端队列的末尾，从双端队列的开头移除元素。从 Queue 接口继承的方法完全等效于 Deque 方法

- 双端队列也可用作 LIFO（后进先出）堆栈。应优先使用此接口而不是遗留 Stack 类。在将双端队列用作堆栈时，元素被推入双端队列的开头并从双端队列开头弹出。堆栈方法完全等效于 Deque 方法

![](/imgs/leetcode/classify/image-20210831182153342.png)

Deque的实现：

- 一般场景
  - LinkedList 大小可变的**链表**双端队列，允许元素为 null
  - ArrayDeque 大小可变的**数组**双端队列，不允许 null
- 并发场景
  - LinkedBlockingDeque 如果队列为空时，获取操作将会阻塞，知道有元素添加

#### Demo示例

```java
    private static void dequeTest() {
        Deque<String> deque = new LinkedList<String>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque);
        //获取栈首元素后，元素不会出栈
        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);
        while (deque.size() > 0) {
            //获取栈首元素后，元素将会出栈
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }
//
[c, b, a]
c
[c, b, a]
c
b
a
[]
  
    private static void dequeTest1() {
        Deque<Integer> deque = new LinkedList<>();
        deque.offer(1);
        deque.offer(2);
        deque.offer(3);
        System.out.println(deque);
        Integer in = deque.peek();
        System.out.println(in);
        System.out.println(deque);
        while (deque.size() > 0) {
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }  
  
//
[1, 2, 3]
1
[1, 2, 3]
1
2
3
[]
```



## 

```java
class RecentCounter {
    Deque<Integer> q;

    public RecentCounter() {
        q = new LinkedList<>();
    }

    public int ping(int t) {
        q.offerLast(t);
        while (q.peek() < t - 3000) q.pollFirst();
        return q.size();
    }
}
```

- 另

```java
class RecentCounter {

    PriorityQueue<Integer> pq;
    int N = 3000;

    public RecentCounter() {
        pq = new PriorityQueue<>();
    }

    public int ping(int t) {

        while (!pq.isEmpty() && pq.peek() < t - N) {
            pq.poll();
        }
        pq.offer(t);
        return pq.size();
    }
}
```





### 更多阅读

- 算法题解的链接地址： [gitbook](https://cnwangzhou.gitbook.io/algorithm/)

- [个人主页【阿飞算法】](https://blog.csdn.net/wat1r/article/details/117533156) 加我好友，进群一起交流~
