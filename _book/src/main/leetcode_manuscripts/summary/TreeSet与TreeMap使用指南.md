# TreeSet与TreeMap使用指南





## 2.TreeMap

```java
public class TreeMap<K,V>
extends AbstractMap<K,V>
implements NavigableMap<K,V>, Cloneable, Serializable
```

TreeMap中的元素默认按照keys的自然排序排列。

（对Integer来说，其自然排序就是数字的升序；对String来说，其自然排序就是按照字母表排序）

### 构造函数

TreeMap()：创建一个空TreeMap，keys按照自然排序

```java
TreeMap<Integer, String> treeMap = new TreeMap<>();
```

TreeMap(Comparator comparator)：创建一个空TreeMap，按照指定的comparator排序

```java
TreeMap<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
map.put(3, "val");
map.put(2, "val");
map.put(1, "val");
map.put(5, "val");
map.put(4, "val");
System.out.println(map); // {5=val, 4=val, 3=val, 2=val, 1=val}
```

TreeMap(Map m)：由给定的map创建一个TreeMap，keys按照自然排序

```java
Map<Integer, String> map = new HashMap<>();
map.put(1, "val");
...
TreeMap<Integer, String> treeMap = new TreeMap<>(map);
TreeMap(SortedMap m)：由给定的有序map创建TreeMap，keys按照原顺序排序
```

### 常用方法

#### 增添元素

- `V put(K key, V value)`：将指定映射放入该`TreeMap`中
- `V putAll(Map map)`：将指定`map`放入该`TreeMap`中

#### 删除元素

- `void clear()`：清空TreeMap中的所有元素
- `V remove(Object key)`：从TreeMap中移除指定key对应的映射

#### 修改元素

- `V replace(K key, V value)`：替换指定`key`对应的`value`值
- `boolean replace(K key, V oldValue, V newValue)`：当指定`key`的对应的`value`为指定值时，替换该值为新值

#### 查找元素

- `boolean containsKey(Object key)`：判断该`TreeMap`中是否包含指定`key`的映射
- `boolean containsValue(Object value)`：判断该`TreeMap`中是否包含有关指定`value`的映射
- `Map.Entry<K, V> firstEntry()`：返回该`TreeMap`的第一个（最小的）映射
- `K firstKey()`：返回该TreeMap的第一个（最小的）映射的`key`
- `Map.Entry<K, V> lastEntry()`：返回该`TreeMap`的最后一个（最大的）映射
- `K lastKey()`：返回该`TreeMap`的最后一个（最大的）映射的`key`
- `v get(K key)`：返回指定`key`对应的`value`
- `SortedMap<K, V> headMap(K toKey)`：返回该`TreeMap`中严格小于指定key的映射集合
- `SortedMap<K, V> subMap(K fromKey, K toKey)`：返回该`TreeMap`中指定范围的映射集合（大于等于`fromKey`，小于`toKey`）
- **`Object ceilingKey（Object key）`**：返回大于或等于给定键的最小键，如果没有这样的键则返回null。
- **`Object higherKey（Object key）`：**返回严格大于指定键的最小键。
- **`Map.Entry<K,V>	higherEntry(K key) `**一个键-值映射关系，它与严格大于给定键的最小键关联；如果不存在这样的键，则返回 null。
- 
   **`Map.Entry<K,V>	ceilingEntry(K key) `**一个键-值映射关系，它与大于等于给定键的最小键关联；如果不存在这样的键，则返回 null。

#### 遍历方式

- for循环

```java
for (Map.Entry entry : treeMap.entrySet()) {
      System.out.println(entry);
}
```

#### 迭代器循环

```java
Iterator iterator = treeMap.entrySet().iterator();
while (iterator.hasNext()) {
      System.out.println(iterator.next());
}
```

#### 补充：如何选择合适的Map

HashMap可实现快速存储和检索，但其缺点是其包含的元素是无序的，这导致它在存在大量迭代的情况下表现不佳。
LinkedHashMap保留了HashMap的优势，且其包含的元素是有序的。它在有大量迭代的情况下表现更好。
TreeMap能便捷的实现对其内部元素的各种排序，但其一般性能比前两种map差。
LinkedHashMap映射减少了HashMap排序中的混乱，且不会导致TreeMap的性能损失。





## Python

其中：数组a是升序排序的有序数组

bisect_right()

```python
             描述：在数组a中找到x应该插入的索引，如果a中已经有了元素x，那么在右边找到其插入索引。
             返回值：应该插入的位置
```

bisect_left()

```python
            描述：在数组a中找到x应该插入的索引，如果a中已经有了元素x，那么在左边找到其插入索引。
            返回值：应该插入的位置
```

insort_right()

                 描述：在数组a中插入x,如果x存在，那么将x插入在右边
                 返回值：无

insort_left()

                 同上，右改为左即可

insort():

                 通过实验结果来看该方法和insort_right()是一样的.

//https://qa.1r1g.com/sf/ask/206151221/






## Reference

- https://www.jianshu.com/p/e11fe1760a3d