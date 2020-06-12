package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CompanyCardController {

    @FXML
    Label companyName;
    
    @FXML
    private BorderPane borderPaneCompanyCard;

    @FXML
    void viewMedicines(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
    	FXMLLoader loaderViewMedicine = new FXMLLoader(getClass().getResource("/UserPages/ViewMedicine.fxml"));
    	Parent root = loaderViewMedicine.load();
    	
		String strSelectCompanyMedicines = "SELECT * FROM `medicines` WHERE `company` = '"+companyName.getText()+"'";
    	
    	SearchMedicineController searchMedicineController = loaderViewMedicine.getController();
    	searchMedicineController.companyName = companyName.getText();
    	searchMedicineController.key = "searchCompanyMedicines";

    	searchMedicineController.getMedicines(strSelectCompanyMedicines);
		
    	
    	Scene scene=new Scene(root,839,543);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
    }
    
    void setCompanyCard(String name) {
    	companyName.setText(name);
    }

}

