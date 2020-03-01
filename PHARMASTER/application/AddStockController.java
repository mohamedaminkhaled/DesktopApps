package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    void confirmAddStock(MouseEvent event) throws SQLException {
    	int intQuantityToAdd = Integer.parseInt(quantityToAdd.getText());
    	int intCurrentQuantity = Integer.parseInt(currentQuantity.getText());
    	int newQuantity = intCurrentQuantity + intQuantityToAdd;
    	String strNewQuantity = String.valueOf(newQuantity);
    	
    	Connection conn=DBinfo.connDB();
    	
    	String strUpdate ="UPDATE `medicines` SET `quantity`=?";  
		PreparedStatement ps = conn.prepareStatement(strUpdate);
		ps.setString(1, strNewQuantity);
		ps.executeUpdate();
		
		successMessage.setText("Operation performed successfully!");
    }
    
    void setCurrentQuantity(String str) {
    	currentQuantity.setText(str);
    }

}
