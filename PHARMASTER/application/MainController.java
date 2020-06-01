package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController {
	
	double x, y;

    @FXML
    private AnchorPane login;

    @FXML
    private Label register;

    @FXML
    private Label exit;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_passwoard;

    @FXML
    private Button btn_submit;
    
    @FXML
    void close(MouseEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void register(MouseEvent event) {

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
			
			String name = rs.getString("firstname");
			
			 if(str.equals(strUser)) {
					
				 	Stage stage = new Stage();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Client.fxml"));
			    	Parent root = loader.load();
			    	
			    	ClientController clientController = loader.getController();
			    	clientController.setClientName(name);
			    	clientController.setDashboard();
			    	
					Scene scene = new Scene(root,1180,750);					
					scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
					stage.setResizable(true);
					stage.setScene(scene);
					stage.initStyle(StageStyle.TRANSPARENT);
					
					Stage oldStage = (Stage)((Node)event.getSource()).getScene().getWindow();
					oldStage.close();
					stage.show();
				}else if(str.equals(strAdmin)){
										
					Stage stage = new Stage();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin.fxml"));
			    	Parent root = loader.load();
			    	
			    	AdminController adminController = loader.getController();
			    	adminController.setAdminName(name);
			    	adminController.setDashboard();
					
					Scene scene = new Scene(root,1180,750);
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
    
    public void errException(SQLException e) {
		System.out.println("Error: "+e.getMessage());
		System.out.println("code: "+e.getErrorCode());
		System.out.println("state: "+e.getSQLState());
		System.out.println("message: "+e.getLocalizedMessage());
		
	}

}
