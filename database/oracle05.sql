/*
CREATE [OR REPLACE] VIEW view
[(alias[, alias]...)]
AS subquery
[WITH READ ONLY];
*/
--�����ͨ�û���һ�δ�����ͼ,��ʾû��Ȩ��,Ҫʹ�ù���Աȥ�޸�Ȩ��
grant create view to scott; 
--������ͼ
create view v_emp as select * from emp where deptno=30;
--��ͼ��ʹ��
select * from v_emp;
--����ͼ���������,ִ�гɹ�֮��,��Ҫ�ύ����,��ɫ��ʾ�ύ����,��������Ч����ɫ��ʾ�ع�����,�����ݻָ�ԭ״̬
--ִ�к�δ�ύ����,ֻ���ڵ�ǰ�Ự��Ч
insert into v_emp(empno,ename) values(1111,'zhangsan');
select * from emp;
--����������ͼ�Ƿ�ֻ����ͼ�Ļ�,����ͨ����ͼ����в�������,�����ֻ����ͼ,�򲻿��Բ�������
create view v_emp2 as select * from emp with read only;
select * from v_emp2;
--ֻ����ͼֻ�ṩ��ѯ������,�޷�������ɾ�Ĳ���
insert into v_emp2(empno,ename) values(1234,'lisi');
--ɾ����ͼ
drop view v_emp2;
--��ɾ����ͼ�е����ݵ�ʱ��,���������Դ�ڶ������,���ʱ����ȫ������ɾ��,ֻ��ɾ��һ�����е�����

--����Ҫ��ƽ��нˮ�ĵȼ���͵Ĳ���,���Ĳ���������ʲô,������ȫʹ���Ӳ�ѯ
--1.����ƽ��нˮ
select e.deptno,avg(e.sal) avs from emp e group by e.deptno; 
--2.����ƽ��нˮ�ĵȼ�
select t.deptno, sg.grade
  from salgrade sg
  join (select e.deptno, avg(e.sal) avs from emp e group by e.deptno) t
    on t.avs between sg.losal and sg.hisal;
--3.����ƽ��нˮ�ĵȼ���͵Ĳ���
select t.deptno
  from (select t.deptno, sg.grade
          from salgrade sg
          join (select e.deptno, avg(e.sal) avs from emp e group by e.deptno) t
            on t.avs between sg.losal and sg.hisal) t
 where t.grade in
       (select min(t.grade)
          from (select t.deptno, sg.grade
                  from salgrade sg
                  join (select e.deptno, avg(e.sal) avs
                         from emp e
                        group by e.deptno) t
                    on t.avs between sg.losal and sg.hisal) t);
--4.����ƽ��нˮ�ĵȼ���͵Ĳ��ŵĲ�������
select d.dname
  from dept d
 where d.deptno in
       (select t.deptno
          from (select t.deptno, sg.grade
                  from salgrade sg
                  join (select e.deptno, avg(e.sal) avs
                         from emp e
                        group by e.deptno) t
                    on t.avs between sg.losal and sg.hisal) t
         where t.grade in
               (select min(t.grade)
                  from (select t.deptno, sg.grade
                          from salgrade sg
                          join (select e.deptno, avg(e.sal) avs
                                 from emp e
                                group by e.deptno) t
                            on t.avs between sg.losal and sg.hisal) t));
--�鿴sql����ܷ���,sql���кܶ���ظ���sql�Ӳ�ѯ,����ͨ����ͼ���ظ��������������
--������ͼ
create view v_deptno_grade as 
       select t.deptno, sg.grade 
       from salgrade sg 
       join (select e.deptno, avg(e.sal) avs 
            from emp e 
            group by e.deptno) t 
       on t.avs between sg.losal and sg.hisal;
--ʹ����ͼ�滻
select d.dname
  from dept d
 where d.deptno in
       (select t.deptno
          from v_deptno_grade t
         where t.grade in (select min(t.grade) from v_deptno_grade t));


