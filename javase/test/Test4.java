import java.util.Scanner;
public class Test4 {
	
	public static void main(String[] args) {
		
		System.out.println("*************************************************");
		System.out.println("请选择购买商品的编号");
		System.out.println("1.T恤" + "\t" + "2.网球鞋" + "\t" + "3.网球拍");
		System.out.println("*************************************************");
		
		Scanner sc = new Scanner(System.in);
		
		Char contrl = 'y';
		
		while(contrl=="y") {
			
			System.out.print("请输入商品编号: ");
			int number = sc.nextInt();
			System.out.print("请输入商品数量: ");
			int sum = sc.nextInt();
			switch(number) {
				case 1:
					System.out.println("T恤￥245.0" + "\t" + "数量" + sum + "合计￥" + 245.0*sum);
					break;
				case 2:
					System.out.println("网球鞋￥570.0" + "\t" + "数量" + sum + "\t" + "合计￥" + 570.0*sum);
					break;
				case 3:
					System.out.println("网球拍￥320.0" + "\t" + "数量" + sum + "合计￥" + 320.0*sum);
					break;	
				default:
					System.out.println("输入编号不存在");
			}
			System.out.print("是否继续(y/n)");
			contrl = sc.nextChar();
		}
	}
}