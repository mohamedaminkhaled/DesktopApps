import java.io.File;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.util.Duration;

 public class MediaDemo extends Application {
	 	
	 public String path=null;
	 public File selectedFile=null;
	 Media media=null;
	 MediaPlayer mediaPlayer=null;
	 MediaView mediaView=null;
	 BorderPane pane;
	 Slider slVolume;
	 Slider seekSlider;
	 int height=500,width=800,prefHeight=60;
	 Scene scene;
	 
	 @Override // Override the start method in the Application class
	 public void start(Stage primaryStage) {
		 
		 Tooltip openToolTip=new Tooltip();
		 openToolTip.setText("Open");
		 openToolTip.setFont(new Font(14));
		 
		 Tooltip playToolTip=new Tooltip();
		 playToolTip.setText("Play");
		 playToolTip.setFont(new Font(14));
		 
		 Tooltip pauseToolTip=new Tooltip();
		 pauseToolTip.setText("Pause");
		 pauseToolTip.setFont(new Font(14));
		 
		 Tooltip rewindToolTip=new Tooltip();
		 rewindToolTip.setText("Rewind");
		 rewindToolTip.setFont(new Font(14));
		 
		 Tooltip backwardToolTip=new Tooltip();
		 backwardToolTip.setText("Backward");
		 backwardToolTip.setFont(new Font(14));
		 
		 Tooltip forwardToolTip=new Tooltip();
		 forwardToolTip.setText("Forward");
		 forwardToolTip.setFont(new Font(14));
		 
		 Tooltip muteToolTip=new Tooltip();
		 muteToolTip.setText("Mute");
		 muteToolTip.setFont(new Font(14));
		 
		 Tooltip unmuteToolTip=new Tooltip();
		 unmuteToolTip.setText("Unmute");
		 unmuteToolTip.setFont(new Font(14));
		 	 
		 Button playButton = new Button();
		 playButton.setScaleX(0.8);
		 playButton.setScaleY(0.8);
		 playButton.setTooltip(playToolTip);
		 playButton.setGraphic(new ImageView(new Image(
				 getClass().getResourceAsStream("playbutton_default.png"))));
		 playButton.setStyle("-fx-background-color:black");
		 
		 Button open=new Button();
		 open.setScaleX(1.2);
		 open.setScaleY(1.2);
		 open.setTooltip(openToolTip);
		 open.setGraphic(new ImageView(new Image(
				 getClass().getResourceAsStream("open.png"))));
		 open.setStyle("-fx-background-color:black");
		 
		 Button rewindButton = new Button();
		 rewindButton.setScaleX(1);
		 rewindButton.setScaleY(1);
		 rewindButton.setTooltip(rewindToolTip);
		 rewindButton.setGraphic(new ImageView(new Image(
				 getClass().getResourceAsStream("repeaton.png"))));
		 rewindButton.setStyle("-fx-background-color:black");
		 
		 Button btnForward=new Button();
		 btnForward.setScaleX(1);
		 btnForward.setScaleY(1);
		 btnForward.setTooltip(forwardToolTip);
		 btnForward.setGraphic(new ImageView(new Image(
				 getClass().getResourceAsStream("NextVisNormal.png"))));
		 btnForward.setStyle("-fx-background-color:black");
		 
		 Button btnBackward=new Button();
		 btnBackward.setScaleX(1);
		 btnBackward.setScaleY(1);
		 btnBackward.setTooltip(backwardToolTip);
		 btnBackward.setGraphic(new ImageView(new Image(
				 getClass().getResourceAsStream("PrevVisNormal.png"))));
		 btnBackward.setStyle("-fx-background-color:black");
		 
		 Button sound=new Button();
		 sound.setScaleX(1);
		 sound.setScaleY(1);
		 sound.setTooltip(muteToolTip);
		 sound.setGraphic(new ImageView(new Image(
				 getClass().getResourceAsStream("m_mute_no.png"))));
		 sound.setStyle("-fx-background-color:black");
		 
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.getExtensionFilters().addAll(new
			        FileChooser.ExtensionFilter("MP4", "*.mp4"),
		            new FileChooser.ExtensionFilter("Mp3", "*.mp3"));
		 
		 open.setOnAction(e->{
			 
			 if(media!=null) {
				 mediaPlayer.pause();
				 playButton.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("playbutton_default.png"))));
				 playButton.setTooltip(pauseToolTip);
			 }
			 
			 selectedFile = fileChooser.showOpenDialog(primaryStage);
		     
			 if(selectedFile != null) {
				 path = selectedFile.getAbsolutePath();		     
				 path = path.replace("\\","/");
				 media = new Media(new File(path).toURI().toString());
				 mediaPlayer = new MediaPlayer(media);
				 mediaView = new MediaView(mediaPlayer);
				 mediaView.fitHeightProperty().bind(scene.heightProperty());
				 mediaView.fitWidthProperty().bind(scene.widthProperty());
				 pane.setCenter(mediaView);
						 
				 mediaPlayer.play();
				 playButton.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("pausebutton_default.png"))));
				 playButton.setTooltip(pauseToolTip);
						 
				 mediaPlayer.volumeProperty().bind(
						 slVolume.valueProperty().divide(100));
				 
				 seekSlider.maxProperty().bind(Bindings.createDoubleBinding(
				         () -> mediaPlayer.getTotalDuration().toSeconds(),
			              mediaPlayer.totalDurationProperty()));
		
				 mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
			              seekSlider.setValue(newValue.toSeconds());
						        });
		
			     seekSlider.setOnMousePressed((MouseEvent event) -> {
					      mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
						        });
			 }
		     else {
				 mediaPlayer.play();
				 playButton.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("pausebutton_default.png"))));
				 playButton.setTooltip(pauseToolTip);
			 }
		 }); 
		 
		 playButton.setOnAction(e -> {
			 if (media != null) {
				 if(mediaPlayer.getRate() !=0) {
					mediaPlayer.setRate(0);
					playButton.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("playbutton_default.png"))));
					playButton.setTooltip(playToolTip);
				 }
				 else {
					 mediaPlayer.setRate(1);
					 mediaPlayer.play();
					 playButton.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("pausebutton_default.png"))));
					 playButton.setTooltip(pauseToolTip);
				 } 
			 }
			 else return;
		});
		 
		 playButton.setOnMouseEntered(e -> {
			 if (media != null) {
				 if(mediaPlayer.getRate() !=0) {
					playButton.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("playbutton_hover.png"))));
				 }
				 else {
					 playButton.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("pausebutton_hover.png"))));
				 } 
			 }
			 else return;
		});
		 
		 playButton.setOnMouseExited(e -> {
			 if (media != null) {
				 if(mediaPlayer.getRate() !=0) {
					playButton.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("playbutton_default.png"))));
				 }
				 else {
					 playButton.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("pausebutton_default.png"))));
				 } 
			 }
			 else return;
		});
		
		 rewindButton.setOnAction(e -> {
			 mediaPlayer.seek(Duration.ZERO);
			 mediaPlayer.play();
			 playButton.setGraphic(new ImageView(new Image(
					 getClass().getResourceAsStream("pausebutton_default.png"))));
			 playButton.setTooltip(pauseToolTip);
		 });
		 
		 sound.setOnAction(e -> {
			 if(mediaPlayer.isMute()) {
				 mediaPlayer.setMute(false);
				 sound.setTooltip(muteToolTip);
				 sound.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("m_mute_no.png"))));
			 }
			 else {
				 mediaPlayer.setMute(true);
				 sound.setTooltip(unmuteToolTip);
				 sound.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("m_mute_do.png"))));
			 }
		 });
		 
		 sound.setOnMouseEntered(e -> {
			 if(mediaPlayer.isMute()) {
				 sound.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("m_mute_do_hov.png"))));
			 }
			 else {
				 sound.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("m_mute_hov.png"))));
			 }
		 });
		 
		 sound.setOnMouseExited(e -> {
			 if(mediaPlayer.isMute()) {
				 sound.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("m_mute_do.png"))));
			 }
			 else {
				 sound.setGraphic(new ImageView(new Image(
						 getClass().getResourceAsStream("m_mute_no.png"))));
			 }
		 });
		 
		 btnBackward.setOnAction(e -> {
			 mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(5)));
		 });
		 
		 btnBackward.setOnMouseEntered(e -> {
			 btnBackward.setGraphic(new ImageView(new Image(
					 getClass().getResourceAsStream("PrevVisPressedMouseOver.png"))));
		 });
		 
		 btnBackward.setOnMouseExited(e -> {
			 btnBackward.setGraphic(new ImageView(new Image(
					 getClass().getResourceAsStream("PrevVisNormal.png"))));
		 });
		 
		 btnForward.setOnAction(e -> {
			 mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(5)));
		 });
		 
		 btnForward.setOnMouseEntered(e -> {
			 btnForward.setGraphic(new ImageView(new Image(
					 getClass().getResourceAsStream("NextVisMouseOver.png"))));
		 });
		 
		 btnForward.setOnMouseExited(e -> {
			 btnForward.setGraphic(new ImageView(new Image(
					 getClass().getResourceAsStream("NextVisNormal.png"))));
		 });
				 
		 slVolume = new Slider();
		 slVolume.setPrefWidth(150);
		 slVolume.setMaxWidth(Region.USE_PREF_SIZE);
		 slVolume.setMinWidth(30);
		 slVolume.setValue(50);
		 slVolume.setStyle("-fx-background-color:black");
		  
		 seekSlider=new Slider();
		 seekSlider.setPrefWidth(2000);
		 seekSlider.setStyle("-fx-background-color:black");
		 		  	 		 		 
		 HBox hBoxCenter = new HBox();
		 hBoxCenter.setAlignment(Pos.CENTER);
		 hBoxCenter.getChildren().addAll(btnBackward, playButton, btnForward);
		 hBoxCenter.setStyle("-fx-Background-color:BLACK");
		 		 
		 HBox hBoxLeft = new HBox(5);
		 hBoxLeft.setAlignment(Pos.CENTER_LEFT);
		 hBoxLeft.getChildren().addAll(open, rewindButton);
		 hBoxLeft.setStyle("-fx-Background-color:BLACK");
		 
		 HBox hBoxRight = new HBox(5);
		 hBoxRight.setAlignment(Pos.CENTER_LEFT);
		 hBoxRight.getChildren().addAll(sound, slVolume);
		 hBoxRight.setStyle("-fx-Background-color:BLACK");
		 
		 BorderPane bPaneBottom = new BorderPane();
		 bPaneBottom.setLeft(hBoxLeft);
		 bPaneBottom.setCenter(hBoxCenter);
		 bPaneBottom.setRight(hBoxRight);
		 
		 HBox hBoxSeek = new HBox(5);
		 hBoxSeek.setPrefHeight(3);
		 hBoxSeek.setAlignment(Pos.CENTER);
		 hBoxSeek.getChildren().add(seekSlider);
		 hBoxSeek.setStyle("-fx-Background-color:BLACK");
		 
		 VBox vb1 = new VBox(0);
		 vb1.setPrefHeight(30);
		 vb1.getChildren().addAll(hBoxSeek, bPaneBottom);
		 vb1.setPadding(new Insets(0, 5, 0, 5));
		 vb1.setStyle("-fx-Background-color:BLACK");
		 
		 pane = new BorderPane();
		 pane.setStyle("-fx-Background-color:BLACK");
		 scene = new Scene(pane, width, height);
		 
		 Image image=new Image(getClass().getResourceAsStream("media2.jpg"));
		 ImageView imageView=new ImageView(image);
		 imageView.fitHeightProperty().bind(scene.heightProperty().subtract(prefHeight));
		 imageView.fitWidthProperty().bind(scene.widthProperty());
		 
		 pane.setCenter(imageView);
		 pane.setBottom(vb1);
				 
		 primaryStage.setTitle("MediaDemo"); 
		 primaryStage.setScene(scene); 
		 primaryStage.show(); 
	 }
	public static void main(String[] args) {
		launch(args);
	}
}
