package basicProblems;

import java.util.Scanner;

public class SwapArray {

	public static void main(String[] args) {
Scanner in=new Scanner(System.in);
		
		System.out.print("Enter array length: ");
		int arrlength=in.nextInt();
		
		System.out.print("Enter array elements: ");
		double[] arr1= new double[arrlength];
		for(int m=0; m<arr1.length; m++) {
			arr1[m]=in.nextDouble();
		}
		in.close();
		
		swapArray(arr1);
		
	}

	public static double[] swapArray(double[] arr1) {
		
		
		double[] arr2=new double[arr1.length];
		
		for(int i=(arr1.length-1); i>=0; i--) {
			arr2[i]=arr1[(arr1.length-1)-i];
		}
		
		System.out.println("The swapped array is:");
		
		for(int j=0; j<arr2.length; j++) {
			System.out.println(arr2[j]);
		}
		
		return arr2;
	}

}
