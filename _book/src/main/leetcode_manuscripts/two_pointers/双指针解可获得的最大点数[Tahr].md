## 双指针解可获得的最大点数[Tahr]

![animal-2027624_960_720](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解可获得的最大点数[].assets\animal-2027624_960_720.png)

### 方法1：滑动窗口

![image-20201118100132468](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解可获得的最大点数[].assets\image-20201118100132468.png)

- 如上图，初始化定好 `l`和`r`这两个指针，不断滑动蓝色虚线框，剩下的红色部分相加即长度为`k`，要想这个`k`的数最大，即反向思考，要求滑动窗口的值最小
- 因为滑动窗口的长度为`r-l+1` 如第一种情况，`(n-k-1)-0+1` 即`n-k`

```java
public int maxScore(int[] nums, int k) {
    int n = nums.length;
    int l = 0, r = n - k - 1;
    int totalSum = 0;//记录总的和
    int currSum = 0;//记录当前滑动窗口的和
    for (int i = 0; i < n; i++) {
        if (i <= r) currSum += nums[i];
        totalSum += nums[i];
    }
    int minSum = currSum;//记录滑动的过程中，滑动窗口的最小值
    while (r < n - 1) {
        //先讲右窗口扩大，再将左窗口缩小
        currSum = currSum - nums[l++] + nums[++r];
        minSum = Math.min(minSum, currSum);
    }
    return totalSum - minSum;//反向思考
}
```

### 方法2：前缀和

![image-20201118093232236](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\two_pointers\双指针解可获得的最大点数[].assets\image-20201118093232236.png)

- 如上图，每个框内均为索引
  - 第1部分区间是`[0,i-1]` 长度为`i-1+1`即`i`
  - 第2部分区间是`[n-k+i,n-1]`,长度为`(n-1)-(n-k+i)+1`即`k-i`
  - 上述两部分相加 `(i)+(k-i)`即 `k` 

- 举例，当`n=7,k=3`时，`i=2`的位置
  - 第1部分区间是`[0,i-1]` 长度为`i-1+1=2-1+1=2`即`2`
  - 第2部分区间是`[n-k+i,n-1]`,长度为`(n-1)-(n-k+i)+1=(7-1)-(7-3+2)+1`即`k-i=3-2=1`
  - 上述两部分相加 `(i)+(k-i)=2+(3-2)=3`即 `k` 

```java
    public int maxScore(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        //前缀和，多放一个下标，pre[i]表示前i个数字的和，即[0...i-1]
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];
        int ans = -1;
        for (int i = 0; i <= k; i++) {
            ans = Math.max(ans, pre[i] + pre[n] - pre[n - k + i]);
        }
        return ans;
    }
```

