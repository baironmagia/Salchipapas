/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author Bairon Dajome
 */
public class v_Platillo extends javax.swing.JFrame {

    public v_Platillo() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        escote = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_des = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_valor = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        caja_txt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        filtro_combo = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        uptade = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        escote.setBackground(new java.awt.Color(30, 30, 30));

        javax.swing.GroupLayout escoteLayout = new javax.swing.GroupLayout(escote);
        escote.setLayout(escoteLayout);
        escoteLayout.setHorizontalGroup(
            escoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        escoteLayout.setVerticalGroup(
            escoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel1.setText("Descripcion Del Platillo :");

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel2.setText("Valor Del Platillo :");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        caja_txt.setBackground(new java.awt.Color(40, 45, 52));
        caja_txt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        caja_txt.setForeground(new java.awt.Color(255, 255, 255));
        caja_txt.setBorder(null);
        caja_txt.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel9.add(caja_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 220, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cajaImg.png"))); // NOI18N
        jPanel9.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 15, -1, -1));

        filtro_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Descripcion" }));
        jPanel9.add(filtro_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 166, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgSave.png"))); // NOI18N
        save.setBorder(null);
        save.setBorderPainted(false);
        save.setContentAreaFilled(false);
        save.setFocusable(false);
        save.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgSave2.png"))); // NOI18N
        save.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgSave2.png"))); // NOI18N
        save.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgSave2.png"))); // NOI18N

        uptade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgUpdate.png"))); // NOI18N
        uptade.setBorder(null);
        uptade.setBorderPainted(false);
        uptade.setContentAreaFilled(false);
        uptade.setFocusable(false);
        uptade.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgUpdate2.png"))); // NOI18N
        uptade.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgUpdate2.png"))); // NOI18N
        uptade.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgUpdate2.png"))); // NOI18N

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgDelete.png"))); // NOI18N
        delete.setBorder(null);
        delete.setBorderPainted(false);
        delete.setContentAreaFilled(false);
        delete.setFocusable(false);
        delete.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgDelete2.png"))); // NOI18N
        delete.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgDelete2.png"))); // NOI18N
        delete.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgDelete2.png"))); // NOI18N

        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgClear.png"))); // NOI18N
        clear.setBorder(null);
        clear.setBorderPainted(false);
        clear.setContentAreaFilled(false);
        clear.setFocusable(false);
        clear.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgClear2.png"))); // NOI18N
        clear.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgClear2.png"))); // NOI18N
        clear.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/imgClear2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(save)
                .addGap(10, 10, 10)
                .addComponent(uptade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(delete)
                .addGap(10, 10, 10)
                .addComponent(clear)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uptade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, 70));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_des, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_des, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Descripcion", "Valor", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scroll.setViewportView(tabla);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(escote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField caja_txt;
    public javax.swing.JButton clear;
    public javax.swing.JButton delete;
    public javax.swing.JPanel escote;
    public javax.swing.JComboBox<String> filtro_combo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    public javax.swing.JButton save;
    public javax.swing.JScrollPane scroll;
    public javax.swing.JTable tabla;
    public javax.swing.JTextField txt_des;
    public javax.swing.JTextField txt_valor;
    public javax.swing.JButton uptade;
    // End of variables declaration//GEN-END:variables
}
