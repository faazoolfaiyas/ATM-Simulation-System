module org.example.atm_simulation_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens org.example.atm_simulation_system to javafx.fxml;
    exports org.example.atm_simulation_system;
}