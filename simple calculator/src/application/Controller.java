package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	private TextField tf;
	@FXML
	Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5,
	       btn_6, btn_7, btn_8, btn_9, btn_dot, btn_add,
		   btn_mul, btn_sub, btn_div,btn_result;
		   

	public void control_result(ActionEvent e) {
		String str = tf.getText();
		char[] ch = str.toCharArray();
		char charLoop=ch[0];
		
		ArrayList<Double> numArray = new ArrayList<>();
		ArrayList<Character> charArray = new ArrayList<>();
		
		int i=0;
		 while (i < ch.length) {
			 ArrayList<Character> charNum = new ArrayList<>();
			
			 while (charLoop!='+' && charLoop!='-' && charLoop!='/' && charLoop!='*') {
				charNum.add(charLoop);
				if(++i < ch.length)
					charLoop = ch[i];
				else
					break;
			}
			 char[] subNum = new char[charNum.size()];				
			 for (int n = 0; n < charNum.size(); n++) {
				 subNum[n] = charNum.get(n);
			}
			 String strNum = String.valueOf(subNum);
			
			 double num = Double.parseDouble(strNum);
			 numArray.add(num);
			
			 if(i != ch.length) {
				 char op = charLoop;
				 charArray.add(op);
				 charLoop=ch[++i];
			 }
		}
		 
		 double result = numArray.get(0);
		 int x=0;
		 while(x < charArray.size()) {
			 char operation = charArray.get(x);
			 
			 
			 switch (operation) {
				 case '+': result += numArray.get(++x);
					 break;
				 case '-': result -= numArray.get(++x);
					 break;
				 case '/': result /= numArray.get(++x);
				 	 break;
				 case '*': result *= numArray.get(++x);
				 	 break;
		
				 default:
					 break;
			 }
		 }
		 
		 String strResult = String.valueOf(result);
		 tf.setText(strResult);
	}
	
	public void control_0(ActionEvent e) {
		tf.setText(tf.getText()+btn_0.getText());
	}
	
	public void control_1(ActionEvent e) {
		tf.setText(tf.getText()+btn_1.getText());
	}
	
	public void control_2(ActionEvent e) {
		tf.setText(tf.getText()+btn_2.getText());
	}
	
	public void control_3(ActionEvent e) {
		tf.setText(tf.getText()+btn_3.getText());
	}
	
	public void control_4(ActionEvent e) {
		tf.setText(tf.getText()+btn_4.getText());
	}
	
	public void control_5(ActionEvent e) {
		tf.setText(tf.getText()+btn_5.getText());
	}
	
	public void control_6(ActionEvent e) {
		tf.setText(tf.getText()+btn_6.getText());
	}
	
	public void control_7(ActionEvent e) {
		tf.setText(tf.getText()+btn_7.getText());
	}
	
	public void control_8(ActionEvent e) {
		tf.setText(tf.getText()+btn_8.getText());
	}
	
	public void control_9(ActionEvent e) {
		tf.setText(tf.getText()+btn_9.getText());
	}
	
	public void control_dot(ActionEvent e) {
		tf.setText(tf.getText()+btn_dot.getText());
	}
	
	public void control_add(ActionEvent e) {
		tf.setText(tf.getText()+btn_add.getText());
	}
	
	public void control_sub(ActionEvent e) {
		tf.setText(tf.getText()+btn_sub.getText());
	}
	
	public void control_div(ActionEvent e) {
		tf.setText(tf.getText()+btn_div.getText());
	}
	
	public void control_mul(ActionEvent e) {
		tf.setText(tf.getText()+btn_mul.getText());
	}
	
	public void control_Clr(ActionEvent e) {
		tf.setText("");
	}
	
	public void control_Factorial(ActionEvent e) {
		 String str = tf.getText();
		
		 double num = Double.parseDouble(str);
		 
		 double fact = num;
			for(int j = 1; j < num; j++) {
				fact=fact*(num-j);
			}
		String result = String.valueOf(fact);
		
		tf.setText(result);
	}
	
	public void control_Sine(ActionEvent e) {
		 String str = tf.getText();
		
		 double num = Double.parseDouble(str);
		 
		 double result = Math.sin(num);
		 String strResult = String.valueOf(result);
		
		 tf.setText(strResult);
	}
	
	public void control_Cosin(ActionEvent e) {
		 String str = tf.getText();
		
		 double num = Double.parseDouble(str);
		 
		 double result = Math.cos(num);
		 String strResult = String.valueOf(result);
		
		 tf.setText(strResult);
	}
	
	public void control_Tan(ActionEvent e) {
		 String str = tf.getText();
		
		 double num = Double.parseDouble(str);
		 
		 double result = Math.tan(num);
		 String strResult = String.valueOf(result);
		
		 tf.setText(strResult);
	}
}
