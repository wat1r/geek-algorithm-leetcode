## 状态压缩DP之N次操作后的最大分数和



### 代码1



```java
int[] nums = new int[]{3,4,6,8}
//下面的索引从 0到3 分别是 3 4 6 8
//二进制的时候是反的， 0011  0101  
//                  8643  8643
//其中上面的 0011 换算成10进制为3 表示的是 第1 和2 的bit位上的数字存在 也就是 3 4 这两个数
//其中上面的 0101 换算成10进制为5 表示的是 第1 和3 的bit位上的数字存在 也就是 3 6 这两个数
i:0,bin:0000,cnt:0
  j:0,k:1,st:3,st_bin:0011,if:false
  j:0,k:2,st:5,st_bin:0101,if:false
  j:0,k:3,st:9,st_bin:1001,if:false
  j:1,k:2,st:6,st_bin:0110,if:false
  j:1,k:3,st:10,st_bin:1010,if:false
  j:2,k:3,st:12,st_bin:1100,if:false
i:1,bin:0001,cnt:1
//...
i:4,bin:0100,cnt:1
i:5,bin:0101,cnt:2
  j:0,k:1,st:3,st_bin:0011,if:false// 看这一行的 i =5 二进制 0101 这个为False是因为这一轮选的j k在 0011 这个状态中不存在 (st & i) == st  -> 0101 & 0011 = 0001 (st)
  j:0,k:2,st:5,st_bin:0101,if:true // 看这一行的 i =5 二进制 0101 这个为True是因为这一轮选的j k在 0101 这个状态中恰好存在 (st & i) == st  -> 0101 & 0101 = 0101 (st)
  j:0,k:3,st:9,st_bin:1001,if:false
  j:1,k:2,st:6,st_bin:0110,if:false
  j:1,k:3,st:10,st_bin:1010,if:false
  j:2,k:3,st:12,st_bin:1100,if:false
//...
i:13,bin:1101,cnt:3
i:14,bin:1110,cnt:3
i:15,bin:1111,cnt:4
  j:0,k:1,st:3,st_bin:0011,if:true
// 看这一行的 i =15 二进制 1111 这个为True是因为这一轮选的j k在 0011 这个状态中存在 (st & i) == st  -> 1111 & 0011 = 0011 (st) 而这两个状态相减：
/*
 1111
-0011
-----
 1100 十进制是12 dp[12] = 2 
 而 j 和k 的数分别是 3 4 后面部分的结果是 gcd(3,4) *cnt /2 = 1*4 /2 =2  这一轮 dp[15] =4
*/
  j:0,k:2,st:5,st_bin:0101,if:true
// 看这一行的 i =15 二进制 1111 这个为True是因为这一轮选的j k在 0101 这个状态中存在 (st & i) == st  -> 1111 & 0101 = 0101 (st) 而这两个状态相减：
/*
 1111
-0101
-----
 1010 十进制是12 dp[10] = 4 
 而 j 和k 的数分别是 3 6 后面部分的结果是 gcd(3,6) *cnt /2 = 3*4 /2 =6  这一轮 dp[15] =10
*/
  j:0,k:3,st:9,st_bin:1001,if:true
// 看这一行的 i =15 二进制 1111 这个为True是因为这一轮选的j k在 1001 这个状态中存在 (st & i) == st  -> 1111 & 1001 = 1001 (st) 而这两个状态相减：
/*
 1111
-1001
-----
 0110 十进制是6 dp[6] = 2 
 而 j 和k 的数分别是 3 8 后面部分的结果是 gcd(3,8) *cnt /2 = 1*4 /2 =2  这一轮 dp[15] =10 (取最大值)
*/
  j:1,k:2,st:6,st_bin:0110,if:true
// 看这一行的 i =15 二进制 1111 这个为True是因为这一轮选的j k在 0110 这个状态中存在 (st & i) == st  -> 1111 & 0110 = 0110 (st) 而这两个状态相减：
/*
 1111
-0110
-----
 1001 十进制是9 dp[9] = 1 
 而 j 和k 的数分别是 4 6 后面部分的结果是 gcd(4,6) *cnt /2 = 2*4 /2 =4  这一轮 dp[15] =10 (取最大值)
*/
  j:1,k:3,st:10,st_bin:1010,if:true
// 看这一行的 i =15 二进制 1111 这个为True是因为这一轮选的j k在 1010 这个状态中存在 (st & i) == st  -> 1111 & 1010 = 1010 (st) 而这两个状态相减：
/*
 1111
-1010
-----
 0101 十进制是5 dp[5] = 3
 而 j 和k 的数分别是 4 8 后面部分的结果是 gcd(4,8) *cnt /2 = 4*4 /2 =8  这一轮 dp[15] =11 (取最大值)
*/
  j:2,k:3,st:12,st_bin:1100,if:true
//
```





```java
       public int maxScore(int[] nums) {
            int N = nums.length;//数组长度 以[3, 4, 6, 8]为例
            int[] dp = new int[1 << N];//dp[st] 表示st这个状态下，N次操作后的最大分数和后
            for (int i = 0; i < 1 << N; i++) {//枚举状态 从 0000 0001 ... 1111[表示全部数都在的]
                int cnt = count(i);//计算当前i中1的个数，1表示这个索引下这个数存在
//                System.out.printf("i:%d,bin:%s,cnt:%d\n", i, PrintUtils.toBinaryString(i, 4), cnt);
                if ((cnt & 1) == 1) continue;//奇数跳过 当1的个数是奇数个时，需要跳过，只有偶数个数才能做gcd
                for (int j = 0; j < N; j++) {//第1个数
                    for (int k = j + 1; k < N; k++) {//当前数后面开始枚举第2个数
                        //获取这个两个数组成的10进制的数，如j =0 , k =1
                        //1<<j = 0001 1<<1 = 0010
                        //0001 | 0010 = 0011 也就是十进制的3
                        int st = (1 << j) | (1 << k);
//                        System.out.printf("  j:%d,k:%d,st:%d,st_bin:%s,if:%s\n", j, k, st, PrintUtils.toBinaryString(st, 4), (st & i) == st);
                        if ((st & i) == st) {//i这个状态是否包含st这个状态，包含才有意义
                            dp[i] = Math.max(dp[i], dp[i - st] + gcd(nums[j], nums[k]) * cnt / 2);//cnt是当前1的个数 取这个状态转移来的之前的状态 i-st这个状态
                        }
                    }
                }
            }
            return dp[(1 << N) - 1];//- 优先于 <<  相当于取二进制位上各位都为1的结果 恰好是整个数组

        }
        //计算i的1的个数
        public int count(int i) {
            int ans = 0;
            while (i != 0) {
                ans += i & 1;
                i >>>= 1;
            }
            return ans;
        }

        //gcd
        public int gcd(int a, int b) {
//            System.out.printf("a:%d,b:%d\n", a, b);
            return b == 0 ? a : gcd(b, a % b);
        }
```



### 代码2

> 摘自@Knarf大佬

下面主要针对` cnt[i] = cnt[i - (i & (-i))] + 1` 做一点解读

```java
i:1,post:0001,i:0001,cnt[1]=1
i:2,post:0010,i:0010,cnt[2]=1
i:3,post:0001,i:0011,cnt[3]=2
i:4,post:0100,i:0100,cnt[4]=1
i:5,post:0001,i:0101,cnt[5]=2
i:6,post:0010,i:0110,cnt[6]=2
i:7,post:0001,i:0111,cnt[7]=3
i:8,post:1000,i:1000,cnt[8]=1
i:9,post:0001,i:1001,cnt[9]=2 //  1001 二进制抹掉低位1 得到 1000 也就是说1001 从1000 翻转来的 +1 得到二进制1的个数 cnt[9] = cnt[8] +1  = 1+1 =2
i:10,post:0010,i:1010,cnt[10]=2
i:11,post:0001,i:1011,cnt[11]=3
i:12,post:0100,i:1100,cnt[12]=2// 1100 二进制抹掉低位1 得到1000  也就是说1100 从0100翻转来的 +1 得到二进制1的个数 cnt[12] = cnt[4]+1 =  1+1 =2 
i:13,post:0001,i:1101,cnt[13]=3
i:14,post:0010,i:1110,cnt[14]=3
i:15,post:0001,i:1111,cnt[15]=4
```



```java
       public int maxScore(int[] nums) {
            int n = nums.length;
            int N = 1 << n;
            int[] dp = new int[N];
            int[] cnt = new int[N];
            for (int i = 1; i < N; i++) {
                cnt[i] = cnt[i - (i & (-i))] + 1;//记录i的二进制中 1的个数   (i & (-i))返回的是i这个二进制的最低位为1的权值，i- (i & (-i)) 相当于抹掉最低位的1
                System.out.printf("i:%d,post:%s,i:%s,cnt[%d]=%d\n", i, PrintUtils.toBinaryString((i & (-i)), 4), PrintUtils.toBinaryString(i, 4), i, cnt[i]);
                if ((cnt[i] & 1) == 1) continue;//
                // System.out.println(i);
                for (int j = 0; (1 << j) < i; j++) { //枚举状态
                    if (((1 << j) & i) == 0) continue;//如果j不在i中，没意义
                    for (int k = j + 1; (1 << k) < i; k++) {
                        if (((1 << k) & i) == 0) continue;//如果k不在i中，没意义
                        // System.out.println(String.format("%d,%d,%d",i,j,k));
                        //i - (1 << k) - (1 << j) i这个状态中抹掉j k这两个状态   (cnt[i] >> 1)是当前的1的数量 /2
                        dp[i] = Math.max(dp[i], dp[i - (1 << k) - (1 << j)] + (cnt[i] >> 1) * gcd(nums[j], nums[k]));
                    }
                }
            }
            // System.out.println(Arrays.toString(cnt));
            // System.out.println(Arrays.toString(dp));
            return dp[N - 1]; //返回st中 都位1 的状态的值
        }

        int gcd(int a, int b) {
            while (b > 0) {
                int c = a % b;
                a = b;
                b = c;
            }
            return a;
        }
```



