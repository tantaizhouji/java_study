--�������ע��
comment on table emp is '��Ա��';
--�������ע��
comment on column emp.ename is '��Ա����';

/*sql���ѧϰ

SELECT [DISTINCT] {*,column alias...}
FROM table alias
Where �������ʽ

*/

--��ѯ��Ա���в��ű����10��Ա��
select empno,ename,job from emp where deptno = 10;
--distinctȥ���ظ�����
select distinct deptno from emp;
--ȥ��Ҳ������Զ���ֶ�,����ֶ�ֵֻҪ��һ����ƥ������ǲ�ͬ�ļ�¼
select distinct deptno,sal from emp;
--�ڲ�ѯ�Ĺ����п��Ը�����ӱ���,ͬʱҲ���Ը�����ӱ���
select e.empno  ��Ա���,e.ename  ��Ա����,e.job  ��Ա���� from emp e where e.deptno = 10;
--������������Լ�as,Ҳ���Բ���,������
select e.empno as ��Ա���,e.ename as ��Ա����,e.job as ��Ա���� from emp e where e.deptno = 10;
--���������,���������а����ո�,��ô��Ҫ��������""��������
select e.empno as "��Ա ���",e.ename as "��Ա ����",e.job as "��Ա ����" from emp e where e.deptno = 10;
--��ѯ���е������ֶ�,����ʹ��*,��������Ŀ��ǧ��Ҫ���ʹ��,���ױ�����
select * from emp;

--  =
select * from emp where deptno = 20;
-- !=
select * from emp where deptno !=20;
-- <>������
select * from emp where deptno <>20;
-- <
select sal from emp where sal <1500;
-- >
select sal from emp where sal >1500;
-- <=
select sal from emp where sal <=1500;
-- >=
select sal from emp where sal >=1500;
-- any,ȡ��������һ��
select sal from emp where sal > any(1000,1500,3000);
-- some,some��any��ͬһ��Ч��,ֻҪ��������ĳһ��ֵ�������
select sal from emp where sal > some(1000,1500,3000);
-- all,�������е�ֵ�Ż����
select sal from emp where sal > all(1000,1500,3000);
-- is null,��sql���﷨��,null��ʾһ������ĺ���,null != null,����ʹ��=��!=���ж�,��Ҫʹ��is��is not
select * from emp where comm is null;
-- is not null
select * from emp where comm is not null;
-- between x and y,����x��y��ֵ
select * from emp where sal between 1500 and 3000;
select * from emp where sal >=1500 and sal <=3000;
--��Ҫ����ĳЩֵ�ĵ�ֵ�жϵ�ʱ�����ʹ��in��not in
-- in(list)
select * from emp where deptno in(10,20);
--����ʹ��and��orע��Ĺؼ���,and�൱���������,or��ͬ���ǻ����
-- and��or���ܳ�����ͬһ��sql�����,��ʱ��Ҫע��and��or�����ȼ�
-- and�����ȼ�Ҫ����or,����һ��Ҫ��or����ز�����()������,������ȼ�
select * from emp where deptno = 10 or deptno = 20;
-- not in(list)
select * from emp where deptno not in(10,20);
select * from emp where deptno != 10 and deptno != 20;
-- exists (sub - query)�Ӳ�ѯ.��exists�е��Ӳ�ѯ����ܲ鵽��Ӧ�Ľ����ʱ��,��ζ�ŵ�������
--�൱��˫��forѭ��
--����Ҫ��ѯ���ű��Ϊ10��20��Ա��,Ҫ��ʹ��existsʵ��
select * from emp where deptno = 10 or deptno = 20;
--ͨ�����ѭ�����淶�ڲ�ѭ��
select * from emp e where exists (select deptno from dept d where d.deptno in(10,20) and e.deptno = d.deptno);
select *
  from emp e
 where exists (select deptno
          from dept d
         where (d.deptno = 10 or d.deptno = 20)
           and e.deptno = d.deptno);
-- like ģ����ѯ.��like������У���Ҫʹ��ռλ������ͨ���
--  _,ĳ���ַ��������ֽ�����һ��
--  %,�����ַ������������
--��ѯ������S��ͷ���û�
--ʹ��like��ʱ��Ҫ����,��Ϊlike��Ч�ʱȽϵ�
--ʹ��like���Բο�ʹ������,����Ҫ������%��ͷ
--�漰�����ı�������ʱ��,����ʹ��ĳЩ��� luence,solr,elastic search
select * from emp where ename like('S%');
--��ѯ������S��ͷ�ҵ����ڶ����ַ�ΪT���û�
select * from emp where ename like('S%T_');
-- escape,ʹ��ת���ַ�,�����Լ��涨ת���ַ�
--��ѯ�����д�%���û�
select * from emp where ename like('%\%%') escape('\');
--order by�����������,Ĭ���������ɵ�������Ĳ���
-- asc:��Ĭ�ϵ�����ʽ,��������
-- desc:���������ʽ
--�����ǰ�����Ȼ˳����������,�������ֵ,��ô���մ�С,������ַ���,��ô�����ֵ�������
--�ڽ��������ʱ�����ָ������ֶ�,���Ҷ���ֶο���ʹ�ò�ͬ������ʽ
select * from emp order by sal;
--ÿ����ִ��order by��ʱ���൱��������ȫ����,˼��ȫ�����Ч��
--��ȽϺķ�ϵͳ����Դ,���ѡ����ҵ��̫��æ��ʱ�����
select * from emp order by sal desc;
select * from emp order by ename;
select * from emp order by sal desc, ename asc;
select * from emp order by ename asc, sal desc;
--ʹ�ü����ֶ�
--�ַ������ӷ�
select 'my name is ' || ename name from emp;
select concat('my name is ', ename) from emp;
--��������Ա������н
select ename,(e.sal + e.comm)*12 from emp e;
--null�ǱȽ�����Ĵ���,null���κ����㻹��Ϊnull,���Ҫ���ս���ת��
--���뺯��nvl,nvl(arg1,arg2),���arg1�ǿ�,��ô����arg2,������ǿ�,�򷵻�ԭ����ֵ
select ename,(e.sal + nvl(e.comm,0))*12 from emp e;
--dual��oracle���ݿ��е�һ�������,û��ʵ�ʵ�����,��������������
select 100+null from dual;
-- A ����A
select * from emp where deptno = 30;
-- B ����B
select * from emp where sal > 1000;
--����,���������н�������ݼ�,ֻ��ʾһ��
select *
  from emp
 where deptno = 30
intersect
select *
  from emp
 where sal > 1000;
--����,�����������е��������ݶ�������ʾ,���ǲ������ظ�������
select *
  from emp
 where deptno = 30
union
select *
  from emp
 where sal > 1000;
--ȫ��,���������ϵ�����ȫ����ʾ,�������ȥ�صĲ���
select *
  from emp
 where deptno = 30
union all
select *
  from emp
 where sal > 1000;
--�,������A���϶���������B�����е�����,��A��B�ļ���˳�����
select *
  from emp
 where deptno = 30
minus
select *
  from emp
 where sal > 1000;
