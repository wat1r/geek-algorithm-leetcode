package com.frankcooper.other;

import com.frankcooper.utils.PrintUtils;

import java.util.*;

public class TestOne {

    static class _1st {
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

//        test1();
            test2();
        }


        private static void test1() {
            int state = 29;
            PrintUtils.toBinaryString(state, 8);
        }


        private static void test2() {
            Float s = new Float(0.1f);
            Float t = new Float(0.1f);
            Double u = new Double(0.1);
//        System.out.println(s.equals(t));
//        System.out.println(u.equals(s));
            int[][] arr = new int[5][];
            System.out.println(arr.length);//5
            System.out.println(arr[0].length);//NPE
        }

        private static void test3() {
//            // 第一段：不使用泛型的方式
//            List a1 = new ArrayList();
//            a1.add(new Object());
//            a1.add(new Integer(1));
//            a1.add(new String("a1"));
//
//            // 第二段：把a1赋值给List<Object>类型的a2，看似a2与a1没有区别
//            List<Object> a2 = a1;
//            a2.add(new Object());
//            a2.add(new Integer(2));
//            a2.add(new String("a2"));
//            // 但是如果尝试把一个带有其它类型泛型的b2赋值给a2，则会编译报错
//            List<String> b2 = new ArrayList<>();
//            // 编译报错，这也是List与List<Object>的区别
//            a2 = b2;
//
//            // 第三段：把a1赋值给List<Integer>类型的a3，赋值过程没有编译报错，主要为了向前兼容（泛型jdk1.5之后才出现）
//            List<Integer> a3 = a1;
//            a3.add(new Integer(3));
//            // java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.Integer
//            Integer integer = a3.get(0);
//            // 编译报错，不允许add非Integer类型
//            a3.add(new Object());
//            a3.add(new String("a3"));
//
//            // 第四段：把a1赋值给List<?>类型的a4
//            List<?> a4 = a1;
//            a4.remove(0);
//            a4.clear();
//            a4.add(null);
//            // 编译出错，不允许add非null的数据
//            a4.add(new Object());
        }
    }

}
