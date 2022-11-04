/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validar;

import static Conect.Conexion.getConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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

    public static void insertarFruta(Connection con, int id, String nombre, int cantidad, double precio) {
        boolean valido = false;
        PreparedStatement ps;
        String sql;

        try
        {

            sql = "insert into fruits(id,nombre,cantidad,precio,precioTotal) values(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setInt(3, cantidad);
            ps.setDouble(4, precio);
            ps.setDouble(5, precio * cantidad);
            ps.executeUpdate();
            valido = true;
            crearAlertaInfo("Fruta añadida al carrito");

            //Salta la exception si encuentra un usuario con ese mismo dni
        } catch (SQLException e)
        {

        }

    }

    public static boolean buscarFruta(Connection conexion, int id, int cant) {

        PreparedStatement ps;
        ResultSet rs;
        boolean encontrado = false;

        try
        {
            String SQL = "SELECT * FROM fruits WHERE id = ? ;";
            ps = (PreparedStatement) conexion.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next())
            {
                return encontrado = true;
            }
            if (encontrado)
            {
                encontrado = true;

            } else
            {

            }
        } catch (SQLException ex)
        {

        }
        return encontrado;

    }

    public static void modificarFruta(int id, double cant, double precio) {
        Connection con = getConexion();
        PreparedStatement ps;
        ResultSet rs;
        try
        {

            String SQL = "SELECT * FROM fruits WHERE id = ? ;";
            ps = (PreparedStatement) con.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String sentenciaSql = "UPDATE fruits SET cantidad = ?, precioTotal = ? "
                        + "WHERE id=?";
                PreparedStatement sentencia = null;
                sentencia = con.prepareStatement(sentenciaSql);
                sentencia.setDouble(1, rs.getDouble("cantidad") + cant);
                sentencia.setDouble(2, rs.getDouble("precioTotal") + (cant * precio));
                sentencia.setInt(3, id);

                sentencia.executeUpdate();
                crearAlertaInfo("Carrito modificado");
            }
        } catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

    }

    public static void borrarDatos(Connection conexion) {
        String sentenciaSql = "DELETE FROM fruits";
        PreparedStatement sentencia = null;

        try
        {
            sentencia = conexion.prepareStatement(sentenciaSql);

            sentencia.executeUpdate();
        } catch (SQLException sqle)
        {
            sqle.printStackTrace();
        } finally
        {
            if (sentencia != null)
    try
            {
                sentencia.close();
            } catch (SQLException sqle)
            {
                sqle.printStackTrace();
            }
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
        if(action.get()==ButtonType.OK){
            return true;
        }else{
        return false;
    }
    }
    
    
}
