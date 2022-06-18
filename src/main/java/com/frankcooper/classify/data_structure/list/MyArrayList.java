package com.frankcooper.classify.data_structure.list;


public class MyArrayList {


    private Object[] data;//Object类型的数组
    private int size;//数组内的元素个数

    public MyArrayList(int capacity) {
        data = new Object[capacity];//初始化
    }

    public int size() {
        return size;
    }

    public MyArrayList() {
        this(4);//初始容量为10
    }


    public void add(Object obj) {
        data[size++] = obj;
        //如果元素满了，进行扩容
        expandCapacity();
    }

    //
    public Object get(int index) {
        validateIndex(index);
        return data[index];
    }


    public void remove(int index) {
        validateIndex(index);
        //当前元素的[index+1...]挪到[index...]
        int target = size - index - 1;
        if (target > 0) {
            System.arraycopy(data, index + 1, data, index, target);
            data[--size] = null;
        }
    }


    public void remove(Object obj) {
        for (int i = 0; i < size; i++) {
            if (get(i).equals(obj)) {
                remove(i);
                data[--size] = null;
            }
        }

    }


    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("out of index");
        }
    }

    public void add(int index, Object obj) {
        validateIndex(index);
        expandCapacity();
        //[index...] 挪到 [index+1...] 共size-index个元素
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = obj;
        size++;
    }


    private void expandCapacity() {
        if (size == data.length) {
            //创建一个新的数组，将老数组的元素拷贝到新数组，新数组赋给老数组
            Object[] tmp = new Object[2 * size + 1];
            System.arraycopy(data, 0, tmp, 0, data.length);
            data = tmp;
        }
    }


    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.remove(2);
        list.add("F");


    }


}
