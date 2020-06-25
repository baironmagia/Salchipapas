
package Modelo;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Funcion {
       
    public static void PintarMarcoTabla(JTable t){
        JTableHeader tb=t.getTableHeader();
        tb.setDefaultRenderer(new GestionTabla());
        t.setRowHeight(22);
        t.setTableHeader(tb);
    }
    public static void PitarScroll(JScrollPane scrol){
        scrol.getViewport().setBackground(new Color(255,255,255));    
    }
    public static void Limpiar_tabla(JTable t,DefaultTableModel m) {
        for (int i = 0; i < t.getRowCount(); i++) {
            m.removeRow(i);
            i -= 1;
        }   
    }
    public static void SoloNumeros(int num,KeyEvent evt,JTextField campo){
        String letra=String.valueOf(evt.getKeyChar());
        switch(num){
            case 1:if(!(letra.matches("[0-9]")))evt.consume();// solo numersos enteros
            break;
            case 2:if(!(letra.matches("[0-9]")) && (int)evt.getKeyChar()!=46){
                        evt.consume();
                    }
                    if(evt.getKeyChar()=='.' && campo.getText().contains(".")){
                        evt.consume();               
                    }// numeros enteros y decimal
            break; 
            case 3:if(!(letra.matches("[0-9]")) && !letra.matches("-"))evt.consume();// nit
            break; 
        }
    }
    public static void SoloLetras(int num,KeyEvent evt){
        String letra=String.valueOf(evt.getKeyChar());
        switch(num){
            case 1:if(!(letra.matches("[a-z]")) && !(letra.matches("[A-Z]")))evt.consume();//Solo letras
            break;
            case 2:if(!(letra.matches("[a-z]")) && !(letra.matches("[A-Z]")) && !letra.matches(" "))evt.consume();//Solo letras y espacios
            break;  
        }
    }
    public static void SoloLetrasNumeros(int num,KeyEvent evt){
        String letra=String.valueOf(evt.getKeyChar());
        switch(num){
            case 1:if(!(letra.matches("[0-9]")) && !(letra.matches("[a-z]")) && !(letra.matches("[A-Z]")))evt.consume();// solo letras y numeros
            break;
            case 2:if(!(letra.matches("[a-z]")) && !(letra.matches("[A-Z]")) && !letra.matches(" ") && !(letra.matches("[0-9]")))evt.consume();//Solo letras y numeros con espacios  
        }
    }
    public static void ValidarEmail(KeyEvent evt){
        String letra=String.valueOf(evt.getKeyChar());
        if( !(letra.matches("[0-9]")) && !(letra.matches("[a-z]")) && !(letra.matches("[A-Z]")) && evt.getKeyChar()!='_' && evt.getKeyChar()!='@' && evt.getKeyChar()!='.')evt.consume();
    }
    public static Boolean validaEmail (String email) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void Aviso(int num){  
        switch(num){
            case 1:JOptionPane.showMessageDialog(null,"No se encontro resultados","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/vacio.png")));
            break;
            case 2:JOptionPane.showMessageDialog(null,"Se realizo el pago con Exito","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/pago.png")));
            break;
            case 3:JOptionPane.showMessageDialog(null,"Proceso Exitoso","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/corecto.png")));
            break;
            case 4:JOptionPane.showMessageDialog(null,"Acceso Denegado","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/negado.png")));
            break;
            case 5:JOptionPane.showMessageDialog(null,"No se encontro resultados","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/sinpermiso.png")));
            break;
            case 6:JOptionPane.showMessageDialog(null,"No permite Duplicados ni NULL","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/duplicaco.png")));
            break;
            case 7:JOptionPane.showMessageDialog(null,"Error de Proceso","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/errorEjecucion.png")));
            break;
            case 8:JOptionPane.showMessageDialog(null,"Nose Admite Numero negativos","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/negativo.png")));
            break;
            case 9:JOptionPane.showMessageDialog(null,"Seleccione un Registro","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/seleccione.png")));
            break;
            case 10:JOptionPane.showMessageDialog(null,"No se ejecuto el proceso","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/noEjecuion.png")));
            break;
            case 11:JOptionPane.showMessageDialog(null,"Lista vacia","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/listavacia.png")));
            break;
            case 12:JOptionPane.showMessageDialog(null,"Verifique que los datos este Correctos","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/nosesion.png")));
            break;
            case 13:JOptionPane.showMessageDialog(null,"Perosona Inactivo","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/usuInactivo.png")));
            break;
            case 14:JOptionPane.showMessageDialog(null,"Hay Campos Vacio","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/vacio.png")));
            break;
            case 15:JOptionPane.showMessageDialog(null,"Proceso Anulado","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/anular.png")));
            break;
            case 16:JOptionPane.showMessageDialog(null,"No se puede establecer la Conexion","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/conexion.png")));
            break;
            case 17:JOptionPane.showMessageDialog(null,"Email no Valido","Resultado",JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/IMG/correo.png")));
            break;
        }
        
    }
    public int Confirme(int num){
       // Icon icon;
        int op=-1;
        switch(num){
            case 1:op = JOptionPane.showConfirmDialog(null, "Confirme Actualizacion", "Seguro de Actualizar el Registro",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(getClass().getResource("/IMG/update.png")));
                break;
            case 2:op = JOptionPane.showConfirmDialog(null, "Confirme Eliminacion", "Seguro de Eliminar Registro",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(getClass().getResource("/IMG/eliminar.png")));
                break;
            case 3:op = JOptionPane.showConfirmDialog(null, "Confirme Anulacion", "Seguro de Anular Factura",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(getClass().getResource("/IMG/interrogacion.png")));
                break;
            case 4:op = JOptionPane.showConfirmDialog(null, "Confirme Guardar", "Seguro de Guardar",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(getClass().getResource("/IMG/save.png")));
                break;
        }
        return op;
    }
}
