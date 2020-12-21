## 石子游戏七匹狼系列之六[Gray Wolf]

![wild-animal-708258_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之三[Gray Wolf].assets\wild-animal-708258_640.png)

![image-20201221200205711](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\dp\stone\石子游戏七匹狼系列之一[Gray Wolf].assets\image-20201221200205711.png)

## 石子游戏七匹狼系列之七[Gray Wolf]



### 方法1：贪心

> 参考国际站`lee215`大佬的思路

- 对于`Alice`和`Bob`来说，当遇到一个石子，`Alice`认为它的价值是`a`,`Bob`认为它的价值是`b`, 
  - 如果`Alice`拿走了这个石子，对于`Alice`来说，收益是`a`
  - 如果`Bob`拿走了这个石子，对于`Bob`来说，收益是`b`,因为他们对于同一个石子的认可价值不同
  - 反过来想，`-b`就是对于`Alice`的价值，虽然是负值

- 对于某个索引的石子，如果`Alice`认为它价值很高，`Alice`处于价值最大化的考虑，肯定要拿走
- 对于某个索引的石子，如果`Bob`认为它价值很高，`Alice`也要拿走，因为`Alice`不拿走，`Bob`就如偿所愿地选走了这个`Bob`认为有很大价值的石子，总之，`Alice`需要拿走，处在同一位置的上，价值最大的那一组

#### 思路

- 合并`aliceValues`与`bobValues`的数组，按最大价值从大到小排列，`Alice`先选，`Bob`后选，偶数下标就标称`Alice`选取的石头的总价值，`Bob`选的则是奇数下标的石子，比较两个总和

```java
public int stoneGameVI(int[] A, int[] B) {
    int n = A.length;
    //0存Alice与Bob认为当前石子的价值偶总和，1存Alice认为的石子的价值，2存Bob认为的石子价值
    int[][] sum = new int[n][3];
    for (int i = 0; i < n; i++) sum[i] = new int[]{A[i] + B[i], A[i], B[i]};
    //从大到小排序
    Arrays.sort(sum, (o1, o2) -> o2[0] - o1[0]);
    int a = 0, b = 0;
    for (int i = 0; i < n; i++) {
        //偶数 Alice从  0 2 4 ... a是Alice拿到的总价值
        if ((i & 1) == 0) a += sum[i][1];
        //奇数 Bob从 1 3 5 .... b是Bob拿到的总价值
        else b += sum[i][2];
    }
    return Integer.compare(a, b);
}
```









