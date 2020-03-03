
package players;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test{
	
	public static void main(String[] args) {
	  
	  Test test=new Test();
		
		  ExecutorService executor = Executors.newFixedThreadPool(10);
		  executor.execute(test.new Player("player0"));
		  executor.execute(test.new Player("player1"));
		  executor.execute(test.new Player("player2"));
		  executor.execute(test.new Player("player3"));
		  executor.execute(test.new Player("player4"));
		  executor.execute(test.new Player("player5"));
		  executor.execute(test.new Player("player6"));
		  executor.execute(test.new Player("player7"));
		  executor.execute(test.new Player("player8"));
		  executor.execute(test.new Player("player9"));
		  
		  executor.shutdown();
		  
	}
 
	class Player implements Runnable {

	String str;
	 
 public Player(String str) {
	  
	 this.str=str;
 
 }

 @Override 
 public void run() {
	 
	 for(int i=0;i<=300;i++) {
		 System.out.println(str);
		 try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
  

	}
}

