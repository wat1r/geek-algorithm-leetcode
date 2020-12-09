## 状态压缩之每个元音包含偶数次的最长子字符串[Tiger Shark]

![desert-5773182_640](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\greedy\state\状态压缩之每个元音包含偶数次的最长子字符串[Tiger Shark].assets\desert-5773182_640.jpg)

![image-20201209091922124](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\greedy\state\状态压缩之每个元音包含偶数次的最长子字符串[Tiger Shark].assets\image-20201209091922124.png)





![image-20201209091942492](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\greedy\state\状态压缩之每个元音包含偶数次的最长子字符串[Tiger Shark].assets\image-20201209091942492.png)

- 对5个元音字母`aeiou`做成一个`bit`, `a`在最高位，`u`在最低位，
  - 如果此位置上为0，表示该元音字符出现的次数是偶数次
  - 如果此位置上为1，表示该元音字符出现的次数是奇数次

```java
0: 00000 //表示5个元音字符出现的次数均是偶数次
1: 00001 //最低位u这个字符出现了奇数次，其他均为偶数次
2: 00010 //o这个字符出现了奇数次，其他均为偶数次
3: 00100 //i 这个字符出现了奇数次，其他为偶数次
... 
31:11111 //aeiou均出现了奇数次
```

![image-20201209092748717](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\greedy\state\状态压缩之每个元音包含偶数次的最长子字符串[Tiger Shark].assets\image-20201209092748717.png)

- 需要说明的是，这里的状态只记录，出现与否，不记录出现的次数，即**只在乎有没有，不在于有多少**
  - 如上图的 `0`位置，即`[0...0]`这个区间，出现的元音字符有一个为`e`，此时的状态`01000`
  - 当来到位置`3`时，即`[0...3]`这个区间，出现的元音字符有三个为`eee`,此时的状态也为`01000`
  - 对比上面的的状态，可以发现，虽然上述两个状态均是`01000`，但是表示的位置却不相同，这是因为**只在乎有没有，不在于有多少**
- 一个重要的性质：**奇数减奇数等于偶数，偶数减偶数等于偶数**
- 下面是记录遍历过程中的状态:

```java
status:01000,i:0 //e 出现 元音字符e 
status:01000,i:1 //l 对其没有影响，还是出现元音字符e
status:00000,i:2 //e 抵消掉前面的e  即01000 ^ 01000 = 00000 这次为偶数次， e出现了2次
status:01000,i:3 //e 出现了奇数次3次 这时候要想以当前e结尾的字符，到前面只能上探到i=1的位置，即[1...3]，因为如果到i=0位置，会有3个e，e出现了奇数次
status:01000,i:4 //t 此字符对前面的没有影响，直接扩大即可
status:01000,i:5
status:01100,i:6
status:01100,i:7
status:01000,i:8
status:01000,i:9
status:01010,i:10
status:01010,i:11
status:01000,i:12
status:01000,i:13
status:01010,i:14
status:00010,i:15
status:00010,i:16
```

> 位运算基础知识

- 异或（ ^ ）每一位进行比较，相同为0，不同为1（^ -4 = -3）

```java
  1 0 0 1 1 -->(19)[10]
^ 1 1 0 0 1 -->(25)[10]
-----------------------------
  0 1 0 1 0 -->(10)[10]
```

- 左移（ << ） 整体左移，右边空出位补零，左边位舍弃 （-4 << 1 = -8）

```java
int a = 8;
a << 3;
移位前：0000 0000 0000 0000 0000 0000 0000 1000 -->(8)[10]
移位后：0000 0000 0000 0000 0000 0000 0100 0000 -->(64)[10]  相当于 X 2^3
```

### 方法1：

```java
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        //大小为32的数组pos，记录当前状态status出现的最早的的位置
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c){
                case 'u': status ^= (1 << 0);break;
                case 'o': status ^= (1 << 1);break;
                case 'i': status ^= (1 << 2);break;
                case 'e': status ^= (1 << 3);break;
                case 'a': status ^= (1 << 4);break;
                default:break;
            }
            if (pos[status] >= 0) ans = Math.max(ans, i + 1 - pos[status]);
            else pos[status] = i + 1;
        }
        return ans;
    }
```

- 对于处理边界与初始化处理不同

### 方法2：

```java
    public int findTheLongestSubstring(String s) {
        Integer INF = Integer.MAX_VALUE;
        int[] pre=new int[1<<5];
        Arrays.fill(pre,INF);
        pre[0]=-1;
        int size=s.length(),status=0,ans=0;
        for(int i=0;i<size;i++){
            char ch=s.charAt(i);
            switch(ch){
                case 'a':status^=1<<0;break;
                case 'e':status^=1<<1;break;
                case 'i':status^=1<<2;break;
                case 'o':status^=1<<3; break;
                case 'u':status^=1<<4;break;
            }
            if(pre[status]==INF){
                pre[status]=i;
            }else{
                ans=Math.max(ans,i-pre[status]);
            }
        }
        return ans;
    }

```

- 注意此方法的写法，`INF`的初始值，需要做一个`pre[0]=-1;`的处理，如`aa`这个字符，如果走到`idx=1`的时候，要是依据正常的处理，这时候处理的长度结果为1，但是其实答案是2

