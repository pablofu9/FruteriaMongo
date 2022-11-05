/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validar;

import Conect.ConnectionDB;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Pablo
 */
public class Validaciones {

    public static double calcularPrecio(int cant, double preciokg) {
        double total;
        total = cant * preciokg;
        return total;
    }

    public static void nuevaFruta(MongoClient con, int id, String nombre, int cant, double precio) {

        //INSERTA NUEVA FRUTA
        MongoDatabase database = con.getDatabase("fruteria");
        MongoCollection<Document> collection = database.getCollection("frutas");
        Document encontrado = (Document) collection.find(new Document("_id", id)).first();
        if (encontrado == null)
        {
            Document d1 = new Document();
            d1.append("_id", id).append("nombre", nombre).append("cantidad", cant)
                    .append("precio", precio).append("precioTotal", cant * precio);
            
                collection.insertOne(d1);
                crearAlertaInfo("Fruta añadida al carrito");
                //SALTA EXCEPCION CUANDO LA FRUTA YA ESTA EN LA BD
             
        }else{
            //FALTA ACTUALIZAR EL PRECIOTOTAL
            double precioSumador=encontrado.getDouble("precioTotal");
            
            int cantTotal=encontrado.getInteger("cantidad");
            Bson actualizado = new Document("cantidad",cantTotal+cant);
            
            Bson operar = new Document("$set", actualizado);
            
            collection.updateOne(encontrado, operar);
            
            crearAlertaInfo("Carrito actualizado");
        }
    }

    //METODO PARA VACAIR EL CARRITO
    public static void vaciarCarrito(MongoClient con){
        MongoDatabase database = con.getDatabase("fruteria");
        MongoCollection<Document> collection = database.getCollection("frutas");
        try{
         collection.deleteMany(Filters.gte("_id", 0));
        }catch(NumberFormatException e){
            
        }
    }

    

    public static void crearAlertaInfo(String textoAlerta) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información");
        alert.setContentText(textoAlerta);
        alert.showAndWait();
    }

    public static boolean crearAlertaConf(String confirmar) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(confirmar);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK)
        {
            return true;
        } else
        {
            return false;
        }
    }

}
