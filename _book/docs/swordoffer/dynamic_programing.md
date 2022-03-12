

# 02.算法

## 动态规划

## JZ42连续子数组的最大和

![](/imgs/swordoffer/JZ_42_title.png)

### 方法1.动态规划

```java
        public int FindGreatestSumOfSubArray(int[] array) {
            if (array == null || array.length == 0) return 0;
            int n = array.length;
            //f[i]表示以array[i-1]这个数为结尾的，连续子数组的最大和
            int[] f = new int[n + 1];
            int res = -101;
            for (int i = 1; i <= n; i++) {
                //当前这个数array[i-1]和前一个数的连续子数组形成一个最大的连续子数组：[...i-2,i-1]
                //当前这个数array[i-1]自己形成一个子数组[i-1]
                //两者取最大值，不断更新全局变量
                f[i] = Math.max(f[i - 1] + array[i - 1], array[i - 1]);
                res = Math.max(res, f[i]);
            }
            return res;
        }
```

## **JZ85** **连续子数组的最大和(二)**

![](/imgs/swordoffer/JZ_85_title.png)

### 方法1:朴素版DP

```java
        public int[] FindGreatestSumOfSubArray(int[] arr) {
            if (arr == null || arr.length == 0) return new int[]{};
            int n = arr.length;
            //f[i]表示以arr[i]这个数为结尾的，连续子数组的最大和
            int[] f = new int[n];
            f[0] = arr[0];
            int maxSum = f[0];
            int l = 0, r = 0;//当前的滑窗
            int resl = 0, resr = 0;//结果集滑窗
            for (int i = 1; i < n; i++) {
                r++;//当前的滑窗的右区间
                //更新这个f[i]的值
                f[i] = Math.max(f[i - 1] + arr[i], arr[i]);
                //如果区间发生变化，重新移动当前的滑窗
                if (f[i - 1] + arr[i] < arr[i]) {//重新
                    l = r;
                }
                //maxSum遇到更大的，更新resl resr区间
                //如果maxSum相同，更新一个最长的resl resr区间
                if (f[i] > maxSum || (f[i] == maxSum && (resr - resl + 1) < (r - l + 1))) {
                    maxSum = f[i];
                    resl = l;
                    resr = r;
                }
            }
            //组装结果
            int[] res = new int[resr - resl + 1];
            for (int i = resl; i <= resr; i++) {
                res[i - resl] = arr[i];
            }
            return res;
        }
```

### 方法2：空间压缩DP

```java
   public int[] FindGreatestSumOfSubArray(int[] arr) {
            if (arr == null || arr.length == 0) return new int[]{};
            int n = arr.length;
            int prev = arr[0], cur = 0;
            int maxSum = prev;
            int l = 0, r = 0;//当前的滑窗
            int resl = 0, resr = 0;//结果集滑窗
            for (int i = 1; i < n; i++) {
                r++;//当前的滑窗的右区间
                //更新这个f[i]的值
                cur = Math.max(prev + arr[i], arr[i]);
                //如果区间发生变化，重新移动当前的滑窗
                if (prev + arr[i] < arr[i]) {//重新
                    l = r;
                }
                //maxSum遇到更大的，更新resl resr区间
                //如果maxSum相同，更新一个最长的resl resr区间
                if (cur > maxSum || (cur == maxSum && (resr - resl + 1) < (r - l + 1))) {
                    maxSum = cur;
                    resl = l;
                    resr = r;
                }
                prev = cur;
            }
            //组装结果
            int[] res = new int[resr - resl + 1];
            for (int i = resl; i <= resr; i++) {
                res[i - resl] = arr[i];
            }
            return res;
        }
```





## **JZ63** **买卖股票的最好时机(一)**

![](/imgs/swordoffer/JZ_63_title.png)

### 方法2:空间压缩DP

```java
        //空间压缩DP
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            //f_0是当天手里无股票， f_1是当天手里有股票
            int f_0 = 0, f_1 = -prices[0];
            int n = prices.length;
            for (int i = 0; i < n; i++) {
                f_0 = Math.max(f_0, f_1 + prices[i]);
                f_1 = Math.max(f_1, -prices[i]);
            }
            return f_0;
        }
```







