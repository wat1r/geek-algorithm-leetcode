# 结构设计





## [155. 最小栈](https://leetcode-cn.com/problems/min-stack/)

### 方法1：辅助栈[浪费空间]

- 基础版辅助栈，准备一个`data`, 数据栈，准备一个`help` 辅助栈

- 其中`data`的存数据的，`help`辅助栈用来存最小值，在`push`操作时，`help`如果栈顶元素大于待`push`的元素，将待`push`的元素塞进`help`中，如果不是，则重复塞一次`help`的栈顶元素，注意`help`为空的时候特殊处理下

![](/imgs/leetcode/classify/image-20210901194728655.png)

- 准备两个栈，data和help，做push操作时，需要保持help栈顶的元素始终最小，data的数据正常推入，help栈顶维持最小，在执行getMin方法的时候，返回help的栈顶元素

```java
class MinStack {
        private Stack<Integer> data;
        private Stack<Integer> help;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data = new Stack<>();
            help = new Stack<>();
        }

        public void push(int x) {
            data.push(x);
            if (help.isEmpty()) help.push(x);
            else if (help.peek() < x) help.push(help.peek());
            else help.push(x);
        }

        public void pop() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            data.pop();
            help.pop();
        }

        public int top() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            return data.peek();

        }

        public int getMin() {
            if (data.isEmpty()) throw new RuntimeException("stack empty");
            return help.peek();
        }
    }
```

#### 方法2：辅助栈[不浪费空间]

-  升级版本辅助栈，当`push` 进去的时候，
   - 当`help`的栈顶元素比新来的元素小的时候，这个时候保持`help`不变
   - 当`help`的栈顶元素大于等于新来的元素时，`help`同步要推一份新的元素进来
   - 当`help`元素为空的时候，也需要往里推
-  `pop`的时候，`pop`的元素是否和`help` 的元素有重叠，有就将`help`的元素`pop`出去，没有就维持不动，`data`栈正常`pop`

```java
class MinStack {
    Stack<Integer> data;
    Stack<Integer> help;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        data = new Stack<>();
        help = new Stack<>();
    }

    public void push(int x) {
        if (help.isEmpty() || help.peek() >= x) {
            help.push(x);
        }
        data.push(x);

    }

    public void pop() {
        if (data.isEmpty()) throw new RuntimeException("The stack is empty");
        int pop = data.pop();
        if (pop == help.peek()) {
            help.pop();
        }
    }

    public int top() {
        if (data.isEmpty()) throw new RuntimeException("The stack is empty");
        return data.peek();
    }

    public int getMin() {
        if (help.isEmpty()) throw new RuntimeException("The stack is empty");
        return help.peek();
    }
}
```









## [307. 区域和检索 - 数组可修改](https://leetcode-cn.com/problems/range-sum-query-mutable/)

### 方法1:二分

```java
class NumArray {

    class TreeNode {
        int val;
        int start;
        int end;
        TreeNode left;
        TreeNode right;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    TreeNode root = null;


    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        TreeNode curr = new TreeNode(start, end);
        if (start == end) curr.val = nums[start];
        else {
            int mid = start + (end - start) / 2;
            curr.left = buildTree(nums, start, mid);
            curr.right = buildTree(nums, mid + 1, end);
            curr.val = curr.left.val + curr.right.val;
        }
        return curr;
    }


    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    public void update(int i, int val) {
        updateTree(root, i, val);
    }

    public void updateTree(TreeNode node, int i, int val) {
        if (node.start == node.end) {
            node.val = val;
        } else {
            int mid = node.start + (node.end - node.start) / 2;
            if (i <= mid) updateTree(node.left, i, val);
            else updateTree(node.right, i, val);
            node.val = node.left.val + node.right.val;
        }
    }

    public int sumRange(int i, int j) {
        return queryTree(root, i, j);
    }

    public int queryTree(TreeNode node, int i, int j) {
        System.out.println(String.format("i:%d,j:%d", i, j));
        System.out.println(node);
        System.out.println(String.format("Node.val:%d", node.val));
        if (node.start == i && node.end == j) return node.val;
        else {
            int mid = node.start + (node.end - node.start) / 2;
            if (j <= mid) {
                return queryTree(node.left, i, j);
            } else if (i >= mid + 1) {
                return queryTree(node.right, i, j);
            } else {
                return queryTree(node.left, i, mid) + queryTree(node.right, mid + 1, j);
            }
        }
    }
}
```

### 方法2:线段树

