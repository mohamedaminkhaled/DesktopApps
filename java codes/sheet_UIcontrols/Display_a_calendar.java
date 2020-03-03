package sheet_UIcontrols;

import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Display_a_calendar extends Application {

	@Override
	public void start(Stage stage){
		
		BorderPane bpane=new BorderPane();
		
		GregorianCalendar gc=new GregorianCalendar(2017,1,30);
		TextArea ta=new TextArea();
		ta.setText(gc.getCalendarType());
		
		bpane.setCenter(ta);
		
		Scene scene=new Scene(bpane,400,400);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
