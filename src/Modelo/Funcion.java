
package Modelo;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
}
