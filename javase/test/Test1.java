import java.util.Scanner;
public class Test1 {
	
	public static void main(String[] args) {
		
		System.out.println("�밴���ֽ��в���<1-4>");
		Scanner sc = new Scanner(System.in);
		String number = sc.nextLine();
		switch(number){
			case "1":
				System.out.println("���ں��аְ�");
				break;
			case "2":
				System.out.println("���ں�������");
				break;
			case "3":
				System.out.println("���ں���үү");
				break;
			case "4":
				System.out.println("���ں�������");
				break;
			default:
				System.out.println("�����������");
		} 
	}
}