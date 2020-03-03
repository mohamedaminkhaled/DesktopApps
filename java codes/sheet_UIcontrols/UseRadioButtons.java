package sheet_UIcontrols;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UseRadioButtons extends Application {
	
	@Override
	public void start(Stage stage) {
		
		BorderPane bpane=new BorderPane();
		
		RadioButton rbRed = new RadioButton("Red");
		RadioButton rbGreen = new RadioButton("Green");
		RadioButton rbBlue = new RadioButton("Blue");
		RadioButton rbOrange = new RadioButton("Orange");
		RadioButton rbBlack = new RadioButton("Black");
		
		HBox paneForRadioButtons=new HBox(20);
		
		paneForRadioButtons.setAlignment(Pos.CENTER);
		paneForRadioButtons.setPadding(new Insets(5, 5, 5, 5));
		paneForRadioButtons.getChildren().addAll(rbRed, rbGreen, rbBlue,
				rbOrange,rbBlack);
		bpane.setTop(paneForRadioButtons);
		
	    ToggleGroup group = new ToggleGroup();
		rbRed.setToggleGroup(group);
		rbGreen.setToggleGroup(group);
		rbBlue.setToggleGroup(group);
		rbOrange.setToggleGroup(group);
		rbBlack.setToggleGroup(group);
		
		Pane paneForText=new Pane();
		
		Text text=new Text(100,50,"Programming Is Fun");
		text.setFont(Font.font("Times New Roman",FontPosture.ITALIC,25));
		paneForText.getChildren().add(text);
		
		bpane.setCenter(paneForText);
		
		Button btLeft=new Button("<=");
		Button btRight=new Button("=>");
		
		HBox paneForButtons=new HBox(20);
		paneForButtons.setAlignment(Pos.CENTER);
		paneForButtons.setPadding(new Insets(5, 5, 5, 5));
		paneForButtons.getChildren().addAll(btLeft,btRight);
		
		bpane.setBottom(paneForButtons);
		
		rbRed.setOnAction(e -> {
			 if (rbRed.isSelected()) {
				 text.setFill(Color.RED);
			 	}
			 });
			
		rbGreen.setOnAction(e -> {
			if (rbGreen.isSelected()) {
				text.setFill(Color.GREEN);
			   }
			 });
			
		rbBlue.setOnAction(e -> {
			if (rbBlue.isSelected()) {
				text.setFill(Color.BLUE);
			   }
			 });
		
		rbBlack.setOnAction(e -> {
			if (rbBlack.isSelected()) {
				text.setFill(Color.BLACK);
			   }
			 });
		
		rbOrange.setOnAction(e -> {
			if (rbOrange.isSelected()) {
				text.setFill(Color.ORANGE);
			   }
			 });
		
		btLeft.setOnAction(e->{
			text.setX(text.getX()-5);
		});
		
		btRight.setOnAction(e->{
			text.setX(text.getX()+5);
		});
		
		Scene scene=new Scene(bpane,400,200);
		stage.setTitle("prog1");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
