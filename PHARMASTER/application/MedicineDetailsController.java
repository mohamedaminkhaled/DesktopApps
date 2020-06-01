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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MedicineDetailsController {
	
	double x, y;

    @FXML
    private BorderPane page_1;

    @FXML
    private FontAwesomeIconView icon_close;

    @FXML
    private FontAwesomeIconView icon_fullscreen;

    @FXML
    private FontAwesomeIconView icon_iconize;

    @FXML
    private Label medicineName;

    @FXML
    private Label medicineID;

    @FXML
    private Label companyName;

    @FXML
    private Label batch;

    @FXML
    private Label dateExpiary;

    @FXML
    private Label dateManufact;

    @FXML
    private ImageView medicineImage;

    @FXML
    private Label price;

    @FXML
    private Label quantity;

    @FXML
    private Button btnAddStock;

    @FXML
    private Button btnClose;
    
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
    void getAddStock(MouseEvent event) throws IOException, SQLException {
    	
    	Stage stage=new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPages/AddStock.fxml"));
		Parent root = loader.load();
		
		AddStockController addStockController = loader.getController();
		addStockController.setCurrentQuantity(quantity.getText());
		addStockController.medicineID = medicineID.getText();
		
		Scene scene=new Scene(root,630,266);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
    }
    
    @FXML
    void getSellStock(MouseEvent event) throws IOException {
    	Stage stage=new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPages/SellStock.fxml"));
		Parent root = loader.load();
		
		SellStockController sellStockController = loader.getController();
		sellStockController.setCurrentQuantity(quantity.getText());
		sellStockController.medicineID = medicineID.getText();
	
		Scene scene=new Scene(root,630,266);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
    }
    
    void setMedicineDetails(String id) throws SQLException {
    	Statement state;
		ResultSet rs;
		
		String strSelect = "SELECT * FROM medicines WHERE `id` = '"+id+"'";
		
		Connection conn=DBinfo.connDB();
		state=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		rs=state.executeQuery(strSelect);
		rs.last();
		
		//assign values to card attributes
		medicineName.setText(rs.getString("name"));
		medicineID.setText(rs.getString("id"));
		dateManufact.setText(rs.getString("datemanifact"));
		dateExpiary.setText(rs.getString("dateexpiary"));
		price.setText(rs.getString("price"));
		companyName.setText(rs.getString("company"));
		quantity.setText(rs.getString("quantity"));
		batch.setText(rs.getString("batch"));
		medicineImage.setImage(new Image(rs.getString("image")));
    }
}
