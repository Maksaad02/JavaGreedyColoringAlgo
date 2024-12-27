module com.mkrcoding.coloriageglouton {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.mkrcoding.coloriageglouton to javafx.fxml;
    exports com.mkrcoding.coloriageglouton;
}