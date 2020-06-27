
package Modelo;

import Controlador.Control;
import Vista.v_proveedor;
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

public class LogicaProveedor {
    private Connection cn;
    public String pk;
    public DefaultTableModel modelo;
    public Funcion funcion;
    private CallableStatement fun;
    private boolean seleccion=false;
    public LogicaProveedor() {
        funcion=new Funcion();
        IniciarModel();
    }  
    private void IniciarModel(){          
        Funcion.PitarScroll(Control.v3.scroll);
        Funcion.PintarMarcoTabla(Control.v3.tabla);
        String titulo1[]={"","Primer Nombre","","Primer Apellido","","Telefono","","Direccion","","",""};
        modelo =new DefaultTableModel(null, titulo1){
            public boolean isCellEditable(int row, int column) {
                if(column==11)return true;
                else return false;   
            } 
        }; 
        CargarTabla(6,"",Control.v3.tabla,modelo);
        TamañoTabla(Control.v3.tabla);     
    }
    public void Add(){
        if(Verificar()){
            try {
                if(funcion.Confirme(4)==0){
                    cn = AccesoDatos.conexion();
                    fun=cn.prepareCall("{?=call  Addproveedor(?,?,?,?,?,?,?,?)}");
                    fun.registerOutParameter(1,Types.BOOLEAN);
                    fun.setString(2,Control.v3.n1_txt.getText());                 
                    fun.setString(3,Control.v3.n2_txt.getText());
                    fun.setString(4,Control.v3.ap1_txt.getText());
                    fun.setString(5,Control.v3.ap2_txt.getText());
                    fun.setString(6,Control.v3.tel_txt.getText());
                    fun.setString(7,Control.v3.nit_txt.getText());
                    fun.setString(8,Control.v3.ima_txt.getText());
                   
                    fun.execute();
                    if(fun.getBoolean(1)){      
                        seleccion=false;
                        LimpiarCajas(); 
                        Funcion.Limpiar_tabla(Control.v3.tabla,modelo);
                        CargarTabla(6,"",Control.v3.tabla,modelo);  
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
                        fun=cn.prepareCall("{?=call UpPersona(?,?,?,?,?,?,?,?,?,?,?,?)}");
                        fun.registerOutParameter(1,Types.BOOLEAN);
                        fun.setString(2,pk);
                        fun.setString(3,Control.v1.n1_txt.getText());
                        fun.setString(4,Control.v1.n2_txt.getText());
                        fun.setString(5,Control.v1.ap1_txt.getText());
                        fun.setString(6,Control.v1.ap2_txt.getText());
                        fun.setString(7,Control.v1.tel_txt.getText());
                        fun.setString(8,Control.v1.dir_txt.getText());
                        fun.setString(9,Control.v1.tipo_combo.getSelectedItem().toString());
                        fun.setString(10,Control.v1.usu_txt.getText());
                        fun.setString(11,Control.v1.clv_pass.getText());
                        fun.setString(12,Control.v1.email_txt.getText());
                        fun.setInt(13,Control.v1.est_combo.getSelectedIndex());
                        fun.execute();

                       if(fun.getBoolean(1)){
                        seleccion=false;
                        LimpiarCajas(); 
                        Funcion.Limpiar_tabla(Control.v1.tabla,modelo);
                        CargarTabla(6,"",Control.v1.tabla,modelo);  
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
        String registro[] = new String[11];
        String sql = null;
        
        if(num!=0){
            if (num==1)sql = "SELECT *FROM persona where nom1_perso LIKE '%" + valor + "%' where est_perso=1"; 
            if (num==2)sql = "SELECT *FROM persona where nom2_perso LIKE '%" + valor + "%' where est_perso=1"; 
            if (num==3)sql = "SELECT *FROM persona where ape1_perso LIKE '%" + valor + "%' where est_perso=1"; 
            if (num==4)sql = "SELECT *FROM persona where ape2_perso LIKE '%" + valor + "%' where est_perso=1"; 
            if (num==5)sql = "SELECT *FROM persona where tipo_perso LIKE '%" + valor + "%' where est_perso=1"; 
            if (num==6)sql = "SELECT *FROM persona where est_perso=1";
            if(num!=6)Funcion.Limpiar_tabla(t,m);   
            try {
                cn =AccesoDatos.conexion();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    registro[0] = rs.getString(1);//id
                    registro[1] = rs.getString(2);//nom1
                    registro[2] = rs.getString(3);//nom2
                    registro[3] = rs.getString(4);//ap1
                    registro[4] = rs.getString(5);//ap2
                    registro[5] = rs.getString(6);//tel
                    registro[6] = rs.getString(7);//nit
                    registro[7] = rs.getString(8);//dir
                    registro[8] = rs.getString(9);//cor
                    registro[9] = rs.getString(10);//fecha
                    registro[10] = rs.getString(11);//estado
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
        columnModel.getColumn(1).setPreferredWidth(100);//nombres1
        columnModel.getColumn(3).setPreferredWidth(100);//apellido1
        columnModel.getColumn(5).setPreferredWidth(70);//telefono
        columnModel.getColumn(6).setPreferredWidth(300);//direccion

        //id
        t.getColumnModel().getColumn(0).setMinWidth(0);
        t.getColumnModel().getColumn(0).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        //Segundo nombre
        t.getColumnModel().getColumn(2).setMinWidth(0);
        t.getColumnModel().getColumn(2).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);

        //Segundo Apellido
        t.getColumnModel().getColumn(4).setMinWidth(0);
        t.getColumnModel().getColumn(4).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);

        //Tipo
        t.getColumnModel().getColumn(7).setMinWidth(0);
        t.getColumnModel().getColumn(7).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);

        // Usuario
        t.getColumnModel().getColumn(8).setMinWidth(0);
        t.getColumnModel().getColumn(8).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);

        // fecha 
        t.getColumnModel().getColumn(9).setMinWidth(0);
        t.getColumnModel().getColumn(9).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);

        // estado
        t.getColumnModel().getColumn(10).setMinWidth(0);
        t.getColumnModel().getColumn(10).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);  
        
 
    }
    public void Select(JTable t){
        if (t.getSelectedRow() != -1) {
            int fila = t.getSelectedRow();                 
            pk=t.getValueAt(fila,0).toString();
            Control.v1.n1_txt.setText(t.getValueAt(fila,1).toString());
            Control.v1.n2_txt.setText(t.getValueAt(fila,2).toString());
            Control.v1.ap1_txt.setText(t.getValueAt(fila,3).toString());
            Control.v1.ap2_txt.setText(t.getValueAt(fila,4).toString());
            Control.v1.tel_txt.setText(t.getValueAt(fila,5).toString());
            Control.v1.dir_txt.setText(t.getValueAt(fila,6).toString());
            Control.v1.tipo_combo.setSelectedItem(t.getValueAt(fila,7).toString());
            Control.v1.usu_txt.setText(t.getValueAt(fila,8).toString());
            Control.v1.clv_pass.setText(t.getValueAt(fila,9).toString());
            Control.v1.email_txt.setText(t.getValueAt(fila,10).toString());
            Control.v1.est_combo.setSelectedIndex(Integer.parseInt(t.getValueAt(fila,11).toString()));
            seleccion=true;     
        }      
    }
    /*public void LimpiarCajas(){
        Control.v1.n1_txt.setText("");
        Control.v1.n2_txt.setText("");
        Control.v1.ap1_txt.setText("");
        Control.v1.ap2_txt.setText("");
        Control.v1.tel_txt.setText("");           
        Control.v1.dir_txt.setText("");
        Control.v1.email_txt.setText("");
        Control.v1.usu_txt.setText("");
        Control.v1.clv_pass.setText("");
        Control.v1.est_combo.setSelectedIndex(0);
        Control.v1.tipo_combo.setSelectedIndex(0);
        Control.v1.tabla.clearSelection();//deseleccionando la fila seleccionada
    }*/
    private boolean Verificar(){
        if(!Control.v3.n1_txt.getText().isEmpty()&&!Control.v3.ap1_txt.getText().isEmpty()&&
        !Control.v3.tel_txt.getText().isEmpty()&&!Control.v3.nit_txt.getText().isEmpty()&&
        !Control.v3.ima_txt.getText().isEmpty()) return true;
        else funcion.Aviso(14);
        return false; 
    }
}
