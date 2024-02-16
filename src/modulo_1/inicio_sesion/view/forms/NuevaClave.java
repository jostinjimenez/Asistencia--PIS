package modulo_1.inicio_sesion.view.forms;

import ModuloEstudianteDocente.controlador.EstudianteController;
import ModuloEstudianteDocente.controlador.DocenteController;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.controller.PersonaController;

import javax.swing.*;

import modulo_1.inicio_sesion.view.tablas.ModeloTablaCuenta;
import model.Cuenta;
import model.Persona;

public class NuevaClave extends javax.swing.JDialog {

    public NuevaClave(java.awt.Frame parent, boolean modal, ModeloTablaCuenta mtp, JTable jTable1) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        pc.setIndex(-1);
        cc.setIndex(-1);

//        cargarVista(mtp, jTable1);
        btnGuardar.addActionListener(e -> validarCuenta());
        btnCancelar.addActionListener(e -> this.dispose());
    }

    public NuevaClave(java.awt.Frame parent, boolean modal, int x, int y) {
        super(parent, modal);
        initComponents();
        this.setLocation(x, y);
        this.setResizable(false);

        btnGuardar.addActionListener(e -> validarCuenta());
        btnCancelar.addActionListener(e -> this.dispose());
    }

    //Variables
    private PersonaController pc = new PersonaController();
    private CuentaController cc = new CuentaController();

//    //Metodos
//    public void cargarVista(ModeloTablaCuenta mtp, JTable jTable1) {
//        pc.setIndex(jTable1.getSelectedRow());
//        if (pc.getIndex() < 0) {
//            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            try {
//                isEditing = true;
//                cc.setCuenta(mtp.getCuentas().get(cc.getIndex()));
//                txtCorreoInst.setText(cc.getPersona(cc.getCuenta().getId()).getNombre());
//                txtNuevaClave.setText(cc.getPersona(cc.getCuenta().getId()).getApellido());
//                txtTelefono.setText(cc.getPersona(cc.getCuenta().getId()).getDni());
//                txtVerClave.setText(cc.getPersona(cc.getCuenta().getId()).getCorreo_personal());
////                txtFechaNacim.setDate(cc.getPersona(cc.getCuenta().getId()).getFecha_nacimiento());
//                txtClaveAnterior.setText(cc.getPersona(cc.getCuenta().getId()).getTelefono());
//                txtTelefono.setEnabled(false);
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                System.out.println(e.getMessage());
//                throw new RuntimeException(e);
//            }
//        }
//    }
    public Boolean validar() {
        return !txtCorreoInst.getText().trim().isEmpty()
                && !txtNuevaClave.getText().trim().isEmpty()
                && !txtDNI.getText().trim().isEmpty()
                && !txtNuevaClave2.getText().trim().isEmpty()
                && !txtClaveAn.getText().trim().isEmpty();
    }

    public void validarCuenta() {
        if (validar()) {
            try {
                Persona persona = pc.busquedaBinaria2(pc.getPersonas(), txtDNI.getText(), "dni");
                Cuenta cuenta = cc.validarCuenta(txtCorreoInst.getText(), persona);
                if (cuenta == null) {
                    JOptionPane.showMessageDialog(null, "La cuenta no existe", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (verificarClaves() == true) {
                        String claveCifrada = cc.cifrar(txtNuevaClave.getText(), 10);
                        cc.getCuenta().setClave(claveCifrada);
                        if (cc.update()) {
                            JOptionPane.showMessageDialog(null, "Su clave ha sido actualizada", "Clave Actualizada", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println("ERROR AL VALIDAR SU CUENTA " + e);
            }
        }
    }

    private Boolean verificarClaves() {

        if (txtNuevaClave.getText().equalsIgnoreCase(txtNuevaClave2.getText())) {
            lblMessage.setText("Claves validadas");
            return true;
        } else {
            lblMessage.setText("Claves no son considentes");
            return false;

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        txtCorreoInst = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        txtNuevaClave = new javax.swing.JPasswordField();
        txtNuevaClave2 = new javax.swing.JPasswordField();
        txtClaveAn = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(740, 523));
        setMinimumSize(new java.awt.Dimension(740, 523));
        setPreferredSize(new java.awt.Dimension(740, 523));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(948, 406));
        jPanel1.setPreferredSize(new java.awt.Dimension(948, 406));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(740, 523));
        jPanel2.setMinimumSize(new java.awt.Dimension(740, 523));
        jPanel2.setPreferredSize(new java.awt.Dimension(740, 523));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setText("Modificar Contrasenia");

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtCorreoInst.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreoInst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoInstActionPerformed(evt);
            }
        });

        txtDNI.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(225, 233, 243));
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Correo INST:");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Nueva Clave:");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Nueva Clave:");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Cedula:");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Clave Anterior:");

        txtNuevaClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevaClaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreoInst, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClaveAn, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNuevaClave2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNuevaClave, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(jLabel1)))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel5)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel6)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCorreoInst, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtClaveAn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtNuevaClave2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtNuevaClave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 440));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoInstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoInstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoInstActionPerformed

    private void txtNuevaClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevaClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNuevaClaveActionPerformed

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

    }

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                NuevoUsuario dialog = new NuevoUsuario(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JPasswordField txtClaveAn;
    private javax.swing.JTextField txtCorreoInst;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JPasswordField txtNuevaClave;
    private javax.swing.JPasswordField txtNuevaClave2;
    // End of variables declaration//GEN-END:variables
}
