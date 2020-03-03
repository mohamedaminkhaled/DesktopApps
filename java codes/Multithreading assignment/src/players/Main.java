
package players;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

/**
 * @author MohamedAmin
 *
 */
public class Main extends Application {
	
	public Line drawLine(int start_x,int start_y,int end_x,int end_y) {
		
		Line line = new Line(start_x,  start_y, end_x, end_y);
		  line.setStrokeWidth(3);
		  line.setStroke(Color.GREEN);
		return line;
	}	
	
@Override
	public void start(Stage primaryStage) {

		Pane pane =new Pane();
		
		  Line line0=drawLine(5, 30, 500, 30);
		  Line line1=drawLine(5, 60, 500, 60);
		  Line line2=drawLine(5, 90, 500, 90);
		  Line line3=drawLine(5, 120, 500, 120);
		  Line line4=drawLine(5, 150, 500, 150);
		  Line line5=drawLine(5, 180, 500, 180);
		  Line line6=drawLine(5, 210, 500, 210);
		  Line line7=drawLine(5, 240, 500, 240);
		  Line line8=drawLine(5, 270, 500, 270);
		  Line line9=drawLine(5, 300, 500, 300);
		  
		  Circle circle0= new Circle(5,30,7);
		  circle0.setFill(Color.BLUE);
		  Circle circle1= new Circle(5,60,7);
		  circle1.setFill(Color.CRIMSON);
		  Circle circle2= new Circle(5,90,7);
		  circle2.setFill(Color.CORNFLOWERBLUE);
		  Circle circle3= new Circle(5, 120,7);
		  circle3.setFill(Color.BLUEVIOLET);
		  Circle circle4= new Circle(5, 150,7);
		  circle4.setFill(Color.DARKCYAN);
		  Circle circle5= new Circle(5, 180,7);
		  circle5.setFill(Color.DARKMAGENTA);
		  Circle circle6= new Circle(5, 210,7);
		  circle6.setFill(Color.DARKGREEN);
		  Circle circle7= new Circle(5, 240,7);
		  circle7.setFill(Color.DARKTURQUOISE);
		  Circle circle8= new Circle(5, 270,7);
		  circle8.setFill(Color.GOLD);
		  Circle circle9= new Circle(5, 300,7);
		  circle9.setFill(Color.DEEPPINK);
		  
		  pane.getChildren().addAll(line0,line1,line2,line3,
				  line4,line5,line6,line7,line8,line9,
				  circle0,circle1,circle2,circle3,circle4,
				  circle5,circle6,circle7,circle8,circle9);
		  		  
		  Runnable player_0=new Player(circle0);
		  Runnable player_1=new Player(circle1);
		  Runnable player_2=new Player(circle2);
		  Runnable player_3=new Player(circle3);
		  Runnable player_4=new Player(circle4);
		  Runnable player_5=new Player(circle5);
		  Runnable player_6=new Player(circle6);
		  Runnable player_7=new Player(circle7);
		  Runnable player_8=new Player(circle8);
		  Runnable player_9=new Player(circle9);
	  
		// Create a fixed thread pool with maximum ten threads
		  ExecutorService executor = Executors.newFixedThreadPool(10);
		  executor.execute(player_0);
		  executor.execute(player_1);
		  executor.execute(player_2);
		  executor.execute(player_3);
		  executor.execute(player_4);
		  executor.execute(player_5);
		  executor.execute(player_6);
		  executor.execute(player_7);
		  executor.execute(player_8);
		  executor.execute(player_9);
		  
		  executor.shutdown();
		  		  
		  Scene scene = new Scene(pane, 505, 330);
		  primaryStage.setTitle("ShowLine"); // Set the stage title
		  primaryStage.setScene(scene); // Place the scene in the stage
		  primaryStage.show(); // Display the stage
	}

 class Player implements Runnable {

	 Circle circle;
	 
 public Player(Circle circle) {
	  
	 this.circle=circle;
 
 }

 @Override 
 public void run() {
	 while(circle.getCenterX()<=500) {
		 try {
			circle.setCenterX(circle.getCenterX()+1);
			
				Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
  }
 }

	public static void main(String[] args) {

		launch(args);
	}

}
