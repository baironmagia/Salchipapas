
package Modelo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class GestionTabla implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent componente=null;
        if(value instanceof String){
            componente=new JLabel((String )value);
            ((JLabel)componente).setHorizontalAlignment(SwingConstants.CENTER);
            ((JLabel)componente).setSize(25,componente.getWidth());
            ((JLabel)componente).setPreferredSize(new Dimension(6,componente.getWidth()));
        }
        componente.setBorder(javax.swing.BorderFactory.createMatteBorder(0,0,1,1,new java.awt.Color(255, 255, 255)));
        componente.setOpaque(true);
        componente.setBackground(new Color(65, 65, 65));
        componente.setToolTipText("Tabla Contenido");
        componente.setForeground(Color.WHITE);
        
        return componente;    
    }
    
}
