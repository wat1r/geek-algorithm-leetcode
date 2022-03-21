## Next Greater Element(下一个较大元素)

给定一个数组，为每个元素打印下一个较大元素（NGE）。元素x的下一个较大元素是数组中x右侧的第一个较大元素。没有更大元素的元素，考虑下一个更大的元素为-1。

### Examples:
- 1.对于数组，最右边的元素始终具有下一个较大的元素-1。
- 2.对于按降序排序的数组，所有元素的下一个较大元素为-1。
- 3.对于输入数组[4,5,2,25]，每个元素的下一个较大元素如下所示。

```java
Element       NGE
   4      -->   5
   5      -->   25
   2      -->   25
   25     -->   -1
```

- 4.对于输入数组[13,7,6,12]，每个元素的下一个较大元素如下所示。

```java
 Element        NGE
   13      -->    -1
   7       -->     12
   6       -->     12
   12      -->     -1
```

### 方法1（简单）:

使用两个循环：外部循环逐个遍历所有元素。内部循环为外部循环拾取的元素查找第一个较大的元素。如果找到较大的元素，则该元素将作为下一个元素打印，否则将打印-1。

#### Code:

```java
 static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int arr[] = {11, 13, 21, 3};
            int n = arr.length;
            handler.printNGE(arr, n);

        }

        public void printNGE(int arr[], int n) {
            int next, i, j;
            for (i = 0; i < n; i++) {
                next = -1;
                for (j = i + 1; j < n; j++) {
                    if (arr[i] < arr[j]) {
                        next = arr[j];
                        break;
                    }
                }
                System.out.println(arr[i] + " -- " + next);
            }
        }
    }
/**
11 -- 13
13 -- 21
21 -- -1
3 -- -1
**/
```


### 方法2（使用栈）

- 将第一个元素推送到栈。
- 逐个遍历其余元素，并在循环中遵循以下步骤：
  - 1.将当前元素标记为next。
  - 2.若栈不为空，则将栈的顶部元素与next进行比较。
  - 3.如果next大于top元素，则从栈中弹出元素。next是弹出元素的下一个较大元素。
  - 4.当弹出的元素小于next时，保持从栈弹出。next将成为所有此类弹出元素的下一个较大元素。
  - 最后，推入栈中的next。
  - 步骤2中的循环结束后，从栈中弹出所有元素并打印-1作为它们的下一个元素。
    ![image-20211027193501918](/Users/frankcooper/Library/Application Support/typora-user-images/image-20211027193501918.png)

#### Code:

```java
 static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            int arr[] = {11, 13, 21, 3};
            arr = new int[]{4, 5, 2, 25};
            handler.printNGE(arr);
        }


        public void printNGE(int[] arr) {
            Stack<Integer> stk = new Stack<>();
            int ele, next;
            //第一个元素进栈
            stk.push(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                next = arr[i];
                if (!stk.isEmpty()) {
                    //栈不为空，弹出栈顶元素赋值给ele
                    ele = stk.pop();
                    //如果栈顶元素比next小，打印ele->next 不断弹出栈顶元素，直到栈空
                    while (ele < next) {
                        System.out.printf("%d ---> %d \n", ele, next);
                        if (stk.isEmpty()) break;
                        ele = stk.pop();
                    }
                    //如果当前元素大于next,将当前元素入栈
                    if (ele > next) {
                        stk.push(ele);
                    }
                }
                stk.push(next);
            }
            while (!stk.isEmpty()) {
                ele = stk.pop();
                next = -1;
                System.out.printf("%d ---> %d \n", ele, next);
            }
        }
    }

//4 ---> 5 
//2 ---> 25 
//5 ---> 25 
//25 ---> -1 
```

#### 复杂度分析：

- **时间复杂度**：O(N)
- **空间复杂度**：O(N)

最坏的情况发生在所有元素按降序排序时。如果元素按降序排序，则每个元素最多处理4次。

- 1.初始时被推到栈中。

- 2.在处理next时从栈中弹出。
- 3.因为next更小，所以被推回栈。
- 4.在算法的步骤3中从堆栈中弹出。

**如何以与输入相同的顺序获取元素？**

上述方法可能不会以与输入相同的顺序生成输出元素。为了达到相同的顺序，我们可以按相反的顺序遍历相同的对象

#### Code:

```java
 static class _1st_2 {
        public static void main(String[] args) {
            _1st_2 handler = new _1st_2();
            int[] arr = new int[]{11, 13, 21, 3};
            handler.printNGE(arr);
        }

        public void printNGE(int[] arr) {
            Stack<Integer> stk = new Stack<>();
            int[] res = new int[arr.length];
            for (int i = arr.length - 1; i >= 0; --i) {
                if (!stk.isEmpty()) {
                    while (!stk.isEmpty() && stk.peek() <= arr[i]) {
                        stk.pop();
                    }
                }
                res[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(arr[i]);
            }
            for (int i = 0; i < res.length; i++) {
                System.out.printf("%d ---> %d \n", arr[i], res[i]);
            }

        }
    }
```

#### 复杂度分析：

- **时间复杂度**：O(N)
- **空间复杂度**：O(N)





### [496. 下一个更大元素 I](https://leetcode-cn.com/problems/next-greater-element-i/)

#### 方法1:栈(Stack)

```java
   static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};
            handler.nextGreaterElement(nums1, nums2);

        }


        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length];
            Stack<Integer> stk = new Stack<>();
            //k:nums2的当前元素，v:当前元素的next greater element
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums2) {
                while (!stk.isEmpty() && stk.peek() < x) {
                    map.put(stk.pop(), x);
                }
                stk.push(x);
            }
            //如果当前元素没有NGE，返回-1
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.getOrDefault(nums1[i], -1);
            }
            return res;
        }
    }
```

#### 方法2：栈(Deque)

### 基础

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

![image-20210831181556668](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210831181556668.png)

- Deque接口扩展(继承)了 Queue 接口。在将双端队列用作队列时，将得到 FIFO（先进先出）行为。将元素添加到双端队列的末尾，从双端队列的开头移除元素。从 Queue 接口继承的方法完全等效于 Deque 方法

- 双端队列也可用作 LIFO（后进先出）堆栈。应优先使用此接口而不是遗留 Stack 类。在将双端队列用作堆栈时，元素被推入双端队列的开头并从双端队列开头弹出。堆栈方法完全等效于 Deque 方法

![image-20210831182153342](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210831182153342.png)

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



```java
public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length];
            Deque<Integer> stk = new ArrayDeque<>();
            //k:nums2的当前元素，v:当前元素的next greater element
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : nums2) {
                while (!stk.isEmpty() && stk.peekLast() < x) {
                    map.put(stk.pollLast(), x);
                }
                stk.addLast(x);
            }
            //如果当前元素没有NGE，返回-1
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.getOrDefault(nums1[i], -1);
            }
            return res;
        }
```

#### 方法3：栈(Deque) 倒序

- 倒序，存储res结果

```java
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length];
            Deque<Integer> deque = new ArrayDeque<>();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = nums2.length - 1; i >= 0; --i) {
                int t = nums2[i];
                while (!deque.isEmpty() && deque.peekLast() <= t) {
                    deque.pollLast();
                }
                map.put(t, deque.isEmpty() ? -1 : deque.peekLast());
                deque.offerLast(t);
            }
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.get(nums1[i]);
            }
            return res;
        }
```



### [503. 下一个更大元素 II](https://leetcode-cn.com/problems/next-greater-element-ii/)

#### 方法1：栈

```java
       public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Arrays.fill(res, -1);
            Stack<Integer> stk = new Stack<>();
            for (int i = 0; i < 2 * n; i++) {
                int ele = nums[i % n];
                System.out.printf("%d-->%d\n", i, ele);
                while (!stk.isEmpty() && nums[stk.peek()] < ele) {
                    res[stk.pop()] = ele;
                }
                stk.push(i % n);
            }
            return res;
        }
```


### Reference

- [Next Greater Element](https://www.geeksforgeeks.org/next-greater-element/)

