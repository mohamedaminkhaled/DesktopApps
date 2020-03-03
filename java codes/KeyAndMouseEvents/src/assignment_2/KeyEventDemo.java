package assignment_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

 public class KeyEventDemo extends Application {
	 
	 Circle circle;
	 
	 @Override // Override the start method in the Application class
	 public void start(Stage primaryStage) {
		 
		 // Create a pane and set its properties
		 circle=new Circle(150,150,30);
		 circle.setStroke(Color.BLACK);
		 circle.setFill(Color.DARKCYAN);
		 
		 Pane pane = new Pane();
		 pane.getChildren().add(circle);
		 
		 //lambda handler
		 pane.setOnKeyPressed(e -> {
		 switch (e.getCode()) {
			 case DOWN: circle.setCenterY(circle.getCenterY() + 10); break;
			 case UP: circle.setCenterY(circle.getCenterY() - 10); break;
			 case LEFT: circle.setCenterX(circle.getCenterX() - 10); break;
			 case RIGHT: circle.setCenterX(circle.getCenterX() + 10); break;
			 default:
				break;
		 	}
		 });
		
		 // Create a scene and place it in the stage
		 Scene scene = new Scene(pane,300,300);		 
		 primaryStage.setTitle("KeyEventDemo"); // Set the stage title
		 primaryStage.setScene(scene); // Place the scene in the stage
		 primaryStage.show(); // Display the stage
		
		 pane.requestFocus(); // text is focused to receive key input
 }
 
	public static void main(String[] args) {
		launch(args);
	}
}
