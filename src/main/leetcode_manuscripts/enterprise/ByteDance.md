## two_pointers

### [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

```java

public int lengthOfLongestSubstring(String s) {
    int res = 0;
    int i = 0, j = 0, n = s.length();
    Set<Character> set = new HashSet<>();
    while(j<n&&i<n){
        if(!set.contains(s.charAt(j))){
            set.add(s.charAt(j++));
            res =Math.max(res,j-i);
        }else{
            set.remove(s.charAt(i++));
        }
    }
    return res;
}
```



```java
public int lengthOfLongestSubstring(String s) {
    int res = 0, l =0, r = 0, n = s.length();
    int[] arr =new int[128];
    while(r<n){
        l = Math.max(l,arr[s.charAt(r)]);
        res = Math.max(res,r-l+1);
        arr[s.charAt(r)] = 1+r++;
    }
    return res;
}
```