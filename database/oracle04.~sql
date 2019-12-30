create table tmp(rq varchar2(10),shengfu varchar2(5));

insert into tmp values('2005-05-09','胜');
insert into tmp values('2005-05-09','胜');
insert into tmp values('2005-05-09','负');
insert into tmp values('2005-05-09','负');
insert into tmp values('2005-05-10','胜');
insert into tmp values('2005-05-10','负');
insert into tmp values('2005-05-10','负');
/*
            胜 负
2005-05-09  2  2
2005-05-10  1  2
*/
select rq,decode(shengfu,'胜',1),decode(shengfu,'负',2) from tmp;

select rq,
       count(decode(shengfu, '胜', 1)) 胜,
       count(decode(shengfu, '负', 2)) 负
  from tmp
 group by rq
 order by rq;
------------------------------------------------------------------
create table STUDENT_SCORE
(
  name    VARCHAR2(20),
  subject VARCHAR2(20),
  score   NUMBER(4,1)
);
insert into student_score (NAME, SUBJECT, SCORE) values ('张三', '语文', 78.0);
insert into student_score (NAME, SUBJECT, SCORE) values ('张三', '数学', 88.0);
insert into student_score (NAME, SUBJECT, SCORE) values ('张三', '英语', 98.0);
insert into student_score (NAME, SUBJECT, SCORE) values ('李四', '语文', 89.0);
insert into student_score (NAME, SUBJECT, SCORE) values ('李四', '数学', 76.0);
insert into student_score (NAME, SUBJECT, SCORE) values ('李四', '英语', 90.0);
insert into student_score (NAME, SUBJECT, SCORE) values ('王五', '语文', 99.0);
insert into student_score (NAME, SUBJECT, SCORE) values ('王五', '数学', 66.0);
insert into student_score (NAME, SUBJECT, SCORE) values ('王五', '英语', 91.0);
/*
1.得到类似下面的结果
姓名   语文  数学  英语

王五    89    56    89
*/
--至少4种方式下写出
--decode
select name 姓名,
       max(decode(subject, '语文', score)) 语文,
       max(decode(subject, '数学', score)) 数学,
       max(decode(subject, '英语', score)) 英语
  from student_score sc
 group by name;
--case when
select name 姓名,
       max(case subject
             when '语文' then
              score
           end) 语文,
       max(case subject
             when '数学' then
              score
           end) 数学,
       max(case subject
             when '英语' then
              score
           end) 英语
  from student_score sc
 group by name;
-- join
select sc.name,sc.score from student_score sc where sc.subject='语文';
select sc.name,sc.score from student_score sc where sc.subject='数学';
select sc.name,sc.score from student_score sc where sc.subject='英语';
select ss01.name 姓名, ss01.score 语文, ss02.score 数学, ss03.score 英语
  from (select sc.name, sc.score
          from student_score sc
         where sc.subject = '语文') ss01
  join (select sc.name, sc.score
          from student_score sc
         where sc.subject = '数学') ss02
    on ss01.name = ss02.name
  join (select sc.name, sc.score
          from student_score sc
         where sc.subject = '英语') ss03
    on ss01.name = ss03.name;
--  union all
select t.name, sum(t.语文), sum(t.数学), sum(t.英语)
  from (select ss01.name, ss01.score 语文, 0 数学, 0 英语
          from student_score ss01
         where ss01.subject = '语文'
        union all
        select ss02.name, 0 语文, ss02.score 数学, 0 英语
          from student_score ss02
         where ss02.subject = '数学'
        union all
        select ss03.name, 0 语文, 0 数学, ss03.score 英语
          from student_score ss03
         where ss03.subject = '英语') t
 group by t.name;
/*
2.有一张表，里面有3个字段：语文，数学，英语。其中有3条记录分别表示语文70分，数学80分，英语58分，请用一条sql语句查询出这三条记录并按以下条件显示出来（并写出您的思路）：  
   大于或等于80表示优秀，大于或等于60表示及格，小于60分表示不及格。  
       显示格式：  
       语文              数学                英语  
       及格              优秀                不及格    
*/
select 姓名,
       case
         when 语文 >= 80 then
          '优秀'
         when 语文 >= 60 then
          '及格'
         else
          '不及格'
       end 语文,
       case
         when 数学 >= 80 then
          '优秀'
         when 数学 >= 60 then
          '及格'
         else
          '不及格'
       end 数学,
       case
         when 英语 >= 80 then
          '优秀'
         when 英语 >= 60 then
          '及格'
         else
          '不及格'
       end 英语
  from (select name 姓名,
               max(decode(subject, '语文', score)) 语文,
               max(decode(subject, '数学', score)) 数学,
               max(decode(subject, '英语', score)) 英语
          from student_score sc
         group by name);

------------------------------------------------------------------
create table yj01(
       month varchar2(10),
       deptno number(10),
       yj number(10)
);
insert into yj01(month,deptno,yj) values('一月份',01,10);
insert into yj01(month,deptno,yj) values('二月份',02,10);
insert into yj01(month,deptno,yj) values('二月份',03,5);
insert into yj01(month,deptno,yj) values('三月份',02,8);
insert into yj01(month,deptno,yj) values('三月份',04,9);
insert into yj01(month,deptno,yj) values('三月份',03,8);

create table yjdept(
       deptno number(10),
       dname varchar2(20)
);
insert into yjdept(deptno,dname) values(01,'国内业务一部');
insert into yjdept(deptno,dname) values(02,'国内业务二部');
insert into yjdept(deptno,dname) values(03,'国内业务三部');
insert into yjdept(deptno,dname) values(04,'国际业务部');
/*
请用一个sql语句得出结果
从table1,table2中取出如table3所列格式数据，注意提供的数据及结果不准确，
只是作为一个格式向大家请教。
table1

月份mon 部门dep 业绩yj
-------------------------------
一月份      01      10
一月份      02      10
一月份      03      5
二月份      02      8
二月份      04      9
三月份      03      8

table2

部门dep      部门名称dname
--------------------------------
      01      国内业务一部
      02      国内业务二部
      03      国内业务三部
      04      国际业务部

table3 （result）

部门dep 一月份      二月份      三月份
--------------------------------------
      01      10                  
      02      10         8         
      03                 5        8
      04                          9

------------------------------------------
*/
select deptno,
       sum(decode(month, '一月份', yj)) 一月份,
       sum(decode(month, '二月份', yj)) 二月份,
       sum(decode(month, '三月份', yj)) 三月份
  from yj01
 group by deptno order by deptno;
