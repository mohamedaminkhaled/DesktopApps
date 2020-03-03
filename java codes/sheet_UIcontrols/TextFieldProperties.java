package sheet_UIcontrols;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextFieldProperties extends Application {
		
	@Override
	public void start(Stage stage){
		
		BorderPane bpane=new BorderPane();
		
		Text t1=new Text("Text Field");
		Text t2=new Text("column size");
		Text t3=new Text("font size");
		
		TextField tf1=new TextField();
		tf1.setText("javaFX");
		TextField tf2=new TextField();
		tf2.setPrefColumnCount(3);
		TextField tf3=new TextField();
		tf3.setPrefColumnCount(3);
		
		HBox hb1=new HBox(20);
		hb1.setAlignment(Pos.CENTER);
		hb1.setPadding(new Insets(20,20,20,20));
		hb1.getChildren().addAll(t1,tf1);
		bpane.setTop(hb1);
		
		RadioButton rbLeft = new RadioButton("Left");
		RadioButton rbCenter = new RadioButton("Center");
		RadioButton rbRight = new RadioButton("Right");
		
		HBox paneForRadioButtons=new HBox(20);	
		paneForRadioButtons.setAlignment(Pos.CENTER);
		paneForRadioButtons.setPadding(new Insets(10, 10, 10, 10));
		paneForRadioButtons.getChildren().addAll(rbLeft, rbCenter, rbRight,
				t2,tf2,t3,tf3);
		bpane.setBottom(paneForRadioButtons);
		
	    ToggleGroup group = new ToggleGroup();
	    rbLeft.setToggleGroup(group);
	    rbCenter.setToggleGroup(group);
	    rbRight.setToggleGroup(group);
	    
	    rbLeft.setOnAction(e->{
	    	if(rbLeft.isSelected())
	    		tf1.setAlignment(Pos.BOTTOM_LEFT);
	    });
	    
	    rbCenter.setOnAction(e->{
	    	if(rbCenter.isSelected())
	    		tf1.setAlignment(Pos.CENTER);
	    });
	    
	    rbRight.setOnAction(e->{
	    	if(rbRight.isSelected())
	    		tf1.setAlignment(Pos.BOTTOM_RIGHT);
	    });
	    
	    tf2.setOnAction(e->{
	    	tf1.setPrefColumnCount(Integer.parseInt(tf2.getText()));
	    });
	    
	    tf3.setOnAction(e->{
	    	tf1.setFont(Font.font(Integer.parseInt(tf3.getText())));
	    });
	    
	    Scene scene=new Scene(bpane,500,130);
	    stage.setTitle("prog3");
	    stage.setScene(scene);
	    stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
