import java.util.Scanner;
public class Test4 {
	
	public static void main(String[] args) {
		
		System.out.println("*************************************************");
		System.out.println("��ѡ������Ʒ�ı��");
		System.out.println("1.T��" + "\t" + "2.����Ь" + "\t" + "3.������");
		System.out.println("*************************************************");
		
		Scanner sc = new Scanner(System.in);
		
		Char contrl = 'y';
		
		while(contrl=="y") {
			
			System.out.print("��������Ʒ���: ");
			int number = sc.nextInt();
			System.out.print("��������Ʒ����: ");
			int sum = sc.nextInt();
			switch(number) {
				case 1:
					System.out.println("T����245.0" + "\t" + "����" + sum + "�ϼƣ�" + 245.0*sum);
					break;
				case 2:
					System.out.println("����Ь��570.0" + "\t" + "����" + sum + "\t" + "�ϼƣ�" + 570.0*sum);
					break;
				case 3:
					System.out.println("�����ģ�320.0" + "\t" + "����" + sum + "�ϼƣ�" + 320.0*sum);
					break;	
				default:
					System.out.println("�����Ų�����");
			}
			System.out.print("�Ƿ����(y/n)");
			contrl = sc.nextChar();
		}
	}
}