/*
�����൱�����ݽṹ��һ��ʵ��,�ܶ������ڽ��д洢��ʱ����Ҫʹ������
	���ݽṹ:
		���Ա�
		�����Ա�
		��
		ͼ
		����
		ջ
���龭���������������㷨:
	��������:
		1.д��ĳ�������㷨
			ð������
			ѡ������
			��������
			��������
		2.�����㷨��ʱ�临�Ӷ�(�ռ临�Ӷ�)
			����һ�����ݽṹ�Ƿ���ʵĺ�����׼
		3.�����㷨���ȶ���
			����֮���ֵ������֮ǰ��ֵλ���Ƿ����仯
*/
public class ArraySort {
	
	public static void main(String[] args) {
		
		//��������
		int[] array = new int[] {4,1,7,2,5,3,9,8,6};
		//����������������,��С����
		//ѡ������
		/*
		for(int i = 0; i < array.length; i++) {
			for(int j = i + 1; j < array.length; j++) {
				if(array[i] > array[j]) {
					int min = array[j];
					array[j] = array[i];
					array[i] = min;
				}
			}
		}
		*/
		
		//ð������
		for(int i = 1; i < array.length; i++) {
			for(int j = 0; j < array.length -i; j++) {
				if(array[j] > array[j+1]) {
					int tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
			}
		}
		
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
	}
}