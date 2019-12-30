/*
数据类型
	java是一种强类型的语言
		强类型表示,变量在定义的时候必须显式的声明类型是什么		java
		弱类型表示,变量会根据值自己去推断,不需要指定什么类型	js,python,scala
	java数据类型
		基本数据类型(4类8种)
			整数类型：byte short int long(不同类型表示不同的长度)
				byte: 	使用一个字节存储,因此范围是 -128 ~ 127
				short: 	使用两个字节存储，因此范围是 -32768 ~ 32767
				int:	使用四个字节存储，因此范围是 -2^31 ~ 2^31-1
				long:	使用八个字节存储，因此范围是 -2^63 ~ 2^63-1
				注意:
					在使用征信类型的时候,默认都是int类型,如果使用long类型的话,必须在数字的后面添加L,建议使用大写,小写容易更1混淆
			浮点类型: float double
				float:	单精度,精度可以精确到小数点后7位
				double:	双精度,精度是float的双倍
				注意:
					1.默认浮点类型是double类型
					2.使用float的时候,数字后要添加f
					3.浮点类型并不能表示一个精确的值,会损失一定的精度
			字符类型: char
				占用2个字节,使用的时候使用''表示
			布尔类型: boolean
				只有true和false两值,在存储的时候占1位
		引用数据类型
*/
public class DataTypeDemo{
	
	public static void main(String[] args) {
		//byte b = 128;
		//short s = 32777;
		//int i = 11111111111111111;
		//long l = 11111111111111111L;
		
		float f1 = 2.1223123F;
		float f2 = 2.12231232321F;
		System.out.println(f1==f2);
		
		//表示1个字符
		char a = 'a';
		//表示1个字符串,表示1个字符序列
		String s = "A";
		System.out.println(a==97);
		
		char ch = '\t';
		System.out.println("["+ch+"]");
	}
}