# 双指针

>



### 0.抽象模型

- 初始化时准备两个指针，`left`与`right`指针，指向`index=0`
- 扩大`right`指针，当第一次符合窗口大小时，执行逻辑
- 在符合条件下，不断扩`right`，缩`left`，直到`right`达到数组或是字符串的末尾，`left`缩小到不符合窗口大小

![](/imgs/leetcode/classify/1588806280620.png)

## [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

![](/imgs/leetcode/classify/1588603266606.png)

> 这一题是leetcode的NO.3题，曾经被面到过，很经典的一道题

- **注意：子串与子序列的区别**
  - 子串：不可跳跃，如 `pwwkew`中`wke`是子串
  - 子序列：可以跳跃，如`pwwkew`中`pkw`是子序列

### 方法1:粗糙版滑动窗口

- 用`Set`来维护重复字符与否的问题
  - 出现新的字符，很好，`right`指针向右扩展，并将当前字符加入`Set`中，更新最长无重复子串长度
  - 出现旧的字符，将左边界`left`指针向右收缩，移除当前的`left`指针指向的字符

```java
    public int lengthOfLongestSubstring1st(String s) {
        int res = 0, left = 0, right = 0;
        int n = s.length();
        Set<Character> set = new HashSet<>();
        while (right < n && left < n) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right++));
                res = Math.max(res, right - left);
            } else {
                set.remove(s.charAt(left++));
            }
        }
        return res;
    }
```

### 方法2:优化版滑动窗口

- 用一个`hashmap`来建立字符和其出现位置之间的映射。
- 维护一个滑动窗口，窗口内的都是没有重复的字符，去尽可能的扩大窗口的大小，窗口不停的向右滑动。
- （1）如果当前遍历到的字符从未出现过，那么直接扩大右边界；
- （2）如果当前遍历到的字符出现过，则缩小窗口（左边索引向右移动），然后继续观察当前遍历到的字符；
- （3）重复（1）（2），直到左边索引无法再移动；
- （4）维护一个结果`res`，每次用出现过的窗口大小来更新结果`res`，最后返回res获取结果。
  - 用一个`mapper`记录出现过并且没有被删除的字符
  - 用一个滑动窗口记录当前`index`开始的最大的不重复的字符序列
  - 用`res`去记录目前位置最大的长度，每次滑动窗口更新就去决定是否需要更新`res`

```java
  public int lengthOfLongestSubstring2nd(String s) {
        int res = 0, left = 0, right = 0;
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        while (right < n) {
             //使用hashmap作为存储，每次left的值取最大的
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)));
            }
            //计算下标的距离，记录下标的绝对物理index
            res = Math.max(res, right - left + 1);
            map.put(s.charAt(right), 1 + right++);
        }
        return res;
    }
```

### 方法3:再优化版滑动窗口

- 常用的表如下所示：

```python
int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
int [128] 用于ASCII码
int [256] 用于扩展ASCII码
```

- 准备一个`helper`数组，每次记录`right`指针的绝对位置，
- 更新`res`,更新`left`(拿到其最大位置)

```java
    public int lengthOfLongestSubstring3rd(String s) {
        int res = 0, left = 0, right = 0;
        int n = s.length();
        int[] helper = new int[128];
        while (right < n) {
            left = Math.max(left, helper[s.charAt(right)]);
            res = Math.max(res, right - left + 1);
            helper[s.charAt(right)] = 1 + right++;
        }
        return res;
    }
```



## 最小覆盖子串

![](/imgs/leetcode/classify/1588859233255.png)

- 准备两个`hash`   `arr`，`source`   `target`，先给`t`的记上，作为标准，每个字母出现的次数
- 提供一个`valid`的方法，比较`source`  `target`，判断`source`是否都包含`target`，包含的话`true`，不包含的话`false`

- 然后开始构造`window`，最外层的条件是`right<t.size()`,

  - 当`valid`过不了的时候，说明`window`中还不含有`t`，记录下`source`，并将`right++`，即将`window`的右边界往右边推
   - 当`valid`满足条件时，说明`window`都能涵盖了`t`的字符，记录长度和`res`，并将`source`相应的字符`--`，将`left++`，缩小`window`的左边界

```java
public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        int left = 0, right = 0, len = Integer.MAX_VALUE;
        String res = "";
        int n = s.length();
        int[] source = new int[128];
        int[] target = new int[128];
        for (char c : t.toCharArray()) target[c]++;
        while (right < n) {
            if (!valid(source, target)) source[s.charAt(right++)]++;
            while (valid(source, target)) {
                if (right - left < len) {
                    len = Math.min(len, right - left);
                    res = s.substring(left, right);
                }
                source[s.charAt(left++)]--;
            }
        }
        return res;
    }

//判断source是否都包含target，包含的话true，不包含的话false
    private boolean valid(int[] source, int[] target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] < target[i]) return false;
        }
        return true;
    }
```





## [15. 三数之和](https://leetcode-cn.com/problems/3sum/)

```java
/*
 *[-1,0,1,2,-1,-4]
 *[[-1,-1,2],[-1,0,1]]
 * @param nums
 * @return
 */
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    if (nums == null || nums.length < 3) return results;
    Arrays.sort(nums);
    int n = nums.length;
    for (int i = 0; i < n; i++) {
        if (nums[i] > 0) break;
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        int l = i + 1, r = n - 1;
        while (l < r) {
            int sum = nums[i] + nums[l] + nums[r];
            if (sum == 0) {
                results.add(Arrays.asList(nums[i], nums[l], nums[r]));
                while (l < r && nums[l + 1] == nums[l]) l++;
                while (l < r && nums[r - 1] == nums[r]) r--;
                l++;
                r--;
            } else if (sum < 0) l++;
            else if (sum > 0) r--;
        }
    }
    return results;
}
```





## [16. 最接近的三数之和](https://leetcode-cn.com/problems/3sum-closest/)

```java
public int threeSumClosest(int[] nums, int target) {
    //边界判断
    if (nums == null || nums.length < 3) return -1;
    //保持数组有序，使用双指针
    Arrays.sort(nums);
    int n = nums.length;
    //目前的三数之和的值
    int t = nums[0] + nums[1] + nums[2];
    //初始的t 与 target之间的差值，取绝对值
    int baseDelta = Math.abs(t - target);
    //开始遍历，i到n-3止
    for (int i = 0; i < n - 2; i++) {
        int l = i + 1, r = n - 1;//左右指针
        while (l < r) {
            //三数之和
            int sum = nums[i] + nums[l] + nums[r];
            //新的 sum 与 target 之间的差值
            int newDelta = Math.abs(sum - target);
            //差值因为是绝对值，不可能比0还小
            if (newDelta == 0) return sum;
            //比较新旧差值，更新
            if (newDelta < baseDelta) {
                t = sum;
                baseDelta = newDelta;
            }
            //双指针
            if (sum > target) r--;
            else l++;
        }
    }
    return t;
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







## [151. 翻转字符串里的单词](https://leetcode-cn.com/problems/reverse-words-in-a-string/)

### 方法1:基本

```java
public String reverseWords(String s) {
    if (s == null || s.length() == 0) return null;
    char[] chas = s.toCharArray();
  	//整体翻转一次
    reverse(chas, 0, chas.length - 1);
    String t = String.valueOf(chas);
    String[] arr = t.trim().split("\\s+");
    StringBuilder ans = new StringBuilder();
    for (String a : arr) {
        char[] tmp = a.toCharArray();
      	//每一个单词再次翻转一次
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
  	//整体翻转一次
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

//
public void reverseWords(char[] chas, int n) {
    int l = 0, r = 0;
    while (l < chas.length) {
       //移除空格
        while (l < r || l < n && chas[l] == ' ') l++;
        while (r < l || r < n && chas[r] != ' ') r++;
        reverse(chas, l, r - 1);
    }
}

//翻转整个数组
public void reverse(char[] chas, int l, int r) {
    while (l < r) {
        char t = chas[l];
        chas[l++] = chas[r];
        chas[r--] = t;
    }
}
```





## [161. 相隔为 1 的编辑距离](https://leetcode.cn/problems/one-edit-distance)

### 方法1：比较字符串

```java
public static void main(String[] args) {
    _1st handler = new _1st();
    String s = "ab", t = "acb";
    Assert.assertTrue(handler.isOneEditDistance(s, t));
    s = "cab";
    t = "ad";
    Assert.assertFalse(handler.isOneEditDistance(s, t));
    s = "1203";
    t = "1213";
    Assert.assertTrue(handler.isOneEditDistance(s, t));


}


public boolean isOneEditDistance(String s, String t) {
    int sn = s.length(), tn = t.length();
    //维持s的长度小于t
    if (sn > tn) {
        return isOneEditDistance(t, s);
    }
    if (tn - sn > 1) return false;//相隔大于1时，返回false
    for (int i = 0; i < sn; i++) {
        if (s.charAt(i) != t.charAt(i)) {
            //s与t的长度相同，比较后面的
            if (sn == tn) {
                return s.substring(i + 1).equals(t.substring(i + 1));
            } else {//s与t的长度不同 s的字符短
                return s.substring(i).equals(t.substring(i + 1));
            }
        }
    }
    return sn + 1 == tn;
}
```



## [186. 翻转字符串里的单词 II](https://leetcode.cn/problems/reverse-words-in-a-string-ii)

### 方法1：翻转

```java
public void reverseWords(char[] str) {
    int i = 0;
    for (int j = 0; j < str.length; j++) {
        if (str[j] == ' ') {
            reverse(str, i, j);
            i = j + 1;
        }
    }
    reverse(str, i, str.length);
    System.out.println(String.valueOf(str));
    reverse(str, 0, str.length);
}

/**
 * 将 ch 的 [l, r] 进行翻转，
 * 注意，[l,r) 是左闭右开
 *
 * @param ch
 * @param l
 * @param r
 */
private void reverse(char[] ch, int l, int r) {
    for (int k = l; k < (l + r) / 2; k++) {
        char tmp = ch[k];  // 位置 k 的元素
        int g = r - 1 - k + l;  // 位置 k 的对称位置
        ch[k] = ch[g];
        ch[g] = tmp;
    }
}
```







## [522. 最长特殊序列 II](https://leetcode.cn/problems/longest-uncommon-subsequence-ii/)

### 方法1：暴力枚举+状态压缩

```java
public int findLUSlength(String[] strs) {
    Map<String, Integer> map = new HashMap<>();
    for (String str : strs) {
        int n = str.length();
        //从低位到高位，如果该位位1 表示这一位的字符会被扣掉，不会组成下面的seq的子序列
        //例如 abcd 如果 为0000表示 abcd均可用
        //         如果  为0001表示 a这一个字符不可用 bcd可用
        //         如果  为1001表示，ad这两个字符不可用 bc可用
        for (int i = 0; i < (1 << n); i++) {
            String seq = "";
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 0) {
                    seq += str.charAt(j);
                }
            }
            System.out.printf("i:%d,bit:%3s,%s\n", i, PrintUtils.toBinaryString(i, 4), seq);
            map.put(seq, map.getOrDefault(seq, 0) + 1);
        }
    }
    int res = -1;
    //统一只有孤立的一个字序列的字符串
    for (String k : map.keySet()) {
        if (map.get(k) == 1) res = Math.max(res, k.length());
    }
    return res;
}
```

### 方法2：暴力枚举+挨个检查

```java

        //如果s是t的一个子序列，返回true，否则返回false
        public boolean isSubsequence(String s, String t) {
            int i = 0, j = 0;
            for (; i < s.length() && j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                }
            }
            return i == s.length();
        }

        public int findLUSlength(String[] strs) {
            int res = -1;
            for (int i = 0, j; i < strs.length; i++) {
                for (j = 0; j < strs.length; j++) {
                    if (j == i)
                        continue;
                    // isSubsequence(String s, String t) 表示s是否是t的一个子序列
                    // 提前break说明str[i] 已经是 strs[j]的子序列
                    // 结合  「最长特殊序列 定义如下：该序列为某字符串 独有的最长子序列（即不能是其他字符串的子序列）。」
                    //str[i] 即是 str[i] 也是 str[j] 的子序列，不满足 「独有」的条件
                    if (isSubsequence(strs[i], strs[j])) {
                        System.out.printf("%s %s\n", strs[i], strs[j]);
                        break;
                    }

                }
                if (j == strs.length)
                    res = Math.max(res, strs[i].length());
            }
            return res;
        }
```







## [532. 数组中的 k-diff 数对](https://leetcode.cn/problems/k-diff-pairs-in-an-array/)

### 方法1：双指针

```java
        public int findPairs(int[] nums, int k) {
            Arrays.sort(nums);
            int res = 0, n = nums.length, i = 0, j = 1;
            while (i <= j && j < n) {
                //计算diff
                int diff = nums[j] - nums[i];
                if (diff == k) {
                    res++;
                    // System.out.printf("%diff %diff -> ",  nums[i] , nums[j]  );
                    //如果j重复出现，不再被统计
                    while (j < n - 1 && nums[j + 1] == nums[j]) j++;
                    j++;
                } else if (diff > k) i++;
                else if (diff < k) j++;
                if (i == j) j++;//强制性岔开1个数,否则比较的是两个相同数本身
            }
            return res;
        }
```

另

```java

        public int findPairs(int[] nums, int k) {
            Arrays.sort(nums);
            int n = nums.length, j = 0, res = 0;
            for (int i = 0; i < n; i++) {//定左区间
                //i==0 初始情况，进入
                //i这个数和之前的数相同，跳过
                if (i == 0 || nums[i] != nums[i - 1]) {
                    //选定j的边界
                    //[j]-[i]的 diff比 k小 要扩j
                    //i==j 说明i追上j了，错开1个位置
                    while (j < n && (nums[j] - nums[i] < k || j == i)) {
                        j++;
                    }
                    //统计
                    if (j < n && nums[j] - nums[i] == k) {
                        res++;
                    }
                }
            }
            return res;
        }
```



### 方法2：哈希

> 两个hash的做法，写法很好

```java
     	//int nums[] = {3 1 4 1 5} k = 2;
        //							|
        //i=0				vis:3
        //					res:
        //i=1				vis:3 1
        //					res:1
        //i=2				vis:3 1 4
        //					res:1
        //i=3				vis:3 1 4  //这一步已经添加了元素1，两个set去重了
        //					res:1
        //i=4				vis:3 1 4 5
        //					res:1 3
```

```java
     public int findPairs(int[] nums, int k) {
            Set<Integer> vis = new HashSet<>(), res = new HashSet<>();
            for (int x : nums) {
                //以x为起点，找其上，即[x,x+k]
                if (vis.contains(x + k)) {
                    res.add(x);//都是以下作为参考值
                }
                //以x为起点，找其下,即[x-k,x]
                if (vis.contains(x - k)) {
                    res.add(x - k);//都是以下作为参考值
                }
                vis.add(x);//x已经被统计过
            }
            return res.size();
        }
```







## [905. 按奇偶排序数组](https://leetcode-cn.com/problems/sort-array-by-parity/)

- 这一题应该选择原地，没有额外空间复杂度的写法

### 方法1：前前双指针

- 固定`i`,`j`指针，`j`指针往后滑动，如果是偶数，交换`i`和`j`，并将i后滑动

![](/imgs/leetcode/classify/image-20220428074838120.png)

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

![](/imgs/leetcode/classify/image-20220428075433494.png)

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



## [1023. 驼峰式匹配](https://leetcode.cn/problems/camelcase-matching/)

### 方法1：双指针+check

- 校验s与pattern的字符

```java
public List<Boolean> camelMatch(String[] queries, String pattern) {
    List<Boolean> res = new ArrayList<>();
    for (String s : queries) {
        res.add(check(s, pattern));
    }
    return res;
}

private boolean check(String s, String p) {
    int m = s.length(), n = p.length(), i = 0, j = 0;
    while (i < m || j < n) {
        // System.out.printf("%d %d-> ",i , j );
        if (i < m && j < n && s.charAt(i) == p.charAt(j)) {
            i++;
            j++;
        } else if (i < m && s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
            i++;
        } else {
            return false;
        }
    }
    return true;

}
```







## [1089. 复写零](https://leetcode.cn/problems/duplicate-zeros/)

### 方法1：快慢指针

```java
        public void duplicateZeros(int[] arr) {
            int n = arr.length;
            int i = 0, j = 0;
            while (j < n) {
                if (arr[i] == 0) j++;
                i++;
                j++;
            }
            i--;
            j--;
            while (i >= 0) {
                if (j < n) arr[j] = arr[i];
                if (arr[i] == 0) {
                    arr[--j] = arr[i];
                }
                i--;
                j--;
            }
        }
```

另

```java
        public void duplicateZeros(int[] arr) {
            int countZero = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) countZero++;
            }
            int len = arr.length + countZero;
            //We just need O(1) space if we scan from back
            //i point to the original array, j point to the new location
            for (int i = arr.length - 1, j = len - 1; i < j; i--, j--) {
                if (arr[i] != 0) {
                    if (j < arr.length) arr[j] = arr[i];
                } else {// 为0的情况下
                    if (j < arr.length) arr[j] = arr[i];
                    j--;
                    if (j < arr.length) arr[j] = arr[i]; //copy twice when hit '0'
                }
            }
        }
```



## [LintCode]31 · 数组划分

### 方法1：快速选择

```java
public int partitionArray(int[] nums, int k) {
    int l = 0, r = nums.length - 1;
    while (l <= r) {//出口条件是l = r +1
        //返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k
        //当nums[l]<k，说明l这个点不是分割点
        while (l <= r && nums[l] < k) {
            l++;
        }
        //当nums[r]>=k r点都不会是分隔点
        while (l <= r && nums[r] >= k) {
            r--;
        }
        //交换 nums[l] 和 nums[r]
        if (l < r) {
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
            l++;
            r--;
        }
    }
    return l;
}
```
