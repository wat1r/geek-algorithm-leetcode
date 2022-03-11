## 双指针解四数之和[Topi]

![antelope-5200114_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解四数之和[Topi].assets\antelope-5200114_640.jpg)



### 1.四数之和I

![image-20200806211809453](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解四数之和[Topi].assets\image-20200806211809453.png)

- 和三数之和的逻辑极度类似，三数之和我们固定了一个数，四数之和我们多固定一个数,固定了$a$与$b$,不断游走$c$和$d$
- 难点是判断重复：当$a$与前一个重复，跳过，当$b$与前一个重复，跳过
- $c$和$d$的判断重复的逻辑类似，不同的是需要推进指针

![image-20200807083514352](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解四数之和[Topi].assets\image-20200807083514352.png)



```java
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length < 4) return results;
        Arrays.sort(nums);
        int n = nums.length;
        for (int a = 0; a < n; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) continue;
            for (int b = a + 1; b < n; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) continue;
                int c = b + 1, d = n - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum == target) {
                        results.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c + 1] == nums[c]) c++;
                        while (c < d && nums[d - 1] == nums[d]) d--;
                        c++;
                        d--;
                    } else if (sum < target) c++;
                    else if (sum > target) d--;
                }
            }
        }
        return results;
    }

```

### 2.四数相加II

![image-20200807081018402](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解四数之和[Topi].assets\image-20200807081018402.png)

> 本题用的hash的方式解的，放在本题题解下，匹配全类型的题目

- 准备一个$map$记录$A$与$B$数组的和，$<K,V>$  $K$表示$A$ 与$B$数组的两个数的和，$V$表示这个数出现的次数
- 题目要求$A[i] + B[j] + C[k] + D[l] = 0$, 而$A[i] + B[j]$我们已经求得了，在$map$中进行了保存
- 只需要求$A[i] + B[j]$的相反数即可，出现的次数累加，返回

> map函数介绍

```java
//当Map集合中有这个key时，就使用这个key值，如果没有就使用默认值defaultValue
default V getOrDefault(Object key, V defaultValue)
//如果key值已存在，则不替换对应的value
V putIfAbsent(K key, V value)
```



```java

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum1 = a + b;
                map1.put(sum1, map1.getOrDefault(sum1, 0) + 1);
            }
        }
        for (int c : C) {
            for (int d : D) {
                int sum2 = c + d;
                if(map1.containsKey(-sum2))  res += map1.get(-sum2);
            }
        }
        return res;
    }
```





