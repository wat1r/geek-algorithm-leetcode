### 二分模板

 关于取中位数，基于此题解，我记忆的口诀是「左动取左，右动取右」，即 `if (...) left = mid + 1;` 归为「左动」，对应左中位数；`if (...) right = mid - 1;` 归为「右动」，对应右中位数。

```java
// 查找第一个值等于给定值的元素
private int firstEquals(int[] arr, int target) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
        int mid = l + ((r - l) >> 1);
        if (arr[mid] < target) l = mid + 1;
        else r = mid; // 收缩右边界不影响 first equals
    }
    if (arr[l] == target && (l == 0 || arr[l - 1] < target)) return l;
    return -1;
}
// 查找最后一个值等于给定值的元素
private int lastEquals(int[] arr, int target) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
        int mid = l + ((r - l + 1) >> 1);
        if (arr[mid] > target) r = mid - 1;
        else l = mid; // 收缩左边界不影响 last equals
    }
    if (arr[l] == target && (l == arr.length - 1 || arr[l + 1] > target)) return l;
    return -1;
}
// 查找第一个大于等于给定值的元素
private int firstLargeOrEquals(int[] arr, int target) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
        int mid = l + ((r - l) >> 1);
        if (arr[mid] < target) l = mid + 1;
        else r = mid; // 收缩右边界不影响 first equals
    }
    if (arr[l] >= target && (l == 0 || arr[l - 1] < target)) return l; // >=
    return -1;
}
// 查找最后一个小于等于给定值的元素
private int lastLessOrEquals(int[] arr, int target) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
        int mid = l + ((r - l + 1) >> 1);
        if (arr[mid] > target) r = mid - 1;
        else l = mid; // 收缩左边界不影响 last equals
    }
    if (arr[l] <= target && (l == arr.length - 1 || arr[l + 1] > target)) return l; // <=
    return -1;
}
```



### 二分查找的两个模板

```c++
二分模板一共有两个，分别适用于不同情况。
算法思路：假设目标值在闭区间[l, r]中， 每次将区间长度缩小一半，当l = r时，我们就找到了目标值。

版本1
当我们将区间[l, r]划分成[l, mid]和[mid + 1, r]时，其更新操作是r = mid或者l = mid + 1;，计算mid时不需要加1。

C++ 代码模板：
int bsearch_1(int l, int r)
{
    while (l < r)
    {
        int mid = l + r >> 1;
        if (check(mid)) r = mid;
        else l = mid + 1;
    }
    return l;
}
版本2
当我们将区间[l, r]划分成[l, mid - 1]和[mid, r]时，其更新操作是r = mid - 1或者l = mid;，此时为了防止死循环，计算mid时需要加1。

C++ 代码模板：

int bsearch_2(int l, int r)
{
    while (l < r)
    {
        int mid = l + r + 1 >> 1;
        if (check(mid)) l = mid;
        else r = mid - 1;
    }
    return l;
}
```





```java
一、查找精确值
从一个有序数组中找到一个符合要求的精确值（如猜数游戏）。如查找值为Key的元素下标，不存在返回-1。

//这里是left<=right。
//考虑这种情况：如果最后剩下A[i]和A[i+1]（这也是最容易导致导致死循环的情况)首先mid = i,
//如果A[mid] < key，那么left = mid+1 = i +1，如果是小于号，则A[i + 1]不会被检查，导致错误
int left = 1,right = n;
while(left <= right)
{
   //这里left和right代表的是数组下标，所有没有必要改写成mid = left + (right - left)/2;
  //因为当代表数组下标的时候，在数值越界之前，内存可能就已经越界了
  //如果left和right代表的是一个整数，就有必要使用后面一种写法防止整数越界
        int mid = (left + right) / 2;
    if(A[mid] == key)
      return mid;
    else if(A[mid] > key)//这里因为mid不可能是答案了，所以搜索范围都需要将mid排除
      right = mid - 1;
    else
      left = mid + 1;
}
return -1;
二、查找大于等于/大于key的第一个元素
这种通常题目描述为满足某种情况的最小的元素。

int left = 1,right = n;
while(left < right)
{
  //这里不需要加1。我们考虑如下的情况，最后只剩下A[i],A[i + 1]。
  //首先mid = i，如果A[mid] > key，那么right = left = i，跳出循环，如果A[mid] < key，left = right = i + 1跳出循环，所有不会死循环。
  int mid = (left + right) / 2;
  if(A[mid] > key)//如果要求大于等于可以加上等于，也可以是check(A[mid])
    right = mid;
  //因为找的是大于key的第一个元素，那么比A[mid]大的元素肯定不是第一个大于key的元素，因为A[mid]已经大于key了，所以把mid+1到后面的排除
  else
    left = mid + 1;
  //如果A[mid]小于key的话，那么A[mid]以及比A[mid]小的数都需要排除，因为他们都小于key。不可能是第一个大于等于key的元素，
}
三、查找小于等于/小于key的最后一个元素
这种通常题目描述为满足某种情况的最大的元素。如Leetcode69题，求sqrt(x)向下取整就是这种模板。

int left = 1, right = n;
while(left < right)
{
  //这里mid = (left + right + 1) / 2;
  //考虑如下一种情况，最后只剩下A[i],A[i + 1]，如果不加1，那么mid = i，如果A[mid] < key，执行更新操作后，left = mid，right = mid + 1，就会是死循环。
  //加上1后，mid = i + 1,如果A[mid] < key，那么left = right = mid + 1,跳出循环。如果A[mid] > key，left = mid = i，跳出循环。
  int mid = (left + right + 1) / 2;
  if(A[mid] < key)
    left = mid;//如果A[mid]小于key，说明比A[mid]更小的数肯定不是小于key的最大的元素了，所以要排除mid之前的所有元素
  else
    right = mid - 1;//如果A[mid]大于key，那么说明A[mid]以及比A[mid]还要大的数都不可能小于key，所以排除A[mid]及其之后的元素。
}
四、总结
最后两种情况的循环跳出条件是left<right，为什么不是小于等于呢？因为我们的区间变换思路是不断的舍去不可能是解的区间，最后只剩下一个数就是我们的解。而第一种情况就算最后只剩一个数也有可能不是解，所以需要使用小于等于。

查找精确值，循环条件是小于等于；查找满足情况的最大最小值，循环条件是小于。
查找满足条件的最大数，mid = (right + left + 1) / 2；查找满足条件的最小数，mid = (right + left)/2
mid = left + (right - left) / 2，不是适用于所有的情况。
如果存在没有解的情况，比如从[1,2,3,4,5]找出大于等于6的第一个数，我们只需要将最后剩下的数单独进行一次判断就可以了。


```





```java
虽然这模版真的是非常好用，但是每次在决定那个 check 函数时总是让我想破头，
因为一不小心写反就找不到了，一路跌跌撞撞后稍稍有点心得，如果有错还请各位高手指正

假设有一个总区间，经由我们的 check 函数判断后，可分成两部分，
这边以o作 true，.....作 false 示意较好识别

如果我们的目标是下面这个v，那麽就必须使用模板 1

................vooooooooo

假设经由 check 划分后，整个区间的属性与目标v如下，则我们必须使用模板 2

oooooooov...................

所以下次可以观察 check 属性再与模板1 or 2 互相搭配就不会写错啦

作者：yxc
链接：https://www.acwing.com/blog/content/31/
来源：AcWing
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```





## Reference

- [二分查找算法模板](https://www.acwing.com/blog/content/31/)

- [二分边界详细总结](https://www.acwing.com/blog/content/307/)