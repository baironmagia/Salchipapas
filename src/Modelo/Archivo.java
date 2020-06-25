package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


public class Archivo {

    private FileReader lectorAchivo;
    private String texto;
    
    
    private String fechaLimite;
    

    public boolean Leer(){
        try {
            lectorAchivo=new FileReader("conf.txt");
            BufferedReader textoArchivo=new BufferedReader(lectorAchivo);
            texto=textoArchivo.readLine();// leo la primera linea del archivo i la meto en la bariable texto
            textoArchivo.close();// cierro el BufferedReader
            lectorAchivo.close();// cierro el archivo
           
            String datos[]=texto.split(";");
            fechaLimite=datos[3];
            
            if(fechaLimite.equals(getFechaSistema())){
                File archivo=new File("conf.txt");
                archivo.delete();
                JOptionPane.showMessageDialog(null,"Contactar al Desarrollador de esta aplicioncion \n         Telefonos: 3148134241,3154658804 \n           Email: baironforver@homtail.com","No prodras Iniciar",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            else{
//                AccesoDatos.bd=datos[0];
//                AccesoDatos.user=datos[1];
//                AccesoDatos.password=datos[2];
//                if(AccesoDatos.password.equals("0"))AccesoDatos.password="";
            }    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Contactar al Desarrollador de esta aplicioncion \n         Telefonos: 3148134241,3154658804 \n           Email: baironforver@homtail.com","No prodras Iniciar",JOptionPane.WARNING_MESSAGE);
            return false;
        } 
        return true;
    } 
    
    private String getFechaSistema(){
        DateFormat formato=new SimpleDateFormat("YYYY/MM/dd");  
        return formato.format(new Date());
    }
}
