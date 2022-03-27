## 基础

> 

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







##  Arrays.BinarySearch

### binarySearch(Object[] a, Object key)

- a: 要搜索的数组

- key：要搜索的值

- 如果key在数组中，则返回搜索值的索引；否则返回-1或“-”（插入点）。插入点是索引键将要插入数组的那一点，即第一个大于该键的元素的索引。

这里涉及到搜索值的几种情况：

- 1.搜索值是数组元素，从0开始计数，得搜索值的索引值
- 2.搜索值不是数组元素，且小于数组内元素，索引值为 – 1
- 3.搜索值不是数组元素，且在数组范围内，从1开始计数，得“ - 插入点索引值”
- 4.搜索值不是数组元素，且大于数组内元素，索引值为 – (length + 1)

**举例**

```java
@Test
public void testArraysBinarySearch() {
    int[] arr = new int[]{3, 5, 7, 9, 11, 13};
    Arrays.sort(arr);
    for (int i = 0; i < 17; i++) {
        System.out.println("数字【" + i + "】：" + Arrays.binarySearch(arr, i));
    }
}
```

**输出**

```java

数字【0】：-1
数字【1】：-1
数字【2】：-1
数字【3】：0
数字【4】：-2
数字【5】：1
数字【6】：-3
数字【7】：2
数字【8】：-4
数字【9】：3
数字【10】：-5
数字【11】：4
数字【12】：-6
数字【13】：5
数字【14】：-7
数字【15】：-7
数字【16】：-7
```

### binarySearch(Object[] a, int fromIndex, int toIndex, Object key)

- a：要搜索的数组

- fromIndex：指定范围的开始处索引（包含）

- toIndex：指定范围的结束处索引（不包含）

- key：要搜索的值

- 如果要搜索的元素key在指定的范围内，则返回搜索值的索引；否则返回-1或“-”（插入点）。

这里涉及到搜索值的几种情况：

- 1.该搜索键在范围内，且是数组元素，由0开始计数，得搜索值的索引值
- 2.该搜索键不在范围内，且小于范围（数组）内元素，返回–(fromIndex + 1)
- 3.该搜索键在范围内，但不是数组元素，由1开始计数，得“ - 插入点索引值”

- 4.该搜索键不在范围内，且大于范围（数组）内元素，返回 –(toIndex + 1)

**举例**

```java
@Test
public void testArraysBinarySearchIndex() {
    int[] arr = new int[]{3, 5, 7, 9, 11, 13};
    Arrays.sort(arr);
    for (int i = 0; i < 17; i++) {
        System.out.println("数字【" + i + "】：" + Arrays.binarySearch(arr, 1, 4, i));
    }
}
```

**输出**

```java
数字【0】：-2
数字【1】：-2
数字【2】：-2
数字【3】：-2
数字【4】：-2
数字【5】：1
数字【6】：-3
数字【7】：2
数字【8】：-4
数字【9】：3
数字【10】：-5
数字【11】：-5
数字【12】：-5
数字【13】：-5
数字【14】：-5
数字【15】：-5
数字【16】：-5
```

