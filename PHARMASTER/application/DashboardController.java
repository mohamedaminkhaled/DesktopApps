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
    	
    	@SuppressWarnings("deprecation")
		GregorianCalendar gc2 = new GregorianCalendar(intYear, date.getMonth()+2, 0);
    	date = gc2.getTime();
    	String gcTimeLessOrEqual = dateFormate.format(date);
    	gcTimeLessOrEqual = gcTimeLessOrEqual.replace("/","-");
    	
    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.getExpiaryThisMonth(gcTimeMore, gcTimeLessOrEqual);
    	
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
    	
    	Date date = new Date();
		DateFormat dateFormate = new SimpleDateFormat("yyyy/MM/dd");
		String dateTimt = dateFormate.format(date);
		dateTimt = dateTimt.replace("/","-");
    	
    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.getOutOfStockMedicines(dateTimt);
    	
    	Scene scene=new Scene(root,839,543);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
    }

    @FXML
    void getSimilarCompanies(MouseEvent event) throws IOException, SQLException {
    	
    	Stage stage = new Stage();
    	
    	FXMLLoader loaderViewMedicine = new FXMLLoader(getClass().getResource("/UserPages/ViewMedicine.fxml"));
    	Parent root = loaderViewMedicine.load();
    	
    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.getCompanies();
    	
    	Scene scene=new Scene(root,839,543);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
    }

    @FXML
    void getTotalMedicines(MouseEvent event) throws IOException, SQLException {
    	
    	Stage stage = new Stage();
    	
    	FXMLLoader loaderViewMedicine = new FXMLLoader(getClass().getResource("/UserPages/ViewMedicine.fxml"));
    	Parent root = loaderViewMedicine.load();
    	
    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.getMedicines();
    	
    	Scene scene=new Scene(root,839,543);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
    }

    @FXML
    void getTotalSales(MouseEvent event) throws IOException, SQLException {
    	
    	Stage stage = new Stage();
    	
    	FXMLLoader loaderViewMedicine = new FXMLLoader(getClass().getResource("/UserPages/ViewMedicine.fxml"));
    	Parent root = loaderViewMedicine.load();
    	
    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.getMedicineSales();
    	
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
