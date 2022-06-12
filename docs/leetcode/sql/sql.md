



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







