package sheet_UIcontrols;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Control_a_group_of_fans extends Application {
	
	Pane pane=new Pane();
	Pane pane2=new Pane();
	Pane pane3=new Pane();
	BorderPane bpane1=new BorderPane();
	BorderPane bpane2=new BorderPane();
	BorderPane bpane3=new BorderPane();
	BorderPane mainPane=new BorderPane();
	int a1=5;
	int a2=5;
	int a3=5;
	
	@Override
	public void start(Stage stage){
		
		Circle circle1=new Circle(90, 70, 50);		
		circle1.setStroke(Color.BLACK);
		circle1.setFill(Color.WHITE);
		
		pane.getChildren().add(circle1);
	
		Arc arc1=new Arc(circle1.getCenterX(), circle1.getCenterY(), circle1.getRadius()-10,
				circle1.getRadius()-10, 70, 40);
		arc1.setStroke(Color.BLACK);
		arc1.setFill(Color.GRAY);
		arc1.setType(ArcType.ROUND);
		
		Arc arc2=new Arc(circle1.getCenterX(), circle1.getCenterY(), circle1.getRadius()-10,
				circle1.getRadius()-10, 160, 40);
		arc2.setStroke(Color.BLACK);
		arc2.setFill(Color.GRAY);
		arc2.setType(ArcType.ROUND);
		
		Arc arc3=new Arc(circle1.getCenterX(), circle1.getCenterY(), circle1.getRadius()-10,
				circle1.getRadius()-10, 250, 40);
		arc3.setStroke(Color.BLACK);
		arc3.setFill(Color.GRAY);
		arc3.setType(ArcType.ROUND);
		
		Arc arc4=new Arc(circle1.getCenterX(), circle1.getCenterY(), circle1.getRadius()-10,
				circle1.getRadius()-10, 340, 40);
		arc4.setStroke(Color.BLACK);
		arc4.setFill(Color.GRAY);
		arc4.setType(ArcType.ROUND);
		pane.getChildren().addAll(arc1,arc2,arc3,arc4);
		
		Button btPlay=new Button("play");
		Button btPause=new Button("pause");
		Button btReverse=new Button("reverse");
		
		Slider slHorizontal = new Slider();
		 slHorizontal.setShowTickLabels(false);
		 slHorizontal.setShowTickMarks(false);
		
		HBox hb1=new HBox(20);
		hb1.getChildren().addAll(btPlay,btPause,btReverse);
		hb1.setPadding(new Insets(5,5,5,5));
		hb1.setAlignment(Pos.CENTER);
		
		bpane1.setCenter(pane);
		bpane1.setTop(hb1);
		bpane1.setBottom(slHorizontal);
		bpane1.setStyle("-fx-border-color: green");
		
		EventHandler<ActionEvent> eventHandler =e->{
			arc1.setStartAngle(arc1.getStartAngle()+a1);
			arc2.setStartAngle(arc2.getStartAngle()+a1);
			arc3.setStartAngle(arc3.getStartAngle()+a1);
			arc4.setStartAngle(arc4.getStartAngle()+a1);
		};

		Timeline animation=new Timeline(new KeyFrame( Duration.millis(300),eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		
		btPlay.setOnAction(e->animation.play());
		
		btPause.setOnAction(e->animation.pause());
		
		btReverse.setOnAction(e->{
			a1*=-1;
		});
		
		slHorizontal.valueProperty().addListener(e->{
			animation.setRate(slHorizontal.getValue());
		});
		
		Circle circle2=new Circle(90, 70, 50);		
		circle2.setStroke(Color.BLACK);
		circle2.setFill(Color.WHITE);
		
		pane2.getChildren().add(circle2);
	
		Arc arc1_2=new Arc(circle2.getCenterX(), circle2.getCenterY(), circle2.getRadius()-10,
				circle2.getRadius()-10, 70, 40);
		arc1_2.setStroke(Color.BLACK);
		arc1_2.setFill(Color.GRAY);
		arc1_2.setType(ArcType.ROUND);
		
		Arc arc2_2=new Arc(circle2.getCenterX(), circle2.getCenterY(), circle2.getRadius()-10,
				circle2.getRadius()-10, 160, 40);
		arc2_2.setStroke(Color.BLACK);
		arc2_2.setFill(Color.GRAY);
		arc2_2.setType(ArcType.ROUND);
		
		Arc arc3_2=new Arc(circle2.getCenterX(), circle2.getCenterY(), circle2.getRadius()-10,
				circle2.getRadius()-10, 250, 40);
		arc3_2.setStroke(Color.BLACK);
		arc3_2.setFill(Color.GRAY);
		arc3_2.setType(ArcType.ROUND);
		
		Arc arc4_2=new Arc(circle2.getCenterX(), circle2.getCenterY(), circle2.getRadius()-10,
				circle2.getRadius()-10, 340, 40);
		arc4_2.setStroke(Color.BLACK);
		arc4_2.setFill(Color.GRAY);
		arc4_2.setType(ArcType.ROUND);
		pane2.getChildren().addAll(arc1_2,arc2_2,arc3_2,arc4_2);
		
		Button btPlay2=new Button("play");
		Button btPause2=new Button("pause");
		Button btReverse2=new Button("reverse");
		
		Slider slHorizontal2 = new Slider();
		 slHorizontal2.setShowTickLabels(false);
		 slHorizontal2.setShowTickMarks(false);
		
		HBox hb2=new HBox(20);
		hb2.getChildren().addAll(btPlay2,btPause2,btReverse2);
		hb2.setPadding(new Insets(5,5,5,5));
		hb2.setAlignment(Pos.CENTER);
		
		bpane2.setCenter(pane2);
		bpane2.setTop(hb2);
		bpane2.setBottom(slHorizontal2);
		bpane2.setStyle("-fx-border-color: green");
		
		EventHandler<ActionEvent> eventHandler2 =e->{
			arc1_2.setStartAngle(arc1_2.getStartAngle()+a2);
			arc2_2.setStartAngle(arc2_2.getStartAngle()+a2);
			arc3_2.setStartAngle(arc3_2.getStartAngle()+a2);
			arc4_2.setStartAngle(arc4_2.getStartAngle()+a2);
		};

		Timeline animation2=new Timeline(new KeyFrame( Duration.millis(300),eventHandler2));
		animation2.setCycleCount(Timeline.INDEFINITE);
		
		btPlay2.setOnAction(e->animation2.play());
		
		btPause2.setOnAction(e->animation2.pause());
		
		btReverse2.setOnAction(e->{
			a2*=-1;
		});
		
		slHorizontal2.valueProperty().addListener(e->{
			animation2.setRate(slHorizontal2.getValue());
		});
		
		Circle circle3=new Circle(90, 70, 50);		
		circle3.setStroke(Color.BLACK);
		circle3.setFill(Color.WHITE);
		
		pane3.getChildren().add(circle3);
	
		Arc arc1_3=new Arc(circle3.getCenterX(), circle3.getCenterY(), circle3.getRadius()-10,
				circle3.getRadius()-10, 70, 40);
		arc1_3.setStroke(Color.BLACK);
		arc1_3.setFill(Color.GRAY);
		arc1_3.setType(ArcType.ROUND);
		
		Arc arc2_3=new Arc(circle3.getCenterX(), circle3.getCenterY(), circle3.getRadius()-10,
				circle3.getRadius()-10, 160, 40);
		arc2_3.setStroke(Color.BLACK);
		arc2_3.setFill(Color.GRAY);
		arc2_3.setType(ArcType.ROUND);
		
		Arc arc3_3=new Arc(circle3.getCenterX(), circle3.getCenterY(), circle3.getRadius()-10,
				circle3.getRadius()-10, 250, 40);
		arc3_3.setStroke(Color.BLACK);
		arc3_3.setFill(Color.GRAY);
		arc3_3.setType(ArcType.ROUND);
		
		Arc arc4_3=new Arc(circle3.getCenterX(), circle3.getCenterY(), circle3.getRadius()-10,
				circle3.getRadius()-10, 340, 40);
		arc4_3.setStroke(Color.BLACK);
		arc4_3.setFill(Color.GRAY);
		arc4_3.setType(ArcType.ROUND);
		pane3.getChildren().addAll(arc1_3,arc2_3,arc3_3,arc4_3);
		
		Button btPlay3=new Button("play");
		Button btPause3=new Button("pause");
		Button btReverse3=new Button("reverse");
		
		Slider slHorizontal3 = new Slider();
		 slHorizontal3.setShowTickLabels(false);
		 slHorizontal3.setShowTickMarks(false);
		
		HBox hb3=new HBox(20);
		hb3.getChildren().addAll(btPlay3,btPause3,btReverse3);
		hb3.setPadding(new Insets(5,5,5,5));
		hb3.setAlignment(Pos.CENTER);
		
		bpane3.setCenter(pane3);
		bpane3.setTop(hb3);
		bpane3.setBottom(slHorizontal3);
		bpane3.setStyle("-fx-border-color: green");
		
		EventHandler<ActionEvent> eventHandler3 =e->{
			arc1_3.setStartAngle(arc1_3.getStartAngle()+a3);
			arc2_3.setStartAngle(arc2_3.getStartAngle()+a3);
			arc3_3.setStartAngle(arc3_3.getStartAngle()+a3);
			arc4_3.setStartAngle(arc4_3.getStartAngle()+a3);
		};

		Timeline animation3=new Timeline(new KeyFrame( Duration.millis(300),eventHandler3));
		animation3.setCycleCount(Timeline.INDEFINITE);
		
		btPlay3.setOnAction(e->animation3.play());
		
		btPause3.setOnAction(e->animation3.pause());
		
		btReverse3.setOnAction(e->{
			a3*=-1;
		});
		
		slHorizontal3.valueProperty().addListener(e->{
			animation3.setRate(slHorizontal3.getValue());
		});
		
		Button btStartAll=new Button("StartAll");
		Button btStopAll=new Button("StopAll");
		
		btStartAll.setOnAction(e->{
			animation.play();
			animation2.play();
			animation3.play();
		});
		
		btStopAll.setOnAction(e->{
			animation.pause();
			animation2.pause();
			animation3.pause();
		});
		
		HBox bottomBox=new HBox(20);
		bottomBox.getChildren().addAll(btStartAll,btStopAll);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(5,5,5,5));
		bottomBox.setStyle("-fx-border-color: green");
		
		HBox mainBox=new HBox(20);
		mainBox.getChildren().addAll(bpane1,bpane2,bpane3);
		mainBox.setStyle("-fx-border-color: green");
		mainBox.setPadding(new Insets(5,5,5,5));
		mainBox.setAlignment(Pos.CENTER);
			
		mainPane.setCenter(mainBox);
		mainPane.setBottom(bottomBox);
		
		Scene scene=new Scene(mainPane,700,250);
		stage.setTitle("prog5");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
