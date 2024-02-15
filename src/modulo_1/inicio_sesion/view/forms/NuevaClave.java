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
    
    public NuevaClave(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
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
                Persona persona = pc.busquedaBinaria2(pc.getPersonas(), txtDNI.getText(),"dni");
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
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
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

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 110, 30));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Modificar Contrasenia");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        txtCorreoInst.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreoInst.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        txtCorreoInst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoInstActionPerformed(evt);
            }
        });
        jPanel1.add(txtCorreoInst, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 240, 30));

        txtDNI.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDNI.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 240, 30));

        btnGuardar.setBackground(new java.awt.Color(102, 102, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 110, 30));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Correo INST:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nueva Clave:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nueva Clave:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cedula:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Clave Anterior:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));
        jPanel1.add(lblMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, 240, 20));
        jPanel1.add(txtNuevaClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 240, 30));
        jPanel1.add(txtNuevaClave2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 240, 30));
        jPanel1.add(txtClaveAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 240, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 948, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoInstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoInstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoInstActionPerformed
    
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
    private javax.swing.JLabel lblMessage;
    private javax.swing.JPasswordField txtClaveAn;
    private javax.swing.JTextField txtCorreoInst;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JPasswordField txtNuevaClave;
    private javax.swing.JPasswordField txtNuevaClave2;
    // End of variables declaration//GEN-END:variables
}
