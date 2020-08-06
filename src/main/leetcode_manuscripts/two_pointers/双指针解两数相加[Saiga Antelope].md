## 双指针解两数相加[Saiga Antelope]



![background-2068779_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解两数相加[Tibetan Antelope].assets\background-2068779_640.jpg)



### 1.两数相加I

![image-20200805204018584](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解两数相加[Tibetan Antelope].assets\image-20200805204018584.png)



#### 方法1

- 当$l1$或者$l2$其中有一个不为$null$时，返回不为$null$的$ListNode$
- 准备$carry$变量记录进位的值，$dummy$哑结点，$head$节点指向这个初始化的$dummy$节点
- 当$l1$与$l2$有不为$null$的，不断转：
  - 初始是$sum$=$carry$，$l1$不为$null$时，将其值加到$sum$上去，并将$l1$指向下个节点，同理$l2$
  - 拿到$carry$的值= $sum/10$,
  - 拿到当前留下的值$sum$%$10$，新建一个新节点接到$dummy$节点后面，将$dummy$节点移到下一个节点
- 处理下小尾巴:$carry$不为0时，说明最后还有一个进位，新建一个节点接到$dummy$节点后面
- 返回一开始$head$节点的下一个节点，即为头节点

```java
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            sum %= 10;
            dummy.next = new ListNode(sum);
            dummy = dummy.next;
        }
        if (carry != 0) {
            dummy.next = new ListNode(carry);
        }
        return head.next;
    }
```

#### 方法2

> 另外一种写法

```java
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            head.next = new ListNode(sum % 10);
            head = head.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) {
            head.next = new ListNode(carry);
        }
        return dummy.next;
    }
```



### 2.两数相加II

![image-20200805204156903](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解两数相加[Tibetan Antelope].assets\image-20200805204156903.png)

#### 方法1：栈

- 先将$l1$与$l2$入栈，处理$carry$与$sum$的做法类似于上一题
- 这里说下链表的移动规则，大体是从最后的节点往前走，初始化$head$为$null$，当$curr$指向$head$后，将$head$往前移动一个节点到$curr$，如此往复
- 返回$head$，即新链表的头结点

![image-20200805212236311](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解两数相加[Tibetan Antelope].assets\image-20200805212236311.png)

```java
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int sum = 0;
            int x = s1.isEmpty() ? 0 : s1.pop();
            int y = s2.isEmpty() ? 0 : s2.pop();
            sum = x + y + carry;
            carry = sum / 10;
            ListNode curr = new ListNode(sum % 10);
            curr.next = head;
            head = curr;
        }
        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next = head;
            head = curr;
        }
        return head;
    }
```

### 总结

- 这类题目通常借助的思想是双指针，游走两个链表

- 要学会求进位值$carry$ =$sum/10$ , 与当前的$sum%10$
- 最后收尾的时候注意判断$carry$值



