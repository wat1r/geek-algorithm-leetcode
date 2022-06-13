



## [175. 组合两个表](https://leetcode.cn/problems/combine-two-tables/)

### 方法1：左连接

```sql
SELECT p.firstName,
         p.lastName ,
         a.city,
         a.state
FROM Person p
LEFT JOIN Address a
    ON p.personId = a.personId 
```



## [176. 第二高的薪水](https://leetcode.cn/problems/second-highest-salary/)

### 方法1：子查询+Limit子句

```java
//这个case如果不使用子查询,会出错，这个case下只有一条工资记录
{"headers":{"Employee":["id","salary"]},"rows":{"Employee":[[1,100]]}}
//return
{"headers": ["SecondHighestSalary"], "values": []}
//期望的返回
{"headers": ["SecondHighestSalary"], "values": [[null]]}
```

```sql
select
    (SELECT DISTINCT salary
    FROM Employee
    ORDER BY  salary DESC limit 1 offset 1) AS SecondHighestSalary
```

或者

```sql
select
    (SELECT DISTINCT salary
    FROM Employee
    ORDER BY  salary DESC limit 1 , 1) AS SecondHighestSalary
```



### 方法2：IFNULL函数

MySQL `IFNULL`函数是MySQL控制流函数之一，它接受两个参数，如果不是`NULL`，则返回第一个参数。 否则，`IFNULL`函数返回第二个参数。

两个参数可以是文字值或表达式。

以下说明了`IFNULL`函数的语法：

```sql
IFNULL(expression_1,expression_2);
SQL
```

如果`expression_1`不为`NULL`，则`IFNULL`函数返回`expression_1`; 否则返回`expression_2`的结果。

`IFNULL`函数根据使用的上下文返回字符串或数字。

如果要返回基于`TRUE`或`FALSE`条件的值，而不是`NULL`，则应使用[IF函数](http://www.yiibai.com/mysql/if-function.html)。

> [拓展链接](https://www.yiibai.com/mysql/ifnull.html)

```sql
SELECT ifnull(
    (SELECT DISTINCT salary
    FROM Employee
    ORDER BY  salary DESC limit 1 offset 1), null) AS SecondHighestSalary
```







## [177. 第N高的薪水](https://leetcode.cn/problems/nth-highest-salary/)

### 方法1:子查询+Limit子句

- 将176题推广到一般情况，先要重塑N的偏移值

```sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT BEGIN SET N = N-1; RETURN ( # Write your MySQL query statement below.SELECT 
    (SELECT DISTINCT salary
    FROM Employee
    ORDER BY  salary DESC LIMIT 1 offset N) AS NthHighestSalary ); END
```

## [178. 分数排名](https://leetcode.cn/problems/rank-scores/)

### 方法1：Count

- rank要加``，会报错
- 第二部分的`rank`，计算的是比当前去重后的scroe大

```sql
SELECT a.score ,
         
    (SELECT count(DISTINCT b.score)
    FROM Scores b
    WHERE b.score >=a.score) AS `rank`
FROM Scores a
ORDER BY  a.score DESC
```

### 方法2：rank_dense()

- Mysql8.0

[MySQL rank() over、dense_rank() over、row_number() over 用法介绍](https://blog.csdn.net/qq_41057885/article/details/109176014)

```sql
SELECT score ,
         dense_rank() over(
ORDER BY  score DESC ) AS "rank"
FROM scores;
```



## [180. 连续出现的数字](https://leetcode.cn/problems/consecutive-numbers/)

### 方法1：三表联查

```sql
SELECT DISTINCT a.num AS ConsecutiveNums
FROM Logs a , Logs b, Logs c
WHERE a.id = b.id-1
        AND b.id = c.id -1
        AND a.num = b.num
        AND b.num = c.num
```

### 方法2：开窗函数row_number()

- 行数-分组行号 = k
- 根据值和特点进行group by
- having筛选连续次数的条件
- 给出指定输出（e.g. 输出连续3次出现的数字，输出用户连续登录3天以上连续的次数、用户数……）

```sql
SELECT DISTINCT Num ConsecutiveNums FROM
    (SELECT *,
         ROW_NUMBER()
        OVER (PARTITION BY Num
    ORDER BY  Id) rownum, ROW_NUMBER()
        OVER (ORDER BY Id) id2
    FROM LOGS ) t
GROUP BY  (id2-rownum),Num
HAVING COUNT(*)>=3
```

### 方法3：变量

```sql
SELECT DISTINCT t.Num AS ConsecutiveNums
    FROM ( SELECT
        CASE @pre
        WHEN Num THEN
        @cnt := @cnt + 1
        ELSE @cnt := 1
        END AS cnt, @pre := Num, Num
    FROM Logs a, 
        (SELECT @cnt := 1,
         @pre := '') b ) t
    WHERE t.cnt >= 3
```

## [181. 超过经理收入的员工](https://leetcode.cn/problems/employees-earning-more-than-their-managers/)

### 方法1：联表查询

```sql
SELECT a.name Employee
FROM Employee a
LEFT JOIN Employee b ON a.managerId = b.Id
WHERE a.salary>=b.salary
```



## [182. 查找重复的电子邮箱](https://leetcode.cn/problems/duplicate-emails/)

### 方法1：group by+临时表

```sql
SELECT Email
FROM 
    (SELECT Email ,
        count(1) cnt
    FROM Person
    GROUP BY  Email) t
WHERE t.cnt > 1
```

### 方法2：group by+having

> where>group by>having>order by
>
>  温(where)哥(group by)华(having)ol(order by limit)

```sql
SELECT Email
FROM Person
GROUP BY  Email
HAVING count(Email ) > 1 
```



## [183. 从不订购的客户](https://leetcode.cn/problems/customers-who-never-order/)

### 方法1：not in

```sql
SELECT Name Customers
FROM Customers
WHERE Id NOT in
    (SELECT CustomerId
    FROM Orders)
```

### 方法2：联表查询

```sql
SELECT a.Name AS Customers
FROM Customers AS a
LEFT JOIN Orders AS b
    ON a.Id=b.CustomerId
WHERE b.CustomerId is null;
```

### 方法3：NOT EXISTS

```sql
SELECT name AS Customers
FROM customers
WHERE NOT EXISTS 
    (SELECT customerId
    FROM orders
    WHERE customerId = customers.id );
```





## [184. 部门工资最高的员工](https://leetcode.cn/problems/department-highest-salary/)

### 方法1：in

```sql
SELECT d.name AS 'Department', e.name AS 'Employee', Salary
FROM Employee AS e
JOIN Department AS d
    ON e.departmentId = d.id
WHERE (e.departmentId , salary) IN 
    (SELECT departmentId,
         MAX(salary)
    FROM Employee
    GROUP BY  departmentId ) 
```



## [185. 部门工资前三高的所有员工](https://leetcode.cn/problems/department-top-three-salaries/)

### 方法1：count

- 公司里前 3 高的薪水意味着有不超过 3 个工资比这些值大。
- 然后，我们需要把表 **Employee** 和表 **Department** 连接来获得部门信息。
- 查询e1表的员工姓名和薪资，使得Employee表里面比e1查出来的薪资高的最多只有2个（小于3），因此一张表是e1，另外还要从Employee表里查，也就是子查询中的e2，最终查出的结果就是前3高的薪水

```sql
SELECT d.name AS 'Department', e1.name AS 'Employee' ,e1.salary AS 'Salary'
FROM Employee e1
LEFT JOIN Department d
    ON e1.departmentId = d.id
WHERE 3 > 
    (SELECT count( DISTINCT e2.salary )
    FROM Employee e2
    WHERE e2.salary > e1.salary
            AND e1.departmentId = e2.departmentId )
```

### 方法2：dense_rank()

- 先对Employee表进行部门分组工资排名，再关联Department表查询部门名称，再使用WHERE筛选出排名小于等于3的数据（也就是每个部门排名前3的工资）。

```sql
SELECT B.Name AS Department,
         A.Name AS Employee,
         A.Salary
FROM 
    (SELECT DENSE_RANK()
        OVER (partition by DepartmentId
    ORDER BY  Salary desc) AS ranking,DepartmentId,Name,Salary
    FROM Employee) AS A
JOIN Department AS B
    ON A.DepartmentId=B.id
WHERE A.ranking<=3
```

## [196. 删除重复的电子邮箱](https://leetcode.cn/problems/delete-duplicate-emails/)

### 方法1：联表

- 需要找到其他记录中具有相同电子邮件地址的更大 ID。所以我们可以像这样给 `WHERE` 子句添加一个新的条件
- 已经得到了要删除的记录，所以我们最终可以将该语句更改为 `DELETE`

```sql
DELETE p1
FROM Person p1, Person p2
WHERE p1.Email = p2.Email
        AND p1.Id > p2.Id ;
```



## [197. 上升的温度](https://leetcode.cn/problems/rising-temperature/)

### 方法1：内连接

```sql
SELECT w2.id
FROM weather w1
JOIN weather w2
    ON datediff(w2.recordDate,w1.recordDate) =1
        AND w1.temperature < w2.temperature
```



## [511. 游戏玩法分析 I](https://leetcode.cn/problems/game-play-analysis-i/)

### 方法1：group by

```sql
SELECT player_id ,
        min(event_date) AS first_login
FROM Activity
GROUP BY  player_id
```





## [584. 寻找用户推荐人](https://leetcode.cn/problems/find-customer-referee/)

### 方法1：IS NULL

```sql
SELECT name
FROM customer
WHERE referee_id is null
        OR referee_id!=2 
```



## [586. 订单最多的客户](https://leetcode.cn/problems/customer-placing-the-largest-number-of-orders/)

### 方法1：ORDER BY

```sql
SELECT customer_number
FROM Orders
GROUP BY  customer_number
ORDER BY  count(1) DESC limit 0,1 
```

> 如果有多个订单数最多并列的用户呢?

- 查询出最值使用having子句匹配

```sql
SELECT
    customer_number
FROM
    Orders
GROUP BY customer_number
HAVING COUNT(*) = (
    SELECT 
        COUNT(customer_number) AS 'cnt' 
    FROM 
        Orders 
    GROUP BY customer_number 
    ORDER BY cnt DESC  
    LIMIT 1
    )
```

另使用dense_rank()函数

```sql
SELECT customer_number
FROM 
    (SELECT customer_number,
         dense_rank() over(order by count(order_number) desc) AS ranking
    FROM orders
    GROUP BY  customer_number) t
WHERE ranking = 1 
```

## [595. 大的国家](https://leetcode.cn/problems/big-countries/)

### 方法1：OR

```sql
SELECT name,
         population,
         area
FROM world
WHERE area >= 3000000
        OR population >= 25000000
```

### 方法2：UNION

```sql
SELECT name,
         population,
         area
FROM world
WHERE area >= 3000000
UNION
SELECT name,
         population,
         area
FROM world
WHERE population >= 25000000 
```

## [596. 超过5名学生的课](https://leetcode.cn/problems/classes-more-than-5-students/)

### 方法1：子查询

- distinct去重，同一门课中学生被重复计算

```sql
SELECT class
FROM 
    (SELECT class,
         COUNT(DISTINCT student) AS num
    FROM courses
    GROUP BY  class) AS temp_table
WHERE num >= 5 
```

#### 方法2：HAVING语句

```sql
SELECT class
FROM courses
GROUP BY  class
HAVING COUNT(DISTINCT student) >= 5 
```

## [607. 销售员](https://leetcode.cn/problems/sales-person/)

### 方法1：not in 

```sql
SELECT s.name
FROM salesperson s
WHERE s.sales_id NOT IN 
    (SELECT o.sales_id
    FROM orders o
    LEFT JOIN company c
        ON o.com_id = c.com_id
    WHERE c.name = 'RED') 
```



## [608. 树节点](https://leetcode.cn/problems/tree-node/)

- Root: 没有父节点
- Inner: 它是某些节点的父节点，且有非空的父节点
- Leaf: 除了上述两种情况以外的节点

### 方法1：三段式UNION

- ORDER BY放在最后

```sql
SELECT id,
         'Root' AS Type
FROM tree
WHERE p_id IS NULL
UNION
SELECT id,
         'Leaf' AS Type
FROM tree
WHERE id NOT IN 
    (SELECT DISTINCT p_id
    FROM tree
    WHERE p_id IS NOT NULL)
        AND p_id IS NOT NULL
UNION
SELECT id,
         'Inner' AS Type
FROM tree
WHERE id IN 
    (SELECT DISTINCT p_id
    FROM tree
    WHERE p_id IS NOT NULL)
        AND p_id IS NOT NULL
ORDER BY  id
```

### 方法2：CASE

```sql
SELECT a.id ,
        
    CASE
    WHEN a.id = 
    (SELECT id
    FROM tree
    WHERE p_id is null) THEN
    'Root'
    WHEN a.id IN 
    (SELECT p_id
    FROM tree) THEN
    'Inner'
    ELSE 'Leaf'
    END AS Type
FROM tree a
ORDER BY  a.id
```

### 方法3：IF NULL

```sql
SELECT a.id,
         IF(ISNULL(a.p_id),
         'Root', IF(a.id IN 
    (SELECT p_id
    FROM tree), 'Inner','Leaf')) Type
FROM tree a
ORDER BY  a.id
```



## [620. 有趣的电影](https://leetcode.cn/problems/not-boring-movies/)

### 方法1：MOD条件判断

```sql
SELECT *
FROM cinema
WHERE mod(id, 2) = 1
        AND description != 'boring'
ORDER BY  rating DESC
```





## [626. 换座位](https://leetcode.cn/problems/exchange-seats/)

### 方法1：CASE

```sql
SELECT
    (CASE
        WHEN MOD(id, 2) != 0 AND counts != id THEN id + 1
        WHEN MOD(id, 2) != 0 AND counts = id THEN id
        ELSE id - 1
    END) AS id,
    student
FROM
    seat,
    (SELECT
        COUNT(*) AS counts
    FROM
        seat) AS seat_counts
ORDER BY id ASC;
```

### 方法2：使用位操作和 COALESCE()

```sql
SELECT
    s1.id, COALESCE(s2.student, s1.student) AS student
FROM
    seat s1
        LEFT JOIN
    seat s2 ON ((s1.id + 1) ^ 1) - 1 = s2.id
ORDER BY s1.id
```





## [627. 变更性别](https://leetcode.cn/problems/swap-salary/)

### 方法1：CASE

```sql
UPDATE salary SET sex =
    CASE sex
    WHEN 'm' THEN
    'f'
    ELSE 'm' END
```

另

```sql
UPDATE salary SET sex=IF(sex='f','m','f') 
```

## [1050. 合作过至少三次的演员和导演](https://leetcode.cn/problems/actors-and-directors-who-cooperated-at-least-three-times/)

```sql
SELECT actor_id,
        director_id
FROM ActorDirector
GROUP BY  actor_id,director_id
HAVING count(*)>=3
```















