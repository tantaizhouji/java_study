import java.util.Scanner;
public class Test3 {
	
	public static void main(String[] args) {
		
		System.out.println("��Ҫ���·ݵ�Ʊ?");
		Scanner sc = new Scanner(System.in);
		int month = sc.nextInt();
		System.out.println("��Ҫ�򼸵�����Ʊ?");
		int level = sc.nextInt();
		if(month>=4&&month<=10) {
			if(level == 1){
				System.out.println("��Ҫ" + 5000*0.9);
			} else {
				System.out.println("��Ҫ" + 5000*0.8);
			}
		} else if(month<1||month>12) {
			System.out.println("�·��������");
		} else {
			if(level == 1){
				System.out.println("��Ҫ" + 5000*0.5);
			} else {
				System.out.println("��Ҫ" + 5000*0.4);
			}
		}
	}
}