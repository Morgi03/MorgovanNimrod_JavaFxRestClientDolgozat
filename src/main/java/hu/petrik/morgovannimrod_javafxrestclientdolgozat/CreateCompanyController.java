package hu.petrik.morgovannimrod_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (creditcard.isEmpty()) {
            warning("A bankkártya mező kitöltése kötelező");
            return;
        }
        Pattern VALID_PHONE_REGEX = Pattern.compile("^\\(+[0-9]+\\)\\ +[0-9]{3}+\\-[0-9]{4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher_Phone = VALID_PHONE_REGEX.matcher(phone);
        if (!matcher_Phone.find()) {
            warning("A telefonszám formátuma helytelen (helyes formátum: (hivószám száma) xxx-xxxx)");
            return;
        }
        Pattern VALID_CREDIT_CARD_REGEX = Pattern.compile("^[0-9]{4}\\-[0-9]{4}\\-[0-9]{4}\\-[0-9]{4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher_CC = VALID_CREDIT_CARD_REGEX.matcher(creditcard);
        if (!matcher_CC.find()) {
            warning("A bankszámla formátuma helytelen (helyes fomrátum: xxxx-xxxx-xxxx-xxxx)");
            return;
        }
        Company newCompany = new Company(0, name, phone, totalEmpolyee, creditcard);
        Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = converter.toJson(newCompany);
        try {
            Response response = RequestHandler.post(App.BASE_URL, json);
            if (response.getResponseCode() == 201) {
                warning("A cég hozzáadása sikeres volt!");
                nameField.setText("");
                PhoneField.setText("");
                totalempolyeeField.getValueFactory().setValue(50);
                creditcardField.setText("");
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
