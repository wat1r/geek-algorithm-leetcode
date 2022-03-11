## 浅谈DP中状态压缩





![image-20200825084348348](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\classical_algorithm\浅谈DP中状态压缩.assets\image-20200825084348348.png)



如何表示一个点集：
由于只有16个点，所以我们用一个整数表示一个点集：
例如：
  5 ＝ 0000000000000101；（2进制表示）
 它的第0位和第2位是1，就表示这个点集里有2个点，分别是点0和点2。
  31 ＝ 0000000000011111； （2进制表示）
 表示这个点集里有5个点，分别是0，1，2，4，5；





- 只含有第i个元素的集合：1<<i 
-   含有从0开始的n个元素的集合:  1<<n-1
-    判断第i个元素是否属于集合S：if(S>>i&1)
- 向集合中加入第i个元素：S|(1<<i)
- 向集合中删去第i个元素：S&~(1<<i)
- 判断集合T是否为S的子集:if((S|T)==S)





### 1643.旅行商问题



### 91.最短Hamilton路径



### 464. 我能赢吗




### 炮兵阵地









### Reference

- https://www.cnblogs.com/fusiwei/p/11384756.html

- https://www.cnblogs.com/ibilllee/p/7651971.html
- https://blog.csdn.net/sdz20172133/article/details/81948607
- https://www.cnblogs.com/LonecharmRiver/articles/9508145.html

- https://www.cnblogs.com/fusiwei/p/11826298.html
- https://www.cnblogs.com/mxrmxr/p/9799832.html