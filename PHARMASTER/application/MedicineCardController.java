package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class MedicineCardController {

    @FXML
    private BorderPane BorderPaneMedicineCard;

    @FXML
    private ImageView medicineImage;

    @FXML
    private Label medicineName;

    @FXML
    private Label medicineID;

    @FXML
    private Label medicineDateManufact;

    @FXML
    private Label medicineDateExpiary;

    @FXML
    private Label medicinePrice;

    @FXML
    private Button btnAddStock;

    @FXML
    private Button btnViewMedicineDetails;

    @FXML
    void getAddStock(MouseEvent event) throws IOException, SQLException {
    	
    	Statement state;
		ResultSet rs;
		
		String strSelect = "SELECT `quantity` FROM medicines WHERE `id` = '"+medicineID.getText()+"'";
		
		Connection conn=DBinfo.connDB();
		state=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		rs=state.executeQuery(strSelect);
		rs.last();
    	
    	Stage stage=new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPages/AddStock.fxml"));
		Parent root = loader.load();
		
		AddStockController addStockController = loader.getController();
		addStockController.setCurrentQuantity(rs.getString("quantity"));
		addStockController.medicineID = medicineID.getText();
		
		Scene scene=new Scene(root,630,266);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
    }

    @FXML
    void getMedicineDetails(MouseEvent event) throws IOException, SQLException {
    	
    	Stage stage=new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPages/MedicineDetails.fxml"));
		Parent root = loader.load();
		
		MedicineDetailsController medicineDetailsController = loader.getController();
		
		medicineDetailsController.setMedicineDetails(medicineID.getText());
		
		Scene scene=new Scene(root,876,580);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
    }
    
    void setMedicineCard(String id) throws SQLException {
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
		medicineDateManufact.setText(rs.getString("datemanifact"));
		medicineDateExpiary.setText(rs.getString("dateexpiary"));
		medicinePrice.setText(rs.getString("price"));
		medicineImage.setImage(new Image(rs.getString("image")));
    }   
}
