

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

## [25. K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)



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

### [103. 二叉树的锯齿形层序遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)->[BFS]

```java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int level = 0;
    while (!queue.isEmpty()) {
        List<Integer> levelList = new ArrayList<>();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            TreeNode cur = queue.poll();
            if (level % 2 == 0) levelList.add(cur.val);
            else levelList.add(0, cur.val);
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }
        level++;
        result.add(levelList);
    }
    return result;
}
```



### [15. 三数之和](https://leetcode-cn.com/problems/3sum/)->[三指针]

```java
     public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            int n = nums.length;
            Arrays.sort(nums);
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0) break;
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int l = i + 1, r = n - 1;
                while (l < r) {
                    int t = nums[i] + nums[l] + nums[r];
                    if (t == 0) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (t > 0) {
                        r--;
                    } else if (t < 0) {
                        l++;
                    }
                }
            }
            return res;
        }
```



### [121. 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)->[DP]

```java
     public int maxProfit(int[] prices) {

            //f[i][0]表示第i天手里没有股票获得的最大利润
            //f[i][1]表示第i天手里有股票获得的最大利润

            int n = prices.length;
            int[][] f = new int[n][2];
            f[0][0] = 0;
            f[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
                f[i][1] = Math.max(f[i - 1][1], -prices[i]);
//                System.out.printf("i：%d, %d,%d\n", i, f[i][0], f[i][1]);
            }
            return f[n - 1][0];
        }
```





### [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)->[Hash]

```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer,Integer> map = new HashMap<>();
    for(int i =0;i<nums.length;i++){
       int t = target - nums[i];
        if(map.containsKey(t)){
            return new int[] {map.get(t),i};
        }
        map.put(nums[i],i);
    }
    return null;    
}
```



### [160. 相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)->[双指针]

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode l1 = headA, l2 = headB;
    while (l1 != l2) {
        l1 = (l1 == null) ? headB : l1.next;
        l2 = (l2 == null) ? headA : l2.next;
    }
    return l1;
}
```





### [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

```java
public List<Integer> spiralOrder(int[][] mat) {
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int R = mat.length, C = mat[0].length;
    List<Integer> res = new ArrayList<>();
    int r = 0, c = 0, d = 0;
    for (int k = 0; k < R * C; k++) {
        res.add(mat[r][c]);
        mat[r][c] = -100;
        int nr = r + dirs[d][0], nc = c + dirs[d][1];
        if (nr >= R || nr < 0 || nc >= C || nc < 0 || mat[nr][nc] == -100) {
            d = (d + 1) % 4;
            nr = r + dirs[d][0];
            nc = c + dirs[d][1];
        }
        r = nr;
        c = nc;
    }
    return res;
}
```

