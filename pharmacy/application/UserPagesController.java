package application;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserPagesController {
	
	double x, y;
	
    @FXML
    private BorderPane clientPage;

    @FXML
    private FontAwesomeIconView icon_iconize;

    @FXML
    private FontAwesomeIconView icon_fullscreen;

    @FXML
    private FontAwesomeIconView icon_close;

    @FXML
    private BorderPane borderPaneEdit;

    @FXML
    private Button addMedicine;

    @FXML
    private Label clientName;

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
    
//    @FXML
//    void dashboard(MouseEvent event) {
//    	loadUI("/UserPages/dashboard");
//    }
    
//    @FXML
//    void addMedicine(MouseEvent event) {
//    	loadUI("/UserPages/dashboard");
//    }
    
//    @FXML
//    void viewMedicine(MouseEvent event) {
//    	loadUI("/UserPages/ViewMedicine");
//    }
//    
//    @FXML
//    void sysInfo(MouseEvent event) {
//    	loadUI("/UserPages/SysInfo");
//    }
//    
//    @FXML
//    void profile(MouseEvent event) {
//    	loadUI("/UserPages/Profile");
//    }
//    
//    @FXML
//    void logout(MouseEvent event) {
//    	loadUI("/UserPages/Logout");
//    }

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
}

