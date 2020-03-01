package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    
    void setWelcomeMessage(String str) {
    	welcomMessage.setText(str);
    }

}
