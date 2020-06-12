package application;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.StageStyle;

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
    
    private void cleareFields() {
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
    void cancelRegisterUser(MouseEvent event) throws IOException {
    	cleareFields();
    }

    @FXML
    void confirmRegisterUser(MouseEvent event) throws IOException {    	
    	//validate user data
    	//Error message for first name
    	if(tfFisrtName.getText().isEmpty()) {
    		showErr("Error! First Name can't be Empty");
    		return;
    	}
    	
    	//Error message for last name
    	if(tfLastName.getText().isEmpty()) {
    		showErr("Error! Last Name can't be Empty");
    		return;
    	}
    	
    	//Error message for Gender
    	if(!(radioFemale.isSelected()) && !(radioMale.isSelected())) {
    		showErr("Error! your gender must be selected");
    		return;
    	}
    	
    	//Error message for Job title
    	if(!(radioAdmin.isSelected()) && !(radioClient.isSelected())) {
    		showErr("Error! your job title must be selected");
    		return;
    	}
    	
    	//Error message for User Name
    	if(tfUserName.getText().isEmpty()) {
    		showErr("Error! Username can't be Empty");
    		return;
    	}
    	    	    	    	    	
    	//Username can't be repeated in Database
    	try {
			Connection conn=DBinfo.connDB();
			Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String strSelectUsers = "SELECT `username` FROM `employees`";
			ResultSet rs = stat.executeQuery(strSelectUsers);
			rs.first();
			
			do {
				if(tfUserName.getText().equals(rs.getString("username"))){
					showErr("Error! Username already existed");
					return;
				}
			}
			while(rs.next());
			
			//Error message for Password
			if(tfPasswoard.getText().isEmpty()) {
				showErr("Error! Password can't be Empty");
				return;
			}
			
			//Error message for confirm Password
			if(tfConfirmPasswoard.getText().isEmpty()) {
				showErr("Error! Confirm Password can't be Empty");
				return;
			}

			if(!(tfConfirmPasswoard.getText().equals(tfPasswoard.getText()))) {
				showErr("Error! Pssword and Confirm Password must be tepical");
				return;
			}
			
			//After all validation tests above, Register user 
			String sql="INSERT INTO `employees`(`firstname`, `lastname`, "
					+ "`gender`, `jobtitle`, `username`, `passwoard`, `image`) "
					+ "VALUES (?,?,?,?,?,?,?)";		
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tfFisrtName.getText());
			ps.setString(2, tfLastName.getText());
			ps.setString(3, radioMale.isSelected() ? "male" : "femaile");
			ps.setString(4, radioAdmin.isSelected() ? "admin" : "user");
			ps.setString(5, tfUserName.getText());
			ps.setString(6, tfPasswoard.getText());
			ps.setString(7, userImage.getImage().getUrl());
			ps.executeUpdate();
		} catch (SQLException e) {
			ErrorServerNotFound err = new ErrorServerNotFound();
			err.errException(e);
			return;
		}
		
		//clear all fields
		cleareFields();
    	
    	//Show Success message after registration
		showSuccess();
    }
     
    void showErr(String message) throws IOException {
		Stage stage = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ErrorMessage.fxml"));
    	Parent root = loader.load();
    	
    	ErrorMessageController errorMessageController = loader.getController();
    	
		Scene scene = new Scene(root,598,203);					
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		errorMessageController.errMessage.setText(message);
		stage.show();
	}
    
    void showSuccess() throws IOException {    	
    	Stage stage = new Stage();
    	FXMLLoader loaderSuccess = new FXMLLoader(getClass().getResource("/fxml/SuccessMessage.fxml"));
		Parent root = loaderSuccess.load();
    	    	
		Scene scene = new Scene(root,584,210);					
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
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
}
