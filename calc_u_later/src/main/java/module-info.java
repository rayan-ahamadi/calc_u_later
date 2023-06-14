module com.example.calc_u_later {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;


    opens com.example.calc_u_later to javafx.fxml;
    exports com.example.calc_u_later;
}