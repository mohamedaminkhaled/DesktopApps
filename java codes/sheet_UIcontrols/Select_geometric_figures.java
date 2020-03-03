package sheet_UIcontrols;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Select_geometric_figures extends Application {	

	@Override
	public void start(Stage stage) {
		
		BorderPane bpane=new BorderPane();
		
		RadioButton rbCircle = new RadioButton("Circle");
		RadioButton rbRectangle = new RadioButton("Rectangle");
		RadioButton rbEllipse = new RadioButton("Ellipse");
		
		CheckBox chkFill=new CheckBox("Fill");
		
		HBox paneForRadioButtons=new HBox(20);
		
		paneForRadioButtons.setAlignment(Pos.CENTER);
		paneForRadioButtons.setPadding(new Insets(5, 5, 5, 5));
		paneForRadioButtons.getChildren().addAll(rbCircle, rbRectangle, 
				rbEllipse,chkFill);
		
		bpane.setBottom(paneForRadioButtons);
		
	    ToggleGroup group = new ToggleGroup();
	    rbCircle.setToggleGroup(group);
	    rbRectangle.setToggleGroup(group);
	    rbEllipse.setToggleGroup(group);
			    
	    Circle circle=new Circle(50);
	    
	    Rectangle rectangle=new Rectangle(100,70);
	    
	    Ellipse ellipse=new Ellipse(50,35);
	    
	    StackPane spane=new StackPane();
	    
	    bpane.setCenter(spane);
	    
	    rbCircle.setOnAction(e->{
	    	if(rbCircle.isSelected()){
	    		spane.getChildren().clear();
	    		circle.setStroke(Color.BLACK);
	    	    circle.setFill(Color.WHITE);
	    		spane.getChildren().add(circle);
	    	}
	    });
	    
	    rbRectangle.setOnAction(e->{
	    	if(rbRectangle.isSelected()){
	    		spane.getChildren().clear();
	    		rectangle.setStroke(Color.BLACK);
	    	    rectangle.setFill(Color.WHITE);
	    		spane.getChildren().add(rectangle);
	    	}
	    });
	    
	    rbEllipse.setOnAction(e->{
	    	if(rbEllipse.isSelected()){
	    		spane.getChildren().clear();
	    		ellipse.setStroke(Color.BLACK);
	    		ellipse.setFill(Color.WHITE);
	    		spane.getChildren().add(ellipse);
	    	}
	    });
	    
	    chkFill.setOnAction(e->{
	    	if(chkFill.isSelected())
	    		if(rbCircle.isSelected())
	    			circle.setFill(Color.GREEN);
	    		else if((rbRectangle.isSelected()))
	    				rectangle.setFill(Color.GREEN);
	    		else if(rbEllipse.isSelected())
	    			ellipse.setFill(Color.GREEN);
	    });
	    
	    Scene scene=new Scene(bpane,400,300);
	    stage.setTitle("prog2");
	    stage.setScene(scene);
	    stage.show();
	   
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
