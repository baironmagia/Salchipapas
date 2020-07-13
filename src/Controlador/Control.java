package Controlador;
import Modelo.Archivo;
import Modelo.LogicaBodega;
import Modelo.LogicaCliente;
import Modelo.LogicaCompraDia;
import Modelo.LogicaProveedor;
import Modelo.LogicaUsuario;
import Vista.Principal;
import Vista.v_CompraDia;
import Vista.v_bodega;
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
import javax.swing.JOptionPane;

public class Control implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    public int numx, numy;
    public Principal v0;
    public static v_usuario v1;
    public static v_cliente v2;
    public static v_proveedor v3;
    public static v_bodega v4;
    public static v_CompraDia v5;
    
    
    private Archivo a;
    private LogicaUsuario USU;
    private LogicaCliente CLI;
    private LogicaProveedor PROV;
    private LogicaBodega BOD;
    private LogicaCompraDia COMP;
  
    public Control() {
        a=new Archivo();
        System.out.println("ertrr");
        v0=new Principal();
        v1=new v_usuario();
        v2=new v_cliente();
        v3=new v_proveedor();
        v4=new v_bodega();
        v5=new v_CompraDia();
        
        CLI=new LogicaCliente();
        USU=new LogicaUsuario();
        PROV=new LogicaProveedor();
        BOD=new LogicaBodega();
        COMP =new LogicaCompraDia();
        inicioBoton();
        
       //if(a.Leer())
       //this.principal.setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //------- eventos action para los usuario ----------------//
        if(e.getSource()==v1.save){
           USU.Add();
        }
        else if(e.getSource()==v1.uptade){
            USU.Update();
        }
        else if(e.getSource()==v1.clear){
            USU.LimpiarCajas();
        }
        //---------- eventos action para los clientes --------//
        if(e.getSource()==v2.save){
           CLI.Add();
        }
        else if(e.getSource()==v2.uptade){
            CLI.Update();
        }
        else if(e.getSource()==v2.clear){
            CLI.LimpiarCajas();
        }
        //---------- eventos action para los proveedores --------//
        
        if(e.getSource()==v3.save){
           PROV.Add();
        }
        else if(e.getSource()==v3.uptade){
            PROV.Update();
        }
        else if(e.getSource()==v3.clear){
            PROV.LimpiarCajas();
        }
        //--------- eventos action para bodega ---------------------//
       if(e.getSource()==v4.btn_detalle){
            BOD.CargarTabla(v4.tabla, BOD.modelo);
        }
        else if(e.getSource()==v4.btn_consulta){
            BOD.CargarTabla1(v4.filtro_combo.getSelectedIndex(), "", v4.tabla1, BOD.modelo1);
        }
       //--------------------------------------------------------------------
       
        if(e.getSource()==v5.save){
           COMP.Add();
        }
        else if(e.getSource()==v5.uptade){
           COMP.Update();
        }
        else if(e.getSource()==v5.clear){
            COMP.LimpiarCajas();
        }
        else if(e.getSource()==v5.btn_consulta){
            COMP.CargarTabla(v5.filtro_combo.getSelectedIndex(), "", v5.tabla, COMP.modelo);
        }
       
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() ==v2.tabla){
            CLI.Select(v2.tabla);
        }
        if(e.getSource() ==v3.tabla){
            PROV.Select(v3.tabla);
        }
        if(e.getSource() ==v4.tabla1){
            BOD.Select(v4.tabla1);
        }
        if(e.getSource() ==v5.tabla){
            COMP.Select(v5.tabla);
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
            //ventana de usuario
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            v1.setLocation(x - numx, y - numy);
        }
        if (e.getSource() == v2.escote) {
            //ventana cliente
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            v2.setLocation(x - numx, y - numy);
        }
        if (e.getSource() == v3.escote) {
            //ventana proveedores
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            v3.setLocation(x - numx, y - numy);
        }
        if (e.getSource() == v4.escote) {
            //ventana proveedores
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            v4.setLocation(x - numx, y - numy);
        }
        if (e.getSource() == v5.escote) {
            //ventana proveedores
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            v5.setLocation(x - numx, y - numy);
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
            //ventana usuario
            numx = e.getX();
            numy = e.getY();
        }
        if (e.getSource() == v2.escote) {
            //ventana cliente
            numx = e.getX();
            numy = e.getY();
        }
        if (e.getSource() == v3.escote) {
            //ventana proveedor
            numx = e.getX();
            numy = e.getY();
        }
        if (e.getSource() == v4.escote) {
            //ventana proveedor
            numx = e.getX();
            numy = e.getY();
        }
        if (e.getSource() == v5.escote) {
            //ventana proveedor
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
        if(e.getSource()==v2.caja_txt){
            CLI.CargarTabla(v2.filtro_combo.getSelectedIndex(),v2.caja_txt.getText(),v2.tabla,CLI.modelo);
        }
        if(e.getSource()==v3.caja_txt){
            PROV.CargarTabla(v3.filtro_combo.getSelectedIndex(),v3.caja_txt.getText(),v3.tabla,PROV.modelo);
        }
        if(e.getSource()==v4.caja_txt){
            BOD.CargarTabla1(v4.filtro_combo.getSelectedIndex(),v4.caja_txt.getText(),v4.tabla1,BOD.modelo1);
            
        }
        if(e.getSource()==v5.caja_txt){
            COMP.CargarTabla(v5.filtro_combo.getSelectedIndex(),v5.caja_txt.getText(),v5.tabla,COMP.modelo);
            
        }
    }
    
    private void inicioBoton(){
//        v0.lblUsuario.addMouseMotionListener(this);
//        v0.lblUsuario.addMouseListener(this);   
//        v0.lblBodega.addMouseMotionListener(this);
//        v0.lblBodega.addMouseListener(this);
//        v0.lblCliente.addMouseMotionListener(this);
//        v0.lblCliente.addMouseListener(this);
//        v0.lblProveedor.addMouseMotionListener(this);
//        v0.lblProveedor.addMouseListener(this);
//        v0.rBodega.addMouseMotionListener(this);
//        v0.rBodega.addMouseListener(this);
//        v0.rCliente.addMouseMotionListener(this);
//        v0.rCliente.addMouseListener(this);
//        v0.rProveedor.addMouseMotionListener(this);
//        v0.rProveedor.addMouseListener(this);
//        v0.rVenta.addMouseMotionListener(this);
//        v0.rVenta.addMouseListener(this);
//        v0.escote.addMouseMotionListener(this);
//        v0.setLocationRelativeTo(null);
//        v0.setVisible(true);
        
        
//        v1.escote.addMouseMotionListener(this);
//        v1.escote.addMouseListener(this);
//        v1.tabla.addMouseListener(this);
//        v1.caja_txt.addKeyListener(this);
//        v1.save.addActionListener(this);
//        v1.uptade.addActionListener(this);
//        v1.clear.addActionListener(this);
//        v1.setVisible(true);
        
//        v2.escote.addMouseMotionListener(this);
//        v2.escote.addMouseListener(this);
//        v2.tabla.addMouseListener(this);
//        v2.caja_txt.addKeyListener(this);
//        v2.save.addActionListener(this);
//        v2.uptade.addActionListener(this);
//        v2.clear.addActionListener(this);
//        v2.setVisible(true);

        //para asignar eventos a cada boton en este caso de la ventana de proveedor
//        v3.escote.addMouseMotionListener(this);
//        v3.escote.addMouseListener(this);
//        v3.tabla.addMouseListener(this);
//        v3.caja_txt.addKeyListener(this);
//        v3.save.addActionListener(this);
//        v3.uptade.addActionListener(this);
//        v3.clear.addActionListener(this);
//        v3.setVisible(true);
//
//        v4.escote.addMouseMotionListener(this);
//        v4.escote.addMouseListener(this);
//        v4.tabla1.addMouseListener(this);
//        v4.caja_txt.addKeyListener(this);
//        v4.btn_detalle.addActionListener(this);
//        v4.btn_consulta.addActionListener(this);
//        v4.setVisible(true);
//       

        v5.escote.addMouseMotionListener(this);
        v5.escote.addMouseListener(this);
        v5.tabla.addMouseListener(this);
        v5.caja_txt.addKeyListener(this);
        v5.save.addActionListener(this);
        v5.uptade.addActionListener(this);
        v5.clear.addActionListener(this);
        v5.btn_consulta.addActionListener(this);
        v5.setVisible(true);
        
    }
}
