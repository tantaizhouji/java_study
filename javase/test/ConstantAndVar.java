/*
常量和变量
	常量:在程序运行过程中,值不会发生改变的量叫做常量
	变量:在程序运行过程中,值会发生改变的量叫做常量
	
	变量:
		1.先声明,后赋值
			int a;	声明
			a = 10;	赋值
		2.声明+赋值
	注意:
		在类内,方法外定义的变量叫成员变量,会存在默认值
		在方法内,定义的变量必须进行初始化操作,不会存在默认值
*/
public class ConstantAndVar{
	
	static int d;
	public static void main(String[] args) {
		//声明
		int a;
		//赋值
		a = 10;
		//声明+赋值
		int b = 20;
		int c;
		System.out.println(d);
	}
}