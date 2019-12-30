import java.util.Scanner;
public class IfDemo{
	
	public static void main(String[] args) {
		
		//单分支判断,Math.random()产生的数据范围是[0,1)
		//得到[0,6)之间的随机数
		int i = (int)(Math.random()*6);
		if(i>3) {
			System.out.println("值大于3");
		}
		System.out.println("number: " + i);
		
		//双分支结构
		int r = 1;
		double PI = 3.14;
		double area = PI * r * r;
		double length = 2 * PI * r;
		if(area > length) {
			System.out.println("面积大于周长");
		} else {
			System.out.println("面积小于周长");
		}
		
		//Scanner
		//创建文件扫描对象,System.in表示的是标准输入,可以从控制台读取数据(装饰者模式)
		//注意:每次读取回来的值都是字符串类型,需要进行类型转换
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入数据");
		String str = sc.nextLine();
		System.out.println(str);
	}
}