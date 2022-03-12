package com.frankcooper.other;

import com.frankcooper.struct.ListNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ExploreDemo {


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
//            handler.testOne();
            handler.testTwo();
        }


        private void testOne() {
            List<int[]> list = new ArrayList<int[]>() {{
                add(new int[]{1, 2});
            }};
            System.out.println(list.contains(new int[]{1, 2}));//False
        }

        private void testTwo() {
            List<User> list = new ArrayList<User>() {{
                add(new User(1, 10, "Tom"));
                add(new User(2, 15, "Jerry"));
            }};
            User u1 = new User(1, 10, "Tom");
            Assert.assertTrue(list.contains(u1));//false
        }


        //        @Data
//        @AllArgsConstructor
        static class User {
            Integer id;
            Integer age;
            String name;

            public User(Integer id, Integer age, String name) {
                this.id = id;
                this.age = age;
                this.name = name;
            }


        }
    }


    static class _2nd{
        public static void main(String[] args) {

        }
    }


    static class _3rd{
        public static void main(String[] args) {

        }
    }


    static class _4th{
        public static void main(String[] args) {

        }
    }
}
