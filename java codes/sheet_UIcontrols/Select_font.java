package sheet_UIcontrols;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Select_font extends Application {

	@Override
	public void start(Stage stage) {
		
		BorderPane bpane=new BorderPane();
		
		HBox hb1=new HBox(20);
		HBox hb2=new HBox(20);
		
		StackPane spane=new StackPane();
		
		Text t1=new Text("Font Name");
		Text t2=new Text("Font Size");
		Text t3=new Text("Programming Is Fun");
		
		spane.getChildren().add(t3);
		
		String[] size={"1","2","3","4","5","6","7","8","9","10","11","12","13",
				"14","15","16","17","18","19","20","21","22","23","24","25",
				"26","27","28","29","30"};
		
		ComboBox<String> cmbo1=new ComboBox<>();
		cmbo1.getItems().addAll(Font.getFamilies());
		cmbo1.setValue("Book Antiqua");
		
		ComboBox<String> cmbo2=new ComboBox<>();
		ObservableList<String> items =
				 FXCollections.observableArrayList(size);
		cmbo2.getItems().addAll(items);
		cmbo2.setValue("16" );
		
		CheckBox checkBold=new CheckBox("Bold");
		CheckBox checkItalic=new CheckBox("Italic");
		
		hb1.getChildren().addAll(t1,cmbo1,t2,cmbo2);
		hb1.setPadding(new Insets(5,5,5,5));
		hb1.setAlignment(Pos.CENTER);
		
		hb2.getChildren().addAll(checkBold,checkItalic);
		hb2.setPadding(new Insets(5,5,5,5));
		hb2.setAlignment(Pos.CENTER);
		
		bpane.setTop(hb1);
		bpane.setCenter(spane);
		bpane.setBottom(hb2);
		
		cmbo1.setOnAction(e->{
			t3.setFont(Font.font(cmbo1.getValue(), Integer.parseInt(cmbo2.getValue())));
		});
		
		cmbo2.setOnAction(e->{
			t3.setFont(Font.font(cmbo1.getValue(), Integer.parseInt(cmbo2.getValue())));
		});
		
		EventHandler<ActionEvent> handler = e -> {
			 if (checkBold.isSelected() && checkItalic.isSelected()) {
				 t3.setFont(Font.font(cmbo1.getValue(), FontWeight.BOLD, 
						 FontPosture.ITALIC, Integer.parseInt(cmbo2.getValue()))); // Both check boxes checked
			 }
			 else if (checkBold.isSelected()) {
				 t3.setFont(Font.font(cmbo1.getValue(), FontWeight.BOLD, 
						 FontPosture.REGULAR, Integer.parseInt(cmbo2.getValue()))); // The Bold check box checked
			 }
			 else if (checkItalic.isSelected()) {
				 t3.setFont(Font.font(cmbo1.getValue(), FontWeight.NORMAL, 
						 FontPosture.ITALIC, Integer.parseInt(cmbo2.getValue()))); // The Italic check box checked
			 }
			 else {
				 t3.setFont(Font.font(cmbo1.getValue(), FontWeight.NORMAL, 
						 FontPosture.REGULAR, Integer.parseInt(cmbo2.getValue()))); // Both check boxes unchecked
			 }
		 };
		
		 checkBold.setOnAction(handler);
		 checkItalic.setOnAction(handler);
		
		Scene scene=new Scene(bpane,500,200);
		stage.setTitle("prog4");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
