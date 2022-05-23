package com.frankcooper.other;

import com.frankcooper.utils.PrintUtils;

public class TestOne {

    public static void main(String[] args) {
        // `is_to_hive` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否导入hive, 0: 不导入，1:导入',
        //  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:未审核,, 2:审核通过,4:审核后修改',
        //  `has_create` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:没有建表;1:已经建表;2:建表后修改',
        //  `is_to_hbase` tinyint(4) NOT NULL DEFAULT '0',
        //  `is_to_cassandra` tinyint(4) NOT NULL DEFAULT '0',
        //  `is_to_elasticsearch` tinyint(4) NOT NULL DEFAULT '0',
        //  `is_to_hive_orc` tinyint(4) NOT NULL DEFAULT '0',
        //  `cs_create` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'cassandra 0:没有建表;1:已经建表;2:建表后修改',
        //  `es_create` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'es 0:没有建表;1:已经建表;2:建表后修改',
        //  `hb_create` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'hbase 0:没有建表;1:已经建表;2:建表后修改',
        //  `is_to_phoenix` tinyint(4) NOT NULL DEFAULT '0',
        //  `px_create` tinyint(4) NOT NULL DEFAULT '0',
        //  `hive_orc_create` tinyint(4) NOT NULL DEFAULT '0',
        //  `is_to_kudu` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'kudu导入',
        //  `kd_create` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'kudu表是否创建',


        //import_status
        //hive :           00不导入  01导入  10扩展  11扩展
        //elasticsearch    ...


        //storage_status
        //hive :            00 没有建表  01已经建表  10建表后修改  11扩展的状态
        //elasticsearch     00 没有建表  01已经建表  10建表后修改  11扩展的状态
        //hbase             00 没有建表  01已经建表 10建表后修改
        //kudu              00 没有建表  01已经建表 10建表后修改
        //cassandra         00 没有建表  01已经建表 10建表后修改
        //phoenix           00 没有建表  01已经建表 10建表后修改
        //hive_orc          00 没有建表  01已经建表 10建表后修改
        //低位到高位  hive -> ... -> phoenix
        //00 00 00 00 10 01 10
        //      11

        int c = 3;
        int d = 0;
        int f = 0;
        while (c > 0) {
            d |= ((c & 1) << (8 + f));
            c >>= 1;
            f++;
        }
//        int base = 38;//000000100110
//        System.out.println(d);
//        int e = d | base;
//        System.out.println(e);
//        PrintUtils.toBinaryString(e, 20);
//
//
//        int result = PrintUtils.transformBinStr2Int("000000100110");
//        System.out.println(result);
//        int a = 0;
//        int i = 0;
//        int j = 0;
//        while (base > 0) {
//            a |= ((base & 1) << j);
//            i++;
//            base >>= 1;
//            j++;
//            if (i % 2 == 0) {
//                System.out.println(a);
//                a = 0;
//                j = 0;
//            }
//        }


        int bit = 0;

        int candidate = 1010;


//        PrintUtils.toBinaryString()

        test1();
    }


    private static void test1() {
        int state = 29;
        PrintUtils.toBinaryString(state, 8);
    }

}
