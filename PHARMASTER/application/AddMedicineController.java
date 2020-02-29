package application;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
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

    @FXML
    void cancelAddMedicine(MouseEvent event) {
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
    void confirmAddMedicine(MouseEvent event) throws SQLException {
    	Connection conn=DBinfo.connDB();
		String sql="INSERT INTO `medicines`(`id`,`name`, `company`, "
    			+ "`batch`, `datemanifact`, `dateexpiary`, `price`,`quantity`, `image`) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
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
		ps.setString(9, medicineImage.getImage().getUrl());
		ps.executeUpdate();
		System.out.println("Row inserted!");
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
    
    public void errException(SQLException e) {
		System.out.println("Error: "+e.getMessage());
		System.out.println("code: "+e.getErrorCode());
		System.out.println("state: "+e.getSQLState());
		System.out.println("message: "+e.getLocalizedMessage());
		
	}

}
