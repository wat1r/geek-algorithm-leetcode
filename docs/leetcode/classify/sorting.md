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





## [937. 重新排列日志文件](https://leetcode-cn.com/problems/reorder-data-in-log-files/)

```java
        public String[] reorderLogFiles(String[] logs) {
            Arrays.sort(logs, (a, b) -> {
                //limit表示分割的份数 dig1 8 1 5 1 -> "dig1"与"8 1 5 1"
                //let1 art can -> "let1"与"art can"
                String[] splitA = a.split(" ", 2);
                String[] splitB = b.split(" ", 2);
                //判断日志的类型
                boolean aIsDigit = Character.isDigit(splitA[1].charAt(0));
                boolean bIsDigit = Character.isDigit(splitB[1].charAt(0));
                //a和b都是数字日志，保留原来的相对顺序 return 0;
                if (aIsDigit && bIsDigit) return 0;
                    //a是字母日志，b是数字日志 字母日志在数字日志之前 return -1;
                else if (!aIsDigit && bIsDigit) return -1;
                    //a是数字日志，b是字母日志 字母日志在数字日志之前 return 1;
                else if (aIsDigit & !bIsDigit) return 1;
                    //a和b都是字母日志 内容不同，按内容排序
                    //内容相同，按标识符来排序
                else if (!aIsDigit && !bIsDigit) {
                    return !splitA[1].equals(splitB[1]) ? splitA[1].compareTo(splitB[1])
                            : splitA[0].compareTo(splitB[0]);
                }
                return 0;

            });
            return logs;

        }
```







### Reference

- [动画阐释各种排序算法](https://www.bilibili.com/video/BV13Y4y1H7j5)
