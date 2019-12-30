import java.util.Scanner;
public class AddCust {
	
	public static void main(String[] args) {
		
		System.out.println("欢迎光临登陆系统");
		System.out.println("添加用户信息");
		//创建Scanner对象
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入用户号码<4位整数>");
		String number = sc.nextLine();
		System.out.println("请输入用户生日<月/日>");
		String birthday = sc.nextLine();
		System.out.println("请输入用户积分");
		String score = sc.nextLine();
		
		if(number.length() == 4) {
			System.out.println(number +"\t"+ birthday +"\t"+ score);
		} else {
			System.out.println("用户号码错误");
		}
	}	
}