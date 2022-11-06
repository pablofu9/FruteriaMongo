/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tienda;

import Conect.ConnectionDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bson.Document;
import validar.Validaciones;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class ResumenCompraController implements Initializable {

    MongoClient conexion;

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btnVolver, btnActualizar;
    
    @FXML
    private JFXTextField txtNombre, txtCantidad, txtPrecioTotal;

    @FXML
    private Text titulo;

    @FXML
    public TableView<Document> tablaFrutas;
    @FXML
    public TableColumn<Document, Integer> colId;
    @FXML
    public TableColumn<Document, Integer> colCantidad;
    @FXML
    public TableColumn<Document, String> colNombre;
    @FXML
    public TableColumn<Document, Double> colPrecio;

    @FXML
    public TableColumn<Document, Double> colPrecioTotal;
    
    @FXML
    private void volverAtras() throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        //Para cerrar el login
        Stage loginStage = (Stage) this.btnVolver.getScene().getWindow();
        loginStage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = ConnectionDB.conectar();
        MongoDatabase database = conexion.getDatabase("fruteria");
        
        colId.setCellValueFactory(new PropertyValueFactory<Document, Integer>("_id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Document, String>("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<Document, Integer>("cantidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Document, Double>("precio"));
        colPrecioTotal.setCellValueFactory(new PropertyValueFactory<Document, Double>("precioTotal"));
        
        ObservableList<Document> lista;
        lista=Validaciones.getFrutas(conexion);
        //FALTA LLENAR LA TABlA
        /*tablaFrutas.setItems(lista);
        System.out.println(lista);*/
        
    }

}
