import java.util.Scanner;
public class Test2 {
	
	public static void main(String[] args) {
		
		System.out.println("��ӭʹ�ü��׼�����");
		System.out.println("�������һ������");
		Scanner sc = new Scanner(System.in);
		int number1 = sc.nextInt();
		System.out.println(number1);
		System.out.println("������ڶ�������");
		int number2 = sc.nextInt();
		System.out.println(number2);
		System.out.println("��������Ҫ���еļ���<+,-,*,/>");
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
				System.out.println("���ܽ�������������");
		} 
	}
}