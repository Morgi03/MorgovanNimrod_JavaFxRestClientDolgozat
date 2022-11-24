package hu.petrik.morgovannimrod_javafxrestclientdolgozat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.google.gson.Gson;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CompanyController extends Controller {

    @FXML
    private TableColumn<Company, String> phoneCol;
    @FXML
    private Button updateBtn;
    @FXML
    private TableColumn<Company, Integer> totalemployeeCol;
    @FXML
    private Button insertBtn;
    @FXML
    private TableColumn<Company, String> nameCol;
    @FXML
    private TableView<Company> companyTable;
    @FXML
    private TableColumn<Company, String> creditcardCol;
    @FXML
    private Button deleteBtn;

    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("companyPhone"));
        totalemployeeCol.setCellValueFactory(new PropertyValueFactory<>("totalEmployee"));
        creditcardCol.setCellValueFactory(new PropertyValueFactory<>("creditCard"));
        Platform.runLater(() -> {
            try {
                loadFromServer();
            } catch (IOException e) {
                error("A szerverről nem lehetett adatot lekérni", e.getMessage());
                Platform.exit();
            }
        });
    }

    private void loadFromServer() throws IOException {
        Response response = RequestHandler.get(App.BASE_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Company[] companies = converter.fromJson(content, Company[].class);
        companyTable.getItems().clear();
        for (Company company : companies) {
            companyTable.getItems().add(company);
        }
    }

    @FXML
    public void insertClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("create-company-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 270);
            Stage stage = new Stage();
            stage.setTitle("Create Company");
            stage.setScene(scene);
            stage.show();
            insertBtn.setDisable(true);
            updateBtn.setDisable(true);
            deleteBtn.setDisable(true);
            stage.setOnCloseRequest(event -> {
                insertBtn.setDisable(false);
                updateBtn.setDisable(false);
                deleteBtn.setDisable(false);
                try {
                    loadFromServer();
                } catch (IOException e) {
                    error("Hiba történt a szerverrel való kommunikáció során");
                }
            });
        } catch (IOException e) {
            error("A szerverről nem lehetett adatot lekérni", e.getMessage());
        }
    }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {
        int selectedIndex = companyTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            warning("Elősszőr válassz ki egy vállalatot a törléshez");
            return;
        }

        Company selected = companyTable.getSelectionModel().getSelectedItem();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText(String.format("Biztosan szeretnéd törölni a(z) %s nevű vállalatot?", selected.getCompanyName()));
        Optional<ButtonType> optionalButtonType = confirmation.showAndWait();
        if (optionalButtonType.isEmpty()) {
            System.err.println("Ismeretlen hiba történt");
            return;
        }
        ButtonType clickedButton = optionalButtonType.get();
        if (clickedButton.equals(ButtonType.OK)) {
            String url = App.BASE_URL + "/" + selected.getId();
            try {
                RequestHandler.delete(url);
                loadFromServer();
            } catch (IOException e) {
                error("Hiba történt a szerverrel való kommunikáció során");
            }
        }
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
    }
}