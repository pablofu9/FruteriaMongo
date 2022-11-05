/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tienda;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import validar.Validaciones;
import static validar.Validaciones.calcularPrecio;
import Conect.ConnectionDB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import com.mongodb.MongoClientURI;
/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class MenuController implements Initializable {

    MongoClient conexion;
    /**
     * Initializes the controller class.
     */
    int ids[] = {1,2,3,4,5,6,7,8,9};
    double precios[] = {0.8,1,2,1.2,2.2,1.8,4,3.2,1};
    
    int id;
    
    @FXML
    private TextField txtCompra;
    
    
    @FXML
    private AnchorPane panelFrutas;

    @FXML
    private Label lblCompra, lblTotal;

    @FXML
    private ImageView imagenCompra;

    @FXML
    private Pane mandarina, panelPrincipal, panelComprar;

    @FXML
    private Button botonNaraja, botonPera, botonFresa, botonSandia, botonMelon, botonLimon, botonPapaya, botonAguacate, botonPlatano, botonCompra, botonCalcular, vaciar;

    
    //BOTONES QUE MANDAN LA FRUTA QUE QUIERES COMPRAR AL PANEL DE COMPRA
    @FXML
    private void comprarNaranja() throws MalformedURLException {
        lblCompra.setText("NARANJA");
        Path imageFile = Paths.get("src/main/resources/Images/naranja.png");
        imagenCompra.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        panelFrutas.setVisible(true);
        id = ids[0];
    }

    @FXML
    private void comprarPera() throws MalformedURLException {
        lblCompra.setText("PERA");
        Path imageFile = Paths.get("src/main/resources/Images/pera.png");
        imagenCompra.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        panelFrutas.setVisible(true);
        id = ids[1];
    }

    @FXML
    private void comprarFresa() throws MalformedURLException {
        lblCompra.setText("FRESA");
        Path imageFile = Paths.get("src/main/resources/Images/fresa.png");
        imagenCompra.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        panelFrutas.setVisible(true);
        id = ids[2];
    }

    @FXML
    private void comprarLimon() throws MalformedURLException {
        lblCompra.setText("LIMON");
        Path imageFile = Paths.get("src/main/resources/Images/limon.png");
        imagenCompra.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        panelFrutas.setVisible(true);
        id = ids[3];
    }

    @FXML
    private void comprarSandia() throws MalformedURLException {
        lblCompra.setText("SANDIA");
        Path imageFile = Paths.get("src/main/resources/Images/sandia.png");
        imagenCompra.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        panelFrutas.setVisible(true);
        id = ids[4];
    }

    @FXML
    private void comprarPapaya() throws MalformedURLException {
        lblCompra.setText("PAPAYA");
        Path imageFile = Paths.get("src/main/resources/Images/papaya.png");
        imagenCompra.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        panelFrutas.setVisible(true);
        id = ids[5];
    }

    @FXML
    private void comprarMelon() throws MalformedURLException {
        lblCompra.setText("MELON");
        Path imageFile = Paths.get("src/main/resources/Images/melon.png");
        imagenCompra.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        panelFrutas.setVisible(true);
    }

    @FXML
    private void comprarAguacate() throws MalformedURLException {
        lblCompra.setText("AGUACATE");
        Path imageFile = Paths.get("src/main/resources/Images/aguacate.png");
        imagenCompra.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        panelFrutas.setVisible(true);
        id = ids[7];
    }

    @FXML
    private void comprarPlatano() throws MalformedURLException {
        lblCompra.setText("PLATANO");
        Path imageFile = Paths.get("src/main/resources/Images/platano.png");
        imagenCompra.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        panelFrutas.setVisible(true);

        id = ids[8];
    }

    //BOTON PARA CALCULAR LOS PRECIOS
    
    
    //SELECCIONAMOS EL PRECIO DE LA FRUTA, SEGUN EL ID
    @FXML
    private void calculoPrecio() {
        
        if (id == 1){
            lblTotal.setText(String.valueOf((Validaciones.calcularPrecio(Integer.parseInt(txtCompra.getText()), precios[0]))));
        } else if (id == 2){  
            lblTotal.setText(String.valueOf((Validaciones.calcularPrecio(Integer.parseInt(txtCompra.getText()), precios[1]))));
        } else if (id == 3){
            lblTotal.setText(String.valueOf((Validaciones.calcularPrecio(Integer.parseInt(txtCompra.getText()), precios[2]))));
        }else if(id==4){
            lblTotal.setText(String.valueOf((Validaciones.calcularPrecio(Integer.parseInt(txtCompra.getText()), precios[3]))));
        }else if(id==5){
            lblTotal.setText(String.valueOf((Validaciones.calcularPrecio(Integer.parseInt(txtCompra.getText()), precios[4]))));
        }else if(id==6){
            lblTotal.setText(String.valueOf((Validaciones.calcularPrecio(Integer.parseInt(txtCompra.getText()), precios[5]))));
        }else if(id==7){
            lblTotal.setText(String.valueOf((Validaciones.calcularPrecio(Integer.parseInt(txtCompra.getText()), precios[6]))));
        }else if(id==8){
            lblTotal.setText(String.valueOf((Validaciones.calcularPrecio(Integer.parseInt(txtCompra.getText()), precios[7]))));
        }else if(id==9){
            lblTotal.setText(String.valueOf((Validaciones.calcularPrecio(Integer.parseInt(txtCompra.getText()), precios[8]))));
        }
        

    }
    
    @FXML
    private void comprar(){
        
            Validaciones.nuevaFruta(conexion,id,lblCompra.getText() , Integer.parseInt(txtCompra.getText()),precios[id-1]);
            /*if(Validaciones.buscarFruta(con, id,Integer.parseInt(txtCompra.getText()))==false){
                Validaciones.insertarFruta(con,id, lblCompra.getText(),Integer.parseInt(txtCompra.getText()), precios[id-1] );
                vaciar.setVisible(true);
                
            }else{
                Validaciones.modificarFruta(id,Integer.parseInt(txtCompra.getText()), precios[id-1]);
            }*/
        
           
        
        
    }
    
    //METODO PARA VACIAR EL CARRITO
    @FXML
    private void vaciarCarrito(){
        /*if(Validaciones.crearAlertaConf("¿Seguro que quieres vaciar el carrito?")){
            Validaciones.borrarDatos(con);
            panelFrutas.setVisible(false);
            vaciar.setVisible(false);
        }*/
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //CONECTA AL INICIAR LA CLASE
        conexion=ConnectionDB.conectar();
        MongoDatabase database= conexion.getDatabase("fruteria");
    }

}
