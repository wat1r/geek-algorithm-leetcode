# 排序

> 

## [912. 排序数组](https://leetcode-cn.com/problems/sort-an-array/)

### 方法1:快排

```java
  public int[] sortArray(int[] nums) {
            return quickSort(nums, 0, nums.length - 1);

        }


        private int[] quickSort(int[] nums, int left, int right) {
            if (left < right) {
                int pId = parition(nums, left, right);
                quickSort(nums, left, pId - 1);
                quickSort(nums, pId + 1, right);
            }
            return nums;
        }

        private int parition(int[] nums, int left, int right) {
            int pivot = left;
            int index = pivot + 1;
            for (int i = index; i <= right; i++) {
                if (nums[i] < nums[pivot]) {
                    swap(nums, i, index);
                    index++;
                }
            }
            swap(nums, pivot, index - 1);
            return index - 1;
        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
```







### Reference

- [动画阐释各种排序算法](https://www.bilibili.com/video/BV13Y4y1H7j5)
