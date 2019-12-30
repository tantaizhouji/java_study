--关联查询
/*
select t1.c1,t2.c2 from t1,t2 where t1.c3 = t2.c4;
在进行连接的时候,可以使用等值连接,也可以使用非等值连接
*/
--查询需要将雇员的名称和部门的名称
select dname,ename from emp,dept where emp.deptno = dept.deptno;
--查询雇员名称已经直接的薪水等级
select e.ename, e.sal, sg.grade
  from emp e, salgrade sg
 where e.sal between sg.losal and sg.hisal;
--等值连接,两个表中包含相同的列名
--非等值连接,两个表中没有相同的列名,但是某一个列在另一个表的列的范围之中
--外连接
--需要将雇员表中的所有数据都进行显示,利用等值连接的话只会把关联的数据显示
--没有关联到的数据不会显示,此时需要外连接
--分类：左外连接(把左表的全部数据显示)和右外连接(把右表的全部数据显示)
select * from emp e,dept d where e.deptno = d.deptno; --等值连接
select * from emp e,dept d where e.deptno = d.deptno(+); --左外连接
select * from emp e,dept d where e.deptno(+) = d.deptno; --右外连接
--自连接,将一张表当成不同的表来看待,直接关联自己
--将雇员和他经理的名称查出来
select e.ename,m.ename from emp e,emp m where e.mgr = m.empno;
--笛卡尔积,当关联多张表,但是不指定连接条件的时候,会进行笛卡尔积
--关联后的总记录条数为M*n,一般不要使用
select * from emp e,dept d;

--92的表连接语法有什么问题???
--在92语法中,多张表的连接条件会放在where子句中,同时where需要对表进行条件过滤
--因此,相当于将过滤条件和连接条件揉到一起,太乱了,因此出现了99语法


--99语法
/*
CROSS JOIN
NATURAL JOIN
USING子句
ON子句
LEFT OUTER JOIN
RIGHT OUTER JOIN
FULL OUTER JOIN
Inner outer join
*/
-- cross join等同于92语法中的笛卡尔积
select * from emp cross join dept;
-- natural join 相当与是等值连接,但是注意,不需要写连接条件,会从两张表中找到相同的列做连接
--当两张表中不具有相同的列名的时候,会进行笛卡尔积操作,自然连接跟92语法的自连接没有任何关系
select * from emp e natural join dept d;
select * from emp e natural join salgrade sg;

-- on子句,可以添加任意的连接条件
--添加连接条件,相当于92语法中的等值连接
select * from emp e join dept d on e.deptno=d.deptno;
--相当于92语法中的非等值连接
select * from emp e join salgrade sg on e.sal between losal and hisal;

-- left outer join,会把左表中的全部数据正常显示,右表没有对应的数据直接显示空即可
select * from emp e left outer join dept d on e.deptno = d.deptno;
-- right outer join,会把右表中的全部数据正常显示,左表中没有对应的记录的话显示空即可
select * from emp e right outer join dept d on e.deptno=d.deptno;
-- full outer join,相当于左外连接和右外连接的合集
select * from emp e full outer join dept d on e.deptno=d.deptno;
-- inner outer join,两张表的连接查询,只会查询出有匹配记录的数据
select * from emp e inner join dept d on e.deptno=d.deptno;
select * from emp e join dept d on e.deptno=d.deptno;
-- using ,除了使用on表示连接条件之外,也可以使用using作为连接条件,此时连接条件的列不再归属于任何一张表
select * from emp e join dept d using(deptno);
select * from emp e join dept d on e.deptno=d.deptno;
--总结:两种语法的SQL语句没有任何限制,在公司中可以随意使用,但是建议使用99语法,不要使用92语法,SQL语句显得清楚明了

--检索雇员名字、所在单位、薪水等级
select e.ename, d.loc, sg.grade
  from emp e
  join dept d
    on e.deptno = d.deptno
  join salgrade sg
    on e.sal between sg.losal and sg.hisal;
/*
子查询:
    嵌套在其他sql语句中的完整sql语句,可以称之为子查询
分类:
    单行子查询
    多行子查询
*/
--有哪些人的薪水是在整个雇员的平均薪水之上
select ename, deptno, sal + nvl(comm, 0)
  from emp
 where sal + nvl(comm, 0)  > (select avg(sal + nvl(comm, 0)) from emp);
--要查在雇员中有哪些人是经理人
--1.查询所有的经理人编号
select distinct e.mgr from emp e;
--2.在雇员表中过滤这些标号即可
select * from emp e where e.empno in(select distinct e.mgr from emp e);

--求每个部门平均薪水的等级
--1.先求出部门的平均薪水
select deptno,avg(sal+nvl(comm,0)) avgsal from emp group by deptno;
--2.跟薪水登记表做关联,找出平均薪水的登记
select a.deptno, sg.grade, a.avgsal,sg.losal,sg.hisal
  from salgrade sg
  join (select deptno, avg(sal + nvl(comm, 0)) avgsal
          from emp
         group by deptno) a
    on a.avgsal between sg.losal and sg.hisal;

--1、求平均薪水最高的部门的部门编号
--求部门的平均薪水
select deptno,avg(e.sal) a from emp e group by e.deptno
--求平均薪水最高的部门
select max(v.a) mx from (select deptno,avg(e.sal) a from emp e group by deptno) v 
--求部门编号
select t.deptno
  from (select deptno, avg(e.sal) a from emp e group by e.deptno) t
 where t.a =
       (select max(v.a) m
          from (select deptno, avg(e.sal) a from emp e group by deptno) v);

select deptno
  from (select deptno, avg(e.sal) a from emp e group by deptno) e1
  join (select max(v.a) mx
          from (select deptno, avg(e.sal) a from emp e group by deptno) v) e2
    on e2.mx = e1.a;
--2、求部门平均薪水的等级
select deptno,avg(e.sal) from emp e group by deptno

select avge.deptno, sg.grade
  from salgrade sg
  join (select deptno, avg(e.sal) a from emp e group by deptno) avge
    on avge.a between sg.losal and sg.hisal;
--3、求部门平均的薪水等级
--求部门每个人的薪水等级
select from salgrade sg join emp e on e.sal between sg.losal and sg.hisal
--按照部门求平均等级
select s.deptno, avg(s.grade)
  from (select *
          from salgrade sg
          join emp e
            on e.sal between sg.losal and sg.hisal) s
 group by s.deptno;
--现在输出,limit,mysql中用来限制输出的,但是oracle中不是
--在oracle中,如果需要使用限制输出和分页的功能的话,必须要使用rownum,但是rownum不能直接使用,需要嵌套使用
--4、求薪水最高的前5名雇员
select *
  from (select * from emp e order by e.sal desc) t1
 where rownum <= 5;
--5、求薪水最高的第6到10名雇员
select t1.*, rownum
  from (select * from emp e order by e.sal desc) t1
 where rownum <= 10;
--使用rownum的时候必须要在外层添加嵌套,此时才能将rownum作为其中的一个列,然后再进行限制输出
select *
  from (select t1.*, rownum rn
          from (select * from emp e order by e.sal desc) t1
         where rownum <= 10) t
 where t.rn between 6 and 10;
