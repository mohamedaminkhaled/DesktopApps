package basicProblems;

import java.util.Scanner;

public class MidOfThreeNums {

	public static void main(String[] args) {
		
		Scanner in=new Scanner(System.in);
		System.out.println("Enter three numbers: ");
		int num1=in.nextInt();
		int num2=in.nextInt();
		int num3=in.nextInt();
		
		int midNum=0;
		if(num1<=num2&&num1<=num3)
			midNum=num1;
		else if(num2<=num1&&num2<=num3)
			midNum=num2;
		else if(num3<=num1&&num3<=num2)
			midNum=num3;
		in.close();
		
		System.out.println("The midle number is: "+midNum);
		
	}

}
