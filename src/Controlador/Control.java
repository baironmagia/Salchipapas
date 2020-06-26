package Controlador;
import Modelo.Archivo;
import Modelo.LogicaCliente;
import Modelo.LogicaProveedor;
import Modelo.LogicaUsuario;
import Vista.Principal;
import Vista.v_cliente;
import Vista.v_proveedor;
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
    public Principal v0;
    public static v_usuario v1;
    public static v_cliente v2;
    public static v_proveedor v3;
    private Archivo a;
    private LogicaUsuario USU;
    private LogicaCliente CLI;
    private LogicaProveedor PROV;

  
    public Control() {
        a=new Archivo();
        
        v0=new Principal();
        v1=new v_usuario();
        v2=new v_cliente();
        v3=new v_proveedor();
        
        
        CLI=new LogicaCliente();
        USU=new LogicaUsuario();
        PROV=new LogicaProveedor();
        inicioBoton();
        
        
       //if(a.Leer())
           //this.principal.setVisible(true);
        
        
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
        if(e.getSource() ==v0.lblUsuario){
            v0.lblUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==v0.lblCliente){
            v0.lblCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==v0.lblProveedor){
            v0.lblProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==v0.lblBodega){
            v0.lblBodega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==v0.rBodega){
            v0.rBodega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==v0.rVenta){
            v0.rVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==v0.rCliente){
            v0.rCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
        if(e.getSource() ==v0.rProveedor){
            v0.rProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30,30,30)));
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource() == v0.escote) {
            //ventana principal
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            v0.setLocation(x - numx, y - numy);
        }
        if (e.getSource() == v1.escote) {
            //ventana principal
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            v1.setLocation(x - numx, y - numy);
        }
        if (e.getSource() == v2.escote) {
            //ventana principal
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            v2.setLocation(x - numx, y - numy);
        }
        if (e.getSource() == v3.escote) {
            //ventana principal
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            v3.setLocation(x - numx, y - numy);
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getSource() == v0.escote) {
            //ventana principal
            numx = e.getX();
            numy = e.getY();
        }
        if (e.getSource() == v1.escote) {
            //ventana principal
            numx = e.getX();
            numy = e.getY();
        }
        if (e.getSource() == v2.escote) {
            //ventana principal
            numx = e.getX();
            numy = e.getY();
        }
        if (e.getSource() == v3.escote) {
            //ventana principal
            numx = e.getX();
            numy = e.getY();
        }
        if(e.getSource() ==v0.lblUsuario){
            v0.lblUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==v0.lblCliente){
            v0.lblCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==v0.lblProveedor){
            v0.lblProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==v0.lblBodega){
            v0.lblBodega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==v0.rBodega){
            v0.rBodega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==v0.rVenta){
            v0.rVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==v0.rCliente){
            v0.rCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }
        if(e.getSource() ==v0.rProveedor){
            v0.rProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
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
        v0.lblUsuario.addMouseMotionListener(this);
        v0.lblUsuario.addMouseListener(this);   
        v0.lblBodega.addMouseMotionListener(this);
        v0.lblBodega.addMouseListener(this);
        v0.lblCliente.addMouseMotionListener(this);
        v0.lblCliente.addMouseListener(this);
        v0.lblProveedor.addMouseMotionListener(this);
        v0.lblProveedor.addMouseListener(this);
        v0.rBodega.addMouseMotionListener(this);
        v0.rBodega.addMouseListener(this);
        v0.rCliente.addMouseMotionListener(this);
        v0.rCliente.addMouseListener(this);
        v0.rProveedor.addMouseMotionListener(this);
        v0.rProveedor.addMouseListener(this);
        v0.rVenta.addMouseMotionListener(this);
        v0.rVenta.addMouseListener(this);
        v0.escote.addMouseMotionListener(this);
        v0.setLocationRelativeTo(null);
        
        
        v1.escote.addMouseMotionListener(this);
        v1.escote.addMouseListener(this);
        v1.tabla.addMouseListener(this);
        v1.caja_txt.addKeyListener(this);
        v1.save.addActionListener(this);
        v1.uptade.addActionListener(this);
        v1.clear.addActionListener(this);
        v1.setVisible(true);
        
        v2.escote.addMouseMotionListener(this);
        v2.escote.addMouseListener(this);
        v2.tabla.addMouseListener(this);
        v2.caja_txt.addKeyListener(this);
        v2.save.addActionListener(this);
        v2.uptade.addActionListener(this);
        v2.clear.addActionListener(this);
        v2.setVisible(true);
        
        v3.escote.addMouseMotionListener(this);
        v3.escote.addMouseListener(this);
        v3.tabla.addMouseListener(this);
        v3.caja_txt.addKeyListener(this);
        v3.save.addActionListener(this);
        v3.uptade.addActionListener(this);
        v3.clear.addActionListener(this);
        v3.setVisible(true);
       
        
    }
}
