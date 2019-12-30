/*
使用命令行的方式执行的时候，cmd的默认编码格式为GBK
因此在输入中文的时候需要设置文件的编码格式为ANSI，不会出现乱码错误

注意:
	1.java文件的名称必须与public class的名称保持一致
	2.一个java文件中包含多个class，凡是public class只能有一个
*/
/*
java中的注释分为三类
	1.单行注释://
	2.多行注释:/**/
	3.文档注释:
*/
public class HelloWorld{

	public static void main(String[] args) {
		System.out.println("写个HelloWorld");
	}
}