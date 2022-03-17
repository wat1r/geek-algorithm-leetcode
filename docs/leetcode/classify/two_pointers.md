# 双指针

>





## [26. 删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)

### 方法1

```java
//            [1,1,2]  ->[1,2]
//[0,0,1,1,1,2,2,3,3,4] -> [0,1,2,3,4]
//[1] -> [1]
```

```java
public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 1) return nums.length;
    int i = 0, j = 1;
    for (; i < nums.length && j < nums.length; ) {
        while (j < nums.length && nums[i] == nums[j]) {
            j++;
        }
        i++;
        if (j < nums.length) nums[i] = nums[j];
    }
    return i;
}
```



### 方法2

```java
public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int i = 0, j = 1;
    while (j < nums.length) {
        if (nums[i] == nums[j]) j++;
        else {
            int t = nums[++i];
            nums[i] = nums[j];
            nums[j++] = t;
        }
    }
    return i + 1;
}
```



## [18. 四数之和](https://leetcode-cn.com/problems/4sum/)

```java
public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> results = new ArrayList<>();
    if (nums == null || nums.length < 4) return results;
    Arrays.sort(nums);//排序
    int n = nums.length;
    for (int a = 0; a < n; a++) {
        //重复的a 跳过
        if (a > 0 && nums[a] == nums[a - 1]) continue;
        for (int b = a + 1; b < n; b++) {
            //重复的b 跳过
            if (b > a + 1 && nums[b] == nums[b - 1]) continue;
            //固定a b 后  选取 c d两个指针
            int c = b + 1, d = n - 1;
            while (c < d) {
                int sum = nums[a] + nums[b] + nums[c] + nums[d];
                //找到了
                if (sum == target) {
                    results.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                    //跳过c d 重复
                    while (c < d && nums[c + 1] == nums[c]) c++;
                    while (c < d && nums[d - 1] == nums[d]) d--;
                    c++;
                    d--;
                } else if (sum < target) c++;//滑动
                else if (sum > target) d--;//滑动
            }
        }
    }
    return results;
}
```





## [151. 翻转字符串里的单词](https://leetcode-cn.com/problems/reverse-words-in-a-string/)

### 方法1:基本

```java
public String reverseWords(String s) {
    if (s == null || s.length() == 0) return null;
    char[] chas = s.toCharArray();
    reverse(chas, 0, chas.length - 1);
    String t = String.valueOf(chas);
    String[] arr = t.trim().split("\\s+");
    StringBuilder ans = new StringBuilder();
    for (String a : arr) {
        char[] tmp = a.toCharArray();
        reverse(tmp, 0, a.length() - 1);
        ans.append(tmp);
        ans.append(" ");
    }
    return ans.toString().trim();
}


private void reverse(char[] chas, int l, int r) {
    while (l < r) {
        char tmp = chas[l];
        chas[l++] = chas[r];
        chas[r--] = tmp;
    }
}
```

### 方法2:多次翻转

```java
public String reverseWords(String s) {
    char[] chas = s.toCharArray();
    int n = chas.length;
    reverse(chas, 0, chas.length - 1);
    reverseWords(chas, n);
    return cleanSpaces(chas, n);
}


public String cleanSpaces(char[] chas, int n) {
    int l = 0, r = 0;
    while (r < n) {
        while (r < n && chas[r] == ' ') r++;
        while (r < n && chas[r] != ' ') chas[l++] = chas[r++];
        while (r < n && chas[r] == ' ') r++;
        if (r < n) chas[l++] = ' ';
    }
    return new String(chas).substring(0, l);
}

public void reverseWords(char[] chas, int n) {
    int l = 0, r = 0;
    while (l < chas.length) {
        while (l < r || l < n && chas[l] == ' ') l++;
        while (r < l || r < n && chas[r] != ' ') r++;
        reverse(chas, l, r - 1);
    }
}


public void reverse(char[] chas, int l, int r) {
    while (l < r) {
        char t = chas[l];
        chas[l++] = chas[r];
        chas[r--] = t;
    }
}
```

