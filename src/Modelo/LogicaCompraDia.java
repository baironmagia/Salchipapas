
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
    private Marca MA;
     private MarcaP MAP;
    public LogicaCompraDia() {
        funcion=new Funcion();
        IniciarModel();
        MA=new Marca();
        MAP=new MarcaP();
       MA.CargarMarca(Control.v5.cmb_persona);
       MAP.CargarMarca(Control.v5.cmb_proveedor);
    }  
    private void IniciarModel(){          
        Funcion.PitarScroll(Control.v5.scroll);
        Funcion.PintarMarcoTabla(Control.v5.tabla);
        String titulo1[]={"","","","Persona","Proveedor","Fecha",""};
        modelo =new DefaultTableModel(null, titulo1){
            public boolean isCellEditable(int row, int column) {
                if(column==7)return true;
                else return false;   
            } 
        }; 
        CargarTabla(4,"",Control.v5.tabla,modelo);
        TamañoTabla(Control.v5.tabla);     
    }
    public void Add(){
//        if(Verificar()){
            try {
                if(funcion.Confirme(4)==0){
                    cn = AccesoDatos.conexion();
                    fun=cn.prepareCall("{?=call  AddCompraDia(?,?)}");
                    fun.registerOutParameter(1,Types.BOOLEAN);
                    fun.setInt(2,Control.v5.cmb_persona.getItemAt(Control.v5.cmb_persona.getSelectedIndex()).getId());
                    fun.setInt(3,Control.v5.cmb_proveedor.getItemAt(Control.v5.cmb_proveedor.getSelectedIndex()).getId());
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
//        }
    }
    public void Update(){
//       JOptionPane.showMessageDialog(null," "+MA.Item(Integer.parseInt( Control.v5.cmb_persona.getModel().getSelectedItem()))  );
//        if(seleccion){
//            if(Verificar()){
//               try {
//                   if(funcion.Confirme(1)==0){
//                        cn = AccesoDatos.conexion();
//                        fun=cn.prepareCall("{?=call UpCompraDia(?,?,?)}");
//                        fun.registerOutParameter(1,Types.BOOLEAN);
//                        fun.setString(2,pk);
//                        fun.setInt(3,Control.v5.cmb_persona.getItemAt(Control.v5.cmb_persona.getSelectedIndex()).getId());
//                        fun.setInt(4,Control.v5.cmb_proveedor.getItemAt(Control.v5.cmb_proveedor.getSelectedIndex()).getId());
//                        
//                        fun.execute();
//
//                       if(fun.getBoolean(1)){
//                        seleccion=false;
//                        LimpiarCajas(); 
//                        Funcion.Limpiar_tabla(Control.v5.tabla,modelo);
//                        CargarTabla(4,"",Control.v5.tabla,modelo);  
//                        funcion.Aviso(3);
//                       }               
//                       cn.close();
//                       fun.close();
//                   }
//               }
//               //manejo de los indice o valores nulos
//               catch (SQLIntegrityConstraintViolationException e){
//                   funcion.Aviso(6);
//               }
//               catch (SQLException e){
//                   JOptionPane.showMessageDialog(null,e);
//               }
//            }
//        }else funcion.Aviso(9);
    }
    
    public void CargarTabla(int num, String valor,JTable t,DefaultTableModel m) {     
        String registro[] = new String[7];
        String sql = null;
        if(num!=0){
            if (num==1)sql = "SELECT compra_dia.id_compra,persona.id_perso,proveedor.id_prove,persona.nom1_perso,proveedor.nom1_prove,compra_dia.fecha,compra_dia.est_comp FROM persona,proveedor,compra_dia WHERE compra_dia.id_perso = persona.id_perso AND compra_dia.id_prove = proveedor.id_prove AND persona.nom1_perso LIKE '%"+valor+"%'"; 
            if (num==2)sql = "SELECT compra_dia.id_compra,persona.id_perso,proveedor.id_prove,persona.nom1_perso,proveedor.nom1_prove,compra_dia.fecha,compra_dia.est_comp FROM persona,proveedor,compra_dia WHERE compra_dia.id_perso = persona.id_perso AND compra_dia.id_prove = proveedor.id_prove AND proveedor.nom1_prove LIKE '%"+valor+"%'"; 
            if (num==3)sql ="SELECT compra_dia.id_compra,persona.id_perso,proveedor.id_prove,persona.nom1_perso,proveedor.nom1_prove,compra_dia.fecha,compra_dia.est_comp FROM persona,proveedor,compra_dia WHERE compra_dia.id_perso = persona.id_perso AND compra_dia.id_prove = proveedor.id_prove and compra_dia.fecha between '"+Funcion.getFecha(Control.v5.fec_0)+"' and '"+Funcion.getFecha(Control.v5.fec_1)+"'";
            if (num==4)sql = "SELECT compra_dia.id_compra,persona.id_perso,proveedor.id_prove, persona.nom1_perso,proveedor.nom1_prove,compra_dia.fecha,compra_dia.est_comp FROM persona,proveedor,compra_dia WHERE compra_dia.id_perso = persona.id_perso AND compra_dia.id_prove = proveedor.id_prove";
                       
            if(num!=4)Funcion.Limpiar_tabla(t,m);   
            try {
                cn =AccesoDatos.conexion();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    registro[0] = rs.getString(1);//id
                    registro[1] = rs.getString(2);//id_perso
                    registro[2] = rs.getString(3);//id_prov
                    registro[3] = rs.getString(4);//nom1_perso
                    registro[4] = rs.getString(5);//nom1_prove
                    registro[5] = rs.getString(6);//fec
                    registro[6] = rs.getString(7);//est
                    
                    
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
        columnModel.getColumn(3).setPreferredWidth(150);//nom1_perso
        columnModel.getColumn(4).setPreferredWidth(150);//nom1__prove
        columnModel.getColumn(5).setPreferredWidth(100);//fec
       
        //id
        t.getColumnModel().getColumn(0).setMinWidth(0);
        t.getColumnModel().getColumn(0).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        //id_perso
        t.getColumnModel().getColumn(1).setMinWidth(0);
        t.getColumnModel().getColumn(1).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);

        //id_prove
        t.getColumnModel().getColumn(2).setMinWidth(0);
        t.getColumnModel().getColumn(2).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);

        // estado
        t.getColumnModel().getColumn(6).setMinWidth(0);
        t.getColumnModel().getColumn(6).setPreferredWidth(0); 
        t.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);  
        
 
    }
    public void Select(JTable t){
        if (t.getSelectedRow() != -1) {
            int fila = t.getSelectedRow();                 
            pk=t.getValueAt(fila,0).toString();
//            JOptionPane.showMessageDialog(null, "cod compra_dia --  "+pk+ "cod persona -- " +t.getValueAt(fila,1).toString()+"cod proveedor -- " +t.getValueAt(fila,2).toString());
            Control.v5.cmb_persona.getModel().setSelectedItem(t.getValueAt(fila,3).toString());
            Control.v5.cmb_proveedor.getModel().setSelectedItem(t.getValueAt(fila,4).toString());
            
            seleccion=true;     
        }      
    }
    public void LimpiarCajas(){
        Control.v5.cmb_persona.setSelectedIndex(0);  
        Control.v5.cmb_proveedor.setSelectedIndex(0);
    }
    private boolean Verificar(){
        if(Control.v5.cmb_persona.getSelectedIndex() !=0 && Control.v5.cmb_proveedor.getSelectedIndex()!=0 ) return true;
        else funcion.Aviso(14);
        return false; 
    }
}
