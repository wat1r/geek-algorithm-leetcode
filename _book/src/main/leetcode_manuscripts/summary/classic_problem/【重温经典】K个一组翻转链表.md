![image-20210818204700868](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210818204700868.png)

#### 方法1:迭代

![image-20210818132443176](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210818132443176.png)

- 需要注意是end节点的跳步的判断，可能不足k步，处理掉这种特殊情况

```java
    public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy, end = dummy;
            while (end.next != null) {
                for (int i = 0; i < k && end != null; i++) {
                    end = end.next;
                }
                if (end == null) break;
                ListNode start = prev.next;
                ListNode nxt = end.next;
                end.next = null;
                prev.next = reverse(start);
                start.next = nxt;
                prev = start;
                end = start;
            }
            return dummy.next;
        }

        /**
         * 翻转链表，并返回翻转后的头结点
         *
         * @param head
         * @return
         */
        private ListNode reverse(ListNode head) {
            ListNode prev = null, cur = head;
            while (cur != null) {
                ListNode nxt = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nxt;
            }
            return prev;
        }
```

#### 方法2:递归

![image-20210818204423219](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210818204423219.png)

![image-20210818204319546](/Users/frankcooper/Library/Application Support/typora-user-images/image-20210818204319546.png)

```java
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode tail = head;
    for (int i = 0; i < k; i++) {
        //当数量不足k的时候，返回head节点，不需要翻转了
        if (tail == null) return head;
        tail = tail.next;
    }
    //开始翻转[head,tail)范围内的链表，并返回翻转后的新的节点，也就是tail节点的上一个节点
    ListNode new_head = reverse(head, tail);
    //递归翻转以tail为头节点的如下部分链表
    head.next = reverseKGroup(tail, k);
    return new_head;//返回翻转后的新的头节点
}
//过程建figure.c
private ListNode reverse(ListNode head, ListNode tail) {
    ListNode prev = null, cur = null;
    while (head != tail) {
        cur = head.next;//step1
        head.next = prev;//step2
        prev = head;//step3
        head = cur;//step4
    }
    return prev;
}
```



