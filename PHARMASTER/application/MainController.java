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
    private BorderPane adminPage;

    @FXML
    private FontAwesomeIconView icon_iconize;

    @FXML
    private FontAwesomeIconView icon_fullscreen;

    @FXML
    private FontAwesomeIconView icon_close;

    @FXML
    BorderPane borderPaneContent;

    @FXML
    private Label totalMedicine;

    @FXML
    private Label expiaryThisMonth;

    @FXML
    private Label outOfStock;

    @FXML
    private Label totalSales;

    @FXML
    private Label similarCompany;
    

    @FXML
    void close(MouseEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void pressed(MouseEvent event) {
    	x = event.getScreenX();
    	y = event.getScreenY();
    }
    
    @FXML
    void dragged(MouseEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setX(event.getScreenX() -x);
    	stage.setY(event.getScreenY() -y);
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
    void getDashboard(MouseEvent event) {
    	loadPage("/AdminPages/Dashboard");
    }

    @FXML
    void getLogout(MouseEvent event) {

    }

    @FXML
    void getModifyUser(MouseEvent event) {
    	loadPage("/AdminPages/ModifyUser");
    }

    @FXML
    void getMyProfile(MouseEvent event) {

    }

    @FXML
    void getRegisterUser(MouseEvent event) {
    	loadPage("/AdminPages/RegisterUser");
    }

    @FXML
    void getSysInfo(MouseEvent event) {
    	loadPage("/AdminPages/SysInfo");
    }
    
    void loadPage(String ui) {
    	Parent root = null;
    	try {
			root=FXMLLoader.load(getClass().getResource(ui+".fxml"));
			
    	}catch(Exception e) {
			e.printStackTrace();
		}
    	borderPaneContent.setCenter(root);
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
