import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your string: ");
		String string = scanner.nextLine();
		
		if(isPalindrome(string)) {
			System.out.println("String " + string + " is a Palindrome.");
		}else {
			System.out.println("String " + string + " is NOT a Palindrome.");
		}
		
		scanner.close();
	}
	
	public static boolean isPalindrome(String word) {
		boolean isPalindrome = false;
		if(word.length()==0 || word.length() == 1) {
			isPalindrome = true;
			return isPalindrome;
		}
		
		if(word.charAt(0) == word.charAt(word.length() - 1)) {
			return isPalindrome(word.substring(1, word.length()-1));
		}
		
		return isPalindrome;
	}

}
