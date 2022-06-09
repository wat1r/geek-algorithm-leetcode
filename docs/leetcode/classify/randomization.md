> 





## 蓄水池抽样算法（Reservoir Sampling）

> **给定一个数据流，数据流长度N很大，且N直到处理完所有数据之前都不可知，请问如何在只遍历一遍数据（O(N)）的情况下，能够随机选取出m个不重复的数据。**

题意中有三个背景：

- 1.数据流长度N很大且不可知，所以不能一次性存入内存。

- 2.时间复杂度为O(N)。

- 3.随机选取m个数，每个数被选中的概率为m/N。

第1点限制了不能直接取N内的m个随机数，然后按索引取出数据。第2点限制了不能先遍历一遍，然后分块存储数据，再随机选取。第3点是数据选取绝对随机的保证。讲真，在不知道蓄水池算法前，我想破脑袋也不知道该题做何解

### 核心代码

```java
int[] reservoir = new int[m];

// init
for (int i = 0; i < reservoir.length; i++)
{
    reservoir[i] = dataStream[i];
}

for (int i = m; i < dataStream.length; i++)
{
    // 随机获得一个[0, i]内的随机整数
    int d = rand.nextInt(i + 1);
    // 如果随机整数落在[0, m-1]范围内，则替换蓄水池中的元素
    if (d < m)
    {
        reservoir[d] = dataStream[i];
    }
}
```

![](/imgs/leetcode/classify/image-20220425074932719.png)



![](/imgs/leetcode/classify/image-20220425083058319.png)





## [382. 链表随机节点](https://leetcode-cn.com/problems/linked-list-random-node/)

### 方法1：蓄水池抽样

```java
class Solution {
    ListNode head;
    Random random;

    public Solution(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        ListNode cur = head;
        int i = 1;
        int reserve = 0;
        while (cur != null) {
            if (random.nextInt(i) == 0) reserve = cur.val;
            cur = cur.next;
            i++;
        }
        return reserve;
    }
}
```

## [384. 打乱数组](https://leetcode-cn.com/problems/shuffle-an-array/)







## [398. 随机数索引](https://leetcode-cn.com/problems/random-pick-index/)

### 方法1：蓄水池抽样

```java
class Solution {
            int[] nums;
            Random random;

            public Solution(int[] nums) {
                this.nums = nums;
                random = new Random();
            }

            public int pick(int target) {
                int res = 0;
                int cnt = 0;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == target) {
                        //统计target遇到的次数
                        cnt++;// 第 cnt 次遇到 target
                        //同一个数字的频数1/n的概率选出其中一个索引
                        int r = random.nextInt(cnt);
                        if (r == 0) {
                            res = i;
                        }
                    }
                }
                return res;
            }
        }
```

### 方法2:HashMap

```java
class Solution {

    Map<Integer, List<Integer>> map = new HashMap<>();
    Random random = new Random();

    public Solution(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        return list.get(random.nextInt(list.size()));
    }
}
```





## [470. 用 Rand7() 实现 Rand10()](https://leetcode-cn.com/problems/implement-rand10-using-rand7/)





## [478. 在圆内随机生成点](https://leetcode.cn/problems/generate-random-point-in-a-circle/)

### 方法1：拒绝采样

```java
        class Solution {

            double sx, sy;
            double sr;

            public Solution(double radius, double x_center, double y_center) {
                sx = x_center;
                sy = y_center;
                sr = radius;
            }

            public double[] randPoint() {
                Random random = new Random();
                while (true) {
                    double tx = random.nextDouble() * 2 * sr - sr, ty = random.nextDouble() * 2 * sr - sr;
                    if (tx * tx + ty * ty <= sr * sr) {
                        return new double[]{sx + tx, sy + ty};
                    }
                }
            }
        }
```

### 方法2：极坐标

ρ= random ∗r

极坐标的的角度也是随机的 θ = 2∗π∗random

x = x\_center + ρ * cos(θ)

y = y\_center + ρ * sin(θ)

- [Explanation with Graphs why using Math.sqrt()](https://leetcode.com/problems/generate-random-point-in-a-circle/discuss/155650/Explanation-with-Graphs-why-using-Math.sqrt())

```java
        class Solution {

            double sx, sy;
            double sr;


            public Solution(double radius, double x_center, double y_center) {
                sx = x_center;
                sy = y_center;
                sr = radius;
            }

            public double[] randPoint() {
                Random random = new Random();
                double l = Math.sqrt(random.nextDouble()) * sr;
                double d = random.nextDouble() * 2 * Math.PI;
                double tx = sx + l * Math.cos(d);
                double ty = sy + l * Math.sin(d);
                return new double[]{tx, ty};
            }
        }
```



## [497. 非重叠矩形中的随机点](https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles/)

### 方法1：二分+前缀和

```java
 class Solution {
    Random random = new Random();
    int[][] rects;
    List<Integer> arr;//标记每个rect的开始的位置
    int n;

    public Solution(int[][] _rects) {
        rects = _rects;
        arr = new ArrayList<>();
        arr.add(0);
        for (int[] p : rects) {
            int a = p[0], b = p[1], x = p[2], y = p[3];
            //上个位置+当前rect中整数点的个数
            arr.add(arr.get(arr.size() - 1) + (x - a + 1) * (y - b + 1));
        }
        n = arr.size();
    }

    public int[] pick() {
        int k = random.nextInt(arr.get(n - 1));//获取[0...n-1]之间的一个随机数
        //k+1的写法 如果没有 +1 上面的 k可能会是0 这样返回的rectIndex=-1 会越界
        int rectIndex = binarySearch(arr, k + 1) - 1;//获取k应该在的rects中的索引位置
        k -= arr.get(rectIndex);//将k变成相对量,当前rectIndex上的增量
        int[] p = rects[rectIndex];//rectIndex的信息
        int a = p[0], b = p[1], x = p[2], y = p[3];
        int col = y - b + 1;//列上有多少个点，算上边框
        int delta_a = k / col;//在a这个点的增量
        int delta_b = k - col * delta_a;//剩下的是b的增量
        return new int[]{a + delta_a, b + delta_b};//在[a,b]点上增加增量返回
    }


    public int binarySearch(List<Integer> arr, int target) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;//下取整
            if (arr.get(mid) == target) return mid;
            else if (arr.get(mid) > target) hi = mid - 1;
            else lo = mid + 1;
        }
        return lo;//返回是 lo = hi+1
    }
}
```

### 方法2：蓄水池抽样

```java
        class Solution {
            int[][] rects;
            Random random = new Random();


            public Solution(int[][] _rects) {
                rects = _rects;
            }

            public int[] pick() { //等效从一堆点中抽一个点，若在某个矩形中包含被抽到的点，则等效抽到这个矩形
                int index = -1, n = 0; //分别记录抽到的矩形下标、当前点的总数
                for (int i = 0; i < rects.length; i++) {
                    int a = rects[i][0], b = rects[i][1], x = rects[i][2], y = rects[i][3];
                    int k = (x - a + 1) * (y - b + 1); //当前矩形包含的点数量
                    n += k;
                    if (random.nextInt(n) < k) index = i; //当前矩形有k/n的概率被保留
                }
                int[] rect = rects[index]; //抽到矩形后，再从这个矩形中随机抽取x、y的值
                int x1 = rect[0], x2 = rect[2], y1 = rect[1], y2 = rect[3];
                return new int[]{x1 + random.nextInt(x2 - x1 + 1), y1 + random.nextInt(y2 - y1 + 1)};
            }
        }
```







## Reference

- [蓄水池抽样算法（Reservoir Sampling）](https://www.jianshu.com/p/7a9ea6ece2af)

- [【经典算法题】蓄水池抽样算法](https://www.bilibili.com/video/BV17i4y1j7wE?spm_id_from=333.337.search-card.all.click)

- [【白纸推导】蓄水池采样](https://www.bilibili.com/video/BV16K4y1T7J9?spm_id_from=333.337.search-card.all.click)
- [蓄水池抽样算法](https://leetcode-cn.com/problems/linked-list-random-node/solution/xu-shui-chi-chou-yang-suan-fa-by-idouble-g7y9/)

- [水塘抽样](https://zh.wikipedia.org/wiki/%E6%B0%B4%E5%A1%98%E6%8A%BD%E6%A8%A3)