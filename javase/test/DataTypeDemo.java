/*
��������
	java��һ��ǿ���͵�����
		ǿ���ͱ�ʾ,�����ڶ����ʱ�������ʽ������������ʲô		java
		�����ͱ�ʾ,���������ֵ�Լ�ȥ�ƶ�,����Ҫָ��ʲô����	js,python,scala
	java��������
		������������(4��8��)
			�������ͣ�byte short int long(��ͬ���ͱ�ʾ��ͬ�ĳ���)
				byte: 	ʹ��һ���ֽڴ洢,��˷�Χ�� -128 ~ 127
				short: 	ʹ�������ֽڴ洢����˷�Χ�� -32768 ~ 32767
				int:	ʹ���ĸ��ֽڴ洢����˷�Χ�� -2^31 ~ 2^31-1
				long:	ʹ�ð˸��ֽڴ洢����˷�Χ�� -2^63 ~ 2^63-1
				ע��:
					��ʹ���������͵�ʱ��,Ĭ�϶���int����,���ʹ��long���͵Ļ�,���������ֵĺ�������L,����ʹ�ô�д,Сд���׸�1����
			��������: float double
				float:	������,���ȿ��Ծ�ȷ��С�����7λ
				double:	˫����,������float��˫��
				ע��:
					1.Ĭ�ϸ���������double����
					2.ʹ��float��ʱ��,���ֺ�Ҫ����f
					3.�������Ͳ����ܱ�ʾһ����ȷ��ֵ,����ʧһ���ľ���
			�ַ�����: char
				ռ��2���ֽ�,ʹ�õ�ʱ��ʹ��''��ʾ
			��������: boolean
				ֻ��true��false��ֵ,�ڴ洢��ʱ��ռ1λ
		������������
*/
public class DataTypeDemo{
	
	public static void main(String[] args) {
		//byte b = 128;
		//short s = 32777;
		//int i = 11111111111111111;
		//long l = 11111111111111111L;
		
		float f1 = 2.1223123F;
		float f2 = 2.12231232321F;
		System.out.println(f1==f2);
		
		//��ʾ1���ַ�
		char a = 'a';
		//��ʾ1���ַ���,��ʾ1���ַ�����
		String s = "A";
		System.out.println(a==97);
		
		char ch = '\t';
		System.out.println("["+ch+"]");
	}
}