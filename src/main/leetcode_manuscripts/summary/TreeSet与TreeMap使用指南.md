# TreeSet与TreeMap使用指南

## 1.TreeSet

#### 将元素插入TreeSet

- add() - 将指定的元素插入集合
- addAll() - 将指定集合的所有元素插入集合

例如，

```java
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {

        TreeSet<Integer> evenNumbers = new TreeSet<>();

        // 使用 the add() 方法
        evenNumbers.add(2);
        evenNumbers.add(4);
        evenNumbers.add(6);
        System.out.println("TreeSet: " + evenNumbers);

        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(1);

        // 使用 the addAll() 方法
        numbers.addAll(evenNumbers);
        System.out.println("新的TreeSet: " + numbers);
    }
}
```

**输出结果**

```
TreeSet: [2, 4, 6]
新的TreeSet: [1, 2, 4, 6]
```

#### 访问TreeSet元素

要访问树集的元素，我们可以使用iterator()方法。为了使用这种方法，我们必须导入java.util.Iterator包。例如，

```java
import java.util.TreeSet;
import java.util.Iterator;

class Main {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(2);
        numbers.add(5);
        numbers.add(6);
        System.out.println("TreeSet: " + numbers);

        // 调用 iterator() 方法
        Iterator<Integer> iterate = numbers.iterator();
        System.out.print("TreeSet使用迭代器: ");
        //访问元素
        while(iterate.hasNext()) {
            System.out.print(iterate.next());
            System.out.print(", ");
        }
    }
}
```

**输出结果**

```java
TreeSet: [2, 5, 6]
TreeSet使用迭代器: 2, 5, 6,
```

### 删除元素

- remove() - 从集合中删除指定的元素
- removeAll() - 从集合中删除所有元素

例如，

```java
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(2);
        numbers.add(5);
        numbers.add(6);
        System.out.println("TreeSet: " + numbers);

        // 使用remove() 方法
        boolean value1 = numbers.remove(5);
        System.out.println("5被删除了? " + value1);

        // 使用removeAll() 方法
        boolean value2 = numbers.removeAll(numbers);
        System.out.println("是否删除了所有元素? " + value2);
    }
}
```

**输出结果**

```
TreeSet: [2, 5, 6]
5被删除了? true
是否删除了所有元素? true
```

### 导航方法

因为TreeSet类实现了NavigableSet，所以它提供了各种方法来导航树集的元素。

#### 1. first()和last()方法

- first() - 返回集合的第一个元素
- last() - 返回集合的最后一个元素

例如，

```java
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(2);
        numbers.add(5);
        numbers.add(6);
        System.out.println("TreeSet: " + numbers);

        // 使用 first() 方法
        int first = numbers.first();
        System.out.println("第一个数字: " + first);

        // 使用 last() 方法
        int last = numbers.last();
        System.out.println("最后一个数字: " + last);
    }
}
```

**输出结果**

```java
TreeSet: [2, 5, 6]
第一个数字: 2
最后一个数字: 6
```

#### 2. ceiling()，floor()，higher()和lower()方法

- **higher(element)** - 返回大于指定元素(element)的最小元素。
- **lower(element)** - 返回小于指定元素(element)的最大元素。
- **ceiling(element)** - 返回大于指定元素(element)的那些元素中的最小元素。如果传递的元素(element)存在于树集中，则返回作为参数传递的元素(element)。
- **floor(element)** - 返回小于指定元素(element)的元素中最大的元素。如果传递的元素(element)存在于树集中，则返回作为参数传递的元素(element)。

例如，

```java
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(2);
        numbers.add(5);
        numbers.add(4);
        numbers.add(6);
        System.out.println("TreeSet: " + numbers);

        // 使用 higher()
        System.out.println("使用 higher: " + numbers.higher(4));

        // 使用 lower()
        System.out.println("使用 lower: " + numbers.lower(4));

        // 使用 ceiling()
        System.out.println("使用 ceiling: " + numbers.ceiling(4));

        // 使用 floor()
        System.out.println("使用 floor: " + numbers.floor(3));

    }
}
```

**输出结果**

```java
TreeSet: [2, 4, 5, 6]
使用 higher: 5
使用 lower: 2
使用 ceiling: 4
使用 floor: 2
```

#### 3. pollfirst()和pollLast()方法

- pollFirst() - 返回并从集合中删除第一个元素
- pollLast() - 返回并从集合中删除最后一个元素

例如，

```java
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(2);
        numbers.add(5);
        numbers.add(4);
        numbers.add(6);
        System.out.println("TreeSet: " + numbers);

        // 使用 pollFirst()
        System.out.println("删除第一个元素: " + numbers.pollFirst());

        // 使用 pollLast()
        System.out.println("删除最后一个元素: " + numbers.pollLast());

        System.out.println("新的TreeSet: " + numbers);
    }
}
```

**输出结果**

```java
TreeSet: [2, 4, 5, 6]
删除第一个元素: 2
删除最后一个元素: 6
新的TreeSet: [4, 5]
```

#### 4. headSet()，tailSet()和subSet()方法

##### headSet(element,booleanValue)

headSet()方法返回指定元素（作为参数传递）之前的树集的所有元素。

booleanValue参数是可选的。默认值为false。

如果booleanValue的值为true，则该方法返回指定元素之前的所有元素，包括指定元素。

例如，

```java
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(2);
        numbers.add(5);
        numbers.add(4);
        numbers.add(6);
        System.out.println("TreeSet: " + numbers);

        // 使用 headSet()使用默认的布尔值
        System.out.println("使用不带布尔值的headSet: " + numbers.headSet(5));

        // 使用 headSet()使用指定的布尔值
        System.out.println("将headSet与布尔值一起使用: " + numbers.headSet(5, true));
    }
}
```

**输出结果**

```java
TreeSet: [2, 4, 5, 6]
使用不带布尔值的headSet: [2, 4]
将headSet与布尔值一起使用: [2, 4, 5]
```

##### tailSet(element,booleanValue)

tailSet()方法返回包含指定元素的指定元素（作为参数传递）之后的树集的所有元素。

booleanValue参数是可选的。默认值为true。

如果false作为a传递booleanValue，则该方法将返回指定后的所有元素，element而不包括指定的element。

例如，

```java
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(2);
        numbers.add(5);
        numbers.add(4);
        numbers.add(6);
        System.out.println("TreeSet: " + numbers);

        // 使用 tailSet()使用默认的布尔值
        System.out.println("tailSet()使用默认的布尔值: " + numbers.tailSet(4));

        // 使用 tailSet() with specified boolean value
        System.out.println("tailSet()带有布尔值: " + numbers.tailSet(4, false));
    }
}
```

**输出结果**

```java
TreeSet: [2, 4, 5, 6]
使用 tailSet()使用默认的布尔值: [4, 5, 6]
tailSet()带有布尔值: [5, 6]
```

##### subSet(e1,bv1,e2,bv2)

subSet()方法返回e1和e2之间的所有元素，包括e1。

bv1和bv2是可选参数。  bv1的默认值为true，bv2的默认值为false。

如果将false作为bv1传递，则该方法返回e1和e2之间的所有元素，而不包括e1。

如果将true作为bv2传递，则该方法返回e1和e2之间的所有元素，包括e1。

例如，

```java
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(2);
        numbers.add(5);
        numbers.add(4);
        numbers.add(6);
        System.out.println("TreeSet: " + numbers);

        // 使用 subSet() with default boolean value
        System.out.println("subSet()使用默认布尔值: " + numbers.subSet(4, 6));

        // 使用 subSet() 使用指定的布尔值
        System.out.println("subSet()使用指定的布尔值: " + numbers.subSet(4, false, 6, true));
    }
}
```

**输出结果**

```java
TreeSet: [2, 4, 5, 6]
subSet()使用默认布尔值: [4, 5]
subSet()使用指定的布尔值: [5, 6]
```









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

- [TreeSet与TreeMap使用指南](https://blog.csdn.net/wat1r/article/details/124831320)

