package Modelo;

import Controlador.Control;
import com.toedter.calendar.JDateChooser;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class LogicaBodega {

    private Connection cn;//parte de mysql
    public String pk;
    public DefaultTableModel modelo1,modelo;
    public Funcion funcion;
    private CallableStatement fun;//parte de mysql
    private boolean seleccion = false;
    String no, no1, no2, no3, no4;

    public LogicaBodega() {
        funcion = new Funcion();
        IniciarModel1();
        IniciarModelF();

    }

    private void IniciarModel1() {
        Funcion.PitarScroll(Control.v4.scroll1);
        Funcion.PintarMarcoTabla(Control.v4.tabla1);
        String titulo1[] = {"", "Persona", "Proveedor", "Total Compra", "Fecha"};
        modelo1 = new DefaultTableModel(null, titulo1) {
            public boolean isCellEditable(int row, int column) {
                if (column == 5) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        CargarTabla1(4, "", Control.v4.tabla1, modelo1);
        TamañoTabla1(Control.v4.tabla1);
    }

    public void CargarTabla1(int num, String valor, JTable t, DefaultTableModel m) {
        String registro[] = new String[5];
        String sql = null;
        if (num != 0) {
            if (num == 1) {
                sql = " SELECT compra_dia.id_compra,concat(persona.nom1_perso,' ',persona.ape1_perso) AS nom_perso ,concat(proveedor.nom1_prove,' ',proveedor.ape1_prove) AS nom_prove, (detalle_compra.valor*detalle_compra.cantidad) AS total,compra_dia.fecha AS fecha FROM compra_dia, detalle_compra, persona, proveedor WHERE  concat(persona.nom1_perso,' ',persona.ape1_perso) LIKE '%"+valor+"%' AND  detalle_compra.id_compra=compra_dia.id_compra AND compra_dia.id_compra=persona.id_perso AND compra_dia.id_prove=proveedor.id_prove AND detalle_compra.id_compra=compra_dia.id_compra GROUP BY compra_dia.id_compra";
            }
            if (num == 2) {
                sql = "SELECT compra_dia.id_compra,concat(persona.nom1_perso,' ',persona.ape1_perso) AS nom_perso ,concat(proveedor.nom1_prove,' ',proveedor.ape1_prove) AS nom_prove, (detalle_compra.valor*detalle_compra.cantidad) AS total,compra_dia.fecha AS fecha FROM compra_dia, detalle_compra, persona, proveedor WHERE  concat(proveedor.nom1_prove,' ',proveedor.ape1_prove) LIKE '%"+valor+"%' AND  detalle_compra.id_compra=compra_dia.id_compra AND compra_dia.id_compra=persona.id_perso AND compra_dia.id_prove=proveedor.id_prove AND detalle_compra.id_compra=compra_dia.id_compra GROUP BY compra_dia.id_compra";
            }
            if (num == 3) {
                sql = "SELECT compra_dia.id_compra,concat(persona.nom1_perso,' ',persona.ape1_perso) AS nom_perso ,concat(proveedor.nom1_prove,' ',proveedor.ape1_prove) AS nom_prove, detalle_compra.valor*detalle_compra.cantidad AS total,compra_dia.fecha AS fecha from compra_dia,detalle_compra,persona,proveedor WHERE detalle_compra.id_compra=compra_dia.id_compra AND compra_dia.id_compra=persona.id_perso AND compra_dia.id_prove=proveedor.id_prove AND detalle_compra.id_compra=compra_dia.id_compra  AND compra_dia.fecha BETWEEN '" + Funcion.getFecha(Control.v4.fec_0) + "' AND '" + Funcion.getFecha(Control.v4.fec_1) + "' AND compra_dia.id_compra";
            }
            if (num == 4) {
                sql = "SELECT compra_dia.id_compra,concat(persona.nom1_perso,' ',persona.ape1_perso) AS nom_perso ,concat(proveedor.nom1_prove,' ',proveedor.ape1_prove) AS nom_prove, detalle_compra.valor*detalle_compra.cantidad AS total,compra_dia.fecha AS fecha from compra_dia,detalle_compra,persona,proveedor WHERE detalle_compra.id_compra=compra_dia.id_compra AND compra_dia.id_compra=persona.id_perso AND compra_dia.id_prove=proveedor.id_prove AND detalle_compra.id_compra=compra_dia.id_compra GROUP BY compra_dia.id_compra";
            }

            if (num != 4) {
                Funcion.Limpiar_tabla(t, m);
            }
            try {
                cn = AccesoDatos.conexion();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    registro[0] = rs.getString(1);//id_compra
                    registro[1] = rs.getString(2);//nom_perso
                    registro[2] = rs.getString(3);//nom_prove
                    registro[3] = rs.getString(4);//total
                    registro[4] = rs.getString(5);//fecha
                    m.addRow(registro);
                }
                cn.close();
                rs.close();
                t.setModel(m);
                TamañoTabla1(t);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Registro" +ex.toString());
            }
        }
    }

    private void TamañoTabla1(JTable t) {
        TableColumnModel columnModel = t.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(200);//cantidad
        columnModel.getColumn(2).setPreferredWidth(200);//descripcion
        columnModel.getColumn(3).setPreferredWidth(100);//valor
        columnModel.getColumn(4).setPreferredWidth(100);//valor

        //id_compra
        t.getColumnModel().getColumn(0).setMinWidth(0);
        t.getColumnModel().getColumn(0).setPreferredWidth(0);
        t.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }

//--------------------------------------------------------------------------------
    
    
    private void IniciarModelF() {
        Funcion.PitarScroll(Control.v4.scroll);
        Funcion.PintarMarcoTabla(Control.v4.tabla);
        String titulo[] = {"", "Cantidad", "Descripcion", "Valor"};
        modelo = new DefaultTableModel(null, titulo) {
            public boolean isCellEditable(int row, int column) {
                if (column == 4) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        CargarTabla(Control.v4.tabla, modelo);
        TamañoTabla(Control.v4.tabla);
    }
    
    public void Select(JTable t) {
        if (t.getSelectedRow() != -1) {
            int fila = t.getSelectedRow();
            pk = t.getValueAt(fila, 0).toString();
            seleccion = true;
            funcion.Aviso(18);
        }
    }

    
    private void TamañoTabla(JTable t1) {
        TableColumnModel columnModel = t1.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(200);//cantidad
        columnModel.getColumn(2).setPreferredWidth(300);//descripcion
        columnModel.getColumn(3).setPreferredWidth(200);//valor

        //id_compra
        t1.getColumnModel().getColumn(0).setMinWidth(0);
        t1.getColumnModel().getColumn(0).setPreferredWidth(0);
        t1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        t1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }

    public void CargarTabla(JTable t1 ,DefaultTableModel m1) {
        String registro[] = new String[4];
        String sql = "SELECT * FROM detalle_compra WHERE id_compra=?";
        
       
        PreparedStatement pst;
        Funcion.Limpiar_tabla(t1, m1);
        try {
            
            cn = AccesoDatos.conexion();
            pst = cn.prepareStatement(sql);
            pst.setString(1, pk);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                registro[0] = rs.getString(1);//id_compra
                registro[1] = rs.getString(2);//cantidad
                registro[2] = rs.getString(3);//descripcion
                registro[3] = rs.getString(4);//valor
                m1.addRow(registro);
            }
            
            cn.close();
            rs.close();
            t1.setModel(m1);
            TamañoTabla(t1);    
        } catch (SQLException ex) {
            Logger.getLogger(LogicaBodega.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
   
}
