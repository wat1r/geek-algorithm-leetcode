> 







## 最小表示法

> 找出字符串S的循环同构串中字典序最小的那个

![](https://wat1r-1311637112.cos.ap-shanghai.myqcloud.com/imgs/20220530224453.png)

```c++
#include <iostream>
#include <algorithm>

using namespace std;
const int N = 1e5 + 10;
int n;
char s[N];

int get_min(char *s)
{
    n = strlen(s + 1);
    for (int i = 1; i <= n; i++)
    {
        s[n + i] = s[i];
    }
    int i = 1, j = 2, k = 0;
    while (i <= n && j <= n)
    {
        for (k = 0; k < n && s[i + k] == s[j + k]; k++)
            ;
        s[i + k] > s[j + k] ? i = i + k + 1 : j = j + k + 1;
        if (i == j)
        {
            j++;
        }
    }
    return min(i, j);
}
```



### [796. 旋转字符串](https://leetcode.cn/problems/rotate-string/)

> 最小表示法的思路：

- 1.当前字符复制一倍，单链成环

- 2.初始化指针i =0 ,j =1 ,匹配长度k=0

- 3.比较`s[i+k]`与`s[j+k]`的大小

  - `s[i+k]`=`s[j+k]`时k++
  - `s[i+k]`>`s[j+k]`时i=i+k+1
  - `s[i+k]`<`s[j+k]`时j=j+k+1

  - 如果i=j时，表明在同一个位置上，需要岔开一位，让j++

- 4.重复上面的过程，取到最小值即min(i,j)

> 本题的思路

- 拿到两个字符的s和goal的最小表示法下的字符串，即字典序最小的那个，如果是一样的，说明s可以翻转得到goal

```java
//最小表示法
public boolean rotateString(String s, String goal) {
    if (s.length() != goal.length()) return false;
    return getMin(s).equals(getMin(goal));
}


public String getMin(String s) {
    int n = s.length();
    s = s + s;
    int i = 0, j = 1, k = 0;
    while (i < n && j < n) {
        for (k = 0; k < n && s.charAt(i + k) == s.charAt(j + k); k++) ;
        if (s.charAt(i + k) > s.charAt(j + k)) {
            i = i + k + 1;
        } else {
            j = j + k + 1;
        }
        if (i == j) {
            j++;
        }
    }
    int t = Math.min(i, j);
    return s.substring(t, t + n);
}
```



## [158.项链](https://www.acwing.com/problem/content/160/)

```java
static class Main {

    static Main main = new Main();

    public static void main(String[] args) {
        main.process();
    }


    private void process() {
        Scanner in = new Scanner(System.in);
        String a = in.next(), b = in.next();
        String aa = getMin(a), bb = getMin(b);
        if (aa.equals(bb)) {
            System.out.println("Yes");
            System.out.println(aa);
        } else {
            System.out.println("No");
        }

    }

    public String getMin(String s) {
        int n = s.length();
        s = s + s;
        char[] ch = s.toCharArray();
        int i = 0, j = 1;
        while (i < n && j < n) {
            int k = 0;//while的写法把k放在内侧
            while (k < n && ch[i + k] == ch[j + k]) k++;
            if (ch[i + k] > ch[j + k]) {
                i += k + 1;
            } else {
                j += k + 1;
            }
            if (i == j) j++;
        }
        int t = Math.min(i, j);
        return s.substring(t, t + n);
    }
}
```



## [899. 有序队列](https://leetcode.cn/problems/orderly-queue/)

- 当k=1时，即求字符串的最小表示法

- 当k>1时，每次可以调整开头的的至少两个字符的位置，如bazzz->  bzzza -> zzzab  ...->abzzz，对于在排序算法中，值需要交换相邻的两个字符可以实现序列有序，所以这种情况下只需要字符串排序即可

- 当 K = 2 时，可以发现，我们能够交换字符串中任意两个相邻的字母。具体地，设字符串 S 为 S[1], S[2], ..., S[i], S[i + 1], ..., S[N]，我们需要交换 S[i] 和 S[j]。首先我们依次将 S[i] 之前的所有字符依次移到末尾，得到

  S[i], S[i + 1], ..., S[N], S[1], S[2], ..., S[i - 1]

  随后我们先将 S[i + 1] 移到末尾，再将 S[i] 移到末尾，得到

  S[i + 2], ..., S[N], S[1], S[2], ..., S[i - 1], S[i + 1], S[i]

  最后将 S[i + 1] 之后的所有字符依次移到末尾，得到

  S[1], S[2], ..., S[i - 1], S[i + 1], S[i], S[i + 2], ..., S[N]

  这样就交换了 S[i] 和 S[i + 1]，而没有改变其余字符的位置。

  当我们可以交换任意两个相邻的字母后，就可以使用冒泡排序的方法，仅通过交换相邻两个字母，使得字符串变得有序。因此当 K = 2 时，我们可以将字符串移动得到最小的字典序。

  当 K > 2 时，我们可以完成 K = 2 时的所有操作。

```java

        public String orderlyQueue(String s, int k) {
            if (k == 1) return getMin(s);
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            return String.valueOf(ch);
        }

        public String getMin(String s) {
            int n = s.length();
            s = s + s;
            int i = 0, j = 1;
            while (i < n && j < n) {
                int p = 0;
                while (p < n && s.charAt(i + p) == s.charAt(j + p)) p++;
                if (s.charAt(i + p) > s.charAt(j + p)) {
                    i += p + 1;
                } else {
                    j += p + 1;
                }
                if (i == j) j++;
            }
            int q = Math.min(i, j);
            return s.substring(q, q + n);
        }
```











1163

> https://www.bilibili.com/video/av64366938
