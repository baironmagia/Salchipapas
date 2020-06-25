
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
public class AccesoDatos { 
    public static Connection conexion(){
        Connection conectar=null; 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/Salchipapas_bd","root","1234");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conectar;
    }
      
}
