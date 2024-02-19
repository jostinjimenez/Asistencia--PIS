package modelLuis.view;

public class AsistenciaPanel extends javax.swing.JPanel {

    public AsistenciaPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new plantilla.swing.RoundPanel();
        cbxParalelos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbxAsignaturas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbxCiclos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cbxCarreras = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxParalelos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxParalelos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 230, 30));

        jLabel1.setText("Paralelo:");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        cbxAsignaturas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxAsignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 230, 30));

        jLabel6.setText("Asignaturas:");
        roundPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        cbxCiclos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        cbxCiclos.setPreferredSize(new java.awt.Dimension(85, 22));
        cbxCiclos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCiclosItemStateChanged(evt);
            }
        });
        cbxCiclos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCiclosActionPerformed(evt);
            }
        });
        roundPanel1.add(cbxCiclos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 230, 30));

        jLabel2.setText("Ciclo:");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxCarreras.setPreferredSize(new java.awt.Dimension(85, 22));
        cbxCarreras.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCarrerasItemStateChanged(evt);
            }
        });
        cbxCarreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCarrerasActionPerformed(evt);
            }
        });
        jPanel1.add(cbxCarreras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, 30));

        roundPanel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 220, 620));

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 160, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxCiclosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCiclosItemStateChanged
   
    }//GEN-LAST:event_cbxCiclosItemStateChanged

    private void cbxCiclosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCiclosActionPerformed

    }//GEN-LAST:event_cbxCiclosActionPerformed

    private void cbxCarrerasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCarrerasItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCarrerasItemStateChanged

    private void cbxCarrerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCarrerasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCarrerasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxAsignaturas;
    private javax.swing.JComboBox<String> cbxCarreras;
    private javax.swing.JComboBox<String> cbxCiclos;
    private javax.swing.JComboBox<String> cbxParalelos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
