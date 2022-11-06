module com.mycompany.tienda {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires java.base;
    requires com.jfoenix;
    requires org.json;
    
    opens com.mycompany.tienda to javafx.fxml;
    exports com.mycompany.tienda;
}
