package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AddStockController {
	
	double x, y;

    @FXML
    private BorderPane addStockPage;

    @FXML
    private FontAwesomeIconView icon_close;

    @FXML
    private TextField currentQuantity;

    @FXML
    private TextField quantityToAdd;

    @FXML
    private Label successMessage;

    @FXML
    private Button btnAddStock;
    
    String medicineID = null;

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
    void confirmAddStock(MouseEvent event) throws IOException {
    	if(quantityToAdd.getText().equals("")) {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPages/RegisterUser.fxml"));
        	Parent root = loader.load();
        	RegisterUserController registerUserController = loader.getController();
    		registerUserController.showErr("Error! Quantity to add can't be Empty");
    		return;
    	}
    	
    	int intQuantityToAdd = Integer.parseInt(quantityToAdd.getText());
    	int intCurrentQuantity = Integer.parseInt(currentQuantity.getText());
    	int newQuantity = intCurrentQuantity + intQuantityToAdd;
    	String strNewQuantity = String.valueOf(newQuantity);
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPages/RegisterUser.fxml"));
    	Parent root = loader.load();
    	RegisterUserController registerUserController = loader.getController();
    	
    	try {
			Connection conn=DBinfo.connDB();
			
			String strUpdate ="UPDATE `medicines` SET `quantity`=? WHERE `id`=?";  
			PreparedStatement ps = conn.prepareStatement(strUpdate);
			ps.setString(1, strNewQuantity);
			ps.setString(2, medicineID);
			ps.executeUpdate();
		} catch (SQLException e) {
			ErrorServerNotFound err = new ErrorServerNotFound();
			err.errException(e);
			return;
			}
    	
    	Stage oldStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		oldStage.close();
    	
    	//Show success message
    	registerUserController.showSuccess();
		
		//successMessage.setText("Operation performed successfully!");
    }
    
    void setCurrentQuantity(String str) {
    	currentQuantity.setText(str);
    }

}
