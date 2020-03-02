package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class SearchMedicineController {

    @FXML
    private BorderPane borderPaneSearch;

    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnSearch;
    
    @FXML
    private FlowPane flowPaneContent;
    
    void getMedicines() throws SQLException, IOException {
    	
    	Statement state;
		ResultSet rs;
		
		String strSelect = "SELECT `id` FROM medicines";
		
		Connection conn=DBinfo.connDB();
		state=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		rs=state.executeQuery(strSelect);
		
		while(rs.next()) {
			FXMLLoader loaderMedicineCard = new FXMLLoader(getClass().getResource("/UserPages/MedicineCard.fxml"));
	    	Parent root = loaderMedicineCard.load();

	    	MedicineCardController medicineCardController = loaderMedicineCard.getController();
			
			medicineCardController.setMedicineCard(rs.getString("id"));
			flowPaneContent.getChildren().addAll(root);
		} 
    }
    
    void getOutOfStockMedicines(String date) throws SQLException, IOException {
    	Statement state;
		ResultSet rs;
		
		String strSelectOutOfStock = "SELECT * FROM `medicines` WHERE `dateexpiary` < '"+date+"'";
		
		Connection conn=DBinfo.connDB();
		state=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		rs=state.executeQuery(strSelectOutOfStock);
		
		while(rs.next()) {
			FXMLLoader loaderMedicineCard = new FXMLLoader(getClass().getResource("/UserPages/MedicineCard.fxml"));
	    	Parent root = loaderMedicineCard.load();

	    	MedicineCardController medicineCardController = loaderMedicineCard.getController();
			
			medicineCardController.setMedicineCard(rs.getString("id"));
			flowPaneContent.getChildren().addAll(root);
		} 
    }
    
    void getExpiaryThisMonth(String dateMorThan, String dateLessOrEqual) throws SQLException, IOException {
    	Statement state;
		ResultSet rs;
    	
    	String strSelectExpiaryThisMonth = "SELECT * FROM `medicines` WHERE (`dateexpiary` > '"+dateMorThan+"') AND (`dateexpiary` <= '"+dateLessOrEqual+"')"; 
    	Connection conn=DBinfo.connDB();
		state=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		rs=state.executeQuery(strSelectExpiaryThisMonth);
    	
		while(rs.next()) {
			FXMLLoader loaderMedicineCard = new FXMLLoader(getClass().getResource("/UserPages/MedicineCard.fxml"));
	    	Parent root = loaderMedicineCard.load();

	    	MedicineCardController medicineCardController = loaderMedicineCard.getController();
			
			medicineCardController.setMedicineCard(rs.getString("id"));
			flowPaneContent.getChildren().addAll(root);
		} 
    }

    @FXML
    void serchMedicine(MouseEvent event) {

    }
    
    void setTFsearch(String str) {
    	tfSearch.setText(str);
    }

}
