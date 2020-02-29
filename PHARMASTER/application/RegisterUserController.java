package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPages/Admin.fxml"));
    FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("AdminPages/Dashboard.fxml"));
	
	//Parent root=FXMLLoader.load(getClass().getResource("welcom.fxml"));
	
	MainController adminPage = loader.getController();
	

    @FXML
    void cancelRegisterUser(MouseEvent event) throws IOException {
    	Parent root = dashboardLoader.load();
    	adminPage.borderPaneContent.setCenter(root);
    }

    @FXML
    void confirmRegisterUser(MouseEvent event) {

    }

    @FXML
    void openImageChooser(MouseEvent event) {

    }
    
    private void loadPage(String ui) {
    	Parent root = null;
    	try {
			root=FXMLLoader.load(getClass().getResource(ui+".fxml"));
			
    	}catch(Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public void errException(SQLException e) {
		System.out.println("Error: "+e.getMessage());
		System.out.println("code: "+e.getErrorCode());
		System.out.println("state: "+e.getSQLState());
		System.out.println("message: "+e.getLocalizedMessage());
		
	}

}
