package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ModifyUserController {

    @FXML
    TextField tfUserName;

    @FXML
    PasswordField tfPassword;

    @FXML
    PasswordField tfNewPassword;

    private void cleareFields() {
    	tfUserName.setText("");
    	tfPassword.setText("");
    	tfNewPassword.setText("");
    }
    
    @FXML
    void cancelModifyUser(MouseEvent event) throws IOException {
    	cleareFields();
    }
    
    @FXML
    void deleteUser(MouseEvent event) throws IOException, SQLException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPages/RegisterUser.fxml"));
    	Parent root = loader.load();
    	RegisterUserController registerUserController = loader.getController();
    	
    	Connection conn=DBinfo.connDB();
    	
    	//validate user data
    	//Error message for Username
    	if(tfUserName.getText().isEmpty()) {
    		registerUserController.showErr("Error! Username can't be Empty");
    		return;
    	}
    	
    	//Error message for Password
    	if(tfPassword.getText().isEmpty()) {
    		registerUserController.showErr("Error! Password can't be Empty");
    		return;
    	}
    	
    	//Username and Password must be found in Database
		Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String strSelectUsers = "SELECT `id` FROM `employees` WHERE `username` = '"+tfUserName.getText()+"' AND `passwoard` = '"+tfPassword.getText()+"'";
		ResultSet rs = stat.executeQuery(strSelectUsers);		
		rs.first();
		if(rs.getRow() == 0) {
			registerUserController.showErr("Error! user not found");
			return;
		}
    	    	
		//After all validations above, update user data
		String strUpdate ="DELETE FROM `employees` WHERE `username` =? AND `passwoard` =?";
				
		PreparedStatement ps = conn.prepareStatement(strUpdate);
		ps.setString(1, tfUserName.getText());
		ps.setString(2, tfPassword.getText());
		ps.executeUpdate();
		
		//clear all fields
		cleareFields();
		    	
		//Show Success message after registration
		registerUserController.showSuccess();
    }

    @FXML
    void confirmModifyUser(MouseEvent event) throws SQLException, IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPages/RegisterUser.fxml"));
    	Parent root = loader.load();
    	RegisterUserController registerUserController = loader.getController();
    	
    	Connection conn=DBinfo.connDB();
    	
    	//validate user data
    	//Error message for Username
    	if(tfUserName.getText().isEmpty()) {
    		registerUserController.showErr("Error! Username can't be Empty");
    		return;
    	}
    	
    	//Error message for Password
    	if(tfPassword.getText().isEmpty()) {
    		registerUserController.showErr("Error! Password can't be Empty");
    		return;
    	}
    	
    	//Error message for Newpassword
    	if(tfNewPassword.getText().isEmpty()) {
    		registerUserController.showErr("Error! New Password can't be Empty");
    		return;
    	}
    	    	
    	//Username and Password must be found in Database
		Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String strSelectUsers = "SELECT `id` FROM `employees` WHERE `username` = '"+tfUserName.getText()+"' AND `passwoard` = '"+tfPassword.getText()+"'";
		ResultSet rs = stat.executeQuery(strSelectUsers);
		
		if(!(rs.first())) {
			registerUserController.showErr("Error! user not found");
			return;
		}
    	    	
		//After all validations above, update user data
		String strUpdate ="UPDATE `employees` set `passwoard`=? WHERE `username` =? AND `passwoard` =?";  
				
		PreparedStatement ps = conn.prepareStatement(strUpdate);
		ps.setString(1, tfNewPassword.getText());
		ps.setString(2, tfUserName.getText());
		ps.setString(3, tfPassword.getText());
		ps.executeUpdate();
		
		//clear all fields
		cleareFields();
		    	
		//Show Success message after registration
		registerUserController.showSuccess();
    }
}
