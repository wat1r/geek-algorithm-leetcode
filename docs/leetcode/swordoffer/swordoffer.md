# 剑指Offer





## [剑指 Offer 11. 旋转数组的最小数字](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

### 方法1.二分

```java
public int minArray(int[] nums) {
    int n = nums.length;
    int l = 0, r = n - 1;
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] < nums[r]) r = mid;
        else if (nums[mid] > nums[r]) l = mid + 1;
        else r--;
    }
    return nums[l];
}
```







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



## [剑指 Offer 51. 数组中的逆序对](https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/)

```java
public int reversePairs(int[] arr) {
    if (arr == null || arr.length < 2) return 0;
    return mergeSortProcess(arr, 0, arr.length - 1);
}


private int mergeSortProcess(int[] arr, int left, int right) {
    if (left == right) return 0;//递归的出口条件
    int mid = (right + left) / 2;//取mid，二分中的mid取值有很多讲究
    int leftCnt = mergeSortProcess(arr, left, mid);//左半部分
    int rightCnt = mergeSortProcess(arr, mid + 1, right);//右半部分
    int brigeCnt = merge(arr, left, mid, right);//对每个部分进行merge
    return leftCnt + rightCnt + brigeCnt;
}

private int merge(int[] arr, int left, int mid, int right) {
    int[] help = new int[right - left + 1];//辅助数组，[left,right]
    int count = 0;
    //p1表示从[left,mid]的指针
    //p2表示从[mid+1,righ1]的指针
    //i表示辅助数组help的指针
    //套在两个while循环外的，当p1,p2均为到达边界的时候，谁小取谁，进辅助数组help
    int p1 = left, p2 = mid + 1, i = 0;
    while (p1 <= mid && p2 <= right) {
        count += arr[p1] <= arr[p2] ? (p2 - (mid + 1)) : 0;
        help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    //下面的两个while只会发生一个，将剩余的部分移进辅助数组help
    while (p1 <= mid) {
        count += p2 - (mid + 1);
        help[i++] = arr[p1++];
    }
    while (p2 <= right) {
        help[i++] = arr[p2++];
    }
    //排序好的 辅助数组反向拷贝进原数组arr，注意下标
    for (int k = 0; k < help.length; k++) {
        arr[left + k] = help[k];
    }
    return count;
}
```







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

