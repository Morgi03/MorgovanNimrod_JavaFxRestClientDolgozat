module hu.petrik.morgovannimrod_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens hu.petrik.morgovannimrod_javafxrestclientdolgozat to javafx.fxml;
    exports hu.petrik.morgovannimrod_javafxrestclientdolgozat;
}