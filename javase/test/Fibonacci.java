public class Fibonacci {
	
	public static void main(String[] args) {
		
		int i = 10;
		
		for(int j = 1; j <= i ; j++) {
			System.out.println(fibonacci(j));
		}
	}
	
	private static int fibonacci(int i) {
		
		if(i>2) {
			return fibonacci(i-1) + fibonacci(i-2);
		} else {
			return 1;
		}
	}
}