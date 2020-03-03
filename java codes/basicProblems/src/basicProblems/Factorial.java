package basicProblems;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		
		System.out.print("Enter an integer: ");
		
		Scanner in=new Scanner(System.in);
		int num=in.nextInt();
		
		int fact=num;
		for(int i=1;i<num;i++) {
			fact=fact*(num-i);
		}
		
		System.out.println("Factorial of [ "+num+" ] is: "+fact);
	}

}
