# Java_dev_first
##20个最佳的Java集合框架面试题目

#1 为什么Map接口不继承Collection 接口？

· Set是无序集合，并且不允许重复的元素
· List是有序的集合，并且允许重复的元素
· 而Map是键值对
· 它被视为是键的set和值的set的组合
· Map被设计为键值对的集合，所以不需要继承Collection 接口

#2 HashMap和Hashtable之间的区别？

· 同步或线程安全
· Null键和Null值
· 迭代值
· 默认容量大小

#3 omparable 和 comparator的不同之处？

· comparable接口实际上是出自java.lang包
· 它有一个 compareTo(Object obj)方法来将objects排序
· comparator接口实际上是出自 java.util 包
· 它有一个compare(Object obj1, Object obj2)方法来将objects排序

#4 如何对Object的list排序？

· 对objects数组进行排序，我们可以用Arrays.sort()方法
· 如果要对objects的集合进行排序，需要使用Collections.sort()方法

#5 fail-fast 与 fail-safe 之间的区别？

· Fail fast快速地报告任何的failure。无论何时任何一个问题都会引发 fail fast系统fails
· 在Java Fail fast 迭代器中，迭代objects集合有时会出现并发修改异常，出现这种情况有2个原因
· 如果一个线程正在迭代一个集合，而另一个线程同时试图修改这个集合
· 在调用remove()方法后，如何我们还试图去修改集合object

#6 Iterator、ListIterator 和 Enumeration的区别？

· Enumeration接口在Java1.2版本开始有，所以Enumeration是合法规范的接口
· Enumeration使用elements()方法
· Iterator对所有Java集合类都有实现
· Iterator使用iterator方法
· Iterator只能往一个方向前进
· ListIterator仅仅对List类型的类实现了
· ListIterator使用listIterator（）方法

#7 Java 中 Set 与 List 有什么不同?

· Set是一个不允许重复元素存在的集合
· Set没有索引
· Set仅仅允许一个null值
· Set有类：HashSet、LinkedHashMap、TreeSet
· List有索引
· List允许N个null值
· List可以按插入顺序显示
· List有类：Vector、ArrayList、LinkedList

#8 arraylist 与 vector 的区别?

· Vector 在Java的第一个版本就引入了，也就是说vector是一个合法规范的类
· ArrayList在Java1.2版本引入的，是Java 集合框架的组成部分
· Vector是同步的
· ArrayList是不同步的

#9 什么类实现了List接口？

· ArrayList
· LinkedList
· Vector

#10 什么类实现了Set接口？

· HashSet
· LinkedHashSet
· TreeSet

#11 如何保证一个集合线程安全？

· Vector, Hashtable, Properties 和 Stack 都是同步的类，所以它们都线程安全的，可以被使用在多线程环境中
· 使用Collections.synchronizedList(list)) 方法，可以保证list类是线程安全的
· 使用java.util.Collections.synchronizedSet()方法可以保证set类是线程安全的

#12 是否可以往 TreeSet 或者 HashSet 中添加 null 元素？

· 可以往 hashset 中添加一个 null
· TreeSet 也允许一个 null值

#13 解释下Collection的接口继承关系？


#14 Iterator符合哪个设计模式？

· Iterator 设计模式

#15 HashSet 实现了哪个数据结构？

· HashSet 内部实现了hashmap

#16 为什么 Collection 不能继承 Cloneable 和 Serializable？

· List和Set唯一继承 Collection 接口
· SortedMap 继承了 Map 接口

#17 hashCode() 和 equals() 方法的重要性？如何在Java中使用它们？

· hashCode() 和 equals() 方法定义在"object"类中
· 如果equals() 方法在比较2个对象时返回true，那么hashCode()的返回值必须得一样

#18 array 和 arraylist 的区别？

· Array类似object集合类型，大小固定
· Arraylist是同质和异质元素的集合

#19 什么是 Properties 类？

· Properties 是Hashtable的子类。它被用于维护值的list，其中它们的键、值都是String类型

#20 如何将一个字符串转换为arraylist?

· 使用 arrayList.toArray() 方法
