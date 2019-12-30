import java.util.Scanner;
public class Test3 {
	
	public static void main(String[] args) {
		
		System.out.println("您要买几月份的票?");
		Scanner sc = new Scanner(System.in);
		int month = sc.nextInt();
		System.out.println("您要买几等座的票?");
		int level = sc.nextInt();
		if(month>=4&&month<=10) {
			if(level == 1){
				System.out.println("需要" + 5000*0.9);
			} else {
				System.out.println("需要" + 5000*0.8);
			}
		} else if(month<1||month>12) {
			System.out.println("月份输入错误");
		} else {
			if(level == 1){
				System.out.println("需要" + 5000*0.5);
			} else {
				System.out.println("需要" + 5000*0.4);
			}
		}
	}
}