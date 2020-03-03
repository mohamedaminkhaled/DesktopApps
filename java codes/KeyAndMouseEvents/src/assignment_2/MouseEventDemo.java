package assignment_2;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

 public class MouseEventDemo extends Application {
	
	 @Override // Override the start method in the Application class
	 public void start(Stage primaryStage) {
		 
		 // Create a pane and set its properties
		 StackPane pane = new StackPane();
		 Text text1 = new Text("java is fun");
		 text1.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 
				 FontPosture.ITALIC, 24));
		 text1.setStroke(Color.BLUE);
		 
		 Text text2 = new Text("Java is powerful");
		 text2.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 
				 FontPosture.ITALIC, 24));
		 
		 pane.getChildren().add(text1);
		 pane.getChildren().add(text2);
		 
		 //lambda handler
		 pane.setOnMouseClicked(e -> {	
			 	 Text tempText=new Text(text1.getText());
			 	 text1.setText(text2.getText());
			 	 text2.setText(tempText.getText());
		 });
		
		 // Create a scene and place it in the stage
		 Scene scene = new Scene(pane, 300, 100);
		 primaryStage.setTitle("MouseEventDemo"); // Set the stage title
		 primaryStage.setScene(scene); // Place the scene in the stage
		 primaryStage.show(); // Display the stage
	 }
 
	public static void main(String[] args) {
		launch(args);
	 }
}
