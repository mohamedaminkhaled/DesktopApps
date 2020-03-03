package basicProblems;

public class MultiplicationTable {

	public static void main(String[] args) {
		
		for(int i=1;i<=10;i++) {
			for(int j=i;j<=10;j++) {
				System.out.print((i*j)+" ");
			}
			System.out.println();
			for(int t=0;t<=i;t++) {
				System.out.print(" ");
			}
		}
	}

}
