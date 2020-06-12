package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminController {
	
	double x, y;

    @FXML
    private BorderPane adminPage;

    @FXML
    private FontAwesomeIconView icon_iconize;

    @FXML
    private FontAwesomeIconView icon_fullscreen;

    @FXML
    private FontAwesomeIconView icon_close;

    @FXML
    private BorderPane borderPaneContent;

    @FXML
    private Label adminName;

    @FXML
    ImageView adminImage;

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
    
    FXMLLoader loader;
    Stage stage;
    Parent root;
    Statement state;
	ResultSet rs;
	Connection conn;

    @FXML
    void close(MouseEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void pressed(MouseEvent event) {
    	x = event.getScreenX();
    	y = event.getScreenY();
    }
    
    @FXML
    void dragged(MouseEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setX(event.getScreenX() -x);
    	stage.setY(event.getScreenY() -y);
    }
    
    @FXML
    void max(MouseEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setFullScreenExitHint("");
    	if(!stage.isFullScreen()) {
    		stage.setFullScreen(true);
    	}else {
    		stage.setFullScreen(false);
    	}
    }

    @FXML
    void min(MouseEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setIconified(true);
    }
    
    @FXML
    void setDashboard() throws IOException {
		try {
			conn=DBinfo.connDB();
			state=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			    	
			loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
			root = loader.load();
			
			DashboardController dashboardController = loader.getController();
			dashboardController.setWelcomeMessage("Welcom Dear Admin, "+adminName.getText());
			
			// select count of medicines
			String strSelectTotalMedicines = "SELECT * FROM medicines";
			rs=state.executeQuery(strSelectTotalMedicines);
			rs.last();
			int totalMedicines = rs.getRow();
			
			dashboardController.setTotalMedicine(String.valueOf(totalMedicines));
			
			// select count of similar companies
			String strSelectSimilarCompanies = "SELECT DISTINCT `company` FROM `medicines`";
			rs=state.executeQuery(strSelectSimilarCompanies);
			rs.last();
			int intSimilarCompanies = rs.getRow();
			
			dashboardController.setSimilarCompanies(String.valueOf(intSimilarCompanies));
			
			Date date = new Date();
			DateFormat dateFormate = new SimpleDateFormat("yyyy/MM/dd");
			String dateTimt = dateFormate.format(date);
			dateTimt = dateTimt.replace("/","-");
			
			// select medicines out of stock
			String strSelectOutOfStock = "SELECT * FROM `medicines` WHERE `dateexpiary` < '"+dateTimt+"'";
			rs=state.executeQuery(strSelectOutOfStock);
			rs.last();
			int intOutOfStock = rs.getRow();
			
			dashboardController.setOutOfStock(String.valueOf(intOutOfStock));
			
			String strFullDate = dateFormate.format(date);
			String strYear = strFullDate.substring(0, 4);
			int intYear = Integer.parseInt(strYear);
			
			GregorianCalendar gc = new GregorianCalendar(intYear, date.getMonth(), 0);
			date = gc.getTime();
			String gcTimeMore = dateFormate.format(date);
			gcTimeMore = gcTimeMore.replace("/","-");
			
			GregorianCalendar gc2 = new GregorianCalendar(intYear, date.getMonth()+2, 0);
			date = gc2.getTime();
			String gcTimeLessOrEqual = dateFormate.format(date);
			gcTimeLessOrEqual = gcTimeLessOrEqual.replace("/","-");
			    	
			String strSelectExpiaryThisMonth = "SELECT * FROM `medicines` WHERE (`dateexpiary` > '"+gcTimeMore+"') AND (`dateexpiary` <= '"+gcTimeLessOrEqual+"')"; 
			rs=state.executeQuery(strSelectExpiaryThisMonth);
			rs.last();
			int intExpiaryThisMonth = rs.getRow();
			
			dashboardController.setExpiaryThisMonth(String.valueOf(intExpiaryThisMonth));
			
			// select price and sold
			String selectPriceAndSold = "SELECT `price`,`sold` FROM `medicines`";
			rs=state.executeQuery(selectPriceAndSold);
			int price, sold;
			int totalSales = 0;
			
			while(rs.next()) {
				price = rs.getInt("price");
				sold = rs.getInt("sold");
				totalSales += (price * sold);
			}
			dashboardController.setTotalSales(String.valueOf(totalSales));
		} catch (SQLException e) {
			ErrorServerNotFound err = new ErrorServerNotFound();
			err.errException(e);
			return;
		}
    	
    	borderPaneContent.setCenter(root);
    }

    @FXML
    void getModifyUser(MouseEvent event) throws IOException, SQLException {
    	loadPage("/AdminPages/ModifyUser");   
    }

    @FXML
    void getRegisterUser(MouseEvent event) {
    	loadPage("/AdminPages/RegisterUser");
    }
  
    void setAdminName(String str) {
    	adminName.setText(str);
    }
    
    void setAdminImage(Image image) {
    	adminImage.setImage(image);
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

}
