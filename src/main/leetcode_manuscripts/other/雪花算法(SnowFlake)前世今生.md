## 雪花算法(SnowFlake)前世今生

### 前言

>  雪花的基本形状是六角形，但是大自然中却几乎找不出两朵完全相同的雪花，每一个雪花都拥有自己的独有图案，就象地球上找不出两个完全相同的人一样。许多学者用显微镜观测过成千上万朵雪花，这些研究最后表明，形状、大小完全一样和各部分完全对称的雪花，在自然界中是无法形成的 

### 基本思想

![1589813782573](D:\Dev\SrcCode\geek-algorithm-leetcode\src\main\leetcode_manuscripts\other\雪花算法(SnowFlake)前世今生.assets\1589813782573.png)
- 最高位是符号位，始终为0，不可用，如果是1的话，会变成负数，无意义
- 41位的时间序列，精确到毫秒级，2^41-1,41位的长度可以使用69年，（T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69）。时间位还有一个很重要的作用是可以根据时间进行排序。
- 10位的机器标识，10位的长度最多支持部署1024个节点，一般， 10 bit 里 5 个 bit 代表机房 id，5 个 bit 代表机器 id。意思就是最多代表 2 ^ 5 个机房（32 个机房），每个机房里可以代表 2 ^ 5 个机器（32 台机器） 
- 12位的计数序列号，序列号即一系列的自增id，可以支持同一节点同一毫秒生成多个ID序号，12位的计数序列号， 2 ^ 12 - 1 = 4096 ，支持每个节点每毫秒产生4096个ID序号。

### 实现逻辑

```java
public class SnowFlake {
    // 起始的时间戳
    private final static long START_STMP = 1577808000000L; //2020-01-01
    // 每一部分占用的位数，就三个
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5; //机器标识占用的位数
    private final static long DATACENTER_BIT = 5; //数据中心占用的位数
    // 每一部分最大值
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    // 每一部分向左的位移
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
    private long datacenterId; //数据中心
    private long machineId; //机器标识
    private long sequence = 0L; //序列号
    private long lastStmp = -1L; //上一次时间戳

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    //产生下一个ID
    public synchronized long nextId() {
        long currStmp = timeGen();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //if条件里表示当前调用和上一次调用落在了相同毫秒内，只能通过第三部分，序列号自增来判断为唯一，所以+1.
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大，只能等待下一个毫秒
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            //执行到这个分支的前提是currTimestamp > lastTimestamp，说明本次调用跟上次调用对比，已经不再同一个毫秒内了，这个时候序号可以重新回置0了。
            sequence = 0L;
        }

        lastStmp = currStmp;
        //就是用相对毫秒数、机器ID和自增序号拼接
        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = timeGen();
        while (mill <= lastStmp) {
            mill = timeGen();
        }
        return mill;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowFlake worker = new SnowFlake(1,1);
        for (int i = 0; i < 22; i++) {
            System.out.println(worker.nextId());
        }
    }
}
```

### REFERENCE
- [雪花算法](https://www.cnblogs.com/Hollson/p/9116218.html)

