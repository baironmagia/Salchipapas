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

public class LogicaVenta {

    private Connection cn;//crea una variable para la conexion
    public String pk;
    public DefaultTableModel modelo;
    public Funcion funcion;
    private CallableStatement fun;//llama las funciones de mysql
    private boolean seleccion = false;
    private ComboVentaCliente CVCLI;
    private ComboVentaPersona CVPER;

    public LogicaVenta() {
        funcion = new Funcion();
        IniciarModel();
        CVCLI = new ComboVentaCliente();
        CVPER = new ComboVentaPersona();

        CVCLI.CargarCliente(Control.v6.cmb_cliente);
        CVPER.CargarPersona(Control.v6.cmb_persona);
    }

    private void IniciarModel() {
        Funcion.PitarScroll(Control.v6.scroll);
        Funcion.PintarMarcoTabla(Control.v6.tabla);
        String titulo1[] = {"", "", "", "Cliente", "Persona", "Fecha", ""};
        modelo = new DefaultTableModel(null, titulo1) {
            public boolean isCellEditable(int row, int column) {
                if (column == 7) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        CargarTabla(4, "", Control.v6.tabla, modelo);
        TamañoTabla(Control.v6.tabla);
    }

    public void Add() {
        if (Verificar()) {
            try {
                if (funcion.Confirme(4) == 0) {
                    cn = AccesoDatos.conexion();
                    fun = cn.prepareCall("{?=call  Addventa(?,?)}");
                    fun.registerOutParameter(1, Types.BOOLEAN);
                    fun.setInt(2, Control.v6.cmb_cliente.getItemAt(Control.v6.cmb_cliente.getSelectedIndex()).getId());
                    fun.setInt(3, Control.v6.cmb_persona.getItemAt(Control.v6.cmb_persona.getSelectedIndex()).getId());
                    fun.execute();
                    if (fun.getBoolean(1)) {
                        seleccion = false;
                        LimpiarCajas();
                        Funcion.Limpiar_tabla(Control.v6.tabla, modelo);
                        CargarTabla(4, "", Control.v6.tabla, modelo);
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
                        fun = cn.prepareCall("{?=call UpVenta(?,?,?)}");
                        fun.registerOutParameter(1, Types.BOOLEAN);
                        fun.setString(2, pk);
                        fun.setInt(3, CVCLI.Item(Control.v6.cmb_cliente.getSelectedItem().toString()));
                        fun.setInt(4, CVPER.Item(Control.v6.cmb_persona.getSelectedItem().toString()));

                        fun.execute();

                        if (fun.getBoolean(1)) {
                            seleccion = false;
                            LimpiarCajas();
                            Funcion.Limpiar_tabla(Control.v6.tabla, modelo);
                            CargarTabla(4, "", Control.v6.tabla, modelo);
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
        String registro[] = new String[7];
        String sql = null;
        if (num != 0) {
            if (num == 1) {
                sql = "SELECT venta.id_venta, cliente.id_cli, persona.id_perso,cliente.nom1_cli,persona.nom1_perso, venta.fecha_ven,venta.est_ven FROM venta,cliente,persona WHERE venta.id_cli = cliente.id_cli and venta.id_perso=persona.id_perso and cliente.nom1_cli LIKE '%" + valor + "%'";
            }
            if (num == 2) {
                sql = "SELECT venta.id_venta, cliente.id_cli, persona.id_perso,cliente.nom1_cli,persona.nom1_perso, venta.fecha_ven,venta.est_ven FROM venta,cliente,persona WHERE venta.id_cli = cliente.id_cli and venta.id_perso=persona.id_perso and persona.nom1_perso LIKE '%" + valor + "%'";
            }
            if (num == 3) {
                sql = "SELECT venta.id_venta, cliente.id_cli, persona.id_perso,cliente.nom1_cli,persona.nom1_perso, venta.fecha_ven,venta.est_ven FROM venta,cliente,persona WHERE venta.id_cli = cliente.id_cli and venta.id_perso=persona.id_perso and venta.fecha_ven between '" + Funcion.getFecha(Control.v6.fec_0) + "' and '" + Funcion.getFecha(Control.v6.fec_1) + "'";
            }
            if (num == 4) {
                sql = "SELECT venta.id_venta,cliente.id_cli,persona.id_perso,cliente.nom1_cli,persona.nom1_perso,venta.fecha_ven,venta.est_ven FROM venta,cliente,persona WHERE venta.id_cli = cliente.id_cli AND venta.id_perso = persona.id_perso";
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
                    registro[2] = rs.getString(3);//id_perso
                    registro[3] = rs.getString(4);//nom1_cli
                    registro[4] = rs.getString(5);//nom1_perso
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
        columnModel.getColumn(3).setPreferredWidth(150);//nom1_cli
        columnModel.getColumn(4).setPreferredWidth(150);//nom1__perso
        columnModel.getColumn(5).setPreferredWidth(100);//fec

        //id
        t.getColumnModel().getColumn(0).setMinWidth(0);
        t.getColumnModel().getColumn(0).setPreferredWidth(0);
        t.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        //id_cli
        t.getColumnModel().getColumn(1).setMinWidth(0);
        t.getColumnModel().getColumn(1).setPreferredWidth(0);
        t.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        t.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);

        //id_perso
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

    public void Select(JTable t) {
        if (t.getSelectedRow() != -1) {
            int fila = t.getSelectedRow();
            pk = t.getValueAt(fila, 0).toString();
            Control.v6.cmb_cliente.getModel().setSelectedItem(t.getValueAt(fila, 3).toString());
            Control.v6.cmb_persona.getModel().setSelectedItem(t.getValueAt(fila, 4).toString());

            seleccion = true;
        }
    }

    public void LimpiarCajas() {
        Control.v6.cmb_cliente.setSelectedIndex(0);
        Control.v6.cmb_persona.setSelectedIndex(0);
    }

    private boolean Verificar() {
        if (Control.v6.cmb_cliente.getSelectedIndex() != 0 && Control.v6.cmb_persona.getSelectedIndex() != 0) {
            return true;
        } else {
            funcion.Aviso(14);
        }
        return false;
    }
}
