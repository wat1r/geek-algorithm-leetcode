package com.frankcooper.bank._501_600;

import java.util.*;

import org.junit.Assert;

public class _599 {

    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
        }

        public String[] findRestaurant(String[] list1, String[] list2) {
            //map k:list1的string v:string的索引，因为题目中没有指出来list1的string是否有重复，忽略了重复的情况
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < list1.length; i++) map.put(list1[i], i);
            //结果集list
            List<String> list = new ArrayList<>();
            //索引和的最小值，根据数据范围取
            int minn = 2005;
            //遍历list2
            for (int i = 0; i < list2.length; i++) {
                //list2中有这个string
                if (map.containsKey(list2[i])) {
                    int j = map.get(list2[i]);
                    //case1:比minn还要小，重新更新
                    if (i + j < minn) {
                        minn = i + j;
                        list.clear();
                        list.add(list2[i]);
                    } else if (i + j == minn) {//case2:和minn相等，添加
                        list.add(list2[i]);
                    }
                }
            }
            //收集结果
            String[] res = new String[list.size()];
            for (int i = 0; i < res.length; i++) res[i] = list.get(i);
            return res;
        }


    }

    static class _2nd {
        public static void main(String[] args) {
            _2nd handler = new _2nd();
        }
    }


    static class _3rd {
        public static void main(String[] args) {
            _3rd handler = new _3rd();
        }
    }

    static class _4th {
        public static void main(String[] args) {
            _4th handler = new _4th();
        }
    }
}
