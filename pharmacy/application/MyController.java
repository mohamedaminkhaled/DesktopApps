package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyController {

	double x, y;
	
    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_passwoard;
	
    @FXML
    private Label client_name;
	
    @FXML
    private HBox hbox_title;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private BorderPane borderPaneEdit;

    @FXML
    void dragged(MouseEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setX(event.getScreenX() -x);
    	stage.setY(event.getScreenY() -y);
    }

    @FXML
    void pressed(MouseEvent event) {
    	x = event.getScreenX();
    	y = event.getScreenY();
    }
    
    @FXML
    void max(MouseEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setFullScreenExitHint("");
    	if(!stage.isFullScreen()) {
    		stage.setFullScreen(true);
    	}else {
    		stage.setFullScreen(false);
    	}
    }

    @FXML
    void min(MouseEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setIconified(true);
    }
    
    @FXML
    void close(MouseEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void dashboard(MouseEvent event) {
    	loadUI("/AdminPages/dashboard");
    }
    
    @FXML
    void registerUser(MouseEvent event) {
    	loadUI("/AdminPages/RegisterUser");
    }
    
    @FXML
    void getLogout(MouseEvent event) {
    	loadUI("/AdminPages/Logout");
    }

    @FXML
    void getModifyUser(MouseEvent event) {
    	loadUI("/AdminPages/ModifyUser");
    }

    @FXML
    void getProfile(MouseEvent event) {
    	loadUI("/AdminPages/Profile");
    }

    @FXML
    void getSysInfo(MouseEvent event) {
    	loadUI("/AdminPages/SysInfo");
    }

    @FXML
    void exitApp(MouseEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    }
    
    private void loadUI(String ui) {
    	Parent root = null;
    	try {
			root=FXMLLoader.load(getClass().getResource(ui+".fxml"));
			
    	}catch(Exception e) {
			e.printStackTrace();
		}
    	borderPaneEdit.setCenter(root);
    }
    
    @FXML
    void login(MouseEvent event) throws IOException {
    	Connection conn;
		Statement state;
		ResultSet rs;
		
		String userName = tf_username.getText();
		String passwoard = tf_passwoard.getText();
		
		String strSelect = "SELECT * FROM Employees WHERE `username` = '"+userName+"' AND `passwoard` = '"+passwoard+"'";
		
		try {
			conn=DBinfo.connDB();
			state=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs=state.executeQuery(strSelect);
			rs.first();
			String str = new String(rs.getString("jobtitle"));
			String strUser = new String("user");
			String strAdmin = "admin";
			if(str.equals(strUser));
			System.out.println(str);
			
			 if(str.equals(strUser)) {
					
				//client_name.setText(new String(rs.getString("name")));
					
					Stage stage = new Stage();
					Parent root=FXMLLoader.load(getClass().getResource("/fxml/Client.fxml"));
					Scene scene = new Scene(root,1120,700);
					scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
					stage.setResizable(true);
					stage.setScene(scene);
					stage.initStyle(StageStyle.TRANSPARENT);
					
					Stage oldStage = (Stage)((Node)event.getSource()).getScene().getWindow();
					oldStage.close();
					
					stage.show();
				}else if(str.equals(strAdmin)){
					Stage stage = new Stage();
					Parent root=FXMLLoader.load(getClass().getResource("/fxml/Admin.fxml"));
					Scene scene = new Scene(root,1120,700);
					scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
					stage.setResizable(true);
					stage.setScene(scene);
					stage.initStyle(StageStyle.TRANSPARENT);
					
					Stage oldStage = (Stage)((Node)event.getSource()).getScene().getWindow();
					oldStage.close();
					
					stage.show();

			}else return ;
			
			
		} catch (SQLException e) {
			errException(e);
		}
    }
    
    @FXML
    void register(MouseEvent event) {

    }
    
    public void errException(SQLException e) {
		System.out.println("Error: "+e.getMessage());
		System.out.println("code: "+e.getErrorCode());
		System.out.println("state: "+e.getSQLState());
		System.out.println("message: "+e.getLocalizedMessage());
		
	}

}
