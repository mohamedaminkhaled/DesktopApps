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
    
    String key = null;
    String companyName;
    
    void getMedicines(String strSelect) throws SQLException, IOException {
    	
    	Statement state;
		ResultSet rs;
				
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
    
    void getCompanies(String strSelectSimilarCompanies) throws SQLException, IOException {
    	
    	Statement state;
		ResultSet rs;
				
		Connection conn=DBinfo.connDB();
		state=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		rs=state.executeQuery(strSelectSimilarCompanies);
		
		while(rs.next()) {
			FXMLLoader loaderCompanyCard = new FXMLLoader(getClass().getResource("/UserPages/CompanyCards.fxml"));
	    	Parent root = loaderCompanyCard.load();

	    	CompanyCardController companyCardController = loaderCompanyCard.getController();
			
	    	companyCardController.setCompanyCard(rs.getString("company"));
			flowPaneContent.getChildren().addAll(root);
		} 
    }
    
    void getMedicineSales(String strSelectOutOfStock) throws SQLException, IOException {
    	
    	Statement state;
		ResultSet rs;
				
		Connection conn=DBinfo.connDB();
		state=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		rs=state.executeQuery(strSelectOutOfStock);
		
		while(rs.next()) {
			FXMLLoader loaderSalesCard = new FXMLLoader(getClass().getResource("/UserPages/SalesCard.fxml"));
	    	Parent root = loaderSalesCard.load();

	    	SalesCardController salesCardController = loaderSalesCard.getController();
			
	    	salesCardController.setSalesCard(rs.getString("id"));
			flowPaneContent.getChildren().addAll(root);
		} 
    }

    @FXML
    void serchMedicine(MouseEvent event) throws SQLException, IOException {
    	String searchWord = tfSearch.getText();
    	flowPaneContent.getChildren().clear();
    	
    	FXMLLoader loaderDashboard = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
    	Parent root = loaderDashboard.load();
    	DashboardController dashboardController = loaderDashboard.getController();
    	    	
    	switch (key) {
			case "getExpiarythisMonth":
				{
					String gcTimeMore = dashboardController.getTimeMore();
			    	String gcTimeLessOrEqual = dashboardController.getTimeLessOrEqual();
			    	String strSelectExpiaryThisMonth = "SELECT * FROM `medicines` WHERE (`dateexpiary` > '"+gcTimeMore+"') AND (`dateexpiary` <= '"+gcTimeLessOrEqual+"') AND (`name` LIKE '"+searchWord+"%')"; 
					getMedicines(strSelectExpiaryThisMonth);
				}
			break;
			
			case "getSimilarCompanies":
				{
			    	String strSelectSimilarCompanies = "SELECT DISTINCT `company` FROM `medicines` WHERE `company` LIKE '"+searchWord+"%'";
					getCompanies(strSelectSimilarCompanies);
				}
			break;
			
			case "getOutOfStock":
				{
					String dateTimt = dashboardController.getDate();
					String strSelectOutOfStock = "SELECT * FROM `medicines` WHERE `dateexpiary` < '"+dateTimt+"' AND `name` LIKE '"+searchWord+"%'";
					getMedicines(strSelectOutOfStock);
				}
			break;
			
			case "getTotalSales":
				{
					String strSelectTotalSales = "SELECT * FROM `medicines` WHERE `name` LIKE '"+searchWord+"%'";
					getMedicineSales(strSelectTotalSales);
				}
			break;
			
			case "searchCompanyMedicines":
			{
				String strSearchCompanyMedicines = "SELECT * FROM `medicines` WHERE `company` = '"+companyName+"' AND `name` LIKE '"+searchWord+"%'";
				getMedicines(strSearchCompanyMedicines);
			}
			break;
	
			default:
				{
					String strSelect = "SELECT `id` FROM medicines WHERE `name` LIKE '"+searchWord+"%'";
					getMedicines(strSelect);
				}
			break;
		}
    }
    
    void setTFsearch(String str) {
    	tfSearch.setText(str);
    }
}