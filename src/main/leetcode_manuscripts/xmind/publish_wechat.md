





## 图解905按奇偶排序数组(原地多解法)






> **欢迎阅读、点赞、转发、订阅，你的举手之间，我的动力源泉，欢评论区提供其他语言的版本**



- 这一题应该选择原地，没有额外空间复杂度的写法

### 方法1：前前双指针

- 固定`i`,`j`指针，`j`指针往后滑动，如果是偶数，交换`i`和`j`，并将i后滑动

![image-20220428074838120](/Users/frankcooper/Library/Application Support/typora-user-images/image-20220428074838120.png)

```java
public int[] sortArrayByParity(int[] nums) {
    for (int i = 0, j = 0; j < nums.length; j++) {
        if (nums[j] % 2 == 0) {
            int t = nums[i];
            nums[i++] = nums[j];
            nums[j] = t;
        }
    }
    return nums;
}
```





### 方法2：前后双指针

![image-20220428075433494](/Users/frankcooper/Library/Application Support/typora-user-images/image-20220428075433494.png)

```java
        public int[] sortArrayByParity(int[] nums) {
            int n = nums.length, l = 0, r = n - 1;
            while (l < r) {
                if (nums[l] % 2 == 1 && nums[r] % 2 == 0) {
                    int t = nums[l];
                    nums[l++] = nums[r];
                    nums[r--] = t;
                } else if (nums[l] % 2 == 0) l++;
                else if (nums[r] % 2 == 1) r--;
            }
            return nums;
        }
```







### 更多阅读

- 算法题解的链接地址： [gitbook](https://cnwangzhou.gitbook.io/algorithm/)

- [个人主页【阿飞算法】](https://blog.csdn.net/wat1r/article/details/117533156) 加我好友，进群一起交流~
