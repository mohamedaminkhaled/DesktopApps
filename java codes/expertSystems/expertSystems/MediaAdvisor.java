package expertSystems;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class MediaAdvisor extends Application {
	
	String stimulus_sitiuation;
	String stimulus_response;
	
@Override
	public void start(Stage stage) {
	
	  String[] environment = {"papers", "manuals", "documents", "textbooks", "pictures", "illustrations", 
			 "photographs", "diagrams", "machines","buildings "," tools"," numbers"," formulas","computer programs "};
	 
	  String[]  job = {"lecturing", "advising", "counselling", "building",
			  "repairing "," troubleshooting","writing "," typing","drawing ",
			  " evaluating"," reasoning"," investigating"};
	 
	  String[] feedback = {"required", "not required"};
	  
	   ComboBox<String> cbo1 = new ComboBox<>(); // environment
	   ComboBox<String> cbo2 = new ComboBox<>(); // stimulus_situation
	   ComboBox<String> cbo3 = new ComboBox<>(); // feedback
	   
	   ObservableList<String> environment_items =
				 FXCollections.observableArrayList(environment);
				 cbo1.getItems().addAll(environment_items);
				 cbo1.setValue("papers");
				 
	  ObservableList<String> job_items =
				FXCollections.observableArrayList(job);
				cbo2.getItems().addAll(job_items);
				cbo2.setValue("lecturing");
				
	 ObservableList<String> feedback_items =
				FXCollections.observableArrayList(feedback);
				 cbo3.getItems().addAll(feedback_items);
				 cbo3.setValue("required");
				 
	Label lb1=new Label("environment",cbo1);
	lb1.setContentDisplay(ContentDisplay.RIGHT);
	
	Label lb2=new Label("job",cbo2);
	lb2.setContentDisplay(ContentDisplay.RIGHT);
	
	Label lb3=new Label("feedback",cbo3);
	lb3.setContentDisplay(ContentDisplay.RIGHT);
	
	 BorderPane bpane=new BorderPane();
	 
	 HBox hbox1=new  HBox(20);
	 
	 hbox1.getChildren().addAll(lb1,lb2,lb3);
	 hbox1.setAlignment(Pos.CENTER);
	 hbox1.setPadding(new Insets(10,10,10,10));
	 
	 bpane.setTop(hbox1);
	 
	  Button btAdvice=new Button("Selected Media");
	  
	  TextArea ta = new TextArea();
		ta.setFont(new Font("Serif", 14));
		
		 ta.setWrapText(false);
		 ta.setEditable(false);
		 ta.setMaxWidth(200);
		 ta.setMaxHeight(20);
		 ta.setPadding(new Insets(5,5,5,5));
		 
		 HBox hbox2=new HBox(30);
		 hbox2.getChildren().addAll(btAdvice,ta);
		 hbox2.setAlignment(Pos.CENTER);
		 
		 bpane.setCenter(hbox2);
		 
		  btAdvice.setOnAction(e->{
			
			  selectStimulusSitiuation(cbo1);
			  selectStimuluResponse(cbo2);
			  
			  ta.setText( selectMedia(stimulus_sitiuation,stimulus_response,cbo3));
		  });
		 
	 Scene scene=new Scene(bpane,700,200);
	 stage.setTitle("MediaAdvisor");
	 stage.setScene(scene);
	 stage.show();
	 
		
	}

public void selectStimuluResponse(ComboBox<String> cbo2) {
	
	String cbo2_value=cbo2.getValue();
		 
	    // knowledge base , part 2
	
		// Rule 5
		 
		 if(cbo2_value=="lecturing"||cbo2_value=="advising"||cbo2_value=="counselling")
			 stimulus_response="oral";
		 
		 //Rule 6
		 
		 if(cbo2_value=="building"||cbo2_value=="repairing"||cbo2_value=="troubleshooting")
			 stimulus_response="hands-on";
 
		 //Rule 7
		 
		 if(cbo2_value=="writing"||cbo2_value=="typing"||cbo2_value=="drawing")
			 stimulus_sitiuation="documented";
		 
	 //Rule 8
		 
		 if(cbo2_value=="evaluating"||cbo2_value=="reasoning"||cbo2_value=="investigating")
			 stimulus_response="analytical";
}

public void selectStimulusSitiuation(ComboBox<String> cbo1) {
	// knowledge base , part 1
	
	 String cbo1_value=cbo1.getValue();
	 
	// Rule 1
	 
	 if(cbo1_value=="papers"||cbo1_value=="manuals"||cbo1_value=="documents"||cbo1_value=="textbooks")
		 stimulus_sitiuation="verbal";
	 
	 //Rule 2
	 
	 if(cbo1_value=="pictures"||cbo1_value=="illustrations"||cbo1_value=="photographs"||cbo1_value=="diagrams")
		 stimulus_sitiuation="visual";
	 
	 //Rule 3
	 
	 if(cbo1_value=="machines"||cbo1_value=="buildings"||cbo1_value=="tools")
		 stimulus_sitiuation="physical objec";
	 
	 //Rule 4
	 
	 if(cbo1_value=="numbers"||cbo1_value=="formulas"||cbo1_value=="computer programs")
		 stimulus_sitiuation="symbolic";
}

public String selectMedia(String stimulusSituation,String stimulusResponse,ComboBox<String> cbo3){
	
	//knowledge 	base , part 3
	
	String media = null;
	
	String selectedFeedback=cbo3.getValue();
	
	//Rule 9
	
	if(stimulusSituation=="physical object"&&stimulusResponse=="hands-on"&&selectedFeedback=="required")
		media="workshop";
	
	//Rule 10
	
	if(stimulusSituation=="symbolic"&&stimulusResponse=="analytical"&&selectedFeedback=="required")
		media="lecture – tutorial";
	
	//Rule 11
	
	if(stimulusSituation=="visual"&&stimulusResponse=="documented"&&selectedFeedback=="not required")
		media="videocassette";
	
	//Rule 12
	
	if(stimulusSituation=="visual"&&stimulusResponse=="oral"&&selectedFeedback=="required")
		media="lecture – tutorial";
	
	//Rule 13
	
	if(stimulusSituation=="verbal"&&stimulusResponse=="analytical"&&selectedFeedback=="required")
		media="lecture – tutorial";
	
	//Rule 14
	
	if(stimulusSituation=="verbal"&&stimulusResponse=="oral"&&selectedFeedback=="required")
		media="role-play exercises";
	return media;
}

	public static void main(String[] args) {
			launch(args);
	}
}
