import java.util.Scanner;
public class AddCust {
	
	public static void main(String[] args) {
		
		System.out.println("��ӭ���ٵ�½ϵͳ");
		System.out.println("����û���Ϣ");
		//����Scanner����
		Scanner sc = new Scanner(System.in);
		System.out.println("�������û�����<4λ����>");
		String number = sc.nextLine();
		System.out.println("�������û�����<��/��>");
		String birthday = sc.nextLine();
		System.out.println("�������û�����");
		String score = sc.nextLine();
		
		if(number.length() == 4) {
			System.out.println(number +"\t"+ birthday +"\t"+ score);
		} else {
			System.out.println("�û��������");
		}
	}	
}