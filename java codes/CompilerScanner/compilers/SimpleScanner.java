package compilers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SimpleScanner {
	public static void main(String[] args) throws Exception {
		 Scanner input = new Scanner(System.in);
		 System.out.print("Enter a Java source file: ");
		 String filename = input.nextLine();
		
		 File file = new File(filename);
		 if (file.exists()) {
			 scan(file);
		 }
		 else {
			 System.out.println("File " + filename + " does not exist");
		 }
		 input.close();
	 }
	
	 public static void scan(File file) throws Exception {
		 // Array of all Java keywords + true, false and null
		 String[] keywordString = {"abstract", "assert", "boolean",
			 "break", "byte", "case", "catch", "char", "class", "const",
			 "continue", "default", "do", "double", "else", "enum",
			 "extends", "for", "final", "finally", "float", "goto",
			 "if", "implements", "import", "instanceof", "int",
			 "interface", "long", "native", "new", "package", "private",
			 "protected", "public", "return", "short", "static",
			 "strictfp", "super", "switch", "synchronized", "this",
			 "throw", "throws", "transient", "try", "void", "volatile",
			 "while", "true", "false", "null"};
		 
		 char[] operatorCharacter = {'>','<','=',
				 '+','-','&','|','*','!','/','%'};
				 	 
		 char[] specialCharacter = {'@','#','$','_','[',']',
				 '{','}','(',')','\'',';',':',',','.','?'};
		 
		 char[] characters= {'a','b','c','d','e','f','g','h',
				 'j','k','l','m','n','o','i','p','s','r','t','u',
				 'v','q','w','x','y','z','Q','W','E','R','T','Y',
				 'U','I','O','P','A','S','D','F','G','H','J','K',
				 'L','Z','X','C','V','B','N','M'};
		 
		 ArrayList<String> keywordSet =
				 new ArrayList<>(Arrays.asList(keywordString));

		 Scanner input = new Scanner(file);
		
		 while (input.hasNext()) {
				 String word = input.next();
				 char[] c=word.toCharArray();
			 int n;
			 
			if (keywordSet.contains(word))
				 System.out.println(word+" : is keyword");
			 
			 else 
				 for(int i=0;i<c.length;i++)
					 for(char ch : operatorCharacter)
						 if (c[i]==ch) {
							 System.out.println(c[i]+" : is operator");
							 break;
							 }
						  
			 	 for(int j=0;j<c.length;j++)
			 		 for(char chr : specialCharacter)
			 			 if (c[j]==chr) {
							 System.out.println(c[j]+" : is special character");
							 break;
							 }
			 
			 
		 }
		
	}
}



