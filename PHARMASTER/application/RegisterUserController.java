package application;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RegisterUserController {

    @FXML
    private ImageView userImage;

    @FXML
    private Button btnChoosePicture;

    @FXML
    private TextField tfFisrtName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfUserName;

    @FXML
    private Button dtnCancel;

    @FXML
    private Button btnConfirm;

    @FXML
    private PasswordField tfPasswoard;

    @FXML
    private PasswordField tfConfirmPasswoard;

    @FXML
    private HBox radioGroup;

    @FXML
    private RadioButton radioMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton radioFemale;
    
    @FXML
    private HBox radioGroup1;

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private ToggleGroup jobTitle;

    @FXML
    private RadioButton radioClient;
    
    //FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPages/RegisterUser.fxml"));
    //FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("AdminPages/Dashboard.fxml"));
	
	//Parent root=FXMLLoader.load(getClass().getResource("welcom.fxml"));
	
	//MainController adminPage = loader.getController();
	
    @FXML
    void cancelRegisterUser(MouseEvent event) throws IOException {
    	tfFisrtName.setText("");
    	tfLastName.setText("");
    	tfUserName.setText("");
    	tfPasswoard.setText("");
    	tfConfirmPasswoard.setText("");
    	radioMale.setSelected(false);
    	radioFemale.setSelected(false);
    	radioAdmin.setSelected(false);
    	radioClient.setSelected(false);
    	userImage.setImage(new Image("/resources/avatar2.jpg"));
    }

    @FXML
    void confirmRegisterUser(MouseEvent event) throws SQLException {
    	
		Connection conn=DBinfo.connDB();
		String sql="INSERT INTO `employees`(`firstname`, `lastname`, "
    			+ "`gender`, `jobtitle`, `username`, `passwoard`, `image`) "
				+ "VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps;
		
		ps = conn.prepareStatement(sql);

		ps.setString(1, tfFisrtName.getText());
		ps.setString(2, tfLastName.getText());
		ps.setString(3, radioMale.isSelected() ? "male" : "femaile");
		ps.setString(4, radioAdmin.isSelected() ? "admin" : "user");
		ps.setString(5, tfUserName.getText());
		ps.setString(6, tfPasswoard.getText());
		ps.setString(7, userImage.getImage().getUrl());
		ps.executeUpdate();
		System.out.println("Row inserted!");
    	
    }

    @FXML
    void openImageChooser(MouseEvent event) {
    	
    	FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new
			        FileChooser.ExtensionFilter("jpg", "*.jpg"),
		            new FileChooser.ExtensionFilter("JPGE", "*.JPGE"),
		            new FileChooser.ExtensionFilter("png", "*.png"));
		
		Stage stage = new Stage();
		File selectedFile = fileChooser.showOpenDialog(stage);
	     
		 if(selectedFile != null) {
			 String path = selectedFile.getAbsolutePath();		     
			 path = path.replace("\\","/");
			 Image image = new Image(new File(path).toURI().toString());
			 userImage.setImage(image);
		 }
    }
    
    public void errException(SQLException e) {
		System.out.println("Error: "+e.getMessage());
		System.out.println("code: "+e.getErrorCode());
		System.out.println("state: "+e.getSQLState());
		System.out.println("message: "+e.getLocalizedMessage());
		
	}

}
