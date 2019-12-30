--�麯���ֳ�Ϊ�ۺϺ���,������ֵ,����ֻ�᷵��һ��ֵ
--�麯����������ѡ���б���ѯ��having���Ӿ�
--���к���,����һ��ֵ,���һ��ֵ

--��ѯ����Ա����нˮ�ܺ�
select sum(sal) from emp;
--�鿴�����ж�������¼
select count(*) from emp;
select deptno, count(*) from emp group by deptno having count(*) > 3;
--�ַ�����
-- concat():��ʾ�ַ���������,��ͬ��||
select concat('my name is ',ename) from emp;
-- initcap():���ַ���������ĸ��д
select initcap(ename) from emp;
-- upper():���ַ���ȫ��ת��Ϊ��д
select upper(ename) from emp;
-- lower():���ַ���ȫ��ת��ΪСд
select lower(ename) from emp;
-- lpad(),rpad():����ַ���
select lpad(ename,10,'*') from emp;
select rpad(ename,10,'*') from emp;
-- trim():ȥ���ո�
select trim(ename) from emp;
select ltrim(ename) from emp;
select rtrim(ename) from emp;
-- instr():����ָ���ַ�����λ��
select instr('ABCDABEEFA','A') from emp;
-- length():�鿴�ַ����ĳ���
select length(ename) from emp;
-- substr():��ȡ�ַ����Ĳ���
select substr(ename,0,2) from emp;
-- replace():�滻����
select replace('abcdabcdba','ab','12') from emp;

--��ֵ����
-- round():��С��������������Ĳ���,����ָ��С�����ֵ�λ��
select round(123.123,2) from dual;
select round(123.128,2) from dual;
-- trunc():�ض�����,����λ�����н�ȡ,���ǲ��������������Ĳ���
select trunc(123.128,2) from dual;
-- mod():ȡģ����
select mod(10,4) from dual;
-- ceil():����ȡ��
select ceil(12.12) from dual;
-- floor():����ȡ��
select floor(12.12) from dual;
-- abs():ȥ����ֵ
select abs(-100) from dual;
-- sign():��ȡ����ֵ
select sign(-2) from dual;
-- power():x��y����
select power(2,3) from dual;
-- ���ں���
-- sysdate:���ص�ǰϵͳʱ��
select sysdate from dual;
-- current_date:���ص�ǰϵͳʱ��
select current_date from dual;
-- add_months(),���ָ�����·�
select add_months(hiredate,2),hiredate from emp;
-- last_day�����������������·ݵ����һ��
select last_day(sysdate) from dual;
-- months_between():���������������·�
select months_between(sysdate,hiredate) from emp;
-- next_day():���ؽ����������ڼ�
select next_day(sysdate,'������') from dual;
-- localtimestamp:�������ڵ�ʱ���
select localtimestamp from dual;
select current_timestamp from dual;
-- extract():��ȡ�����е��ض�����
SELECT SYSDATE "date",
       EXTRACT(YEAR FROM SYSDATE) "year",
       EXTRACT(MONTH FROM SYSDATE) "month",
       EXTRACT(DAY FROM SYSDATE) "day",
       EXTRACT(HOUR FROM SYSTIMESTAMP) "hour",
       EXTRACT(MINUTE FROM SYSTIMESTAMP) "minute",
       EXTRACT(SECOND FROM SYSTIMESTAMP) "second"
  FROM dual;

--ת������
--��oracle�д�����ֵ����ʽת������ʽת��,��ʽת��ָ�����ַ�������ת��Ϊ��ֵ��������
--��ʽת��:
-- to_char:�������ֻ�������ת���ַ�����ʱ��,����Ҫ�涨��ʽ
select '999'+10 from dual;
-- date:to_char
select to_char(sysdate,'YYYY-MM-dd HH-mm-ss') from dual;
-- number:to_char
select to_char(123.456789,'9999')from dual;
select to_char(123.456789,'0000.00')from dual;
select to_char(123.456789,'$0000.00')from dual;
select to_char(123.456789,'L0000.00')from dual;
select to_char(123456789,'999,999,999,999')from dual;
-- to_date:ת��֮���ǹ̶��ĸ�ʽ
select to_date('2019-10-10 10:10:10','YYYY/MM/DD HH24/MI/SS') from dual;
-- to_number:ת������
select to_number('123,456,789','999,999,999') from dual;

--��ʾû���ϼ�����Ĺ�˾����
select ename,nvl(to_char(mgr),'BOSS') from emp where mgr is null;
--��ʾԱ����Ӷ����6���º���һ�������������
select ename,hiredate,next_day(add_months(hiredate,6),'������') from emp;

--��������
-- decode,case when
--����ͬ���ŵ���Ա��н,10������%10,20������20%,30������30%
select ename,
       sal,
       deptno,
       decode(deptno, 10, sal * 1.1, 20, sal * 1.2, 30, sal * 1.3)
  from emp;
select ename,
       sal,
       deptno,
       case deptno
         when 10 then
          sal * 1.1
         when 20 then
          sal * 1.2
         when 30 then
          sal * 1.3
       end
  from emp;
-----------------------------------------

create table test(
   id number(10) primary key,
   type number(10) ,
   t_id number(10),
   value varchar2(5)
);
insert into test values(100,1,1,'����');
insert into test values(200,2,1,'��');
insert into test values(300,3,1,'50');

insert into test values(101,1,2,'����');
insert into test values(201,2,2,'��');
insert into test values(301,3,2,'30');

insert into test values(102,1,3,'����');
insert into test values(202,2,3,'Ů');
insert into test values(302,3,3,'10');

select * from test;
/*����
�������ʾת��Ϊ
����      �Ա�     ����
--------- -------- ----
����       ��        50
*/
select max(decode(type, 1, value)) ����,
       max(decode(type, 2, value)) �Ա�,
       max(decode(type, 3, value)) ����
  from test group by t_id;

/*
�麯�������õ��麯����5��,һ�������,�麯����Ҫ��group by���ʹ��
�麯��һ������ѡ���б����having�����ж�
avg() ƽ��ֵ ֻ��������ֵ���͵�����
min() ��Сֵ �������κ�����
max() ���ֵ �������κ�����
count() ��¼�� ,�����ʱ���������ֵ����ǿ�ֵ
countһ��������ȡ���еļ�¼����,��ȡ������ʱ�����ʹ��*����
      ĳһ���������,��������ʹ�ô�����������,���Ǵ�����Ч�ʵĽǶ�
      ����,����ʹ�����ֻ���ĳһ���������,����Ҫʹ��*
sum() ��� ֻ��������ֵ���͵�����
*/
select avg(sal) from emp;
select min(sal) from emp;
select max(sal) from emp;
select count(sal) from emp;
select sum(sal) from emp;
-- group by,����ĳЩ��ͬ��ֵȥ���з������
/*
group by���з��������ʱ��,����ָ��һ���л��߶����,���ǵ�ָ����group by֮��
ѡ���б���ֻ�ܰ����麯����ֵ����group by����ͨ�ֶ�
*/
--��ÿ�����ŵ�ƽ��нˮ
select deptno,avg(sal) from emp group by deptno;
--��ƽ��нˮ����2000�Ĳ���
select deptno,avg(sal) from emp group by deptno having avg(sal)>2000;

select count(ename) from emp;
--�����¹�Ա�Ĺ���>2000����
select deptno,count(1)from emp where sal>2000 group by deptno;
--����нˮ���
select deptno,max(sal) from emp group by deptno;
--�������� ������С���������ҳ���,ȡ������
 

