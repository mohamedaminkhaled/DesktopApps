package application;

import java.io.File;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ModifyUserController {

    @FXML
    private ImageView userImage;

    @FXML
    private Button btnChoosImage;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfUserName;

    @FXML
    private Button btnCancelModifyUser;

    @FXML
    private Button btnModify;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private PasswordField tfConfirmPasswoard;

    @FXML
    private RadioButton genderMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton genderFemale;

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private ToggleGroup gender1;

    @FXML
    private RadioButton radioPharmacist;
    

    @FXML
    void cancelModifyUser(MouseEvent event) {
    	tfFirstName.setText("");
    	tfLastName.setText("");
    	tfUserName.setText("");
    	tfPassword.setText("");
    	tfConfirmPasswoard.setText("");
    	genderMale.setSelected(false);
    	genderFemale.setSelected(false);
    	radioAdmin.setSelected(false);
    	radioPharmacist.setSelected(false);
    	userImage.setImage(new Image("/resources/avatar2.jpg"));
    }

    @FXML
    void confirmModifyUser(MouseEvent event) throws SQLException {
    	Connection conn=DBinfo.connDB();
		
		String strUpdate ="UPDATE `employees`" 
				+ "SET `firstname`=?, `lastname`=?, `gender`=?,"  
				+ "`jobtitle`=?, `username`=?, `username`=?, `image`=? "
				+ " WHERE username =? AND passwoard =?";  
		
		PreparedStatement ps;
		
		ps = conn.prepareStatement(strUpdate);

		ps.setString(1, tfFirstName.getText());
		ps.setString(2, tfLastName.getText());
		ps.setString(3, genderMale.isSelected() ? "male" : "femaile");
		ps.setString(4, radioAdmin.isSelected() ? "admin" : "user");
		ps.setString(5, tfUserName.getText());
		ps.setString(6, tfPassword.getText());
		ps.setString(7, userImage.getImage().getUrl());
		ps.setString(8, tfUserName.getText());
		ps.setString(9, tfPassword.getText());
		ps.executeUpdate();
		System.out.println("1 Row updated!");
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
