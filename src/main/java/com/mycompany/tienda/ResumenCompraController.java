/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tienda;

import Conect.ConnectionDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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

    @FXML
    private JFXComboBox comboFruta;

    @FXML
    private JFXButton btnVolver;

    @FXML
    private JFXTextField txtNombre, txtCantidad, txtPrecioTotal,txtTicket;

    @FXML
    private Text titulo;

    @FXML
    public TableView<Fruta> tablaFrutas;
    @FXML
    public TableColumn<Fruta, Integer> colId;
    @FXML
    public TableColumn<Fruta, Integer> colCantidad;
    @FXML
    public TableColumn<Fruta, String> colNombre;
    @FXML
    public TableColumn<Fruta, Double> colPrecio;

    @FXML
    public TableColumn<Fruta, Double> colPrecioTotal;

    @FXML
    public ObservableList<Fruta> filtroFruta;

    @FXML
    private void volverAtras() throws IOException {
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

    //FILTRO PARA SACAR EN LA TABLA LA FRUTA QUE QUERAMOS
    @FXML
    private void filtro() {

        tablaFrutas.setItems(Validaciones.itemCombo(comboFruta));

    }

    //RESETEAR EL FILTRO
    @FXML
    private void limpiarCombo() {
        //Validaciones.vaciarCombo(comboFruta);
        comboFruta.setValue("Selecciona la fruta");
        llenarTabla();
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecioTotal.setText("");

    }

    //PARA VER QUE FILA ESTA SELECCIONADA Y SE LO ASIGNAMOS AL MOUSE CLICKED DE LA TABLA
    @FXML
    public void getSelected() {
        int index = tablaFrutas.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;

        }

        txtNombre.setText(colNombre.getCellData(index));
        txtCantidad.setText(colCantidad.getCellData(index).toString());
        txtPrecioTotal.setText(colPrecioTotal.getCellData(index).toString());

    }

    @FXML
    private void llenarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<Fruta, Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Fruta, String>("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<Fruta, Integer>("cantidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Fruta, Double>("precio"));
        colPrecioTotal.setCellValueFactory(new PropertyValueFactory<Fruta, Double>("precioTotal"));

        tablaFrutas.setItems(Validaciones.getFrutas());
    }

    @FXML
    private void sumarTotal(){
        double p=0;
        double t=0;
        for(int i = 0 ; i < tablaFrutas.getItems().size();i++){
            p = colPrecioTotal.getCellData(i);
            t +=p;
        }
        txtTicket.setText(String.valueOf(t+" €"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTabla();
        
        Validaciones.llenarCombo(comboFruta);
        sumarTotal();
    }

}
