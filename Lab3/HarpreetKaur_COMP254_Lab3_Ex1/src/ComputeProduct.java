import java.util.Scanner;

public class ComputeProduct {

	public static void main(String[] args) {
	
		int num1 = 0, num2 = 0;
		Scanner scanner = new Scanner(System.in);
		
		try {			
			
			System.out.println("Enter 1st number: ");
			num1 = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Enter 2nd number: ");
			num2 = Integer.parseInt(scanner.nextLine());
			
			int product = findProduct(num1, num2);
			
			System.out.println("Product of " + num1 + " and " + num2 + " is :" + product);
			
			scanner.close();
		}catch(Exception ex) {
			System.out.println("You have entered an invalid number. Please try again and enter a valid integer number.");
		}finally {
			scanner.close();
		}		
	}
	
	public static int findProduct(int m, int n) {
		int product = 0;
		if(n==1) {
			return m;
		}
		if(m==1) {
			return n;
		}
		
		product = m + findProduct(m, n-1);
		
		return product;
	}

}
