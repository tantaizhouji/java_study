import java.util.Scanner;
public class Factorial {
	
	public static void main(String[] args) {
		
		int i;
		int sum = 0;
		for(i=1;i<=2;i++) {
			sum += factorial(i);
		}
		System.out.println(sum);
	}
	
	private static int factorial(int i) {
		if(i>1) {
			return i*factorial(i-1);
		} else {
			return 1;
		}
	}
}