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

public class LogicaPuntos {

    private Connection cn;//crea una variable para la conexion
    public String pk;
    public DefaultTableModel modelo;
    public Funcion funcion;
    private CallableStatement fun;//llama las funciones de mysql
    private boolean seleccion = false;
    private ComboVentaCliente CVCLI;
   

    public LogicaPuntos() {
        funcion = new Funcion();
        IniciarModel();
        CVCLI = new ComboVentaCliente();
        CVCLI.CargarCliente(Control.v8.cmb_clientes);
    }

    private void IniciarModel() {
        Funcion.PitarScroll(Control.v8.scroll);
        Funcion.PintarMarcoTabla(Control.v8.tabla);
        String titulo1[] = {"", "", "Cliente", "Cantidad de puntos", "Fecha",""};
        modelo = new DefaultTableModel(null, titulo1) {
            public boolean isCellEditable(int row, int column) {
                if (column == 6) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        CargarTabla(4, "", Control.v8.tabla, modelo);
        TamañoTabla(Control.v8.tabla);
    }

    public void Add() {
        if (Verificar()) {
            try {
                if (funcion.Confirme(4) == 0) {
                    cn = AccesoDatos.conexion();
                    fun = cn.prepareCall("{?=call  AddPuntos(?,?)}");
                    fun.registerOutParameter(1, Types.BOOLEAN);
                    fun.setInt(2,Integer.parseInt( Control.v8.txt_puntos.getText()));
                    fun.setInt(3,Control.v8.cmb_clientes.getItemAt(Control.v8.cmb_clientes.getSelectedIndex()).getId());
                    fun.execute();
                    if (fun.getBoolean(1)) {
                        seleccion = false;
                        LimpiarCajas();
                        Funcion.Limpiar_tabla(Control.v8.tabla, modelo);
                        CargarTabla(4, "", Control.v8.tabla, modelo);
                        funcion.Aviso(3);
                    }
                    cn.close();
                    fun.close();
                }
            } //manejo de los indice o valores nulos
            catch (SQLIntegrityConstraintViolationException e) {
                funcion.Aviso(6);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void Update() {

        if (seleccion) {

            if (Verificar()) {
                try {
                    if (funcion.Confirme(1) == 0) {
                        cn = AccesoDatos.conexion();
                        fun = cn.prepareCall("{?=call Uppuntos(?,?,?)}");
                        fun.registerOutParameter(1, Types.BOOLEAN);
                        fun.setString(2, pk);
                        fun.setInt(3,Integer.parseInt( Control.v8.txt_puntos.getText()));
                        fun.setInt(4, CVCLI.Item(Control.v8.cmb_clientes.getSelectedItem().toString()));

                        fun.execute();

                        if (fun.getBoolean(1)) {
                            seleccion = false;
                            LimpiarCajas();
                            Funcion.Limpiar_tabla(Control.v8.tabla, modelo);
                            CargarTabla(4, "", Control.v8.tabla, modelo);
                            funcion.Aviso(3);
                        }
                        cn.close();
                        fun.close();
                    }
                } //manejo de los indice o valores nulos
                catch (SQLIntegrityConstraintViolationException e) {
                    funcion.Aviso(6);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } else {
            funcion.Aviso(9);
        }
    }

    public void CargarTabla(int num, String valor, JTable t, DefaultTableModel m) {
        String registro[] = new String[6];
        String sql = null;
        if (num != 0) {
            if (num == 1) {
                sql = "SELECT puntos.id_pun, cliente.id_cli,cliente.nom1_cli, puntos.cant_pun, puntos.fecha_pun,puntos.est_pun FROM puntos,cliente WHERE puntos.id_cli=cliente.id_cli AND cliente.nom1_cli LIKE '%" + valor + "%'";
            }
            if (num == 2) {
                sql = "SELECT puntos.id_pun, cliente.id_cli,cliente.nom1_cli, puntos.cant_pun, puntos.fecha_pun,puntos.est_pun FROM puntos,cliente WHERE puntos.id_cli=cliente.id_cli AND  puntos.cant_pun BETWEEN '"+Control.v8.txt_pun_ini.getText()+"' AND '"+Control.v8.txt_pun_fin.getText()+"'";
            }
            if (num == 3) {
                sql = "SELECT puntos.id_pun, cliente.id_cli,cliente.nom1_cli, puntos.cant_pun, puntos.fecha_pun,puntos.est_pun FROM puntos,cliente WHERE puntos.id_cli=cliente.id_cli AND  puntos.fecha_pun BETWEEN '" + Funcion.getFecha(Control.v8.fec_0) + "' AND '" + Funcion.getFecha(Control.v8.fec_1) + "'";
            }
            if (num == 4) {
                sql = "SELECT puntos.id_pun, cliente.id_cli,cliente.nom1_cli, puntos.cant_pun, puntos.fecha_pun,puntos.est_pun FROM puntos,cliente WHERE puntos.id_cli=cliente.id_cli";
            }

            if (num != 4) {
                Funcion.Limpiar_tabla(t, m);
            }
            try {
                cn = AccesoDatos.conexion();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    registro[0] = rs.getString(1);//id
                    registro[1] = rs.getString(2);//id_cli
                    registro[2] = rs.getString(3);//nom1_cli
                    registro[3] = rs.getString(4);//cant_pun
                    registro[4] = rs.getString(5);//fec
                    registro[5] = rs.getString(6);//est_pun
                    
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
        columnModel.getColumn(2).setPreferredWidth(150);//nom1_cli
        columnModel.getColumn(3).setPreferredWidth(150);//nom1__perso
        columnModel.getColumn(4).setPreferredWidth(100);//fec

        //id
        t.getColumnModel().getColumn(0).setMinWidth(0);
        t.getColumnModel().getColumn(0).setPreferredWidth(0);
        t.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        // 
        t.getColumnModel().getColumn(1).setMinWidth(0);
        t.getColumnModel().getColumn(1).setPreferredWidth(0);
        t.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        
        t.getColumnModel().getColumn(5).setMinWidth(0);
        t.getColumnModel().getColumn(5).setPreferredWidth(0);
        t.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);

    }

    public void Select(JTable t) {
        if (t.getSelectedRow() != -1) {
            int fila = t.getSelectedRow();
            pk = t.getValueAt(fila, 0).toString();
            Control.v8.cmb_clientes.getModel().setSelectedItem(t.getValueAt(fila, 2).toString());
            Control.v8.txt_puntos.setText(t.getValueAt(fila,3).toString());
//            Control.v1.n2_txt.setText(t.getValueAt(fila,4).toString());
            seleccion = true;
        }
    }

    public void LimpiarCajas() {
        Control.v8.cmb_clientes.setSelectedIndex(0);
        Control.v8.txt_puntos.setText("");
        Control.v8.txt_pun_ini.setText("");
        Control.v8.txt_pun_fin.setText("");
    }

    private boolean Verificar() {
        if (Control.v8.cmb_clientes.getSelectedIndex() != 0 && !Control.v8.txt_puntos.getText().isEmpty()) {
            return true;
        } else {
            funcion.Aviso(14);
        }
        return false;
    }
}
