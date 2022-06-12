



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





```sql
SELECT name
FROM customer
WHERE referee_id is null
        OR referee_id!=2 
```

