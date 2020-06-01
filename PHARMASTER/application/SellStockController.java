package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SellStockController {

    @FXML
    private BorderPane addStockPage;

    @FXML
    private FontAwesomeIconView icon_close;

    @FXML
    private TextField currentQuantity;

    @FXML
    private TextField quantityToSell;

    @FXML
    private Label successMessage;
    
    String medicineID = null;
    
    double x, y;
    
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
    void confirmSellStock(MouseEvent event) throws SQLException {
    	int intQuantityToSell = Integer.parseInt(quantityToSell.getText());
    	int intCurrentQuantity = Integer.parseInt(currentQuantity.getText());
    	int newQuantity = intCurrentQuantity - intQuantityToSell;
    	String strNewQuantity = String.valueOf(newQuantity);
    	
    	Connection conn=DBinfo.connDB();
		ResultSet rs;
    	
    	String selectSold = "SELECT `sold` FROM `medicines` WHERE `id`= '"+medicineID+"'";
    	
    	Statement state=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		rs=state.executeQuery(selectSold);
    	rs.first();
    	
    	int selectedSold = rs.getInt("sold");
    	int sold = intQuantityToSell + selectedSold;
		
    	String strsold = String.valueOf(sold);
    	
    	String strUpdate ="UPDATE `medicines` SET `quantity`=? , `sold`=? WHERE `id`=?" ;  
		PreparedStatement ps = conn.prepareStatement(strUpdate);
		ps.setString(1, strNewQuantity);
		ps.setString(2, strsold);
		ps.setString(3, medicineID);
		ps.executeUpdate();
		
		successMessage.setText("Operation performed successfully!");
    }
    
    void setCurrentQuantity(String str) {
    	currentQuantity.setText(str);
    }

}

