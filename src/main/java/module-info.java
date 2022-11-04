module com.mycompany.tienda {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires java.sql;
    requires java.base;
    
    opens com.mycompany.tienda to javafx.fxml;
    exports com.mycompany.tienda;
}
