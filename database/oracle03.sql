--������ѯ
/*
select t1.c1,t2.c2 from t1,t2 where t1.c3 = t2.c4;
�ڽ������ӵ�ʱ��,����ʹ�õ�ֵ����,Ҳ����ʹ�÷ǵ�ֵ����
*/
--��ѯ��Ҫ����Ա�����ƺͲ��ŵ�����
select dname,ename from emp,dept where emp.deptno = dept.deptno;
--��ѯ��Ա�����Ѿ�ֱ�ӵ�нˮ�ȼ�
select e.ename, e.sal, sg.grade
  from emp e, salgrade sg
 where e.sal between sg.losal and sg.hisal;
--��ֵ����,�������а�����ͬ������
--�ǵ�ֵ����,��������û����ͬ������,����ĳһ��������һ������еķ�Χ֮��
--������
--��Ҫ����Ա���е��������ݶ�������ʾ,���õ�ֵ���ӵĻ�ֻ��ѹ�����������ʾ
--û�й����������ݲ�����ʾ,��ʱ��Ҫ������
--���ࣺ��������(������ȫ��������ʾ)����������(���ұ��ȫ��������ʾ)
select * from emp e,dept d where e.deptno = d.deptno; --��ֵ����
select * from emp e,dept d where e.deptno = d.deptno(+); --��������
select * from emp e,dept d where e.deptno(+) = d.deptno; --��������
--������,��һ�ű��ɲ�ͬ�ı�������,ֱ�ӹ����Լ�
--����Ա������������Ʋ����
select e.ename,m.ename from emp e,emp m where e.mgr = m.empno;
--�ѿ�����,���������ű�,���ǲ�ָ������������ʱ��,����еѿ�����
--��������ܼ�¼����ΪM*n,һ�㲻Ҫʹ��
select * from emp e,dept d;

--92�ı������﷨��ʲô����???
--��92�﷨��,���ű���������������where�Ӿ���,ͬʱwhere��Ҫ�Ա������������
--���,�൱�ڽ��������������������ൽһ��,̫����,��˳�����99�﷨


--99�﷨
/*
CROSS JOIN
NATURAL JOIN
USING�Ӿ�
ON�Ӿ�
LEFT OUTER JOIN
RIGHT OUTER JOIN
FULL OUTER JOIN
Inner outer join
*/
-- cross join��ͬ��92�﷨�еĵѿ�����
select * from emp cross join dept;
-- natural join �൱���ǵ�ֵ����,����ע��,����Ҫд��������,������ű����ҵ���ͬ����������
--�����ű��в�������ͬ��������ʱ��,����еѿ���������,��Ȼ���Ӹ�92�﷨��������û���κι�ϵ
select * from emp e natural join dept d;
select * from emp e natural join salgrade sg;

-- on�Ӿ�,��������������������
--�����������,�൱��92�﷨�еĵ�ֵ����
select * from emp e join dept d on e.deptno=d.deptno;
--�൱��92�﷨�еķǵ�ֵ����
select * from emp e join salgrade sg on e.sal between losal and hisal;

-- left outer join,�������е�ȫ������������ʾ,�ұ�û�ж�Ӧ������ֱ����ʾ�ռ���
select * from emp e left outer join dept d on e.deptno = d.deptno;
-- right outer join,����ұ��е�ȫ������������ʾ,�����û�ж�Ӧ�ļ�¼�Ļ���ʾ�ռ���
select * from emp e right outer join dept d on e.deptno=d.deptno;
-- full outer join,�൱���������Ӻ��������ӵĺϼ�
select * from emp e full outer join dept d on e.deptno=d.deptno;
-- inner outer join,���ű�����Ӳ�ѯ,ֻ���ѯ����ƥ���¼������
select * from emp e inner join dept d on e.deptno=d.deptno;
select * from emp e join dept d on e.deptno=d.deptno;
-- using ,����ʹ��on��ʾ��������֮��,Ҳ����ʹ��using��Ϊ��������,��ʱ�����������в��ٹ������κ�һ�ű�
select * from emp e join dept d using(deptno);
select * from emp e join dept d on e.deptno=d.deptno;
--�ܽ�:�����﷨��SQL���û���κ�����,�ڹ�˾�п�������ʹ��,���ǽ���ʹ��99�﷨,��Ҫʹ��92�﷨,SQL����Ե��������

--������Ա���֡����ڵ�λ��нˮ�ȼ�
select e.ename, d.loc, sg.grade
  from emp e
  join dept d
    on e.deptno = d.deptno
  join salgrade sg
    on e.sal between sg.losal and sg.hisal;
/*
�Ӳ�ѯ:
    Ƕ��������sql����е�����sql���,���Գ�֮Ϊ�Ӳ�ѯ
����:
    �����Ӳ�ѯ
    �����Ӳ�ѯ
*/
--����Щ�˵�нˮ����������Ա��ƽ��нˮ֮��
select ename, deptno, sal + nvl(comm, 0)
  from emp
 where sal + nvl(comm, 0)  > (select avg(sal + nvl(comm, 0)) from emp);
--Ҫ���ڹ�Ա������Щ���Ǿ�����
--1.��ѯ���еľ����˱��
select distinct e.mgr from emp e;
--2.�ڹ�Ա���й�����Щ��ż���
select * from emp e where e.empno in(select distinct e.mgr from emp e);

--��ÿ������ƽ��нˮ�ĵȼ�
--1.��������ŵ�ƽ��нˮ
select deptno,avg(sal+nvl(comm,0)) avgsal from emp group by deptno;
--2.��нˮ�ǼǱ�������,�ҳ�ƽ��нˮ�ĵǼ�
select a.deptno, sg.grade, a.avgsal,sg.losal,sg.hisal
  from salgrade sg
  join (select deptno, avg(sal + nvl(comm, 0)) avgsal
          from emp
         group by deptno) a
    on a.avgsal between sg.losal and sg.hisal;

--1����ƽ��нˮ��ߵĲ��ŵĲ��ű��
--���ŵ�ƽ��нˮ
select deptno,avg(e.sal) a from emp e group by e.deptno
--��ƽ��нˮ��ߵĲ���
select max(v.a) mx from (select deptno,avg(e.sal) a from emp e group by deptno) v 
--���ű��
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
--2������ƽ��нˮ�ĵȼ�
select deptno,avg(e.sal) from emp e group by deptno

select avge.deptno, sg.grade
  from salgrade sg
  join (select deptno, avg(e.sal) a from emp e group by deptno) avge
    on avge.a between sg.losal and sg.hisal;
--3������ƽ����нˮ�ȼ�
--����ÿ���˵�нˮ�ȼ�
select from salgrade sg join emp e on e.sal between sg.losal and sg.hisal
--���ղ�����ƽ���ȼ�
select s.deptno, avg(s.grade)
  from (select *
          from salgrade sg
          join emp e
            on e.sal between sg.losal and sg.hisal) s
 group by s.deptno;
--�������,limit,mysql���������������,����oracle�в���
--��oracle��,�����Ҫʹ����������ͷ�ҳ�Ĺ��ܵĻ�,����Ҫʹ��rownum,����rownum����ֱ��ʹ��,��ҪǶ��ʹ��
--4����нˮ��ߵ�ǰ5����Ա
select *
  from (select * from emp e order by e.sal desc) t1
 where rownum <= 5;
--5����нˮ��ߵĵ�6��10����Ա
select t1.*, rownum
  from (select * from emp e order by e.sal desc) t1
 where rownum <= 10;
--ʹ��rownum��ʱ�����Ҫ��������Ƕ��,��ʱ���ܽ�rownum��Ϊ���е�һ����,Ȼ���ٽ����������
select *
  from (select t1.*, rownum rn
          from (select * from emp e order by e.sal desc) t1
         where rownum <= 10) t
 where t.rn between 6 and 10;
