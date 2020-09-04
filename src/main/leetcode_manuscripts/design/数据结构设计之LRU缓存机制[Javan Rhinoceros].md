## 数据结构设计之LRU缓存机制[Javan Rhinoceros]

![rhino-3668126_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计Javan Rhinoceros.assets\rhino-3668126_640.jpg)



需要一个哈希双端链表，$DoubleLinkedNode$

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

> 将新加入的节点插入到双端链表的头部位置$addFirst(node)$

![image-20200904085250881](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计Javan Rhinoceros.assets\image-20200904085250881.png)

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

> 移除一个节点$removeNode(node)$

![image-20200904090539191](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\design\数据结构设计Javan Rhinoceros.assets\image-20200904090539191.png)

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

> 弹出最末尾的节点，并返回最后的节点$popLast$

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

> 将一个已经在链表中存在的节点移动到链表的开头$moveToHead(node)$

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

> 下面开始$LRU$

#### 思路

> 初始化

- 注意$head$节点和$tail$节点需要$new$出来

> $get(key)$

- 如果$cache$中不存在$key$,返回-1
- 如果$cache$中存在，取出这个节点，将节点$moveToHead$，返回节点的值

> $put(k,v)$

- 取出节点，分节点存在与否讨论：
  - 节点不存在：新创建节点，将该节点插入到链表的头部，并将其$put$进$cache$中
    - 做一个额外的判断：如果当前的$cache$的大小大于$capacity$，需要移除最末尾的节点，链表和$cache$都要做移除操作
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

