package basicProblems;

import java.util.Scanner;

public class CheckPrime {

	public static void main(String[] args) {
		
		Scanner in=new Scanner(System.in);
		System.out.print("enter number: ");
		int num=in.nextInt();
		if(num==1 || num==2)
			System.out.println(num+" is prime!");
		else
			for(int i=2;i<num;i++) {
				if(num%i==0) {
					System.out.println(num+" is not prime!");
					break;
				}
				else {
					System.out.println(num+" is prime!");
					break;
				}
			}
		
	}

}
