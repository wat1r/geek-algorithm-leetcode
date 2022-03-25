# 剑指Offer







## [剑指 Offer 40. 最小的k个数](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)

### 方法1:优先队列

```java
public int[] getLeastNumbers(int[] arr, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    for (int x : arr) {
        pq.offer(x);
        if (!pq.isEmpty() && (pq.size() > k)) {
            pq.poll();
        }
    }
    int[] res = new int[k];
    for (int i = 0; i < k; i++) res[i] = pq.poll();
    return res;
}
```

### 方法2:排序

[剑指 Offer 40. 最小的 k 个数（基于快速排序的数组划分，清晰图解）](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/jian-zhi-offer-40-zui-xiao-de-k-ge-shu-j-9yze/)





## [剑指 Offer 62. 圆圈中最后剩下的数字 LCOF](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/)

### 扩展

![](/imgs/leetcode/swordoffer/image-20220313153132102.png)

- 使用链表的方式模拟删除的操作

```java
        public static void main(String[] args) {
            _2nd handler = new _2nd();
            ListNode head = ListNodeIOUtils.transform("[0,1,2,3,4]");
            ListNode tail = head.next.next.next.next;
            tail.next = head;
            int m = 3;
            handler.delNodeInCircle(head, m);

        }


        private int delNodeInCircle(ListNode head, int m) {
            int idx = 0;//计数索引
            //如果只剩下一个节点，因为节点是环状的，所以自己指向自己即可退出
            while (head.next != head) {
                if (idx == m - 2) {//m表示个数 idx是索引，差2个
                    head.next = head.next.next;//删除要删除的节点
                    idx = 0;
                } else {
                    idx++;//计数
                }
                //跳到下一个节点
                head = head.next;
            }

            return head.val;
        }
```

- 如果每次旋转的圈数很大，可以mod掉

```java
        private int delNodeInCircle(ListNode head, int m, int size) {
            int idx = 0;//计数索引
            //如果只剩下一个节点，因为节点是环状的，所以自己指向自己即可退出
            while (head.next != head) {
                if (idx == (m - 2) % size) {//m表示个数 idx是索引，差2个
                    head.next = head.next.next;//删除要删除的节点
                    idx = 0;
                    size--;
                } else {
                    idx++;//计数
                }
                //跳到下一个节点
                head = head.next;
            }
            return head.val;
        }
```

- 按递推公式来处理

```java
public int lastRemaining(int n, int m) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; i++) list.add(i);
    int idx = 0;
    while (list.size() > 1) {
        idx = (idx + m - 1) % list.size();
        list.remove(idx);
    }
    return list.get(0);
}
```

