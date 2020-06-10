package application;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddMedicineController {

    @FXML
    private ImageView medicineImage;

    @FXML
    private Button btnImageChooser;

    @FXML
    private TextField tfMedicineID;

    @FXML
    private TextField tfMedicineName;

    @FXML
    private TextField tfCompany;

    @FXML
    private TextField tfBatch;

    @FXML
    private TextField tfPrice;

    @FXML
    private TextField tfQuantity;

    @FXML
    private DatePicker datepickerManufact;

    @FXML
    private DatePicker datepickerExpire;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnAdd;

    void cleareFields() {
    	tfMedicineID.setText("");
    	tfCompany.setText("");
    	tfBatch.setText("");
    	tfMedicineName.setText("");
    	datepickerManufact.setValue(null);
    	datepickerExpire.setValue(null);
    	tfQuantity.setText("");
    	tfPrice.setText("");
    	medicineImage.setImage(new Image("/resources/medicine1.jpg"));
    }
    
    @FXML
    void cancelAddMedicine(MouseEvent event) {
    	cleareFields();
    }

    @FXML
    void confirmAddMedicine(MouseEvent event) throws SQLException, IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPages/RegisterUser.fxml"));
    	Parent root = loader.load();
    	RegisterUserController registerUserController = loader.getController();
    	
    	//Error message for medicine id
    	if(tfMedicineID.getText().isEmpty()) {
    		registerUserController.showErr("Error! MedicineID can't be Empty");
    		return;
    	}
    	
    	//MedicineID can't be repeated in Database
    	Connection conn=DBinfo.connDB();
    	Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String strSelectMedicine = "SELECT `id` FROM `medicines`";
		ResultSet rs = stat.executeQuery(strSelectMedicine);
		
		while(rs.next()) {
			if(tfMedicineID.getText().equals(rs.getString("id"))){
				registerUserController.showErr("Error! This medicine already existed");
	    		return;
			}
		}
    	
    	//Error message for medicine name
    	if(tfMedicineName.getText().isEmpty()) {
    		registerUserController.showErr("Error! Medicine Name can't be Empty");
    		return;
    	}
    	
    	//Error message for company name
    	if(tfCompany.getText().isEmpty()) {
    		registerUserController.showErr("Error! field Company can't be Empty");
    		return;
    	}
    	
    	//Error message batch
    	if(tfBatch.getText().isEmpty()) {
    		registerUserController.showErr("Error! field Batch can't be Empty");
    		return;
    	}
    	
    	//Error message for manufacturing date
    	if(!(datepickerManufact.getValue() != null)) {
    		registerUserController.showErr("Error! Date of Manufacture can't be Empty");
    		return;
    	}
    	
    	//Error message expiary date
    	if(!(datepickerExpire.getValue() != null)) {
    		registerUserController.showErr("Error! Date of Expiary can't be Empty");
    		return;
    	}
    	
    	//Error message expiary date
    	if((datepickerExpire.getValue().isEqual(datepickerManufact.getValue()))
    			|| (datepickerExpire.getValue().isBefore(datepickerManufact.getValue()))) {
    		registerUserController.showErr("Error! Date of Expiary must be after Date of Manufacture");
    		return;
    	}
    	
    	//Error message for price
    	if(tfPrice.getText().isEmpty()) {
    		registerUserController.showErr("Error! field Price can't be Empty");
    		return;
    	}
    	
    	//Error message for quantity
    	if(tfQuantity.getText().isEmpty()) {
    		registerUserController.showErr("Error! field Quantity can't be Empty");
    		return;
    	}
    	    	
    	//After all validation tests above, Register the Medicine
		String sql="INSERT INTO `medicines`(`id`,`name`, `company`, "
    			+ "`batch`, `datemanifact`, `dateexpiary`, `price`,`quantity`,`sold`,`image`) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		
		ps = conn.prepareStatement(sql);

		ps.setString(1, tfMedicineID.getText());
		ps.setString(2, tfMedicineName.getText());
		ps.setString(3, tfCompany.getText());
		ps.setString(4, tfBatch.getText());
		ps.setString(5, datepickerManufact.getValue().toString());
		ps.setString(6, datepickerExpire.getValue().toString());
		ps.setString(7, tfPrice.getText());
		ps.setString(8, tfQuantity.getText());
		ps.setInt(9, 0);
		ps.setString(10, medicineImage.getImage().getUrl());
		ps.executeUpdate();
		
		//clear all fields
		cleareFields();
		
		//Show success message
		registerUserController.showSuccess();
    }

    @FXML
    void openImageChooser(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new
			        FileChooser.ExtensionFilter("jpg", "*.jpg"),
		            new FileChooser.ExtensionFilter("JPGE", "*.JPGE"),
		            new FileChooser.ExtensionFilter("png", "*.png"));
		
		Stage stage = new Stage();
		File selectedFile = fileChooser.showOpenDialog(stage);
	     
		 if(selectedFile != null) {
			 String path = selectedFile.getAbsolutePath();		     
			 path = path.replace("\\","/");
			 Image image = new Image(new File(path).toURI().toString());
			 medicineImage.setImage(image);
		 }
    }
}
