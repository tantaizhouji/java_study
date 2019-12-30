import java.util.Scanner;
public class Test1 {
	
	public static void main(String[] args) {
		
		System.out.println("请按数字进行拨号<1-4>");
		Scanner sc = new Scanner(System.in);
		String number = sc.nextLine();
		switch(number){
			case "1":
				System.out.println("正在呼叫爸爸");
				break;
			case "2":
				System.out.println("正在呼叫妈妈");
				break;
			case "3":
				System.out.println("正在呼叫爷爷");
				break;
			case "4":
				System.out.println("正在呼叫奶奶");
				break;
			default:
				System.out.println("号码输入错误");
		} 
	}
}