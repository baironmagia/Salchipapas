
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Marca {
    private int id;
    private String nombre;
    private Connection cn;
    
    
    public Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Marca() {
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 
    
    public void CargarMarca(JComboBox<Marca> combo){
        try {
            cn = AccesoDatos.conexion();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT *FROM persona");
            combo.addItem(new Marca(0,"Seleccione"));
            while (rs.next()) {
                combo.addItem(new Marca(rs.getInt(1),rs.getString(2)));              
            }
            rs.close();
            st.close();
            cn.close();
        } catch (Exception e) {
            //funcion.Aviso(16);
        }
    }
//    public int Item(String n){       
//        int tem = 0;
//        try {
//            PreparedStatement pt=AccesoDatos.conexion().prepareStatement("SELECT persona.n FROM persona where id_perso=?");
//            pt.setInt(1,n);
//            ResultSet rs=pt.executeQuery();
//            while (rs.next())tem=rs.getString(1);
//        } catch (SQLException ex) {
//           Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return tem;
//    }
//    
    public int Item(String n){       
        int tem = 0;
        try {
            System.out.println(n);
            PreparedStatement pt=AccesoDatos.conexion().prepareStatement("SELECT *FROM persona where nom1_perso=?");
            pt.setString(1,n);
            ResultSet rs=pt.executeQuery();
            while (rs.next()){
                tem=rs.getInt(1);
            }
        } catch (SQLException ex) {
           Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(tem);
        return tem;
    }
    @Override
    public String toString(){
        return nombre;
    }
    
}
