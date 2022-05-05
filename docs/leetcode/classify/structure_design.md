# 结构设计









## [232. 用栈实现队列](https://leetcode-cn.com/problems/implement-queue-using-stacks/)

- 栈：先进后出`FILO`
- 队列：先进先出`FIFO`
- 准备两个栈，一个数据栈`data`,一个辅助栈`help`
  - `push`时，只需要向`help`栈中推
  - `pop`时，只要去`data`的栈顶元素，即`data.pop()`,但是`data`栈没有元素了需要怎样?将`help`栈的元素不断往`data`栈推，推完为止，如果`data`,`help`栈均无元素，报错
  - `peek`时，与`pop`的过程一样，只是在返回的时候，`data.peek()`
  - `empty`，当`data`,`help`均为空时，返回`true`

```java
 class MyQueue {
        Stack<Integer> data;
        Stack<Integer> help;
 
        public MyQueue() {
            data = new Stack<>();
            help = new Stack<>();
        }

        public void push(int x) {
            help.push(x);
        }

        public int pop() {
            if (data.isEmpty() && help.isEmpty()) throw new RuntimeException("empty queue");
            if (data.isEmpty()) {
                while (!help.isEmpty()) data.push(help.pop());
            }
            return data.pop();
        }

        public int peek() {
            if (data.isEmpty() && help.isEmpty()) throw new RuntimeException("empty queue");
            if (data.isEmpty()) {
                while (!help.isEmpty()) data.push(help.pop());
            }
            return data.peek();
        }

        public boolean empty() {
            return data.isEmpty() && help.isEmpty();
        }
    }
```



## [剑指 Offer 09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

### 方法1:Stack

- 参考「232. 用栈实现队列」

```java
class CQueue {

    Stack<Integer> data;
    Stack<Integer> help;

    public CQueue() {
        data = new Stack<>();
        help =new Stack<>();
    }
    
    public void appendTail(int value) {
        help.push(value);
    }
    
    public int deleteHead() {
      	//两个栈的数据都为空，返回-1
        if(data.isEmpty() && help.isEmpty()) return -1;
      	//data的数据没有了从help中拿
        if(data.isEmpty()){
            while(!help.isEmpty()) data.push(help.pop());
        }
      	//弹出data的栈顶元素
        return data.pop();
    }
}
```

### 方法2:Deque

- 注释的代码写法是等价的，详细参考[基础与技巧](/docs/leetcode/classify/basic_skill.md#Deque的主要使用方式)

```java
        class CQueue {

            Deque<Integer> data;
            Deque<Integer> help;

            public CQueue() {
                data = new ArrayDeque<>();
                help = new ArrayDeque<>();
            }

            public void appendTail(int value) {
                help.addFirst(value);
//                help.push(value);
            }

            public int deleteHead() {
                if (data.isEmpty() && help.isEmpty()) return -1;
                if (data.isEmpty()) {
                    while (!help.isEmpty()) {
//                        data.addFirst(help.pollFirst());
                        data.push(help.pop());
                    }
                }
//                return data.pollFirst();
                return data.pop();
            }
        }
```





## [225. 用队列实现栈](https://leetcode-cn.com/problems/implement-stack-using-queues/)

### 方法1:两个队列

- 队列是先进先出，栈是先进后出，在`top()`方法中，为了避免，数据拷贝，有一个`swap()`的动作，`data`与`help`的存储顺序是一样的
- 因为`data`队列的元素始终存在，判断栈是否为空的时候，只需要关注`size(data)==0`
- `top()`与`pop()`方法只是在弹出`data`的最后一个元素的时候是否要继续放回`help`队列

```java
 class MyStack {
        private Queue<Integer> data;
        private Queue<Integer> help;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            data = new LinkedList<>();
            help = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            data.add(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            while (data.size() != 1) help.add(data.poll());
            int res = data.poll();
            swap();
            return res;
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            while (data.size() != 1) help.add(data.poll());
            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }


        private void swap() {
            Queue tmp = help;
            help = data;
            data = tmp;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return data.size() == 0;
        }
    }
```

### 方法2:一个队列

- `shift()`方法是为`top()` 与`pop()`方法准备的，做一件事：就是弹出队头到倒数第`2`个队尾元素的，将其送到队列的尾部，在执行`top()` 与`pop()`，弹出`queue`的队头元素，如果是`top()`，继续保留这个元素，`pop()`时扔掉

```java
class MyStack {

    private Queue<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        queue = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queue.offer(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        shift();
        int res = queue.poll();
        return res;
    }

    /**
     * Get the top element.
     */
    public int top() {
        shift();
        int res = queue.poll();
        queue.offer(res);
        return res;
    }


    public void shift() {
        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            queue.offer(queue.poll());
        }

    }


    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}
```







## [155. 最小栈](https://leetcode-cn.com/problems/min-stack/)

### 方法1：辅助栈[浪费空间]

- 基础版辅助栈，准备一个`data`, 数据栈，准备一个`help` 辅助栈

- 其中`data`的存数据的，`help`辅助栈用来存最小值，在`push`操作时，`help`如果栈顶元素大于待`push`的元素，将待`push`的元素塞进`help`中，如果不是，则重复塞一次`help`的栈顶元素，注意`help`为空的时候特殊处理下

![](/imgs/leetcode/classify/image-20210901194728655.png)

- 准备两个栈，data和help，做push操作时，需要保持help栈顶的元素始终最小，data的数据正常推入，help栈顶维持最小，在执行getMin方法的时候，返回help的栈顶元素

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
-  `pop`的时候，`pop`的元素是否和`help` 的元素有重叠，有就将`help`的元素`pop`出去，没有就维持不动，`data`栈正常`pop`

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









## [307. 区域和检索 - 数组可修改](https://leetcode-cn.com/problems/range-sum-query-mutable/)

### 方法1:二分

```java
class NumArray {

    class TreeNode {
        int val;
        int start;
        int end;
        TreeNode left;
        TreeNode right;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    TreeNode root = null;


    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        TreeNode curr = new TreeNode(start, end);
        if (start == end) curr.val = nums[start];
        else {
            int mid = start + (end - start) / 2;
            curr.left = buildTree(nums, start, mid);
            curr.right = buildTree(nums, mid + 1, end);
            curr.val = curr.left.val + curr.right.val;
        }
        return curr;
    }


    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    public void update(int i, int val) {
        updateTree(root, i, val);
    }

    public void updateTree(TreeNode node, int i, int val) {
        if (node.start == node.end) {
            node.val = val;
        } else {
            int mid = node.start + (node.end - node.start) / 2;
            if (i <= mid) updateTree(node.left, i, val);
            else updateTree(node.right, i, val);
            node.val = node.left.val + node.right.val;
        }
    }

    public int sumRange(int i, int j) {
        return queryTree(root, i, j);
    }

    public int queryTree(TreeNode node, int i, int j) {
        System.out.println(String.format("i:%d,j:%d", i, j));
        System.out.println(node);
        System.out.println(String.format("Node.val:%d", node.val));
        if (node.start == i && node.end == j) return node.val;
        else {
            int mid = node.start + (node.end - node.start) / 2;
            if (j <= mid) {
                return queryTree(node.left, i, j);
            } else if (i >= mid + 1) {
                return queryTree(node.right, i, j);
            } else {
                return queryTree(node.left, i, mid) + queryTree(node.right, mid + 1, j);
            }
        }
    }
}
```

### 方法2:线段树





## [380. O(1) 时间插入、删除和获取随机元素](https://leetcode-cn.com/problems/insert-delete-getrandom-o1/)

```java
class RandomizedSet {

    Map<Integer, Integer> map = new HashMap<>();//k:插入的值，v:list的size
    List<Integer> list = new ArrayList<>();
    Random random = new Random();

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {

    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int idx = map.get(val);//找到val的索引
        int lastEle = list.get(list.size() - 1);//list中的最后一个元素
        list.set(idx, lastEle);//当前的val用lastEle替换
        map.put(lastEle, idx);//更新关系
        //移除元素
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int ranIdx = random.nextInt(list.size());
        return list.get(ranIdx);
    }
}
```



## [146. LRU 缓存](https://leetcode-cn.com/problems/lru-cache/)

需要一个哈希双端链表，`DoubleLinkedNode`

>  这个双端链表有下面的几个属性

```java
        class DoubleLinkedNode {
            int key, value;//k,v
            DoubleLinkedNode pre, next;//前驱接节点，后继节点

            public DoubleLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public DoubleLinkedNode() {
            }
        }
```

> 将新加入的节点插入到双端链表的头部位置`addFirst(node)`

![image-20200904085250881.png](https://img-blog.csdnimg.cn/img_convert/595178f5084777224efe8b2963abcdde.png)

```java
        /**
         * 将节点加入到双向链表的开头的位置
         */
        public void addFirst(DoubleLinkedNode node) {
            node.pre = head;//1.当前节点的前驱结点指向head节点
            node.next = head.next;//2.当前节点的后继节点指向head的后继节点

            head.next.pre = node;//3.head节点的后继节点的前驱结点指向当前节点
            head.next = node;//4.head的后继节点指向当前节点
        }

```

> 移除一个节点`removeNode(node)`

![image-20200904090539191.png](https://img-blog.csdnimg.cn/img_convert/eb1d65b49848509f04e1768eddc0112a.png)


```java
        /**
         * 移除一个普通的节点
         *
         * @param node
         */
        public void removeNode(DoubleLinkedNode node) {
            DoubleLinkedNode next = node.next;
            DoubleLinkedNode pre = node.pre;
            pre.next = next;
            next.pre = pre;
        }
```

> 弹出最末尾的节点，并返回最后的节点`popLast`

```java
        /**
         * 弹出最末尾的节点并将改节点返回
         *
         * @return
         */
        public DoubleLinkedNode popLast() {
            DoubleLinkedNode last = tail.pre;
            removeNode(last);
            return last;
        }
```

> 将一个已经在链表中存在的节点移动到链表的开头`moveToHead(node)`

- 先移除这个节点移除，再将这个节点添加到链表的开头

```java
        /**
         * 将当前节点移动到最头部位置
         *
         * @param node
         */
        public void moveToHead(DoubleLinkedNode node) {
            removeNode(node);
            addFirst(node);
        }
```

> 下面开始`LRU`

#### 思路

> 初始化

- 注意`head`节点和`tail`节点需要`new`出来

> `get(key)`

- 如果`cache`中不存在`key`,返回-1
- 如果`cache`中存在，取出这个节点，将节点`moveToHead`，返回节点的值

> `put(k,v)`

- 取出节点，分节点存在与否讨论：
  - 节点不存在：新创建节点，将该节点插入到链表的头部，并将其`put`进`cache`中
    - 做一个额外的判断：如果当前的`cache`的大小大于`capacity`，需要移除最末尾的节点，链表和$cache$都要做移除操作
  - 节点存在:返回节点的值，将节点移动到链表头部

```java
    class LRUCache {
        DoubleLinkedNode head, tail;//Node的收尾节点
        int capacity;//容量
        Map<Integer, DoubleLinkedNode> cache;//<k,v> k是key，v是key生成的node

        /**
         * 初始化
         *
         * @param capacity
         */
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.head = new DoubleLinkedNode();
            this.tail = new DoubleLinkedNode();
            this.head.next = tail;
            this.tail.pre = head;

        }

        public int get(int key) {
            if (!cache.containsKey(key)) return -1;
            DoubleLinkedNode node = cache.get(key);
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DoubleLinkedNode node = cache.get(key);
            if (node == null) {
                node = new DoubleLinkedNode(key, value);
                addFirst(node);
                cache.put(key, node);
                if (cache.size() > capacity) {
                    DoubleLinkedNode last = popLast();
                    cache.remove(last.key);
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }
    }
```





## [933. 最近的请求次数](https://leetcode-cn.com/problems/number-of-recent-calls/)

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





## [2043. 简易银行系统](https://leetcode-cn.com/problems/simple-bank-system/)

### 方法1:模拟

```java
class Bank {

    long[] balance;
    int capacity;

    public Bank(long[] balance) {
        this.balance = new long[balance.length + 1];
        this.capacity = balance.length + 1;
        for (int i = 0; i < balance.length; i++) {
            this.balance[i + 1] = balance[i];
        }
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > capacity || account2 > capacity) return false;
        if (this.balance[account1] < money) return false;
        this.balance[account1] -= money;
        this.balance[account2] += money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if (account > capacity) return false;
        this.balance[account] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account > capacity) return false;
        if (this.balance[account] < money) return false;
        this.balance[account] -= money;
        return true;
    }
}
```
