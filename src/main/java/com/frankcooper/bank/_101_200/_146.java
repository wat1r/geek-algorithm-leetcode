package com.frankcooper.bank._101_200;


import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2020/9/4
 * @Author Frank Cooper
 * @Description
 */
public class _146 {


    class LRUCache {
        DoubleLinkedNode head, tail;//Node的收尾节点
        int capacity;//容量
        Map<Integer, DoubleLinkedNode> cache;//<k,v> k是key，v是key生成的node

        class DoubleLinkedNode {
            int key, value;//k,v
            DoubleLinkedNode pre, next;//前驱接节点，后继节点

            /**
             * 构造方法
             *
             * @param key
             * @param value
             */
            public DoubleLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public DoubleLinkedNode() {
            }
        }


        /**
         * 将节点加入到双向链表的开头的位置
         */
        public void addFirst(DoubleLinkedNode node) {
            node.pre = head;//1.当前节点的前驱结点指向head节点
            node.next = head.next;//2.当前节点的后继节点指向head的后继节点

            head.next.pre = node;//3.head节点的后继节点的前驱结点指向当前节点
            head.next = node;//4.head的后继节点指向当前节点
        }

        /**
         * 移除一个普通的节点
         *
         * @param node
         */
        public void removeNode(DoubleLinkedNode node) {
            DoubleLinkedNode next = node.next;
            DoubleLinkedNode pre = node.pre;
            pre.next = next;
            next.pre = pre;
        }

        /**
         * 弹出最末尾的节点并将改节点返回
         *
         * @return
         */
        public DoubleLinkedNode popLast() {
            DoubleLinkedNode last = tail.pre;
            removeNode(last);
            return last;
        }

        /**
         * 将当前节点移动到最头部位置
         *
         * @param node
         */
        public void moveToHead(DoubleLinkedNode node) {
            removeNode(node);
            addFirst(node);
        }

        /**
         * 初始化
         *
         * @param capacity
         */
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.head = new DoubleLinkedNode();
            this.tail = new DoubleLinkedNode();
            this.head.next = tail;
            this.tail.pre = head;

        }

        public int get(int key) {
            if (!cache.containsKey(key)) return -1;
            DoubleLinkedNode node = cache.get(key);
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DoubleLinkedNode node = cache.get(key);
            if (node == null) {
                node = new DoubleLinkedNode(key, value);
                addFirst(node);
                cache.put(key, node);
                if (cache.size() > capacity) {
                    DoubleLinkedNode last = popLast();
                    cache.remove(last.key);
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }
    }


    static class _1st {
        class LRUCache {


            int capacity;
            Map<Integer, DoubleLinkedNode> cache;
            DoubleLinkedNode head;
            DoubleLinkedNode tail;


            class DoubleLinkedNode {
                int k, v;
                DoubleLinkedNode prev, next;

                public DoubleLinkedNode(int k, int v) {
                    this.k = k;
                    this.v = v;
                }

                public DoubleLinkedNode() {
                }
            }


            private void removeNode(DoubleLinkedNode node) {
                DoubleLinkedNode next = node.next;
                DoubleLinkedNode prev = node.prev;
                prev.next = next;
                next.prev = prev;
            }


            private void addFirst(DoubleLinkedNode node) {
                node.prev = head;
                node.next = head.next;

                head.next.prev = node;
                head.next = node;

            }


            private DoubleLinkedNode popLast() {
                DoubleLinkedNode node = tail.prev;
                removeNode(node);
                return node;
            }


            private void moveToHead(DoubleLinkedNode node) {
                removeNode(node);
                addFirst(node);

            }


            public LRUCache(int capacity) {
                this.capacity = capacity;
                this.cache = new HashMap<>();
                this.head = new DoubleLinkedNode();
                this.tail = new DoubleLinkedNode();
                head.next = tail;
                tail.prev = head;
            }

            public int get(int key) {
                if (!cache.containsKey(key)) return -1;
                DoubleLinkedNode node = cache.get(key);
                moveToHead(node);
                return node.v;
            }

            public void put(int key, int value) {
                DoubleLinkedNode node = cache.get(key);
                if (node == null) {
                    node = new DoubleLinkedNode(key, value);
                    addFirst(node);
                    cache.put(key, node);
                    if (cache.size() > capacity) {
                        DoubleLinkedNode lastNode = popLast();
                        cache.remove(lastNode.k);
                    }
                } else {
                    node.v = value;
                    moveToHead(node);
                }
            }
        }
    }


    static class _2nd {
        class LRUCache {

            DoubleLinkedNode head;
            DoubleLinkedNode tail;
            int capacity;
            Map<Integer, DoubleLinkedNode> cache;

            public LRUCache(int capacity) {
                this.capacity = capacity;
                head = new DoubleLinkedNode();
                tail = new DoubleLinkedNode();
                head.next = tail;
                tail.prev = head;
                cache = new HashMap<>();
            }

            public int get(int key) {
                if (!cache.containsKey(key)) return -1;
                DoubleLinkedNode node = cache.get(key);
                moveToHead(node);
                return node.value;
            }

            public void put(int key, int value) {
                DoubleLinkedNode node = cache.get(key);
                if (node == null) { //node为空
                    node = new DoubleLinkedNode(key, value);
                    addFirst(node);
                    cache.put(key, node);
                    if (cache.size() > capacity) {
                        DoubleLinkedNode last = popLast();
                        cache.remove(last.key);
                    }
                } else { //node不为空
                    node.value = value;
                    moveToHead(node);
                }


            }


            private void addFirst(DoubleLinkedNode node) {
                node.prev = head;
                node.next = head.next;

                head.next.prev = node;
                head.next = node;
            }

            private void remove(DoubleLinkedNode node) {
                DoubleLinkedNode prev = node.prev;
                DoubleLinkedNode next = node.next;
                prev.next = next;
                next.prev = prev;
            }

            private DoubleLinkedNode popLast() {
                DoubleLinkedNode last = tail.prev;
                remove(last);
                return last;
            }

            private void moveToHead(DoubleLinkedNode node) {
                remove(node);
                addFirst(node);
            }
        }


        class DoubleLinkedNode {
            int key, value;
            DoubleLinkedNode prev, next;

            public DoubleLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public DoubleLinkedNode() {
            }
        }
    }

}
