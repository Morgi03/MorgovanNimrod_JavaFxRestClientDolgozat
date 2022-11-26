package hu.petrik.morgovannimrod_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateCompanyController extends Controller {

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
    private Company company;

    @FXML
    private void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 50);
        totalempolyeeField.setValueFactory(valueFactory);
    }

    public void setCompany(Company company) {
        this.company = company;
        nameField.setText(this.company.getCompanyName());
        PhoneField.setText(this.company.getCompanyPhone());
        totalempolyeeField.getValueFactory().setValue(this.company.getTotalEmployee());
        creditcardField.setText(this.company.getCreditCard());
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
        String name = nameField.getText().trim();
        String phone = PhoneField.getText().trim();
        int totalEmpolyee = totalempolyeeField.getValue();
        String creditcard = creditcardField.getText().trim();
        if (name.isEmpty()) {
            warning("A cégnevet kötelező kitölteni");
            return;
        }
        if (phone.isEmpty()) {
            warning("A telefonszám mezőt kötelező kitölteni");
            return;
        }
        Pattern VALID_PHONE_REGEX = Pattern.compile("^\\(+[0-9]+\\)\\ +[0-9]{3}+\\-[0-9]{4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher_Phone = VALID_PHONE_REGEX.matcher(phone);
        if (!matcher_Phone.find()) {
            warning("A telefonszám formátuma helytelen (helyes formátum: (hivószám száma) xxx-xxxx)");
            return;
        }
        if (creditcard.isEmpty()) {
            warning("A bankkártya mező kitöltése kötelező");
            return;
        }
        Pattern VALID_CREDIT_CARD_REGEX = Pattern.compile("^[0-9]{4}\\-[0-9]{4}\\-[0-9]{4}\\-[0-9]{4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher_CC = VALID_CREDIT_CARD_REGEX.matcher(creditcard);
        if (!matcher_CC.find()) {
            warning("A bankszámla formátuma helytelen (helyes fomrátum: xxxx-xxxx-xxxx-xxxx)");
            return;
        }
        this.company.setCompanyName(name);
        this.company.setCompanyPhone(phone);
        this.company.setTotalEmployee(totalEmpolyee);
        this.company.setCreditCard(creditcard);
        Gson converter = new Gson();
        String json = converter.toJson(this.company);
        try {
            String url = App.BASE_URL + "/" + this.company.getId();
            Response response = RequestHandler.put(url, json);
            if (response.getResponseCode() == 200) {
                Stage stage = (Stage) this.submitButton.getScene().getWindow();
                stage.close();
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
