package Controlador;
import Modelo.Archivo;
import Modelo.LogicaCliente;
import Modelo.LogicaUsuario;
import Vista.Principal;
import Vista.v_cliente;
import Vista.v_usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Control implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    public int numx, numy;
    public Principal principal;
    public static v_usuario v1;
    public static v_cliente v2;
    private Archivo a;
    private LogicaUsuario USU;
    private LogicaCliente CLI;

  
    public Control() {
        a=new Archivo();
        principal=new Principal();
        v1=new v_usuario();
        v2=new v_cliente();
        CLI=new LogicaCliente();
        USU=new LogicaUsuario();
        inicioBoton();
        
        
       //if(a.Leer())
          // this.principal.setVisible(true);
        
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==v1.save){
           USU.Add();
        }
        else if(e.getSource()==v1.uptade){
            USU.Update();
        }
        else if(e.getSource()==v1.clear){
            USU.LimpiarCajas();
        }
        
        
        

     
        

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() ==v1.tabla){
            USU.Select(v1.tabla);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
//        if(e.getSource() ==ventana.salir_btn){
//           System.exit(0);
//        }
//        else if(e.getSource()==p_op1.add_lbl){
//            System.out.println(producto.id_marca.get(p_op1.combo_marca.getSelectedIndex()-1));
//            producto.Add_art();
//        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
       
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() ==principal.lblUsuario){
            principal.lblUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==principal.lblCliente){
            principal.lblCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==principal.lblProveedor){
            principal.lblProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==principal.lblBodega){
            principal.lblBodega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==principal.rBodega){
            principal.rBodega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==principal.rVenta){
            principal.rVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==principal.rCliente){
            principal.rCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==principal.rProveedor){
            principal.rProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource() == principal.escote) {
            //ventana principal
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            principal.setLocation(x - numx, y - numy);
        }
        if (e.getSource() == v1.escote) {
            //ventana principal
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            v1.setLocation(x - numx, y - numy);
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getSource() == principal.escote) {
            //ventana principal
            numx = e.getX();
            numy = e.getY();
        }
        if (e.getSource() == v1.escote) {
            //ventana principal
            numx = e.getX();
            numy = e.getY();
        }
        if(e.getSource() ==principal.lblUsuario){
            principal.lblUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==principal.lblCliente){
            principal.lblCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==principal.lblProveedor){
            principal.lblProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==principal.lblBodega){
            principal.lblBodega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==principal.rBodega){
            principal.rBodega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==principal.rVenta){
            principal.rVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==principal.rCliente){
            principal.rCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==principal.rProveedor){
            principal.rProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
          }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() ==v1.caja_txt) {
            USU.CargarTabla(v1.filtro_combo.getSelectedIndex(),v1.caja_txt.getText(),v1.tabla,USU.modelo);
        }
        
    }
    private void inicioBoton(){
        principal.lblUsuario.addMouseMotionListener(this);
        principal.lblUsuario.addMouseListener(this);   
        principal.lblBodega.addMouseMotionListener(this);
        principal.lblBodega.addMouseListener(this);
        principal.lblCliente.addMouseMotionListener(this);
        principal.lblCliente.addMouseListener(this);
        principal.lblProveedor.addMouseMotionListener(this);
        principal.lblProveedor.addMouseListener(this);
        principal.rBodega.addMouseMotionListener(this);
        principal.rBodega.addMouseListener(this);
        principal.rCliente.addMouseMotionListener(this);
        principal.rCliente.addMouseListener(this);
        principal.rProveedor.addMouseMotionListener(this);
        principal.rProveedor.addMouseListener(this);
        principal.rVenta.addMouseMotionListener(this);
        principal.rVenta.addMouseListener(this);
        principal.escote.addMouseMotionListener(this);
        principal.setLocationRelativeTo(null);
        
        
        v1.escote.addMouseMotionListener(this);
        v1.escote.addMouseListener(this);
        v1.tabla.addMouseListener(this);
        v1.caja_txt.addKeyListener(this);
        v1.save.addActionListener(this);
        v1.uptade.addActionListener(this);
        v1.clear.addActionListener(this);
        v1.setVisible(true);
       
        
    }
}
