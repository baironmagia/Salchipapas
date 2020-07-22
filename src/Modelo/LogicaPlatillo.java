
package Modelo;

import Controlador.Control;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class LogicaPlatillo {
    private Connection cn;
    public String pk;
    public DefaultTableModel modelo;
    public Funcion funcion;
    private CallableStatement fun;
    private boolean seleccion=false;
   
    public LogicaPlatillo() {
        funcion=new Funcion();
        IniciarModel();
    }  
    private void IniciarModel(){          
        Funcion.PitarScroll(Control.v7.scroll);
        Funcion.PintarMarcoTabla(Control.v7.tabla);
        String titulo1[]={"","Descripcion","Valor",""};
        modelo =new DefaultTableModel(null, titulo1){
            public boolean isCellEditable(int row, int column) {
                if(column==4)return true;
                else return false;   
            } 
        }; 
        CargarTabla(2,"",Control.v7.tabla,modelo);
        TamañoTabla(Control.v7.tabla);     
    }
    public void Add(){
        if(Verificar()){
            try {
                if(funcion.Confirme(4)==0){
                    cn = AccesoDatos.conexion();
                    fun=cn.prepareCall("{?=call  AddPlatillo(?,?)}");
                    fun.registerOutParameter(1,Types.BOOLEAN);
                    fun.setString(2,Control.v7.txt_des.getText());
                    fun.setString(3,Control.v7.txt_valor.getText());
                    
                    fun.execute();
                    if(fun.getBoolean(1)){      
                        seleccion=false;
                        LimpiarCajas(); 
                        Funcion.Limpiar_tabla(Control.v7.tabla,modelo);
                        CargarTabla(2,"",Control.v7.tabla,modelo);  
                        funcion.Aviso(3);
                    }
                    cn.close();
                    fun.close();
                }
            }
            //manejo de los indice o valores nulos
            catch (SQLIntegrityConstraintViolationException e){
               funcion.Aviso(6);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null,e);
            }     
        }
    }
    public void Update(){
        if(seleccion){
            if(Verificar()){
               try {
                   if(funcion.Confirme(1)==0){
                        cn = AccesoDatos.conexion();
                        fun=cn.prepareCall("{?=call Upplatillo(?,?,?)}");
                        fun.registerOutParameter(1,Types.BOOLEAN);
                        fun.setString(2,pk);
                        fun.setString(3,Control.v7.txt_des.getText());
                        fun.setString(4,Control.v7.txt_valor.getText());
                        
                        fun.execute();

                       if(fun.getBoolean(1)){
                        seleccion=false;
                        LimpiarCajas(); 
                        Funcion.Limpiar_tabla(Control.v7.tabla,modelo);
                        CargarTabla(2,"",Control.v7.tabla,modelo);  
                        funcion.Aviso(3);
                       }               
                       cn.close();
                       fun.close();
                   }
               }
               //manejo de los indice o valores nulos
               catch (SQLIntegrityConstraintViolationException e){
                   funcion.Aviso(6);
               }
               catch (SQLException e){
                   JOptionPane.showMessageDialog(null,e);
               }
            }
        }else funcion.Aviso(9);
    }
    public void CargarTabla(int num, String valor,JTable t,DefaultTableModel m) {     
        String registro[] = new String[3];
        String sql = null;
        
        if(num!=0){
            if (num==1)sql = "select *from platillo where des_plato like '%" + valor + "%' and est_plato=1"; 
            if (num==2)sql = "SELECT *FROM platillo";
            if(num!=2)Funcion.Limpiar_tabla(t,m);   
            try {
                cn =AccesoDatos.conexion();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    registro[0] = rs.getString(1);//id
                    registro[1] = rs.getString(2);//des
                    registro[2] = rs.getString(3);//valor
                    m.addRow(registro);
                }
                cn.close();
                rs.close();
                t.setModel(m); 
                TamañoTabla(t);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Registro");
            }
        }
    }
    private void TamañoTabla(JTable t) {
        TableColumnModel columnModel = t.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(300);//des
        columnModel.getColumn(2).setPreferredWidth(100);//valor
      
        //id
        t.getColumnModel().getColumn(0).setMinWidth(0);
        t.getColumnModel().getColumn(0).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        
        //estado
        t.getColumnModel().getColumn(3).setMinWidth(0);
        t.getColumnModel().getColumn(3).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);


    }
    public void Select(JTable t){
        if (t.getSelectedRow() != -1) {
            int fila = t.getSelectedRow();                 
            pk=t.getValueAt(fila,0).toString();
            Control.v7.txt_des.setText(t.getValueAt(fila,1).toString());
            Control.v7.txt_valor.setText(t.getValueAt(fila,2).toString());
            seleccion=true;     
        }      
    }
    public void LimpiarCajas(){
        Control.v7.txt_des.setText("");
        Control.v7.txt_valor.setText("");
        //Control.v2.tipo_combo.setSelectedIndex(0);
       // Control.v1.tabla.clearSelection();//deseleccionando la fila seleccionada
    }
    private boolean Verificar(){
        if(!Control.v7.txt_des.getText().isEmpty()&&!Control.v7.txt_valor.getText().isEmpty())return true;
        else funcion.Aviso(14);
        return false; 
    }
}
