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



## Reference

- [蓄水池抽样算法（Reservoir Sampling）](https://www.jianshu.com/p/7a9ea6ece2af)

- [【经典算法题】蓄水池抽样算法](https://www.bilibili.com/video/BV17i4y1j7wE?spm_id_from=333.337.search-card.all.click)

- [【白纸推导】蓄水池采样](https://www.bilibili.com/video/BV16K4y1T7J9?spm_id_from=333.337.search-card.all.click)