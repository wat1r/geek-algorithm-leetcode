## [LeetCode]907. 子数组的最小值之和

## 题目

[907. 子数组的最小值之和](https://leetcode.cn/problems/sum-of-subarray-minimums/)

```java
907. 子数组的最小值之和
给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。

由于答案可能很大，因此 返回答案模 10^9 + 7 。

 

示例 1：

输入：arr = [3,1,2,4]
输出：17
解释：
子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。 
最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
示例 2：

输入：arr = [11,81,94,43,3]
输出：444
 

提示：

1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104

```





## 解法

### 方法1

```java
        public int sumSubarrayMins(int[] arr) {
            int MOD = (int) 1e9 + 7;
            int n = arr.length;
            long sum = 0;
            Stack<Integer> stk = new Stack<>();
            int j, k;
            for (int i = 0; i <= n; i++) {
                while (!stk.isEmpty() && arr[stk.peek()] > (i == n ? Integer.MIN_VALUE : arr[i])) {
                    j = stk.pop();
                    k = stk.isEmpty() ? -1 : stk.peek();
                    sum += (long) arr[j] * (i - j) * (j - k);
                }
                stk.push(i);
            }
            return (int) (sum % MOD);
        }
```

- 另外

```java
       public int sumSubarrayMins(int[] arr) {
            int MOD = (int) 1e9 + 7;
            //存的是当前元素的下标索引
            Deque<Integer> stk = new ArrayDeque<>();
            int[] A = new int[arr.length + 1];
            //最后一个元素作为哨兵
            for (int i = 0; i < arr.length; i++) A[i] = arr[i];
//            A[A.length-1] = 0;
            int N = A.length;
            long res = 0;
            for (int i = 0; i < N; i++) {
                //遍历到的元素比栈顶的元素（遍历到的元素最近的一个元素）要小
                while (!stk.isEmpty() && A[i] <= A[stk.peek()]) {
                    //设置弹出的元素是当前元素
                    int index = stk.pop();
                    //前一个元素
                    int prev = -1;
                    if (!stk.isEmpty()) prev = stk.peek();
                    int m = index - prev - 1;//
                    int n = i - index - 1;
                    res += (long) (A[index]) * (m + 1) * (n + 1) % MOD;
                    res %= MOD;
                }
                stk.push(i);
            }
            return (int) res;
        }
```



### 方法2：计算左右的贡献值

```java
        public int sumSubarrayMins(int[] nums) {
            int MOD = (int) 1e9 + 7;
            int n = nums.length;
            Deque<Integer> stk = new ArrayDeque<>();
            int[] left = new int[n], right = new int[n];
            //找到左侧第一个比nums[i]小的下标，维护一个单调递增栈
            for (int i = 0; i < n; i++) {
                //栈顶元素不小于(即大于等于)当前元素，此时有递减的趋势，弹出栈顶元素
                while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                    stk.pop();
                }
                left[i] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(i);

            }
            stk.clear();
            //栈顶元素大于(此处没有等于，此处与上一处只能一处有等于，防止重复计算)当前元素，此时有递减的趋势，弹出栈顶元素
            for (int i = n - 1; i >= 0; --i) {
                while (!stk.isEmpty() && nums[stk.peek()] > nums[i]) {
                    stk.pop();
                }
                right[i] = stk.isEmpty() ? n : stk.peek();
                stk.push(i);
            }

            long res = 0;
            for (int i = 0; i < n; i++) {
                res += 1L * (i - left[i]) * (right[i] - i) * nums[i];
                res %= MOD;
            }
            return (int) res;

        }
```



### 方法3

```java
       int MOD = (int) 1e9 + 7;

        public int sumSubarrayMins(int[] A) {
            Stack<Pair> stack = new Stack<>();
            int res = 0, t = 0;
            for (int i = 0; i < A.length; i++) {
                int count = 1;
                while (!stack.empty() && stack.peek().val >= A[i]) {
                    Pair pair = stack.pop();
                    count += pair.count;
                    t -= pair.val * pair.count;
                }
                stack.push(new Pair(A[i], count));
                t += A[i] * count;
                res += t;
                res %= MOD;
            }
            return res;
        }

        class Pair {
            public int val;
            public int count;

            public Pair(int val, int count) {
                this.val = val;
                this.count = count;
            }

        }
```










