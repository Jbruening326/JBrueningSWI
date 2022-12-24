module bruening.jbrueningc482 {
    requires javafx.controls;
    requires javafx.fxml;


    opens bruening.jbrueningc482 to javafx.fxml;
    exports bruening.jbrueningc482;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Model;
    opens Model to javafx.base;
}