package hu.petrik.morgovannimrod_javafxrestclientdolgozat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class CreateCompanyController extends Controller {

    @FXML
    private Button submitButton;
    @FXML
    private Spinner<Integer> totalempolyeeField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField PhoneField;
    @FXML
    private TextField creditcardField;

    @FXML
    private void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 50);
        totalempolyeeField.setValueFactory(valueFactory);
    }

    @FXML
    public void submitClick(ActionEvent actionEvent) {

    }

}
