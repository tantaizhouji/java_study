--DML:���ݿ��������
--��
--ɾ
--��

--��ʵ����Ŀ��,ʹ�������Ƕ�ȡ����,���ǲ������ݺ�ɾ������ͬ����Ҫ,���޸Ĳ�����Խ���
/*
�������:
  Ԫ��ֵ�Ĳ���
  ��ѯ����Ĳ���
*/
--������Ĳ��뷽ʽ
-- insert into tablename values(val1,val2,...)�������֮��û����,��ôֻ�ܽ����е��ж�����
-- insert into tablename(col1,col2,...) values(val1,val2...)����ָ������Щ���в�������

insert into emp values(2222,'haha','Clerk',7902,to_date('2019-11-2','YYYY-MM-dd'),1000,500,10);
--�򲿷��в������ݵ�ʱ�򣬲��������ĸ��в���Ͳ����,Ҫ��ѭ�������ʱ����Ĺ淶
insert into emp(empno,ename) values(3333,'wangwu');
select * from emp;

--�������������ʽ
--���Ʊ�ͬʱ���Ʊ�����,���Ḵ��Լ��
create table emp2 as select * from emp;
--���Ʊ�ṹ���ǲ����Ʊ�����,���Ḵ��Լ��
create table emp3 as select * from emp where 1=2;
--�����һ�����ϵ�����,�Ѽ����е��������ݶ���������Ļ�,Ч�����?һ����ʵ�ʵĲ�����,����һ��������,���������������

/*
ɾ������:
delete from tablename where condition
*/
--ɾ����������������
delete from emp2 where deptno=10;
select * from emp2;
--�����ű������ȫ�����
delete from emp2;
-- truncate,��delete��������,delete�ڽ���ɾ����ʱ�򾭹�����,��truncate����������,һ��ɾ����������ɾ��,���߱��ع��Ĳ���
--Ч�ʱȽϸ�,�������׷��������,���Բ�����ʹ��
truncate table emp2;

/*
�޸Ĳ���:
   update tablename set col1 = val1,col2=val2 where condition;
   ���Ը��»����޸�����������һ���л��߶����
*/
--���µ���
update emp2 set ename = 'leilei' where ename='SMITH';
--���¶���е�ֵ
update emp2 set job='teacher' where empno=7369;

/*
��ɾ�������ݿ�ĳ��ò���,�ڽ��в�����ʱ����Ҫ�����񡷵ı�֤,Ҳ����˵ÿ����pl/sql��ִ��sql���֮����Ҫ����commit�Ĳ���
�����÷ǳ��ؼ�:
       ����Ҫ��Ŀ����Ϊ������һ����
       ���ͬһ������,��ͬһ��ʱ��ֻ����һ���˷��ʣ��Ͳ���������ݴ��ҵ�����,���������ڵ���Ŀ��,������ǲ�������
       �������ʵ�ͬ�´����ľ������ݵĲ���ȫ,Ҳ���ǲ�һ��
       ���Ҫ��֤���ݵİ�ȫ,����Ҫ�ķ�ʽ���Ǽ����ķ�ʽ,MVCC
       
       ���������:
            ����������ݿ�����
            ����ʽ����
            �ֲ�ʽ����
        Ϊ�����Ч��,�п��ܶ����������ͬһ��������ִ��,��ô���п��ܲ��ֳɹ�,����ʧ��,����ע��������Ҫ����Ŀ���.
        select * from emp where id=7902 for update
        select * from emp where id=7902 lock in share mode
        ��ô����֤����Ļ�,��������,�����ظ���,�ö�
*/
