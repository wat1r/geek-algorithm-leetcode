package com.frankcooper.platform.leetcode.bank._401_500;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @Date 2020/9/7
 * @Author Frank Cooper
 * @Description
 */
public class _460 {

    static _460 handler = new _460();


    public static void main(String[] args) {
        handler.testOne();

    }

    public void testOne() {
        _1st first = new _1st();
        _1st.LFUCache cache = new _1st.LFUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

        System.out.println(cache.cacheMap.keySet());

        int res1 = cache.get(1);
        System.out.println(res1);

        cache.put(3, 3);
        System.out.println(cache.cacheMap.keySet());

        int res2 = cache.get(2);
        System.out.println(res2);

        int res3 = cache.get(3);
        System.out.println(res3);

        cache.put(4, 4);
        System.out.println(cache.cacheMap.keySet());

        int res4 = cache.get(1);
        System.out.println(res4);

        int res5 = cache.get(3);
        System.out.println(res5);

        int res6 = cache.get(4);

    }

    static class _1st {


        static class Node {
            int key, value;
            int freq = 1;

            public Node() {
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }


        static class LFUCache {

            Map<Integer, Node> cacheMap;
            Map<Integer, LinkedHashSet<Node>> freqMap;
            int capacity;
            int size;
            int minFreq;

            public LFUCache(int capacity) {
                cacheMap = new HashMap<>();
                freqMap = new HashMap<>();
                this.capacity = capacity;
            }

            public int get(int key) {
                Node node = cacheMap.get(key);
                if (node == null) return -1;
                helper(node);
                return node.value;
            }


            public void put(int key, int value) {
                if (this.capacity == 0) return;
                Node currNode = cacheMap.get(key);
                //1.当前节点存在，更新value值并且更新freq信息
                if (currNode != null) {
                    currNode.value = value;
                    helper(currNode);
                } else {
                    //2.节点不存在
                    //2.1.容量达到上限时，移除节点
                    if (size == this.capacity) {
                        Node removeNode = removeNode();
                        cacheMap.remove(removeNode.key);
                        size--;
                    }
                    //2.2.添加新的节点
                    Node node = new Node(key, value);
                    cacheMap.put(key, node);
                    addNode(node);
                    size++;

                }
            }

            private void helper(Node node) {
                //从LinkedHashSet 移除当前节点
                int currFreq = node.freq;
                LinkedHashSet<Node> currSet = freqMap.get(currFreq);
                currSet.remove(node);
                //如果当前的freq与最小的相等且当前的set已经没有了，minFreq++
                if (minFreq == currFreq && currSet.size() == 0) {
                    minFreq++;
                }
                node.freq++;
                //获取新的freq所在的set，并将node加入
                LinkedHashSet<Node> targetSet = freqMap.get(node.freq);
                if (targetSet == null) {
                    targetSet = new LinkedHashSet<>();
                    freqMap.put(node.freq, targetSet);
                }
                targetSet.add(node);
            }

            //移除最小频次下的set中访问时间最早的节点,并返回该移除节点
            private Node removeNode() {
                LinkedHashSet<Node> currSet = freqMap.get(minFreq);
                Node removeNode = currSet.iterator().next();
                currSet.remove(removeNode);
                return removeNode;
            }

            //添加一个节点,因为该节点是不存在的新节点，从1开始，并将minFreq再次置为1
            private void addNode(Node node) {
                LinkedHashSet<Node> currSet = freqMap.get(1);
                if (currSet == null) {
                    currSet = new LinkedHashSet<>();
                    freqMap.put(1, currSet);
                }
                currSet.add(node);
                minFreq = 1;
            }
        }

    }


    class _2nd {

        class Node {
            int key, value;
            int freq = 1;
            Node prev, next;

            public Node() {
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }


        class DoubleLinkedList {
            Node head;
            Node tail;

            public DoubleLinkedList() {
                head = new Node();
                tail = new Node();
                head.next = tail;
                tail.prev = head;
            }

            public void removeNode(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            //表头位置添加新节点
            public void addNode(Node node) {
                node.next = head.next;
                head.next.prev = node;
                head.next = node;
                node.prev = head;
            }
        }


        class LFUCache {
            Map<Integer, Node> cacheMap;
            Map<Integer, DoubleLinkedList> freqMap;
            int size;
            int capacity;
            int minFreq;

            public LFUCache(int capacity) {
                cacheMap = new HashMap<>();
                freqMap = new HashMap<>();
                this.capacity = capacity;
            }

            public int get(int key) {
                Node currNode = cacheMap.get(key);
                if (currNode == null) return -1;
                helper(currNode);
                return currNode.value;
            }


            public void put(int key, int value) {
                if (capacity == 0) return;
                Node currNode = cacheMap.get(key);
                if (currNode != null) {
                    currNode.value = value;
                    helper(currNode);
                } else {
                    if (size == capacity) {
                        DoubleLinkedList minFreqDLList = freqMap.get(minFreq);
                        Node removeNode = minFreqDLList.tail.prev;
                        cacheMap.remove(removeNode.key);
                        minFreqDLList.removeNode(removeNode);
                        size--;
                    }
                    Node node = new Node(key, value);
                    cacheMap.put(key, node);
                    DoubleLinkedList currDLList = freqMap.get(1);
                    if (currDLList == null) {
                        currDLList = new DoubleLinkedList();
                        freqMap.put(1, currDLList);
                    }
                    currDLList.addNode(node);
                    size++;
                    minFreq = 1;
                }

            }

            private void helper(Node node) {
                int currFreq = node.freq;
                DoubleLinkedList currDLList = freqMap.get(currFreq);
                currDLList.removeNode(node);
                if (currFreq == minFreq && currDLList.head.next == currDLList.tail) {
                    minFreq++;
                }
                node.freq++;
                currDLList = freqMap.get(node.freq);
                if (currDLList == null) {
                    currDLList = new DoubleLinkedList();
                    freqMap.put(node.freq, currDLList);
                }
                currDLList.addNode(node);
            }
        }


    }


}
