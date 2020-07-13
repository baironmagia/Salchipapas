
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

public class LogicaCompraDia {
    private Connection cn;//crea una variable para la conexion
    public String pk;
    public DefaultTableModel modelo;
    public Funcion funcion;
    private CallableStatement fun;//llama las funciones de mysql
    private boolean seleccion=false;
    public LogicaCompraDia() {
        funcion=new Funcion();
        IniciarModel();
    }  
    private void IniciarModel(){          
        Funcion.PitarScroll(Control.v5.scroll);
        Funcion.PintarMarcoTabla(Control.v5.tabla);
        String titulo1[]={"","Identifica Persona","Identifica Proveedor","Fecha",""};
        modelo =new DefaultTableModel(null, titulo1){
            public boolean isCellEditable(int row, int column) {
                if(column==5)return true;
                else return false;   
            } 
        }; 
        CargarTabla(4,"",Control.v5.tabla,modelo);
        TamañoTabla(Control.v5.tabla);     
    }
    public void Add(){
        if(Verificar()){
            try {
                if(funcion.Confirme(4)==0){
                    cn = AccesoDatos.conexion();
                    fun=cn.prepareCall("{?=call  AddCompraDia(?,?)}");
                    fun.registerOutParameter(1,Types.BOOLEAN);
                    fun.setString(2,Control.v5.id_per_txt.getText());                 
                    fun.setString(3,Control.v5.id_prove_txt.getText());
                    fun.execute();
                    if(fun.getBoolean(1)){      
                        seleccion=false;
                        LimpiarCajas(); 
                        Funcion.Limpiar_tabla(Control.v5.tabla,modelo);
                        CargarTabla(4,"",Control.v5.tabla,modelo);  
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
                        fun=cn.prepareCall("{?=call UpCompraDia(?,?,?)}");
                        fun.registerOutParameter(1,Types.BOOLEAN);
                        fun.setString(2,pk);
                        fun.setString(3,Control.v5.id_per_txt.getText());
                        fun.setString(4,Control.v5.id_prove_txt.getText());
                        
                        fun.execute();

                       if(fun.getBoolean(1)){
                        seleccion=false;
                        LimpiarCajas(); 
                        Funcion.Limpiar_tabla(Control.v5.tabla,modelo);
                        CargarTabla(4,"",Control.v5.tabla,modelo);  
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
        String registro[] = new String[5];
        String sql = null;
        //select *from compra_dia where fecha between '2020-07-06' and '2020-07-13';
        if(num!=0){
            if (num==1)sql = "SELECT *FROM compra_dia where id_perso LIKE '%" + valor + "%' and est_comp=1"; 
            if (num==2)sql = "SELECT *FROM compra_dia where id_prove LIKE '%" + valor + "%' and est_comp=1"; 
            if (num==3)sql = "select *from compra_dia where fecha between '"+Funcion.getFecha(Control.v5.fec_0)+"' and '"+Funcion.getFecha(Control.v5.fec_1)+"' ";
            if (num==4)sql = "SELECT *FROM compra_dia where est_comp=1";
            if(num!=4)Funcion.Limpiar_tabla(t,m);   
            try {
                cn =AccesoDatos.conexion();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    registro[0] = rs.getString(1);//id
                    registro[1] = rs.getString(2);//id_perso
                    registro[2] = rs.getString(3);//id_prov
                    registro[3] = rs.getString(4);//fec
                    registro[4] = rs.getString(5);//est
                    
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
        columnModel.getColumn(1).setPreferredWidth(100);//id_perso
        columnModel.getColumn(2).setPreferredWidth(100);//id_prov
        columnModel.getColumn(3).setPreferredWidth(200);//fec
       
        //id
        t.getColumnModel().getColumn(0).setMinWidth(0);
        t.getColumnModel().getColumn(0).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        // estado
        t.getColumnModel().getColumn(4).setMinWidth(0);
        t.getColumnModel().getColumn(4).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);  
        
 
    }
    public void Select(JTable t){
        if (t.getSelectedRow() != -1) {
            int fila = t.getSelectedRow();                 
            pk=t.getValueAt(fila,0).toString();
            Control.v5.id_per_txt.setText(t.getValueAt(fila,1).toString());
            Control.v5.id_prove_txt.setText(t.getValueAt(fila,2).toString());
            seleccion=true;     
        }      
    }
    public void LimpiarCajas(){
        Control.v5.id_per_txt.setText("");
        Control.v5.id_prove_txt.setText(""); 
        
    }
    private boolean Verificar(){
        if(!Control.v5.id_per_txt.getText().isEmpty()&&!Control.v5.id_prove_txt.getText().isEmpty()) return true;
        else funcion.Aviso(14);
        return false; 
    }
}
