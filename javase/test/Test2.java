import java.util.Scanner;
public class Test2 {
	
	public static void main(String[] args) {
		
		System.out.println("欢迎使用简易计算器");
		System.out.println("请输入第一个数字");
		Scanner sc = new Scanner(System.in);
		int number1 = sc.nextInt();
		System.out.println(number1);
		System.out.println("请输入第二个数字");
		int number2 = sc.nextInt();
		System.out.println(number2);
		System.out.println("请输入你要运行的计算<+,-,*,/>");
		String sign = sc.next();
		switch(sign){
			case "+":
				System.out.println(number1+number2);
				break;
			case "-":
				System.out.println(number1-number2);
				break;
			case "*":
				System.out.println(number1*number2);
				break;
			case "/":
				System.out.println(number1/number2);
				break;
			default:
				System.out.println("不能进行这样的运算");
		} 
	}
}