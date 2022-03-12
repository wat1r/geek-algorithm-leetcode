

![animal-2304712_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\pre_sum\前缀和，你要跳舞吗？[Red Panda].assets\animal-2304712_960_720.jpg)

## 前缀和，你要跳舞吗？[Red Panda]

> 标题灵感来自新裤子乐队《你要跳舞吗》，可以听听

### 1.和为K的子数组

![1591023575858](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\pre_sum\前缀和，你要跳舞吗？[Red Panda].assets\1591023575858.png)

#### 方法1:基础版前缀和

- **$pre[i]$表示前i个数的前缀和即 $nums[0...i-1]$这个范围内的前缀和**

```java
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        //多放一个位置 pre[i]表示前i个数的前缀和即 nums[0...i-1]这个范围内的前缀和
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (pre[i] - pre[j] == k) res++;
            }
        }
        return res;
    }

```

#### 方法2:Hashmap版前缀和

#### 定义

- **$pre[i]$是$nums[0...i]$里所有数的和，即为前缀和**



- ![560_1](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\pre_sum\前缀和，你要跳舞吗？[Red Panda].assets\560_1.jpg)方框中的数字表示索引，$pre[i]$表示$nums[0...i]$这个区间内所有数的和，即这个区间的前缀和，如果出现红虚线框这种情况：$nums[j...i]$的和为$K$，那么很容易得到$pre[j-1]=pre[i]-K$
- 建立$map<Key,Value>$ ，其中$Key$表示是和，$Value$表示的是$pre[i]$这个前缀和出现的次数，注意初始化时，$<0,1>$,表示和为$0$时出现过$1$次，如$nums=[2,4...]$ 而$K=2$, $pre[0]=1$

```java
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        int res = 0, sumEnd = 0;
        for (int i = 0; i < n; i++) {
            sumEnd += nums[i];
            int remain = sumEnd - k;
            if (preSumMap.containsKey(remain)) res += preSumMap.get(remain);
            preSumMap.put(sumEnd, preSumMap.getOrDefault(sumEnd, 0) + 1);
        }
        return res;
    }
```

### 2.统计「优美子数组」

![1591112688255](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\pre_sum\前缀和，你要跳舞吗？[Red Panda].assets\1591112688255.png)

#### 方法1:前缀和

#### 定义

- $pre[i]$是$nums[0...i]$中奇数的个数，判断当前数是否是奇数的位运算：nums[i]&1，为$0$时表示奇数，同$560$的分析结果，很容易得到$pre[j-1]=pre[i]-K$,表示$nums[j...i]$范围内的奇数的个数恰好为$K$个，所以$pre[i]$的值只与$pre[j-1]$的值有关

- 使用个长度为$n+1$的数组$counter$，记录的是$pre[i]$的个数，即数组范围$nums[0...i]$的奇数出现的次数，而$counter[pre[i]]$的值依赖于$counter[pre[i]-K]$的值，只有当$pre[i]$>$K$时才有意义

- 初始化：$counter[0]=1$, 需要考虑这种边界情况：$ nums = [1,1] k = 2 $, 遍历到$i=1$的索引位置时：

  ```java
  if (odd >= k)res += counter[odd - k];
  ```

  $res=counter[2-2]=counter[0]=1$如果初始化为$0$时，过不了

```java
    public int numberOfSubarrays2nd(int[] nums, int k) {
        int n = nums.length;
        int[] counter = new int[n + 1];
        counter[0] = 1;
        int res = 0, odd = 0;
        for (int i = 0; i < n; i++) {
            odd += nums[i] & 1;
            if (odd >= k)res += counter[odd - k];
            counter[odd]++;
        }
        return res;
    }
```

#### 复杂度分析

- 时间复杂度：$O(N)$, 一次遍历即可，$N$为数组大小

- 空间复杂度：$O(N)$, 数组$counter$的大小

### 推荐阅读