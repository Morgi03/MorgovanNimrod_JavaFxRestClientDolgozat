module hu.petrik.morgovannimrod_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.petrik.morgovannimrod_javafxrestclientdolgozat to javafx.fxml;
    exports hu.petrik.morgovannimrod_javafxrestclientdolgozat;
}