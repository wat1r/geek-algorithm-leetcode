## 前缀和之和为K的子数组[Red Panda]



### 源起

![1591023575858](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\pre_sum\前缀和之和为K的子数组[Red Panda].assets\1591023575858.png)

### 方法1:基础版前缀和

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







### 方法2:Hashmap版前缀和

#### 定义

- **$pre[i]$是$nums[0...i]$里所有数的和，即为前缀和**

![560_1](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\pre_sum\前缀和之和为K的子数组[Red Panda].assets\560_1.jpg)

- 方框中的数字表示索引，$pre[i]$表示$nums[0...i]$这个区间内所有数的和，即这个区间的前缀和，如果出现红虚线框这种情况：$nums[j...i]$的和为$K$，那么很容易得到$pre[j-1]=pre[i]-K$
- 建立$map<Key,Value>$ ，其中$Key$表示是和，$Value$表示的是$pre[i]$这个前缀和出现的次数，注意初始化时，$<0,1>$,表示和为$0$时出现过$1$次



