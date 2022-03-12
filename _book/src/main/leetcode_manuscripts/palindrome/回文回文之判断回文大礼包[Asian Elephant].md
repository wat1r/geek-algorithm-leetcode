

## 回文回文之判断回文大礼包[Asian Elephant]

> 这是回文回文系列文章的第一篇，当餐前甜点吧



### 1.验证回文串

![image-20200519194150274](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\palindrome\回文回文之判断回文大礼包[Asian Elephant].assets\image-20200519194150274.png)

- 判断一个字符串是否是回文：
  - 利用左右指针，分别从左到右扫，不相等，返回$false$,都扫完后，$true$

#### 核心方法(判断一个char[]是否是回文)

- 第二题会用到这段代码

```java
    private boolean validate(char[] chas, int l, int r) {
        while (l < r) {
            if (chas[l++] != chas[r--]) return false
        }
        return true;
    }
```

#### 整体代码

```java
    public boolean isPalindrome(String s) {
        char[] chas = s.toLowerCase().toCharArray();
        StringBuffer ctrlStr = new StringBuffer();
        for (int i = 0; i < chas.length; i++) {
            ctrlStr.append(onlyNumAndAlphabet(chas[i]));
        }
        return validate(ctrlStr.toString().toCharArray(), 0, ctrlStr.toString().length() - 1);
    }

    /**
     * 只去字符和数字
     * @param cha
     * @return
     */
    public String onlyNumAndAlphabet(char cha) {
        StringBuffer sb = new StringBuffer();
        if ((cha >= '0' && cha <= '9') || (cha >= 'a' && cha <= 'z')) {
            sb.append(cha);
        }
        return sb.toString();
    }

    private boolean validate(char[] chas, int l, int r) {
        while (l < r) {
            if (chas[l++] != chas[r--]) return false;
        }
        return true;
    }

```

### 2.验证回文字符串 Ⅱ

![image-20200519195201166](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\palindrome\回文回文之判断回文大礼包[Asian Elephant].assets\image-20200519195201166.png)

#### 题目分析

- 给一次机会删除某个字符，再判断是否是回文，下面的两种情况对应代码中的两处

![image-20200519195951250](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\palindrome\回文回文之判断回文大礼包[Asian Elephant].assets\image-20200519195951250.png)

- 只需要当发现$chas[left]!=chas[right]$时，跳过$left$ 或者$right$

```java
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chas = s.toCharArray();
        int l = 0, r = chas.length-1;
        while (l < r) {
            if (chas[l] != chas[r]) {
                return validate(chas, l + 1, r) || validate(chas, l, r - 1);
            }
            l++;
            r--;
        }
        return true;
    }
```

### 3.回文链表

![image-20200519200611421](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\palindrome\回文回文之判断回文大礼包[Asian Elephant].assets\image-20200519200611421.png)

#### 方法1:栈

- 先将链表的元素$push$进栈，然后遍历链表，边遍历边弹出栈顶元素

```java
 public boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
```

#### 方法2:

![img](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\palindrome\回文回文之判断回文大礼包[Asian Elephant].assets\clipboard.png)

- 先输入一个字符串，将其构成单链表。之后，可先定位到链表的中间位置，再将链表的后半段逆置。之后使用两个指针同时从链表头部和中间开始逐一向后遍历比较。
- 借助了快慢指针找到中间的元素
- 翻转链表用的是比较朴素的做法，还有种递归翻转链表的做法

```java
public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
        return true;
    }
    ListNode slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    ListNode mid = slow.next;
    slow.next = null;
    ListNode right = reverseList(mid);
    ListNode left = head;
    while (left != null && right != null) {
        if (left.val != right.val) {
            return false;
        }
        left = left.next;
        right = right.next;
    }
    return true;
}


/**
 * 返回翻转后的链表的最开始的node
 *
 * @param head
 * @return
 */
private ListNode reverseList(ListNode head) {
    ListNode current = head, next, pre = null;
    while (current != null) {
        // 记录后继结点
        next = current.next;
        // 后继指针逆向
        current.next = pre;
        // 记录当前结点
        pre = current;
        // 下一结点成为当前结点
        current = next;
    }
    return pre;
}
```

#### 复杂度分析

- 时间复杂度为$O(n)$
- 空间复杂度为$O(1)$

### 4.回文栈

> 判断一个栈是不是回文

- 根据栈的特性，可以将字符串全部压入栈，再依次将各个字符出栈，从而得到原字符串的逆置串，将逆置串中的各个字符分别和原字符串中各个字符进行比较，如果完全一致，则为回文串。

```java
public boolean isPalindrome(String str) {
    if (str == null) {
        return true;
    }
    char[] chas = str.toCharArray();
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < chas.length; i++) {
        stack.push(chas[i]);
    }
    for (int i = 0; i < chas.length; i++) {
        if (chas[i] != stack.pop()) {
            return false;
        }
    }
    return true;
}
```



> 本文完

### 关联阅读

