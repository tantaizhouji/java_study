import java.util.Scanner;
public class IfDemo{
	
	public static void main(String[] args) {
		
		//����֧�ж�,Math.random()���������ݷ�Χ��[0,1)
		//�õ�[0,6)֮��������
		int i = (int)(Math.random()*6);
		if(i>3) {
			System.out.println("ֵ����3");
		}
		System.out.println("number: " + i);
		
		//˫��֧�ṹ
		int r = 1;
		double PI = 3.14;
		double area = PI * r * r;
		double length = 2 * PI * r;
		if(area > length) {
			System.out.println("��������ܳ�");
		} else {
			System.out.println("���С���ܳ�");
		}
		
		//Scanner
		//�����ļ�ɨ�����,System.in��ʾ���Ǳ�׼����,���Դӿ���̨��ȡ����(װ����ģʽ)
		//ע��:ÿ�ζ�ȡ������ֵ�����ַ�������,��Ҫ��������ת��
		Scanner sc = new Scanner(System.in);
		System.out.println("����������");
		String str = sc.nextLine();
		System.out.println(str);
	}
}