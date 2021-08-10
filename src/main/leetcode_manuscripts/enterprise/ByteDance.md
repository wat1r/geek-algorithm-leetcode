

### [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) -> [滑动窗口，双指针]

```java

public int lengthOfLongestSubstring(String s) {
    int res = 0;
    int i = 0, j = 0, n = s.length();
    Set<Character> set = new HashSet<>();
    while(j<n&&i<n){
        if(!set.contains(s.charAt(j))){
            set.add(s.charAt(j++));
            res =Math.max(res,j-i);
        }else{
            set.remove(s.charAt(i++));
        }
    }
    return res;
}
```



```java
public int lengthOfLongestSubstring(String s) {
    int res = 0, l =0, r = 0, n = s.length();
    int[] arr =new int[128];
    while(r<n){
        l = Math.max(l,arr[s.charAt(r)]);
        res = Math.max(res,r-l+1);
        arr[s.charAt(r)] = 1+r++;
    }
    return res;
}
```



### [215. 数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array)->[优先队列]

```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < nums.length; i++) {
        if (i < k || nums[i] > pq.peek()) pq.offer(nums[i]);
        if (pq.size() > k) pq.poll();
    }
    return pq.peek();
}
```



### [146. LRU 缓存机制](https://leetcode-cn.com/problems/lru-cache/) ->[双向链表，Hash]

```java
class LRUCache {


    int capacity;
    Map<Integer, DoubleLinkedNode> cache;
    DoubleLinkedNode head;
    DoubleLinkedNode tail;


    class DoubleLinkedNode {
        int k, v;
        DoubleLinkedNode prev, next;

        public DoubleLinkedNode(int k, int v) {
            this.k = k;
            this.v = v;
        }

        public DoubleLinkedNode() {
        }
    }


    private void removeNode(DoubleLinkedNode node) {
        DoubleLinkedNode next = node.next;
        DoubleLinkedNode prev = node.prev;
        prev.next = next;
        next.prev = prev;
    }


    private void addFirst(DoubleLinkedNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;

    }


    private DoubleLinkedNode popLast() {
        DoubleLinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }


    private void moveToHead(DoubleLinkedNode node) {
        removeNode(node);
        addFirst(node);

    }


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new DoubleLinkedNode();
        this.tail = new DoubleLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        DoubleLinkedNode node = cache.get(key);
        moveToHead(node);
        return node.v;
    }

    public void put(int key, int value) {
        DoubleLinkedNode node = cache.get(key);
        if (node == null) {
            node = new DoubleLinkedNode(key, value);
            addFirst(node);
            cache.put(key, node);
            if (cache.size() > capacity) {
                DoubleLinkedNode lastNode = popLast();
                cache.remove(lastNode.k);
            }
        } else {
            node.v = value;
            moveToHead(node);
        }
    }
}
```

### [206. 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)->[链表]



```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null, cur = head;
    while(cur!=null){
        ListNode nxt =cur.next;
        cur.next = prev;
        prev = cur;
        cur = nxt; 
    }
    return prev;
}
```