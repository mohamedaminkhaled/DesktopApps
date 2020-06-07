package application;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashboardController {

    @FXML
    private Label welcomMessage;

    @FXML
    private Label totalMedicine;

    @FXML
    private Button btnTotalMedicine;

    @FXML
    private Label expiaryThisMonth;

    @FXML
    private Button btnExpiaryThisMonth;

    @FXML
    private Label outOfStock;

    @FXML
    private Button btnOutOfStock;

    @FXML
    private Label totalSales;

    @FXML
    private Button btnTotalSales;

    @FXML
    private Label similarCompanies;

    @FXML
    private Button btnsimilarCompanies;
    
    @FXML
    void getExpiarythisMonth(MouseEvent event) throws SQLException, IOException {
    	Stage stage = new Stage();
    	FXMLLoader loaderViewMedicine = new FXMLLoader(getClass().getResource("/UserPages/ViewMedicine.fxml"));
    	Parent root = loaderViewMedicine.load();
    	
    	String gcTimeMore = getTimeMore();
    	String gcTimeLessOrEqual = getTimeLessOrEqual();
    	String strSelectExpiaryThisMonth = "SELECT * FROM `medicines` WHERE (`dateexpiary` > '"+gcTimeMore+"') AND (`dateexpiary` <= '"+gcTimeLessOrEqual+"')"; 

    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.key = "getExpiarythisMonth";
    	searchMedicineController.getMedicines(strSelectExpiaryThisMonth);
    	
    	Scene scene=new Scene(root,839,543);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
    }

    @FXML
    void getOutOfStock(MouseEvent event) throws IOException, SQLException {
    	Stage stage = new Stage();
    	FXMLLoader loaderViewMedicine = new FXMLLoader(getClass().getResource("/UserPages/ViewMedicine.fxml"));
    	Parent root = loaderViewMedicine.load();
    	
    	String dateTimt = getDate();
		String strSelectOutOfStock = "SELECT * FROM `medicines` WHERE `dateexpiary` < '"+dateTimt+"'";

    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.key = "getOutOfStock";
    	searchMedicineController.getMedicines(strSelectOutOfStock);
    	
    	Scene scene=new Scene(root,839,543);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
    }
    
    String getTimeMore() {
    	Date date = new Date();
		DateFormat dateFormate = new SimpleDateFormat("yyyy/MM/dd");

    	String strFullDate = dateFormate.format(date);
		String strYear = strFullDate.substring(0, 4);
		int intYear = Integer.parseInt(strYear);
    	
    	@SuppressWarnings("deprecation")
		GregorianCalendar gc = new GregorianCalendar(intYear, date.getMonth(), 0);
    	date = gc.getTime();
    	String gcTimeMore = dateFormate.format(date);
    	gcTimeMore = gcTimeMore.replace("/","-");
    	return gcTimeMore;
    }
    
    String getTimeLessOrEqual() {
    	Date date = new Date();
		DateFormat dateFormate = new SimpleDateFormat("yyyy/MM/dd");

    	String strFullDate = dateFormate.format(date);
		String strYear = strFullDate.substring(0, 4);
		int intYear = Integer.parseInt(strYear);
    	
    	@SuppressWarnings("deprecation")
		GregorianCalendar gc2 = new GregorianCalendar(intYear, date.getMonth()+2, 0);
    	date = gc2.getTime();
    	String gcTimeLessOrEqual = dateFormate.format(date);
    	gcTimeLessOrEqual = gcTimeLessOrEqual.replace("/","-");
    	return gcTimeLessOrEqual;
    }
    
    String getDate() {
    	Date date = new Date();
		DateFormat dateFormate = new SimpleDateFormat("yyyy/MM/dd");
		String dateTimt = dateFormate.format(date);
		dateTimt = dateTimt.replace("/","-");
		return dateTimt;
    }

    @FXML
    void getSimilarCompanies(MouseEvent event) throws IOException, SQLException {
    	String strSelectSimilarCompanies = "SELECT DISTINCT `company` FROM `medicines`";

    	Stage stage = new Stage();
    	FXMLLoader loaderViewMedicine = new FXMLLoader(getClass().getResource("/UserPages/ViewMedicine.fxml"));
    	Parent root = loaderViewMedicine.load();
    	
    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.key = "getSimilarCompanies";
    	searchMedicineController.getCompanies(strSelectSimilarCompanies);
    	
    	Scene scene=new Scene(root,839,543);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
    }

    @FXML
    void getTotalMedicines(MouseEvent event) throws IOException, SQLException {
		String strSelect = "SELECT `id` FROM medicines";

    	Stage stage = new Stage();
    	FXMLLoader loaderViewMedicine = new FXMLLoader(getClass().getResource("/UserPages/ViewMedicine.fxml"));
    	Parent root = loaderViewMedicine.load();
    	
    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.key = "getTotalMedicines";
    	searchMedicineController.getMedicines(strSelect);
    	
    	Scene scene=new Scene(root,839,543);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
    }

    @FXML
    void getTotalSales(MouseEvent event) throws IOException, SQLException {
		String strSelectTotalSales = "SELECT * FROM `medicines`";
    	
    	Stage stage = new Stage();
    	FXMLLoader loaderViewMedicine = new FXMLLoader(getClass().getResource("/UserPages/ViewMedicine.fxml"));
    	Parent root = loaderViewMedicine.load();
    	
    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.key = "getTotalSales";
    	searchMedicineController.getMedicineSales(strSelectTotalSales);
    	
    	Scene scene=new Scene(root,839,543);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
    }
    
    void setWelcomeMessage(String str) {
    	welcomMessage.setText(str);
    }
    
    void setTotalMedicine(String str) {
    	totalMedicine.setText(str);
    }
    
    void setExpiaryThisMonth(String str) {
    	expiaryThisMonth.setText(str);
    }
    
    void setOutOfStock(String str) {
    	outOfStock.setText(str);
    }
    
    void setTotalSales(String str) {
    	totalSales.setText(str);
    }
    
    void setSimilarCompanies(String str) {
    	similarCompanies.setText(str);
    }
}
