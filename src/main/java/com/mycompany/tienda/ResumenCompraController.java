/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tienda;

import Conect.ConnectionDB;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
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
    private JFXTextField txtNombre, txtCantidad, txtPrecioTotal;

    @FXML
    private Text titulo;

    @FXML
    private TableView<Document> tablaFrutas;
    @FXML
    private TableColumn<Document, Integer> colId;
    @FXML
    private TableColumn<Document, Integer> colCantidad;
    @FXML
    private TableColumn<Document, String> colNombre;
    @FXML
    private TableColumn<Document, Double> colPrecio;

    @FXML
    private TableColumn<Document, Double> colPrecioTotal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = ConnectionDB.conectar();
        MongoDatabase database = conexion.getDatabase("fruteria");
        
        colId.setCellValueFactory(new PropertyValueFactory<Document, Integer>("_id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Document, String>("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<Document, Integer>("cantidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Document, Double>("precio"));
        colPrecioTotal.setCellValueFactory(new PropertyValueFactory<Document, Double>("precioTotal"));
        System.out.println(Validaciones.getFrutas(conexion));
        
        //FALTA LLENAR LA TABlA
        //tablaFrutas.setItems(Validaciones.getFrutas(conexion));
    }

}
