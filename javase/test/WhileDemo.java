/*
循环结构:
	1.while
		需要四部分组成
			初始化:变量的初始化
			条件判断:必须要求返回true或false的值
			循环体:具体的要执行的逻辑代码
			迭代变量:促使此循环结束
*/
public class WhileDemo {
	
	public static void main(String[] args) {
		
		int i = 1;
		while(i<=100) {
			System.out.println("第" + i + "次");
			i++;
		}
	}
}